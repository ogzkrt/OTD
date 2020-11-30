package com.javakaian.game.towers;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.javakaian.game.entity.Enemy;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.util.GameConstants;

public class ElectricTower extends BaseTower {

	public ElectricTower(float x, float y, List<Enemy> enemyList) {
		super(x, y, enemyList);
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
		this.damage += this.damage * 0.6f;
		this.attackPrice *= 2;
	}

	@Override
	public void increaseRange(float range) {
		// TODO Auto-generated method stub
		this.rangePrice *= 2;

	}

	@Override
	public void increaseSpeed() {
		// TODO Auto-generated method stub
		this.speedPrice *= 2;
	}

	@Override
	public void onTargetFound() {
	}

	@Override
	public void onTargetLost() {
	}
}
