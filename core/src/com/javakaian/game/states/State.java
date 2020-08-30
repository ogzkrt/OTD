package com.javakaian.game.states;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.util.GameConstants;
import com.javakaian.game.util.GameUtils;

public abstract class State {

	protected InputProcessor inputProcessor;
	protected OrthographicCamera camera;
	protected ShapeRenderer sr;
	protected SpriteBatch sb;
	protected StateController stateController;
	protected BitmapFont bitmapFont;
	protected GlyphLayout glipLayout;

	public State(StateController stateController) {

		this.stateController = stateController;

		camera = new OrthographicCamera();

		camera.setToOrtho(true, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);

		sr = new ShapeRenderer();
		sb = new SpriteBatch();

		sr.setProjectionMatrix(camera.combined);
		sb.setProjectionMatrix(camera.combined);

		bitmapFont = GameUtils.generateBitmapFont(70, Color.WHITE);
		glipLayout = new GlyphLayout();
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public StateController getStateController() {
		return stateController;
	}

	public abstract void render();

	public abstract void update(float deltaTime);

	public abstract void updateInputs(float x, float y);

	public enum StateEnum {

		PlayState, PauseState, MenuState, GameOverState, CreditsState

	}

}
