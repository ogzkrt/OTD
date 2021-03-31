package com.javakaian.game.buttons;

public class UpgradeButton extends OButton {

	private int price;

	public UpgradeButton(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public void moneyChanged(int money) {
		if (price > money) {
			enable = false;
		} else {
			enable = true;
		}
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
