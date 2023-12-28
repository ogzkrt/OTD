package com.javakaian.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.ui.buttons.OButton;
import com.javakaian.game.ui.buttons.OButtonListener;
import com.javakaian.game.ui.buttons.OToggleButton;
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
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        super.render(sb,sr);
        final float red = 50f;
        final float green = 63f;
        final float blue = 94f;

        Gdx.gl.glClearColor(red / 255f, green / 255f, blue / 255f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        GameUtils.renderCenter(stateName, sb, bitmapFont);
        buttons.forEach(b -> b.render(sb));
        sb.end();

    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void updateInputs(float x, float y) {
    }

    @Override
    public void touchDown(float x, float y, int pointer, int button) {
        buttons.stream()
                .filter(b -> b.contains(x, y))
                .findFirst()
                .ifPresent(b -> b.touchDown(x, y));
    }

    @Override
    public void touchUp(float x, float y, int pointer, int button) {
        buttons.forEach(b->b.setPressed(false));
        buttons.stream()
                .filter(b -> b.contains(x, y))
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
        btnSound.setIcon(MyAtlas.SOUND_ON);
        btnSound.setToggledIcon(MyAtlas.SOUND_OFF);

        positionX += space;
        btnMusic = new OToggleButton(positionX, positionY, width, height);
        btnMusic.setIcon(MyAtlas.MUSIC_ON);
        btnMusic.setToggledIcon(MyAtlas.MUSIC_OFF);

        positionX += space;
        btnBack = new OButton(positionX, positionY, width, height);
        btnBack.setIcon(MyAtlas.GENERIC_BUTTON);
        btnBack.setText("BACK");
        btnBack.setSetTextCenter(true);
    }

    private void setListeners() {
        btnSound.setToggleListener(isToggled -> MusicHandler.setSoundOnOff(!isToggled));
        btnMusic.setToggleListener(isToggled -> MusicHandler.setMusicOnOff(!isToggled));
        btnBack.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE)
                getStateController().goBack();
        });

    }

}
