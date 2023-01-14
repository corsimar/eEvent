package proiect;

import javax.swing.*;
import java.awt.*;

public class LocationPanel extends MyPanel {
    private MyLabel title;
    private JComboBox<String> locations;

    public LocationPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        setVisible(false);

        title = new MyLabel("Spune-ne unde locuiesti pentru o experienta a aplicatiei mai buna", textColor, new Font("Monospaced", Font.BOLD, fontTitleSize), SwingConstants.CENTER, SwingConstants.CENTER);
        add(title);

        String[] options = Utils.locations.toArray(new String[Utils.locations.size()]);
        locations = new JComboBox<String>(options);
        locations.setBackground(Color.WHITE);
        locations.setFont(new Font("Monospaced", Font.BOLD, fontMenuSize));
        locations.setForeground(textColor);
        ((JLabel)locations.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        locations.setMaximumRowCount(10);
        add(locations);
    }

    public String getOption() {
        return locations.getSelectedItem().toString();
    }

    public void resetLocation() {
        locations.setSelectedIndex(0);
    }

    @Override
    public void resizeUI() {
        title.setBounds(0, 0, getBounds().width, 50);
        locations.setBounds((getBounds().width - inputW) / 2, (getBounds().height - inputH) / 2, inputW, inputH);
    }
}
