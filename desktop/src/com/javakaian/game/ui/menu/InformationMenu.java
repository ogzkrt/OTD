package com.javakaian.game.ui.menu;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.towers.BaseTower;
import com.javakaian.game.ui.components.SimpleLayout;
import com.javakaian.game.ui.components.OLabel;
import com.javakaian.game.util.GameConstants;

public class InformationMenu {

    private final OLabel lblDamage;
    private final OLabel lblRange;
    private final OLabel lblSpeed;
    private final OLabel lblScore;
    private final OLabel lblMoney;

    private int money;

    private final SimpleLayout layout;
    private final Sprite menuSprite;

    public InformationMenu(Sprite menuSprite) {
        this.menuSprite = menuSprite;
        this.layout = new SimpleLayout(
                5,
                5,
                GameConstants.SCREEN_WIDTH,
                GameConstants.GRID_HEIGHT,
                10);
        lblDamage = new OLabel("DAMAGE: ");
        lblRange = new OLabel("RANGE: ");
        lblSpeed = new OLabel("SPEED: ");
        lblScore = new OLabel("SCORE: ");
        lblMoney = new OLabel("MONEY: ");

        this.layout.addComponents(lblDamage,
                lblRange,
                lblSpeed,
                lblScore,
                lblMoney);
        this.layout.pack();

    }

    public void render(ShapeRenderer sr) {
    }

    public void render(SpriteBatch sb) {
        renderGrids(sb);
        this.layout.render(sb);
    }

    private void renderGrids(SpriteBatch sb) {
        int row = 1;
        int col = 16;
        float posx;
        float posy;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                posx = j * GameConstants.GRID_WIDTH;
                posy = i * GameConstants.GRID_HEIGHT;
                sb.draw(menuSprite, posx, posy, GameConstants.GRID_WIDTH, GameConstants.GRID_HEIGHT);
            }
        }
    }

    private void updateTexts() {
        lblMoney.setText("MONEY: " + money);
    }

    public void updateTowerInformation(BaseTower t) {
        float damage = t.getDamage();
        float range = t.getRange();
        float speed = t.getSpeed();
        lblDamage.setText("DAMAGE: " + String.format("%.00f", damage));
        lblRange.setText("RANGE: " + String.format("%.00f", range));
        lblSpeed.setText("SPEED: " + String.format("%.02f", speed));
    }

    public void fireMoneyChanged(int amount) {
        this.money = amount;
        updateTexts();
    }

    public void clearInformations() {
        lblDamage.setText("DAMAGE: ");
        lblRange.setText("RANGE: ");
        lblSpeed.setText("SPEED: ");
    }

    public void fireScoreChanged(int score) {
        lblScore.setText("SCORE: " + score);
    }

}
