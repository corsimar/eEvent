package proiect;

import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Timer;

public class AddEventPanel extends MyPanel implements ActionListener {

    private MyLabel panelTitle, addTitleL, titleMaxChar, addColorL, addLocationL, addDateL, dateSelectedL, addHourL, addHourInp, addMinuteInp, addCategoryL, addDescriptionL, descriptionMaxChar, addPriceL;
    private MyButton[] upArrow = new MyButton[2], downArrow = new MyButton[2];
    private JTextField addTitleInp;
    private JTextArea addDescriptionInp;
    private MyButton addColorBtn, addDateBtn, confirmAddEventBtn;
    private JComboBox<String> addLocationCB, addCategoryCB;
    private JSpinner addPriceInp;
    private MyButton[] spinnerOption = new MyButton[2];
    private String dateSelected;
    private String currDate;
    private int minuteSelected, hourSelected, currHour;
    private Calendar calendar;

    public AddEventPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        setVisible(false);

        loadImages();

        panelTitle = new MyLabel("Adauga eveniment nou", textColor, new Font("Monospaced", Font.BOLD, fontTitleSize), SwingConstants.CENTER, -1);
        add(panelTitle);

        addTitleL = new MyLabel("Titlu:", textColor, new Font("Monospaced", Font.BOLD, fontMenuSize), SwingConstants.LEFT, SwingConstants.CENTER);
        add(addTitleL);

        addTitleInp = new JTextField();
        addTitleInp.setDocument(new JTextFieldLimit(20));
        addTitleInp.setFont(new Font("Monospaced", Font.PLAIN, fontInputSize));
        addTitleInp.setForeground(textColor);
        addTitleInp.setHorizontalAlignment(SwingConstants.CENTER);
        add(addTitleInp);

        titleMaxChar = new MyLabel("(maxim 20 de caractere)", textColor, new Font("Monospaced", Font.PLAIN, fontMenuSize), SwingConstants.LEFT, SwingConstants.CENTER);
        add(titleMaxChar);

        addColorL = new MyLabel("Culoare:", textColor, new Font("Monospaced", Font.BOLD, fontMenuSize), SwingConstants.LEFT, SwingConstants.CENTER);
        add(addColorL);

        addColorBtn = new MyButton("", null, btnColor, null, BorderFactory.createLineBorder(menuColor, 1), Cursor.HAND_CURSOR);
        addColorBtn.addActionListener(this);
        add(addColorBtn);

        addLocationL = new MyLabel("Locatie:", textColor, new Font("Monospaced", Font.BOLD, fontMenuSize), SwingConstants.LEFT, SwingConstants.CENTER);
        add(addLocationL);

        addLocationCB = new JComboBox<String>(Utils.locations.toArray(new String[Utils.locations.size()]));
        addLocationCB.setFont(new Font("Monospaced", Font.PLAIN, fontInputSize));
        addLocationCB.setBackground(Color.WHITE);
        addLocationCB.setForeground(textColor);
        ((JLabel)addLocationCB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        add(addLocationCB);

        addDateL = new MyLabel("Data:", textColor, new Font("Monospaced", Font.BOLD, fontMenuSize), SwingConstants.LEFT, SwingConstants.CENTER);
        add(addDateL);

        addDateBtn = new MyButton("Calendar", new Font("Monospaced", Font.BOLD, fontMenuSize), btnColor, Color.WHITE, null, Cursor.HAND_CURSOR);
        addDateBtn.addActionListener(this);
        add(addDateBtn);

        dateSelectedL = new MyLabel("(alegeti data)", textColor, new Font("Monospaced", Font.PLAIN, fontMenuSize), SwingConstants.LEFT, SwingConstants.CENTER);
        add(dateSelectedL);

        addHourL = new MyLabel("Data:", textColor, new Font("Monospaced", Font.BOLD, fontMenuSize), SwingConstants.LEFT, SwingConstants.CENTER);
        add(addHourL);

        addMinuteInp = new MyLabel("00", textColor, new Font("Monospaced", Font.BOLD, fontMenuSize), SwingConstants.RIGHT, SwingConstants.CENTER);
        add(addMinuteInp);

        addHourInp = new MyLabel("", textColor, new Font("Monospaced", Font.BOLD, fontMenuSize), SwingConstants.RIGHT, SwingConstants.CENTER);
        add(addHourInp);

        for(int i = 0; i < 2; i++) {
            upArrow[i] = new MyButton("", null, null, null, null, -1);
            upArrow[i].setIcon(new ImageIcon(upArrowImg));
            upArrow[i].addActionListener(this);
            add(upArrow[i]);

            downArrow[i] = new MyButton("", null, null, null, null, -1);
            downArrow[i].setIcon(new ImageIcon(downArrowImg));
            downArrow[i].addActionListener(this);
            add(downArrow[i]);
        }

        addCategoryL = new MyLabel("Categorie:", textColor, new Font("Monospaced", Font.BOLD, fontMenuSize), SwingConstants.LEFT, SwingConstants.CENTER);
        add(addCategoryL);

        addCategoryCB = new JComboBox<String>(Utils.categories.toArray(new String[Utils.categories.size()]));
        addCategoryCB.setFont(new Font("Monospaced", Font.PLAIN, fontInputSize));
        addCategoryCB.setBackground(Color.white);
        addCategoryCB.setForeground(textColor);
        ((JLabel)addCategoryCB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        add(addCategoryCB);

        addDescriptionL = new MyLabel("Descriere:", textColor, new Font("Monospaced", Font.BOLD, fontMenuSize), SwingConstants.LEFT, SwingConstants.CENTER);
        add(addDescriptionL);

        addDescriptionInp = new JTextArea();
        addDescriptionInp.setDocument(new JTextFieldLimit(300));
        addDescriptionInp.setFont(new Font("Monospaced", Font.PLAIN, fontInputSize));
        addDescriptionInp.setForeground(textColor);
        addDescriptionInp.setBorder(BorderFactory.createLineBorder(menuColor, 1));
        addDescriptionInp.setLineWrap(true);
        add(addDescriptionInp);

        descriptionMaxChar = new MyLabel("(maxim 300 de caractere)", textColor, new Font("Monospaced", Font.PLAIN, fontMenuSize), SwingConstants.LEFT, SwingConstants.CENTER);
        add(descriptionMaxChar);

        addPriceL = new MyLabel("Pret bilet:", textColor, new Font("Monospaced", Font.BOLD, fontMenuSize), SwingConstants.LEFT, SwingConstants.CENTER);
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
            spinnerOption[i] = new MyButton(getSpinnerOptionText(i), new Font("Monospaced", Font.BOLD, fontMenuSize), btnColor, Color.WHITE, null, Cursor.HAND_CURSOR);
            spinnerOption[i].addActionListener(this);
            add(spinnerOption[i]);
        }

        confirmAddEventBtn = new MyButton("Adauga", new Font("Monospaced", Font.BOLD, fontMenuSize), btnColor, Color.WHITE, null, Cursor.HAND_CURSOR);
        confirmAddEventBtn.addActionListener(this);
        add(confirmAddEventBtn);
    }

