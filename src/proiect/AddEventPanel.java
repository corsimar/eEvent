package proiect;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.FontMetrics;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JSpinner.DefaultEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEventPanel extends MyPanel implements ActionListener {

    private JLabel panelTitle;
    private JLabel addTitleL, titleMaxChar, addColorL, addLocationL, addDateL, dateSelectedL, addHourL, addHourInp, addMinuteInp, addCategoryL, addDescriptionL, descriptionMaxChar, addPriceL;
    private JButton[] upArrow = new JButton[2], downArrow = new JButton[2];
    private JTextField addTitleInp;
    private JTextArea addDescriptionInp;
    private JButton addColorBtn, addDateBtn, confirmAddEventBtn;
    private JComboBox<String> addLocationCB, addCategoryCB;
    private JSpinner addPriceInp;
    private JButton[] spinnerOption = new JButton[2];
    private String dateSelected;
    private int minuteSelected, hourSelected;

    public AddEventPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        setVisible(false);

        loadImages();

        panelTitle = new JLabel("Adauga eveniment nou");
        panelTitle.setFont(new Font("Monospaced", Font.BOLD, fontTitleSize));
        panelTitle.setForeground(textColor);
        panelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(panelTitle);

        addTitleL = new JLabel("Titlu:");
        addTitleL.setFont(new Font("Monospaced", Font.BOLD, fontMenuSize));
        addTitleL.setForeground(textColor);
        addTitleL.setHorizontalAlignment(SwingConstants.LEFT);
        addTitleL.setVerticalAlignment(SwingConstants.CENTER);
        add(addTitleL);

        addTitleInp = new JTextField();
        addTitleInp.setDocument(new JTextFieldLimit(20));
        addTitleInp.setFont(new Font("Monospaced", Font.PLAIN, fontInputSize));
        addTitleInp.setForeground(textColor);
        addTitleInp.setHorizontalAlignment(SwingConstants.CENTER);
        add(addTitleInp);

        titleMaxChar = new JLabel("(maxim 20 de caractere)");
        titleMaxChar.setFont(new Font("Monospaced", Font.PLAIN, fontMenuSize));
        titleMaxChar.setForeground(textColor);
        titleMaxChar.setHorizontalAlignment(SwingConstants.LEFT);
        titleMaxChar.setVerticalAlignment(SwingConstants.CENTER);
        add(titleMaxChar);

        addColorL = new JLabel("Culoare:");
        addColorL.setFont(new Font("Monospaced", Font.BOLD, fontMenuSize));
        addColorL.setForeground(textColor);
        addColorL.setHorizontalAlignment(SwingConstants.LEFT);
        addColorL.setVerticalAlignment(SwingConstants.CENTER);
        add(addColorL);

        addColorBtn = new JButton();
        addColorBtn.setBackground(btnColor);
        addColorBtn.setFocusPainted(false);
        addColorBtn.setBorder(BorderFactory.createLineBorder(menuColor, 1));
        addColorBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addColorBtn.addActionListener(this);
        add(addColorBtn);

        addLocationL = new JLabel("Locatie:");
        addLocationL.setFont(new Font("Monospaced", Font.BOLD, fontMenuSize));
        addLocationL.setForeground(textColor);
        addLocationL.setHorizontalAlignment(SwingConstants.LEFT);
        addLocationL.setVerticalAlignment(SwingConstants.CENTER);
        add(addLocationL);

        addLocationCB = new JComboBox<String>(Utils.locations.toArray(new String[Utils.locations.size()]));
        addLocationCB.setFont(new Font("Monospaced", Font.PLAIN, fontInputSize));
        addLocationCB.setBackground(Color.WHITE);
        addLocationCB.setForeground(textColor);
        ((JLabel)addLocationCB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        add(addLocationCB);

        addDateL = new JLabel("Data:");
        addDateL.setFont(new Font("Monospaced", Font.BOLD, fontMenuSize));
        addDateL.setForeground(textColor);
        addDateL.setHorizontalAlignment(SwingConstants.LEFT);
        addDateL.setVerticalAlignment(SwingConstants.CENTER);
        add(addDateL);

        addDateBtn = new JButton("Calendar");
        addDateBtn.setFont(new Font("Monospaced", Font.BOLD, fontMenuSize));
        addDateBtn.setBackground(btnColor);
        addDateBtn.setForeground(Color.white);
        addDateBtn.setBorder(null);
        addDateBtn.setFocusPainted(false);
        addDateBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addDateBtn.addActionListener(this);
        add(addDateBtn);

        dateSelectedL = new JLabel();
        dateSelectedL.setFont(new Font("Monospaced", Font.PLAIN, fontMenuSize));
        dateSelectedL.setForeground(textColor);
        dateSelectedL.setHorizontalAlignment(SwingConstants.LEFT);
        dateSelectedL.setVerticalAlignment(SwingConstants.CENTER);
        add(dateSelectedL);

        addHourL = new JLabel("Ora:");
        addHourL.setFont(new Font("Monospaced", Font.BOLD, fontMenuSize));
        addHourL.setForeground(textColor);
        addHourL.setHorizontalAlignment(SwingConstants.LEFT);
        addHourL.setVerticalAlignment(SwingConstants.CENTER);
        add(addHourL);

        addMinuteInp = new JLabel("00");
        addMinuteInp.setFont(new Font("Monospaced", Font.BOLD, fontMenuSize));
        addMinuteInp.setForeground(textColor);
        addMinuteInp.setHorizontalAlignment(SwingConstants.RIGHT);
        addMinuteInp.setVerticalAlignment(SwingConstants.CENTER);
        add(addMinuteInp);

        addHourInp = new JLabel("12");
        addHourInp.setFont(new Font("Monospaced", Font.BOLD, fontMenuSize));
        addHourInp.setForeground(textColor);
        addHourInp.setHorizontalAlignment(SwingConstants.RIGHT);
        addHourInp.setVerticalAlignment(SwingConstants.CENTER);
        add(addHourInp);

        for(int i = 0; i < 2; i++) {
            upArrow[i] = new JButton();
            upArrow[i].setIcon(new ImageIcon(upArrowImg));
            upArrow[i].setContentAreaFilled(false);
            upArrow[i].setBorder(null);
            upArrow[i].setFocusPainted(false);
            upArrow[i].addActionListener(this);
            add(upArrow[i]);

            downArrow[i] = new JButton();
            downArrow[i].setIcon(new ImageIcon(downArrowImg));
            downArrow[i].setContentAreaFilled(false);
            downArrow[i].setBorder(null);
            downArrow[i].setFocusPainted(false);
            downArrow[i].addActionListener(this);
            add(downArrow[i]);
        }

        addCategoryL = new JLabel("Categorie:");
        addCategoryL.setFont(new Font("Monospaced", Font.BOLD, fontMenuSize));
        addCategoryL.setForeground(textColor);
        addCategoryL.setHorizontalAlignment(SwingConstants.LEFT);
        addCategoryL.setVerticalAlignment(SwingConstants.CENTER);
        add(addCategoryL);

        addCategoryCB = new JComboBox<String>(Utils.categories.toArray(new String[Utils.categories.size()]));
        addCategoryCB.setFont(new Font("Monospaced", Font.PLAIN, fontInputSize));
        addCategoryCB.setBackground(Color.white);
        addCategoryCB.setForeground(textColor);
        ((JLabel)addCategoryCB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        add(addCategoryCB);

        addDescriptionL = new JLabel("Descriere:");
        addDescriptionL.setFont(new Font("Monospaced", Font.BOLD, fontMenuSize));
        addDescriptionL.setForeground(textColor);
        addDescriptionL.setHorizontalAlignment(SwingConstants.LEFT);
        addDescriptionL.setVerticalAlignment(SwingConstants.CENTER);
        add(addDescriptionL);

        addDescriptionInp = new JTextArea();
        addDescriptionInp.setDocument(new JTextFieldLimit(300));
        addDescriptionInp.setFont(new Font("Monospaced", Font.PLAIN, fontInputSize));
        addDescriptionInp.setForeground(textColor);
        addDescriptionInp.setBorder(BorderFactory.createLineBorder(menuColor, 1));
        addDescriptionInp.setLineWrap(true);
        add(addDescriptionInp);

        descriptionMaxChar = new JLabel("(maxim 300 de caractere)");
        descriptionMaxChar.setFont(new Font("Monospaced", Font.PLAIN, fontMenuSize));
        descriptionMaxChar.setForeground(textColor);
        descriptionMaxChar.setHorizontalAlignment(SwingConstants.LEFT);
        descriptionMaxChar.setVerticalAlignment(SwingConstants.CENTER);
        add(descriptionMaxChar);

        addPriceL = new JLabel("Pret bilet:");
        addPriceL.setFont(new Font("Monospaced", Font.BOLD, fontMenuSize));
        addPriceL.setForeground(textColor);
        addPriceL.setHorizontalAlignment(SwingConstants.LEFT);
        addPriceL.setVerticalAlignment(SwingConstants.CENTER);
        add(addPriceL);

        SpinnerNumberModel snm = new SpinnerNumberModel(0, 0, 1000, 1);
        addPriceInp = new JSpinner(snm);
        addPriceInp.setFont(new Font("Monospaced", Font.PLAIN, fontInputSize));
        addPriceInp.getEditor().getComponent(0).setForeground(textColor);
		addPriceInp.getEditor().getComponent(0).setBackground(Color.WHITE);
		((DefaultEditor)addPriceInp.getEditor()).getTextField().setEditable(false);
		((DefaultEditor)addPriceInp.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        add(addPriceInp);

        for(int i = 0; i < 2; i++) {
            spinnerOption[i] = new JButton(getSpinnerOptionText(i));
            spinnerOption[i].setBackground(btnColor);
            spinnerOption[i].setForeground(Color.WHITE);
            spinnerOption[i].setBorder(null);
            spinnerOption[i].setFocusPainted(false);
            spinnerOption[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            spinnerOption[i].addActionListener(this);
            add(spinnerOption[i]);
        }

        confirmAddEventBtn = new JButton("Adauga");
        confirmAddEventBtn.setFont(new Font("Monospaced", Font.BOLD, fontMenuSize));
        confirmAddEventBtn.setBackground(btnColor);
        confirmAddEventBtn.setForeground(Color.WHITE);
        confirmAddEventBtn.setBorder(null);
        confirmAddEventBtn.setFocusPainted(false);
        confirmAddEventBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmAddEventBtn.addActionListener(this);
        add(confirmAddEventBtn);
    }

    private String getSpinnerOptionText(int option) {
        if(option == 0) return "+100";
        else return "-100";
    }

    private void incMinute() {
        minuteSelected++;
        if(minuteSelected == 60) minuteSelected = 0;
        if(minuteSelected < 10) addMinuteInp.setText("0" + minuteSelected);
        else addMinuteInp.setText("" + minuteSelected);
    }

    private void decMinute() {
        minuteSelected--;
        if(minuteSelected < 0) minuteSelected = 59;
        if(minuteSelected < 10) addMinuteInp.setText("0" + minuteSelected);
        else addMinuteInp.setText("" + minuteSelected);
    }
    
    private void incHour() {
        hourSelected++;
        if(hourSelected == 24) hourSelected = 0;
        if(hourSelected < 10) addHourInp.setText("0" + hourSelected);
        else addHourInp.setText("" + hourSelected);
    }

    private void decHour() {
        hourSelected--;
        if(hourSelected < 0) hourSelected = 23;
        if(hourSelected < 10) addHourInp.setText("0" + hourSelected);
        else addHourInp.setText("" + hourSelected);
    }

    public void clearEventForm() {
        addTitleInp.setText("");
        addColorBtn.setBackground(btnColor);
        addLocationCB.setSelectedIndex(0);
        dateSelectedL.setText("");
        dateSelected = null;
        addHourInp.setText("12");
        addMinuteInp.setText("00");
        hourSelected = 12;
        minuteSelected = 0;
        addCategoryCB.setSelectedIndex(0);
        addDescriptionInp.setText("");
        addPriceInp.setValue(0);
    }

    @Override
    public void resizeUI() {
        FontMetrics fm;

        panelTitle.setBounds(0, menuOffset * 3, getBounds().width, fontMenuSize * 2);

        fm = addPriceL.getFontMetrics(addPriceL.getFont());
        int textW = fm.stringWidth(addPriceL.getText());

        addTitleL.setBounds(leftMargin, panelTitle.getBounds().y + panelTitle.getBounds().height + menuOffset * 3, textW, inputH);
        addTitleInp.setBounds(addTitleL.getBounds().x + addTitleL.getBounds().width + menuOffset * 3, addTitleL.getBounds().y, inputW, inputH);
        titleMaxChar.setBounds(addTitleInp.getBounds().x + addTitleInp.getBounds().width + menuOffset * 3, addTitleInp.getBounds().y, inputW, inputH);

        addColorL.setBounds(leftMargin, addTitleL.getBounds().y + addTitleL.getBounds().height + menuOffset * 3, textW, inputH);
        addColorBtn.setBounds(addTitleInp.getBounds().x + addTitleInp.getBounds().width - inputH, addColorL.getBounds().y, inputH, inputH);

        addLocationL.setBounds(leftMargin, addColorL.getBounds().y + addColorL.getBounds().height + menuOffset * 3, textW, inputH);
        addLocationCB.setBounds(addTitleInp.getBounds().x, addLocationL.getBounds().y, inputW, inputH);

        addDateL.setBounds(leftMargin, addLocationL.getBounds().y + addLocationL.getBounds().height + menuOffset * 3, textW, inputH);
        addDateBtn.setBounds(addTitleInp.getBounds().x + addTitleInp.getBounds().width - btnW, addDateL.getBounds().y, btnW, btnH);
        dateSelectedL.setBounds(titleMaxChar.getBounds().x, addDateL.getBounds().y, inputW, inputH);

        addHourL.setBounds(leftMargin, addDateL.getBounds().y + addDateL.getBounds().height + menuOffset * 3, textW, inputH);
        fm = addMinuteInp.getFontMetrics(addMinuteInp.getFont());
        int w = fm.stringWidth("00");
        addMinuteInp.setBounds(addHourL.getBounds().x + addHourL.getBounds().width + menuOffset * 3 + inputW - w, addHourL.getBounds().y, w, inputH);
        addHourInp.setBounds(addMinuteInp.getBounds().x - w - menuOffset * 3, addMinuteInp.getBounds().y, w, inputH);
        upArrow[0].setBounds(addMinuteInp.getBounds().x + (addMinuteInp.getBounds().width - upArrowImg.getWidth(upArrow[0])) / 2, addMinuteInp.getBounds().y - upArrowImg.getHeight(upArrow[0]) / 2, upArrowImg.getWidth(upArrow[0]), upArrowImg.getHeight(upArrow[0]));
        upArrow[1].setBounds(addHourInp.getBounds().x + (addHourInp.getBounds().width - upArrowImg.getWidth(upArrow[0])) / 2, addHourInp.getBounds().y - upArrowImg.getHeight(upArrow[1]) / 2, upArrowImg.getWidth(upArrow[1]), upArrowImg.getHeight(upArrow[1]));
        downArrow[0].setBounds(upArrow[0].getBounds().x, addMinuteInp.getBounds().y + fm.getHeight() + downArrowImg.getHeight(downArrow[1]), downArrowImg.getWidth(downArrow[0]), downArrowImg.getHeight(downArrow[0]));
        downArrow[1].setBounds(upArrow[1].getBounds().x, addHourInp.getBounds().y + fm.getHeight() + downArrowImg.getHeight(downArrow[1]), downArrowImg.getWidth(downArrow[1]), downArrowImg.getHeight(downArrow[1]));

        addCategoryL.setBounds(leftMargin, addHourL.getBounds().y + addHourL.getBounds().height + menuOffset * 3, textW, inputH);
        addCategoryCB.setBounds(addTitleInp.getBounds().x, addCategoryL.getBounds().y, inputW, inputH);

        addDescriptionL.setBounds(leftMargin, addCategoryL.getBounds().y + addCategoryL.getBounds().height + menuOffset * 3, textW, inputH);
        addDescriptionInp.setBounds(addTitleInp.getBounds().x, addDescriptionL.getBounds().y, inputW, inputH * 5);
        descriptionMaxChar.setBounds(titleMaxChar.getBounds().x, addDescriptionL.getBounds().y, inputW, inputH);

        addPriceL.setBounds(leftMargin, addDescriptionL.getBounds().y + addDescriptionInp.getBounds().height + menuOffset * 3, textW, inputH);
        addPriceInp.setBounds(addTitleInp.getBounds().x + inputW / 2, addPriceL.getBounds().y, inputW / 2, inputH);
        spinnerOption[0].setBounds(descriptionMaxChar.getBounds().x, addPriceL.getBounds().y, addPriceInp.getBounds().width / 2, inputH);
        spinnerOption[1].setBounds(spinnerOption[0].getBounds().x + spinnerOption[0].getBounds().width + menuOffset * 3, addPriceL.getBounds().y, addPriceInp.getBounds().width / 2, inputH);

        confirmAddEventBtn.setBounds(leftMargin, addPriceL.getBounds().y + addPriceL.getBounds().height + menuOffset * 3, btnW, btnH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addColorBtn) {
            addColorBtn.setBackground(JColorChooser.showDialog(null, "Alege o culoare", btnColor));
        }
        else if(e.getSource() == addDateBtn) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDateTime now = LocalDateTime.now();
            MyCalendar calendar = new MyCalendar(dtf.format(now));

            dateSelected = null;
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if(calendar.isDateSelected()) {
                        if(calendar.getDate() != null) {
                            dateSelected = calendar.getDate();
                        }
                    }
                    if(!calendar.isVisible()) {
                        timer.cancel();
                        if(dateSelected != null) dateSelectedL.setText(Utils.dateToString(dateSelected));
                    }
                }
            }, 0, 50);
        }
        else if(e.getSource() == upArrow[0]) {
            incMinute();
        }
        else if(e.getSource() == upArrow[1]) {
            incHour();
        }
        else if(e.getSource() == downArrow[0]) {
            decMinute();
        }
        else if(e.getSource() == downArrow[1]) {
            decHour();
        }
        else if(e.getSource() == spinnerOption[0]) {
            int value = ((Integer)addPriceInp.getValue()) + 100, max = (Integer)(((SpinnerNumberModel)addPriceInp.getModel()).getMaximum());
            if(value > max) value = max;
            addPriceInp.setValue(value);
        }
        else if(e.getSource() == spinnerOption[1]) {
            int value = ((Integer)addPriceInp.getValue()) - 100, min = (Integer)(((SpinnerNumberModel)addPriceInp.getModel()).getMinimum());
            if(value < min) value = min;
            addPriceInp.setValue(value);
        }
        else if(e.getSource() == confirmAddEventBtn) {
            if(addTitleInp.getText().length() > 0 && dateSelected != null && addDescriptionInp.getText().length() > 0) {
                StringBuffer hour = new StringBuffer();
                if(hourSelected < 10) hour.append("0");
                hour.append(hourSelected);
                hour.append(":");
                if(minuteSelected < 10) hour.append("0");
                hour.append(minuteSelected);
                Event event = new Event(addTitleInp.getText(), addColorBtn.getBackground(), addLocationCB.getSelectedItem().toString(),
                dateSelected, hour.toString(), addCategoryCB.getSelectedItem().toString(), addDescriptionInp.getText(), (Integer)addPriceInp.getValue());

                Event.saveEvent(event);
                Main.saveInfo(1, true);
                JOptionPane.showMessageDialog(null, "Evenimentul a fost adaugat cu succes!");
                clearEventForm();
            }
            else {
                JOptionPane.showMessageDialog(null, "Completati toate campurile inainte de a adauga evenimentul!");
            }
        }
    }
}
