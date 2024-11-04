package com.bytecrash.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile {
    private Texture texture;
    private int x, y; // Posição no grid

    public Tile(Texture texture, int x, int y) {
        this.texture = texture;
        this.x = x;
        this.y = y;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x * texture.getWidth(), y * texture.getHeight());
    }

    public void dispose() {
        texture.dispose();
    }
}
