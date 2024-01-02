package MainApp.model;

import java.io.*;
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
    static String tempFilePath="Userdatabase_temp.txt";
    public static boolean register(User user) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            // Add user information and watchlist to the file
            fileWriter.write(user.toString());
            fileWriter.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
                            List<Movie> movies =new ArrayList<>();
                            for (String movieInfo : watchlistInfo) {
                                String[] movieDetails = movieInfo.split(";");
                                String title = movieDetails[0];
                                String director = movieDetails[1];
                                int releaseYear = Integer.parseInt(movieDetails[2]);
                                int runningTime = Integer.parseInt(movieDetails[3]);

                                // Create a new Movie object using the Movie class
                                Movie movie = new Movie(title, director, releaseYear, runningTime);
                                movies.add(movie);

                            }
                            // Add to the watchlist
                            user.setWatchlist(movies);
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

    public void  updateWatchList(){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split("\\|");
                if (userInfo.length >= 2) {
                    String storedUsername = userInfo[0];

                    // Check if the current line corresponds to the user to be updated
                    if (username.equals(storedUsername)) {
                        // Perform the update
                        line = toString();
                    }
                }
                // Write the line to the temporary file
                writer.write(line + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the original file with the temporary file
        File oldFile = new File(filePath);
        File newFile = new File(tempFilePath);
        if (newFile.renameTo(oldFile)) {
            System.out.println("Watchlist updated successfully.");
        } else {
            System.out.println("Failed to update the watchlist.");
        }

    }
}
