package proiect;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;

public class LocationPanel extends MyPanel {
    private JLabel title;
    private JComboBox<String> locations;

    public LocationPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        setVisible(false);

        title = new JLabel("Spune-ne unde locuiesti pentru o experienta a aplicatiei mai buna");
        title.setFont(new Font("Monospaced", Font.BOLD, fontTitleSize));
        title.setForeground(textColor);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
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
