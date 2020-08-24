package com.javakaian.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.javakaian.game.map.GameConstants;
import com.javakaian.game.map.GameUtils;

public class SplashState {

	private SpriteBatch sb;
	private OrthographicCamera camera;
	private BitmapFont bitmapFont;

	public SplashState() {

		sb = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(true, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
		sb.setProjectionMatrix(camera.combined);
		bitmapFont = GameUtils.generateBitmapFont(70, Color.WHITE);
	}

	public void render() {

		System.out.println("splash render");
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		GameUtils.renderCenter("SPLASH SCREEN", sb, bitmapFont);
		sb.end();
	}

}
