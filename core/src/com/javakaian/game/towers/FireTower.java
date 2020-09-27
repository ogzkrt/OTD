package com.javakaian.game.towers;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.entity.Bullet;
import com.javakaian.game.entity.Bullet.EnumBulletType;
import com.javakaian.game.entity.Enemy;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.util.GameConstants;

public class FireTower extends BaseTower {

	private long soundId;

	public FireTower(float x, float y, float width, float height, List<Enemy> enemyList) {
		super(x, y, width, height, enemyList);
		damage = GameConstants.TOWER_DAMAGE_FIRE;
		ice = false;
		sprite = MyAtlas.FIRE_PLANE;
		spriteSelected = MyAtlas.FIRE_PLANE;

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
	public void increaseDamage() {
		this.damage += 10;
		this.attackPrice += 1;
	}

	@Override
	public void projectileShoot() {

		bulletList.add(new Bullet(center.x, center.y, target, damage, EnumBulletType.FIRE_BULLET));
	}

	@Override
	public void contiuniousShoot() {

	}

	@Override
	public void onTargetFound() {
		// long id = MusicHandler.fireShoot.loop();
	}

	@Override
	public void onTargetLost() {
	}
}
