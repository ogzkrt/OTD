package com.javakaian.game.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.javakaian.game.buttons.OButton;
import com.javakaian.game.buttons.OButtonListener;
import com.javakaian.game.buttons.OToggleButton;
import com.javakaian.game.buttons.OToggleButtonListener;
import com.javakaian.game.buttons.TowerButton;
import com.javakaian.game.buttons.UpgradeButton;
import com.javakaian.game.level.Level;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.towers.BaseTower;
import com.javakaian.game.towers.BaseTower.TowerType;
import com.javakaian.game.util.GameConstants;
import com.javakaian.game.util.GameUtils;

public class TowerSelectionMenu extends Menu {

	private Level level;

	private String healthText = "HEALTH: ";
	private String remainsText = "REMAINS: ";

	private BitmapFont bitmapFont;

	private TowerButton btnFire;
	private TowerButton btnIce;
	private TowerButton btnElectric;

	private UpgradeButton btnDamage;
	private UpgradeButton btnRange;
	private UpgradeButton btnSpeed;

	private OToggleButton btnPauseResume;
	private OToggleButton btnDoubleSpeed;

	private OButton btnRestart;
	private OButton btnExit;

	public TowerSelectionMenu(Level level) {
		super(0, GameConstants.GRID_HEIGHT * 7, GameConstants.TOWER_SELECTION_MENU_WIDTH,
				GameConstants.TOWER_SELECTION_MENU_HEIGHT, MyAtlas.MENU);
		this.level = level;

		initButtons();

		this.bitmapFont = GameUtils.generateBitmapFont(23, Color.BLACK);
	}

