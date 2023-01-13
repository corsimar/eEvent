package proiect;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class MyPanel extends JPanel {

    public Color barColor = Color.decode("#E5E5E5");
    public Color menuColor = Color.decode("#D9D9D9");
    public Color textColor = Color.decode("#141414");
    public Color btnColor = Color.decode("#4D88FF");
    public Color btnLightColor = Color.decode("#66BAFF");

    public int fontTitleSize = 21;
    public int fontMenuSize = 17;
    public int fontInputSize = 14;

    public int leftMargin = 32;
    public int menuOffset = 8;

    public int inputW = 300, inputH = 40, btnW = 140, btnH = 40, vLineW = 2, vLineH = 30, lineTextOffset = 16;

    public BufferedImage upArrowBuff, downArrowBuff;
    public Image upArrowImg, downArrowImg;

    public abstract void resizeUI();

    public void loadImages() {
        try {
            upArrowBuff = ImageIO.read(Main.class.getResource("/res/UpArrow.png"));
            upArrowImg = upArrowBuff.getScaledInstance(17, 11, Image.SCALE_SMOOTH);
            downArrowBuff = ImageIO.read(Main.class.getResource("/res/DownArrow.png"));
            downArrowImg = downArrowBuff.getScaledInstance(17, 11, Image.SCALE_SMOOTH);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getDateMonth(String date) {
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
}