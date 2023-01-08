package proiect;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class User implements Serializable {
    Utils utile = new Utils();
    private boolean isLogged;
    private String email;
    private String password;
    private String location;
    private ArrayList<Integer> preference;
    private ArrayList<Event> savedEvent;
    private ArrayList<Ticket> boughtTickets;
    

    //constructor
    User(){
        this.preference = new ArrayList<Integer>();
        this.savedEvent = new ArrayList<Event>();
        this.boughtTickets = new ArrayList<Ticket>();
    };

    User(String email,String password){
        this.email = email;
        this.password = password;
        this.preference = new ArrayList<Integer>();
        this.savedEvent = new ArrayList<Event>();
        this.boughtTickets = new ArrayList<Ticket>();
    };


    //adauga bilet cumparat
    public void buyTicket(Ticket ticket){
        getBoughtTickets().add(ticket);
    }

    //sterge bilet cumparat

    public void sellTicket(Ticket ticket){
        if(getBoughtTickets()!=null){
            if(getBoughtTickets().contains(ticket)){
                for(int i=0;i<getBoughtTickets().size();i++){
                    if(getBoughtTickets().get(i).equals(ticket)){
                        getBoughtTickets().remove(i);
                    }
                }
            }
        }
    }




    //adauga evenimente salvate

    void addSavedEvent(Event event){
        if(this.savedEvent.size() == 0){
            this.savedEvent.add(event);
        }
        else if(this.savedEvent.contains(event) == false){
            this.savedEvent.add(event);
        }
    }

    //sterge eveniment salvat

    void removeSavedEvent(Event event){
        if(getSavedEvents()!=null){
            if(getSavedEvents().contains(event)){
                for(int i=0;i<getSavedEvents().size();i++){
                    if(getSavedEvents().get(i).equals(event)){
                        getSavedEvents().remove(i);
                    }
                }
            }
        }
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
    
    void removePreference(int preference){
        if(getPreference().contains(preference)){
            for (int i=0;i<getPreference().size();i++){
                if(getPreference().get(i).equals(preference)){
                    getPreference().remove(i);
                }
            }
        }    
    }

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
        if(userExists(email, password)){
            return true;
        }
        return false;
    }

    //verifica daca exista deja
    public static boolean userExists(String email,String password){
        ArrayList<User> existentUsers = User.readUsers();
        for(int i=0;i<existentUsers.size();i++){
            if(existentUsers.get(i).getEmail().equalsIgnoreCase(email) && existentUsers.get(i).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    } 



    //inregistrare

    public static void registerUser(String email,String password){
        if(userExists(email, password)){
            JOptionPane.showMessageDialog(null,"Utilizatorul exista deja");
        }
        else{
            ArrayList<User> users = User.readUsers();
            users.add(new User(email,password));
            User.saveUser(users);
        }
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

    public ArrayList<Integer> getPreference() {
        return this.preference;
    }


    public String getLocation() {
        return this.location;
    }

    public void setLocatie(String location) {
        this.location = location;
    }

    //get evenimente salvate

    public ArrayList<Event> getSavedEvents(){
        return this.savedEvent;
    }

    //get bilete cumparate
    public ArrayList<Ticket> getBoughtTickets(){
        return this.boughtTickets;
    }

    //toString()
    public String toString(){
       StringBuffer sb = new StringBuffer();
       sb.append("Email: ");
       sb.append(this.getEmail());
       sb.append("\nPassword: ");
       sb.append(password);
       return sb.toString(); 
    }
}
