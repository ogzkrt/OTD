package com.javakaian.game.states;

import static com.javakaian.game.util.GameConstants.ALPHA;
import static com.javakaian.game.util.GameConstants.BLUE;
import static com.javakaian.game.util.GameConstants.GREEN;
import static com.javakaian.game.util.GameConstants.GRID_HEIGHT;
import static com.javakaian.game.util.GameConstants.GRID_WIDTH;
import static com.javakaian.game.util.GameConstants.RED;
import static com.javakaian.game.util.GameConstants.SCREEN_HEIGHT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.ui.buttons.ButtonFactory;
import com.javakaian.game.ui.buttons.OButton;
import com.javakaian.game.ui.buttons.OButtonListener;
import com.javakaian.game.util.GameUtils;

import java.util.ArrayList;
import java.util.List;

public class CreditState extends State {

    private final String stateName = "CREDIT STATE";

    private OButton btnBack;
    private final List<OButton> buttons;
    private final BitmapFont textFont;

    public CreditState(StateController stateController) {
        super(stateController);
        glyphLayout.setText(bitmapFont, stateName);

        buttons = new ArrayList<>();
        initButtons();
        setListeners();
        textFont = GameUtils.generateBitmapFont(30, Color.GRAY);
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        super.render(sb, sr);

        Gdx.gl.glClearColor(RED, GREEN, BLUE, ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        GameUtils.renderCenter(stateName, sb, bitmapFont);

        float posY = SCREEN_HEIGHT / 2.4f;
        float yOffset = GRID_HEIGHT / 1.5f;

        GameUtils.renderCenterWithY("I want to thank, my best friend" +
                " for the graphics", sb, textFont, posY);
        posY += yOffset;
        GameUtils.renderCenterWithY("Also see https://www.flaticon.com/authors/freepik" +
                " for the icons", sb, textFont, posY);

        buttons.forEach(b -> b.render(sb));
        sb.end();

    }

    @Override
    public void update(float deltaTime) {
    }

    private void initButtons() {
        float positionX = GRID_WIDTH * 7.5f;
        float positionY = GRID_HEIGHT * 7;
        final ButtonFactory bf = new ButtonFactory(GRID_WIDTH * 1.5f, GRID_HEIGHT * 1.5f);
        btnBack = bf.createOButton(positionX, positionY, "BACK", MyAtlas.GENERIC_BUTTON, true);
        buttons.add(btnBack);
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
        buttons.forEach(b -> b.setPressed(false));
        buttons.stream()
                .filter(b -> b.contains(x, y))
                .findFirst()
                .ifPresent(b -> b.touchRelease(x, y));
    }

    @Override
    public void scrolled(int amount) {
    }

    private void setListeners() {
        btnBack.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE)
                getStateController().goBack();
        });
    }
}