	private void initButtons() {

		float xPosition = GameConstants.MENU_ITEM_OFFSET_X;
		float yPosition = GameConstants.MENU_ITEM_OFFSET_Y + GameConstants.GRID_HEIGHT * 7;
		float damageRangeSpeedYPosition = GameConstants.MENU_ITEM_OFFSET_Y + GameConstants.GRID_HEIGHT * 7.2f;

		btnFire = new TowerButton(xPosition, yPosition, GameConstants.MENU_ITEM_WIDTH, GameConstants.MENU_ITEM_HEIGHT);
		btnFire.setPrice(GameConstants.TOWER_PRICE);
		btnFire.setIcon(MyAtlas.FIRE_PLANE_MENU);
		btnFire.setPressedIcon(MyAtlas.FIRE_PLANE);
		btnFire.setDisabledSprite(MyAtlas.FIRE_PLANE_MENU_DISABLED);

		xPosition += (GameConstants.MENU_ITEM_OFFSET_X + GameConstants.MENU_ITEM_WIDTH);
		btnIce = new TowerButton(xPosition, yPosition, GameConstants.MENU_ITEM_WIDTH, GameConstants.MENU_ITEM_HEIGHT);
		btnIce.setPrice(GameConstants.TOWER_PRICE);
		btnIce.setIcon(MyAtlas.ICE_TOWER_MENU);
		btnIce.setPressedIcon(MyAtlas.ICE_TOWER_MENU);
		btnIce.setDisabledSprite(MyAtlas.ICE_TOWER_MENU_DISABLED);

		xPosition += (GameConstants.MENU_ITEM_OFFSET_X + GameConstants.MENU_ITEM_WIDTH);
		btnElectric = new TowerButton(xPosition, yPosition, GameConstants.MENU_ITEM_WIDTH,
				GameConstants.MENU_ITEM_HEIGHT);
		btnElectric.setPrice(GameConstants.ELECTRIC_TOWER_PRICE);
		btnElectric.setIcon(MyAtlas.ELECTRIC_MENU_ITEM);
		btnElectric.setPressedIcon(MyAtlas.ELECTRIC_MENU_ITEM);
		btnElectric.setDisabledSprite(MyAtlas.ELECTRIC_MENU_ITEM_DISABLED);

		xPosition += GameConstants.GRID_WIDTH * 3.5;
		xPosition += (GameConstants.MENU_ITEM_OFFSET_X + GameConstants.MENU_ITEM_WIDTH);

		btnDamage = new UpgradeButton(xPosition, damageRangeSpeedYPosition, GameConstants.MENU_ITEM_WIDTH * (0.75f),
				GameConstants.MENU_ITEM_HEIGHT * (0.75f));
		btnDamage.setIcon(MyAtlas.ATTACK_MENU_ITEM);
		btnDamage.setPressedIcon(MyAtlas.ATTAKC_MENU_ITEM_PRESSED);
		btnDamage.setDisabledSprite(MyAtlas.ATTAKC_MENU_ITEM_DISABLED);
		btnDamage.setEnable(false);

		xPosition += (GameConstants.MENU_ITEM_OFFSET_X + GameConstants.MENU_ITEM_WIDTH);
		btnRange = new UpgradeButton(xPosition, damageRangeSpeedYPosition, GameConstants.MENU_ITEM_WIDTH * (0.75f),
				GameConstants.MENU_ITEM_HEIGHT * (0.75f));
		btnRange.setIcon(MyAtlas.RANGE_MENU_ITEM);
		btnRange.setPressedIcon(MyAtlas.RANGE_MENU_ITEM_PRESSED);
		btnRange.setDisabledSprite(MyAtlas.RANGE_MENU_ITEM_DISABLED);
		btnRange.setEnable(false);

		xPosition += (GameConstants.MENU_ITEM_OFFSET_X + GameConstants.MENU_ITEM_WIDTH);
		btnSpeed = new UpgradeButton(xPosition, damageRangeSpeedYPosition, GameConstants.MENU_ITEM_WIDTH * (0.75f),
				GameConstants.MENU_ITEM_HEIGHT * (0.75f));
		btnSpeed.setIcon(MyAtlas.SPEED_MENU_ITEM);
		btnSpeed.setPressedIcon(MyAtlas.SPEED_MENU_ITEM_PRESSED);
		btnSpeed.setDisabledSprite(MyAtlas.SPEED_MENU_ITEM_DISABLED);
		btnSpeed.setEnable(false);

		btnPauseResume = new OToggleButton(GameConstants.PAUSE_RESUME_POS_X, yPosition,
				GameConstants.PAUSE2X_ITEM_WIDTH, GameConstants.PAUSE2X_ITEM_HEIGHT);
		btnPauseResume.setIcon(MyAtlas.PAUSE_MENU_ITEM);
		btnPauseResume.setPressedIcon(MyAtlas.RESUME_MENU_ITEM);

		btnDoubleSpeed = new OToggleButton(GameConstants.DOUBLE_SPEED_POS_X, yPosition,
				GameConstants.PAUSE2X_ITEM_WIDTH, GameConstants.PAUSE2X_ITEM_HEIGHT);
		btnDoubleSpeed.setIcon(MyAtlas.MENU_ITEM_2X);
		btnDoubleSpeed.setPressedIcon(MyAtlas.MENU_ITEM_2X_PRESSED);

		btnExit = new OButton(GameConstants.DOUBLE_SPEED_POS_X, yPosition + GameConstants.PAUSE2X_ITEM_WIDTH,
				GameConstants.PAUSE2X_ITEM_WIDTH, GameConstants.PAUSE2X_ITEM_WIDTH);
		btnExit.setIcon(MyAtlas.QUIT_MENU_ITEM);
		btnExit.setPressedIcon(MyAtlas.QUIT_MENU_ITEM_PRESSED);

		btnRestart = new OButton(GameConstants.PAUSE_RESUME_POS_X, yPosition + GameConstants.PAUSE2X_ITEM_WIDTH,
				GameConstants.PAUSE2X_ITEM_WIDTH, GameConstants.PAUSE2X_ITEM_WIDTH);
		btnRestart.setIcon(MyAtlas.REMAKE_MENU_ITEM);
		btnRestart.setPressedIcon(MyAtlas.REMAKE_MENU_ITEM_PRESSED);

		towerButtons.add(btnFire);
		towerButtons.add(btnIce);
		towerButtons.add(btnElectric);

		upgradeButtons.add(btnDamage);
		upgradeButtons.add(btnRange);
		upgradeButtons.add(btnSpeed);

		menuButtons.add(btnPauseResume);
		menuButtons.add(btnDoubleSpeed);
		menuButtons.add(btnRestart);
		menuButtons.add(btnExit);

		initButtonListener();

	}

