package com.javakaian.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        super.render(sb,sr);
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
    }

    private void initButtons() {

        float positionX = GameConstants.GRID_WIDTH * 4f;
        float positionY = GameConstants.GRID_HEIGHT * 5;
        float width = GameConstants.GRID_WIDTH * 1.5f;
        float height = GameConstants.GRID_HEIGHT * 1.5f;

        float space = GameConstants.GRID_WIDTH * 3.0f;

        btnPlay = new OButton(positionX, positionY, width, height);
        btnPlay.setIcon(MyAtlas.MENU_PLAY);

        positionX += space;
        btnOptions = new OButton(positionX, positionY, width, height);
        btnOptions.setIcon(MyAtlas.GENERIC_BUTTON);
        btnOptions.setText("OPTIONS");
        btnOptions.setSetTextCenter(true);

        positionX += space;
        btnCredits = new OButton(positionX, positionY, width, height);
        btnCredits.setIcon(MyAtlas.GENERIC_BUTTON);
        btnCredits.setText("CREDITS");
        btnCredits.setSetTextCenter(true);

    }


    private void setListeners() {
        btnPlay.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE) {
                getStateController().setState(StateEnum.PlayState);
                MusicHandler.playBackgroundMusic();
                MusicHandler.stopMenuMusic();
            }
        });
        btnOptions.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE) {
                getStateController().setState(StateEnum.OptionState);
            }
        });

        btnCredits.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE) {
                getStateController().setState(StateEnum.CreditsState);
            }
        });

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

}
