package com.javakaian.game.towers;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.javakaian.game.map.GameConstants;
import com.javakaian.game.resources.MyAtlas;

public class ElectricTower extends BaseTower {

	public ElectricTower(float x, float y, float width, float height, List<Enemy> enemyList) {
		super(x, y, width, height, enemyList);
		sprite = MyAtlas.ELECTRIC_TOWER;
		spriteSelected = MyAtlas.ELECTRIC_TOWER;
		ice = false;
		damage = GameConstants.TOWER_DAMAGE_ELECTRIC;
		range = GameConstants.GRID_WIDTH * 15;

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
	public void shoot(float deltaTime) {
		target.shoot(this.damage);
	}
}
