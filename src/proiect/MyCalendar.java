package proiect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MyCalendar extends JFrame implements ActionListener {

    private Color menuColor = Color.decode("#D9D9D9");
    private Color btnColor = Color.decode("#4D88FF");
    private Color textColor = Color.decode("#141414");

    private String currDate, date;
    private int frameW = 350, frameH = 400;
    private int squareW = frameW / 7;
    private int btnW = 140, btnH = 40;

    private MyLabel title;
    private BufferedImage leftArrowBuff, rightArrowBuff;
    private Image leftArrowImg, rightArrowImg;
    private MyButton leftArrowBtn, rightArrowBtn, confirmBtn;
    private JPanel calendarPanel;
    private MyLabel lastDay;
    private int selectedDay = 0;

    public MyCalendar(String currDate) {
        this.currDate = currDate;
        this.date = currDate;

        setSize(frameW, frameH);
        setUndecorated(true);
        getContentPane().setBackground(Color.decode("#FFFFFF"));
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        loadImages();
        setupCalendar();
    }

    private void loadImages() {
        try {
            leftArrowBuff = ImageIO.read(Main.class.getResource("/res/LeftArrow.png"));
            leftArrowImg = leftArrowBuff.getScaledInstance(18, 28, Image.SCALE_SMOOTH);
            rightArrowBuff = ImageIO.read(Main.class.getResource("/res/RightArrow.png"));
            rightArrowImg = rightArrowBuff.getScaledInstance(18, 28, Image.SCALE_SMOOTH);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    private String getDateMonth() {
        try {
            int m = Integer.parseInt(date.substring(3, 5));
            if(m == 1) return "Ianuarie";
            else if(m == 2) return "Februarie";
            else if(m == 3) return "Martie";
            else if(m == 4) return "Aprilie";
            else if(m == 5) return "Mai";
            else if(m == 6) return "Iunie";
            else if(m == 7) return "Iulie";
            else if(m == 8) return "August";
            else if(m == 9) return "Septembrie";
            else if(m == 10) return "Octombrie";
            else if(m == 11) return "Noiembrie";
            else if(m == 12) return "Decembrie";
            else return "null";
        } catch(NumberFormatException nfe) {
            nfe.printStackTrace();
            return "null";
        }
    }

    private int getDateYear() {
        return Integer.parseInt(date.substring(6));
    }

    private void setupCalendar() {
        title = new MyLabel("", textColor, new Font("Monospaced", Font.BOLD, 14), SwingConstants.CENTER, SwingConstants.CENTER);
        updateTitle();
        title.setBounds(0, 0, frameW, 50);
        add(title);

        leftArrowBtn = new MyButton("", null, null, null, null, Cursor.HAND_CURSOR);
        leftArrowBtn.setIcon(new ImageIcon(leftArrowImg));
        leftArrowBtn.addActionListener(this);
        leftArrowBtn.setBounds(squareW - leftArrowImg.getWidth(leftArrowBtn), (title.getBounds().height - leftArrowImg.getHeight(leftArrowBtn)) / 2, leftArrowImg.getWidth(leftArrowBtn), leftArrowImg.getHeight(leftArrowBtn));
        add(leftArrowBtn);

        rightArrowBtn = new MyButton("", null, null, null, null, Cursor.HAND_CURSOR);
        rightArrowBtn.setIcon(new ImageIcon(rightArrowImg));
        rightArrowBtn.addActionListener(this);
        rightArrowBtn.setBounds(frameW - squareW, (title.getBounds().height - leftArrowImg.getHeight(leftArrowBtn)) / 2, leftArrowImg.getWidth(leftArrowBtn), leftArrowImg.getHeight(leftArrowBtn));
        add(rightArrowBtn);

        initDays();

        confirmBtn = new MyButton("Confirma", new Font("Monospaced", Font.BOLD, 17), btnColor, Color.WHITE, null, Cursor.HAND_CURSOR);
        confirmBtn.addActionListener(this);
        confirmBtn.setBounds((frameW - btnW) / 2, title.getBounds().height + calendarPanel.getBounds().height + (frameH - (title.getBounds().height + calendarPanel.getBounds().height) - btnH) / 2, btnW, btnH);
        add(confirmBtn);
    }

    private void updateTitle() {
        title.setText(getDateMonth() + " " + getDateYear());
    }

    private int getDaysInAMonth() {
        try {
            int m = Integer.parseInt(date.substring(3, 5));
            int y = Integer.parseInt(date.substring(6));

            if(m == 1) return 31;
            else if(m == 2 && y % 4 == 0) return 29;
            else if(m == 2 && y % 4 != 0) return 28;
            else if(m == 3) return 31;
            else if(m == 4) return 30;
            else if(m == 5) return 31;
            else if(m == 6) return 30;
            else if(m == 7) return 31;
            else if(m == 8) return 31;
            else if(m == 9) return 30;
            else if(m == 10) return 31;
            else if(m == 11) return 30;
            else if(m == 12) return 31;
            else return 0;

        } catch(NumberFormatException nfe) {
            nfe.printStackTrace();
            return 0;
        }
    }

    int dayX, dayY, dayLine;
    private void addDay(int index) {
        JLabel label = new JLabel((index + 1) + "");
        label.setFont(new Font("Monospaced", Font.BOLD, 12));
        label.setOpaque(true);
        label.setBackground(Color.white);
        label.setForeground(textColor);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(lastDay != null) lastDay.setBackground(Color.white);

                label.setBackground(menuColor);
                lastDay = (MyLabel)label;
                
                selectedDay = index + 1;
            }
        });
        label.setBounds(dayX, dayY, squareW, squareW);
        calendarPanel.add(label);

        dayX += squareW;

        dayLine++;
        if(dayLine == 7) {
            dayLine = 0;
            dayX = 0;
            dayY += squareW;
        }
    }

    private void initDays() {
        int days = getDaysInAMonth();
        dayX = 0; dayY = 0; dayLine = 0;

        calendarPanel = new JPanel();
        calendarPanel.setBackground(Color.white);
        calendarPanel.setLayout(null);
        calendarPanel.setBounds(0, title.getBounds().height, frameW, squareW * 5);
        add(calendarPanel);
        
        for(int i = 0; i < days; i++) {
            addDay(i);
        }
    }

    private void updateDays() {
        if(lastDay != null) {
            lastDay.setBackground(Color.white);
            lastDay = null;
            selectedDay = 0;
        }
        int c = calendarPanel.getComponentCount(), d = getDaysInAMonth();
        if(c != d) {
            if(c < d) {
                for(int i = 0; i < d - c; i++) {
                    addDay(c + i);
                }
            }
            if(c > d) {
                for(int i = c - 1; i > d - 1; i--) {
                    calendarPanel.remove(calendarPanel.getComponent(i));

                    dayX -= squareW;
                    dayLine--;
                }
            }
        }

        repaint();
    }

    private boolean isDateValid() {
        if(Integer.parseInt(currDate.substring(6)) == Integer.parseInt(date.substring(6))) {
            return (Integer.parseInt(currDate.substring(3, 5)) <= Integer.parseInt(date.substring(3, 5)));
        }
        else return (Integer.parseInt(currDate.substring(6)) < Integer.parseInt(date.substring(6)));
    }

    private void decrementDate() {
        try {
            StringBuffer sb = new StringBuffer();
            int d = Integer.parseInt(date.substring(0, 2));
            int m = Integer.parseInt(date.substring(3, 5));
            int y = Integer.parseInt(date.substring(6));
            if(m == 1) {
                y -= 1;
                m = 12;
            }
            else m -= 1;

            if(d < 10) sb.append("0" + d + ".");
            else sb.append(d + ".");
            if(m < 10) sb.append("0" + m + ".");
            else sb.append(m + ".");
            sb.append(y);
            
            date = sb.toString();
            updateTitle();

        } catch(NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

    private void incrementDate() {
        try {
            StringBuffer sb = new StringBuffer();
            int d = Integer.parseInt(date.substring(0, 2));
            int m = Integer.parseInt(date.substring(3, 5));
            int y = Integer.parseInt(date.substring(6));
            if(m == 12) {
                y += 1;
                m = 1;
            }
            else m += 1;

            if(d < 10) sb.append("0" + d + ".");
            else sb.append(d + ".");
            if(m < 10) sb.append("0" + m + ".");
            else sb.append(m + ".");
            sb.append(y);
            
            date = sb.toString();
            updateTitle();

        } catch(NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

    public boolean isDateSelected() {
        return (lastDay != null);
    }

    private String formatDate() {
        StringBuffer sb = new StringBuffer();
        sb = new StringBuffer();
        if(selectedDay < 10) sb.append("0" + selectedDay + ".");
        else sb.append(selectedDay + ".");
        sb.append(date.substring(3, 5) + ".");
        sb.append(date.substring(6));
        return sb.toString();
    }

    public String getDate() {
        String s = null;
        if(currDate.substring(6).equals(date.substring(6)) && currDate.substring(3, 5).equals(date.substring(3, 5))) {
            if(selectedDay >= Integer.parseInt(currDate.substring(0, 2))) {
                s = formatDate();
            }
            else {
                selectedDay = 0;
                if(lastDay != null) lastDay.setBackground(Color.white);
                lastDay = null;
                JOptionPane.showMessageDialog(null, "Nu poti alege o data anterioara zilei curente!");
            }
        }
        else {
            s = formatDate();
        }

        return s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == leftArrowBtn) {
            decrementDate();
            if(!isDateValid()) {
                incrementDate();
                JOptionPane.showMessageDialog(null, "Nu poti alege o data inainte de data curenta!");
            }
            updateDays();
        }
        else if(e.getSource() == rightArrowBtn) {
            incrementDate();
            updateDays();
        }
        else if(e.getSource() == confirmBtn) {
            dispose();
        }
    }
}
