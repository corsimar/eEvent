package proiect;

import javax.swing.JButton;
import javax.swing.border.Border;
import java.awt.*;

public class MyButton extends JButton {
    public MyButton(String text, Font font, Color bgColor, Color textColor, Border border, int cursor) {
        setText(text);
        if(font != null) setFont(font);
        if(bgColor != null) setBackground(bgColor);
        else setContentAreaFilled(false);
        if(textColor!= null) setForeground(textColor);
        setBorder(border);
        setFocusPainted(false);
        if(cursor != -1) setCursor(Cursor.getPredefinedCursor(cursor));
    }
}
