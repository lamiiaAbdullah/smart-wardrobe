package com.smartwardrobe;

import javax.swing.SwingUtilities;

public class SmartWardrobeApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}

