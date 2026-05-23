package com.smartwardrobe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    
    public LoginFrame() {
        setTitle("Smart Wardrobe - Login");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setIcon();
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255));
        
        JPanel iconPanel = new JPanel();
        iconPanel.setBackground(new Color(240, 248, 255));
        iconPanel.setPreferredSize(new Dimension(500, 200));
        
        ImageIcon largeIcon = IconLoader.getLargeLoginIcon();
        JLabel iconLabel = new JLabel(largeIcon);
        iconLabel.setHorizontalAlignment(JLabel.CENTER);
        iconPanel.add(iconLabel);
        
        mainPanel.add(iconPanel, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        
        JLabel titleLabel = new JLabel("Smart Wardrobe");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(70, 130, 180));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(titleLabel, gbc);
        
        JLabel subtitleLabel = new JLabel("Login to your account");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(100, 100, 100));
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 20, 30, 20);
        formPanel.add(subtitleLabel, gbc);
        
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 2;
        gbc.gridx = 0;
        formPanel.add(usernameLabel, gbc);
        
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setPreferredSize(new Dimension(250, 35));
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 3;
        gbc.gridx = 0;
        formPanel.add(passwordLabel, gbc);
        
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setPreferredSize(new Dimension(250, 35));
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);
        
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setPreferredSize(new Dimension(200, 45));
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.BLACK);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (authenticate()) {
                    dispose();
                    new MainFrame().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, 
                        "Invalid username or password!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 20, 20, 20);
        formPanel.add(loginButton, gbc);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private boolean authenticate() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter username and password!", 
                "Error", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(this, 
                    "Cannot connect to database!\nPlease make sure MySQL is running in XAMPP.", 
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            
            boolean result = rs.next();
            rs.close();
            pstmt.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Database error: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private void setIcon() {
        setIconImage(IconLoader.getLoginIcon().getImage());
    }
}
