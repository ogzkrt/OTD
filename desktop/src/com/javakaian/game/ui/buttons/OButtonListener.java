package com.javakaian.game.ui.buttons;

public interface OButtonListener {
    void touchEvent(TouchEvent event,float x,float y);

    enum TouchEvent{
        DOWN,RELEASE,DRAGGED
    }
}
