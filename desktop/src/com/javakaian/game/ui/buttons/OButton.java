package com.javakaian.game.ui.buttons;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.ui.components.Pressable;
import com.javakaian.game.ui.components.UIComponent;


public class OButton implements UIComponent, Pressable {

    private final Vector2 position;
    private final Vector2 size;

    private boolean pressed = false;
    private boolean enable = true;
    private boolean setTextCenter = false;

    private Sprite icon;
    private final BitmapFont font;
    private final GlyphLayout glyphLayout;
    private String text;

    private OButtonListener buttonListener;

    public OButton(float x, float y, float width, float height, BitmapFont font, String text,
                   GlyphLayout glyphLayout) {
        this.position = new Vector2(x, y);
        this.size = new Vector2(width, height);
        this.font = font;
        this.text = text;
        this.glyphLayout = glyphLayout;
    }

    public OButton(float x, float y, float width, float height) {
        this(x, y, width, height, null, null, null);
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
        if (text != null && !text.isEmpty()) {
            renderText(sb);
        }
    }

    private void renderText(SpriteBatch sb) {
        if (setTextCenter) {
            final float x = position.x + size.x / 2 - glyphLayout.width / 2;
            final float y = position.y + size.y / 2 - glyphLayout.height / 2;
            font.draw(sb, text, x, y);
        } else {
            font.draw(sb, text, position.x, position.y - glyphLayout.height);
        }
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

    public void setButtonListener(OButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    @Override
    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
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

    public void setTextCenter(boolean setTextCenter) {
        this.setTextCenter = setTextCenter;
    }

    public void setText(String text) {
        this.text = text;
        glyphLayout.setText(font, text);
    }

    @Override
    public boolean isPressed() {
        return pressed;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Sprite getIcon() {
        return icon;
    }

    public BitmapFont getFont() {
        return font;
    }

    public String getText() {
        return text;
    }

    public boolean isEnable() {
        return enable;
    }

    public OButtonListener getButtonListener() {
        return buttonListener;
    }

    @Override
    public Vector2 getSize() {
        return size;
    }

    public boolean contains(float x, float y) {
        return (x >= position.x && x <= position.x + size.x) &&
                (y >= position.y && y <= position.y + size.y);
    }

}
