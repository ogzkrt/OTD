package com.javakaian.game.ui.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.ui.buttons.OButton;

public class MenuItem extends OButton {

	private Vector2 draggedCoord;

	private boolean isDragged = false;

	private int money;
	private int price;

	public MenuItem(float x, float y, float width, float height) {
		super(x, y, width, height);
		this.draggedCoord = new Vector2();

	}

	public void render(SpriteBatch sb) {
		super.render(sb);
		if (isDragged) {
			sb.draw(pressedSprite, draggedCoord.x - size.x / 2, draggedCoord.y - size.y / 2, size.x, size.y);
		}
	}

	public void update(float deltaTime) {
		super.update(deltaTime);
	}

	public void updateInputs(float x, float y) {
		if (isDragged & enable) {
			draggedCoord.x = x;
			draggedCoord.y = y;
			dragged(x, y);
		}
	}

	public void touchRelease(float x, float y) {
		if (enable) {
			isSelected = false;
			isDragged = false;
			buttonListener.touchRelease(x, y);
		}
	}

	public void touchDown(float x, float y) {
		if (enable) {
			isSelected = true;
			isDragged = true;
			buttonListener.touchDown(x, y);
		}
	}

	public void dragged(float x, float y) {
		buttonListener.dragged(x, y);
	}

	public void moneyChanged(int money) {
		this.money = money;
		checkEnability();
	}

	public void priceChanged(int price) {
		this.price = price;
		text = String.valueOf(price);
		checkEnability();
	}

	public void checkEnability() {
		if (price > money) {
			enable = false;
		} else {
			enable = true;
		}
	}

	public void setPrice(int price) {
		this.price = price;
		this.text = String.valueOf(price);
	}

}
