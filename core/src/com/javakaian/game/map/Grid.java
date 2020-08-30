package com.javakaian.game.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.entity.GameObject;
import com.javakaian.game.resources.MyAtlas;

public class Grid extends GameObject {

	private EnumGridType type;

	public Grid(float x, float y, float width, float height, EnumGridType type) {
		super(x, y, width, height);
		this.type = type;
	}

	@Override
	public void render(ShapeRenderer sr) {
		sr.setColor(Color.FIREBRICK);
		super.render(sr);

	}

	@Override
	public void update(float deltaTime) {

	}

	public void render(SpriteBatch sb) {

		switch (type) {
		case TOWER:
		case LAND:
			sb.draw(MyAtlas.LAND, this.position.x, this.position.y, this.size.x, this.size.y);
			break;
		case WATER:

			break;
		case PATH:
			sb.draw(MyAtlas.PATH, this.position.x, this.position.y, this.size.x, this.size.y);
			break;
		default:
			break;
		}

	}

	public boolean contains(float x, float y) {
		return x >= position.x && x < position.x + size.x && y >= position.y && y < position.y + size.y;
	}

	public EnumGridType getType() {
		return type;
	}

	public void setType(EnumGridType type) {
		this.type = type;
	}

	public enum EnumGridType {

		PATH, WATER, PIT, LAND, TOWER

	}
}
