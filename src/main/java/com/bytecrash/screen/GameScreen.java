package com.bytecrash.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bytecrash.entities.Player;

public class GameScreen implements ApplicationListener {

    private SpriteBatch batch;
    private Player player;

    @Override
    public void create() {
        batch = new SpriteBatch();
        player = new Player("assets/player/player.png",
                100, 100, 64, 64, 200);
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        player.update(Gdx.graphics.getDeltaTime());

        player.render(batch);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Reagir ao redimensionamento da tela, se necessário
    }

    @Override
    public void pause() {
        // Ações quando o jogo é pausado
    }

    @Override
    public void resume() {
        // Ações quando o jogo é retomado
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
    }
}
