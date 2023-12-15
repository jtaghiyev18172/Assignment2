public class Main {
    public static void main(String[] args) {

       User guest1 = new User(null, null);
       guest1.register("Jake090", "tommygun07");
       System.out.println(guest1.login("Jake090", "tommygun07"));
       
    }
}
