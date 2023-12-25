package com.javakaian.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.javakaian.game.states.State.StateEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StateController extends InputAdapter {

    private final Map<Integer, State> stateMap;

    private State currentState;

    private final Stack<StateEnum> stateStack;

    public StateController() {

        stateMap = new HashMap<>();
        stateStack = new Stack<>();
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
        Gdx.input.setInputProcessor(this);
    }

    public void render() {
        currentState.render();
    }

    public void update(float deltaTime) {
        currentState.update(deltaTime);
        Vector2 result = unproject(Gdx.input.getX(),Gdx.input.getY());
        currentState.updateInputs(result.x,result.y);
    }

    public Map<Integer, State> getStateMap() {
        return stateMap;
    }

    public void popState() {
        stateStack.pop();
    }

    public StateEnum peek() {
        return stateStack.peek();
    }

    private Vector2 unproject(int x, int y){
        final OrthographicCamera c = currentState.camera;
        Vector3 r = c.unproject(new Vector3(x,y,1));
        return new Vector2(r.x,r.y);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        final Vector2 result = unproject(screenX,screenY);
        currentState.touchDown(result.x,result.y,pointer,button);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        final Vector2 result = unproject(screenX,screenY);
        currentState.touchUp(result.x,result.y,pointer,button);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        currentState.scrolled(amount);
        return false;
    }
}
