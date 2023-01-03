import java.io.FileNotFoundException;
import java.io.IOException;

class Main{
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
        System.out.println("Hello World");
        User user1 = new User();
        user1.addPreference(3);
        user1.addPreference(0);
        user1.addPreference(3);
        user1.setEmail("ceva@gmail.com");
        user1.setPassword("geani");
        System.out.println(user1.getEmail());
        User.saveUser(user1);
        User user2 = User.readUser();
        System.out.println(user2.getEmail());
    }
}