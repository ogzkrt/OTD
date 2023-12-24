package com.javakaian.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * @author javakaian
 * <p>
 * <p>
 * <p>
 * Base class for almost all game objects. Inherit from this class if
 * the object on your mind has following features; -selectable, -has a
 * sprite -position -dimensions.
 */
public abstract class GameObject {

    /**
     * Position of the object: X,Y coordinates
     */
    public Vector2 position;
    /**
     * Size of the object width,height
     */
    public Vector2 size;
    /**
     * Center point of an object:X,Y
     */
    public Vector2 center;
    /**
     * Image that going to be rendered if its visible and not selected
     */
    protected Sprite sprite;
    /**
     * Image that going to be rendered if its visible and selected
     */
    protected Sprite spriteSelected;
    /**
     * Sets the visibility of the object
     */
    protected boolean visible;
    /**
     * Boundry rectangle for the collision detection
     */
    protected Rectangle boundRect;
    /**
     * To see if its selected or not.
     */
    protected boolean isSelected = false;

    public GameObject(float x, float y, float width, float height) {

        this.position = new Vector2(x, y);
        this.size = new Vector2(width, height);
        this.center = new Vector2(position.x + size.x / 2, position.y + size.y / 2);
        this.boundRect = new Rectangle(x, y, this.size.x, this.size.y);
        this.visible = true;
    }

    /**
     * Renders the boundary rect with shape renderer if object is visible.
     */
    public void render(ShapeRenderer sr) {

        if (visible) {
            sr.rect(this.position.x, this.position.y, this.size.x, this.size.y);
        }

    }

    /**
     * Renders the sprites of an object if object is visible
     **/
    public void render(SpriteBatch sb) {
        if (!visible)
            return;
        if (isSelected) {
            sb.draw(spriteSelected, this.position.x, this.position.y, this.size.x, this.size.y);
        } else {
            sb.draw(sprite, this.position.x, this.position.y, this.size.x, this.size.y);
        }
    }

    /**
     * Updates the boundary rect's coordinates and center location.
     */
    public void update(float deltaTime) {

        // update position
        boundRect.x = position.x;
        boundRect.y = position.y;
        boundRect.width = size.x;
        boundRect.height = size.y;

        // update center
        center.x = position.x + size.x / 2;
        center.y = position.y + size.y / 2;
    }

    public Vector2 getPosition() {
        return position;
    }

}
