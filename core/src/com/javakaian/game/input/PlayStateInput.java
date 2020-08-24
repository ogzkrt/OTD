package com.javakaian.game.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.javakaian.game.states.PlayState;
import com.javakaian.game.states.StateEnum;

public class PlayStateInput extends InputAdapter {

	private PlayState state;

	public PlayStateInput(PlayState state) {

		this.state = state;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		OrthographicCamera camera = state.getCamera();
		Vector3 unprojected = camera.unproject(new Vector3(screenX, screenY, 1));
		state.touchDown(unprojected.x, unprojected.y);
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		OrthographicCamera camera = state.getCamera();
		Vector3 unprojected = camera.unproject(new Vector3(screenX, screenY, 1));
		state.touchRelease(unprojected.x, unprojected.y);
		return super.touchUp(screenX, screenY, pointer, button);
	}

	@Override
	public boolean keyDown(int keycode) {

		if (keycode == Keys.SPACE) {
			state.getStateController().setState(StateEnum.GameOver);
		}
		return super.keyDown(keycode);
	}

}
