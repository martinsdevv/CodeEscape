package com.bytecrash.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Player {

    private Animation<TextureRegion> walkAnimation;

    private Texture walkSheet;
    private TextureRegion idleFrame;

    private Vector2 position;

    private float stateTime;
    private float width, height;
    private float speed;

    private boolean isMoving;

    public Player() {

        // Encontra o SpriteSheet e o "corta" em 4 partes iguais
        walkSheet = new Texture(Gdx.files.internal("assets/player/player-uncut.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / 4, walkSheet.getHeight());

        // Anima o SpriteSheet (coloca um atrás do outro na matriz)
        Array<TextureRegion> walkFrames = new Array<TextureRegion>();
        for (int i = 0; i < 4; i++) {
            walkFrames.add(tmp[0][i]);
        }

        walkAnimation = new Animation<TextureRegion>(0.1f, walkFrames, Animation.PlayMode.LOOP);
        idleFrame = tmp[0][0];

        // Seta a posição inicial, tamanho do player e sua velocidade

        this.position = new Vector2(100, 100);
        this.width = 72;
        this.height = 72;
        this.speed = 200;

        /* 
         * 
         * stateTime: acumula o tempo passado desde o início da animação
         * deltaTime: guarda o tempo em segundos que se passou desde o ultimo frame
         * 
         * resumindo: deltaTime controla o t/s do jogo (definido pelo próprio GDX) e stateTime controla
         * a animação para sincronizar com o deltaTime.
         * 
        */
        stateTime = 0f;
        isMoving = false;
    }

    // renderiza o frame atual da animação caso o player esteja andando
    public void render(SpriteBatch batch) {
        TextureRegion currentFrame = isMoving ? walkAnimation.getKeyFrame(stateTime, true) : idleFrame;
        batch.draw(currentFrame, position.x, position.y, width, height);
    }

    // roubei isso aqui da Unity, é tipo um "loop" que atualiza o estado de movimento a todo frame
    public void update(float deltaTime) {
        float moveX = 0, moveY = 0;
        isMoving = false;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            moveY += speed * deltaTime;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            moveY -= speed * deltaTime;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            moveX -= speed * deltaTime;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            moveX += speed * deltaTime;
            isMoving = true;
        }

        if (isMoving) {
            stateTime += deltaTime;
        } else {
            stateTime = 0f;
        }

        move(moveX, moveY);
    }

    private void move(float deltaX, float deltaY) {
        position.add(deltaX, deltaY);
    }

    // libera os recursos pra não ficar consumindo a memória
    public void dispose() {
        walkSheet.dispose();
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getSpeed() {
        return speed;
    }
}
