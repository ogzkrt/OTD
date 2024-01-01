package com.javakaian.game.ui.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface UIComponent {
    void setPosition(float cx, float cy);

    void render(SpriteBatch sb);

    Vector2 getSize();
}
