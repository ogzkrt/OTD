package com.javakaian.game.states;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.util.GameConstants;
import com.javakaian.game.util.GameUtils;

public abstract class State {

    protected StateController stateController;
    protected BitmapFont bitmapFont;
    protected GlyphLayout glyphLayout;
    protected OrthographicCamera camera;

    public State(StateController stateController) {
        this.stateController = stateController;
        bitmapFont = GameUtils.generateBitmapFont(100, Color.WHITE);
        glyphLayout = new GlyphLayout();
        camera = new OrthographicCamera();
        camera.setToOrtho(true, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
    }
    public StateController getStateController() {
        return stateController;
    }

    /**
     * Each state should override this method.
     * I'm not making it abstract because I want
     * to update camera and projection matrix in every state
     * using `super.render(sb,sr)`
     * */
    public void render(SpriteBatch sb,ShapeRenderer sr){
        camera.update();
        sb.setProjectionMatrix(camera.combined);
        sr.setProjectionMatrix(camera.combined);
    }
    public abstract void update(float deltaTime);

    public abstract void updateInputs(float x, float y);

    public abstract void touchDown(float x, float y, int pointer, int button);

    public abstract void touchUp(float x, float y, int pointer, int button);

    public abstract void scrolled(int amount);

    public enum StateEnum {
        PlayState, PauseState, MenuState, GameOverState, CreditsState, OptionState
    }

}
