package com.javakaian.game.towers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.map.GameConstants;
import com.javakaian.game.resources.MyAtlas;

public abstract class BaseTower extends GameObject {

	protected List<Enemy> enemyList;
	protected List<Bullet> bulletList;

	protected Enemy target;
	protected float range;
	protected float rotation = 0;
	protected float damage;

	protected float attackSpeed = 2f; // time times per second.
	protected float speedCounter = 0;

	protected boolean ice;

	protected int towerPrice;
	protected int rangePrice;
	protected int attackPrice;
	protected int speedPrice;

	protected boolean doubleSpeed = false;

	public BaseTower(float x, float y, float width, float height, List<Enemy> enemyList) {
		super(x, y, width, height);
		this.enemyList = enemyList;
		this.range = GameConstants.TOWER_RANGE;

		towerPrice = GameConstants.TOWER_PRICE;
		attackPrice = GameConstants.TOWER_ATTACK_PRICE;
		rangePrice = GameConstants.TOWER_RANGE_PRICE;
		speedPrice = GameConstants.TOWER_SPEED_PRICE;

		bulletList = new ArrayList<Bullet>();
	}

	@Override
	public void render(ShapeRenderer sr) {

		sr.setColor(Color.CYAN);
		if (isSelected) {
			sr.circle(center.x, center.y, range);
		}
		// sr.rect(position.x, position.y, size.x, size.y);
		for (Bullet bullet : bulletList) {
			bullet.render(sr);
		}

	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);

		if (target == null) {
			findTarget();
		} else if (isInRange() && isTargetAlive()) {

			calculateRotation();
			shoot(deltaTime);
			removeBullets();
		}
		for (Bullet bullet : bulletList) {
			bullet.update(deltaTime);
		}
	}

	public void render(SpriteBatch sb) {

		// origin is bottom left for the texture objects.
		if (isSelected) {

			sb.draw(spriteSelected, position.x, position.y, size.x / 2, size.y / 2, size.x, size.y, 1, 1, rotation);
		} else {

			sb.draw(sprite, position.x, position.y, size.x / 2, size.y / 2, size.x, size.y, 1, 1, rotation);
		}

		for (Bullet bullet : bulletList) {
			bullet.render(sb);
		}
	}

	private void calculateRotation() {
		Vector2 temp = new Vector2(position.x, position.y);
		float angle = temp.sub(target.center).angle() + 180;
		rotation = angle;
	}

	public void shoot(float deltaTime) {

		speedCounter += deltaTime;
		if (speedCounter >= 1 / attackSpeed) {
			speedCounter = 0;

			if (ice) {
				bulletList.add(new Bullet(center.x, center.y, target, damage, EnumBulletType.ICE_BULLET));
			} else {
				bulletList.add(new Bullet(center.x, center.y, target, damage, EnumBulletType.FIRE_BULLET));
			}
		}
	}

	private void shoot_area(float deltaTime) {

		speedCounter += deltaTime;
		if (speedCounter >= 1 / attackSpeed) {
			speedCounter = 0;

			if (ice) {
				Sound s = MyAtlas.shootSoundIce;
				s.play(1f);

				bulletList.add(new Bullet(position.x, position.y, target, damage, EnumBulletType.ICE_BULLET));
			} else {
				Sound s = MyAtlas.shootSound;
				s.play(0.01f);
				bulletList.add(new Bullet(position.x, position.y, target, damage, EnumBulletType.FIRE_BULLET));
			}
		}

	}

	private void removeBullets() {
		List<Bullet> tempList = new ArrayList<Bullet>();
		for (int i = 0; i < bulletList.size(); i++) {
			Bullet bullet = bulletList.get(i);
			if (!bullet.isVisible()) {
				tempList.add(bullet);
			}

		}
		bulletList.removeAll(tempList);

	}

	private boolean isTargetAlive() {
		if (!target.isAlive()) {
			target = null;
			return false;
		}
		return true;
	}

	private boolean isInRange() {
		float distance = position.dst(target.position);
		if (distance > range) {
			target = null;
		}
		return distance <= range;

	}

	private void findTarget() {
		HashMap<Enemy, Float> enemyMap = new HashMap<>();
		for (Enemy enemy : enemyList) {
			float distance = position.dst(enemy.position);
			if (distance <= range && enemy.isAlive()) {
				enemyMap.put(enemy, distance);
			}
		}
		Collection<Float> values = enemyMap.values();
		if (!values.isEmpty()) {

			float loest = Collections.min(values);

			for (Entry<Enemy, Float> entry : enemyMap.entrySet()) {
				if (entry.getValue().equals(loest)) {
					target = entry.getKey();
				}
			}

		}
	}

	public float getRange() {
		return range;
	}

	public float getDamage() {
		return damage;
	}

	public float getSpeed() {
		return attackSpeed;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void doubleSpeedClicked() {
		doubleSpeed = true;
	}

	public void normalSpeedClicked() {
		doubleSpeed = false;
	}

	public void increaseDamage(float damage) {
		this.damage += damage;
		attackPrice += 1;
	}

	public void increaseRange(float range) {
		this.range += range;
		rangePrice += 1;
	}

	public void increaseSpeed() {
		this.attackSpeed *= 1.1;
		speedPrice += 1;
	}

	public int getTowerPrice() {
		return towerPrice;
	}

	public int getRangePrice() {
		return rangePrice;
	}

	public int getAttackPrice() {
		return attackPrice;
	}

	public int getSpeedPrice() {
		return speedPrice;
	}

}
