package proiect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.print.*;

public class TicketPanel extends MyPanel {
    private JLabel box;
    private int boxW = 400, boxH = 300;
    private MyLabel title, location, date, category, price;
    private User user;
    private Event event;
    private MyButton buyBtn, backBtn, fileBtn;
    private boolean owned;
    private TicketPanel panel;
    private ButtonListener buttonListener;

    public TicketPanel() {
        panel = this;

        setLayout(null);
        setBackground(Color.WHITE);
        setVisible(false);

        buttonListener = new ButtonListener();

        box = new JLabel();
        box.setOpaque(true);
        box.setBackground(Color.WHITE);
        box.setBorder(BorderFactory.createLineBorder(menuColor, 2));

        title = new MyLabel("", textColor, new Font("Monospaced", Font.BOLD, fontTitleSize), SwingConstants.CENTER, SwingConstants.CENTER);
        add(title);

        location = new MyLabel("", textColor, new Font("Monospaced", Font.PLAIN, fontMenuSize), SwingConstants.LEFT, SwingConstants.CENTER);
        add(location);

        date = new MyLabel("", textColor, new Font("Monospaced", Font.PLAIN, fontMenuSize), SwingConstants.LEFT, SwingConstants.CENTER);
        add(date);

        category = new MyLabel("", textColor, new Font("Monospaced", Font.PLAIN, fontMenuSize), SwingConstants.LEFT, SwingConstants.CENTER);
        add(category);

        price = new MyLabel("", textColor, new Font("Monospaced", Font.PLAIN, fontMenuSize), SwingConstants.LEFT, SwingConstants.CENTER);
        add(price);

        buyBtn = new MyButton("", new Font("Monospaced", Font.BOLD, fontMenuSize), btnColor, Color.WHITE, null, Cursor.HAND_CURSOR);
        buyBtn.addActionListener(buttonListener);
        add(buyBtn);

        fileBtn = new MyButton("Fisier", new Font("Monospaced", Font.BOLD, fontMenuSize), btnColor, Color.WHITE, null, Cursor.HAND_CURSOR);
        fileBtn.addActionListener(buttonListener);
        fileBtn.setVisible(false);
        add(fileBtn);

        backBtn = new MyButton("", new Font("Monospaced", Font.BOLD, fontMenuSize), btnColor, Color.WHITE, null, Cursor.HAND_CURSOR);
        backBtn.addActionListener(buttonListener);
        add(backBtn);

        add(box);
    }

    public void setTicket(User user, Event event, boolean owned) {
        this.user = user;
        this.event = event;
        this.owned = owned;
        setInfo();
    }

    public void setInfo() {
        String hex = String.format("#%02X%02X%02X", event.getTitleColor().getRed(), event.getTitleColor().getGreen(), event.getTitleColor().getBlue());
        StringBuffer sb = new StringBuffer("<html><font color='");
        sb.append(hex);
        sb.append("'>");
        sb.append(event.getTitle());
        sb.append("</font></html>");
        title.setText(sb.toString());
        location.setText("<html><b>Locatie: </b>" + event.getLocation() + "</html>");
        date.setText("<html><b>Data: </b>" + event.getDate() + "</html>");
        category.setText("<html><b>Categorie: </b>" + event.getCategory() + "</html>");
        price.setText("<html><b>Pret: </b>" + event.getTicketPrice() + " RON</html>");
        if(!owned) {
            buyBtn.setText("Cumpara"); backBtn.setText("Renunta");
            fileBtn.setVisible(false);
        }
        else {
            buyBtn.setText("Printeaza"); backBtn.setText("Inapoi");
            fileBtn.setVisible(true);
        }
    }

    @Override
    public void resizeUI() {
        int hUnit = boxH / 9;

        box.setBounds((getBounds().width - boxW) / 2, (getBounds().height - boxH) / 2, boxW, boxH);
        title.setBounds(box.getBounds().x, box.getBounds().y, box.getBounds().width, hUnit * 2);
        location.setBounds(box.getBounds().x + leftMargin, title.getBounds().y + title.getBounds().height + hUnit, box.getBounds().width - leftMargin, hUnit);
        date.setBounds(box.getBounds().x + leftMargin, location.getBounds().y + location.getBounds().height, box.getBounds().width - leftMargin, hUnit);
        category.setBounds(box.getBounds().x + leftMargin, date.getBounds().y + date.getBounds().height, box.getBounds().width - leftMargin, hUnit);
        price.setBounds(box.getBounds().x + leftMargin, category.getBounds().y + category.getBounds().height, category.getBounds().width - leftMargin, hUnit);
        buyBtn.setBounds(box.getBounds().x + box.getBounds().width - btnW, box.getBounds().y + box.getBounds().height + menuOffset * 3, btnW, btnH);
        fileBtn.setBounds(buyBtn.getBounds().x, buyBtn.getBounds().y + buyBtn.getBounds().height + menuOffset * 3, btnW, btnH);
        backBtn.setBounds(box.getBounds().x, buyBtn.getBounds().y, btnW, btnH);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buyBtn) {
                if(!owned) {
                    buyBtn.setText("Printeaza"); backBtn.setText("Inapoi");
                    Main.buyTicket(event);
                    owned = true;
                    buyBtn.removeActionListener(this);
                    buyBtn.addActionListener(buttonListener);
                    fileBtn.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Ati cumpara biletul cu succes!");
                }
                else {
                    buyBtn.setVisible(false); backBtn.setVisible(false); fileBtn.setVisible(false);
                    PrinterJob printer = PrinterJob.getPrinterJob();
                    Book book = new Book();
                    book.append(new Printer(), printer.defaultPage());
                    printer.setPageable(book);
                    if(printer.printDialog()) {
                        try {
                            printer.print();
                            buyBtn.setVisible(true); backBtn.setVisible(true); fileBtn.setVisible(true);
                        } catch (PrinterException e1) {
                            buyBtn.setVisible(true); backBtn.setVisible(true);
                            JOptionPane.showMessageDialog(null, "Eroare");
                        }
                    }
                    else {
                        buyBtn.setVisible(true); backBtn.setVisible(true); fileBtn.setVisible(true);
                    }
                }
            }
            else if(e.getSource() == fileBtn) {
                StringBuffer path = new StringBuffer(System.getProperty("user.dir"));
                path.append("\\src\\data\\tickets\\");
                StringBuffer fileName = new StringBuffer(user.getEmail());
                fileName.append("_");
                fileName.append(event.getTitle());
                fileName.append(".dat");
                path.append(fileName);

                try {
                    PrintWriter out = new PrintWriter(new FileOutputStream(path.toString()));
                    out.write(event.getTicket());
                    out.flush();
                    out.close();
                    JOptionPane.showMessageDialog(null, "Biletul a fost printat in fisier cu succes!");
                } catch(IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Printarea biletului in fisier nu a reusit!");
                }
            }
            else if(e.getSource() == backBtn) {
                Main.closeTicket();
            }
        }
    }

    class Printer implements Printable {
        @Override
        public int print(Graphics g, PageFormat pf, int pageNum) throws PrinterException {
            if(pageNum > 0) return NO_SUCH_PAGE;

            double panelW = panel.getBounds().width;
            double panelH = panel.getBounds().height;

            double pfW = pf.getImageableWidth();
            double pfH = pf.getImageableHeight();

            double x = pf.getImageableX() + (pfW - panelW) / 2;
            double y = pf.getImageableY() + (pfH - panelH) / 2;

            Graphics2D g2d = (Graphics2D)g;
            g2d.translate(x, y);
            panel.paint(g2d);

            return PAGE_EXISTS;
        }
    }
}