package com.javakaian.game.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.buttons.OButton;
import com.javakaian.game.buttons.TowerButton;
import com.javakaian.game.buttons.UpgradeButton;

public class Menu {

	protected List<TowerButton> towerButtons;
	protected List<UpgradeButton> upgradeButtons;
	protected List<OButton> menuButtons;

	protected TowerButton selectedMenuItem = null;
	protected OButton selectedMenuButton = null;
	protected UpgradeButton selectedUpgradeButton = null;

	protected Vector2 position;
	protected Vector2 size;
	protected Vector2 center;
	protected Rectangle boundaryRect;

	protected Sprite menuSprite;

	public Menu(float x, float y, float width, float height, Sprite menuSprite) {

		this.position = new Vector2(x, y);
		this.size = new Vector2(width, height);
		this.center = new Vector2(position.x + size.x / 2, position.y + size.y / 2);
		this.boundaryRect = new Rectangle(x, y, width, height);
		this.menuSprite = menuSprite;

		towerButtons = new ArrayList<TowerButton>();
		menuButtons = new ArrayList<OButton>();
		upgradeButtons = new ArrayList<UpgradeButton>();
	}

	public void render(SpriteBatch sb) {

		for (OButton button : menuButtons) {
			button.render(sb);
		}
		for (TowerButton towerButton : towerButtons) {
			towerButton.render(sb);
		}
		for (UpgradeButton upgradeButton : upgradeButtons) {
			upgradeButton.render(sb);
		}
	}

	public void render(ShapeRenderer sr) {

		sr.rect(boundaryRect.x, boundaryRect.y, boundaryRect.width, boundaryRect.height);
		for (OButton button : menuButtons) {
			button.render(sr);
		}
		for (TowerButton menuItem : towerButtons) {
			menuItem.render(sr);
		}
		for (UpgradeButton upgradeButton : upgradeButtons) {
			upgradeButton.render(sr);
		}
	}

	public void update(float deltaTime) {

		for (OButton button : menuButtons) {
			button.update(deltaTime);
		}
		for (TowerButton menuItem : towerButtons) {
			menuItem.update(deltaTime);
		}
		for (UpgradeButton upgradeButton : upgradeButtons) {
			upgradeButton.update(deltaTime);
		}
	}

	public void updateInputs(float x, float y) {

		for (OButton button : menuButtons) {
			button.updateInputs(x, y);
		}
		for (TowerButton menuItem : towerButtons) {
			menuItem.updateInputs(x, y);
		}
		for (UpgradeButton upgradeButton : upgradeButtons) {
			upgradeButton.updateInputs(x, y);
		}
	}

	public void touchDown(float x, float y) {

		for (TowerButton menuItem : towerButtons) {

			if (menuItem.getBoundRect().contains(x, y)) {
				selectedMenuItem = menuItem;
				selectedMenuItem.touchDown(x, y);
			}
		}

		for (OButton button : menuButtons) {

			if (button.getBoundRect().contains(x, y)) {
				selectedMenuButton = button;
				selectedMenuButton.touchDown(x, y);
			}

		}
		for (UpgradeButton upgradeButton : upgradeButtons) {
			if (upgradeButton.getBoundRect().contains(x, y)) {
				selectedUpgradeButton = upgradeButton;
				selectedUpgradeButton.touchDown(x, y);
			}
		}

	}

	public void touchRelease(float x, float y) {

		if (selectedMenuItem != null) {
			selectedMenuItem.touchRelease(x, y);
			selectedMenuItem = null;
		}

		if (selectedMenuButton != null) {
			selectedMenuButton.touchRelease(x, y);
			selectedMenuButton = null;
		}
		if (selectedUpgradeButton != null) {
			selectedUpgradeButton.touchRelease(x, y);
			selectedUpgradeButton = null;
		}

	}

	public Rectangle getBoundaryRect() {
		return boundaryRect;
	}

}
