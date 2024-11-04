package com.bytecrash.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bytecrash.entities.Player;
import com.bytecrash.tiles.TileMap;

public abstract class CenaBase {
    protected Player player;
    protected TileMap tileMap;
    protected SceneManager sceneManager;
    protected int[][] layout;

    public CenaBase(SceneManager sceneManager, int[][] layout) {
        this.sceneManager = sceneManager;
        this.layout = layout;

        // Log para verificar se o sceneManager é null
        if (sceneManager == null) {
            System.out.println("CenaBase: sceneManager é null");
        } else {
            System.out.println("CenaBase: sceneManager inicializado corretamente");
        }

        // Inicializa o TileMap e o Player
        Texture floorTexture = new Texture("assets/pdf/cibertype floor 1.png");
        Texture wallTexture = new Texture("assets/pdf/wall preset.png");
        tileMap = new TileMap(layout, floorTexture, wallTexture);
        player = new Player(tileMap);
    }

    public void update(float deltaTime) {
        player.update(deltaTime);

        // Calcula o índice do tile onde o jogador está
        int playerTileX = (int) (player.getPosition().x / tileMap.getTileSize());
        int playerTileY = (int) (player.getPosition().y / tileMap.getTileSize());

        if (player.isOnExitTile()) {
            System.out.println("Player no tile de saída, chamando handleExit...");
            handleExit(playerTileX, playerTileY);
        }

        
    }

    protected boolean isAtExit(int tileX, int tileY) {
        return tileY >= 0 && tileY < layout.length && tileX >= 0 && tileX < layout[0].length && layout[tileY][tileX] == 2;
    }

    protected abstract void handleExit(int tileX, int tileY);

    public void render(SpriteBatch batch) {
        tileMap.render(batch);
        player.render(batch);
    }

    public void dispose() {
        tileMap.dispose();
        player.dispose();
    }
}
