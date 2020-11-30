package com.javakaian.game.controllers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.entity.Enemy;
import com.javakaian.game.entity.Entity;
import com.javakaian.game.level.Level;
import com.javakaian.game.towers.BaseTower;
import com.javakaian.game.towers.ElectricTower;
import com.javakaian.game.towers.FireTower;
import com.javakaian.game.towers.IceTower;

public class TowerController implements Entity {

	private List<BaseTower> towerList;

	private BaseTower selectedTower;

	private Level level;

	public TowerController(Level level) {

		this.level = level;
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

	public void createFireTower(float x, float y, List<Enemy> enemyList) {

		towerList.add(new FireTower(x, y, enemyList));
	}

	public void createIceTower(float x, float y, List<Enemy> enemyList) {

		towerList.add(new IceTower(x, y, enemyList));
	}

	public void createElectricTower(float x, float y, List<Enemy> enemyList) {

		towerList.add(new ElectricTower(x, y, enemyList));
	}

	public List<BaseTower> getTowerList() {
		return towerList;
	}

	public BaseTower getTower(Vector2 center) {

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
		this.selectedTower.increaseDamage();
		level.fireDamagePriceChanged(selectedTower);
	}

	public void increaseRange() {
		selectedTower.increaseRange(10);
		level.fireRangePriceChanged(selectedTower);
	}

	public void increaseSpeed() {
		selectedTower.increaseSpeed();
		level.fireAttackSpeedPriceChanged(selectedTower);
	}

	public void clearSelectedTower() {
		if (selectedTower != null) {
			selectedTower.setSelected(false);
			selectedTower = null;
		}
	}

	public void speed2xClicked() {

		// also consider towers which will be builded during the process.
		for (BaseTower baseTower : towerList) {
			baseTower.attackSpeed = baseTower.attackSpeed * 2;
		}
	}

	public void normalSpeedClicked() {

		for (BaseTower baseTower : towerList) {
			baseTower.attackSpeed = baseTower.attackSpeed / 2;
		}
	}

}
