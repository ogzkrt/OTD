package com.javakaian.game.level;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.map.EnumGridType;
import com.javakaian.game.map.GameConstants;
import com.javakaian.game.map.GameUtils;
import com.javakaian.game.map.Map;
import com.javakaian.game.menu.Entity;
import com.javakaian.game.menu.HeaderMenu;
import com.javakaian.game.menu.TowerSelectionMenu;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.states.PlayState;
import com.javakaian.game.states.StateEnum;
import com.javakaian.game.towers.BaseTower;
import com.javakaian.game.towers.EnemyController;
import com.javakaian.game.towers.Grid;
import com.javakaian.game.towers.TowerController;

public class Level implements Entity {

	private EnemyController enemyController;
	private TowerController towerController;

	private Map map;
	private TowerSelectionMenu towerSelectionMenu;
	private HeaderMenu headerMenu;

	private int enemyNumber;
	private int enemyHealth;
	private int score;
	private int money;
	private int remainingHealth;
	private BitmapFont bitmapFont;
	private GlyphLayout glyphLayout;
	private PlayState state;

	private int timeLeft;
	private boolean renderTimeAndWaveNumber = false;
	private int waveNumber;

	public Level(PlayState state, BitmapFont bitmapFont, GlyphLayout glyphLayout) {
		this.state = state;
		this.bitmapFont = GameUtils.generateBitmapFont(80, Color.BLACK);
		this.glyphLayout = glyphLayout;
		init();

	}

	private void init() {
		waveNumber = 1;
		score = 0;
		money = 200;
		enemyNumber = 20;
		enemyHealth = 200;
		remainingHealth = GameConstants.REMAINING_HEALTH;

		map = new Map();
		enemyController = new EnemyController(this);

		towerController = new TowerController();

		// initiliase menu and update its texts
		headerMenu = new HeaderMenu(MyAtlas.PATH_GREY1);
		headerMenu.fireMoneyChanged(money);

		towerSelectionMenu = new TowerSelectionMenu(this);
		towerSelectionMenu.fireEnemyNumberChanged(enemyNumber);
		towerSelectionMenu.fireHealthChanged(remainingHealth);
		towerSelectionMenu.fireMoneyChanged(money);
		towerSelectionMenu.initButtonStates();
	}

	@Override
	public void render(ShapeRenderer sr) {
		map.render(sr);
		enemyController.render(sr);
		towerController.render(sr);
		towerSelectionMenu.render(sr);
		// headerMenu.render(sr);
	}

