package com.javakaian.game.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.javakaian.game.buttons.Menu;
import com.javakaian.game.buttons.OButton;
import com.javakaian.game.buttons.OButtonListener;
import com.javakaian.game.buttons.OToggleButton;
import com.javakaian.game.buttons.OToggleButtonListener;
import com.javakaian.game.level.Level;
import com.javakaian.game.map.GameConstants;
import com.javakaian.game.map.GameUtils;
import com.javakaian.game.resources.MyAtlas;

public class TowerSelectionMenu extends Menu {

	private Level level;

	private String healthText = "HEALTH: ";
	private String remainsText = "REMAINS: ";

	private BitmapFont bitmapFont;

	private OButton btnFire;
	private OButton btnIce;
	private OButton btnElectric;

	private OButton btnDamage;
	private OButton btnRange;
	private OButton btnAttackSpped;
	private OToggleButton btnPauseResume;
	private OToggleButton btnDoubleSpeed;
	private OButton btnRestart;
	private OButton btnExit;

	public TowerSelectionMenu(Level level) {
		super(0, GameConstants.GRID_HEIGHT * 7, GameConstants.TOWER_SELECTION_MENU_WIDTH,
				GameConstants.TOWER_SELECTION_MENU_HEIGHT, MyAtlas.PATH_GREY1);
		this.level = level;

		initButtons();

		this.bitmapFont = GameUtils.generateBitmapFont(23, Color.BLACK);
	}

	private void initButtons() {

		float xPosition = GameConstants.MENU_ITEM_OFFSET_X;
		float yPosition = GameConstants.MENU_ITEM_OFFSET_Y + GameConstants.GRID_HEIGHT * 7;
		float yPosition2 = GameConstants.MENU_ITEM_OFFSET_Y + GameConstants.GRID_HEIGHT * 7f;

		btnFire = new OButton(xPosition, yPosition, GameConstants.MENU_ITEM_WIDTH, GameConstants.MENU_ITEM_HEIGHT);
		btnFire.setIcon(MyAtlas.FIRE_PLANE_MENU);
		btnFire.setPressedIcon(MyAtlas.FIRE_PLANE);
		btnFire.setDisabledSprite(MyAtlas.FIRE_PLANE_MENU_DISABLED);
		btnFire.setDraggable(true);
		btnFire.setPrice(GameConstants.TOWER_PRICE);

		xPosition += (GameConstants.MENU_ITEM_OFFSET_X + GameConstants.MENU_ITEM_WIDTH);
		btnIce = new OButton(xPosition, yPosition, GameConstants.MENU_ITEM_WIDTH, GameConstants.MENU_ITEM_HEIGHT);
		btnIce.setIcon(MyAtlas.ICE_TOWER_MENU);
		btnIce.setPressedIcon(MyAtlas.ICE_TOWER_MENU);
		btnIce.setDisabledSprite(MyAtlas.ICE_TOWER_MENU_DISABLED);
		btnIce.setDraggable(true);
		btnIce.setPrice(GameConstants.TOWER_PRICE);

		xPosition += (GameConstants.MENU_ITEM_OFFSET_X + GameConstants.MENU_ITEM_WIDTH);
		btnElectric = new OButton(xPosition, yPosition, GameConstants.MENU_ITEM_WIDTH, GameConstants.MENU_ITEM_HEIGHT);
		btnElectric.setIcon(MyAtlas.ELECTRIC_MENU_ITEM);
		btnElectric.setPressedIcon(MyAtlas.ELECTRIC_MENU_ITEM);
		btnElectric.setDisabledSprite(MyAtlas.ELECTRIC_MENU_ITEM_DISABLED);
		btnElectric.setDraggable(true);
		btnElectric.setPrice(GameConstants.TOWER_PRICE);

		xPosition += GameConstants.GRID_WIDTH * 3.5;
		xPosition += (GameConstants.MENU_ITEM_OFFSET_X + GameConstants.MENU_ITEM_WIDTH);

		btnDamage = new OButton(xPosition, yPosition2, GameConstants.MENU_ITEM_WIDTH * (0.75f),
				GameConstants.MENU_ITEM_HEIGHT * (0.75f));
		btnDamage.setIcon(MyAtlas.ATTACK_MENU_ITEM);
		btnDamage.setPressedIcon(MyAtlas.ATTAKC_MENU_ITEM_PRESSED);
		btnDamage.setDisabledSprite(MyAtlas.ATTAKC_MENU_ITEM_DISABLED);
		btnDamage.setEnable(false);
		btnDamage.setPrice(GameConstants.TOWER_ATTACK_PRICE);

		xPosition += (GameConstants.MENU_ITEM_OFFSET_X + GameConstants.MENU_ITEM_WIDTH);
		btnRange = new OButton(xPosition, yPosition2, GameConstants.MENU_ITEM_WIDTH * (0.75f),
				GameConstants.MENU_ITEM_HEIGHT * (0.75f));
		btnRange.setIcon(MyAtlas.RANGE_MENU_ITEM);
		btnRange.setPressedIcon(MyAtlas.RANGE_MENU_ITEM_PRESSED);
		btnRange.setDisabledSprite(MyAtlas.RANGE_MENU_ITEM_DISABLED);
		btnRange.setEnable(false);
		btnRange.setPrice(GameConstants.TOWER_RANGE_PRICE);
		xPosition += (GameConstants.MENU_ITEM_OFFSET_X + GameConstants.MENU_ITEM_WIDTH);
		btnAttackSpped = new OButton(xPosition, yPosition2, GameConstants.MENU_ITEM_WIDTH * (0.75f),
				GameConstants.MENU_ITEM_HEIGHT * (0.75f));
		btnAttackSpped.setIcon(MyAtlas.SPEED_MENU_ITEM);
		btnAttackSpped.setPressedIcon(MyAtlas.SPEED_MENU_ITEM_PRESSED);
		btnAttackSpped.setDisabledSprite(MyAtlas.SPEED_MENU_ITEM_DISABLED);
		btnAttackSpped.setEnable(false);
		btnAttackSpped.setPrice(GameConstants.TOWER_SPEED_PRICE);

		btnPauseResume = new OToggleButton(GameConstants.PAUSE_RESUME_POS_X, yPosition2,
				GameConstants.PAUSE2X_ITEM_WIDTH, GameConstants.PAUSE2X_ITEM_HEIGHT);
		btnPauseResume.setIcon(MyAtlas.PAUSE_MENU_ITEM);
		btnPauseResume.setPressedIcon(MyAtlas.RESUME_MENU_ITEM);
		btnPauseResume.setEnableDisableFlag(false);

		btnDoubleSpeed = new OToggleButton(GameConstants.DOUBLE_SPEED_POS_X, yPosition2,
				GameConstants.PAUSE2X_ITEM_WIDTH, GameConstants.PAUSE2X_ITEM_HEIGHT);
		btnDoubleSpeed.setIcon(MyAtlas.MENU_ITEM_2X);
		btnDoubleSpeed.setPressedIcon(MyAtlas.MENU_ITEM_2X_PRESSED);
		btnDoubleSpeed.setEnableDisableFlag(false);

		btnExit = new OButton(GameConstants.DOUBLE_SPEED_POS_X, yPosition2 + GameConstants.PAUSE2X_ITEM_WIDTH,
				GameConstants.PAUSE2X_ITEM_WIDTH, GameConstants.PAUSE2X_ITEM_WIDTH);
		btnExit.setIcon(MyAtlas.QUIT_MENU_ITEM);
		btnExit.setPressedIcon(MyAtlas.QUIT_MENU_ITEM_PRESSED);
		btnExit.setEnableDisableFlag(false);

		btnRestart = new OButton(GameConstants.PAUSE_RESUME_POS_X, yPosition2 + GameConstants.PAUSE2X_ITEM_WIDTH,
				GameConstants.PAUSE2X_ITEM_WIDTH, GameConstants.PAUSE2X_ITEM_WIDTH);
		btnRestart.setIcon(MyAtlas.REMAKE_MENU_ITEM);
		btnRestart.setPressedIcon(MyAtlas.REMAKE_MENU_ITEM_PRESSED);
		btnRestart.setEnableDisableFlag(false);

		menuItems.add(btnFire);
		menuItems.add(btnIce);
		menuItems.add(btnElectric);
		menuItems.add(btnDamage);
		menuItems.add(btnRange);
		menuItems.add(btnAttackSpped);
		menuItems.add(btnPauseResume);
		menuItems.add(btnDoubleSpeed);
		menuItems.add(btnRestart);
		menuItems.add(btnExit);

		initButtonListener();

	}

