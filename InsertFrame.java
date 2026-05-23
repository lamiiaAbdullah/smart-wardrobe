package com.smartwardrobe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertFrame extends JFrame {
    private String tableName;
    private JPanel fieldsPanel;
    
    public InsertFrame(String tableName) {
        this.tableName = tableName;
        setTitle("Insert into " + tableName.toUpperCase());
        setSize(550, 500);
        setLocationRelativeTo(null);
        setIcon();
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255));
        
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("Insert New Record - " + tableName.replace("_", " ").toUpperCase());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 15, 12, 15);
        gbc.anchor = GridBagConstraints.WEST;
        
        JScrollPane scrollPane = new JScrollPane(fieldsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        JButton insertButton = new JButton("Insert Record");
        insertButton.setFont(new Font("Arial", Font.BOLD, 16));
        insertButton.setPreferredSize(new Dimension(200, 40));
        insertButton.setBackground(new Color(70, 130, 180));
        insertButton.setForeground(Color.BLACK);
        insertButton.setFocusPainted(false);
        insertButton.setBorderPainted(false);
        insertButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        insertButton.addActionListener(new InsertAction());
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.add(insertButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        createFields();
    }
    
    private void createFields() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 15, 12, 15);
        gbc.anchor = GridBagConstraints.WEST;
        
        switch (tableName) {
            case "users":
                addField("Username:", new JTextField(25), gbc);
                addField("Password:", new JPasswordField(25), gbc);
                break;
            case "clothing_items":
                addField("Name:", new JTextField(25), gbc);
                addField("Category:", new JTextField(25), gbc);
                addField("Color:", new JTextField(25), gbc);
                addField("Size:", new JTextField(25), gbc);
                addField("Last Worn Date (YYYY-MM-DD):", new JTextField(25), gbc);
                addField("Weather Type:", new JTextField(25), gbc);
                addField("Is Clean (1/0):", new JTextField(25), gbc);
                break;
            case "outfits":
                addField("Name:", new JTextField(25), gbc);
                addField("Weather Type:", new JTextField(25), gbc);
                break;
            case "outfit_items":
                addField("Outfit ID:", new JTextField(25), gbc);
                addField("Item ID:", new JTextField(25), gbc);
                break;
            case "weather":
                addField("Date (YYYY-MM-DD):", new JTextField(25), gbc);
                addField("Temperature:", new JTextField(25), gbc);
                addField("Condition:", new JTextField(25), gbc);
                addField("Humidity:", new JTextField(25), gbc);
                break;
        }
    }
    
    private void addField(String label, JComponent component, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = fieldsPanel.getComponentCount() / 2;
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Arial", Font.BOLD, 13));
        fieldsPanel.add(labelComponent, gbc);
        gbc.gridx = 1;
        if (component instanceof JTextField) {
            ((JTextField) component).setPreferredSize(new Dimension(250, 30));
            ((JTextField) component).setFont(new Font("Arial", Font.PLAIN, 13));
        } else if (component instanceof JPasswordField) {
            ((JPasswordField) component).setPreferredSize(new Dimension(250, 30));
            ((JPasswordField) component).setFont(new Font("Arial", Font.PLAIN, 13));
        }
        fieldsPanel.add(component, gbc);
    }
    
    private class InsertAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Connection conn = DatabaseConnection.getConnection();
                String sql = "";
                PreparedStatement pstmt = null;
                
                switch (tableName) {
                    case "users":
                        sql = "INSERT INTO users (username, password) VALUES (?, ?)";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, getFieldValue(0));
                        pstmt.setString(2, getFieldValue(1));
                        break;
                    case "clothing_items":
                        sql = "INSERT INTO clothing_items (name, category, color, size, last_worn_date, weather_type, is_clean) VALUES (?, ?, ?, ?, ?, ?, ?)";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, getFieldValue(0));
                        pstmt.setString(2, getFieldValue(1));
                        pstmt.setString(3, getFieldValue(2));
                        pstmt.setString(4, getFieldValue(3));
                        pstmt.setString(5, getFieldValue(4).isEmpty() ? null : getFieldValue(4));
                        pstmt.setString(6, getFieldValue(5).isEmpty() ? null : getFieldValue(5));
                        pstmt.setInt(7, Integer.parseInt(getFieldValue(6)));
                        break;
                    case "outfits":
                        sql = "INSERT INTO outfits (name, weather_type) VALUES (?, ?)";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, getFieldValue(0));
                        pstmt.setString(2, getFieldValue(1).isEmpty() ? null : getFieldValue(1));
                        break;
                    case "outfit_items":
                        sql = "INSERT INTO outfit_items (outfit_id, item_id) VALUES (?, ?)";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setInt(1, Integer.parseInt(getFieldValue(0)));
                        pstmt.setInt(2, Integer.parseInt(getFieldValue(1)));
                        break;
                    case "weather":
                        sql = "INSERT INTO weather (date, temperature, weather_condition, humidity) VALUES (?, ?, ?, ?)";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, getFieldValue(0));
                        pstmt.setDouble(2, Double.parseDouble(getFieldValue(1)));
                        pstmt.setString(3, getFieldValue(2));
                        pstmt.setDouble(4, Double.parseDouble(getFieldValue(3)));
                        break;
                }
                
                if (pstmt != null) {
                    pstmt.executeUpdate();
                    pstmt.close();
                    JOptionPane.showMessageDialog(InsertFrame.this, "Record inserted successfully!", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(InsertFrame.this, "Error: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
        
        private String getFieldValue(int index) {
            Component[] components = fieldsPanel.getComponents();
            int fieldIndex = index * 2 + 1;
            if (fieldIndex < components.length) {
                JComponent comp = (JComponent) components[fieldIndex];
                if (comp instanceof JTextField) {
                    return ((JTextField) comp).getText();
                } else if (comp instanceof JPasswordField) {
                    return new String(((JPasswordField) comp).getPassword());
                }
            }
            return "";
        }
    }
    
    private void setIcon() {
        setIconImage(IconLoader.getInsertIcon().getImage());
    }
}
