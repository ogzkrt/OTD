package com.javakaian.game.buttons;

public interface OButtonListener {
    void touchEvent(TouchEvent event,float x,float y);

    enum TouchEvent{
        DOWN,RELEASE,DRAGGED
    }
}
