package com.javakaian.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.buttons.OButton;
import com.javakaian.game.buttons.OButtonListener;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.util.GameConstants;
import com.javakaian.game.util.GameUtils;

import java.util.ArrayList;
import java.util.List;

public class PauseState extends State {

    private OButton btnResume;
    private OButton btnRestart;
    private OButton btnOptions;
    private final List<OButton> buttons;

    public PauseState(StateController stateController) {
        super(stateController);

        bitmapFont = GameUtils.generateBitmapFont(80, Color.WHITE);
        String stateName = "Paused Menu";
        glyphLayout.setText(bitmapFont, stateName);

        buttons = new ArrayList<>();
        initButtons();
        setListeners();
        buttons.add(btnRestart);
        buttons.add(btnResume);
        buttons.add(btnOptions);

    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        super.render(sb,sr);
        float red = 50f;
        float green = 63f;
        float blue = 94f;

        Gdx.gl.glClearColor(red / 255f, green / 255f, blue / 255f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        GameUtils.render("PLANE DEFENCE", sb, bitmapFont, GameConstants.SCREEN_WIDTH / 2,
                GameConstants.SCREEN_HEIGHT * 0.3f);
        buttons.forEach(b -> b.render(sb));
        sb.end();
    }

    @Override
    public void update(float deltaTime) {
        buttons.forEach(b -> b.update(deltaTime));
    }

    private void initButtons() {

        float positionX = GameConstants.GRID_WIDTH * 4;
        float positionY = GameConstants.GRID_HEIGHT * 5;
        float width = GameConstants.GRID_WIDTH * 1.5f;
        float height = GameConstants.GRID_HEIGHT * 1.5f;

        float space = GameConstants.GRID_WIDTH * 3.0f;

        btnRestart = new OButton(positionX, positionY, width, height);
        btnRestart.setIcon(MyAtlas.REPLAY_BUTTON);
        btnRestart.setPressedIcon(MyAtlas.REPLAY_BUTTON_PRESSED);

        positionX += space;

        btnResume = new OButton(positionX, positionY, width, height);
        btnResume.setIcon(MyAtlas.MENU_RESUME);
        btnResume.setPressedIcon(MyAtlas.MENU_RESUME_PRESSED);

        positionX += space;

        btnOptions = new OButton(positionX, positionY, width, height);
        btnOptions.setIcon(MyAtlas.EMPTY_BUTTON);
        btnOptions.setPressedIcon(MyAtlas.EMPTY_BUTTON_PRESSED);
        btnOptions.setText("Options");
        btnOptions.setTextPositionCenter(true);

    }


    @Override
    public void updateInputs(float x, float y) {
    }

    @Override
    public void touchDown(float x, float y, int pointer, int button) {
        buttons.stream()
                .filter(b -> b.getBoundRect().contains(x, y))
                .findFirst()
                .ifPresent(b -> b.touchDown(x, y));
    }

    @Override
    public void touchUp(float x, float y, int pointer, int button) {
        buttons.stream()
                .filter(b -> b.getBoundRect().contains(x, y))
                .findFirst()
                .ifPresent(b -> b.touchRelease(x, y));
    }

    @Override
    public void scrolled(int amount) {

    }

    private void setListeners() {
        btnRestart.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE) {
                PlayState state = (PlayState) getStateController().getState(StateEnum.PlayState);
                getStateController().setState(StateEnum.PlayState);
                state.restart();
            }
        });
        btnResume.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE)
                getStateController().setState(StateEnum.PlayState);
        });
        btnOptions.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE)
                getStateController().setState(StateEnum.OptionState);
        });
    }
}
