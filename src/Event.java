import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.*;
public class Event implements Serializable {
    private String title;
    private Color titleColor;
    private String location;
    private String date;
    private String category;
    private String description;
    private double ticketPrice;

    private String daysDate;
    private String monthDate;
    private int yearDate;
    private String hourDate;

    //Constructor
    Event(){};

    //Relevanta
    int getRelevance(User user,Event event){
        int relevance=0;
        if(user.getLocation() ==event.location){
            relevance++;
        }
        for(int i=0;i<user.getPreference().size();i++){
            if(user.getPreference().get(i).equals(event.category)){
                relevance++;
                break;
            }
        }
        return relevance;
    }

    //salveaza lista evenimente
    public static void saveEvents(ArrayList<Event> events){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src//data//events.txt"));
            out.writeObject(events);
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //citeste lista evenimente
    public static ArrayList<Event> readEvents(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(("src//data//events.txt")));
            ArrayList<Event> events = (ArrayList<Event>) in.readObject();
            in.close();
            return events;
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: handle exception
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
        
    }



    //set si get
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Color getTitleColor() {
        return this.titleColor;
    }

    public void setTitleColor(Color titleColor) {
        this.titleColor = titleColor;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate() {
        StringBuffer sb = new StringBuffer();
        sb.append("Data: ");
        sb.append(getDaysDate());
        sb.append(" ");
        sb.append(getMonthDate());
        sb.append(" ");
        sb.append(getYearDate());
        sb.append(", ora ");
        sb.append(getHourDate());
        this.date = sb.toString();
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTicketPrice() {
        return this.ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getDaysDate() {
        return this.daysDate;
    }

    public void setDaysDate(String daysDate) {
        this.daysDate = daysDate;
    }

    public String getMonthDate() {
        return this.monthDate;
    }

    public void setMonthDate(String monthDate) {
        this.monthDate = monthDate;
    }

    public int getYearDate() {
        return this.yearDate;
    }

    public void setYearDate(int yearDate) {
        this.yearDate = yearDate;
    }

    public String getHourDate() {
        return this.hourDate;
    }

    public void setHourDate(String hourDate) {
        this.hourDate = hourDate;
    }
    
    
}
