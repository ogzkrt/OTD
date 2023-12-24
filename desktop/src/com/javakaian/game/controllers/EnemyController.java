package com.javakaian.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.entity.Bounty;
import com.javakaian.game.entity.Enemy;
import com.javakaian.game.level.Level;
import com.javakaian.game.map.Map;
import com.javakaian.game.map.MapMaker.Direction;
import com.javakaian.game.util.GameConstants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EnemyController {

    private final List<Enemy> enemyList;
    private float spawnTime = 0;
    private float spawnPeriod;
    private int count;

    private float health;
    private int enemyNumberLimit;
    private int bounty;
    private final LinkedList<Direction> directionList;

    private int nextWaveTimer = 0;

    private final Level level;

    private int enemySpeed = GameConstants.ENEMY_SPEED;

    private final List<Bounty> bountyList;

    public EnemyController(Level level) {

        this.level = level;
        Map map = level.getMap();
        this.directionList = map.getDirectionList();
        this.health = level.getEnemyHealth();
        this.enemyNumberLimit = level.getEnemyNumber();

        count = level.getEnemyNumber();
        enemyList = new ArrayList<>();
        bountyList = new ArrayList<>();

        this.bounty = GameConstants.ENEMY_BOUNTY;
        this.spawnPeriod = GameConstants.ENEMY_SPAWN_PERIOD;
    }

    public void render(SpriteBatch sb) {

        for (Enemy enemy : enemyList) {
            enemy.render(sb);
        }
        for (Bounty bounty : bountyList) {
            bounty.render(sb);
        }
    }

    public void render(ShapeRenderer sr) {
        for (Enemy enemy : enemyList) {
            enemy.render(sr);
        }
    }

    public void update(float deltaTime) {
        for (Enemy enemy : enemyList) {
            enemy.update(deltaTime);
        }
        for (Bounty bounty : bountyList) {
            bounty.update(deltaTime);
        }
        removeCoins();
        createEnemy();
        removeDeadEnemies();
        checkIfEnemyOfScreen();
    }

    public void createEnemy() {

        spawnTime += Gdx.graphics.getDeltaTime();
        if (spawnTime >= spawnPeriod) {
            spawnTime = 0;
            if (count < enemyNumberLimit) {
                Vector2 p = new Vector2(0, 0);
                enemyList.add(new Enemy(
                        p.x - GameConstants.GRID_WIDTH + (GameConstants.GRID_WIDTH / 2 - GameConstants.ENEMY_WIDTH / 2),
                        p.y + GameConstants.GRID_HEIGHT
                                + (GameConstants.GRID_HEIGHT / 2 - GameConstants.ENEMY_HEIGHT / 2),
                        GameConstants.ENEMY_WIDTH, GameConstants.ENEMY_HEIGHT, health, directionList, bounty,
                        enemySpeed));
                count++;
            } else {

                isWaveCompleted();
            }
        }

    }

    private void isWaveCompleted() {
        if (enemyList.isEmpty()) {
            // it means wave has been cleared or enemies passed the path.
            // next wave can be spawned.
            nextWaveTimer++;
            if (nextWaveTimer % 2 == 0)
                level.nextWaveCountDown((GameConstants.NEXT_WAVE_SPAWN_TIME / 2 - nextWaveTimer / 2));

            if (nextWaveTimer == GameConstants.NEXT_WAVE_SPAWN_TIME) {
                count = 0;
                nextWaveTimer = 0;
                this.health *= 2;
                this.enemyNumberLimit += 1;
                this.bounty += 7;
                level.newWaveCreated(this.enemyNumberLimit);
            }
        }

    }

    private void checkIfEnemyOfScreen() {

        List<Enemy> shouldRemoved = new ArrayList<>();
        for (Enemy e : enemyList) {
            if (e.position.x + e.size.x > GameConstants.SCREEN_WIDTH) {
                shouldRemoved.add(e);
                level.enemyPassedTheCheckPoint();
            }
        }
        enemyList.removeAll(shouldRemoved);
    }

    private void removeCoins() {
        List<Bounty> bounties = new ArrayList<>();
        for (Bounty b : bountyList) {
            if (b.isDisposable()) {
                bounties.add(b);
            }
        }
        bountyList.removeAll(bounties);
    }

    public void removeDeadEnemies() {

        for (int i = 0; i < enemyList.size(); i++) {
            Enemy e = enemyList.get(i);
            if (!e.isAlive()) {
                level.enemyKilled(e.getBounty());
                enemyList.remove(i);
                bountyList.add(
                        new Bounty(e.position.x, e.position.y, GameConstants.ENEMY_WIDTH, GameConstants.ENEMY_HEIGHT));
                i--;
            }
        }
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public void speed2xClicked() {
        enemySpeed = GameConstants.ENEMY_SPEED * 2;
        spawnPeriod = GameConstants.ENEMY_SPAWN_PERIOD / 2;
        for (Enemy enemy : enemyList) {
            enemy.speed2XClicked();

        }
    }

    public void normalSpeedClicked() {
        enemySpeed = GameConstants.ENEMY_SPEED;
        spawnPeriod = GameConstants.ENEMY_SPAWN_PERIOD;
        for (Enemy enemy : enemyList) {
            enemy.normalSpeedClicked();

        }
    }
}
