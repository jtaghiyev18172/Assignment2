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

        // Create table with the model
        table = new JTable(tableModel);

        // Set the column width of the table
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);

        // Create scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);

        // Create search label and field
        JLabel searchLabel = new JLabel("Search Movie:");
        searchField = new JTextField(20);
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTable();
            }
        });

        // Create sort by combo box
        JLabel sortByLabel = new JLabel("Sort By:");
        String[] sortByOptions = {"","Release Year", "Running Time"};
        sortByComboBox = new JComboBox<>(sortByOptions);
        sortByComboBox.addActionListener(e -> updateTable());

        // Create buttons
        JButton addToWatchListButton = new JButton("Add Your Watch List");
        addToWatchListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToWatchList();
            }
        });

        // Create layout
        JPanel panel = new JPanel(new BorderLayout());
        JPanel topPanelContainer = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Add components to the top panel
        topPanel.add(searchLabel);
        topPanel.add(searchField);

        // Add components to the top right panel
        topRightPanel.add(sortByLabel);
        topRightPanel.add(sortByComboBox);

        // Add topPanel and topRightPanel to topPanelContainer
        topPanelContainer.add(topPanel, BorderLayout.WEST);
        topPanelContainer.add(topRightPanel, BorderLayout.EAST);

        // Add buttons to bottom panel
        bottomPanel.add(addToWatchListButton);

        // Add panels to the main panel
        panel.add(topPanelContainer, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Set main panel as content pane
        setContentPane(panel);

        // Initial table update
        updateTable();
    }

    private void updateTable() {
        String searchTerm = searchField.getText().toLowerCase();
        String sortBy = (String) sortByComboBox.getSelectedItem();

        List<Movie> filteredList = movieList.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(searchTerm)
                        || movie.getDirector().toLowerCase().contains(searchTerm)
                        || String.valueOf(movie.getReleaseYear()).contains(searchTerm)
                        || String.valueOf(movie.getRunningTime()).contains(searchTerm))
                .collect(Collectors.toList());

        // Sort the filtered list based on the selected option
        filteredList.sort((movie1, movie2) -> {
            switch (sortBy) {
                case "Release Year":
                    return Integer.compare(movie1.getReleaseYear(), movie2.getReleaseYear());
                case "Running Time":
                    return Integer.compare(movie1.getRunningTime(), movie2.getRunningTime());
                default:
                    return 0;
            }
        });

        // Update the table model
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0); // Clear existing rows

        for (int i = 0; i < filteredList.size(); i++) {
            Movie movie = filteredList.get(i);
            tableModel.addRow(new Object[]{i,movie.getTitle(), movie.getDirector(), movie.getReleaseYear(), movie.getRunningTime()});
        }
    }

    private void addToWatchList() {
        int selectedRow = table.getSelectedRow();
        int columnCount = table.getColumnCount();
        if (selectedRow != -1) {
            Object[] rowData = new Object[columnCount];

            for (int i = 0; i < columnCount; i++) {
                rowData[i] = table.getValueAt(selectedRow, i);
            }

            Movie selectedMovie =new Movie((String) rowData[1],(String)rowData[2],(int) rowData[3],(int) rowData[4]);
            watchList.add(selectedMovie);
            user.setWatchlist(watchList);
            user.updateWatchList();
            JOptionPane.showMessageDialog(this, "Movie added to your watch list!");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a movie to add to your watch list.");
        }
    }
}
