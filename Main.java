public class Main {
    public static void main(String[] args) {
        // Movie m = new Movie("MF", "Rovsen", 2021, 120);
        // System.out.println(m.toString());
        // MovieDatabase md = new MovieDatabase();
        

       User guest1 = new User(null, null);
       guest1.register("Jake", "tommygun07");
       System.out.println(guest1.login("Jake", "tommygun07"));
       
    }
}
