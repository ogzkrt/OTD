package com.javakaian.game.ui.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class OToggleButton extends OButton {

	private OToggleButtonListener buttonListener;

	public OToggleButton(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	@Override
	public void render(ShapeRenderer sr) {
		super.render(sr);
	}

	@Override
	public void render(SpriteBatch sb) {
		super.render(sb);
	}

	public void setToggleListener(OToggleButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}

	@Override
	public void touchDown(float x, float y) {

	}

	@Override
	public void touchRelease(float x, float y) {
		setSelected(!isSelected());
		buttonListener.toggled(isSelected());
	}

	@Override
	public void setPressedIcon(Sprite spriteSelected) {
		super.setPressedIcon(spriteSelected);
	}

	@Override
	public void setIcon(Sprite sprite) {
		super.setIcon(sprite);
	}
}