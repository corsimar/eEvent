import java.util.ArrayList;

public class Utilizator {
    Utile utile = new Utile();
    private String email;
    private String password;
    private ArrayList<String> preference;
    private ArrayList<String> location;
    private ArrayList<Eveniment> savedEvent;
    

    //constructor
    Utilizator(){
        preference = new ArrayList<String>();
        location = new ArrayList<String>();

    };

    //adauga preferinte
    void addPreference(int preference){
        if(this.preference.size() == 0){
            this.preference.add(Utile.preference.get(preference));
        }
        else if(this.preference.contains(Utile.preference.get(preference)) == false){
            this.preference.add(Utile.preference.get(preference));
        }
    }


    //set si get

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getPreference() {
        return this.preference;
    }

    public void setPreference(ArrayList<String> preference) {
        this.preference = preference;
    }

    public ArrayList<String> getLocation() {
        return this.location;
    }

    public void setLocatie(ArrayList<String> location) {
        this.location = location;
    }
    
}
