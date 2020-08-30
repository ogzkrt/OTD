package com.javakaian.game.level;

import java.util.List;

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
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.states.PlayState;
import com.javakaian.game.states.State.StateEnum;
import com.javakaian.game.towers.BaseTower;
import com.javakaian.game.ui.menu.InformationMenu;
import com.javakaian.game.ui.menu.TowerSelectionMenu;
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
		money = 200;
		enemyNumber = 20;
		enemyHealth = 200;
		remainingHealth = GameConstants.REMAINING_HEALTH;

		map = new Map();
		enemyController = new EnemyController(this);

		towerController = new TowerController(this);

		// initiliase menu and update its texts
		informationMenu = new InformationMenu(MyAtlas.MENU);
		informationMenu.fireMoneyChanged(money);

		towerSelectionMenu = new TowerSelectionMenu(this);
		towerSelectionMenu.fireEnemyNumberChanged(enemyNumber);
		towerSelectionMenu.fireHealthChanged(remainingHealth);
		towerSelectionMenu.fireMoneyChangedForMenuItems(money);
		towerSelectionMenu.fireMoneyChangedForMenuProps(money);
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
				case LAND:

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
				case LAND:

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
				case LAND:

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
		fireScoreChanged();
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
					fireTowerSelectedEvent(t);
					break;
				case LAND:
					towerController.clearSelectedTower();
					informationMenu.clearInformations();
					towerSelectionMenu.clearSelectedTower();
					break;
				case PATH:
					towerController.clearSelectedTower();
					towerSelectionMenu.clearSelectedTower();
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
		}

	}

	public void increaseRangeClicked() {
		BaseTower t = towerController.getSelectedTower();
		if (t.getRangePrice() <= money) {
			money -= t.getRangePrice();
			fireMoneyChanged();
			towerController.increaseRange();
		}

	}

	public void increaseSpeedClicked() {
		BaseTower t = towerController.getSelectedTower();
		if (t.getSpeedPrice() <= money) {

			money -= t.getSpeedPrice();
			fireMoneyChanged();
			towerController.increaseSpeed();
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

	public void nextWaveCountDown(int i) {
		this.timeLeft = i;
		renderTimeAndWaveNumber = true;

	}

	private void fireTowerSelectedEvent(BaseTower t) {
		informationMenu.updateTowerInformations(t.getDamage(), t.getRange(), t.getSpeed());
		towerSelectionMenu.updatePropertyButtons(t.getAttackPrice(), t.getRangePrice(), t.getSpeedPrice());
	}

	private void fireScoreChanged() {
		informationMenu.fireScoreChanged(this.score);
	}

	private void fireHealthChanged() {
		towerSelectionMenu.fireHealthChanged(remainingHealth);
	}

	private void fireEnemyNumberChanged() {
		towerSelectionMenu.fireEnemyNumberChanged(enemyNumber);
	}

	public void fireMoneyChanged() {
		informationMenu.fireMoneyChanged(money);
		towerSelectionMenu.fireMoneyChangedForMenuItems(money);
		if (towerController.getSelectedTower() != null) {
			towerSelectionMenu.fireMoneyChangedForMenuProps(money);
		}
	}

	public void fireDamagePriceChanged(BaseTower t) {
		towerSelectionMenu.damagePriceChanged(t.getAttackPrice());
		informationMenu.updateTowerInformations(t.getDamage(), t.getRange(), t.getSpeed());
	}

	public void fireRangePriceChanged(BaseTower t) {
		towerSelectionMenu.rangePriceChanged(t.getRangePrice());
		informationMenu.updateTowerInformations(t.getDamage(), t.getRange(), t.getSpeed());
	}

	public void fireAttackSpeedPriceChanged(BaseTower t) {
		towerSelectionMenu.attacSpeedPriceChanged(t.getSpeedPrice());
		informationMenu.updateTowerInformations(t.getDamage(), t.getRange(), t.getSpeed());
	}

	public void returnToMenuClicked() {
		state.getStateController().setState(StateEnum.PauseState);
	}

}
