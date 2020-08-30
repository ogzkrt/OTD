package com.javakaian.game.map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.util.GameConstants;

public class MapMaker {

	private int rowSize;
	private int columnSize;
	private int[][] matrix;
	private Vector2 beginningPoint;
	private Set<Vector2> pathPoints;
	private LinkedList<Direction> directionList;

	public MapMaker() {

		directionList = new LinkedList<Direction>();
		pathPoints = new HashSet<Vector2>();
		initialize();
	}

	public void initialize() {

		columnSize = GameConstants.COLUMN_SIZE;
		rowSize = GameConstants.MAP_ROW_SIZE;
		matrix = generateRandomMap();

		loadPathPoints();
		beginningPoint = findStartingPoint();
		loadDirectionList((int) beginningPoint.y, (int) beginningPoint.x);

		// add last direction to the list one more time
		// so enemy can go outside of screen
		directionList.add(directionList.get(directionList.size() - 1));
	}

	public Vector2 findStartingPoint() {
		for (int row = 0; row < matrix.length; row++) {

			if (matrix[row][0] == 1) {
				// matrix[row][0] = 0;
				return new Vector2(0, row);
			}

		}
		return null;
	}

	public Vector2 findFinishPoint() {
		for (int row = 0; row < matrix.length; row++) {

			if (matrix[row][columnSize - 1] == 1) {
				return new Vector2(columnSize - 1, row);
			}

		}
		return null;
	}

	public void loadDirectionList(int x, int y) {
		if (!(x + 1 >= rowSize) && matrix[x + 1][y] == 1) {
			matrix[x + 1][y] = 0;
			directionList.add(Direction.DOWN);
			loadDirectionList(x + 1, y);
		} else if (!(x - 1 < 0) && matrix[x - 1][y] == 1) {
			matrix[x - 1][y] = 0;
			directionList.add(Direction.UP);
			loadDirectionList(x - 1, y);
		} else if (!(y + 1 >= columnSize) && matrix[x][y + 1] == 1) {
			matrix[x][y + 1] = 0;
			directionList.add(Direction.RIGHT);
			loadDirectionList(x, y + 1);
		} else if (!(y - 1 < 0) && matrix[x][y - 1] == 1) {
			matrix[x][y - 1] = 0;
			directionList.add(Direction.LEFT);
			loadDirectionList(x, y - 1);
		}

	}

	public int[][] generateRandomMap() {

		int colSize = GameConstants.COLUMN_SIZE;
		int rowSize = GameConstants.MAP_ROW_SIZE;
		Random generator = new Random();
		int matrix[][] = new int[rowSize][colSize];
		List<Integer> indexList = new ArrayList<Integer>();
		int a = generator.nextInt(GameConstants.MAP_ROW_SIZE - 1) + 1;
		indexList.add(a);
		indexList.add(a);
		for (int i = 1; i < colSize; i++) {

			if (i % 2 == 0) {
				int temp = generator.nextInt(rowSize);
				indexList.add(temp);
			} else {
				indexList.add(indexList.get(i));
			}

		}

		for (int i = 0; i < colSize; i++) {

			int temp = indexList.get(i);
			int temp1 = indexList.get(i + 1);

			if (temp > temp1) {

				for (int j = temp1; j <= temp; j++) {

					matrix[j][i] = 1;
				}
			} else {
				for (int j = temp; j <= temp1; j++) {
					matrix[j][i] = 1;
				}
			}

		}

		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println("");
		}

		return matrix;
	}

	public void loadPathPoints() {

		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < columnSize; j++) {
				if (matrix[i][j] == 1) {
					pathPoints.add(new Vector2(i, j));
				}
			}
		}
	}

	public Set<Vector2> getPathPoints() {
		return pathPoints;
	}

	public LinkedList<Direction> getDirectionList() {
		return directionList;
	}

	public enum Direction {
		LEFT, RIGHT, UP, DOWN;
	}
}
