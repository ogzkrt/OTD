package com.javakaian.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.javakaian.game.buttons.OButton;
import com.javakaian.game.buttons.OButtonListener;
import com.javakaian.game.input.PauseStateInput;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.util.GameConstants;
import com.javakaian.game.util.GameUtils;

import java.util.ArrayList;
import java.util.List;

public class PauseState extends State {

    private OButton btnResume;
    private OButton btnRestart;
    private OButton btnOptions;

    private OButton selectedButton;

    private final List<OButton> menuItems;

    public PauseState(StateController stateController) {
        super(stateController);

        inputProcessor = new PauseStateInput(this);

        bitmapFont = GameUtils.generateBitmapFont(80, Color.WHITE);

        String stateName = "Main Menu";
        glipLayout.setText(bitmapFont, stateName);

        menuItems = new ArrayList<>();

        initButtons();
        setListeners();

        selectedButton = null;

        menuItems.add(btnRestart);
        menuItems.add(btnResume);
        menuItems.add(btnOptions);

    }

    @Override
    public void render() {

        float red = 50f;
        float green = 63f;
        float blue = 94f;

        Gdx.gl.glClearColor(red / 255f, green / 255f, blue / 255f, 0.5f);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sr.begin(ShapeType.Line);

        sr.end();

        sb.begin();

        GameUtils.render("PLANE DEFENCE", sb, bitmapFont, GameConstants.SCREEN_WIDTH / 2,
                GameConstants.SCREEN_HEIGHT * 0.3f);

        for (OButton oButton : menuItems) {
            oButton.render(sb);
        }

        sb.end();

    }

    @Override
    public void update(float deltaTime) {

        for (OButton oButton : menuItems) {
            oButton.update(deltaTime);
        }

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

    public void touchDown(float x, float y) {

        for (OButton oButton : menuItems) {
            if (oButton.getBoundRect().contains(x, y)) {

                selectedButton = oButton;
                selectedButton.touchDown(x, y);

            }
        }

    }

    public void touchRelease(float x, float y) {

        if (selectedButton != null) {
            selectedButton.touchRelease(x, y);
        }

    }

    private void setListeners() {

        btnRestart.setButtonListener(new OButtonListener() {

            @Override
            public void touchRelease(float x, float y) {
                // TODO Auto-generated method stub
                if (btnRestart.getBoundRect().contains(x, y)) {

                    PlayState state = (PlayState) getStateController().getStateMap().get(StateEnum.PlayState.ordinal());
                    if (state == null) {
                        System.out.println("nulll");
                    } else {
                        state.restart();
                        getStateController().setState(StateEnum.PlayState);
                    }

                }
            }

            @Override
            public void touchDown(float x, float y) {
            }

            @Override
            public void dragged(float x, float y) {

            }
        });

        btnResume.setButtonListener(new OButtonListener() {

            @Override
            public void touchRelease(float x, float y) {

                if (btnResume.getBoundRect().contains(x, y)) {
                    getStateController().setState(StateEnum.PlayState);
                }
            }

            @Override
            public void touchDown(float x, float y) {
            }

            @Override
            public void dragged(float x, float y) {

            }
        });

        btnOptions.setButtonListener(new OButtonListener() {

            @Override
            public void touchRelease(float x, float y) {
                if (btnOptions.getBoundRect().contains(x, y)) {

                    getStateController().setState(StateEnum.OptionState);
                }
            }

            @Override
            public void touchDown(float x, float y) {

            }

            @Override
            public void dragged(float x, float y) {

            }
        });

    }

    @Override
    public void updateInputs(float x, float y) {
        // TODO Auto-generated method stub

    }

}
