package proiect;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EventsPanel extends MyPanel implements ActionListener {
    private JLabel noElementsFoundL;
    private ArrayList<EventCard> eventCard;
    private ArrayList<JButton> eventOption;
    private int cardW, cardH = 300, panelH;
    private JButton scrollUpBtn, scrollDownBtn;
    private int scrollLevel = 0, scrollStep;
    private boolean created = false;

    public EventsPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        setVisible(false);

        loadImages();
        
        noElementsFoundL = new JLabel("Nu au fost adaugate evenimente!");
        noElementsFoundL.setFont(new Font("Monospace", Font.BOLD, fontTitleSize));
        noElementsFoundL.setForeground(textColor);
        noElementsFoundL.setHorizontalAlignment(SwingConstants.CENTER);
        noElementsFoundL.setVerticalAlignment(SwingConstants.CENTER);
        noElementsFoundL.setVisible(false);
        add(noElementsFoundL);

        scrollUpBtn = new JButton();
        scrollUpBtn.setIcon(new ImageIcon(upArrowImg));
        scrollUpBtn.setBackground(btnColor);
        scrollUpBtn.setBorder(null);
        scrollUpBtn.setFocusPainted(false);
        scrollUpBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        scrollUpBtn.addActionListener(this);
        add(scrollUpBtn);

        scrollDownBtn = new JButton();
        scrollDownBtn.setIcon(new ImageIcon(downArrowImg));
        scrollDownBtn.setBackground(btnColor);
        scrollDownBtn.setBorder(null);
        scrollDownBtn.setFocusPainted(false);
        scrollDownBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        scrollDownBtn.addActionListener(this);
        add(scrollDownBtn);
    }

    public boolean cardsCreated() {
        return created;
    }

    public void setPanelH(int frameH, int topBarH) {
        panelH = frameH - topBarH;
    }

    public void resetScroll(int topBarH) {
        scrollLevel = 0;
        setBounds(getBounds().x, topBarH, getBounds().width, getBounds().height);
    }

    public void resizeScroll(int topBarH) {
        setBounds(getBounds().x, topBarH, getBounds().width, getBounds().height);
        int i = 0, scroll = scrollLevel;
        scrollLevel = 0;
        while(i < scroll) {
            scrollDown();
            i++;
        }
        scrollLevel = scroll;
    }

    public void createCardEvents() {
        ArrayList<Event> arr = Event.readEvents();
        if(arr.size() > 0) {
            scrollUpBtn.setVisible(true);
            scrollDownBtn.setVisible(true);
            noElementsFoundL.setVisible(false);
            eventCard = new ArrayList<EventCard>();
            eventOption = new ArrayList<JButton>();
            for(int i = 0; i < arr.size(); i++) {
                eventCard.add(new EventCard(arr.get(i)));
                add(eventCard.get(i));
                addCardOptions(eventCard.get(i));
            }
            created = true;
        }
        else {
            noElementsFoundL.setVisible(true);
            scrollUpBtn.setVisible(false);
            scrollDownBtn.setVisible(false);
        }
    }

    public void appendCardEvent() {
        ArrayList<Event> arr = Event.readEvents();
        int diff = arr.size() - eventCard.size();
        if(diff > 0) {
            for(int i = 0; i < diff; i++) {
                eventCard.add(new EventCard(arr.get(eventCard.size() + i)));
                add(eventCard.get(eventCard.size() - 1));
                addCardOptions(eventCard.get(eventCard.size() - 1));
            }
        }
    }

    private void resizeCardEvents() {
        int y = 0, h = panelH * (eventCard.size() / 2 + 1);
        setBounds(getBounds().x, getBounds().y, getBounds().width, h);
        cardW = getBounds().width * 7 / 10;
        for(int i = 0; i < eventCard.size(); i += 2) {
            eventCard.get(i).setBounds(leftMargin, y + panelH / 4 - (cardH + btnH + menuOffset * 3) / 2, cardW, cardH);
            eventCard.get(i).resizeUI();
            eventOption.get(i).setBounds(eventCard.get(i).getBounds().x + eventCard.get(i).getBounds().width - btnW, eventCard.get(i).getBounds().y + eventCard.get(i).getBounds().height + menuOffset * 3, btnW, btnH);
            if(i + 1 < eventCard.size()) {
                eventCard.get(i + 1).setBounds(leftMargin, y + panelH * 3 / 4 - (cardH + btnH + menuOffset * 3) / 2, cardW, cardH);
                eventCard.get(i + 1).resizeUI();
                eventOption.get(i + 1).setBounds(eventCard.get(i + 1).getBounds().x + eventCard.get(i + 1).getBounds().width - btnW, eventCard.get(i + 1).getBounds().y + eventCard.get(i + 1).getBounds().height + menuOffset * 3, btnW, btnH);
            }
            y += panelH;
        }
        scrollStep = panelH;
    }

    private void addCardOptions(EventCard card) {
        JButton btn = new JButton("Bilet");
        btn.setFont(new Font("Monospaced", Font.BOLD, fontMenuSize));
        btn.setBackground(btnColor);
        btn.setForeground(Color.WHITE);
        btn.setBorder(null);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        int btnID = eventOption.size() + 1;
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(btnID);                
            }
        });
        btn.setBounds(card.getBounds().x + card.getBounds().width - btnW, card.getBounds().y + card.getBounds().height + menuOffset * 3, btnW, btnH);
        add(btn);
        eventOption.add(btn);
    }

    private void scrollUp() {
        if(scrollLevel > 0) {
            scrollUpBtn.setBounds(scrollUpBtn.getBounds().x, scrollUpBtn.getBounds().y - scrollStep, scrollUpBtn.getBounds().width, scrollUpBtn.getBounds().height);
            scrollDownBtn.setBounds(scrollDownBtn.getBounds().x, scrollDownBtn.getBounds().y - scrollStep, scrollDownBtn.getBounds().width, scrollDownBtn.getBounds().height);
            setBounds(getBounds().x, getBounds().y + scrollStep, getBounds().width, getBounds().height);
            scrollLevel--;
        }
    }

    private void scrollDown() {
        if(scrollLevel < (eventCard.size() - 1) / 2) {
            scrollUpBtn.setBounds(scrollUpBtn.getBounds().x, scrollUpBtn.getBounds().y + scrollStep, scrollUpBtn.getBounds().width, scrollUpBtn.getBounds().height);
            scrollDownBtn.setBounds(scrollDownBtn.getBounds().x, scrollDownBtn.getBounds().y + scrollStep, scrollDownBtn.getBounds().width, scrollDownBtn.getBounds().height);
            setBounds(getBounds().x, getBounds().y - scrollStep, getBounds().width, getBounds().height);
            scrollLevel++;
        }
    }

    @Override
    public void resizeUI() {
        noElementsFoundL.setBounds(0, 0, getBounds().width, getBounds().height);
        scrollUpBtn.setBounds(getBounds().width - btnH - leftMargin, leftMargin, btnH, btnH);
        scrollDownBtn.setBounds(getBounds().width - btnH - leftMargin, panelH - btnH - leftMargin, btnH, btnH);

        if(cardsCreated()) resizeCardEvents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == scrollUpBtn) {
            scrollUp();
        }
        else if(e.getSource() == scrollDownBtn) {
            scrollDown();
        }
    }
}
