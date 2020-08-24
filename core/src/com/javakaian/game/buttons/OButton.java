package com.javakaian.game.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.map.GameUtils;
import com.javakaian.game.resources.MyAtlas;

public class OButton {

	private Vector2 position;
	private Vector2 size;
	private Vector2 draggedCoord;
	private Sprite sprite;
	private Sprite pressedSprite;
	private Sprite disabledSprite;
	private Rectangle boundRect;

	private boolean isSelected = false;
	private boolean isDragged = false;
	private boolean draggable = false;
	private boolean enable = true;

	private BitmapFont font;
	private String text = "";
	private OButtonListener buttonListener;

	private boolean enableDisableFlag = true;

	private int money;
	private int price;

	public OButton(float x, float y, float width, float height) {
		this.position = new Vector2(x, y);
		this.size = new Vector2(width, height);
		this.boundRect = new Rectangle(x, y, this.size.x, this.size.y);
		this.font = GameUtils.generateBitmapFont(15, Color.BLACK);
		this.draggedCoord = new Vector2();
		this.draggable = false;
		this.disabledSprite = MyAtlas.FIRE_BULLET;

	}

	public void setDraggable(boolean draggable) {
		this.draggable = draggable;
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
		font.draw(sb, text, position.x, position.y);
		if (isDragged && draggable) {
			sb.draw(pressedSprite, draggedCoord.x - size.x / 2, draggedCoord.y - size.y / 2, size.x, size.y);
		}
		if (!enable && enableDisableFlag) {
			sb.draw(disabledSprite, this.position.x, this.position.y, this.size.x, this.size.y);
		}
	}

	public void update(float deltaTime) {

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

	public void moneyChanged(int money) {
		this.money = money;
		if (enableDisableFlag) {
			checkEnability();
		}
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

	public void dragged(float x, float y) {
		buttonListener.dragged(x, y);
	}

	public void setEnableDisableFlag(boolean enableDisableFlag) {
		this.enableDisableFlag = enableDisableFlag;
	}

	public void setPrice(int price) {
		this.price = price;
		this.text = String.valueOf(price);
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

	public void setText(String text) {

	}
}
