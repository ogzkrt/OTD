package com.javakaian.game.states;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;

public class StateController {

	private Map<Integer, State> stateMap;

	private State currentState;

	public StateController() {

		stateMap = new HashMap<Integer, State>();
	}

	public void setState(StateEnum stateEnum) {

		currentState = stateMap.get(stateEnum.ordinal());
		if (currentState == null) {
			switch (stateEnum) {
			case PlayState:
				currentState = new PlayState(this);
				break;
			case GameOver:
				currentState = new GameOverState(this);
				break;
			case MenuState:
				currentState = new MenuState(this);
				break;
			case OptionState:
				currentState = new OptionState(this);
				break;
			case LevelSelectionState:
				currentState = new LevelSelectionState(this);
				break;
			default:
				break;
			}
		}
		stateMap.put(stateEnum.ordinal(), currentState);
		Gdx.input.setInputProcessor(currentState.inputProcessor);
	}

	public void render() {

		currentState.render();
		currentState.updateInputs(Gdx.input.getX(), Gdx.input.getY());
	}

	public void update(float deltaTime) {

		currentState.update(deltaTime);
	}

}
