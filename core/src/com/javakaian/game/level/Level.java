package com.javakaian.game.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.controllers.EnemyController;
import com.javakaian.game.controllers.TowerController;
import com.javakaian.game.entity.Entity;
import com.javakaian.game.map.Grid;
import com.javakaian.game.map.Grid.EnumGridType;
import com.javakaian.game.map.Map;
import com.javakaian.game.menu.InformationMenu;
import com.javakaian.game.menu.TowerSelectionMenu;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.states.PlayState;
import com.javakaian.game.states.State.StateEnum;
import com.javakaian.game.towers.BaseTower;
import com.javakaian.game.towers.BaseTower.TowerType;
import com.javakaian.game.util.GameConstants;
import com.javakaian.game.util.GameUtils;

public class Level implements Entity {

	private EnemyController enemyController;
	private TowerController towerController;

	private Map map;
	private TowerSelectionMenu towerSelectionMenu;
	private InformationMenu informationMenu;

	private int enemyNumber;
	private int enemyHealth;
	private int score;
	private int money;
	private int remainingHealth;
	private BitmapFont bitmapFont;
	private PlayState state;

	private int timeLeft;
	private boolean renderTimeAndWaveNumber = false;
	private int waveNumber;

	public Level(PlayState state, BitmapFont bitmapFont, GlyphLayout glyphLayout) {
		this.state = state;
		this.bitmapFont = GameUtils.generateBitmapFont(80, Color.BLACK);
		init();
	}

	private void init() {
		waveNumber = 1;
		score = 0;
		money = GameConstants.INITIAL_MONEY;
		enemyNumber = 10;
		enemyHealth = 200;
		remainingHealth = GameConstants.REMAINING_HEALTH;

		map = new Map();
		enemyController = new EnemyController(this);
		towerController = new TowerController();
		informationMenu = new InformationMenu(MyAtlas.MENU);
		towerSelectionMenu = new TowerSelectionMenu(this);
	}

	@Override
	public void render(ShapeRenderer sr) {
		map.render(sr);
		enemyController.render(sr);
		towerController.render(sr);
		towerSelectionMenu.render(sr);
	}

	@Override
	public void render(SpriteBatch sb) {
		map.render(sb);
		enemyController.render(sb);
		towerController.render(sb);
		towerSelectionMenu.render(sb);
		informationMenu.render(sb);
		if (renderTimeAndWaveNumber) {
			GameUtils.renderCenter(
					"Wave: " + String.valueOf(waveNumber) + " in: " + String.valueOf(timeLeft) + " second", sb,
					bitmapFont);
		}
	}

	@Override
	public void update(float deltaTime) {
		map.update(deltaTime);
		enemyController.update(deltaTime);
		towerController.update(deltaTime);
		towerSelectionMenu.update(deltaTime);
	}

	public void updateInputs(float x, float y) {
		towerSelectionMenu.updateInputs(x, y);
	}

	public void createTowerClicked(float x, float y, TowerType type) {

		Grid grid = map.getSelectedGrid(x, y);
		if (grid == null)
			return;
		switch (grid.getType()) {
		case TOWER:
			System.out.println("CAN NOT BUILD TOWER ALREADY EXIST");
			break;
		case LAND:
			int cost = towerController.buildTower(grid.getPosition().x, grid.getPosition().y,
					enemyController.getEnemyList(), type, money);
			if (cost != 0) {
				grid.setType(EnumGridType.TOWER);
				decreaseMoney(cost);
			}
			this.map.getBoard().setRender(false);
			break;
		case PATH:
			System.out.println("CAN NOT BUILD TO THE PATH");
			break;
		}
	}

	public void enemyPassedTheCheckPoint() {
		remainingHealth--;
		towerSelectionMenu.fireHealthChanged(remainingHealth);
		if (remainingHealth == 0) {
			state.gameOver();
		}
	}

	public void enemyKilled(int bounty) {

		score += GameConstants.SCORE_INCREASE_CONSTANT;
		enemyNumber -= 1;
		increaseMoney(bounty);
		informationMenu.fireScoreChanged(this.score);
		towerSelectionMenu.fireEnemyNumberChanged(enemyNumber);
	}

	public void newWaveCreated(int size) {
		waveNumber++;
		enemyNumber = size;
		renderTimeAndWaveNumber = false;
	}

	public void touchDown(float x, float y) {

		if (towerSelectionMenu.getBoundaryRect().contains(x, y)) {
			towerSelectionMenu.touchDown(x, y);
		} else if (informationMenu.getBoundaryRect().contains(x, y)) {
			informationMenu.touchDown(x, y);

		} else {
			selectGrid(x, y);
		}

	}

	public void touchRelease(float x, float y) {

		towerSelectionMenu.touchRelease(x, y);

	}

	public void selectGrid(float x, float y) {

		Grid grid = this.map.getSelectedGrid(x, y);
		if (grid == null)
			return;
		switch (grid.getType()) {
		case TOWER:
			BaseTower t = towerController.getSelectedTower(grid.getPosition());
			informationMenu.updateTowerInformations(t);
			towerSelectionMenu.updateUpgradeButtons(money);
			break;
		case LAND:
			towerController.clearSelectedTower();
			informationMenu.clearInformations();
			towerSelectionMenu.clearSelectedTower();
			break;
		case PATH:
			towerController.clearSelectedTower();
			towerSelectionMenu.clearSelectedTower();
			break;

		default:
			break;

		}

	}

	public BaseTower getSelectedTower() {
		return towerController.getSelectedTower();
	}

	public Map getMap() {
		return map;
	}

	public int getEnemyHealth() {
		return enemyHealth;
	}

	public int getEnemyNumber() {
		return enemyNumber;
	}

	public void renderGrids(boolean b) {
		this.map.getBoard().setRender(b);
	}

	public void nextWaveCountDown(int i) {
		this.timeLeft = i;
		renderTimeAndWaveNumber = true;

	}

	public void increaseAttackClickled() {
		BaseTower t = towerController.getSelectedTower();
		int cost = t.getAttackPrice();
		if (cost <= money) {
			towerController.increaseAttack();
			decreaseMoney(cost);
			informationMenu.updateTowerInformations(t);
		}

	}

	public void increaseRangeClicked() {
		BaseTower t = towerController.getSelectedTower();
		int cost = t.getRangePrice();
		if (cost <= money) {
			towerController.increaseRange();
			decreaseMoney(cost);
			informationMenu.updateTowerInformations(t);
		}

	}

	public void increaseSpeedClicked() {
		BaseTower t = towerController.getSelectedTower();
		int cost = t.getSpeedPrice();
		if (cost <= money) {
			towerController.increaseSpeed();
			decreaseMoney(cost);
			informationMenu.updateTowerInformations(t);
		}
	}

	public void restart() {
		init();
	}

	public void pause() {
		state.pause();
	}

	public void resume() {
		state.resume();
	}

	public void speed2xClicked() {
		towerController.speed2xClicked();
		enemyController.speed2xClicked();
	}

	public void normalSpeedClicked() {
		towerController.normalSpeedClicked();
		enemyController.normalSpeedClicked();
	}

	public void returnToMenuClicked() {
		state.getStateController().setState(StateEnum.PauseState);
	}

	public void increaseMoney(int amount) {
		this.money += amount;
		informationMenu.fireMoneyChanged(money);
		towerSelectionMenu.moneyChanged(money);
	}

	public void decreaseMoney(int amount) {
		this.money -= amount;
		informationMenu.fireMoneyChanged(money);
		towerSelectionMenu.moneyChanged(money);
	}

}
