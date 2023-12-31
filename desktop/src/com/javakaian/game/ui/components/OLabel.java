package com.javakaian.game.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.util.GameConstants;
import com.javakaian.game.util.GameUtils;

public class OLabel implements UIComponent {

    protected Vector2 position = new Vector2(0, 0);
    protected Vector2 size;
    protected String text;
    protected BitmapFont font;
    protected GlyphLayout glyphLayout;

    public OLabel(String text) {
        this(new Vector2(GameConstants.GRID_WIDTH * 2.5f,
                GameConstants.GRID_HEIGHT), text);
    }

    public OLabel(Vector2 size, String text) {
        this.size = size;
        this.text = text;
        this.font = GameUtils.generateBitmapFont(25, Color.BLACK);
        glyphLayout = new GlyphLayout(font, text);
    }

    @Override
    public void render(SpriteBatch sb) {
        font.draw(sb, text, position.x, position.y + glyphLayout.height);
    }

    @Override
    public Vector2 getSize() {
        return size;
    }

    @Override
    public void setSizeLocation(float cx, float cy, float compWidth, float compHeight) {
        this.position.x = cx;
        this.position.y = cy;
        this.size.x = compWidth;
        this.size.y = compHeight;
    }

    public void setText(String text) {
        this.text = text;
    }
}
