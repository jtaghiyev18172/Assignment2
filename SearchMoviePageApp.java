package MainApp;
import MainApp.model.Movie;
import MainApp.model.MovieDatabase;
import MainApp.model.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.stream.Collectors;

public class SearchMoviePageApp extends JFrame {
    private List<Movie> movieList;
    private User user;
    private List<Movie> watchList;
    private JTable table;
    private JTextField searchField;
    private JComboBox<String> sortByComboBox;

    public SearchMoviePageApp(User user) {
        MovieDatabase movieDatabase= new MovieDatabase("Moviedatabase.txt");

        movieList= movieDatabase.getMovies();
        this.user = user;
        this.watchList=user.getWatchlist();
        // JFrame settings
        setTitle("Search Movie Page App");
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Create table model and set column names
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Index");
        tableModel.addColumn("Title");
        tableModel.addColumn("Director");
        tableModel.addColumn("Release Year");
        tableModel.addColumn("Running Time");

        // Populate the table with movie data
        for (int i = 0; i < movieList.size(); i++) {
            Movie movie = movieList.get(i);
            tableModel.addRow(new Object[]{i,movie.getTitle(), movie.getDirector(), movie.getReleaseYear(), movie.getRunningTime()});
        }
