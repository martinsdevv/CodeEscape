package com.bytecrash.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.bytecrash.tiles.TileMap;

public class Player {

    private Animation<TextureRegion> walkAnimation;
    private Texture walkSheet;
    private TextureRegion idleFrame;
    private Vector2 position;
    private float stateTime;
    private float width, height;
    private float speed;
    private boolean isMoving;
    private TileMap tileMap;

    public Player(TileMap tileMap) {
        this.tileMap = tileMap;

        walkSheet = new Texture(Gdx.files.internal("assets/player/player-uncut.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / 4, walkSheet.getHeight());

        Array<TextureRegion> walkFrames = new Array<>();
        for (int i = 0; i < 4; i++) {
            walkFrames.add(tmp[0][i]);
        }

        walkAnimation = new Animation<>(0.1f, walkFrames, Animation.PlayMode.LOOP);
        idleFrame = tmp[0][0];

        this.position = new Vector2(350, 350);
        this.width = 128;
        this.height = 128;
        this.speed = 200;
        stateTime = 0f;
        isMoving = false;

        System.out.println("Tile Size: " + tileMap.getTileSize());
    }

    public void render(SpriteBatch batch) {
        TextureRegion currentFrame = isMoving ? walkAnimation.getKeyFrame(stateTime, true) : idleFrame;
        batch.draw(currentFrame, position.x, position.y, width, height);
    }

    public void update(float deltaTime) {
        float moveX = 0, moveY = 0;
        isMoving = false;

        // Verifica o movimento nas direções e calcula o próximo movimento com base nas colisões
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (canMoveTo(position.x, position.y + speed * deltaTime)) {
                moveY += speed * deltaTime;
            } else {
                moveY = getMaxMove(position.x, position.y, 0, 1, deltaTime);
            }
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (canMoveTo(position.x, position.y - speed * deltaTime)) {
                moveY -= speed * deltaTime;
            } else {
                moveY = getMaxMove(position.x, position.y, 0, -1, deltaTime);
            }
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (canMoveTo(position.x - speed * deltaTime, position.y)) {
                moveX -= speed * deltaTime;
            } else {
                moveX = getMaxMove(position.x, position.y, -1, 0, deltaTime);
            }
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (canMoveTo(position.x + speed * deltaTime, position.y)) {
                moveX += speed * deltaTime;
            } else {
                moveX = getMaxMove(position.x, position.y, 1, 0, deltaTime);
            }
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

    private boolean canMoveTo(float x, float y) {
        return tileMap.isWalkable(x, y);
    }

    public boolean isOnExitTile() {
        int tileX = (int) ((position.x - tileMap.getOffsetX()) / tileMap.getScaledTileSize());
        int tileY = (int) ((position.y - tileMap.getOffsetY()) / tileMap.getScaledTileSize());
    
        int[][] layout = tileMap.getLayout();
        boolean isExit = tileX >= 0 && tileX < layout[0].length && tileY >= 0 && tileY < layout.length && layout[tileY][tileX] == 2;
    
        // Log para verificar a posição do jogador e se ele está em um tile de saída
        System.out.println("Player Position: (" + position.x + ", " + position.y + ")");
        System.out.println("Tile Position: (" + tileX + ", " + tileY + ")");
        System.out.println("Is on exit tile: " + isExit);
    
        return isExit;
    }
    

    private float getMaxMove(float currentX, float currentY, int dirX, int dirY, float deltaTime) {
        float maxMove = 0;
        float stepSize = speed * deltaTime * 0.1f;

        while (canMoveTo(currentX + maxMove * dirX, currentY + maxMove * dirY)) {
            maxMove += stepSize;
        }
        return maxMove - stepSize;
    }

    public void dispose() {
        walkSheet.dispose();
    }

    public Vector2 getPosition() {
        return position;
    }
}
