package com.javakaian.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.javakaian.game.input.PlayStateInput;
import com.javakaian.game.level.Level;
import com.javakaian.game.resources.MyAtlas;

public class PlayState extends State {

	private Level level;
	private boolean paused = false;

	public PlayState(StateController stateController) {
		super(stateController);
		level = new Level(this, bitmapFont, glipLayout);
		inputProcessor = new PlayStateInput(this);

		MyAtlas.CURRENT_MUSIC = MyAtlas.GAME_PLAY_MUSIC;
		// MyAtlas.CURRENT_MUSIC.play();
	}

	@Override
	public void render() {
		sb.begin();
		level.render(sb);
		sb.end();

		sr.begin(ShapeType.Line);
		level.render(sr);
		sr.end();
	}

	@Override
	public void update(float deltaTime) {

		if (!paused) {
			level.update(Gdx.graphics.getDeltaTime());
		}
	}

	public void touchDown(float x, float y) {

		this.level.touchDown(x, y);
	}

	public void touchRelease(float x, float y) {
		this.level.touchRelease(x, y);
	}

	public void gameOver() {

		level.restart();
		stateController.setState(StateEnum.GameOverState);
	}

	public void pause() {
		paused = true;
	}

	public void resume() {
		paused = false;
	}

	@Override
	public void updateInputs(float x, float y) {

		Vector3 unprojected = camera.unproject(new Vector3(x, y, 1));
		level.updateInputs(unprojected.x, unprojected.y);
	}

	public void restart() {
		MyAtlas.CURRENT_MUSIC.stop();
		MyAtlas.CURRENT_MUSIC = MyAtlas.GAME_PLAY_MUSIC;
		MyAtlas.CURRENT_MUSIC.play();
		level.restart();
	}

}
