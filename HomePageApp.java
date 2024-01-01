import MainApp.model.Movie;
import MainApp.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class HomePageApp extends JFrame {
    User user;
    JTable movieTable;

    private List<Movie> watchList;
    // Constructor
    public HomePageApp(User user) {
        // Set the title of the frame
        super("HomePage");
        this.user=user;
        this.watchList=user.getWatchlist();
        // Set the size of the frame
        setSize(800, 600);
        // Set the location of the frame
        setLocationRelativeTo(null);
        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the layout of the frame
        setLayout(new BorderLayout());

        // Create rightPanel with BoxLayout
        // Create an outer panel with FlowLayout
        JPanel outerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        outerPanel.setBorder(BorderFactory.createEmptyBorder(200, 10, 10, 10));

        // Create rigthPanel with BoxLayout
        JPanel rigthPanel = new JPanel();
        rigthPanel.setLayout(new BoxLayout(rigthPanel, BoxLayout.Y_AXIS));

        // Create a label for the user name
        JLabel userLabel = new JLabel("Welcome, " + user.getUsername());
        userLabel.setFont(new Font("Arial", Font.BOLD, 30));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userLabel.setBorder(BorderFactory.createEmptyBorder(10,10,20,10));
        rigthPanel.add(userLabel);

        JButton searchButton = new JButton("Search Movie");
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rigthPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        rigthPanel.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new SearchMoviePageApp(user).setVisible(true));
            }
        });
        // Add rigthPanel to the outer panel
        outerPanel.add(rigthPanel);

        // Add the outerPanel to the frame
        add(outerPanel, BorderLayout.LINE_START);

        // Create whatcListPanel with BoxLayout
        JPanel whatcListPanel = new JPanel();
        whatcListPanel.setLayout(new BoxLayout(whatcListPanel, BoxLayout.Y_AXIS));

        // Create a label for the watch list
        JLabel watchListLabel = new JLabel("Your Watch List");
        // Set the font and alignment of the label
        watchListLabel.setFont(new Font("Arial", Font.BOLD, 30));

        // Add the label to the panel
        whatcListPanel.add(watchListLabel);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Index");
        tableModel.addColumn("Title");
        tableModel.addColumn("Director");
        tableModel.addColumn("Release Year");
        tableModel.addColumn("Running Time");

        // Populate the table with movie data
        updateTable(tableModel);
        movieTable = new JTable(tableModel);
        // Set the column width of the table
        movieTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        movieTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        movieTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        movieTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        movieTable.getColumnModel().getColumn(4).setPreferredWidth(50);

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(movieTable);

        // Add the components to the panel
        whatcListPanel.add(scrollPane);

        // Add the panel to the center of the frame
        add(whatcListPanel, BorderLayout.CENTER);

        // Create a button for adding movies
        JButton updateButton = new JButton("Update");
        // Add an action listener to the add button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updateTable(tableModel);
            }
        });
    }
}