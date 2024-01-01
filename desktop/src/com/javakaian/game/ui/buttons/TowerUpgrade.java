package com.javakaian.game.ui.buttons;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.ui.components.Pressable;
import com.javakaian.game.ui.components.UIComponent;

public class TowerUpgrade implements UIComponent, Pressable {

    private int price;
    private final OButton button;

    public TowerUpgrade(float width, float height, BitmapFont font,
                        String text, GlyphLayout glyphLayout) {
        this.button = new OButton(0, 0, width, height, font, text, glyphLayout);
    }

    public void moneyChanged(int money) {
        button.setEnable(price <= money);
    }

    public void render(SpriteBatch sb) {
        this.button.render(sb);
    }

    @Override
    public Vector2 getSize() {
        return this.button.getSize();
    }

    public boolean contains(float x, float y) {
        return button.contains(x, y);
    }

    public void touchDown(float x, float y) {
        button.touchDown(x, y);
    }

    public void touchRelease(float x, float y) {
        button.touchRelease(x, y);
    }

    @Override
    public boolean isPressed() {
        return button.isPressed();
    }

    @Override
    public void setPressed(boolean pressed) {
        button.setPressed(pressed);
    }

    public void setIcon(Sprite icon) {
        button.setIcon(icon);
    }

    public void setEnable(boolean b) {
        button.setEnable(b);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public void setPosition(float x, float y) {
        this.button.setPosition(x, y);
    }

    public void setButtonListener(OButtonListener listener) {
        button.setButtonListener(listener);
    }

    public void setText(String s) {
        button.setText(s);
    }
}
