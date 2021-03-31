package com.javakaian.game.states;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.javakaian.game.states.State.StateEnum;

public class StateController {

	private Map<Integer, State> stateMap;

	private State currentState;

	private Stack<StateEnum> stateStack;

	public StateController() {

		stateMap = new HashMap<Integer, State>();
		stateStack = new Stack<StateEnum>();
	}

	public void setState(StateEnum stateEnum) {

		currentState = stateMap.get(stateEnum.ordinal());
		if (currentState == null) {
			switch (stateEnum) {
			case PlayState:
				currentState = new PlayState(this);
				break;
			case GameOverState:
				currentState = new GameOverState(this);
				break;
			case MenuState:
				currentState = new MenuState(this);
				break;
			case OptionState:
				currentState = new OptionsState(this);
				break;
			case CreditsState:
				currentState = new CreditState(this);
				break;
			case PauseState:
				currentState = new PauseState(this);
				break;

			default:
				break;
			}
		}
		stateMap.put(stateEnum.ordinal(), currentState);
		stateStack.push(stateEnum);
		Gdx.input.setInputProcessor(currentState.inputProcessor);
	}

	public void render() {

		currentState.render();
		currentState.updateInputs(Gdx.input.getX(), Gdx.input.getY());
	}

	public void update(float deltaTime) {

		currentState.update(deltaTime);
	}

	public Map<Integer, State> getStateMap() {
		return stateMap;
	}

	public StateEnum popState() {
		return stateStack.pop();
	}

	public StateEnum peek() {
		return stateStack.peek();
	}

}
