package com.javakaian.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.util.GameConstants;

public class Bullet extends GameObject {

    private final Enemy target;

    private final float damage;
    private final EnumBulletType bulletType;

    public Bullet(float x, float y, Enemy target, float damage, EnumBulletType bulletType) {
        super(x, y, GameConstants.BULLET_WIDTH, GameConstants.BULLET_HEIGHT);

        this.bulletType = bulletType;
        this.target = target;
        this.damage = damage;
        // ?????????? fix this..
        if (bulletType == EnumBulletType.ICE_BULLET) {
            sprite = MyAtlas.ICE_BULLET;
        } else if (bulletType == EnumBulletType.FIRE_BULLET) {
            sprite = MyAtlas.FIRE_BULLET;
        }

    }

    @Override
    public void render(ShapeRenderer sr) {
        // super.render(sr);
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        checkForRemoval();
        if (visible) {

            Vector2 targetCopy = new Vector2();
            targetCopy.set(target.center.x, target.center.y);

            Vector2 temp = targetCopy.sub(this.center).clamp(GameConstants.BULLET_SPEED, GameConstants.BULLET_SPEED)
                    .scl(deltaTime);
            this.position.add(temp);

        }

    }

    private void checkForRemoval() {
        float distance = target.center.dst(center);
        if (distance <= GameConstants.BULLET_HEIGHT) {
            visible = false;
            target.shoot(damage);
            if (bulletType == EnumBulletType.ICE_BULLET) {
                target.slowDown();
            }

        }

    }

    public boolean isVisible() {
        return visible;
    }

    public enum EnumBulletType {

        ICE_BULLET, FIRE_BULLET

    }

}
