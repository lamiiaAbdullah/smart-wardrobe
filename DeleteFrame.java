package com.smartwardrobe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteFrame extends JFrame {
    private String tableName;
    private JTextField idField;
    
    public DeleteFrame(String tableName) {
        this.tableName = tableName;
        setTitle("Delete from " + tableName.toUpperCase());
        setSize(450, 250);
        setLocationRelativeTo(null);
        setIcon();
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255));
        
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(220, 20, 60));
        JLabel titleLabel = new JLabel("Delete Record - " + tableName.replace("_", " ").toUpperCase());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        
        String idLabel = getPrimaryKeyColumn();
        if (tableName.equals("outfit_items")) {
            idLabel = "Outfit ID, Item ID (comma separated)";
        }
        
        JLabel label = new JLabel("Enter " + idLabel + ":");
        label.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(label, gbc);
        
        idField = new JTextField(25);
        idField.setFont(new Font("Arial", Font.PLAIN, 14));
        idField.setPreferredSize(new Dimension(300, 35));
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(idField, gbc);
        
        JButton deleteButton = new JButton("Delete Record");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 16));
        deleteButton.setPreferredSize(new Dimension(200, 40));
        deleteButton.setBackground(new Color(220, 20, 60));
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(new DeleteAction());
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.add(deleteButton, gbc);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);
    }
    
    private String getPrimaryKeyColumn() {
        switch (tableName) {
            case "users": return "User ID";
            case "clothing_items": return "Item ID";
            case "outfits": return "Outfit ID";
            case "weather": return "Weather ID";
            default: return "ID";
        }
    }
    
    private class DeleteAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(DeleteFrame.this, 
                "Are you sure you want to delete this record?", 
                "Confirm Delete", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);
            
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            
            try {
                Connection conn = DatabaseConnection.getConnection();
                String sql = "";
                PreparedStatement pstmt = null;
                
                if (tableName.equals("outfit_items")) {
                    String[] ids = idField.getText().split(",");
                    sql = "DELETE FROM outfit_items WHERE outfit_id = ? AND item_id = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, Integer.parseInt(ids[0].trim()));
                    pstmt.setInt(2, Integer.parseInt(ids[1].trim()));
                } else {
                    String idColumn = getPrimaryKeyColumn().replace(" ", "_").toLowerCase();
                    sql = "DELETE FROM " + tableName + " WHERE " + idColumn + " = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, Integer.parseInt(idField.getText()));
                }
                
                int rows = pstmt.executeUpdate();
                pstmt.close();
                
                if (rows > 0) {
                    JOptionPane.showMessageDialog(DeleteFrame.this, "Record deleted successfully!", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(DeleteFrame.this, "No record found with that ID!", 
                        "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(DeleteFrame.this, "Error: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
    
    private void setIcon() {
        setIconImage(IconLoader.getDeleteIcon().getImage());
    }
}
