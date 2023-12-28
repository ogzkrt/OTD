package com.javakaian.game.ui.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.ui.components.UIComponent;

public class TowerUpgrade implements UIComponent {

    private int price;
    private final OButton button;

    public TowerUpgrade(float width, float height) {
        this.button = new OButton(0,0,width,height);
    }

    public void moneyChanged(int money) {
        button.enable = price <= money;
    }

    public void render(SpriteBatch sb) {
        this.button.render(sb);
    }

    @Override
    public Vector2 getSize() {
        return this.button.size;
    }

    public boolean contains(float x, float y) {
        return button.contains(x,y);
    }

    public void touchDown(float x, float y) {
        button.touchDown(x,y);
    }

    public void touchRelease(float x, float y) {
        button.touchRelease(x,y);
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
    public void setSizeLocation(float cx, float cy, float compWidth, float compHeight) {
        this.button.setSizeLocation(cx,cy,compWidth,compHeight);
    }
    public void setButtonListener(OButtonListener listener) {
        button.setButtonListener(listener);
    }

    public void setText(String s) {
        button.setText(s);
    }
}
