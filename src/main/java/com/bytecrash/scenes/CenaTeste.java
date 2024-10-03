package com.bytecrash.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bytecrash.entities.Player;

public class CenaTeste {
    private Player player;

    public CenaTeste() {
        player = new Player();
    }

    public void update(float deltaTime) {
        player.update(deltaTime);
    }

    public void render(SpriteBatch batch) {
        player.render(batch);
    }

    public void dispose() {
        player.dispose();
    }
}
