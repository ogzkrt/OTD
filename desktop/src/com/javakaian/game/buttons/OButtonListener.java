package com.javakaian.game.buttons;

public interface OButtonListener {

    void touchDown(float x, float y);

    void touchRelease(float x, float y);

    void dragged(float x, float y);
}