	private void initButtonListener() {

		btnFire.setButtonListener(new OButtonListener() {

			@Override
			public void touchDown(float x, float y) {
				level.renderGrids(true);
				System.out.println("btn fire touch downd");

			}

			@Override
			public void touchRelease(float x, float y) {
				level.createFireTowerClicked(x, y);

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
				level.createIceTowerClicked(x, y);
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
				level.createElectricTowerClicked(x, y);
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

			}

			@Override
			public void touchRelease(float x, float y) {
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

		btnAttackSpped.setButtonListener(new OButtonListener() {

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
					level.returnTomenu();
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

	public void initButtonStates() {
		for (OButton oButton : menuItems) {
			oButton.setEnable(false);
		}
		btnFire.setEnable(true);
		btnIce.setEnable(true);
		btnElectric.setEnable(true);
		btnRestart.setEnable(true);
		btnExit.setEnable(true);
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

	public void fireMoneyChanged(int money) {
		for (OButton oButton : menuItems) {
			oButton.moneyChanged(money);
		}
	}

	public void damagePriceChanged(int price) {
		btnDamage.priceChanged(price);
	}

	public void rangePriceChanged(int price) {
		btnRange.priceChanged(price);
	}

	public void attacSpeedPriceChanged(int price) {
		btnAttackSpped.priceChanged(price);
	}

	public void fireHealthChanged(int remainingHealth) {
		this.healthText = "HEALTH: " + String.valueOf(remainingHealth);

	}

	public void fireEnemyNumberChanged(int enemyNumber) {
		this.remainsText = "REMAINS: " + String.valueOf(enemyNumber);

	}

	public void disableAllMenuItems() {
		btnDamage.setEnable(false);
		btnRange.setEnable(false);
		btnAttackSpped.setEnable(false);
	}

}
