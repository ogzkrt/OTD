package com.javakaian.game.buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PropertyButton extends OButton {

	private int money;
	private int price;

	public PropertyButton(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public void render(SpriteBatch sb) {
		super.render(sb);
	}

	public void update(float deltaTime) {
		super.update(deltaTime);
	}

	public void updateInputs(float x, float y) {
	}

	public void touchRelease(float x, float y) {
		if (enable) {
			isSelected = false;
			buttonListener.touchRelease(x, y);
		}
	}

	public void touchDown(float x, float y) {
		if (enable) {
			isSelected = true;
			buttonListener.touchDown(x, y);
		}
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
