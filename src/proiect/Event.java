package proiect;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.Color;

@SuppressWarnings("unchecked")
public class Event implements Serializable {
    private String title;
    private Color titleColor;
    private String location;
    private String date;
    private String hour;
    private String category;
    private String description;
    private int ticketPrice;
    private int relevance;

    public Event(String title, Color titleColor, String location, String date, String hour, String category, String description, int ticketPrice) {
        this.title = title; this.titleColor = titleColor; this.location = location; this.date = date; this.hour = hour; this.category = category; this.description = description; this.ticketPrice = ticketPrice;
    }

    public int getRelevance(User user) {
        this.relevance = 0;
        if(user.getLocation() == this.location) {
            this.relevance++;
        }
        for(int i = 0; i < user.getPreferences().size(); i++) {
            if(Utils.categories.get(user.getPreferences().get(i)).equals(this.category)) {
                this.relevance++;
                break;
            }
        }
        return this.relevance;
    }

    public static void saveEvent(Event event) {
        try {
            StringBuffer path = new StringBuffer(System.getProperty("user.dir"));
            path.append("\\src\\data\\events.txt");

            ArrayList<Event> events = readEvents();
            events.add(event);

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path.toString()));
            out.writeObject(events);
            out.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
    
    public static ArrayList<Event> readEvents() {
        try {
            StringBuffer path = new StringBuffer(System.getProperty("user.dir"));
            path.append("\\src\\data\\events.txt");

            ObjectInputStream in = new ObjectInputStream(new FileInputStream((path.toString())));
            ArrayList<Event> events = (ArrayList<Event>) in.readObject();
            in.close();
            return events;
        } catch (IOException e) {
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static boolean deleteEvent(int eventID) {
        try {
            StringBuffer path = new StringBuffer(System.getProperty("user.dir"));
            path.append("\\src\\data\\events.txt");

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path.toString()));
            ArrayList<Event> events = readEvents();
            in.close();
            events.remove(eventID);

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path.toString()));
            if(events.size() > 0) out.writeObject(events);
            out.close();

            return true;
        } catch(IOException ex) {
            return false;
        }
    }

    public String getTitle() {
        return this.title;
    }

    public Color getTitleColor() {
        return this.titleColor;
    }

    public String getLocation() {
        return this.location;
    }

    public String getDateMonth() {
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

    public String getDate() {
        StringBuffer sb = new StringBuffer();
        sb.append(Integer.parseInt(date.substring(0, 2)));
        sb.append(" ");
        sb.append(getDateMonth().toLowerCase());
        sb.append(" ");
        sb.append(date.substring(6));

        return sb.toString();
    }

    public String getCategory() {
        return this.category;
    }

    public String getDescription() {
        return this.description;
    }

    public int getTicketPrice() {
        return this.ticketPrice;
    }

    public String getHour() {
        return this.hour;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        return (this.getTitle().equals(((Event)obj).getTitle()));
    }

    public String getTicket() {
        StringBuffer sb = new StringBuffer(getTitle());
        sb.append("\nLocatie: ");
        sb.append(getLocation());
        sb.append("\nData: ");
        sb.append(getDate());
        sb.append(", la ora ");
        sb.append(getHour());
        sb.append("\nCategorie: ");
        sb.append(getCategory());
        sb.append("\nPret: ");
        sb.append(getTicketPrice());
        return sb.toString();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("Titlu: ");
        sb.append(getTitle());
        sb.append("\nCuloare: ");
        sb.append(getTitleColor().toString());
        sb.append("\nLocatie: ");
        sb.append(getLocation());
        sb.append("\nData: ");
        sb.append(getDate());
        sb.append("\nOra: ");
        sb.append(getHour());
        sb.append("\nCategorie: ");
        sb.append(getCategory());
        sb.append("\nDescriere: ");
        sb.append(getDescription());
        sb.append("\nPret bilet: ");
        sb.append(getTicketPrice());
        return sb.toString();
    }
}