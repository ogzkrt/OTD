package com.javakaian.game.towers;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.map.GameConstants;
import com.javakaian.game.resources.MyAtlas;

public class FireTower extends BaseTower {

	public FireTower(float x, float y, float width, float height, List<Enemy> enemyList) {
		super(x, y, width, height, enemyList);
		damage = GameConstants.TOWER_DAMAGE_FIRE;
		ice = false;
		sprite = MyAtlas.FIRE_PLANE;
		spriteSelected = MyAtlas.FIRE_PLANE;

	}

	@Override
	public void render(ShapeRenderer sr) {
		// TODO Auto-generated method stub
		super.render(sr);
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		super.render(sb);

	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
	}

}
