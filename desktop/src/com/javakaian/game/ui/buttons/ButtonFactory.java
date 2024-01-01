package com.javakaian.game.ui.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.javakaian.game.util.GameUtils;


public class ButtonFactory {

    private final float width;
    private final float height;
    private final BitmapFont font;

    public ButtonFactory(float width, float height, BitmapFont font) {
        this.width = width;
        this.height = height;
        this.font = font;
    }

    public ButtonFactory(float width, float height) {
        this(width, height, GameUtils.generateBitmapFont(15, Color.BLACK));
    }

    public TowerBuilder createBuilderButton(int price, Sprite icon) {
        final GlyphLayout glyphLayout = new GlyphLayout(font, String.valueOf(price));
        final TowerBuilder tb = new TowerBuilder(width, height, font, glyphLayout, price);
        tb.setIcon(icon);
        return tb;
    }

    public TowerUpgrade createUpgradeButton(Sprite icon, boolean enable) {
        final GlyphLayout layout = new GlyphLayout(font, "");
        final TowerUpgrade btn = new TowerUpgrade(width, height, font, "", layout);
        btn.setIcon(icon);
        btn.setEnable(enable);
        return btn;
    }

    public OButton createOButton(Sprite icon) {
        final OButton button = new OButton(0, 0, width, height);
        button.setIcon(icon);
        return button;
    }

    public OButton createOButton(String text, Sprite icon, boolean textCenter) {
        return createOButton(0, 0, text, icon, textCenter);
    }

    public OButton createOButton(float x, float y, String text, Sprite icon, boolean textCenter) {
        final GlyphLayout glyphLayout = new GlyphLayout(font, text);
        final OButton button = new OButton(x, y, width, height, font, text, glyphLayout);
        button.setIcon(icon);
        button.setTextCenter(textCenter);
        return button;
    }

    public OToggleButton createToggleButton(Sprite icon, Sprite toggledIcon) {
        final OToggleButton button = new OToggleButton(width, height);
        button.setIcon(icon);
        button.setToggledIcon(toggledIcon);
        return button;
    }

}