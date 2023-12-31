package com.javakaian.game.ui.menu;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.ui.buttons.OButtonListener;
import com.javakaian.game.ui.buttons.TowerBuilder;
import com.javakaian.game.level.Level;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.towers.BaseTower;
import com.javakaian.game.ui.components.SimpleLayout;
import com.javakaian.game.ui.components.UIComponent;
import com.javakaian.game.util.GameConstants;

import java.util.ArrayList;
import java.util.List;

public class TowerBuilderMenu {

    private final SimpleLayout layout;
    private TowerBuilder btnFire;
    private TowerBuilder btnIce;
    private TowerBuilder btnElectric;

    private final List<TowerBuilder> towerButtons;
    private TowerBuilder selectedTower;
    private final Level level;


    public TowerBuilderMenu(Level level) {
        this.level = level;
        this.layout = new SimpleLayout(
                GameConstants.GRID_WIDTH * 6,
                GameConstants.GRID_HEIGHT * 2,
                10,20);
        towerButtons = new ArrayList<>();
        initButtons();
    }

    private void initButtons() {

        btnFire = createTowerBuilderButton(GameConstants.TOWER_PRICE, MyAtlas.FIRE_TOWER);
        btnIce = createTowerBuilderButton(GameConstants.TOWER_PRICE, MyAtlas.ICE_TOWER);
        btnElectric = createTowerBuilderButton(GameConstants.ELECTRIC_TOWER_PRICE, MyAtlas.ELECTRIC_TOWER);

        towerButtons.add(btnFire);
        towerButtons.add(btnIce);
        towerButtons.add(btnElectric);

        layout.addComponents(towerButtons);
        layout.pack();

        initButtonListener();
    }

    private TowerBuilder createTowerBuilderButton(int price, Sprite sprite) {
        final TowerBuilder tb = new TowerBuilder(
                GameConstants.MENU_ITEM_WIDTH,
                GameConstants.MENU_ITEM_HEIGHT);
        tb.setPrice(price);
        tb.setIcon(sprite);
        return tb;
    }

    private void initButtonListener() {

        btnFire.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.DOWN) {
                level.renderGrids(true);
            } else if (event == OButtonListener.TouchEvent.RELEASE) {
                level.createTowerClicked(x, y, BaseTower.TowerType.FIRE);
                level.renderGrids(false);
            }
        });

        btnIce.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.DOWN) {
                level.renderGrids(true);
            } else if (event == OButtonListener.TouchEvent.RELEASE) {
                level.createTowerClicked(x, y, BaseTower.TowerType.ICE);
                level.renderGrids(false);
            }

        });
        btnElectric.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.DOWN) {
                level.renderGrids(true);
            } else if (event == OButtonListener.TouchEvent.RELEASE) {
                level.createTowerClicked(x, y, BaseTower.TowerType.ELECTRIC);
                level.renderGrids(false);
            }

        });
    }

    public void render(ShapeRenderer sr) {
        layout.render(sr);
    }

    public void render(SpriteBatch sb) {
        layout.render(sb);
    }

    public void touchDown(float x, float y) {
        towerButtons.stream().
                filter(b -> b.contains(x, y)).
                findFirst().
                ifPresent(b -> {
                    selectedTower = b;
                    b.touchDown(x, y);
                });
    }

    public void touchRelease(float x, float y) {
        if (selectedTower != null) {
            selectedTower.touchRelease(x, y);
            selectedTower = null;
        }
    }

    public void updateInputs(float x, float y) {
        towerButtons.forEach(b -> b.updateInputs(x, y));
    }

    public void updateTowerButtons(int money) {
        towerButtons.forEach(b -> b.moneyChanged(money));
    }

    public UIComponent getLayout() {
        return layout;
    }
}
