package proiect;

import javax.swing.*;
import java.awt.*;

public class EventCard extends MyPanel {
    private Event event;
    private MyLabel title, date, category, ticketPrice;
    private JTextArea description;

    public EventCard(Event event) {
        this.event = event;
        setLayout(null);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(menuColor, 1, true));

        Color color = event.getTitleColor();
        String hex = String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
        StringBuffer sb = new StringBuffer("<html><font color='");
        sb.append(hex); sb.append("'>"); sb.append(event.getTitle()); sb.append("</font> - "); sb.append(event.getLocation()); sb.append("</html>");

        title = new MyLabel(sb.toString(), textColor, new Font("Monospaced", Font.BOLD, fontTitleSize), SwingConstants.LEFT, SwingConstants.BOTTOM);
        add(title);

        sb = new StringBuffer(event.getDate());
        sb.append(", "); sb.append("ora "); sb.append(event.getHour());

        date = new MyLabel(sb.toString(), textColor, new Font("Monospaced", Font.PLAIN, fontMenuSize), SwingConstants.LEFT, SwingConstants.TOP);
        add(date);

        category = new MyLabel(event.getCategory(), textColor, new Font("Monospaced", Font.BOLD, fontMenuSize), SwingConstants.LEFT, SwingConstants.TOP);
        add(category);

        description = new JTextArea(event.getDescription());
        description.setFont(new Font("Monospaced", Font.PLAIN, fontInputSize));
        description.setForeground(textColor);
        description.setEditable(false);
        description.setLineWrap(true);
        add(description);

        sb = new StringBuffer("<html><p><b>Pret bilet:</b> ");
        sb.append(event.getTicketPrice()); sb.append(" RON</p></html>");

        ticketPrice = new MyLabel(sb.toString(), textColor, new Font("Monospaced", Font.PLAIN, fontMenuSize), SwingConstants.LEFT, SwingConstants.TOP);
        add(ticketPrice);
    }

    public Event getEvent() {
        return this.event;
    }

    @Override
    public void resizeUI() {
        int textW = getBounds().width - leftMargin;
        int hUnit = getBounds().height / 10;

        title.setBounds(leftMargin, 0, textW, hUnit * 2);
        date.setBounds(leftMargin, title.getBounds().y + title.getBounds().height, textW, hUnit);
        category.setBounds(leftMargin, date.getBounds().y + date.getBounds().height, textW, hUnit);
        description.setBounds(leftMargin, category.getBounds().y + category.getBounds().height, textW - leftMargin, hUnit * 4);
        ticketPrice.setBounds(leftMargin, description.getBounds().y + description.getBounds().height, textW, hUnit * 2);
    }
}
