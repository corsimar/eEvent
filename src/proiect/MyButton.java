package proiect;

import javax.swing.JButton;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

public class MyButton extends JButton {
    public MyButton(String text, Font font, Color bgColor, Color textColor, Border border, int cursor) {
        setText(text);
        setFont(font);
        if(bgColor != null) setBackground(bgColor);
        else setContentAreaFilled(false);
        setForeground(textColor);
        setBorder(border);
        setFocusPainted(false);
        if(cursor != -1) setCursor(Cursor.getPredefinedCursor(cursor));
    }
}