	@Override
	public void render(SpriteBatch sb) {
		map.render(sb);
		enemyController.render(sb);
		towerController.render(sb);
		towerSelectionMenu.render(sb);
		headerMenu.render(sb);
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

	private boolean canBuildTower() {
		if (money >= GameConstants.TOWER_PRICE) {
			money -= GameConstants.TOWER_PRICE;
			fireMoneyChanged();
			return true;
		}
		return false;
	}

	public void createFireTowerClicked(float x, float y) {
		List<Grid> gridList = this.map.getBoard().getGridList();
		for (Grid grid : gridList) {
			if (grid.contains(x, y)) {

				switch (grid.getType()) {
				case TOWER:
					System.out.println("CAN NOT BUILD TOWER ALREADY EXIST");
					break;
				case GRASS:

					if (canBuildTower()) {
						towerController.createFireTower(grid.getPosition().x, grid.getPosition().y,
								enemyController.getEnemyList());
						grid.setType(EnumGridType.TOWER);
						fireMoneyChanged();
						this.map.getBoard().setRender(false);

					}
					break;
				case PATH:
					System.out.println("CAN NOT BUILD TO THE PATH");
					break;

				default:
					break;
				}

			}

		}
	}

	public void createElectricTowerClicked(float x, float y) {
		List<Grid> gridList = this.map.getBoard().getGridList();
		for (Grid grid : gridList) {
			if (grid.contains(x, y)) {

				switch (grid.getType()) {
				case TOWER:
					System.out.println("CAN NOT BUILD TOWER ALREADY EXIST");
					break;
				case GRASS:

					if (canBuildTower()) {
						towerController.createElectricTower(grid.getPosition().x, grid.getPosition().y,
								enemyController.getEnemyList());
						grid.setType(EnumGridType.TOWER);
						fireMoneyChanged();
						this.map.getBoard().setRender(false);
					}
					break;
				case PATH:
					System.out.println("CAN NOT BUILD TO THE PATH");
					break;

				default:
					break;
				}

			}

		}
	}

	public void createIceTowerClicked(float x, float y) {

		List<Grid> gridList = this.map.getBoard().getGridList();
		for (Grid grid : gridList) {
			if (grid.contains(x, y)) {

				switch (grid.getType()) {
				case TOWER:
					System.out.println("CAN NOT BUILD TOWER ALREADY EXIST");
					break;
				case GRASS:

					if (canBuildTower()) {
						towerController.createIceTower(grid.getPosition().x, grid.getPosition().y,
								enemyController.getEnemyList());
						grid.setType(EnumGridType.TOWER);
						fireMoneyChanged();
						this.map.getBoard().setRender(false);
					}
					break;
				case PATH:
					System.out.println("CAN NOT BUILD TO THE PATH");
					break;

				default:
					break;
				}

			}

		}

	}

	public void enemyPassedTheCheckPoint() {
		remainingHealth--;
		fireHealthChanged();
		if (remainingHealth == 0) {
			// it means game over
			state.gameOver();
		}
	}

	public void enemyKilled(float bounty) {

		score += GameConstants.SCORE_INCREASE_CONSTANT;
		enemyNumber -= 1;
		money += bounty;
		fireMoneyChanged();
		fireEnemyNumberChanged();
	}

	public void newWaveCreated(int size) {
		waveNumber++;
		enemyNumber = size;
		renderTimeAndWaveNumber = false;
	}

	public void touchDown(float x, float y) {

		if (towerSelectionMenu.getBoundaryRect().contains(x, y)) {

			towerSelectionMenu.touchDown(x, y);

		} else {
			// towerSelectionMenu.clearSelectedMenuItem();
			selectGrid(x, y);
		}

	}

	public void touchRelease(float x, float y) {

		towerSelectionMenu.touchRelease(x, y);

	}

	public void selectGrid(float x, float y) {

		List<Grid> gridList = this.map.getBoard().getGridList();
		for (Grid grid : gridList) {
			if (grid.contains(x, y)) {

				switch (grid.getType()) {
				case TOWER:
					BaseTower t = towerController.getTower(grid.getPosition());

					// update header and towerselection menu informations
					fireDamagePriceChanged(t.getAttackPrice());
					fireRangePriceChanged(t.getRangePrice());
					fireAttackSpeedPriceChanged(t.getSpeedPrice());
					headerMenu.updateTowerInformations(t.getDamage(), t.getRange(), t.getSpeed());

					break;
				case GRASS:
					towerController.clearSelectedTower();
					towerSelectionMenu.disableAllMenuItems();
					break;
				case PATH:
					towerController.clearSelectedTower();
					towerSelectionMenu.disableAllMenuItems();
					System.out.println("CAN NOT BUILD TO THE PATH");
					break;

				default:
					break;
				}
			}

		}

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

	public void increaseAttackClickled() {
		BaseTower t = towerController.getSelectedTower();
		if (t.getAttackPrice() <= money) {
			money -= t.getAttackPrice();
			fireMoneyChanged();
			towerController.increaseAttack();
			fireDamagePriceChanged(t.getAttackPrice());
		}

	}

	public void increaseRangeClicked() {
		BaseTower t = towerController.getSelectedTower();
		if (t.getRangePrice() <= money) {
			money -= t.getRangePrice();
			fireMoneyChanged();
			towerController.increaseRange();
			fireRangePriceChanged(t.getRangePrice());
		}

	}

	public void increaseSpeedClicked() {
		BaseTower t = towerController.getSelectedTower();
		if (t.getSpeedPrice() <= money) {

			money -= t.getSpeedPrice();
			fireMoneyChanged();
			towerController.increaseSpeed();
			fireAttackSpeedPriceChanged(t.getSpeedPrice());
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

	public int getMoney() {
		return money;
	}

	public void nextWaveCountDown(int i) {
		this.timeLeft = i;
		renderTimeAndWaveNumber = true;

	}

	private void fireHealthChanged() {
		towerSelectionMenu.fireHealthChanged(remainingHealth);
	}

	private void fireEnemyNumberChanged() {
		towerSelectionMenu.fireEnemyNumberChanged(enemyNumber);
	}

	public void fireMoneyChanged() {
		if (towerController.getSelectedTower() != null) {
			towerSelectionMenu.fireMoneyChanged(this.money);
		}
		headerMenu.fireMoneyChanged(this.money);
	}

	public void fireDamagePriceChanged(int price) {
		if (towerController.getSelectedTower() != null) {
			towerSelectionMenu.damagePriceChanged(price);
		}

	}

	public void fireRangePriceChanged(int price) {
		if (towerController.getSelectedTower() != null) {
			towerSelectionMenu.rangePriceChanged(price);
		}

	}

	public void fireAttackSpeedPriceChanged(int price) {
		if (towerController.getSelectedTower() != null) {
			towerSelectionMenu.attacSpeedPriceChanged(price);
		}

	}

	public void returnTomenu() {
		state.getStateController().setState(StateEnum.MenuState);
	}

}
