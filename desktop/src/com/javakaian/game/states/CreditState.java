package com.javakaian.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.buttons.OButton;
import com.javakaian.game.buttons.OButtonListener;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.util.GameConstants;
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
        bitmapFont = GameUtils.generateBitmapFont(100, Color.WHITE);
        glyphLayout.setText(bitmapFont, stateName);

        buttons = new ArrayList<>();
        initButtons();
        setListeners();
        buttons.add(btnBack);

        textFont = GameUtils.generateBitmapFont(30, Color.GRAY);
    }

    @Override
    public void render(SpriteBatch sb,ShapeRenderer sr) {
        super.render(sb,sr);
        float red = 50f;
        float green = 63f;
        float blue = 94f;

        Gdx.gl.glClearColor(red / 255f, green / 255f, blue / 255f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        GameUtils.render(stateName, sb, bitmapFont, GameConstants.SCREEN_WIDTH / 2, GameConstants.SCREEN_HEIGHT * 0.3f);
        GameUtils.renderCenter(stateName, sb, bitmapFont);

        float posY = GameConstants.SCREEN_HEIGHT / 2.4f;
        float marginY = GameConstants.GRID_HEIGHT / 1.5f;

        GameUtils.renderCenterWithY("YIGIT KILIC - GRAPHIC DESIGNER", sb, textFont, posY);
        posY += marginY;
        GameUtils.renderCenterWithY("https://www.flaticon.com/authors/freepik", sb, textFont, posY);
        posY += marginY;
        GameUtils.renderCenterWithY("JAVAKAIAN - DEVELOPER", sb, textFont, posY);

        buttons.forEach(b->b.render(sb));
        sb.end();

    }

    @Override
    public void update(float deltaTime) {
    }

    private void initButtons() {

        float positionX = GameConstants.GRID_WIDTH * 4.5f;
        float positionY = GameConstants.GRID_HEIGHT * 5;
        float width = GameConstants.GRID_WIDTH * 1.5f;
        float height = GameConstants.GRID_HEIGHT * 1.5f;

        float space = GameConstants.GRID_WIDTH * 3.0f;

        btnBack = new OButton(positionX + space, positionY + GameConstants.GRID_HEIGHT * 2, width, height);
        btnBack.setIcon(MyAtlas.GENERIC_BUTTON);
        btnBack.setText("BACK");
        btnBack.setSetTextCenter(true);

    }

    @Override
    public void updateInputs(float x, float y) {
    }

    @Override
    public void touchDown(float x, float y, int pointer, int button) {
        buttons.stream()
                .filter(b->b.contains(x,y))
                .findFirst()
                .ifPresent(b->b.touchDown(x,y));
    }
    @Override
    public void touchUp(float x, float y, int pointer, int button) {
        buttons.forEach(b->b.setPressed(false));
        buttons.stream()
                .filter(b->b.contains(x,y))
                .findFirst()
                .ifPresent(b->b.touchRelease(x,y));
    }

    @Override
    public void scrolled(int amount) {
    }
    private void setListeners() {
        btnBack.setButtonListener((event,x,y)-> {
            if(event== OButtonListener.TouchEvent.RELEASE)
                getStateController().goBack();
        });
    }
}
