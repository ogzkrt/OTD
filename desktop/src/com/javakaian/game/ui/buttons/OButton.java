package com.javakaian.game.ui.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.ui.components.UIComponent;
import com.javakaian.game.util.GameUtils;

public class OButton implements UIComponent {

    protected Vector2 position;
    protected Vector2 size;
    protected Vector2 center;
    protected Rectangle boundRect;

    protected boolean pressed = false;
    protected boolean enable = true;
    protected boolean setTextCenter = false;

    protected Sprite icon;
    protected final BitmapFont font;
    protected GlyphLayout glyphLayout;
    protected String text = "";

    protected OButtonListener buttonListener;

    public OButton(float x, float y, float width, float height) {
        this.position = new Vector2(x, y);
        this.size = new Vector2(width, height);
        this.boundRect = new Rectangle(x, y, this.size.x, this.size.y);
        this.font = GameUtils.generateBitmapFont(15, Color.BLACK);
        this.center = calCenter(this.position,this.size);
        glyphLayout = new GlyphLayout(font, text);
    }
    public OButton(float width, float height) {
        this.position = new Vector2(0, 0);
        this.size = new Vector2(width, height);
        this.boundRect = new Rectangle(0, 0, this.size.x, this.size.y);
        this.font = GameUtils.generateBitmapFont(15, Color.BLACK);
        this.center = calCenter(this.position,this.size);
        glyphLayout = new GlyphLayout(font, text);
    }
    public void setButtonListener(OButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public void render(ShapeRenderer sr) {
    }

    @Override
    public void setSizeLocation(float cx, float cy, float compWidth, float compHeight) {
        this.size = new Vector2(compWidth,compHeight);
        this.position = new Vector2(cx,cy);
        this.center = calCenter(this.position,this.size);
        this.boundRect = new Rectangle(this.position.x,this.position.y, this.size.x, this.size.y);
    }

    @Override
    public void render(SpriteBatch sb) {
        if (!enable || pressed) {
            sb.setColor(0.5f, 0.5f, 0.5f, 0.7f);
            sb.draw(icon, this.position.x, this.position.y, this.size.x, this.size.y);
            sb.setColor(1f, 1f, 1f, 1f);
        } else {
            sb.draw(icon, this.position.x, this.position.y, this.size.x, this.size.y);
        }
        renderText(sb);
    }

    @Override
    public Vector2 getSize() {
        return size;
    }

    private void renderText(SpriteBatch sb) {
        if (setTextCenter) {
            font.draw(sb, text, center.x - glyphLayout.width / 2, center.y - glyphLayout.height / 2);
        } else {
            font.draw(sb, text, position.x, position.y - glyphLayout.height);
        }
    }

    public void updateInputs(float x, float y) {
    }

    public void touchRelease(float x, float y) {
        if (!enable) return;
        pressed = false;
        buttonListener.touchEvent(OButtonListener.TouchEvent.RELEASE, x, y);
    }

    public void touchDown(float x, float y) {
        if (!enable) return;
        pressed = true;
        buttonListener.touchEvent(OButtonListener.TouchEvent.DOWN, x, y);
    }

    public void setIcon(Sprite sprite) {
        this.icon = sprite;
    }

    public void setPressed(boolean isPressed) {
        this.pressed = isPressed;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setSetTextCenter(boolean setTextCenter) {
        this.setTextCenter = setTextCenter;
    }

    public void setText(String text) {
        this.text = text;
        glyphLayout = new GlyphLayout(font, text);
    }
    private Vector2 calCenter(Vector2 position,Vector2 size){
        return new Vector2(position.x + size.x / 2, position.y + size.y / 2);
    }
    public boolean contains(float x, float y) {
        return boundRect.contains(x, y);
    }

}
