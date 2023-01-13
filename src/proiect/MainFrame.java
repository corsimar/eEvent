package proiect;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MainFrame extends MyFrame implements ActionListener {
    private int mouseX = -1, mouseY = -1, windowW, windowH;

    private JPanel topBar;
    private MyLabel title;
    private JButton closeBtn, maximizeBtn, minimizeBtn;
    private boolean maximized = false;
    private Timer dragTimer;

    private User user = null;
    private JPanel loginPanel;
    private boolean login = true;
    private MyLabel loginText;
    private JTextField emailInp;
    private JPasswordField passInp;
    private MyButton confirmBtn, registerBtn, adminBtn, loginBtn, backBtn;
    private JLabel verticalLine;
    private PreferencePanel preferencePanel;
    private LocationPanel locationPanel;
    private MyButton nextBtn;

    private JPanel menuBar;
    private MyButton selectedMenu;
    private JLabel selectedCircle;
    private MyButton eventsBtn, myAccountBtn, changePassBtn, prefBtn, disconnectBtn;
    private EventsPanel eventsPanel;
    private TicketPanel ticketPanel;
    private PasswordPanel passwordPanel;

    private JPanel adminBar;
    private MyButton panelBtn, admEventsBtn, addEventBtn, manageEventsBtn, menuAdmBtn, admChangePassBtn, admDisconnectBtn;
    private JComponent lastPanel;
    private DashboardPanel dashboardPanel;
    private AddEventPanel addEventPanel;
    private AEventsPanel aEventsPanel;
    public APasswordPanel aPasswordPanel;

    public MainFrame() {
        setMinimumSize(new Dimension(frameW, frameH));
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        addWindowStateListener(new WindowAdapter() {
            public void windowStateChanged(WindowEvent we) {
				if(we.getNewState() == JFrame.NORMAL) {
					if(maximized) {
						setExtendedState(JFrame.MAXIMIZED_BOTH);
                    }
                }
			}
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
        windowW = getBounds().width; windowH = getBounds().height;

        loadImages();

        initTopBar();
        initLoginPanel();
        initMenuBar();
        initAdminMenuBar();
        initAdminPanel();

        resizeUI();
    }

    private void initTopBar() {
        dragTimer = new Timer();
        TimerTask dragTask = new TimerTask() {
            public void run() {
                if(mouseX != -1) {
                    int dx = mouseX - MouseInfo.getPointerInfo().getLocation().x;
                    int dy = mouseY - MouseInfo.getPointerInfo().getLocation().y;
                    mouseX -= dx; mouseY -= dy;

                    setLocation(getLocation().x - dx, getLocation().y - dy);             
                }
            }
        };
        dragTimer.scheduleAtFixedRate(dragTask, 0, 15);

        topBar = new JPanel();
        topBar.setLayout(null);
        topBar.setBackground(barColor);
        topBar.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if(!maximized) {
                    mouseX = e.getXOnScreen();
                    mouseY = e.getYOnScreen();
                }
            }
            public void mouseReleased(MouseEvent e) {
                if(!maximized) {
                    mouseX = -1; mouseY = -1;
                }
            }
        });
        add(topBar);

        title = new MyLabel("eEvent", textColor, new Font("Monospaced", Font.BOLD, fontTitleSize), SwingConstants.LEFT, SwingConstants.CENTER);
        topBar.add(title);

        closeBtn = new JButton();
        closeBtn.setIcon(new ImageIcon(closeBtnImg));
        closeBtn.setContentAreaFilled(false);
        closeBtn.setBorder(null);
        closeBtn.addActionListener(this);
        topBar.add(closeBtn);

        maximizeBtn = new JButton();
        maximizeBtn.setIcon(new ImageIcon(maximizeBtnImg));
        maximizeBtn.setContentAreaFilled(false);
        maximizeBtn.setBorder(null);
        maximizeBtn.addActionListener(this);
        topBar.add(maximizeBtn);

        minimizeBtn = new JButton();
        minimizeBtn.setBackground(textColor);
        minimizeBtn.setOpaque(true);
        minimizeBtn.setBorder(null);
        minimizeBtn.addActionListener(this);
        topBar.add(minimizeBtn);
    }

    boolean emailFocused, passFocused;
    void initLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(Color.WHITE);
        add(loginPanel);

        loginText = new MyLabel("Autentificare", textColor, new Font("Monospaced", Font.BOLD, fontTitleSize), SwingConstants.CENTER, -1);
        loginPanel.add(loginText);

        emailFocused = false; passFocused = false;

        emailInp = new JTextField("Adresa de email");
        emailInp.setFont(new Font("Monospaced", Font.PLAIN, fontInputSize));
        emailInp.setForeground(textColor);
        emailInp.setHorizontalAlignment(SwingConstants.CENTER);
        emailInp.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(!emailFocused) {
                    emailInp.setText("");
                    emailFocused = true;
                }
            }
            @Override
            public void focusLost(FocusEvent e) {}
        });
        loginPanel.add(emailInp);

        passInp = new JPasswordField("Parola");
        passInp.setFont(new Font("Monospaced", Font.PLAIN, fontInputSize));
        passInp.setForeground(textColor);
        passInp.setHorizontalAlignment(SwingConstants.CENTER);
        char c = passInp.getEchoChar();
        passInp.setEchoChar((char)0);
        passInp.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(!passFocused) {
                    passInp.setText("");
                    passInp.setEchoChar(c);
                    passFocused = true;
                }
            }
            @Override
            public void focusLost(FocusEvent e) {}
        });
        loginPanel.add(passInp);

        confirmBtn = new MyButton("Confirma", new Font("Monospaced", Font.BOLD, fontMenuSize), btnColor, Color.WHITE, null, Cursor.HAND_CURSOR);
        confirmBtn.addActionListener(this);
        loginPanel.add(confirmBtn);

        verticalLine = new JLabel();
        verticalLine.setBackground(menuColor);
        verticalLine.setOpaque(true);
        loginPanel.add(verticalLine);

        registerBtn = new MyButton("Creeaza cont nou", new Font("Monospaced", Font.BOLD, fontMenuSize), null, textColor, null, Cursor.HAND_CURSOR);
        registerBtn.setHorizontalAlignment(SwingConstants.RIGHT);
        registerBtn.setVerticalAlignment(SwingConstants.CENTER);
        registerBtn.addActionListener(this);
        loginPanel.add(registerBtn);

        loginBtn = new MyButton("Autentifica-te", new Font("Monospaced", Font.BOLD, fontMenuSize), null, textColor, null, Cursor.HAND_CURSOR);
        loginBtn.setHorizontalAlignment(SwingConstants.RIGHT);
        loginBtn.setVerticalAlignment(SwingConstants.CENTER);
        loginBtn.addActionListener(this);
        loginBtn.setVisible(false);
        loginPanel.add(loginBtn);

        adminBtn = new MyButton("Admin", new Font("Monospaced", Font.BOLD, fontMenuSize), null, textColor, null, Cursor.HAND_CURSOR);
        adminBtn.setHorizontalAlignment(SwingConstants.LEFT);
        adminBtn.setVerticalAlignment(SwingConstants.CENTER);
        adminBtn.addActionListener(this);
        loginPanel.add(adminBtn);

        backBtn = new MyButton("Inapoi", new Font("Monospaced", Font.BOLD, fontMenuSize), btnColor, Color.WHITE, null, Cursor.HAND_CURSOR);
        backBtn.addActionListener(this);
        backBtn.setVisible(false);
        loginPanel.add(backBtn);

        preferencePanel = new PreferencePanel();
        add(preferencePanel);

        locationPanel = new LocationPanel();
        add(locationPanel);

        nextBtn = new MyButton("Urmator", new Font("Monospaced", Font.BOLD, fontMenuSize), btnColor, Color.WHITE, null, Cursor.HAND_CURSOR);
        nextBtn.addActionListener(this);
        nextBtn.setVisible(false);
        add(nextBtn);
    }

    void resetInputFields() {
        emailFocused = false; passFocused = false;
        emailInp.setText("Adresa de email");
        passInp.setText("Parola");
        passInp.setEchoChar((char)0);
    }

    void initMenuBar() {
        menuBar = new JPanel();
        menuBar.setBackground(menuColor);
        menuBar.setLayout(null);
        menuBar.setVisible(false);
        add(menuBar);

        selectedCircle = new JLabel("");
        selectedCircle.setIcon(new ImageIcon(selectedImg));

        eventsBtn = new MyButton("Exploreaza", new Font("Monospaced", Font.BOLD, fontTitleSize), null, textColor, null, Cursor.HAND_CURSOR);
        eventsBtn.setHorizontalAlignment(SwingConstants.LEFT);
        eventsBtn.addActionListener(this);
        menuBar.add(eventsBtn);

        myAccountBtn = new MyButton("Contul meu", new Font("Monospaced", Font.BOLD, fontTitleSize), null, textColor, null, -1);
        myAccountBtn.setHorizontalAlignment(SwingConstants.LEFT);
        myAccountBtn.setFocusPainted(false);
        menuBar.add(myAccountBtn);

        changePassBtn = new MyButton("Schimba parola", new Font("Monospaced", Font.PLAIN, fontMenuSize), null, textColor, null, Cursor.HAND_CURSOR);
        changePassBtn.setHorizontalAlignment(SwingConstants.LEFT);
        changePassBtn.addActionListener(this);
        menuBar.add(changePassBtn);

        prefBtn = new MyButton("Preferinte", new Font("Monospaced", Font.PLAIN, fontMenuSize), null, textColor, null, Cursor.HAND_CURSOR);
        prefBtn.setHorizontalAlignment(SwingConstants.LEFT);
        prefBtn.addActionListener(this);
        menuBar.add(prefBtn);

        disconnectBtn = new MyButton("Deconecteaza-te", new Font("Monospaced", Font.PLAIN, fontMenuSize), null, textColor, null, Cursor.HAND_CURSOR);
        disconnectBtn.setHorizontalAlignment(SwingConstants.LEFT);
        disconnectBtn.addActionListener(this);
        menuBar.add(disconnectBtn);

        eventsPanel = new EventsPanel();
        add(eventsPanel);

        ticketPanel = new TicketPanel();
        add(ticketPanel);

        passwordPanel = new PasswordPanel();
        add(passwordPanel);
    }

    void initAdminMenuBar() {
        adminBar = new JPanel();
        adminBar.setBackground(menuColor);
        adminBar.setLayout(null);
        adminBar.setVisible(false);
        add(adminBar);

        panelBtn = new MyButton("Panou", new Font("Monospaced", Font.BOLD, fontTitleSize), null, textColor, null, Cursor.HAND_CURSOR);
        panelBtn.setHorizontalAlignment(SwingConstants.LEFT);
        panelBtn.addActionListener(this);
        adminBar.add(panelBtn);

        admEventsBtn = new MyButton("Evenimente", new Font("Monospaced", Font.BOLD, fontTitleSize), null, textColor, null, -1);
        admEventsBtn.setHorizontalAlignment(SwingConstants.LEFT);
        adminBar.add(admEventsBtn);

        addEventBtn = new MyButton("Adauga", new Font("Monospaced", Font.PLAIN, fontMenuSize), null, textColor, null, Cursor.HAND_CURSOR);
        addEventBtn.setHorizontalAlignment(SwingConstants.LEFT);
        addEventBtn.addActionListener(this);
        adminBar.add(addEventBtn);

        manageEventsBtn = new MyButton("Administreaza", new Font("Monospaced", Font.PLAIN, fontMenuSize), null, textColor, null, Cursor.HAND_CURSOR);
        manageEventsBtn.setHorizontalAlignment(SwingConstants.LEFT);
        manageEventsBtn.addActionListener(this);
        adminBar.add(manageEventsBtn);

        menuAdmBtn = new MyButton("Admin", new Font("Monospaced", Font.BOLD, fontTitleSize), null, textColor, null, -1);
        menuAdmBtn.setHorizontalAlignment(SwingConstants.LEFT);
        adminBar.add(menuAdmBtn);

        admChangePassBtn = new MyButton("Schimba parola", new Font("Monospaced", Font.PLAIN, fontMenuSize), null, textColor, null, Cursor.HAND_CURSOR);
        admChangePassBtn.setHorizontalAlignment(SwingConstants.LEFT);
        admChangePassBtn.addActionListener(this);
        adminBar.add(admChangePassBtn);

        admDisconnectBtn = new MyButton("Deconectare", new Font("Monospaced", Font.PLAIN, fontMenuSize), null, textColor, null, Cursor.HAND_CURSOR);
        admDisconnectBtn.setHorizontalAlignment(SwingConstants.LEFT);
        admDisconnectBtn.addActionListener(this);
        adminBar.add(admDisconnectBtn);
    }

    void initAdminPanel() {
        dashboardPanel = new DashboardPanel();
        dashboardPanel.readInfo();
        add(dashboardPanel);

        addEventPanel = new AddEventPanel();
        add(addEventPanel);

        aEventsPanel = new AEventsPanel();
        add(aEventsPanel);

        aPasswordPanel = new APasswordPanel();
        add(aPasswordPanel);
    }

    @Override
    public void resizeUI() {
        windowW = getBounds().width; windowH = getBounds().height;
        FontMetrics fm;

        if(topBar.isVisible()) {
            topBar.setBounds(0, 0, windowW, 50);
            title.setBounds(leftMargin, 0, 100, topBar.getBounds().height);
            closeBtn.setBounds(topBar.getBounds().width - closeBtnImg.getWidth(closeBtn) - rightMargin, topBar.getBounds().height / 2 - closeBtnImg.getHeight(closeBtn) / 2, closeBtnImg.getWidth(closeBtn), closeBtnImg.getHeight(closeBtn));
            maximizeBtn.setBounds(closeBtn.getBounds().x - closeBtn.getBounds().width - rightMargin, closeBtn.getBounds().y, maximizeBtnImg.getWidth(maximizeBtn), maximizeBtnImg.getHeight(maximizeBtn));
            minimizeBtn.setBounds(maximizeBtn.getBounds().x - maximizeBtn.getBounds().width - rightMargin, maximizeBtn.getBounds().y + maximizeBtn.getBounds().height - 4, maximizeBtn.getBounds().width, 4);
        }
        if(loginPanel.isVisible()) {
            loginPanel.setBounds(0, topBar.getBounds().height, windowW, windowH - topBar.getBounds().height);
            fm = loginText.getFontMetrics(loginText.getFont());
            loginText.setBounds(0, loginPanel.getBounds().height / 2 - topBar.getBounds().height * 3, windowW, 50);
            emailInp.setBounds((windowW - inputW) / 2, loginText.getBounds().y + loginText.getBounds().height + menuOffset * 3, inputW, inputH);
            passInp.setBounds(emailInp.getBounds().x, emailInp.getBounds().y + emailInp.getBounds().height + menuOffset * 3, inputW, inputH);
            confirmBtn.setBounds((windowW - btnW) / 2, passInp.getBounds().y + passInp.getBounds().height + menuOffset * 3, btnW, btnH);
            verticalLine.setBounds((windowW - vLineW) / 2, loginPanel.getBounds().height - vLineH - menuOffset * 3, vLineW, vLineH);
            fm = registerBtn.getFontMetrics(registerBtn.getFont());
            registerBtn.setBounds(windowW / 2 - fm.stringWidth(registerBtn.getText()) - lineTextOffset, verticalLine.getBounds().y + (verticalLine.getBounds().height - fm.getHeight()) / 2, fm.stringWidth(registerBtn.getText()), fm.getHeight());
            fm = loginBtn.getFontMetrics(loginBtn.getFont());
            loginBtn.setBounds(windowW / 2 - fm.stringWidth(registerBtn.getText()) - lineTextOffset, verticalLine.getBounds().y + (verticalLine.getBounds().height - fm.getHeight()) / 2, fm.stringWidth(registerBtn.getText()), fm.getHeight());
            fm = adminBtn.getFontMetrics(adminBtn.getFont());
            adminBtn.setBounds(windowW / 2 + lineTextOffset, registerBtn.getBounds().y, fm.stringWidth(adminBtn.getText()), fm.getHeight());
            backBtn.setBounds((windowW - btnW) / 2, verticalLine.getBounds().y - menuOffset * 3, btnW, btnH);
        }
        if(preferencePanel.isVisible()) {
            if(!menuBar.isVisible()) {
                preferencePanel.setBounds(0, topBar.getBounds().height, windowW, windowH * 3 / 4);
                preferencePanel.resizeUI();
                nextBtn.setBounds((windowW - btnW) / 2, windowH - btnH - menuOffset * 6, btnW, btnH);
            }
            else {
                preferencePanel.setBounds(menuBar.getBounds().width, topBar.getBounds().height, windowW - menuBar.getBounds().width, windowH - topBar.getBounds().height);
                preferencePanel.setPreferences(user.getPreferences());
                preferencePanel.resizeUI();
            }
        }
        if(locationPanel.isVisible()) {
            locationPanel.setBounds(0, topBar.getBounds().height, windowW, windowH * 3 / 4);
            locationPanel.resizeUI();
        }
        if(menuBar.isVisible()) {
            menuBar.setBounds(0, topBar.getBounds().height, windowW / 5, windowH - topBar.getBounds().height);

            fm = eventsBtn.getFontMetrics(eventsBtn.getFont());
            eventsBtn.setBounds(leftMargin, menuOffset * 3, fm.stringWidth(eventsBtn.getText()), fm.getHeight());

            fm = myAccountBtn.getFontMetrics(myAccountBtn.getFont());
            myAccountBtn.setBounds(leftMargin, eventsBtn.getBounds().y + eventsBtn.getBounds().height + menuOffset * 3, fm.stringWidth(myAccountBtn.getText()), fm.getHeight());
            fm = changePassBtn.getFontMetrics(changePassBtn.getFont());
            changePassBtn.setBounds(leftMargin, myAccountBtn.getBounds().y + myAccountBtn.getBounds().height + menuOffset, fm.stringWidth(changePassBtn.getText()), fm.getHeight());
            fm = prefBtn.getFontMetrics(prefBtn.getFont());
            prefBtn.setBounds(leftMargin, changePassBtn.getBounds().y + changePassBtn.getBounds().height + menuOffset, fm.stringWidth(prefBtn.getText()), fm.getHeight());
            fm = disconnectBtn.getFontMetrics(disconnectBtn.getFont());
            disconnectBtn.setBounds(leftMargin, prefBtn.getBounds().y + prefBtn.getBounds().height + menuOffset, fm.stringWidth(disconnectBtn.getText()), fm.getHeight());

            if(selectedMenu != null) {
                selectedCircle.setBounds(selectedMenu.getBounds().x, selectedMenu.getBounds().y, selectedImg.getWidth(selectedCircle), selectedImg.getHeight(selectedCircle));
                selectedCircle.setBounds(selectedCircle.getBounds().x - leftMargin / 2 - selectedImg.getWidth(selectedCircle) / 2, selectedCircle.getBounds().y + (eventsBtn.getBounds().height - selectedCircle.getBounds().height) / 2, selectedImg.getWidth(selectedCircle), selectedImg.getHeight(selectedCircle));
            }
        }
        if(eventsPanel.isVisible()) {
            eventsPanel.setBounds(menuBar.getBounds().width, topBar.getBounds().height, windowW - menuBar.getBounds().width, windowH - topBar.getBounds().height);
            eventsPanel.setPanelH(windowH, topBar.getBounds().height);
            eventsPanel.resizeUI();
            eventsPanel.resizeScroll(topBar.getBounds().height);
        }
        if(ticketPanel.isVisible()) {
            ticketPanel.setBounds(0, topBar.getBounds().height, windowW, windowH - topBar.getBounds().height);
            ticketPanel.resizeUI();
        }
        if(passwordPanel.isVisible()) {
            passwordPanel.setBounds(menuBar.getBounds().width, topBar.getBounds().height, windowW - menuBar.getBounds().width, windowH - topBar.getBounds().height);
            passwordPanel.resizeUI();
        }
        if(adminBar.isVisible()) {
            adminBar.setBounds(0, topBar.getBounds().height, windowW / 5, windowH - topBar.getBounds().height);

            fm = panelBtn.getFontMetrics(panelBtn.getFont());
            panelBtn.setBounds(leftMargin, menuOffset * 3, fm.stringWidth(panelBtn.getText()), fm.getHeight());

            fm = admEventsBtn.getFontMetrics(admEventsBtn.getFont());
            admEventsBtn.setBounds(leftMargin, panelBtn.getBounds().y + panelBtn.getBounds().height + menuOffset * 3, fm.stringWidth(admEventsBtn.getText()), fm.getHeight());
            fm = addEventBtn.getFontMetrics(addEventBtn.getFont());
            addEventBtn.setBounds(leftMargin, admEventsBtn.getBounds().y + admEventsBtn.getBounds().height + menuOffset, fm.stringWidth(addEventBtn.getText()), fm.getHeight());
            fm = manageEventsBtn.getFontMetrics(manageEventsBtn.getFont());
            manageEventsBtn.setBounds(leftMargin, addEventBtn.getBounds().y + addEventBtn.getBounds().height + menuOffset, fm.stringWidth(manageEventsBtn.getText()), fm.getHeight());
            
            fm = menuAdmBtn.getFontMetrics(menuAdmBtn.getFont());
            menuAdmBtn.setBounds(leftMargin, manageEventsBtn.getBounds().y + manageEventsBtn.getBounds().height + menuOffset * 3, fm.stringWidth(menuAdmBtn.getText()), fm.getHeight());
            fm = admChangePassBtn.getFontMetrics(admChangePassBtn.getFont());
            admChangePassBtn.setBounds(leftMargin, menuAdmBtn.getBounds().y + menuAdmBtn.getBounds().height + menuOffset, fm.stringWidth(admChangePassBtn.getText()), fm.getHeight());
            fm = admDisconnectBtn.getFontMetrics(admDisconnectBtn.getFont());
            admDisconnectBtn.setBounds(leftMargin, admChangePassBtn.getBounds().y + admChangePassBtn.getBounds().height + menuOffset, fm.stringWidth(admDisconnectBtn.getText()), fm.getHeight());

            if(selectedMenu != null) {
                selectedCircle.setBounds(selectedMenu.getBounds().x, selectedMenu.getBounds().y, selectedImg.getWidth(selectedCircle), selectedImg.getHeight(selectedCircle));
                selectedCircle.setBounds(selectedCircle.getBounds().x - leftMargin / 2 - selectedImg.getWidth(selectedCircle) / 2, selectedCircle.getBounds().y + (selectedMenu.getBounds().height - selectedCircle.getBounds().height) / 2, selectedImg.getWidth(selectedCircle), selectedImg.getHeight(selectedCircle));
        	}
        }
        if(dashboardPanel.isVisible()) {
            dashboardPanel.setBounds(adminBar.getBounds().width, topBar.getBounds().height, windowW - adminBar.getBounds().width, windowH - topBar.getBounds().height);
            dashboardPanel.resizeUI();
        }
        if(addEventPanel.isVisible()) {
            addEventPanel.setBounds(adminBar.getBounds().width, topBar.getBounds().height, windowW - adminBar.getBounds().width, windowH - topBar.getBounds().height);
            addEventPanel.resizeUI();
        }
        if(aEventsPanel.isVisible()) {
            aEventsPanel.setBounds(adminBar.getBounds().width, topBar.getBounds().height, windowW - adminBar.getBounds().width, windowH - topBar.getBounds().height);
            aEventsPanel.setPanelH(windowH, topBar.getBounds().height);
            aEventsPanel.resizeUI();
            aEventsPanel.resizeScroll(topBar.getBounds().height);
        }
        if(aPasswordPanel.isVisible()) {
            aPasswordPanel.setBounds(adminBar.getBounds().width, topBar.getBounds().height, windowW - adminBar.getBounds().width, windowH - topBar.getBounds().height);
            aPasswordPanel.resizeUI();
        }
    }

    private void showPreferences() {
        preferencePanel.setVisible(true);
        preferencePanel.resetPreferences();
        nextBtn.setVisible(true);
        loginPanel.setVisible(false);
        resizeUI();
        preferencePanel.resizeUI();
    }

    private void showLocation() {
        locationPanel.setVisible(true);
        nextBtn.setText("Finalizeaza");
        preferencePanel.resetPreferences();
        preferencePanel.setVisible(false);
        resizeUI();
    }

    private void loginUser() {
        menuBar.setVisible(true);
        loginPanel.setVisible(false);
        locationPanel.setVisible(false);
        nextBtn.setVisible(false);
        menuBar.add(selectedCircle);
        resizeUI();
    }

    private void resetMenuBar() {
        if(selectedMenu != null) {
            selectedMenu.setForeground(textColor);
            if(selectedMenu.getFont().getSize() == fontMenuSize) 
                selectedMenu.setFont(new Font("Monospaced", Font.PLAIN, selectedMenu.getFont().getSize()));
            else if(selectedMenu.getFont().getSize() == fontTitleSize)
                selectedMenu.setFont(new Font("Monospaced", Font.BOLD, selectedMenu.getFont().getSize()));
        }
    }

    private void disconnectUser() {
        loginPanel.setVisible(true);
        menuBar.setVisible(false);
        preferencePanel.resetPreferences();
        if(lastPanel != null) lastPanel.setVisible(false);
        lastPanel = null;
        resetMenuBar();
        user = null;
        selectedMenu = null;
        selectedCircle.setBounds(0, 0, 0, 0);
        resizeUI();
    }

    public void showTicket(int ticketID) {
        ticketPanel.setVisible(true);
        menuBar.setVisible(false);
        eventsPanel.setVisible(false);
        ticketPanel.setTicket(user, eventsPanel.getTicket(ticketID), user.ownsTicket(eventsPanel.getTicket(ticketID)));
        resizeUI();
    }

    public void buyTicket(Event event) {
        if(!user.getTickets().contains(event)) {
            ArrayList<Event> arr = new ArrayList<Event>();
            arr.addAll(user.getTickets());
            arr.add(event);
            user.addTickets(arr);
            User.saveUser(user);
            eventsPanel.buyTicket(event);
        }
    }

    public void closeTicket() {
        menuBar.setVisible(true);
        eventsPanel.setVisible(true);
        ticketPanel.setVisible(false);
        resizeUI();
    }

    private void handleMenuBar(ActionEvent e) {
        if(lastPanel == preferencePanel) {
            user.addPreferences(preferencePanel.getOptions());
            User.saveUser(user);
        }
        if(lastPanel != null && e.getSource() != disconnectBtn) lastPanel.setVisible(false);
        if(e.getSource() == eventsBtn) {
            eventsPanel.setVisible(true);
            lastPanel = eventsPanel;
            
            eventsPanel.setUser(user);
            eventsPanel.setTickets(user.getTickets());
            if(!eventsPanel.cardsCreated()) eventsPanel.createCardEvents();
            else eventsPanel.resetAll();
            resizeUI();
            eventsPanel.setPanelH(frameH, topBar.getBounds().height);
            eventsPanel.resizeUI();
            eventsPanel.resetScroll(topBar.getBounds().height);
        }
        else if(e.getSource() == changePassBtn) {
            passwordPanel.setVisible(true);
            passwordPanel.setData(user);
            passwordPanel.setupFields();
            lastPanel = passwordPanel;
            resizeUI();
        }
        else if(e.getSource() == prefBtn) {
            preferencePanel.setVisible(true);
            lastPanel = preferencePanel;

            resizeUI();
        }
        else if(e.getSource() == disconnectBtn) {
            Object[] options = { "Confirm", "Anulare" };
            int r = JOptionPane.showOptionDialog(null, "Sunteti sigur ca doriti sa va deconectati de pe cont?", "Deconectare", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
            if(r == 0) disconnectUser();
        }
    }

    private void handleAdminMenu(ActionEvent e) {
        if(lastPanel != null && e.getSource() != admDisconnectBtn) lastPanel.setVisible(false);
        if(e.getSource() == panelBtn) {
            dashboardPanel.setVisible(true);
            dashboardPanel.updateInfo();
            lastPanel = dashboardPanel;

            resizeUI();
            dashboardPanel.resizeUI();
        }
        else if(e.getSource() == addEventBtn) {
            addEventPanel.clearEventForm();

            addEventPanel.setVisible(true);
            lastPanel = addEventPanel;

            resizeUI();
            addEventPanel.resizeUI();
        }
        else if(e.getSource() == manageEventsBtn) {
            aEventsPanel.setVisible(true);
            lastPanel = aEventsPanel;

            if(!aEventsPanel.cardsCreated()) aEventsPanel.createCardEvents();
            resizeUI();
            aEventsPanel.setPanelH(windowH, topBar.getBounds().height);
            aEventsPanel.appendCardEvent();
            aEventsPanel.resizeUI();
            aEventsPanel.resetScroll(topBar.getBounds().height);
        }
        else if(e.getSource() == admChangePassBtn) {
            aPasswordPanel.setVisible(true);
            lastPanel = aPasswordPanel;

            resizeUI();
            aPasswordPanel.resizeUI();
            aPasswordPanel.setupFields();
        }
        else if(e.getSource() == admDisconnectBtn) {
            Object[] options = { "Confirm", "Anulare" };
            int r = JOptionPane.showOptionDialog(null, "Sunteti sigur ca doriti sa va deconectati de la contul de admin?", "Deconectare", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
            if(r == 0) {
                if(lastPanel != null) lastPanel.setVisible(false);
                adminBar.setVisible(false);
                resetMenuBar();
                selectedMenu = null;
                selectedCircle.setBounds(0, 0, 0, 0);

                loginPanel.setVisible(true);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == closeBtn) {
            dragTimer.cancel();
            if(preferencePanel.isVisible() && menuBar.isVisible()) {
                user.addPreferences(preferencePanel.getOptions());
                User.saveUser(user);
            }
            dispose();
        }
        else if(e.getSource() == maximizeBtn) {
            if(!maximized) {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                maximized = true;
            }
            else {
                setBounds(0, 0, frameW, frameH);
                setLocationRelativeTo(null);
                maximized = false;
            }
            resizeUI();
        }
        else if(e.getSource() == minimizeBtn) {
            setExtendedState(JFrame.ICONIFIED);
        }
        if(e.getSource() == registerBtn) {
            loginText.setText("Inregistrare");

            resetInputFields();

            registerBtn.setVisible(false);
            loginBtn.setVisible(true);
            login = false;
        }
        else if(e.getSource() == loginBtn) {
            loginText.setText("Autentificare");

            resetInputFields();

            loginBtn.setVisible(false);
            registerBtn.setVisible(true);
            login = true;
        }
        else if(e.getSource() == adminBtn) {
            loginText.setText("Logare ca admin");

            passFocused = false;
            passInp.setText("Parola admin");
            passInp.setEchoChar((char)0);
            passInp.setBounds(emailInp.getBounds());

            emailInp.setVisible(false);
            verticalLine.setVisible(false);
            registerBtn.setVisible(false);
            loginBtn.setVisible(false);
            adminBtn.setVisible(false);
            backBtn.setVisible(true);
        }
        else if(e.getSource() == confirmBtn) {
            if(loginPanel.isVisible()) {
                if(loginText.getText().equals("Logare ca admin")) {
                    if(passInp.getPassword().length > 0 && passInp.getEchoChar() != ((char)0)) {
                        user = new User(String.valueOf(passInp.getPassword()));

                        boolean correct = user.loginAsAdmin();
                        if(correct) {
                            loginPanel.setVisible(false);
                            adminBar.setVisible(true);
                            adminBar.add(selectedCircle);
                            resetInputFields();
                            resizeUI();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Parola introdusa nu este corecta!");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Introduceti parola!");
                    }
                }
                else {
                    if(loginText.getText().equals("Inregistrare")) {
                        user = new User(emailInp.getText(), new String(passInp.getPassword()), new ArrayList<Integer>(), "", new ArrayList<Event>());
                        if(user.isEmailValid()) {
                            if(passInp.getEchoChar() != ((char)0)) {
                                if(user.isPasswordValid()) {
                                    if(!User.userExists(user)) {
                                        resetInputFields();
                                        showPreferences();
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(null, "Exista deja un cont cu acest nume de utilizator!");
                                    }
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "Parola trebuie sa contina cel putin 3 caractere!");
                                }
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Introduceti parola!");
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Introduceti o adresa de email valida!");
                        }
                    }
                    else {
                        user = new User(emailInp.getText(), new String(passInp.getPassword()));
                        if(user.isEmailValid()) {
                            if(passInp.getEchoChar() != ((char)0)) {
                                if(user.isPasswordValid()) {
                                    if(user.login()) {
                                        loginUser();
                                        resetInputFields();
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(null, "Adresa de email sau parola introdusa nu este corecta!");
                                    }
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "Parola trebuie sa contina cel putin 3 caractere!");
                                }
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Introduceti parola!");
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Introduceti o adresa de email valida!");
                        }
                    }
                }
            }
        }
        else if(e.getSource() == backBtn) {
            if(loginPanel.isVisible()) {
                passInp.setBounds(emailInp.getBounds().x, emailInp.getBounds().y + emailInp.getBounds().height + menuOffset * 3, inputW, inputH);

                resetInputFields();

                backBtn.setVisible(false);
                emailInp.setVisible(true);
                verticalLine.setVisible(true);
                if(!login) {
                    loginText.setText("Inregistrare");
                    loginBtn.setVisible(true);
                }
                else {
                    loginText.setText("Autentificare");
                    registerBtn.setVisible(true);
                }
                adminBtn.setVisible(true);
            }
        }
        else if(e.getSource() == nextBtn) {
            if(preferencePanel.isVisible()) {
                user.addPreferences(preferencePanel.getOptions());
                showLocation();
            }
            else if(locationPanel.isVisible()) {
                user.setLocation(locationPanel.getOption());
                locationPanel.resetLocation();
                User.registerUser(user);
                loginUser();
                Main.saveInfo(0, true);
                JOptionPane.showMessageDialog(null, "Contul a fost creat cu succes!");
            }
        }
        else if(e.getSource() == eventsBtn || e.getSource() == changePassBtn || e.getSource() == prefBtn) {
            if(selectedMenu != null) {
                resetMenuBar();
            }

            selectedMenu = ((MyButton)e.getSource());
            selectedCircle.setBounds(((JButton)e.getSource()).getBounds().x, ((JButton)e.getSource()).getBounds().y, selectedImg.getWidth(selectedCircle), selectedImg.getHeight(selectedCircle));
            selectedCircle.setBounds(selectedCircle.getBounds().x - leftMargin / 2 - selectedImg.getWidth(selectedCircle) / 2, selectedCircle.getBounds().y + (eventsBtn.getBounds().height - selectedCircle.getBounds().height) / 2, selectedImg.getWidth(selectedCircle), selectedImg.getHeight(selectedCircle));
            ((JButton)e.getSource()).setForeground(btnColor);
            ((JButton)e.getSource()).setFont(new Font("Monospaced", Font.BOLD, ((JButton)e.getSource()).getFont().getSize()));
            handleMenuBar(e);
        }
        else if(e.getSource() == disconnectBtn) {
            handleMenuBar(e);
        }
        else if(e.getSource() == panelBtn || e.getSource() == addEventBtn || e.getSource() == manageEventsBtn || e.getSource() == admChangePassBtn) {
            if(selectedMenu != null) {
                resetMenuBar();
            }

            selectedMenu = ((MyButton)e.getSource());
            selectedCircle.setBounds(((JButton)e.getSource()).getBounds().x, ((JButton)e.getSource()).getBounds().y, selectedImg.getWidth(selectedCircle), selectedImg.getHeight(selectedCircle));
            selectedCircle.setBounds(selectedCircle.getBounds().x - leftMargin / 2 - selectedImg.getWidth(selectedCircle) / 2, selectedCircle.getBounds().y + (selectedMenu.getBounds().height - selectedCircle.getBounds().height) / 2, selectedImg.getWidth(selectedCircle), selectedImg.getHeight(selectedCircle));
            ((JButton)e.getSource()).setForeground(btnColor);
            ((JButton)e.getSource()).setFont(new Font("Monospaced", Font.BOLD, ((JButton)e.getSource()).getFont().getSize()));

            handleAdminMenu(e);
        }
        else if(e.getSource() == admDisconnectBtn) {
            handleAdminMenu(e);
        }
    }
}

class JTextFieldLimit extends PlainDocument {
    private int limit;

    public JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if(str == null) return;
    
        if((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}