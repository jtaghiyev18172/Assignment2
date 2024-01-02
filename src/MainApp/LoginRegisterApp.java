package MainApp;
import MainApp.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginRegisterApp extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public LoginRegisterApp() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Login Panel
        JPanel loginPanel = createLoginPanel();
        cardPanel.add(loginPanel, "login");

        // Register Panel
        JPanel registerPanel = createRegisterPanel();
        cardPanel.add(registerPanel, "register");

        add(cardPanel);

        JButton loginButton = new JButton("Login Here !");
        JButton registerButton = new JButton("Register Here !");
        loginButton.setVisible(false);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerButton.setVisible(true);
                loginButton.setVisible(false);
                cardLayout.show(cardPanel, "login");
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerButton.setVisible(false);
                loginButton.setVisible(true);
                cardLayout.show(cardPanel, "register");

            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform actions when the login button is clicked
                String username = userField.getText();
                String password = passwordField.getText();

                User user=User.login(username,password);

                if(user!=null){
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new HomePageApp(user).setVisible(true);
                            ((JFrame) SwingUtilities.getWindowAncestor(userField)).dispose();
                        }
                    });
                }
                else {
                    JOptionPane.showMessageDialog(
                            (Component) e.getSource(), // Component to be used as the parent for the dialog
                            "Login unsuccessful. Please check your username and password.",
                            "Login Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JPasswordField confirmPasswordField = new JPasswordField(15);

        JButton registerButton = new JButton("Register");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(confirmPasswordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(confirmPasswordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(registerButton, gbc);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform actions when the register button is clicked
                String username = userField.getText();
                String password = passwordField.getText();
                String confirmPassword = confirmPasswordField.getText();
                // You can perform username, password, and confirmPassword validation here
                if(password.equals(confirmPassword)){
                      User user=new User(username,password);
                      // Attempt to register the user
                      boolean registrationSuccess = User.register(user);
                      // Show dialog based on registration success
                      if (registrationSuccess) {
                          JOptionPane.showMessageDialog(null, "Registration successful!");
                      } else {
                           JOptionPane.showMessageDialog(null, "Registration failed. Please check your input and try again.");
                            }
                }
                else JOptionPane.showMessageDialog(null, "Passwords do not match. Please make sure your passwords match.");
            }
        });

        return panel;
    }

}
