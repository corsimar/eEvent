package proiect;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class APasswordPanel extends MyPanel implements ActionListener {

    private JPasswordField currPass;
    private JPasswordField newPass;
    private JPasswordField confirmPass;
    private char passChar;
    private JButton confirmBtn;

    public APasswordPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        setVisible(false);

        currPass = new JPasswordField();
        currPass.setFont(new Font("Monospaced", Font.PLAIN, fontInputSize));
        currPass.setForeground(textColor);
        currPass.setBackground(Color.WHITE);
        currPass.setHorizontalAlignment(SwingConstants.CENTER);
        passChar = currPass.getEchoChar();
        currPass.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(currPass.getEchoChar() == (char)0) {
                    currPass.setText("");
                    currPass.setEchoChar(passChar);
                }
            }

            @Override
            public void focusLost(FocusEvent arg0) {}
        });
        add(currPass);

        newPass = new JPasswordField();
        newPass.setFont(new Font("Monospaced", Font.PLAIN, fontInputSize));
        newPass.setForeground(textColor);
        newPass.setBackground(Color.WHITE);
        newPass.setHorizontalAlignment(SwingConstants.CENTER);
        newPass.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(newPass.getEchoChar() == (char)0) {
                    newPass.setText("");
                    newPass.setEchoChar(passChar);
                }
            }

            @Override
            public void focusLost(FocusEvent arg0) {}
        });
        add(newPass);

        confirmPass = new JPasswordField();
        confirmPass.setFont(new Font("Monospaced", Font.PLAIN, fontInputSize));
        confirmPass.setForeground(textColor);
        confirmPass.setBackground(Color.WHITE);
        confirmPass.setHorizontalAlignment(SwingConstants.CENTER);
        confirmPass.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(confirmPass.getEchoChar() == (char)0) {
                    confirmPass.setText("");
                    confirmPass.setEchoChar(passChar);
                }
            }

            @Override
            public void focusLost(FocusEvent arg0) {}
        });
        add(confirmPass);

        confirmBtn = new JButton("Confirma");
        confirmBtn.setFont(new Font("Monospace", Font.BOLD, fontMenuSize));
        confirmBtn.setForeground(Color.WHITE);
        confirmBtn.setBackground(btnColor);
        confirmBtn.setBorder(null);
        confirmBtn.setFocusPainted(false);
        confirmBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmBtn.addActionListener(this);
        add(confirmBtn);
    }

    public void setupFields() {
        currPass.setEchoChar((char)0);
        newPass.setEchoChar((char)0);
        confirmPass.setEchoChar((char)0);
        currPass.setText("Parola curenta");
        newPass.setText("Parola noua");
        confirmPass.setText("Confirma parola noua");
    }

    private boolean checkFields() {
        String currP = new String(currPass.getPassword()), newP = new String(newPass.getPassword()), confirmP = new String(confirmPass.getPassword());
        if(currPass.getEchoChar() != (char)0 && newPass.getEchoChar() != (char)0 && confirmPass.getEchoChar() != (char)0 &&
            !currP.equals("") && !newP.equals("") && !confirmP.equals("")) {
            User user = new User(new String(currPass.getPassword()));
            if(user.loginAsAdmin()) {
                if(newP.equals(confirmP)) {
                    if(user.changeAdminPassword(newP)) {
                        return true;
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Schimbarea parolei nu a reusit!");
                        return false;
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Parolele introduse nu corespund!");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Parola curenta nu este corecta!");
                return false;
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Completati toate campurile inainte de a apasa pe buton!");
            return false;
        }
        return false;
    }

    @Override
    public void resizeUI() {
        confirmPass.setBounds((getBounds().width - inputW) / 2, (getBounds().height - inputH) / 2, inputW, inputH);
        newPass.setBounds((getBounds().width - inputW) / 2, confirmPass.getBounds().y - inputH - menuOffset * 3, inputW, inputH);
        currPass.setBounds((getBounds().width - inputW) / 2, newPass.getBounds().y - inputH - menuOffset * 3, inputW, inputH);
        confirmBtn.setBounds(confirmPass.getBounds().x + (inputW - btnW) / 2, confirmPass.getBounds().y + inputH + menuOffset * 3, btnW, btnH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirmBtn) {
            if(checkFields()) {
                JOptionPane.showMessageDialog(null, "Parola contului de admin a fost schimbata cu succes!");
                setupFields();
            }
        }
    }
}
