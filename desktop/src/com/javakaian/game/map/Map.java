package com.javakaian.game.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.map.MapMaker.Direction;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Map {

    private final LinkedList<Direction> directionList;
    private final Board board;

    public Map() {

        final MapMaker mapMaker = new MapMaker();

        directionList = mapMaker.getDirectionList();
        Set<Vector2> pathPoints = mapMaker.getPathPoints();
        board = new Board(pathPoints);
    }

    public void render(ShapeRenderer sr) {
        board.render(sr);
    }

    public void render(SpriteBatch sb) {
        board.render(sb);
    }

    public void update(float deltaTime) {
        board.update(deltaTime);
    }

    public LinkedList<Direction> getDirectionList() {
        return directionList;
    }

    public Board getBoard() {
        return board;
    }

    public Grid getSelectedGrid(float x, float y) {
        List<Grid> gridList = this.board.getGridList();
        for (Grid grid : gridList) {
            if (grid.contains(x, y)) {
                return grid;
            }
        }
        return null;
    }
}
