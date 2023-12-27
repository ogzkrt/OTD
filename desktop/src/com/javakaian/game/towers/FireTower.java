package com.javakaian.game.towers;

import com.javakaian.game.entity.Bullet;
import com.javakaian.game.entity.Bullet.EnumBulletType;
import com.javakaian.game.entity.Enemy;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.util.GameConstants;

import java.util.List;

public class FireTower extends BaseTower {

    public FireTower(float x, float y, List<Enemy> enemyList) {
        super(x, y, enemyList);
        type = TowerType.FIRE;
        damage = GameConstants.TOWER_DAMAGE_FIRE;
        sprite = MyAtlas.FIRE_TOWER;
        spriteSelected = MyAtlas.FIRE_TOWER;

    }

    @Override
    public void projectileShoot() {

        bulletList.add(new Bullet(center.x, center.y, target, damage, EnumBulletType.FIRE_BULLET));
    }

}
