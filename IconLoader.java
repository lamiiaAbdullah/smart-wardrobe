package com.smartwardrobe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class IconLoader {
    private static final String ICON_BASE_PATH = "/icons/";

    public static ImageIcon getLoginIcon() {
        return loadIcon("login.png", 64);
    }

    public static ImageIcon getLargeLoginIcon() {
        return loadIcon("login.png", 150);
    }

    public static ImageIcon getLargeMainIcon() {
        return loadIcon("main.png", 180);
    }

    public static ImageIcon getMainIcon() {
        return loadIcon("main.png", 64);
    }

    public static ImageIcon getViewIcon() {
        return loadIcon("view.png", 64);
    }

    public static ImageIcon getInsertIcon() {
        return loadIcon("insert.png", 64);
    }

    public static ImageIcon getUpdateIcon() {
        return loadIcon("update.png", 64);
    }

    public static ImageIcon getDeleteIcon() {
        return loadIcon("delete.png", 64);
    }

    private static ImageIcon loadIcon(String name, int size) {
        try (InputStream is = IconLoader.class.getResourceAsStream(ICON_BASE_PATH + name)) {
            if (is == null) {
                return new ImageIcon();
            }
            BufferedImage image = ImageIO.read(is);
            if (image == null) {
                return new ImageIcon();
            }
            if (size > 0) {
                Image scaled = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
                return new ImageIcon(scaled);
            }
            return new ImageIcon(image);
        } catch (IOException e) {
            return new ImageIcon();
        }
    }
}
