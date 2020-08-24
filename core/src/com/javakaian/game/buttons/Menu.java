package com.javakaian.game.buttons;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Menu {

	protected List<OButton> menuItems;
	protected OButton selectedMenuItem = null;

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
		menuItems = new ArrayList<OButton>();
	}

	public void render(SpriteBatch sb) {

		for (OButton menuItem : menuItems) {
			menuItem.render(sb);
		}
	}

	public void render(ShapeRenderer sr) {

		sr.rect(boundaryRect.x, boundaryRect.y, boundaryRect.width, boundaryRect.height);
		for (OButton menuItem : menuItems) {
			menuItem.render(sr);
		}
	}

	public void update(float deltaTime) {
		for (OButton oButton : menuItems) {
			oButton.update(deltaTime);
		}
	}

	public void updateInputs(float x, float y) {
		for (OButton oButton : menuItems) {
			oButton.updateInputs(x, y);
		}
	}

	public void touchDown(float x, float y) {
		for (OButton menuItem : menuItems) {

			if (menuItem.getBoundRect().contains(x, y)) {
				selectedMenuItem = menuItem;
				selectedMenuItem.touchDown(x, y);
			}

		}

	}

	public void touchRelease(float x, float y) {

		if (selectedMenuItem != null) {
			selectedMenuItem.touchRelease(x, y);
			selectedMenuItem = null;
		}
	}

	public Rectangle getBoundaryRect() {
		return boundaryRect;
	}

}
