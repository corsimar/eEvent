package proiect;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class MyLabel extends JLabel {
    public MyLabel(String text, Color color, Font font, int hAlignment, int vAlignment) {
        setText(text);
        setFont(font);
        setForeground(color);
        setHorizontalAlignment(hAlignment);
        if(vAlignment != -1) setVerticalAlignment(vAlignment);
    }
}
