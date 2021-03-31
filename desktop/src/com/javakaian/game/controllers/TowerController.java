package com.javakaian.game.controllers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.entity.Enemy;
import com.javakaian.game.entity.Entity;
import com.javakaian.game.towers.BaseTower;
import com.javakaian.game.towers.BaseTower.TowerType;
import com.javakaian.game.towers.ElectricTower;
import com.javakaian.game.towers.FireTower;
import com.javakaian.game.towers.IceTower;
import com.javakaian.game.util.GameConstants;

public class TowerController implements Entity {

	private List<BaseTower> towerList;

	private BaseTower selectedTower;

	private boolean speedMode = false;

	public TowerController() {

		towerList = new ArrayList<BaseTower>();
	}

	@Override
	public void update(float deltaTime) {
		for (BaseTower tower : towerList) {
			tower.update(deltaTime);
		}
	}

	@Override
	public void render(ShapeRenderer sr) {

		for (BaseTower tower : towerList) {
			tower.render(sr);
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		for (BaseTower tower : towerList) {
			tower.render(sb);
		}
	}

	/**
	 * This fuction builds a tower and returns the cost value according to the tower
	 * type. if building tower is not possible then it returns zero as a cost value.
	 **/
	public int buildTower(float x, float y, List<Enemy> enemyList, TowerType type, int money) {
		int cost = 0;
		switch (type) {
		case FIRE:
			cost = GameConstants.TOWER_PRICE;
			if (money >= cost) {
				return buildFireTower(x, y, enemyList);
			}
			break;
		case ICE:
			cost = GameConstants.TOWER_PRICE;
			if (money >= cost) {
				return buildIceTower(x, y, enemyList);
			}
			break;
		case ELECTRIC:
			cost = GameConstants.ELECTRIC_TOWER_PRICE;
			if (money >= cost) {
				return buildElectricTower(x, y, enemyList);
			}
			break;
		}
		return 0;

	}

	private int buildFireTower(float x, float y, List<Enemy> enemyList) {

		FireTower ft = new FireTower(x, y, enemyList);
		if (speedMode) {
			ft.setSpeed(ft.getSpeed() * 2);
		}
		towerList.add(ft);
		return GameConstants.TOWER_PRICE;
	}

	private int buildIceTower(float x, float y, List<Enemy> enemyList) {

		IceTower it = new IceTower(x, y, enemyList);
		if (speedMode) {
			it.setSpeed(it.getSpeed() * 2);
		}
		towerList.add(new IceTower(x, y, enemyList));
		return GameConstants.TOWER_PRICE;
	}

	private int buildElectricTower(float x, float y, List<Enemy> enemyList) {
		ElectricTower et = new ElectricTower(x, y, enemyList);
		if (speedMode) {
			et.setDamage(et.getDamage() * 2);
		}
		towerList.add(new ElectricTower(x, y, enemyList));
		return GameConstants.ELECTRIC_TOWER_PRICE;
	}

	/**
	 * Returns the selected tower.
	 */
	public BaseTower getSelectedTower(Vector2 center) {

		for (BaseTower tower : towerList) {
			tower.setSelected(false);
			if (tower.position.equals(center)) {
				selectedTower = tower;
				selectedTower.setSelected(true);
			}
		}
		return selectedTower;
	}

	public BaseTower getSelectedTower() {
		return selectedTower;
	}

	public void increaseAttack() {
		selectedTower.increaseDamage();
	}

	public void increaseRange() {
		selectedTower.increaseRange();
	}

	public void increaseSpeed() {
		selectedTower.increaseSpeed();
	}

	public void clearSelectedTower() {
		if (selectedTower != null) {
			selectedTower.setSelected(false);
			selectedTower = null;
		}
	}

	public void speed2xClicked() {
		// also consider towers which will be builded during the process.
		speedMode = true;
		for (BaseTower baseTower : towerList) {
			baseTower.setSpeed(baseTower.getSpeed() * 2);
		}
	}

	public void normalSpeedClicked() {
		speedMode = false;
		for (BaseTower baseTower : towerList) {
			baseTower.setSpeed(baseTower.getSpeed() / 2);
		}
	}

}
