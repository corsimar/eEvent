package proiect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PreferencePanel extends MyPanel {
    private MyLabel title;
    private ArrayList<MyButton> preferences = new ArrayList<MyButton>();
    private ArrayList<MyButton> selected = new ArrayList<MyButton>();
    private ArrayList<Integer> options = new ArrayList<Integer>();
    private int textW;
    private ButtonListener buttonListener;

    public PreferencePanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        setVisible(false);

        buttonListener = new ButtonListener();

        title = new MyLabel("Alege categorille tale preferate", textColor, new Font("Monospaced", Font.BOLD, fontTitleSize), SwingConstants.CENTER, SwingConstants.BOTTOM);
        add(title);

        for(int i = 0; i < Utils.categories.size(); i++) {
            MyButton btn = new MyButton(Utils.categories.get(i), new Font("Monospaced", Font.BOLD, fontMenuSize), null, textColor, null, Cursor.HAND_CURSOR);
            btn.setName(i + "");
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.addActionListener(buttonListener);
            add(btn);
            preferences.add(btn);
            if(btn.getText().equals("Dezvoltare personala")) {
                FontMetrics fm = btn.getFontMetrics(btn.getFont());
                textW = fm.stringWidth(btn.getText());
            }
        }
    }

    public void updateButtons() {
        for(int i = 0; i < preferences.size(); i++) {
            preferences.get(i).setName(i + "");
        }
        for(int i = 0; i < selected.size(); i++) {
            selected.get(i).setName(i + "");
        }
    }

    public int getButtonIndex(String category, boolean s) {
        if(!s) {
            for(int i = 0; i < preferences.size(); i++) {
                if(preferences.get(i).getText().equals(category)) return i;
            }
        }
        else {
            for(int i = 0; i < selected.size(); i++) {
                if(selected.get(i).getText().equals(category)) return i;
            }
        }
        return -1;
    }

    public ArrayList<Integer> getOptions() {
        return options;
    }

    public void addPreference(int btnIndex) {
        selected.add(preferences.get(btnIndex));
        preferences.remove(btnIndex);
        selected.get(selected.size() - 1).setName((selected.size() - 1) + "");
        selected.get(selected.size() - 1).setForeground(btnColor);
        int optionID = Utils.categories.indexOf(selected.get(selected.size() - 1).getText());
        if(!options.contains(optionID)) options.add(optionID);
    }

    public void removePreference(int btnIndex) {
        preferences.add(selected.get(btnIndex));
        selected.remove(btnIndex);
        preferences.get(preferences.size() - 1).setName((preferences.size() - 1) + "");
        preferences.get(preferences.size() - 1).setForeground(textColor);
        options.remove((Object)Utils.categories.indexOf(preferences.get(preferences.size() - 1).getText()));
    }

    public void setPreferences(ArrayList<Integer> pref) {
        resetPreferences();
        options.addAll(pref);
        for(int i = 0; i < options.size(); i++) {
            int btnIndex = getButtonIndex(Utils.categories.get(options.get(i)), false);
            addPreference(btnIndex);
        }
        updateButtons();
    }

    public void resetPreferences() {
        options.clear();
        preferences.addAll(selected);
        for(int i = 0; i < selected.size(); i++) {
            selected.get(i).setForeground(textColor);
        }
        selected.clear();
        updateButtons();
        resizeUI();
    }

    @Override
    public void resizeUI() {
        title.setBounds(0, 0, getBounds().width, 50);

        FontMetrics fm;
        int y = title.getBounds().height + menuOffset * 6;
        for(int i = 0; i < preferences.size(); i++) {
            fm = preferences.get(i).getFontMetrics(preferences.get(i).getFont());
            preferences.get(i).setBounds(getBounds().width / 2 - textW - menuOffset * 3, y, fm.stringWidth(preferences.get(i).getText()), fm.getHeight());
            y += fm.getHeight(); y += menuOffset * 2;
        }
        y = title.getBounds().height + menuOffset * 6;
        for(int i = 0; i < selected.size(); i++) {
            fm = selected.get(i).getFontMetrics(selected.get(i).getFont());
            selected.get(i).setBounds(getBounds().width / 2 + menuOffset * 3, y, fm.stringWidth(selected.get(i).getText()), fm.getHeight());
            y += fm.getHeight(); y += menuOffset * 2;
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int btnIndex = Integer.parseInt(((JButton)e.getSource()).getName());
                if(preferences.contains(e.getSource())) {
                    addPreference(btnIndex);
                }
                else {
                    removePreference(btnIndex);
                }
                updateButtons();
                resizeUI();
            } catch(NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
    }
}
