package com.javakaian.game.ui.buttons;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.ui.components.Pressable;
import com.javakaian.game.ui.components.UIComponent;

public class TowerBuilder implements UIComponent, Pressable {

    private final Vector2 previewPosition;
    private boolean isDragged = false;
    private final int price;
    private final OButton button;

    public TowerBuilder(float width, float height, BitmapFont font,
                        GlyphLayout glyphLayout, int price) {
        this.previewPosition = new Vector2();
        this.price = price;
        this.button = new OButton(0, 0, width, height, font, String.valueOf(price),
                glyphLayout);
    }

    @Override
    public void render(SpriteBatch sb) {
        button.render(sb);
        if (isDragged) {
            sb.draw(button.getIcon(),
                    previewPosition.x - button.getSize().x / 2,
                    previewPosition.y - button.getSize().y / 2,
                    button.getSize().x, button.getSize().y);
        }
    }

    public void updateInputs(float x, float y) {
        if (isDragged & button.isEnable()) {
            previewPosition.x = x;
            previewPosition.y = y;
            dragged(x, y);
        }
    }

    public void touchRelease(float x, float y) {
        if (button.isEnable()) {
            button.setPressed(false);
            isDragged = false;
            button.getButtonListener().touchEvent(OButtonListener.TouchEvent.RELEASE, x, y);
        }
    }

    @Override
    public boolean isPressed() {
        return button.isPressed();
    }

    @Override
    public void setPressed(boolean pressed) {
        button.setPressed(pressed);
    }

    public void touchDown(float x, float y) {
        if (button.isEnable()) {
            button.setPressed(true);
            isDragged = true;
            button.getButtonListener().touchEvent(OButtonListener.TouchEvent.DOWN, x, y);
        }
    }

    public void dragged(float x, float y) {
        button.getButtonListener().touchEvent(OButtonListener.TouchEvent.DRAGGED, x, y);
    }

    public void moneyChanged(int money) {
        button.setEnable(price <= money);
    }

    public void setIcon(Sprite icon) {
        this.button.setIcon(icon);
    }

    public void setButtonListener(OButtonListener buttonListener) {
        this.button.setButtonListener(buttonListener);
    }

    @Override
    public void setPosition(float x, float y) {
        this.button.setPosition(x, y);
    }

    @Override
    public Vector2 getSize() {
        return button.getSize();
    }

    public boolean contains(float x, float y) {
        return button.contains(x, y);
    }
}
