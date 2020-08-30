package com.javakaian.game.entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.javakaian.game.resources.MyAtlas;

public class Bounty extends GameObject {

	private float duration = 1.0f; // 2 second
	private float animTime;
	private boolean disposable = false;
	private Animation<TextureRegion> goldAnimation;
	private float stateTime = 0;

	public Bounty(float x, float y, float width, float height) {
		super(x, y, width, height);

		goldAnimation = new Animation<TextureRegion>(0.1f, MyAtlas.coingRegions, PlayMode.LOOP);

	}

	@Override
	public void render(SpriteBatch sb) {

		TextureRegion tr = goldAnimation.getKeyFrame(stateTime);
		sb.draw(tr, position.x, position.y, size.x, size.y);

	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		stateTime += deltaTime;
		animTime += deltaTime;
		if (animTime >= duration) {
			disposable = true;
		}
	}

	public boolean isDisposable() {
		return disposable;
	}

}
