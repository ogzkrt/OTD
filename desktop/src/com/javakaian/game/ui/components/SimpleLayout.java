package com.javakaian.game.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class SimpleLayout implements UIComponent {

    private final List<UIComponent> elements;
    private final int offset;
    private Vector2 size;
    private Vector2 position;

    public SimpleLayout(float x, float y, float width, float height, int offset) {
        this.offset = offset;
        this.elements = new ArrayList<>();
        this.position = new Vector2(x, y);
        this.size = new Vector2(width, height);
    }

    public SimpleLayout(float width, float height, int offset) {
        this(0, 0, width, height, offset);
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public void addComponent(UIComponent element) {
        this.elements.add(element);
    }

    public void addComponents(List<? extends UIComponent> elements) {
        this.elements.addAll(elements);
    }

    public void addComponents(UIComponent... elements) {
        this.elements.addAll(List.of(elements));
    }

    public void pack() {

        float x = offset + position.x;
        float y = offset + position.y;
        float maxHeight = (float) elements.stream().
                mapToDouble(e -> e.getSize().y).max().orElse(0);
        float yPos;

        for (int i = 0; i < elements.size(); i++) {
            UIComponent c = elements.get(i);
            if (x + c.getSize().x + offset > size.x + position.x) {
                x = offset + position.x;
                y += maxHeight + offset;
            }
            yPos = (maxHeight - c.getSize().y) / 2;
            c.setSizeLocation(x, y + yPos, c.getSize().x, c.getSize().y);
            x += c.getSize().x + offset;
        }
        elements.stream().
                filter(SimpleLayout.class::isInstance).
                map(SimpleLayout.class::cast).
                forEach(SimpleLayout::pack);
    }

    @Override
    public void setSizeLocation(float cx, float cy, float compWidth, float compHeight) {
        this.size = new Vector2(compWidth, compHeight);
        this.position = new Vector2(cx, cy);
    }

    public void render(ShapeRenderer sr) {
        sr.setColor(Color.WHITE);
        sr.rect(position.x, position.y, size.x, size.y);
    }

    public void render(SpriteBatch sb) {
        elements.forEach(e -> e.render(sb));
    }

    @Override
    public Vector2 getSize() {
        return size;
    }

    public boolean contains(float x, float y) {
        return x >= position.x && x <= position.x + size.x &&
                y >= position.y && y <= position.y + size.y;
    }
}
