package com.javakaian.game.ui.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.ui.components.UIComponent;

public class TowerBuilder implements UIComponent {

    private final Vector2 previewPosition;
    private boolean isDragged = false;
    private int price;
    private final OButton button;

    public TowerBuilder(float width, float height) {
        this.previewPosition = new Vector2();
        this.button = new OButton(0, 0, width, height);
    }

    @Override
    public void render(SpriteBatch sb) {
        button.render(sb);
        if (isDragged) {
            sb.draw(button.icon,
                    previewPosition.x - button.size.x / 2,
                    previewPosition.y - button.size.y / 2,
                    button.size.x, button.size.y);
        }
    }

    public void updateInputs(float x, float y) {
        if (isDragged & button.enable) {
            previewPosition.x = x;
            previewPosition.y = y;
            dragged(x, y);
        }
    }

    public void touchRelease(float x, float y) {
        if (button.enable) {
            button.pressed = false;
            isDragged = false;
            button.buttonListener.touchEvent(OButtonListener.TouchEvent.RELEASE, x, y);
        }
    }

    public void touchDown(float x, float y) {
        if (button.enable) {
            button.pressed = true;
            isDragged = true;
            button.buttonListener.touchEvent(OButtonListener.TouchEvent.DOWN, x, y);
        }
    }

    public void dragged(float x, float y) {
        button.buttonListener.touchEvent(OButtonListener.TouchEvent.DRAGGED, x, y);
    }

    public void moneyChanged(int money) {
        button.enable = price <= money;
    }

    public void setPrice(int price) {
        this.price = price;
        this.button.setText(String.valueOf(price));
    }

    public void setIcon(Sprite icon) {
        this.button.setIcon(icon);
    }

    public void setButtonListener(OButtonListener buttonListener) {
        this.button.setButtonListener(buttonListener);
    }
    @Override
    public void setSizeLocation(float cx, float cy, float compWidth, float compHeight) {
        this.button.setSizeLocation(cx,cy,compWidth,compHeight);
    }
    @Override
    public Vector2 getSize() {
        return button.size;
    }

    public boolean contains(float x, float y) {
        return button.contains(x, y);
    }
}
