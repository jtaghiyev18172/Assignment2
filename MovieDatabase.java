import java.util.ArrayList;
import java.util.List;


public class MovieDatabase {
    private List<Movie> movies;

    public MovieDatabase() {
        movies = new ArrayList<>();
    }

    public void addMovie(String title, String director, int releaseYear, int runningTime) {
        Movie newMovie = new Movie(title, director, releaseYear, runningTime);
        if (!movies.contains(newMovie)) {
            movies.add(newMovie);
        }
    }

    public void removeMovie(String title) {
        movies.removeIf(m -> m.getTitle().equals(title)); // Predicate Interface in lambda expression
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MovieDatabase{" + "movies =" +movies+ "}";
    }
}
