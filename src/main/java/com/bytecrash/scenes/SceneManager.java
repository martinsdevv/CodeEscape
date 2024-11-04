package com.bytecrash.scenes;

public class SceneManager {
    private CenaBase currentScene;

    public SceneManager() {
        // Passa `this` para o construtor de `Cena1` para inicializar `currentScene`
        currentScene = new Cena1(this);
    }

    public void changeScene(CenaBase newScene) {
        if (currentScene != null) {
            currentScene.dispose();
        }
        currentScene = newScene;
    }

    public CenaBase getCurrentScene() {
        return currentScene;
    }
}
