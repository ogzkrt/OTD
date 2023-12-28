package com.javakaian.game.ui.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.level.Level;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.ui.components.SimpleLayout;
import com.javakaian.game.util.GameConstants;

public class MainControlMenu {

    private final SimpleLayout layoutContainer;

    private final TowerBuilderMenu towerBuilderMenu;
    private final FunctionalButtonsMenu functionalButtonsMenu;
    private final UpgradeMenu upgradeMenu;
    private final HealthMenu healthMenu;

    public MainControlMenu(Level level) {
        this.layoutContainer = new SimpleLayout(
                0,
                GameConstants.GRID_HEIGHT * 7,
                GameConstants.SCREEN_WIDTH,
                GameConstants.GRID_HEIGHT * 2,
                10);

        towerBuilderMenu = new TowerBuilderMenu(level);
        functionalButtonsMenu = new FunctionalButtonsMenu(level);
        upgradeMenu = new UpgradeMenu(level);
        healthMenu = new HealthMenu();

        layoutContainer.addComponents(
                towerBuilderMenu.getLayout(),
                functionalButtonsMenu.getLayout(),
                upgradeMenu.getLayout(),
                healthMenu.getLayout());
        layoutContainer.pack();
    }

    public void render(ShapeRenderer sr) {
        layoutContainer.render(sr);
    }


    public void render(SpriteBatch sb) {
        renderGrids(sb);
        layoutContainer.render(sb);
    }

    public void updateInputs(float x, float y) {
        towerBuilderMenu.updateInputs(x, y);
    }

    private void renderGrids(SpriteBatch sb) {
        int row = 2;
        int col = 16;
        float posx;
        float posy;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                posx = j * GameConstants.GRID_WIDTH;
                posy = i * GameConstants.GRID_HEIGHT + GameConstants.GRID_HEIGHT * 7;
                sb.draw(MyAtlas.MENU_TILE, posx, posy, GameConstants.GRID_WIDTH, GameConstants.GRID_HEIGHT);
            }
        }
    }

    public void fireHealthChanged(int remainingHealth) {
        healthMenu.fireHealthChanged(remainingHealth);

    }

    public void fireEnemyNumberChanged(int enemyNumber) {
        healthMenu.fireEnemyNumberChanged(enemyNumber);

    }

    public void clearSelectedTower() {
        upgradeMenu.clearSelectedTower();
    }

    public void moneyChanged(int money) {
        updateTowerButtons(money);
        upgradeMenu.updateUpgradeButtons(money);
    }

    private void updateTowerButtons(int money) {
        towerBuilderMenu.updateTowerButtons(money);
    }

    public boolean contains(float x, float y) {
        return layoutContainer.contains(x, y);
    }

    public void touchDown(float x, float y) {
        towerBuilderMenu.touchDown(x, y);
        upgradeMenu.touchDown(x, y);
        functionalButtonsMenu.touchDown(x, y);
    }

    public void touchRelease(float x, float y) {
        towerBuilderMenu.touchRelease(x, y);
        upgradeMenu.touchRelease(x, y);
        functionalButtonsMenu.touchRelease(x, y);
    }

    public void updateUpgradeButtons(int money) {
        upgradeMenu.updateUpgradeButtons(money);
    }
}