    private String getSpinnerOptionText(int option) {
        if(option == 0) return "+100";
        else return "-100";
    }

    private void incMinute() {
        if(dateSelected == null) {
            JOptionPane.showMessageDialog(null, "Trebuie sa alegi data evenimentului inainte de a seta ora!");
        }
        else {
            minuteSelected++;
            if(minuteSelected == 60) minuteSelected = 0;
            if(minuteSelected < 10) addMinuteInp.setText("0" + minuteSelected);
            else addMinuteInp.setText("" + minuteSelected);
        }
    }

    private void decMinute() {
        if(dateSelected == null) {
            JOptionPane.showMessageDialog(null, "Trebuie sa alegi data evenimentului inainte de a seta ora!");
        }
        else {
            minuteSelected--;
            if(minuteSelected < 0) minuteSelected = 59;
            if(minuteSelected < 10) addMinuteInp.setText("0" + minuteSelected);
            else addMinuteInp.setText("" + minuteSelected);
        }
    }
    
    private void incHour() {
        if(dateSelected == null) {
            JOptionPane.showMessageDialog(null, "Trebuie sa alegi data evenimentului inainte de a seta ora!");
        }
        else if(currDate.equals(dateSelected) && (hourSelected + 1) == 24) {
            JOptionPane.showMessageDialog(null, "Nu poti alege ora curenta sau o ora anterioara orei curente pentru un eveniment din ziua curenta!");
        }
        else {
            hourSelected++;
            if(hourSelected == 24) hourSelected = 0;
            if(hourSelected < 10) addHourInp.setText("0" + hourSelected);
            else addHourInp.setText("" + hourSelected);
        }
    }

    private void decHour() {
        if(dateSelected == null) {
            JOptionPane.showMessageDialog(null, "Trebuie sa alegi data evenimentului inainte de a seta ora!");
        }
        else if(currDate.equals(dateSelected) && (hourSelected - 1) <= currHour) {
            JOptionPane.showMessageDialog(null, "Nu poti alege ora curenta sau o ora anterioara orei curente pentru un eveniment din ziua curenta!");
        }
        else {
            hourSelected--;
            if(hourSelected < 0) hourSelected = 23;
            if(hourSelected < 10) addHourInp.setText("0" + hourSelected);
            else addHourInp.setText("" + hourSelected);
        }
    }

    public void clearEventForm() {
        addTitleInp.setText("");
        addColorBtn.setBackground(btnColor);
        addLocationCB.setSelectedIndex(0);
        dateSelectedL.setText("(alegeti data)");
        dateSelected = null;
        calendar = Calendar.getInstance();
        currHour = calendar.get(Calendar.HOUR_OF_DAY);
        hourSelected = currHour + 1;
        addHourInp.setText(hourSelected + "");
        addMinuteInp.setText("00");
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
            Color color = JColorChooser.showDialog(null, "Alege o culoare", btnColor);
            if(color != null) {
                int grayscale = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                if(grayscale > 210) JOptionPane.showMessageDialog(null, "Nuanta culorii alese este prea deschisa! Alegeti o nuanta mai inchisa!");
                else addColorBtn.setBackground(color);
            }
        }
        else if(e.getSource() == addDateBtn) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDateTime now = LocalDateTime.now();
            currDate = dtf.format(now);
            MyCalendar myCalendar = new MyCalendar(currDate);

            dateSelected = null;
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if(myCalendar.isDateSelected()) {
                        if(myCalendar.getDate() != null) dateSelected = myCalendar.getDate();
                    }
                    else dateSelected = null;
                    if(!myCalendar.isVisible()) {
                        timer.cancel();
                        if(dateSelected != null) { 
                            dateSelectedL.setText(Utils.dateToString(dateSelected));
                            if(hourSelected <= currHour) {
                                hourSelected = currHour + 1;
                                addHourInp.setText(hourSelected + "");
                            }
                        }
                        myCalendar.dispose();
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
