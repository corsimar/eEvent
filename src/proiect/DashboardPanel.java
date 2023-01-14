package proiect;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class DashboardPanel extends MyPanel {
    private JLabel[] boxLabel = new JLabel[3];
    private MyLabel[] boxTitle = new MyLabel[3], boxInfo = new MyLabel[3];

    public DashboardPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        setVisible(false);

        for(int i = 0; i < boxLabel.length; i++) {
            boxLabel[i] = new JLabel();
            boxLabel[i].setOpaque(true);
            boxLabel[i].setBackground(Color.WHITE);
            boxLabel[i].setBorder(BorderFactory.createLineBorder(menuColor, 1, true));

            boxTitle[i] = new MyLabel("", textColor, new Font("Monospace", Font.BOLD, fontTitleSize), SwingConstants.CENTER, SwingConstants.CENTER);
            boxInfo[i] = new MyLabel("", textColor, new Font("Monospace", Font.PLAIN, fontInputSize), SwingConstants.CENTER, SwingConstants.CENTER);
            
            add(boxTitle[i]);
            add(boxInfo[i]);
            add(boxLabel[i]);
        }
        boxInfo[0].setText("Utilizatori inregistrati");
        boxInfo[1].setText("Evenimente active");
        boxInfo[2].setText("Bilete vandute");

    }

    public void readInfo() {
        StringBuffer path = new StringBuffer(System.getProperty("user.dir"));
        path.append("\\src\\data\\statistics.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(path.toString())));
            String line; int i = 0;
            while((line = br.readLine()) != null) {
                boxTitle[i].setText(line);
                Main.info[i] = Integer.parseInt(line);
                i++;
            }
            br.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        } catch(NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    public void updateInfo() {
        for(int i = 0; i < boxTitle.length; i++) {
            boxTitle[i].setText(Main.info[i] + "");
        }
    }

    @Override
    public void resizeUI() {
        int boxW = getBounds().width / 4;
        int boxH = boxW / 2;
        boxLabel[1].setBounds((getBounds().width - boxW) / 2, (getBounds().height - boxH) / 2, boxW, boxH);
        boxLabel[0].setBounds(boxLabel[1].getBounds().x - boxW - menuOffset * 3, boxLabel[1].getBounds().y, boxW, boxH);
        boxLabel[2].setBounds(boxLabel[1].getBounds().x + boxW + menuOffset * 3, boxLabel[1].getBounds().y, boxW, boxH);
        boxTitle[0].setBounds(boxLabel[0].getBounds().x, boxLabel[0].getBounds().y, boxW, boxH / 3);
        boxTitle[1].setBounds(boxLabel[1].getBounds().x, boxLabel[1].getBounds().y, boxW, boxH / 3);
        boxTitle[2].setBounds(boxLabel[2].getBounds().x, boxLabel[2].getBounds().y, boxW, boxH / 3);
        boxInfo[0].setBounds(boxTitle[0].getBounds().x, boxLabel[0].getBounds().y, boxW, boxH);
        boxInfo[1].setBounds(boxTitle[1].getBounds().x, boxLabel[1].getBounds().y, boxW, boxH);
        boxInfo[2].setBounds(boxTitle[2].getBounds().x, boxLabel[2].getBounds().y, boxW, boxH);
    }
}
