package org.example;

import java.awt.Graphics;

public class MapTile {
    private int x;
    private int y;
    private int width = 32;
    private int height = 32;
    private SpriteMap spriteMap;

 // in the usual way
    public MapTile(SpriteMap spriteMap, int x, int y) {
        this.x = x;
        this.y = y;
        this.spriteMap = spriteMap;
    }
    public void drawUsually(Graphics g) {
        this.spriteMap.drawSprite(this.x, this.y, this.width, this.height, g);
    }


    //fluweigth pattern
     public MapTile(SpriteMap spriteMap) {
        this.spriteMap = spriteMap;
    }
    public void drawPattern(Graphics g, int x, int y) {
        this.spriteMap.drawSprite(x, y, this.width, this.height, g);
    }


}

