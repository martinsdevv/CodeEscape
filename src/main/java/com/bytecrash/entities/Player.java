package com.bytecrash.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Texture texture;
    private Vector2 position; // Usamos Vector2 para a posição (x, y)
    private float width, height;
    private float speed;

    public Player(String texturePath, float x, float y, float width, float height, float speed) {
        this.texture = new Texture(texturePath);
        this.position = new Vector2(x, y); // Inicializa a posição com Vector2
        this.width = width;
        this.height = height;
        this.speed = speed; // Define a velocidade de movimento
    }

    public void render(SpriteBatch batch) {
        // Desenha o sprite do player na tela
        batch.draw(texture, position.x, position.y, width, height);
    }

    public void update(float deltaTime) {
        // Lógica de movimentação: Captura as entradas e move o player
        float moveX = 0, moveY = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            moveY += speed * deltaTime; // Move para cima
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            moveY -= speed * deltaTime; // Move para baixo
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            moveX -= speed * deltaTime; // Move para a esquerda
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            moveX += speed * deltaTime; // Move para a direita
        }

        // Atualiza a posição do player
        move(moveX, moveY);
    }

    private void move(float deltaX, float deltaY) {
        position.add(deltaX, deltaY); // Atualiza a posição com base nos deltas
    }

    public void dispose() {
        texture.dispose();
    }

    // Getters e setters, se necessário
    public Vector2 getPosition() {
        return position;
    }

    public float getSpeed() {
        return speed;
    }
}
