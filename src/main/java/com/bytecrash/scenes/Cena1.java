package com.bytecrash.scenes;

public class Cena1 extends CenaBase {

    public Cena1(SceneManager sceneManager) {
        super(sceneManager, new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 1},
            {2, 0, 1, 0, 0, 1, 0, 2},
            {1, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1}
        });
    }

    @Override
    protected void handleExit(int tileX, int tileY) {
        // Muda para Cena2 ao encontrar uma saída em Cena1
        if (sceneManager != null) {
            System.out.println("handleExit chamado em Cena1 - Transição para Cena2");
            sceneManager.changeScene(new Cena2(sceneManager));
        } else {
            System.err.println("Erro: sceneManager é null ao tentar mudar de cena em Cena1.");
        }
    }

}
