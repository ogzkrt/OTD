package com.javakaian.game.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class HealthBar extends GameObject {

	private float health;
	private float remaniningHealth;

	private float timer = 0;

	public HealthBar(float x, float y, float width, float height, float fullHealth) {
		super(x, y, width, height);

		this.health = fullHealth;
		this.remaniningHealth = health;

	}

	public void setRemaniningHealth(float remaniningHealth) {
		this.remaniningHealth = remaniningHealth;
	}

	@Override
	public void render(ShapeRenderer sr) {

		float x = (remaniningHealth * size.x) / health;

		sr.end();
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.RED);
		sr.rect(this.position.x + x, this.position.y, size.x - x, this.size.y);
		sr.setColor(Color.GREEN);
		sr.rect(this.position.x, this.position.y, x, this.size.y);
		sr.end();
		sr.begin(ShapeType.Line);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		timer += deltaTime;
		if (timer > 1.0f) {
			timer = 0;

		}
	}
}
