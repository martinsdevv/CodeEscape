package com.bytecrash.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bytecrash.scenes.CenaBase;
import com.bytecrash.scenes.SceneManager;
import java.util.Random;

public class GameScreen implements ApplicationListener {

    private SpriteBatch batch;
    private CenaBase currentScene;
    private SceneManager sceneManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        sceneManager = new SceneManager();
    }

    /*private CenaBase getRandomScene() {
        Random random = new Random();
        int sceneNumber = random.nextInt(2); // Altere conforme o número de cenas
        switch (sceneNumber) {
            case 0:
                return new Cena1();
            default:
                return new Cena1();
        }
    }*/

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        sceneManager.getCurrentScene().update(Gdx.graphics.getDeltaTime());
        sceneManager.getCurrentScene().render(batch);

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
        currentScene.dispose();
    }
}
