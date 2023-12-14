import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User: " +username; 
    }

    static String filePath = "database.txt";
    public static void register(String username, String password) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath))) {
            fileWriter.write(username);
            fileWriter.newLine();
            fileWriter.write(password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean login(String username, String password) {
        boolean result = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String firstLine = reader.readLine();

            String secondLine = reader.readLine();

            if (firstLine.equals(username) && secondLine.equals(password)) {
                result = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
