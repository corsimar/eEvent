package proiect;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;

public class EventCard extends MyPanel {

    private JLabel title;
    private JLabel date;
    private JLabel category;
    private JTextArea description;
    private JLabel ticketPrice;

    public EventCard(Event event) {
        setLayout(null);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(menuColor, 1, true));

        Color color = event.getTitleColor();
        String hex = String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
        StringBuffer sb = new StringBuffer("<html><font color='");
        sb.append(hex);
        sb.append("'>");
        sb.append(event.getTitle());
        sb.append("</font> - ");
        sb.append(event.getLocation());
        sb.append("</html>");

        title = new JLabel(sb.toString());
        title.setFont(new Font("Monospaced", Font.BOLD, fontTitleSize));
        title.setForeground(textColor);
        title.setHorizontalAlignment(SwingConstants.LEFT);
        title.setVerticalAlignment(SwingConstants.BOTTOM);
        add(title);

        sb = new StringBuffer(event.getDate());
        sb.append(", ");
        sb.append("ora ");
        sb.append(event.getHour());

        date = new JLabel(sb.toString());
        date.setFont(new Font("Monospaced", Font.PLAIN, fontMenuSize));
        date.setForeground(textColor);
        date.setHorizontalAlignment(SwingConstants.LEFT);
        date.setVerticalAlignment(SwingConstants.TOP);
        add(date);

        category = new JLabel(event.getCategory());
        category.setFont(new Font("Monospaced", Font.BOLD, fontMenuSize));
        category.setForeground(textColor);
        category.setHorizontalAlignment(SwingConstants.LEFT);
        category.setVerticalAlignment(SwingConstants.TOP);
        add(category);

        description = new JTextArea(event.getDescription());
        description.setFont(new Font("Monospaced", Font.PLAIN, fontInputSize));
        description.setForeground(textColor);
        description.setEditable(false);
        description.setLineWrap(true);
        add(description);

        sb = new StringBuffer("<html><p><b>Pret bilet:</b> ");
        sb.append(event.getTicketPrice());
        sb.append(" RON</p></html>");

        ticketPrice = new JLabel(sb.toString());
        ticketPrice.setFont(new Font("Monospaced", Font.PLAIN, fontMenuSize));
        ticketPrice.setForeground(textColor);
        ticketPrice.setHorizontalAlignment(SwingConstants.LEFT);
        ticketPrice.setVerticalAlignment(SwingConstants.TOP);
        add(ticketPrice);
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
