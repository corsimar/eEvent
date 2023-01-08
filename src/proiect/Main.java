package proiect;
import java.util.ArrayList;

class Main{
    public static void main(String[] args) {
        System.out.println("Hello World");
        User user1 = new User();
        User geani = new User();
        geani.setEmail("caca@gmail.com");
        user1.addPreference(3);
        user1.addPreference(0);
        user1.addPreference(3);
        user1.setEmail("ceva@gmail.com");
        user1.setPassword("geani");
        ArrayList<User> users = new ArrayList<>();
        users.add(geani);
        users.add(user1);
        Event event1 = new Event();
        event1.setTitle("Untold");
        geani.addSavedEvent(event1);
        geani.removeSavedEvent(new Event());


        User.saveUser(users);

        
        ArrayList<User>usersS = User.readUsers();
        System.out.println(usersS.get(0).getSavedEvents().get(0).getTitle());
        
        


        
        
    }
}