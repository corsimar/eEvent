package proiect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PasswordPanel extends MyPanel implements ActionListener {
    private User user;
    private MyLabel emailL;
    private JPasswordField currPass, newPass, confirmPass;
    private char passChar;
    private MyButton confirmBtn;

    public PasswordPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        setVisible(false);

        emailL = new MyLabel("", textColor, new Font("Monospaced", Font.BOLD, fontTitleSize), SwingConstants.CENTER, SwingConstants.CENTER);
        add(emailL);

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
        passChar = confirmPass.getEchoChar();
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

        confirmBtn = new MyButton("Confirma", new Font("Monospaced", Font.BOLD, fontMenuSize), btnColor, Color.WHITE, null, Cursor.HAND_CURSOR);
        confirmBtn.addActionListener(this);
        add(confirmBtn);
    }

    public void setData(User user) {
        this.user = user;
        emailL.setText(this.user.getEmail());
    }

    public void setupFields() {
        currPass.setEchoChar((char)0);
        newPass.setEchoChar((char)0);
        confirmPass.setEchoChar((char)0);
        currPass.setText("Parola curenta");
        newPass.setText("Parola noua");
        confirmPass.setText("Confirma parola noua");
    }

    @Override
    public void resizeUI() {
        confirmPass.setBounds((getBounds().width - inputW) / 2, (getBounds().height - inputH) / 2, inputW, inputH);
        newPass.setBounds(confirmPass.getBounds().x, confirmPass.getBounds().y - inputH - menuOffset * 3, inputW, inputH);
        currPass.setBounds(confirmPass.getBounds().x, newPass.getBounds().y - inputH - menuOffset * 3, inputW, inputH);
        emailL.setBounds(0, currPass.getBounds().y - menuOffset * 9, getBounds().width, menuOffset * 9);
        confirmBtn.setBounds((getBounds().width - btnW) / 2, confirmPass.getBounds().y + confirmPass.getBounds().height + menuOffset * 3, btnW, btnH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirmBtn) {
            String currP = new String(currPass.getPassword()), newP = new String(newPass.getPassword()), confirmP = new String(confirmPass.getPassword());
            if(currPass.getEchoChar() != (char)0 && newPass.getEchoChar() != (char)0 && confirmPass.getEchoChar() != (char)0 &&
                !currP.equals("") && !newP.equals("") && !confirmP.equals("")) {
                if(currP.equals(user.getPassword())) {
                    if(newP.equals(confirmP)) {
                        user.setPassword(newP);
                        User.saveUser(user);
                        JOptionPane.showMessageDialog(null, "Parola contului a fost schimbata cu succes!");
                        setupFields();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Parolele introduse nu corespund!");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Parola curenta nu coincide cu parola contului!");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Completati campurile inainte de a apasa pe buton!");
            }
        }
    }
}
