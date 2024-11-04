package com.bytecrash.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

public class TileMap {
    private int[][] layout;
    private Texture floorTexture;
    private Texture wallTexture;
    private int tileSize;
    private int offsetX;
    private int offsetY;
    private int scaledTileSize;

    public TileMap(int[][] layout, Texture floorTexture, Texture wallTexture) {
        this.layout = layout;
        this.floorTexture = floorTexture;
        this.wallTexture = wallTexture;
        this.tileSize = 32; // Tamanho real de cada tile em pixels

        // Calcula escala e offsets uma vez ao criar o TileMap
        calculateScaleAndOffsets();
    }

    private void calculateScaleAndOffsets() {
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        // Calcula a escala para que o layout preencha a tela
        float scaleX = (float) screenWidth / (layout[0].length * tileSize);
        float scaleY = (float) screenHeight / (layout.length * tileSize);
        float scale = Math.max(scaleX, scaleY); // Usa a maior escala para preencher a tela

        scaledTileSize = (int) (tileSize * scale);

        // Calcula o deslocamento para centralizar o layout
        offsetX = (screenWidth - layout[0].length * scaledTileSize) / 2;
        offsetY = (screenHeight - layout.length * scaledTileSize) / 2;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public int getScaledTileSize() {
        return scaledTileSize;
    }

    public int[][] getLayout() {
        return layout;
    }

    public void render(SpriteBatch batch) {
        for (int row = 0; row < layout.length; row++) {
            for (int col = 0; col < layout[0].length; col++) {
                Texture texture = layout[row][col] == 0 ? floorTexture : wallTexture;

                // Desenha cada tile ajustado com o tamanho escalado e centralizado
                batch.draw(texture, offsetX + col * scaledTileSize, offsetY + row * scaledTileSize, scaledTileSize, scaledTileSize);
            }
        }
    }

    public boolean isWalkable(float x, float y) {
        // Converte a posição em pixels para a posição no layout, levando em conta o offset e a escala
        int tileX = (int) ((x - offsetX) / scaledTileSize);
        int tileY = (int) ((y - offsetY) / scaledTileSize);

        // Verifica se a posição está dentro dos limites do layout
        if (tileX < 0 || tileX >= layout[0].length || tileY < 0 || tileY >= layout.length) {
            return false; // Fora dos limites do layout
        }

        // Retorna true se o tile for "caminhável" (0 = chão, 1 = parede, 2 = saída)
        return layout[tileY][tileX] == 0 || layout[tileY][tileX] == 2;
    }

    public void dispose() {
        floorTexture.dispose();
        wallTexture.dispose();
    }
}
