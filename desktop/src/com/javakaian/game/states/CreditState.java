package com.javakaian.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.javakaian.game.buttons.OButton;
import com.javakaian.game.buttons.OButtonListener;
import com.javakaian.game.input.CreditStateInput;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.util.GameConstants;
import com.javakaian.game.util.GameUtils;

import java.util.ArrayList;
import java.util.List;

public class CreditState extends State {

    private final String stateName = "CREDIT STATE";

    private OButton btnBack;

    private OButton selectedButton;

    private final List<OButton> menuItems;

    private final BitmapFont textFont;

    public CreditState(StateController stateController) {
        super(stateController);

        inputProcessor = new CreditStateInput(this);

        bitmapFont = GameUtils.generateBitmapFont(100, Color.WHITE);

        glipLayout.setText(bitmapFont, stateName);

        menuItems = new ArrayList<>();

        initButtons();
        setListeners();

        selectedButton = null;

        menuItems.add(btnBack);

        textFont = GameUtils.generateBitmapFont(30, Color.GRAY);

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

        GameUtils.render(stateName, sb, bitmapFont, GameConstants.SCREEN_WIDTH / 2, GameConstants.SCREEN_HEIGHT * 0.3f);

        GameUtils.renderCenter(stateName, sb, bitmapFont);

        float posY = GameConstants.SCREEN_HEIGHT / 2.4f;
        float marginY = GameConstants.GRID_HEIGHT / 1.5f;

        GameUtils.renderCenterWithY("YIGIT KILIC - GRAPHIC DESIGNER", sb, textFont, posY);
        posY += marginY;
        GameUtils.renderCenterWithY("https://www.flaticon.com/authors/freepik", sb, textFont, posY);
        posY += marginY;
        GameUtils.renderCenterWithY("JAVAKAIAN - DEVELOPER", sb, textFont, posY);

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

        float positionX = GameConstants.GRID_WIDTH * 4.5f;
        float positionY = GameConstants.GRID_HEIGHT * 5;
        float width = GameConstants.GRID_WIDTH * 1.5f;
        float height = GameConstants.GRID_HEIGHT * 1.5f;

        float space = GameConstants.GRID_WIDTH * 3.0f;

        btnBack = new OButton(positionX + space, positionY + GameConstants.GRID_HEIGHT * 2, width, height);
        btnBack.setIcon(MyAtlas.EMPTY_BUTTON);
        btnBack.setPressedIcon(MyAtlas.EMPTY_BUTTON_PRESSED);
        btnBack.setText("BACK");
        btnBack.setTextPositionCenter(true);

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

        btnBack.setButtonListener(new OButtonListener() {

            @Override
            public void touchRelease(float x, float y) {
                // TODO Auto-generated method stub
                if (btnBack.getBoundRect().contains(x, y)) {
                    getStateController().setState(StateEnum.MenuState);
                }
            }

            @Override
            public void touchDown(float x, float y) {
                // TODO Auto-generated method stub

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
