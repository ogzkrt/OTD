package com.javakaian.game.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.map.Grid.EnumGridType;
import com.javakaian.game.util.GameConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Board {

    private final List<Grid> gridList;
    private final Set<Vector2> pathPoints;

    private boolean render = false;

    public Board(Set<Vector2> pathPoints) {

        this.pathPoints = pathPoints;
        gridList = new ArrayList<>();
        createGridList();
    }

    private void createGridList() {
        float gridX, gridY;
        // float gridX, gridY = GameConstants.GRID_HEIGHT;
        float gridWidth = GameConstants.GRID_WIDTH;
        float gridHeight = GameConstants.GRID_HEIGHT;
        for (int i = 0; i < GameConstants.MAP_ROW_SIZE; i++) {
            for (int j = 0; j < GameConstants.COLUMN_SIZE; j++) {
                gridX = j * gridWidth;
                gridY = i * gridHeight + GameConstants.GRID_HEIGHT;
                if (pathPoints.contains(new Vector2(i, j))) {

                    gridList.add(new Grid(gridX, gridY, gridWidth, gridHeight, EnumGridType.PATH));
                } else {
                    gridList.add(new Grid(gridX, gridY, gridWidth, gridHeight, EnumGridType.LAND));
                }
            }
        }
    }

    public void render(ShapeRenderer sr) {
        if (render) {
            for (Grid grid : gridList) {
                grid.render(sr);
            }
        }
    }

    public void render(SpriteBatch sb) {
        for (Grid grid : gridList) {
            grid.render(sb);

        }
    }

    public void update(float deltaTime) {

        for (Grid grid : gridList) {
            grid.update(deltaTime);
        }
    }

    public List<Grid> getGridList() {
        return gridList;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

}
