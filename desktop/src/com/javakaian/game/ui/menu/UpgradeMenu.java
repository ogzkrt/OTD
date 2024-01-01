package com.javakaian.game.ui.menu;


import static com.javakaian.game.util.GameConstants.GRID_HEIGHT;
import static com.javakaian.game.util.GameConstants.GRID_WIDTH;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.level.Level;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.towers.BaseTower;
import com.javakaian.game.ui.buttons.ButtonFactory;
import com.javakaian.game.ui.buttons.OButtonListener;
import com.javakaian.game.ui.buttons.TowerUpgrade;
import com.javakaian.game.ui.components.SimpleLayout;
import com.javakaian.game.ui.components.UIComponent;
import com.javakaian.game.util.GameConstants;

import java.util.ArrayList;
import java.util.List;

public class UpgradeMenu {

    private TowerUpgrade btnDamage;
    private TowerUpgrade btnRange;
    private TowerUpgrade btnSpeed;
    private final List<TowerUpgrade> buttons;
    private final Level level;
    private final SimpleLayout layout;

    public UpgradeMenu(Level level) {
        this.level = level;

        layout = new SimpleLayout(
                GRID_WIDTH * 4,
                GameConstants.GRID_HEIGHT * 2,
                15, 32);
        buttons = new ArrayList<>();
        initButtons();
    }

    public void render(ShapeRenderer sr) {
        layout.render(sr);
    }

    public void render(SpriteBatch sb) {
        layout.render(sb);
    }

    private void initButtons() {
        final ButtonFactory bf = new ButtonFactory(GRID_WIDTH, GRID_HEIGHT);
        btnDamage = bf.createUpgradeButton(MyAtlas.DAMAGE, false);
        btnRange = bf.createUpgradeButton(MyAtlas.RANGE, false);
        btnSpeed = bf.createUpgradeButton(MyAtlas.SPEED, false);

        buttons.add(btnDamage);
        buttons.add(btnRange);
        buttons.add(btnSpeed);

        layout.addComponents(buttons);
        layout.pack();

        initButtonListener();

    }

    private void initButtonListener() {
        btnDamage.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE)
                level.increaseAttackClickled();
        });

        btnRange.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE)
                level.increaseRangeClicked();
        });

        btnSpeed.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE)
                level.increaseSpeedClicked();
        });
    }

    public void updateUpgradeButtons(int money) {

        BaseTower selectedTower = level.getSelectedTower();
        if (selectedTower == null) {
            btnDamage.setEnable(false);
            btnRange.setEnable(false);
            btnSpeed.setEnable(false);
            return;
        }
        btnDamage.setText(String.valueOf(selectedTower.getAttackPrice()));
        btnDamage.setPrice(selectedTower.getAttackPrice());
        btnRange.setText(String.valueOf(selectedTower.getRangePrice()));
        btnRange.setPrice(selectedTower.getRangePrice());
        btnSpeed.setText(String.valueOf(selectedTower.getSpeedPrice()));
        btnSpeed.setPrice(selectedTower.getSpeedPrice());

        for (TowerUpgrade upgradeButton : buttons) {
            upgradeButton.moneyChanged(money);
        }
        switch (selectedTower.getType()) {
            case ICE:
                btnDamage.setEnable(false);
                break;
            case ELECTRIC:
                btnSpeed.setEnable(false);
                break;
            default:
                break;
        }

    }

    public void clearSelectedTower() {
        btnDamage.setEnable(false);
        btnRange.setEnable(false);
        btnSpeed.setEnable(false);

        btnDamage.setText("");
        btnRange.setText("");
        btnSpeed.setText("");
    }

    public void touchDown(float x, float y) {
        buttons.stream().
                filter(b -> b.contains(x, y)).
                findFirst().
                ifPresent(b -> b.touchDown(x, y));
    }

    public void touchRelease(float x, float y) {
        buttons.stream().
                filter(b -> b.contains(x, y)).
                findFirst().
                ifPresent(b -> b.touchRelease(x, y));
    }

    public UIComponent getLayout() {
        return layout;
    }
}
