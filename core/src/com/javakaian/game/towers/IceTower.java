package com.javakaian.game.towers;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.map.GameConstants;
import com.javakaian.game.resources.MyAtlas;

public class IceTower extends BaseTower {

	public IceTower(float x, float y, float width, float height, List<Enemy> enemyList) {
		super(x, y, width, height, enemyList);
		damage = GameConstants.TOWER_DAMAGE_ICE;
		ice = true;
		sprite = MyAtlas.ICE_TOWER;
		spriteSelected = MyAtlas.ICE_TOWER;
	}

	@Override
	public void render(ShapeRenderer sr) {
		super.render(sr);
	}

	@Override
	public void render(SpriteBatch sb) {
		super.render(sb);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
	}

}
