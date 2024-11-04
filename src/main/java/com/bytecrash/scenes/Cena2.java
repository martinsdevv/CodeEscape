package com.bytecrash.scenes;

public class Cena2 extends CenaBase {

    public Cena2(SceneManager sceneManager) {
        super(sceneManager, new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 1, 0, 2},
            {1, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1}
        });
    }

    @Override
    protected void handleExit(int tileX, int tileY) {
        // Log para verificar o estado de sceneManager antes de mudar de cena
        System.out.println("handleExit em Cena1: sceneManager é null? " + (sceneManager == null));

        // Se sceneManager não for nulo, muda para a próxima cena
        if (sceneManager != null) {
            sceneManager.changeScene(new Cena3(sceneManager));
        } else {
            System.err.println("Erro: sceneManager é null ao tentar mudar de cena em Cena1.");
        }
    }

}
