package com.javakaian.game.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.entity.Bullet;
import com.javakaian.game.entity.Enemy;
import com.javakaian.game.entity.GameObject;
import com.javakaian.game.util.GameConstants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public abstract class BaseTower extends GameObject {

    protected List<Enemy> enemyList;
    protected List<Bullet> bulletList;

    protected Enemy target;
    protected float range;
    protected float rotation = 0;
    protected float damage;

    protected float speed = 2f; // times per second.
    protected float speedCounter = 0;

    protected int rangePrice;
    protected int attackPrice;
    protected int speedPrice;

    protected TowerType type;

    protected HashMap<Enemy, Float> enemyMap;

    public BaseTower(float x, float y, List<Enemy> enemyList) {
        super(x, y, GameConstants.TOWER_SIZE, GameConstants.TOWER_SIZE);
        this.enemyList = enemyList;
        this.range = GameConstants.TOWER_RANGE;

        attackPrice = GameConstants.TOWER_ATTACK_PRICE;
        rangePrice = GameConstants.TOWER_RANGE_PRICE;
        speedPrice = GameConstants.TOWER_SPEED_PRICE;

        bulletList = new ArrayList<>();
    }

    @Override
    public void render(ShapeRenderer sr) {

        sr.setColor(Color.CYAN);
        if (isSelected) {
            sr.circle(center.x, center.y, range);
        }
        for (Bullet bullet : bulletList) {
            bullet.render(sr);
        }

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        updateTargetMap();
        for (Bullet bullet : bulletList) {
            bullet.update(deltaTime);
        }
        if (target == null) {
            findTarget();
            return;
        }
        if (target.isAlive()) {
            calculateRotation();
            invokeShootFunctions(deltaTime);
            removeBullets();
        } else {
            target = null;
        }

    }

    public void render(SpriteBatch sb) {

        if (isSelected) {

            sb.draw(spriteSelected, position.x, position.y, size.x / 2, size.y / 2, size.x, size.y, 1, 1, rotation);
        } else {

            sb.draw(sprite, position.x, position.y, size.x / 2, size.y / 2, size.x, size.y, 1, 1, rotation);
        }

        for (Bullet bullet : bulletList) {
            bullet.render(sb);
        }

    }

    /**
     * Calculates the rotation of the tower according to the target .
     */
    private void calculateRotation() {

        Vector2 temp = new Vector2(target.center).sub(center);
        rotation = temp.angle() + 90f;
    }

    /**
     * Override this method if your tower shoots periodically. This method will be
     * called according to attack speed of a tower.
     */
    public void projectileShoot() {

    }

    /**
     * Override this method if your tower is going to shoot continuously. This
     * method will be called per update.
     */
    public void continuousShoot() {

    }

    private void invokeShootFunctions(float deltaTime) {

        continuousShoot();
        speedCounter += deltaTime;
        if (speedCounter >= 1.0f / speed) {
            speedCounter = 0;
            projectileShoot();
        }
    }

    /**
     * Bullets gets invisible after they hit their target.This functions iterates
     * through the bullet lists and removes the invisible ones.
     */
    private void removeBullets() {
        List<Bullet> tempList = new ArrayList<>();
        for (int i = 0; i < bulletList.size(); i++) {
            Bullet bullet = bulletList.get(i);
            if (!bullet.isVisible()) {
                tempList.add(bullet);
            }
        }
        bulletList.removeAll(tempList);

    }

    /**
     * Finds all the enemies within the tower range and puts them in hashmap called
     * enemyMap. If the enemy is out of the range, removes it from the hashmap..
     */
    private void updateTargetMap() {
        enemyMap = new HashMap<>();
        for (Enemy enemy : enemyList) {
            float distance = center.dst(enemy.position);
            if (distance <= range && enemy.isAlive()) {
                enemyMap.put(enemy, distance);
            } else {
                enemyMap.remove(enemy);
            }
        }

    }

    /**
     * Iterates though enemies within the range and sets the closest one as a
     * target.
     */
    private void findTarget() {

        Collection<Float> values = enemyMap.values();
        if (!values.isEmpty()) {

            float lowest = Collections.min(values);

            for (Entry<Enemy, Float> entry : enemyMap.entrySet()) {
                if (entry.getValue().equals(lowest)) {
                    this.target = entry.getKey();
                }
            }

        }
    }

    public float getRange() {
        return range;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float attackSpeed) {
        this.speed = attackSpeed;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void increaseDamage() {
        this.damage *= 2;
        this.attackPrice *= 2;
    }

    public void increaseRange() {
        this.range *= 1.1;
        this.rangePrice *= 2;
    }

    public void increaseSpeed() {
        this.speed *= 1.1;
        this.speedPrice *= 2;
    }

    public int getRangePrice() {
        return rangePrice;
    }

    public int getAttackPrice() {
        return attackPrice;
    }

    public int getSpeedPrice() {
        return speedPrice;
    }

    public TowerType getType() {
        return type;
    }

    public enum TowerType {

        ELECTRIC, FIRE, ICE
    }
}
