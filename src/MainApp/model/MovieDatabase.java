package MainApp.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDatabase {
    private List<Movie> movies;
    private String filePath; // File path

    public MovieDatabase(String filePath) {
        this.filePath = filePath;
        movies = readMoviesFromFile(); // Read data from the file
    }

    public void addMovie(Movie newMovie) {
        if (!movies.contains(newMovie)) {
            movies.add(newMovie);
            writeMoviesToFile(); // Write to the file after adding a movie
        }
    }

    public void removeMovie(String title) {
        movies.removeIf(m -> m.getTitle().equals(title));
        writeMoviesToFile(); // Write to the file after removing a movie
    }

    public List<Movie> getMovies() {
        return movies;
    }

    private List<Movie> readMoviesFromFile() {
        List<Movie> movieList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Convert each line to a Movie object and add it to the list
                Movie movie = Movie.fromString(line);
                if (movie != null) {
                    movieList.add(movie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    private void writeMoviesToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Movie movie : movies) {
                // Convert each Movie object to a line and write to the file
                bw.write(movie.toString());
                bw.newLine(); // Move to the next line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
