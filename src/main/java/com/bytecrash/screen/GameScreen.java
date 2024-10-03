package com.bytecrash.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bytecrash.scenes.CenaTeste;

public class GameScreen implements ApplicationListener {

    private SpriteBatch batch;
    private CenaTeste cenaTeste;

    @Override
    public void create() {
        batch = new SpriteBatch();
        cenaTeste = new CenaTeste();  // Inicializa a primeira cena
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
    }

    // renderiza a cenaTeste
    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        cenaTeste.update(Gdx.graphics.getDeltaTime());
        cenaTeste.render(batch);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        return;
    }

    @Override
    public void pause() {
        return;
    }

    @Override
    public void resume() {
        return;
    }

    // libera os recursos da memoria
    @Override
    public void dispose() {
        batch.dispose();
        cenaTeste.dispose();
    }
}
