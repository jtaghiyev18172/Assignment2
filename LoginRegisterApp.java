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
        return null;
    }
}