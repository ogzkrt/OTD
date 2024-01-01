package com.javakaian.game.ui.menu;

import static com.javakaian.game.util.GameConstants.FUNC_BUTTON_WH;
import static com.javakaian.game.util.GameConstants.GRID_HEIGHT;
import static com.javakaian.game.util.GameConstants.GRID_WIDTH;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.level.Level;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.ui.buttons.ButtonFactory;
import com.javakaian.game.ui.buttons.OButton;
import com.javakaian.game.ui.buttons.OButtonListener;
import com.javakaian.game.ui.buttons.OToggleButton;
import com.javakaian.game.ui.components.SimpleLayout;
import com.javakaian.game.ui.components.UIComponent;

import java.util.ArrayList;
import java.util.List;

public class FunctionalButtonsMenu {

    private OToggleButton btnPauseResume;
    private OToggleButton btnDoubleSpeed;

    private OButton btnRestart;
    private OButton btnExit;
    private final List<OButton> menuButtons;
    private final SimpleLayout layoutFunctionalButtons;
    private final Level level;

    public FunctionalButtonsMenu(Level level) {
        this.level = level;
        this.layoutFunctionalButtons = new SimpleLayout(GRID_WIDTH * 2, GRID_HEIGHT * 2,
                2, 5);
        menuButtons = new ArrayList<>();
        initButtons();
    }

    private void initButtons() {

        final ButtonFactory bf = new ButtonFactory(FUNC_BUTTON_WH, FUNC_BUTTON_WH);
        btnPauseResume = bf.createToggleButton(MyAtlas.WAVE_PAUSE, MyAtlas.WAVE_RESUME);
        btnDoubleSpeed = bf.createToggleButton(MyAtlas.WAVE_SLOW, MyAtlas.WAVE_FAST);
        btnExit = bf.createOButton("", MyAtlas.QUIT_X, false);
        btnRestart = bf.createOButton("", MyAtlas.CHANGE_MAP, false);

        menuButtons.add(btnPauseResume);
        menuButtons.add(btnDoubleSpeed);
        menuButtons.add(btnRestart);
        menuButtons.add(btnExit);

        layoutFunctionalButtons.addComponents(menuButtons);
        layoutFunctionalButtons.pack();

        initButtonListener();

    }

    private void initButtonListener() {

        btnPauseResume.setToggleListener(isToggled -> {
            if (isToggled) {
                level.pause();
            } else {
                level.resume();
            }
        });

        btnDoubleSpeed.setToggleListener(isToggled -> {
            if (isToggled) {
                level.speed2xClicked();
            } else {
                level.normalSpeedClicked();
            }
        });

        btnRestart.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE)
                level.restart();
        });

        btnExit.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE)
                level.returnToMenuClicked();
        });

    }

    public void render(ShapeRenderer sr) {
        layoutFunctionalButtons.render(sr);
    }


    public void render(SpriteBatch sb) {
        layoutFunctionalButtons.render(sb);
    }

    public void touchDown(float x, float y) {
        menuButtons.stream().
                filter(b -> b.contains(x, y)).
                findFirst().
                ifPresent(b -> b.touchDown(x, y));
    }

    public void touchRelease(float x, float y) {
        menuButtons.stream().
                filter(b -> b.contains(x, y)).
                findFirst().
                ifPresent(b -> b.touchRelease(x, y));
    }

    public UIComponent getLayout() {
        return layoutFunctionalButtons;
    }
}
