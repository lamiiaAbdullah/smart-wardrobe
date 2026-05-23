package com.smartwardrobe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateFrame extends JFrame {
    private String tableName;
    private JPanel fieldsPanel;
    private JTextField idField;
    
    public UpdateFrame(String tableName) {
        this.tableName = tableName;
        setTitle("Update " + tableName.toUpperCase());
        setSize(550, 550);
        setLocationRelativeTo(null);
        setIcon();
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255));
        
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("Update Record - " + tableName.replace("_", " ").toUpperCase());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.BLACK);
        headerPanel.add(titleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(240, 248, 255));
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        JLabel idLabel = new JLabel("ID to update:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 13));
        topPanel.add(idLabel);
        idField = new JTextField(15);
        idField.setFont(new Font("Arial", Font.PLAIN, 13));
        idField.setPreferredSize(new Dimension(150, 30));
        topPanel.add(idField);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        
        fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 15, 12, 15);
        gbc.anchor = GridBagConstraints.WEST;
        
        JScrollPane scrollPane = new JScrollPane(fieldsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        JButton updateButton = new JButton("Update Record");
        updateButton.setFont(new Font("Arial", Font.BOLD, 16));
        updateButton.setPreferredSize(new Dimension(200, 40));
        updateButton.setBackground(new Color(70, 130, 180));
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);
        updateButton.setBorderPainted(false);
        updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateButton.addActionListener(new UpdateAction());
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.add(updateButton);
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
    
    private class UpdateAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Connection conn = DatabaseConnection.getConnection();
                String sql = "";
                PreparedStatement pstmt = null;
                String idColumn = getPrimaryKeyColumn();
                
                switch (tableName) {
                    case "users":
                        sql = "UPDATE users SET username = ?, password = ? WHERE " + idColumn + " = ?";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, getFieldValue(0));
                        pstmt.setString(2, getFieldValue(1));
                        pstmt.setInt(3, Integer.parseInt(idField.getText()));
                        break;
                    case "clothing_items":
                        sql = "UPDATE clothing_items SET name = ?, category = ?, color = ?, size = ?, last_worn_date = ?, weather_type = ?, is_clean = ? WHERE " + idColumn + " = ?";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, getFieldValue(0));
                        pstmt.setString(2, getFieldValue(1));
                        pstmt.setString(3, getFieldValue(2));
                        pstmt.setString(4, getFieldValue(3));
                        pstmt.setString(5, getFieldValue(4).isEmpty() ? null : getFieldValue(4));
                        pstmt.setString(6, getFieldValue(5).isEmpty() ? null : getFieldValue(5));
                        pstmt.setInt(7, Integer.parseInt(getFieldValue(6)));
                        pstmt.setInt(8, Integer.parseInt(idField.getText()));
                        break;
                    case "outfits":
                        sql = "UPDATE outfits SET name = ?, weather_type = ? WHERE " + idColumn + " = ?";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, getFieldValue(0));
                        pstmt.setString(2, getFieldValue(1).isEmpty() ? null : getFieldValue(1));
                        pstmt.setInt(3, Integer.parseInt(idField.getText()));
                        break;
                    case "outfit_items":
                        sql = "UPDATE outfit_items SET outfit_id = ?, item_id = ? WHERE outfit_id = ? AND item_id = ?";
                        pstmt = conn.prepareStatement(sql);
                        String oldOutfitId = idField.getText().split(",")[0].trim();
                        String oldItemId = idField.getText().split(",")[1].trim();
                        pstmt.setInt(1, Integer.parseInt(getFieldValue(0)));
                        pstmt.setInt(2, Integer.parseInt(getFieldValue(1)));
                        pstmt.setInt(3, Integer.parseInt(oldOutfitId));
                        pstmt.setInt(4, Integer.parseInt(oldItemId));
                        break;
                    case "weather":
                        sql = "UPDATE weather SET date = ?, temperature = ?, weather_condition = ?, humidity = ? WHERE " + idColumn + " = ?";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, getFieldValue(0));
                        pstmt.setDouble(2, Double.parseDouble(getFieldValue(1)));
                        pstmt.setString(3, getFieldValue(2));
                        pstmt.setDouble(4, Double.parseDouble(getFieldValue(3)));
                        pstmt.setInt(5, Integer.parseInt(idField.getText()));
                        break;
                }
                
                if (pstmt != null) {
                    int rows = pstmt.executeUpdate();
                    pstmt.close();
                    if (rows > 0) {
                        JOptionPane.showMessageDialog(UpdateFrame.this, "Record updated successfully!", 
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(UpdateFrame.this, "No record found with that ID!", 
                            "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(UpdateFrame.this, "Error: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
        
        private String getPrimaryKeyColumn() {
            switch (tableName) {
                case "users": return "user_id";
                case "clothing_items": return "item_id";
                case "outfits": return "outfit_id";
                case "weather": return "weather_id";
                default: return "id";
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
        setIconImage(IconLoader.getUpdateIcon().getImage());
    }
}
