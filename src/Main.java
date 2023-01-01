class Main{
    public static void main(String[] args){
        System.out.println("Hello World");
        Utilizator user1 = new Utilizator();
        user1.addPreference(3);
        user1.addPreference(0);
        user1.addPreference(3);
        System.out.println(user1.getPreference());
    }
}