package com.javakaian.game.util;

public class GameConstants {

	public static float GAME_SCALE = 0.9f;
	public static int ROW_SIZE = 9;
	public static int MAP_ROW_SIZE = 6;
	public static int COLUMN_SIZE = 16;
	public static float SCREEN_HEIGHT = 720;
	public static float SCREEN_WIDTH = (16 * SCREEN_HEIGHT) / 9;

	public static float GRID_WIDTH = SCREEN_WIDTH / COLUMN_SIZE;
	public static float GRID_HEIGHT = SCREEN_HEIGHT / ROW_SIZE;

	public static float VERTICAL_MOVEMENT_UNIT = GRID_HEIGHT;
	public static float HORIZONTAL_MOVEMENT_UNIT = GRID_WIDTH;

	public static float ENEMY_WIDTH = GRID_WIDTH / 2f;
	public static float ENEMY_HEIGHT = GRID_HEIGHT / 2f;
	public static float ENEMY_SPAWN_PERIOD = 0.8f;

	public static int ENEMY_SPEED = (int) GRID_WIDTH * (2 / 2);
	public static int SLOW_ENEMY_SPEED = ENEMY_SPEED / 2;

	public static float TOWER_SIZE = GRID_WIDTH / 1;
	public static float TOWER_RANGE = GRID_WIDTH * 2;
	public static int TOWER_DAMAGE_FIRE = 90;
	public static int TOWER_DAMAGE_ICE = 1;
	public static int TOWER_DAMAGE_ELECTRIC = 10;

	public static int INITIAL_MONEY = 1500;

	public static float TOWER_SELECTION_MENU_WIDTH = SCREEN_WIDTH;
	public static float TOWER_SELECTION_MENU_HEIGHT = GRID_WIDTH * 2;

	public static float MENU_ITEM_WIDTH = GRID_WIDTH * (1.5f);
	public static float MENU_ITEM_HEIGHT = GRID_HEIGHT * (1.5f);

	public static float PAUSE2X_ITEM_WIDTH = GRID_WIDTH * (0.8f);
	public static float PAUSE2X_ITEM_HEIGHT = GRID_HEIGHT * (0.8f);

	public static float MENU_ITEM_OFFSET_X = GRID_WIDTH / 4;
	public static float MENU_ITEM_OFFSET_Y = GRID_WIDTH / 4;

	public static float BULLET_WIDTH = GRID_WIDTH / 2;
	public static float BULLET_HEIGHT = GRID_HEIGHT / 2;
	public static float BULLET_SPEED = GRID_WIDTH * 8;

	public static int SCORE_INCREASE_CONSTANT = 100;
	public static float ENEMY_BOUNTY = 2;

	public static int NEXT_WAVE_SPAWN_TIME = 10;
	public static int REMAINING_HEALTH = 6;

	public static int TOWER_PRICE = 50;
	public static int ELECTRIC_TOWER_PRICE = 200;
	public static int TOWER_RANGE_PRICE = 15;
	public static int TOWER_SPEED_PRICE = 15;
	public static int TOWER_ATTACK_PRICE = 15;

	// FONT POSITIONS
	public static float OFFEST_Y = 0.375f;
	public static float OFFEST_X = GRID_WIDTH / (1f);

	public static float DAMAGE_POS_X = GameConstants.GRID_WIDTH;
	public static float DAMAGE_POS_Y = GameConstants.GRID_WIDTH / 2.7f;
	public static float RANGE_POS_X = GameConstants.GRID_WIDTH * 4;
	public static float RANGE_POS_Y = GameConstants.GRID_WIDTH / 2.7f;;
	public static float SPEED_POS_X = GameConstants.GRID_WIDTH * 7;
	public static float SPEED_POS_Y = GameConstants.GRID_WIDTH / 2.7f;

	public static float SCORE_POS_X = GameConstants.GRID_WIDTH * 10;
	public static float SCORE_POS_Y = GameConstants.GRID_WIDTH / 2.7f;
	public static float MONEY_POS_X = GameConstants.GRID_WIDTH * 13;
	public static float MONEY_POS_Y = GameConstants.GRID_WIDTH / 2.7f;

	public static float REMAINING_POS_X = GameConstants.GRID_WIDTH * 13.8f;
	public static float REMAINNG_POS_Y = GameConstants.GRID_HEIGHT * (8.0f + OFFEST_Y);
	public static float ENEMY_NUM_POS_X = GameConstants.GRID_WIDTH * 13.8f;
	public static float ENEMY_NUM_POS_Y = GameConstants.GRID_HEIGHT * (7.0f + OFFEST_Y);

	private static float boffset_x = -GRID_WIDTH * 1.7f;
	private static float boffset_y = 0f;

	public static float PAUSE_RESUME_POS_X = SCREEN_WIDTH / 2 + boffset_x;
	public static float PAUSE_RESUME_POS_Y = GRID_HEIGHT / 2;

	public static float DOUBLE_SPEED_POS_X = SCREEN_WIDTH / 2 + boffset_x + GRID_WIDTH * 2 / 2;
	public static float DOUBLE_SPEED_POS_Y = GRID_HEIGHT / 2;

}
