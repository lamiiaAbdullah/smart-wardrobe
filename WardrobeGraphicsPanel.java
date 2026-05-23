package com.smartwardrobe;

import javax.swing.*;
import java.awt.*;

public class WardrobeGraphicsPanel extends JPanel {

    private final Image mainImage;

    public WardrobeGraphicsPanel() {
        ImageIcon icon = IconLoader.getLargeMainIcon();
        mainImage = icon != null ? icon.getImage() : null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        int width = getWidth();
        int height = getHeight();
        int padding = 50;

        GradientPaint gradient = new GradientPaint(0, 0, new Color(240, 248, 255), 0, height, new Color(200, 230, 255));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);

        int cardWidth = width - padding * 2;
        int cardHeight = height - padding * 2;
        int cardX = padding;
        int cardY = padding;

        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(cardX, cardY, cardWidth, cardHeight, 40, 40);

        g2d.setColor(new Color(70, 130, 180));
        g2d.setStroke(new BasicStroke(4f));
        g2d.drawRoundRect(cardX, cardY, cardWidth, cardHeight, 40, 40);

        if (mainImage != null) {
            int imgSize = 140;
            int imgX = cardX + (cardWidth - imgSize) / 2;
            int imgY = cardY + 40;
            g2d.drawImage(mainImage, imgX, imgY, imgSize, imgSize, null);
        }

        g2d.setColor(new Color(30, 30, 30));
        g2d.setFont(new Font("Segoe UI", Font.BOLD, 32));
        String title = "Smart Wardrobe System";
        drawCenteredString(g2d, title, width, cardY + (int) (cardHeight * 0.48));

        g2d.setColor(new Color(100, 100, 100));
        g2d.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        drawCenteredString(g2d, "Organize, Coordinate & Suggest Outfits", width, cardY + (int) (cardHeight * 0.56));

        g2d.setColor(new Color(70, 130, 180));
        g2d.setFont(new Font("Segoe UI", Font.BOLD, 18));
        drawCenteredString(g2d, "Lamia Alshamrani", width, cardY + (int) (cardHeight * 0.68));
        drawCenteredString(g2d, "Manar Altamimi", width, cardY + (int) (cardHeight * 0.75));
        drawCenteredString(g2d, "Lubna Alamri", width, cardY + (int) (cardHeight * 0.82));
    }

    private void drawCenteredString(Graphics2D g2d, String text, int panelWidth, int y) {
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int x = (panelWidth - textWidth) / 2;
        g2d.drawString(text, x, y);
    }
}
