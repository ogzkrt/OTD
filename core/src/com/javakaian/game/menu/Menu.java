package com.javakaian.game.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.buttons.OButton;
import com.javakaian.game.buttons.PropertyButton;

public class Menu {

	protected List<MenuItem> menuItems;
	protected List<OButton> menuButtons;
	protected List<PropertyButton> propertyButtons;

	protected MenuItem selectedMenuItem = null;
	protected OButton selectedMenuButton = null;
	protected PropertyButton selectedPropertyButton = null;

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

		menuItems = new ArrayList<MenuItem>();
		menuButtons = new ArrayList<OButton>();
		propertyButtons = new ArrayList<PropertyButton>();
	}

	public void render(SpriteBatch sb) {

		for (OButton button : menuButtons) {
			button.render(sb);
		}
		for (MenuItem menuItem : menuItems) {
			menuItem.render(sb);
		}
		for (PropertyButton propertyButton : propertyButtons) {
			propertyButton.render(sb);
		}
	}

	public void render(ShapeRenderer sr) {

		sr.rect(boundaryRect.x, boundaryRect.y, boundaryRect.width, boundaryRect.height);
		for (OButton button : menuButtons) {
			button.render(sr);
		}
		for (MenuItem menuItem : menuItems) {
			menuItem.render(sr);
		}
		for (PropertyButton propertyButton : propertyButtons) {
			propertyButton.render(sr);
		}
	}

	public void update(float deltaTime) {

		for (OButton button : menuButtons) {
			button.update(deltaTime);
		}
		for (MenuItem menuItem : menuItems) {
			menuItem.update(deltaTime);
		}
		for (PropertyButton propertyButton : propertyButtons) {
			propertyButton.update(deltaTime);
		}

	}

	public void updateInputs(float x, float y) {

		for (OButton button : menuButtons) {
			button.updateInputs(x, y);
		}
		for (MenuItem menuItem : menuItems) {
			menuItem.updateInputs(x, y);
		}
		for (PropertyButton propertyButton : propertyButtons) {
			propertyButton.updateInputs(x, y);
		}
	}

	public void touchDown(float x, float y) {

		for (MenuItem menuItem : menuItems) {

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
		for (PropertyButton propertyButton : propertyButtons) {
			if (propertyButton.getBoundRect().contains(x, y)) {
				selectedPropertyButton = propertyButton;
				selectedPropertyButton.touchDown(x, y);
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
		if (selectedPropertyButton != null) {
			selectedPropertyButton.touchRelease(x, y);
			selectedPropertyButton = null;
		}

	}

	public Rectangle getBoundaryRect() {
		return boundaryRect;
	}

}
