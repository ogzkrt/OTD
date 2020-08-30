package com.javakaian.game.ui.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.util.GameUtils;

public class OButton {

	protected Vector2 position;
	protected Vector2 size;
	protected Vector2 center;
	protected Sprite sprite;

	protected Sprite pressedSprite;
	protected Sprite disabledSprite;
	protected Rectangle boundRect;

	protected boolean isSelected = false;
	protected boolean enable = true;

	protected BitmapFont font;
	protected GlyphLayout glipGlyphLayout;
	protected String text = "";

	protected OButtonListener buttonListener;
	protected boolean textPositionCenter = false;

	public OButton(float x, float y, float width, float height) {
		this.position = new Vector2(x, y);
		this.size = new Vector2(width, height);
		this.boundRect = new Rectangle(x, y, this.size.x, this.size.y);
		this.font = GameUtils.generateBitmapFont(15, Color.BLACK);
		this.disabledSprite = MyAtlas.FIRE_BULLET;
		this.center = new Vector2(position.x + size.x / 2, position.y + size.y / 2);

		glipGlyphLayout = new GlyphLayout(font, text);
	}

	public void setButtonListener(OButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}

	public void render(ShapeRenderer sr) {

		sr.rect(this.position.x, this.position.y, this.size.x, this.size.y);

	}

	public void render(SpriteBatch sb) {

		if (isSelected) {
			sb.draw(pressedSprite, this.position.x, this.position.y, this.size.x, this.size.y);
		} else {
			sb.draw(sprite, this.position.x, this.position.y, this.size.x, this.size.y);
		}
		if (!enable) {
			sb.draw(disabledSprite, this.position.x, this.position.y, this.size.x, this.size.y);
		}
		if (textPositionCenter) {
			font.draw(sb, text, center.x - glipGlyphLayout.width / 2, center.y - glipGlyphLayout.height / 2);
		} else {
			font.draw(sb, text, position.x, position.y - glipGlyphLayout.height);
		}
	}

	public void updateInputs(float x, float y) {

	}

	public void update(float deltaTime) {

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

	public void setFont(BitmapFont font) {
		this.font = font;
	}

	public void setIcon(Sprite sprite) {
		this.sprite = sprite;
	}

	public void setPressedIcon(Sprite spriteSelected) {
		this.pressedSprite = spriteSelected;
	}

	public void setDisabledSprite(Sprite disabledSprite) {
		this.disabledSprite = disabledSprite;
	}

	public Rectangle getBoundRect() {
		return boundRect;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setTextPositionCenter(boolean textPositionCenter) {
		this.textPositionCenter = textPositionCenter;
	}

	public void setText(String text) {
		this.text = text;
		glipGlyphLayout = new GlyphLayout(font, text);
	}

}
