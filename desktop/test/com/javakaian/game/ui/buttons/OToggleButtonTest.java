package com.javakaian.game.ui.buttons;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class OToggleButtonTest {
    @Mock
    private SpriteBatch sb;
    @Mock
    private BitmapFont font;
    @Mock
    private Sprite icon;
    @Mock
    private Sprite toggledIcon;
    @Mock
    private OToggleButtonListener listener;

    private OToggleButton button;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        button = new OToggleButton(0, 0, 100, 50, font, "Test", null);
        button.setIcon(icon);
        button.setToggledIcon(toggledIcon);
        button.setToggleListener(listener);
    }

    @Test
    public void testRenderWithToggledIcon() {
        button.setToggled(true);
        button.render(sb);
        verify(sb).draw(toggledIcon, 0, 0, 100, 50); // Assert drawing toggled icon
    }

    @Test
    public void testRenderWithNonToggledIcon() {
        button.setToggled(false);
        button.render(sb);
        verify(sb).draw(icon, 0, 0, 100, 50); // Assert drawing regular icon
    }
    @Test
    public void testToggleShouldBeTrueOnlyWhenUserReleaseTheCursor() {
        button.touchDown(10,10);
        assertFalse(button.isToggled());
        button.touchRelease(10,10);
        assertTrue(button.isToggled());
    }

}