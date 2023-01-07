package GUI;

import Models.WestminsterSkinConsultationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {
    static JFrame mainFrame = null;
    JPanel leftPanel, rightPanel;

    JTextField userNameField;
    JPasswordField passwordField;
    JButton loginButton;
    JButton cancelButton;

    public LoginFrame() {
        mainFrame = new JFrame();
        mainFrame.setSize(850, 500);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Login");
        mainFrame.setResizable(false);


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(850, 500));
        mainPanel.setBackground(Color.WHITE);

        leftPanel = new JPanel(new GridLayout(1, 1));
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new Dimension(550, 500));
        ImageIcon image = new ImageIcon("src/GUI/Assets/loginBG.png");
        Image img = image.getImage();
        Image newImg = img.getScaledInstance(550, 500, Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        JLabel label = new JLabel(newImc);
        leftPanel.add(label);


        rightPanel = new JPanel(new FlowLayout());
        rightPanel.setPreferredSize(new Dimension(300, 500));
        rightPanel.setBackground(Color.WHITE);

        JLabel loginLabel = new JLabel("Welcome!");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loginLabel.setForeground(Color.decode("#A45CFF"));
        rightPanel.add(MainFrame.addSpace(300, 50));
        rightPanel.add(loginLabel);

        JLabel smallTextLabel1 = new JLabel("Sign in to Westminster");
        smallTextLabel1.setPreferredSize(new Dimension(300, 17));
        smallTextLabel1.setFont(new Font("Arial", Font.PLAIN, 16));
        smallTextLabel1.setForeground(Color.decode("#CAA0FF"));
        smallTextLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel smallTextLabel2 = new JLabel("skin Consultation Manager");
        smallTextLabel2.setPreferredSize(new Dimension(300, 17));
        smallTextLabel2.setFont(new Font("Arial", Font.PLAIN, 16));
        smallTextLabel2.setForeground(Color.decode("#CAA0FF"));
        smallTextLabel2.setHorizontalAlignment(SwingConstants.CENTER);

        // Text field
        JLabel userNameLabel = new JLabel("Username");
        userNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        userNameLabel.setForeground(Color.decode("#CAA0FF"));
        userNameLabel.setPreferredSize(new Dimension(300, 17));
        userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        userNameField = new JTextField("  Username");
        userNameField.setPreferredSize(new Dimension(250, 40));
        userNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        userNameField.setForeground(Color.decode("#CAA0FF"));
        userNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#CAA0FF")));
        userNameField.setOpaque(false);
        userNameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userNameField.getText().equals("  Username")) {
                    userNameField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (userNameField.getText().equals("")) {
                    userNameField.setText("  Username");
                }
            }
        });

        passwordField = new JPasswordField("  Password");
        passwordField.setPreferredSize(new Dimension(250, 40));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setForeground(Color.decode("#CAA0FF"));
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#CAA0FF")));
        passwordField.setOpaque(false);
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (passwordField.getText().equals("  Password")) {
                    passwordField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordField.getText().equals("")) {
                    passwordField.setText("  Password");
                }
            }
        });

        JTextField temp = new JTextField();
        temp.setPreferredSize(new Dimension(250, 1));
        temp.setBorder(null);
        temp.setOpaque(false);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(150, 40));
        loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.decode("#A45CFF"), 2));
        loginButton.setBackground(Color.decode("#A45CFF"));
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setOpaque(true);
        loginButton.addActionListener(this);
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setOpaque(false);
                loginButton.setForeground(Color.decode("#A45CFF"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setOpaque(true);
                loginButton.setBackground(Color.decode("#A45CFF"));
                loginButton.setForeground(new Color(255, 255, 255));
            }
        });


        // Cancel Login
        JPanel cancelBtnContainer = new JPanel(new FlowLayout());
        cancelBtnContainer.setOpaque(false);
        JLabel cancelDescription = new JLabel("Close the application?");
        cancelDescription.setFont(new Font("Arial", Font.PLAIN, 16));
        cancelDescription.setForeground(new Color(164, 92, 255));

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelButton.setOpaque(false);
        cancelButton.setBorder(null);
        cancelButton.setForeground(new Color(164, 92, 255));
        cancelButton.addActionListener(this);
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cancelButton.setForeground(new Color(51, 0, 115));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cancelButton.setForeground(new Color(164, 92, 255));
            }
        });
        cancelBtnContainer.add(cancelDescription);
        cancelBtnContainer.add(cancelButton);


        rightPanel.add(MainFrame.addSpace(300, 10));
        rightPanel.add(smallTextLabel1);
        rightPanel.add(smallTextLabel2);
        rightPanel.add(MainFrame.addSpace(300, 30));
        rightPanel.add(temp);
        rightPanel.add(userNameField);
        rightPanel.add(MainFrame.addSpace(300, 10));
        rightPanel.add(passwordField);
        rightPanel.add(MainFrame.addSpace(300, 20));
        rightPanel.add(loginButton);
        rightPanel.add(MainFrame.addSpace(300, 10));
        rightPanel.add(cancelBtnContainer);


        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (userNameField.getText().equals("  Username") || passwordField.getText().equals("  Password")) {
                JOptionPane.showMessageDialog(null, "Please enter your username and password");
            } else {
                String username = userNameField.getText();
                String password = passwordField.getText();
                if (username.equals("admin") && password.equals("admin")) {
                    WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
                    manager.runGUI();
                    mainFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            }
        } else if (e.getSource() == cancelButton) {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the application?", "Close Application", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}
