package com.javakaian.game.map;

import java.util.LinkedList;
import java.util.Set;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.game.entity.Board;
import com.javakaian.game.entity.Entity;
import com.javakaian.game.map.MapMaker.Direction;

public class Map implements Entity {

	private Vector2 startingPoint;
	private Vector2 finishPoint;
	private LinkedList<Direction> directionList;
	private Set<Vector2> pathPoints;
	private MapMaker mapMaker;
	private Board board;

	public Map() {

		mapMaker = new MapMaker();

		directionList = mapMaker.getDirectionList();
		startingPoint = mapMaker.findStartingPoint();
		finishPoint = mapMaker.findFinishPoint();
		pathPoints = mapMaker.getPathPoints();
		board = new Board(pathPoints);
	}

	@Override
	public void render(ShapeRenderer sr) {
		board.render(sr);
	}

	@Override
	public void render(SpriteBatch sb) {
		board.render(sb);
	}

	@Override
	public void update(float deltaTime) {
		board.update(deltaTime);
	}

	public Vector2 getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(Vector2 startingPoint) {
		this.startingPoint = startingPoint;
	}

	public Vector2 getFinishPoint() {
		return finishPoint;
	}

	public void setFinishPoint(Vector2 finishPoint) {
		this.finishPoint = finishPoint;
	}

	public LinkedList<Direction> getDirectionList() {
		return directionList;
	}

	public void setDirectionList(LinkedList<Direction> directionList) {
		this.directionList = directionList;
	}

	public Board getBoard() {
		return board;
	}

	public Set<Vector2> getPathPoints() {
		return pathPoints;
	}
}
