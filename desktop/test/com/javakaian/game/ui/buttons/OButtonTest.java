package com.javakaian.game.ui.buttons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.resources.MyAtlas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class OButtonTest {

    private BitmapFont font;
    private Sprite icon;
    private OButtonListener listener;
    private GlyphLayout glyphLayout;
    private SpriteBatch sb;

    @BeforeEach
    public void before() {
        font = Mockito.mock(BitmapFont.class);
        icon = MyAtlas.GENERIC_BUTTON;
        listener = Mockito.mock(OButtonListener.class);
        glyphLayout = Mockito.mock(GlyphLayout.class);
        sb = Mockito.mock(SpriteBatch.class);

    }

    @Test
    public void testConstructorWithAllParameters() {
        final OButton button = new OButton(10, 20, 50, 30, font, "Test", glyphLayout);
        assertEquals(new Vector2(10, 20), button.getPosition());
        assertEquals(new Vector2(50, 30), button.getSize());
        assertEquals(icon, button.getIcon());
        assertEquals(font, button.getFont());
        assertEquals("Test", button.getText());
    }

    @Test
    public void testConstructorWithPositionAndSize() {
        final OButton button = new OButton(10, 20, 50, 30);
        assertEquals(new Vector2(10, 20), button.getPosition());
        assertEquals(new Vector2(50, 30), button.getSize());
        assertNull(button.getIcon());
        assertNull(button.getFont());
        assertNull(button.getText());
    }

    @Test
    public void testDoNotCallRenderTextWhenTheTextIsNull() {
        final OButton button = new OButton(0, 0, 100, 50);
        button.setIcon(icon);
        button.render(sb);
        verify(font, never()).draw(any(), anyString(), anyFloat(), anyFloat());
    }

    @Test
    public void testRenderWithIconAndText() {
        final OButton button = new OButton(0, 0, 100, 50, font, "Test", glyphLayout);
        button.setIcon(icon);
        button.render(sb);
        verify(sb).draw(icon, 0, 0, 100, 50);
    }

    @Test
    public void testRenderTextCentered() {
        glyphLayout.width = 10;
        glyphLayout.height = 10;
        final OButton button = new OButton(0, 0, 100, 50, font, "Test", glyphLayout);
        button.setTextCenter(true);
        button.render(sb);
        verify(font).draw(sb, "Test", 45, 20);
    }

    @Test
    public void testRenderTextOnTopLeft() {
        glyphLayout.width = 10;
        glyphLayout.height = 10;
        final OButton button = new OButton(0, 0, 100, 50, font, "Test", glyphLayout);
        button.setTextCenter(false);
        button.render(sb);
        verify(font).draw(sb, "Test", 0, -10);
    }

    @Test
    public void testTouchDown() {
        final OButton button = new OButton(0, 0, 100, 50);
        button.setButtonListener(listener);
        button.touchDown(50, 25);

        assertTrue(button.isPressed());
        verify(listener).touchEvent(OButtonListener.TouchEvent.DOWN, 50, 25);
    }

    @Test
    public void testTouchRelease() {
        final OButton button = new OButton(0, 0, 100, 50);
        button.setButtonListener(listener);
        button.touchDown(50, 20);
        assertTrue(button.isPressed());
        button.touchRelease(50, 25);
        assertFalse(button.isPressed());
        verify(listener).touchEvent(OButtonListener.TouchEvent.RELEASE, 50, 25);
    }

    @Test
    public void testContainsReturnTrueForThePointInsideButton() {
        final OButton button = new OButton(0, 0, 100, 50);
        assertTrue(button.contains(10, 10));
        assertTrue(button.contains(0, 0));
        assertTrue(button.contains(100, 0));
        assertTrue(button.contains(100, 50));
        assertTrue(button.contains(0, 50));
    }

    @Test
    public void testContainsReturnFalseForThePointInsideButton() {
        final OButton button = new OButton(0, 0, 100, 50);
        assertFalse(button.contains(-5, 10));
        assertFalse(button.contains(-5, 10));
        assertFalse(button.contains(10, 51));
        assertFalse(button.contains(101, 10));
    }

    @Test
    public void testSetTextWorksProperly() {
        final OButton button = new OButton(0, 0, 100, 50, font, "Text1", glyphLayout);
        assertEquals(button.getText(), "Text1");
        button.setText("Changed");
        assertEquals(button.getText(), "Changed");
        verify(glyphLayout).setText(font, "Changed");
    }

    @Test
    public void testButtonIsClickableOnlyWhenEnabled() {
        final OButton button = new OButton(0, 0, 100, 50);
        button.setButtonListener(listener);
        button.setEnable(false);
        assertFalse(button.isEnable());
        button.touchDown(10,10);
        assertFalse(button.isPressed());
        button.setEnable(true);
        button.touchDown(10,10);
        assertTrue(button.isEnable());
        assertTrue(button.isPressed());
    }
    @Test
    public void testTouchReleaseShouldReturnEarlyIfButtonDisabled() {
        final OButton button = new OButton(0, 0, 100, 50);
        button.setButtonListener(listener);
        button.setEnable(false);
        button.touchDown(10,10);
        button.touchRelease(10,10);
        assertFalse(button.isEnable());
        assertFalse(button.isPressed());
    }
}
