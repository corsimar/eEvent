package proiect;
public class Ticket {
    private String title;
    private String location;
    private String date;
    private String category;

    

    Ticket(String title,String location,String date,String category){
        this.title = title;
        this.location = location;
        this.date = date;
        this.category = category;
    }

    Ticket(){};
    


    //set si get

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
