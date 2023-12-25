package com.javakaian.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.javakaian.game.buttons.OButton;
import com.javakaian.game.buttons.OButtonListener;
import com.javakaian.game.resources.MusicHandler;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.util.GameConstants;
import com.javakaian.game.util.GameUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuState extends State {

    private final String stateName = "MAIN MENU";
    private OButton btnPlay;
    private OButton btnOptions;
    private OButton btnCredits;
    private final List<OButton> buttons;

    public MenuState(StateController stateController) {
        super(stateController);

        glyphLayout.setText(bitmapFont, stateName);

        buttons = new ArrayList<>();
        initButtons();
        setListeners();
        buttons.add(btnPlay);
        buttons.add(btnOptions);
        buttons.add(btnCredits);
    }

    @Override
    public void render() {

        float red = 50f;
        float green = 63f;
        float blue = 94f;

        Gdx.gl.glClearColor(red / 255f, green / 255f, blue / 255f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        GameUtils.renderCenter(stateName, sb, bitmapFont);
        buttons.forEach(button -> button.render(sb));
        sb.end();

    }

    @Override
    public void update(float deltaTime) {
        buttons.forEach(b -> b.update(deltaTime));
    }

    private void initButtons() {

        float positionX = GameConstants.GRID_WIDTH * 4f;
        float positionY = GameConstants.GRID_HEIGHT * 5;
        float width = GameConstants.GRID_WIDTH * 1.5f;
        float height = GameConstants.GRID_HEIGHT * 1.5f;

        float space = GameConstants.GRID_WIDTH * 3.0f;

        btnPlay = new OButton(positionX, positionY, width, height);
        btnPlay.setIcon(MyAtlas.MENU_PLAY);
        btnPlay.setPressedIcon(MyAtlas.MENU_PLAY_PRESSED);

        positionX += space;
        btnOptions = new OButton(positionX, positionY, width, height);
        btnOptions.setIcon(MyAtlas.EMPTY_BUTTON);
        btnOptions.setPressedIcon(MyAtlas.EMPTY_BUTTON_PRESSED);
        btnOptions.setText("OPTIONS");
        btnOptions.setTextPositionCenter(true);

        positionX += space;
        btnCredits = new OButton(positionX, positionY, width, height);
        btnCredits.setIcon(MyAtlas.EMPTY_BUTTON);
        btnCredits.setPressedIcon(MyAtlas.EMPTY_BUTTON_PRESSED);
        btnCredits.setText("CREDITS");
        btnCredits.setTextPositionCenter(true);

    }


    private void setListeners() {

        btnPlay.setButtonListener(new OButtonListener() {

            @Override
            public void touchRelease(float x, float y) {
                if (btnPlay.getBoundRect().contains(x, y)) {
                    getStateController().setState(StateEnum.PlayState);
                    MusicHandler.playBackgroundMusic();
                    MusicHandler.stopMenuMusic();
                }
            }

            @Override
            public void touchDown(float x, float y) {

            }

            @Override
            public void dragged(float x, float y) {}
        });

        btnOptions.setButtonListener(new OButtonListener() {
            @Override
            public void touchRelease(float x, float y) {

                if (btnOptions.getBoundRect().contains(x, y))
                    getStateController().setState(StateEnum.OptionState);
            }

            @Override
            public void touchDown(float x, float y) {
            }

            @Override
            public void dragged(float x, float y) {
            }
        });

        btnCredits.setButtonListener(new OButtonListener() {

            @Override
            public void touchRelease(float x, float y) {

                if (btnCredits.getBoundRect().contains(x, y))
                    getStateController().setState(StateEnum.CreditsState);

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
}