	private void initButtonListener() {

		btnFire.setButtonListener(new OButtonListener() {

			@Override
			public void touchDown(float x, float y) {
				level.renderGrids(true);

			}

			@Override
			public void touchRelease(float x, float y) {
				level.createTowerClicked(x, y, TowerType.FIRE);
				level.renderGrids(false);
			}

			@Override
			public void dragged(float x, float y) {
			}

		});

		btnIce.setButtonListener(new OButtonListener() {

			@Override
			public void touchDown(float x, float y) {
				level.renderGrids(true);
			}

			@Override
			public void touchRelease(float x, float y) {
				level.createTowerClicked(x, y, TowerType.ICE);
				level.renderGrids(false);
			}

			@Override
			public void dragged(float x, float y) {
			}
		});
		btnElectric.setButtonListener(new OButtonListener() {

			@Override
			public void touchRelease(float x, float y) {
				// TODO Auto-generated method stub
				level.createTowerClicked(x, y, TowerType.ELECTRIC);
				level.renderGrids(false);
			}

			@Override
			public void touchDown(float x, float y) {
				level.renderGrids(true);
			}

			@Override
			public void dragged(float x, float y) {

			}
		});
		btnPauseResume.setToggleListener(new OToggleButtonListener() {

			@Override
			public void toggled(boolean isToggled) {
				System.out.println(isToggled);
				if (isToggled) {
					level.pause();
				} else {
					level.resume();
				}
			}
		});

		btnDoubleSpeed.setToggleListener(new OToggleButtonListener() {

			@Override
			public void toggled(boolean isToggled) {
				System.out.println(isToggled);
				if (isToggled) {
					level.speed2xClicked();
				} else {
					level.normalSpeedClicked();
				}
			}
		});

		btnDamage.setButtonListener(new OButtonListener() {

			@Override
			public void touchDown(float x, float y) {
				System.out.println("increase damage clciked");
			}

			@Override
			public void touchRelease(float x, float y) {
				System.out.println("increase attack clicked");
				level.increaseAttackClickled();
			}

			@Override
			public void dragged(float x, float y) {

			}
		});

		btnRange.setButtonListener(new OButtonListener() {

			@Override
			public void touchDown(float x, float y) {

			}

			@Override
			public void touchRelease(float x, float y) {
				level.increaseRangeClicked();
			}

			@Override
			public void dragged(float x, float y) {

			}
		});

		btnSpeed.setButtonListener(new OButtonListener() {

			@Override
			public void touchDown(float x, float y) {

			}

			@Override
			public void touchRelease(float x, float y) {
				level.increaseSpeedClicked();
			}

			@Override
			public void dragged(float x, float y) {

			}
		});

		btnRestart.setButtonListener(new OButtonListener() {

			@Override
			public void touchRelease(float x, float y) {

				System.out.println("buton restart");
				if (btnRestart.getBoundRect().contains(x, y)) {
					level.restart();
				}
			}

			@Override
			public void touchDown(float x, float y) {
				System.out.println("btn restart touched..");
			}

			@Override
			public void dragged(float x, float y) {

			}
		});

		btnExit.setButtonListener(new OButtonListener() {

			@Override
			public void touchRelease(float x, float y) {
				if (btnExit.getBoundRect().contains(x, y)) {
					level.returnToMenuClicked();
				}
			}

			@Override
			public void touchDown(float x, float y) {
				System.out.println("exit");
			}

			@Override
			public void dragged(float x, float y) {

			}
		});

	}

	@Override
	public void render(SpriteBatch sb) {
		renderGrids(sb);
		super.render(sb);
		renderBitmapFonts(sb);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
	}

	@Override
	public void updateInputs(float x, float y) {
		super.updateInputs(x, y);
	}

	private void renderGrids(SpriteBatch sb) {
		int row = 2;
		int col = 16;
		float posx = 0;
		float posy = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				posx = j * GameConstants.GRID_WIDTH;
				posy = i * GameConstants.GRID_HEIGHT + GameConstants.GRID_HEIGHT * 7;
				sb.draw(menuSprite, posx, posy, GameConstants.GRID_WIDTH, GameConstants.GRID_HEIGHT);
			}
		}
	}

	private void renderBitmapFonts(SpriteBatch sb) {

		bitmapFont.draw(sb, healthText, GameConstants.ENEMY_NUM_POS_X, GameConstants.ENEMY_NUM_POS_Y);
		bitmapFont.draw(sb, remainsText, GameConstants.REMAINING_POS_X, GameConstants.REMAINNG_POS_Y);

	}

	public void renderGrids(boolean b) {

		level.renderGrids(b);
	}

	public void fireHealthChanged(int remainingHealth) {
		this.healthText = "HEALTH: " + String.valueOf(remainingHealth);

	}

	public void fireEnemyNumberChanged(int enemyNumber) {
		this.remainsText = "REMAINS: " + String.valueOf(enemyNumber);

	}

	public void clearSelectedTower() {
		btnDamage.setEnable(false);
		btnRange.setEnable(false);
		btnSpeed.setEnable(false);

		btnDamage.setText("");
		btnRange.setText("");
		btnSpeed.setText("");
	}

	public void moneyChanged(int money) {
		updateTowerButtons(money);
		BaseTower selectedTower = level.getSelectedTower();
		if (selectedTower == null) {
			btnDamage.setEnable(false);
			btnRange.setEnable(false);
			btnSpeed.setEnable(false);
			return;
		}
		updateUpgradeButtons(money);
	}

	public void updateUpgradeButtons(int money) {

		BaseTower selectedTower = level.getSelectedTower();
		btnDamage.setText(String.valueOf(selectedTower.getAttackPrice()));
		btnDamage.setPrice(selectedTower.getAttackPrice());
		btnRange.setText(String.valueOf(selectedTower.getRangePrice()));
		btnRange.setPrice(selectedTower.getRangePrice());
		btnSpeed.setText(String.valueOf(selectedTower.getSpeedPrice()));
		btnSpeed.setPrice(selectedTower.getSpeedPrice());

		for (UpgradeButton upgradeButton : upgradeButtons) {
			upgradeButton.moneyChanged(money);
		}
		switch (selectedTower.getType()) {
		case ICE:
			btnDamage.setEnable(false);
			break;
		case ELECTRIC:
			btnSpeed.setEnable(false);
			break;
		default:
			break;
		}

	}

	private void updateTowerButtons(int money) {
		for (TowerButton tbutton : towerButtons) {
			tbutton.moneyChanged(money);
		}

	}

}
