package proiect;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class MyFrame extends JFrame {
    public Color barColor = Color.decode("#E5E5E5");
    public Color menuColor = Color.decode("#D9D9D9");
    public Color textColor = Color.decode("#141414");
    public Color btnColor = Color.decode("#4D88FF");

    public int fontTitleSize = 21;
    public int fontMenuSize = 17;
    public int fontInputSize = 14;

    public int frameW = 1200, frameH = 900;
    public int inputW = 300, inputH = 40, btnW = 140, btnH = 40, vLineW = 2, vLineH = 30, lineTextOffset = 16;
    public int leftMargin = 32, rightMargin = 32, menuOffset = 8;

    public BufferedImage closeBtnBuff, maximizeBtnBuff, selectedBuff, upArrowBuff, downArrowBuff;
    public Image closeBtnImg, maximizeBtnImg, selectedImg, upArrowImg, downArrowImg;

    public void loadImages() {
        try {
            closeBtnBuff = ImageIO.read(Main.class.getResource("/res/CloseBtn.png"));
            closeBtnImg = closeBtnBuff.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            maximizeBtnBuff = ImageIO.read(Main.class.getResource("/res/MaximizeBtn.png"));
            maximizeBtnImg = maximizeBtnBuff.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            selectedBuff = ImageIO.read(Main.class.getResource("/res/Selected.png"));
            selectedImg = selectedBuff.getScaledInstance(8, 8, Image.SCALE_SMOOTH);

            upArrowBuff = ImageIO.read(Main.class.getResource("/res/UpArrow.png"));
            upArrowImg = upArrowBuff.getScaledInstance(17, 11, Image.SCALE_SMOOTH);
            downArrowBuff = ImageIO.read(Main.class.getResource("/res/DownArrow.png"));
            downArrowImg = downArrowBuff.getScaledInstance(17, 11, Image.SCALE_SMOOTH);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public abstract void resizeUI();
}
