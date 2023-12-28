package com.javakaian.game.ui.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface UIComponent {
    void setSizeLocation(float cx, float cy, float compWidth, float compHeight);
    void render(SpriteBatch sb);
    Vector2 getSize();
}
