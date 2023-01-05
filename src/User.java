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
    private boolean isLogged;
    private String email;
    private String password;
    private ArrayList<Integer> preference;
    private String location;
    private ArrayList<Event> savedEvent;
    

    //constructor
    User(){
        preference = new ArrayList<Integer>();
        savedEvent = new ArrayList<Event>();

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

    //get evenimente salvate

    public ArrayList<Event> getSavedEvents(){
        return this.savedEvent;
    }

    //adauga preferinte
    void addPreference(int preference){
        if(this.preference.size() == 0){
            this.preference.add(preference);
        }
        else if(!this.preference.contains(preference)){
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
   
    static ArrayList<User> readUsers(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("src//data//users.txt"));
            ArrayList<User> users = (ArrayList<User>) in.readObject();
            return users;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    //salveaza user

    static void saveUser(ArrayList<User> users){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src//data//users.txt"));
            out.writeObject(users);
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    //logare
    public boolean logIn(String email,String password){
        ArrayList<User> users = User.readUsers();
        for(int i = 0 ;i < users.size();i++){
            if(users.get(i).getEmail().equalsIgnoreCase(email) && users.get(i).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }




    //set si get

    public boolean getIsLogged(){
        return this.isLogged;
    }

    public void setIsLogged(){
        this.isLogged = !this.isLogged;
    }

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
    

    @Override
    public String toString(){
        return "Email:" + getEmail() + " password:" + getPassword(); 
    }
}
