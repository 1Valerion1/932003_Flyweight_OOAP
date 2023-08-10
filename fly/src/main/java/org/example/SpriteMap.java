package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteMap {
    private BufferedImage image;
    public static SpriteMap grass;
    public static SpriteMap dirt;
    public static SpriteMap water;
    public static SpriteMap mushrooms;
    public static SpriteMap tree;


    public SpriteMap(BufferedImage image) {
        this.image = image;
    }
    public static void init() {
        grass = new SpriteMap(SpriteMap.loadImage("/grass.png"));
        dirt = new SpriteMap(SpriteMap.loadImage("/dirt.png"));
        water = new SpriteMap(SpriteMap.loadImage("/water.png"));
        mushrooms = new SpriteMap(SpriteMap.loadImage("/mushrooms.png"));
        tree = new SpriteMap(SpriteMap.loadImage("/tree.png"));
    }

    public static BufferedImage loadImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(SpriteMap.class.getResource(path));
        }
        catch (IOException iOException) {
            System.out.println("Error "+ iOException);
        }
        return img;
    }

    public void drawSprite(int x, int y, int w, int h, Graphics g) {
        g.drawImage(this.image, x, y, w, h, null);
    }

}