package com.javakaian.game.towers;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.javakaian.game.entity.Enemy;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.util.GameConstants;

public class ElectricTower extends BaseTower {

	public ElectricTower(float x, float y, float width, float height, List<Enemy> enemyList) {
		super(x, y, width, height, enemyList);
		sprite = MyAtlas.ELECTRIC_TOWER;
		spriteSelected = MyAtlas.ELECTRIC_TOWER;
		ice = false;
		damage = GameConstants.TOWER_DAMAGE_ELECTRIC;
		range = GameConstants.GRID_WIDTH * 3;

		setType(TowerType.ELECTRIC);

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
	public void render(SpriteBatch sb) {
		super.render(sb);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);

	}

	@Override
	public void projectileShoot() {

	}

	@Override
	public void contiuniousShoot() {
		target.shoot(damage);
	}

	@Override
	public void increaseDamage() {
		this.damage += 1;
		this.attackPrice += 1;
	}

}
