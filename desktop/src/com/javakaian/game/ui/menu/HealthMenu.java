package com.javakaian.game.ui.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.ui.components.SimpleLayout;
import com.javakaian.game.ui.components.OLabel;
import com.javakaian.game.ui.components.UIComponent;
import com.javakaian.game.util.GameConstants;

public class HealthMenu {

    private OLabel lblHealth;
    private OLabel lblRemaining;
    private final SimpleLayout layout;

    public HealthMenu() {
        this.layout = new SimpleLayout(
                GameConstants.GRID_WIDTH * 3,
                GameConstants.GRID_HEIGHT * 2,
                0);
        initButtons();
    }

    private void initButtons() {

        lblHealth = new OLabel("HEALTH:");
        lblRemaining = new OLabel("REMAINS:");

        layout.addComponents(lblHealth, lblRemaining);
        layout.pack();

    }

    public void render(ShapeRenderer sr) {
        layout.render(sr);
    }


    public void render(SpriteBatch sb) {
        layout.render(sb);
    }

    public void fireHealthChanged(int remainingHealth) {
        this.lblHealth.setText("HEALTH: " + remainingHealth);

    }

    public void fireEnemyNumberChanged(int enemyNumber) {
        this.lblRemaining.setText("REMAINS: " + enemyNumber);

    }

    public UIComponent getLayout() {
        return layout;
    }
}
