package com.javakaian.game.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.buttons.Menu;
import com.javakaian.game.map.GameUtils;
import com.javakaian.game.resources.MyAtlas;

public abstract class MenuItem {

	protected Vector2 position;
	protected Vector2 size;
	protected Rectangle boundaryRect;
	protected Sprite sprite;
	protected Sprite spriteSelected;
	protected Sprite spriteDisabled;
	protected Menu parent;
	protected BitmapFont bitmapFont;
	protected GlyphLayout glipLayout;
	protected boolean isSelected;
	protected boolean enable;
	protected int price;
	protected MenuItemType type;

	public MenuItem(float x, float y, float width, float height, Menu menu) {
		this.parent = menu;
		this.position = new Vector2(x, y);
		this.size = new Vector2(width, height);
		// room for a improvement
		Vector2 temp = new Vector2(menu.getBoundaryRect().x, menu.getBoundaryRect().y);
		this.position = this.position.add(temp);
		this.boundaryRect = new Rectangle(this.position.x, this.position.y, this.size.x, this.size.y);

		this.isSelected = false;

		this.bitmapFont = GameUtils.generateBitmapFont(18, Color.RED);
		// FOR NOW ASSIGN DEFAULT TYPE
		enable = false;
		this.type = MenuItemType.DEFAULT;
		this.price = 50;
		this.spriteDisabled = MyAtlas.GRASS;
	}

	public void render(SpriteBatch sb) {

		if (enable) {
			if (isSelected) {
				sb.draw(spriteSelected, position.x, position.y, size.x, size.y);
			} else {
				sb.draw(sprite, position.x, position.y, size.x, size.y);
			}
		} else {
			sb.draw(spriteDisabled, position.x, position.y, size.x, size.y);
		}
		bitmapFont.draw(sb, String.valueOf(price), position.x, position.y);

	}

	public void render(ShapeRenderer sr) {
		sr.rect(this.position.x, this.position.y, this.size.x, this.size.y);
	};

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Rectangle getBoundRect() {
		return this.boundaryRect;
	}

	public MenuItemType getType() {
		return type;
	}

	public abstract void touchDown(float x, float y);

	public abstract void touchRelease(float x, float y);

}
