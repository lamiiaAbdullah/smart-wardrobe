package com.smartwardrobe;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    
    public MainFrame() {
        setTitle("Smart Wardrobe - Main Interface");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIcon();
        
        setLayout(new BorderLayout());
        
        add(new WardrobeGraphicsPanel(), BorderLayout.CENTER);
        
        JMenuBar menuBar = new JMenuBar();
        
        JMenu viewMenu = new JMenu("View");
        JMenuItem viewUsers = new JMenuItem("View Users");
        JMenuItem viewClothing = new JMenuItem("View Clothing Items");
        JMenuItem viewOutfits = new JMenuItem("View Outfits");
        JMenuItem viewOutfitItems = new JMenuItem("View Outfit Items");
        JMenuItem viewWeather = new JMenuItem("View Weather");
        
        viewUsers.addActionListener(e -> new ViewFrame("users").setVisible(true));
        viewClothing.addActionListener(e -> new ViewFrame("clothing_items").setVisible(true));
        viewOutfits.addActionListener(e -> new ViewFrame("outfits").setVisible(true));
        viewOutfitItems.addActionListener(e -> new ViewFrame("outfit_items").setVisible(true));
        viewWeather.addActionListener(e -> new ViewFrame("weather").setVisible(true));
        
        viewMenu.add(viewUsers);
        viewMenu.add(viewClothing);
        viewMenu.add(viewOutfits);
        viewMenu.add(viewOutfitItems);
        viewMenu.add(viewWeather);
        
        JMenu insertMenu = new JMenu("Insert");
        JMenuItem insertUser = new JMenuItem("Insert User");
        JMenuItem insertClothing = new JMenuItem("Insert Clothing Item");
        JMenuItem insertOutfit = new JMenuItem("Insert Outfit");
        JMenuItem insertOutfitItem = new JMenuItem("Insert Outfit Item");
        JMenuItem insertWeather = new JMenuItem("Insert Weather");
        
        insertUser.addActionListener(e -> new InsertFrame("users").setVisible(true));
        insertClothing.addActionListener(e -> new InsertFrame("clothing_items").setVisible(true));
        insertOutfit.addActionListener(e -> new InsertFrame("outfits").setVisible(true));
        insertOutfitItem.addActionListener(e -> new InsertFrame("outfit_items").setVisible(true));
        insertWeather.addActionListener(e -> new InsertFrame("weather").setVisible(true));
        
        insertMenu.add(insertUser);
        insertMenu.add(insertClothing);
        insertMenu.add(insertOutfit);
        insertMenu.add(insertOutfitItem);
        insertMenu.add(insertWeather);
        
        JMenu updateMenu = new JMenu("Update");
        JMenuItem updateUser = new JMenuItem("Update User");
        JMenuItem updateClothing = new JMenuItem("Update Clothing Item");
        JMenuItem updateOutfit = new JMenuItem("Update Outfit");
        JMenuItem updateOutfitItem = new JMenuItem("Update Outfit Item");
        JMenuItem updateWeather = new JMenuItem("Update Weather");
        
        updateUser.addActionListener(e -> new UpdateFrame("users").setVisible(true));
        updateClothing.addActionListener(e -> new UpdateFrame("clothing_items").setVisible(true));
        updateOutfit.addActionListener(e -> new UpdateFrame("outfits").setVisible(true));
        updateOutfitItem.addActionListener(e -> new UpdateFrame("outfit_items").setVisible(true));
        updateWeather.addActionListener(e -> new UpdateFrame("weather").setVisible(true));
        
        updateMenu.add(updateUser);
        updateMenu.add(updateClothing);
        updateMenu.add(updateOutfit);
        updateMenu.add(updateOutfitItem);
        updateMenu.add(updateWeather);
        
        JMenu deleteMenu = new JMenu("Delete");
        JMenuItem deleteUser = new JMenuItem("Delete User");
        JMenuItem deleteClothing = new JMenuItem("Delete Clothing Item");
        JMenuItem deleteOutfit = new JMenuItem("Delete Outfit");
        JMenuItem deleteOutfitItem = new JMenuItem("Delete Outfit Item");
        JMenuItem deleteWeather = new JMenuItem("Delete Weather");
        
        deleteUser.addActionListener(e -> new DeleteFrame("users").setVisible(true));
        deleteClothing.addActionListener(e -> new DeleteFrame("clothing_items").setVisible(true));
        deleteOutfit.addActionListener(e -> new DeleteFrame("outfits").setVisible(true));
        deleteOutfitItem.addActionListener(e -> new DeleteFrame("outfit_items").setVisible(true));
        deleteWeather.addActionListener(e -> new DeleteFrame("weather").setVisible(true));
        
        deleteMenu.add(deleteUser);
        deleteMenu.add(deleteClothing);
        deleteMenu.add(deleteOutfit);
        deleteMenu.add(deleteOutfitItem);
        deleteMenu.add(deleteWeather);
        
        menuBar.add(viewMenu);
        menuBar.add(insertMenu);
        menuBar.add(updateMenu);
        menuBar.add(deleteMenu);
        
        setJMenuBar(menuBar);
    }
    
    private void setIcon() {
        setIconImage(IconLoader.getMainIcon().getImage());
    }
}

