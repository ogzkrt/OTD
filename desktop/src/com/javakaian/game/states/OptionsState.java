package com.javakaian.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.javakaian.game.buttons.OButton;
import com.javakaian.game.buttons.OButtonListener;
import com.javakaian.game.buttons.OToggleButton;
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
    private final List<OButton> buttons;

    public OptionsState(StateController stateController) {
        super(stateController);
        glyphLayout.setText(bitmapFont, stateName);

        buttons = new ArrayList<>();
        initButtons();
        setListeners();
        buttons.add(btnSound);
        buttons.add(btnMusic);
        buttons.add(btnBack);

    }

    @Override
    public void render(SpriteBatch sb,ShapeRenderer sr) {

        final float red = 50f;
        final float green = 63f;
        final float blue = 94f;

        Gdx.gl.glClearColor(red / 255f, green / 255f, blue / 255f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        GameUtils.renderCenter(stateName, sb, bitmapFont);
        buttons.forEach(b->b.render(sb));
        sb.end();

    }

    @Override
    public void update(float deltaTime) {
        buttons.forEach(b->b.update(deltaTime));
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
                if (btnBack.getBoundRect().contains(x, y))
                    getStateController().goBack();
            }

            @Override
            public void touchDown(float x, float y) {
            }

            @Override
            public void dragged(float x, float y) {
            }
        });

    }

}
