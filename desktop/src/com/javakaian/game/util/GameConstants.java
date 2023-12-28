package com.javakaian.game.util;

public class GameConstants {

    public static float GAME_SCALE = 1f;
    public static int ROW_SIZE = 9;
    public static int MAP_ROW_SIZE = 6;
    public static int COLUMN_SIZE = 16;
    public static float SCREEN_HEIGHT = 720;
    public static float SCREEN_WIDTH = (16 * SCREEN_HEIGHT) / 9;

	public static float GRID_WIDTH = SCREEN_WIDTH / COLUMN_SIZE;
	public static float GRID_HEIGHT = SCREEN_HEIGHT / ROW_SIZE;

	public static float ENEMY_WIDTH = GRID_WIDTH / 2f;
	public static float ENEMY_HEIGHT = GRID_HEIGHT / 2f;
	public static float ENEMY_SPAWN_PERIOD = 0.8f;

	public static int ENEMY_SPEED = (int) GRID_WIDTH;

	public static float TOWER_SIZE = GRID_WIDTH;
	public static float TOWER_RANGE = GRID_WIDTH * 2;
	public static int TOWER_DAMAGE_FIRE = 90;
	public static int TOWER_DAMAGE_ICE = 1;
	public static int TOWER_DAMAGE_ELECTRIC = 10;

	public static int INITIAL_MONEY = 1500;

	public static float MENU_ITEM_WIDTH = GRID_WIDTH * (1.5f);
	public static float MENU_ITEM_HEIGHT = GRID_HEIGHT * (1.5f);

	public static float FUNC_BUTTON_WH = GRID_WIDTH * (0.8f);


	public static float BULLET_WIDTH = GRID_WIDTH / 2;
	public static float BULLET_HEIGHT = GRID_HEIGHT / 2;
	public static float BULLET_SPEED = GRID_WIDTH * 8;

	public static int SCORE_INCREASE_CONSTANT = 100;
	public static int ENEMY_BOUNTY = 2;

	public static int NEXT_WAVE_SPAWN_TIME = 10;
	public static int REMAINING_HEALTH = 6;

	public static int TOWER_PRICE = 50;
	public static int ELECTRIC_TOWER_PRICE = 200;
	public static int TOWER_RANGE_PRICE = 15;
	public static int TOWER_SPEED_PRICE = 15;
	public static int TOWER_ATTACK_PRICE = 15;


}
