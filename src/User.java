import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class User implements Serializable {
    Utils utile = new Utils();
    private String email;
    private String password;
    private ArrayList<Integer> preference;
    private String location;
    //private ArrayList<Event> savedEvent;
    

    //1~mail~parola

    //constructor
    User(){
        preference = new ArrayList<Integer>();
        

    };

    //adauga evenimente salvate

    void addSavedEvent(Event event){
        if(this.savedEvent.size() == 0){
            this.savedEvent.add(event);
        }
        else if(this.savedEvent.contains(event) == false){
            this.savedEvent.add(event);
        }
    }

    //adauga preferinte
    void addPreference(int preference){
        if(this.preference.size() == 0){
            this.preference.add(preference);
        }
        else if(this.preference.contains(preference)){
            this.preference.add(preference);
        }
    }

    //sterge preferinte
    
    /*void removePreference(int preference){
        try {
            if()
        } catch (Exception e) {
            // TODO: handle exception
        }
    }*/

    //citeste user
    static User readUser() throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("users.txt"));
        User user = (User) in.readObject();
        return user;
    }


    //salveaza user

    static void saveUser(User user) throws FileNotFoundException, IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("users.txt"));
        out.writeObject(user);
    }

    //set si get

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if(email.contains("@")){
            this.email = email;
        }
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public ArrayList<String> getPreference() {
        return this.preference;
    }

    public void setPreference(ArrayList<String> preference) {
        this.preference = preference;
    }*/

    public String getLocation() {
        return this.location;
    }

    public void setLocatie(String location) {
        this.location = location;
    }
    
}
