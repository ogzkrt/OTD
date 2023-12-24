package com.javakaian.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.javakaian.game.buttons.OButton;
import com.javakaian.game.buttons.OButtonListener;
import com.javakaian.game.buttons.OToggleButton;
import com.javakaian.game.buttons.OToggleButtonListener;
import com.javakaian.game.input.OptionStateInput;
import com.javakaian.game.resources.MusicHandler;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.util.GameConstants;
import com.javakaian.game.util.GameUtils;

import java.util.ArrayList;
import java.util.List;

public class OptionsState extends State {

    private final String stateName = "OPTION MENU";

    private OToggleButton btnMusic;
    private OToggleButton btnSound;
    private OButton btnBack;

    private OButton selectedButton;

    private final List<OButton> menuItems;

    public OptionsState(StateController stateController) {
        super(stateController);

        inputProcessor = new OptionStateInput(this);

        glipLayout.setText(bitmapFont, stateName);

        menuItems = new ArrayList<>();

        initButtons();
        setListeners();

        selectedButton = null;

        menuItems.add(btnSound);
        menuItems.add(btnMusic);
        menuItems.add(btnBack);

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

        GameUtils.renderCenter(stateName, sb, bitmapFont);
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

    @Override
    public void updateInputs(float x, float y) {

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

    private void initButtons() {

        float positionX = GameConstants.GRID_WIDTH * 4f;
        float positionY = GameConstants.GRID_HEIGHT * 5;
        float width = GameConstants.GRID_WIDTH * 1.5f;
        float height = GameConstants.GRID_HEIGHT * 1.5f;

        float space = GameConstants.GRID_WIDTH * 3.0f;

        btnSound = new OToggleButton(positionX, positionY, width, height);
        btnSound.setIcon(MyAtlas.MENU_SOUND_ON);
        btnSound.setPressedIcon(MyAtlas.MENU_SOUND_OFF);

        positionX += space;
        btnMusic = new OToggleButton(positionX, positionY, width, height);
        btnMusic.setIcon(MyAtlas.MENU_MUSIC_ON);
        btnMusic.setPressedIcon(MyAtlas.MENU_MUSIC_OFF);

        positionX += space;
        btnBack = new OButton(positionX, positionY, width, height);
        btnBack.setIcon(MyAtlas.EMPTY_BUTTON);
        btnBack.setPressedIcon(MyAtlas.EMPTY_BUTTON_PRESSED);
        btnBack.setText("BACK");
        btnBack.setTextPositionCenter(true);

    }

    private void setListeners() {

        btnSound.setToggleListener(isToggled -> MusicHandler.setSoundOnOff(!isToggled));

        btnMusic.setToggleListener(isToggled -> MusicHandler.setMusicOnOff(!isToggled));

        btnBack.setButtonListener(new OButtonListener() {

            @Override
            public void touchRelease(float x, float y) {
                if (btnBack.getBoundRect().contains(x, y)) {

                    getStateController().popState();
                    StateEnum state = getStateController().peek();
                    getStateController().setState(state);

                }
            }

            @Override
            public void touchDown(float x, float y) {
                // TODO Auto-generated method stub
                System.out.println("Touched");
            }

            @Override
            public void dragged(float x, float y) {
                // TODO Auto-generated method stub

            }
        });

    }

}
