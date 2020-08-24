package com.javakaian.game.towers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

	protected Vector2 position;
	protected Vector2 size;
	protected Vector2 center;
	protected Sprite sprite;
	protected Sprite spriteSelected;
	protected boolean visible;
	protected Rectangle boundRect;
	protected boolean isSelected = false;

	public GameObject(float x, float y, float width, float height) {

		this.position = new Vector2(x, y);
		this.size = new Vector2(width, height);
		this.center = new Vector2(position.x + size.x / 2, position.y + size.y / 2);
		this.boundRect = new Rectangle(x, y, this.size.x, this.size.y);
		this.visible = true;
	}

	public void render(ShapeRenderer sr) {

		if (visible) {
			sr.rect(this.position.x, this.position.y, this.size.x, this.size.y);
		}

	}

	public void render(SpriteBatch sb) {

		if (visible) {
			if (isSelected) {
				sb.draw(spriteSelected, this.position.x, this.position.y, this.size.x, this.size.y);
			} else {
				sb.draw(sprite, this.position.x, this.position.y, this.size.x, this.size.y);
			}
		}
	}

	public void update(float deltaTime) {

		// update position
		boundRect.x = position.x;
		boundRect.y = position.y;
		boundRect.width = size.x;
		boundRect.height = size.y;

		// update center
		center.x = position.x + size.x / 2;
		center.y = position.y + size.y / 2;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public Vector2 getCenter() {
		return center;
	}

	public Rectangle getBoundRect() {
		return boundRect;
	}

}
