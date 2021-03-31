package com.javakaian.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface Entity {

	public void render(ShapeRenderer sr);

	public void render(SpriteBatch sb);

	public void update(float deltaTime);

}
