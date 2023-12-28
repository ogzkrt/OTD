package com.javakaian.game.ui.buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.resources.MusicHandler;

public class OToggleButton extends OButton {

    private OToggleButtonListener buttonListener;
    private Sprite toggledIcon;
    private boolean toggled = false;

    public OToggleButton(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public OToggleButton(float width,float height){
        super(0,0,width,height);
    }

    @Override
    public void render(ShapeRenderer sr) {
        super.render(sr);
    }

    @Override
    public void render(SpriteBatch sb) {
        Sprite s = toggled ? toggledIcon : icon;
        sb.draw(s, this.position.x, this.position.y, this.size.x, this.size.y);
    }

    public void setToggleListener(OToggleButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    @Override
    public void touchDown(float x, float y) {
        MusicHandler.playClickSound();
    }

    @Override
    public void touchRelease(float x, float y) {
        if (boundRect.contains(x, y)) {
            setToggled(!toggled);
            buttonListener.toggled(toggled);
        }
    }
    @Override
    public void setSizeLocation(float cx, float cy, float compWidth, float compHeight) {
        super.setSizeLocation(cx, cy, compWidth, compHeight);
    }
    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }
    @Override
    public void setIcon(Sprite sprite) {
        super.setIcon(sprite);
    }

    public void setToggledIcon(Sprite toggledIcon) {
        this.toggledIcon = toggledIcon;
    }
}
