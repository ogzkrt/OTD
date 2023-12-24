package com.javakaian.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.map.MapMaker.Direction;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.util.GameConstants;

import java.util.LinkedList;

public class Enemy extends GameObject {

    private float remainingHealth;
    private final int bounty;
    private int speed;

    private float distanceToTile;
    private Direction currentDirection;

    private final LinkedList<Direction> directionList;
    private boolean alive = true;

    private float slowDownTime;
    private boolean slowed = false;

    private final HealthBar healthBar;

    public Enemy(float x, float y, float width, float height, float health, LinkedList<Direction> directionList,
                 int bounty, int speed) {
        super(x, y, width, height);
        this.speed = speed;
        this.directionList = new LinkedList<>(directionList);
        this.remainingHealth = health;
        this.bounty = bounty;
        this.sprite = MyAtlas.ENEMY;
        getNextDirection();

        healthBar = new HealthBar(x, y - height / 5, width, height / 5, remainingHealth);
    }

    @Override
    public void render(ShapeRenderer sr) {
        healthBar.render(sr);

    }

    public void render(SpriteBatch sb) {
        super.render(sb);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (currentDirection == null) {
            return;
        }
        if (distanceToTile < 0) {
            getNextDirection();
        }
        if (currentDirection != null) {

            float distance = speed * deltaTime;
            switch (currentDirection) {
                case UP:
                    position.y -= distance;
                    distanceToTile -= distance;
                    if (distanceToTile < 0) {
                        position.y -= distanceToTile;
                    }
                    break;
                case DOWN:
                    position.y += distance;
                    distanceToTile -= distance;
                    if (distanceToTile < 0) {
                        position.y += distanceToTile;
                    }
                    break;
                case LEFT:
                    position.x -= distance;
                    distanceToTile -= distance;
                    if (distanceToTile < 0) {
                        position.x -= distanceToTile;
                    }
                    break;
                case RIGHT:
                    position.x += distance;
                    distanceToTile -= distance;
                    if (distanceToTile < 0) {
                        position.x += distanceToTile;
                    }
                    break;

                default:
                    break;
            }

        }

        clearSlowEffects(deltaTime);

        healthBar.position.x = position.x;
        healthBar.position.y = position.y - size.y / 5;

    }

    private void getNextDirection() {
        currentDirection = directionList.pollFirst();
        if (currentDirection != null) {
            switch (currentDirection) {
                case UP:
                case DOWN:
                    distanceToTile = GameConstants.VERTICAL_MOVEMENT_UNIT;
                    break;
                case RIGHT:
                case LEFT:
                    distanceToTile = GameConstants.HORIZONTAL_MOVEMENT_UNIT;
                    break;
            }

        }
    }

    public void shoot(float damage) {

        this.remainingHealth -= damage;

        if (this.remainingHealth <= 0) {
            alive = false;
            visible = false;
        } else {

            healthBar.setRemaniningHealth(remainingHealth);

        }

    }

    private void clearSlowEffects(float deltaTime) {

        if (slowed) {
            slowDownTime += deltaTime;
            float slowDownDuration = 0.5f;
            if (slowDownTime >= slowDownDuration) {
                speed *= 2;
                sprite = MyAtlas.ENEMY;
                slowed = false;
            }
        }
    }

    public void slowDown() {

        if (!slowed) {
            slowDownTime = 0;
            speed /= 2;
            sprite = MyAtlas.ENEMY_SLOWED;
            slowed = true;
        } else {
            slowDownTime = 0;
        }

    }

    public boolean isAlive() {
        return alive;
    }

    public int getBounty() {
        return bounty;
    }

    public void speed2XClicked() {
        speed *= 2;
    }

    public void normalSpeedClicked() {
        speed /= 2;
    }

}