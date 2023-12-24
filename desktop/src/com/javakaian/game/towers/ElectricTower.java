package com.javakaian.game.towers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.javakaian.game.entity.Enemy;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.util.GameConstants;

import java.util.List;

public class ElectricTower extends BaseTower {

    public ElectricTower(float x, float y, List<Enemy> enemyList) {
        super(x, y, enemyList);
        type = TowerType.ELECTRIC;
        sprite = MyAtlas.ELECTRIC_TOWER;
        spriteSelected = MyAtlas.ELECTRIC_TOWER;
        damage = GameConstants.TOWER_DAMAGE_ELECTRIC;
        range = GameConstants.GRID_WIDTH * 3;

    }

    @Override
    public void render(ShapeRenderer sr) {
        super.render(sr);
        if (target != null) {
            sr.end();
            sr.begin(ShapeType.Filled);
            sr.triangle(center.x, center.y, target.center.x, target.center.y + target.size.x / 2, target.center.x,
                    target.center.y - target.size.x / 2);
            sr.end();
            sr.begin(ShapeType.Line);
        }
    }

    @Override
    public void continuousShoot() {
        target.shoot(damage);
    }

}
