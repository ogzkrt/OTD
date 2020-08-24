package com.javakaian.game.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.javakaian.game.states.LevelSelectionState;
import com.javakaian.game.states.StateEnum;

public class LevelSelectionInput extends InputAdapter {

	private LevelSelectionState state;

	public LevelSelectionInput(LevelSelectionState state) {

		this.state = state;
	}

	@Override
	public boolean keyDown(int keycode) {

		if (keycode == Keys.SPACE) {
			state.getStateController().setState(StateEnum.PlayState);

		}
		return super.keyDown(keycode);
	}

}
