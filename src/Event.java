import java.io.Serializable;

public class Event implements Serializable {
    private String title;
    private String titleColor;
    private String location;
    private String date;
    private String category;
    private String description;
    private double ticketPrice;
    private String currency;

    private String daysDate;
    private String monthDate;
    private int yearDate;
    private String hourDate;

    //Constructor
    Event(){
        
    };

    //Relevanta
    int getRelevance(User user,Event event){
        int relevance=0;
        if(user.getLocation() ==event.location){
            relevance++;
        }
        for(int i=0;i<user.getPreference().size();i++){
            if(user.getPreference().get(i).equalsIgnoreCase(event.category)){
                relevance++;
                break;
            }
        }
        return relevance;
    }



    //set si get
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleColor() {
        return this.titleColor;
    }

    public void setTitleColor(String titleColor) {
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
        this.date = "Data: " + getDaysDate() +" "+ getMonthDate()+
                    " " + getYearDate() + ", ora " + getHourDate();
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

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
