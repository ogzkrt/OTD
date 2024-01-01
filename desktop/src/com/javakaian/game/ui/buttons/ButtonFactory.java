package com.javakaian.game.ui.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;


public class ButtonFactory {

    private final float width;
    private final float height;

    public ButtonFactory(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public TowerBuilder createBuilderButton(int price, Sprite icon) {
        final TowerBuilder tb = new TowerBuilder(width, height);
        tb.setPrice(price);
        tb.setIcon(icon);
        return tb;
    }

    public TowerUpgrade createUpgradeButton(Sprite icon, boolean enable) {
        final TowerUpgrade btn = new TowerUpgrade(width, height);
        btn.setIcon(icon);
        btn.setEnable(enable);
        return btn;
    }

    public OButton createOButton(String text, Sprite icon, boolean textCenter) {
        return createOButton(0, 0, text, icon, textCenter);
    }

    public OButton createOButton(float x, float y, String text, Sprite icon, boolean textCenter) {
        final OButton button = new OButton(x, y, width, height);
        button.setIcon(icon);
        button.setText(text);
        button.setSetTextCenter(textCenter);
        return button;
    }

    public OToggleButton createToggleButton(Sprite icon, Sprite toggledIcon) {
        final OToggleButton button = new OToggleButton(width, height);
        button.setIcon(icon);
        button.setToggledIcon(toggledIcon);
        return button;
    }

}