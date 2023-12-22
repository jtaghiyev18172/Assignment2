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
    }

    private JPanel createRegisterPanel() {
        return null;
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
    }
}