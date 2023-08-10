package org.example;

import java.util.HashMap;


public class TileFactory {
    private static HashMap<SpriteMap, MapTile> tilesBySprite = new HashMap();

    public static MapTile getTile(SpriteMap spriteMap) {
        MapTile t = tilesBySprite.get(spriteMap);
        if (t == null) {
            t = new MapTile(spriteMap);
            tilesBySprite.put(spriteMap, t);
        }
        return t;
    }
}

