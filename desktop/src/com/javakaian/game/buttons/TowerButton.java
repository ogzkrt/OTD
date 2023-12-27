package com.javakaian.game.buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TowerButton extends OButton {

    private final Vector2 draggedCoord;

    private boolean isDragged = false;

    private int price;

    public TowerButton(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.draggedCoord = new Vector2();

    }

    public void render(SpriteBatch sb) {
        super.render(sb);
        if (isDragged) {
            sb.draw(icon, draggedCoord.x - size.x / 2, draggedCoord.y - size.y / 2, size.x, size.y);
        }
    }

    public void updateInputs(float x, float y) {
        if (isDragged & enable) {
            draggedCoord.x = x;
            draggedCoord.y = y;
            dragged(x, y);
        }
    }

    public void touchRelease(float x, float y) {
        if (enable) {
            pressed = false;
            isDragged = false;
            buttonListener.touchEvent(OButtonListener.TouchEvent.RELEASE,x, y);
        }
    }

    public void touchDown(float x, float y) {
        if (enable) {
            pressed = true;
            isDragged = true;
            buttonListener.touchEvent(OButtonListener.TouchEvent.DOWN,x, y);
        }
    }

    public void dragged(float x, float y) {
        buttonListener.touchEvent(OButtonListener.TouchEvent.DRAGGED,x, y);
    }

    public void moneyChanged(int money) {
        enable = price <= money;
    }

    public void setPrice(int price) {
        this.price = price;
        this.setText(String.valueOf(price));
    }

}
