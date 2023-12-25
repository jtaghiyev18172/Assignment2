package MainApp.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;



    List<Movie> watchlist;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.watchlist = new ArrayList<>();
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

    public List<Movie> getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(List<Movie> watchlist) {
        this.watchlist = watchlist;
    }
    @Override
    public String toString() {
        // Combine user information and watchlist into a single line
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(username).append("|").append(password).append("|");
        for (Movie movie : watchlist) {
            stringBuilder.append(movie.toString()).append(",");
        }
        return stringBuilder.toString();
    }


    static String filePath = "Userdatabase.txt";
    public static void register(User user) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            // Add user information and watchlist to the file
            fileWriter.write(user.toString());
            fileWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User login(String username, String password) {
        User user = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split("\\|");
                if (userInfo.length >= 2) { // Check if there are at least username and password
                    String storedUsername = userInfo[0];
                    String storedPassword = userInfo[1];

                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        user = new User(storedUsername, storedPassword);

                        // Check if watchlist information is available
                        if (userInfo.length == 3) {
                            String[] watchlistInfo = userInfo[2].split(","); // Split watchlist information

                            for (String movieInfo : watchlistInfo) {
                                String[] movieDetails = movieInfo.split(";");
                                String title = movieDetails[0];
                                String director = movieDetails[1];
                                int releaseYear = Integer.parseInt(movieDetails[2]);
                                int runningTime = Integer.parseInt(movieDetails[3]);

                                // Create a new Movie object using the Movie class
                                Movie movie = new Movie(title, director, releaseYear, runningTime);

                                // Add to the watchlist
                                user.getWatchlist().add(movie);
                            }
                        }
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}
