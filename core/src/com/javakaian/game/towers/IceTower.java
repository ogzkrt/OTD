package com.javakaian.game.towers;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.entity.Bullet;
import com.javakaian.game.entity.Enemy;
import com.javakaian.game.entity.Bullet.EnumBulletType;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.util.GameConstants;

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

	@Override
	public void projectileShoot() {

		bulletList.add(new Bullet(center.x, center.y, target, damage, EnumBulletType.ICE_BULLET));
	}

	@Override
	public void contiuniousShoot() {

	}

}
