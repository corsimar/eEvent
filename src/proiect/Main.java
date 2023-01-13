package proiect;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static MainFrame mainFrame;
    public static int[] info = new int[3];
    public static void main(String[] args) {
        mainFrame = new MainFrame();
    }

    public static void saveInfo(int infoID, boolean add) {
        StringBuffer path = new StringBuffer(System.getProperty("user.dir"));
        path.append("\\src\\data\\statistics.txt");
        try {
            if(add) info[infoID]++;
            else info[infoID]--;
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path.toString())));
            StringBuffer sb = new StringBuffer();
            sb.append(info[0]); sb.append("\n"); sb.append(info[1]); sb.append("\n"); sb.append(info[2]);
            bw.write(sb.toString());
            bw.flush();
            bw.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void ticketClicked(int ticketID) {
        mainFrame.showTicket(ticketID);
    }

    public static void buyTicket(Event event) {
        mainFrame.buyTicket(event);
    }

    public static void closeTicket() {
        mainFrame.closeTicket();
    }
}