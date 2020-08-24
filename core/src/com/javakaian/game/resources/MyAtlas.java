package com.javakaian.game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

public class MyAtlas {

	public static Sprite GRASS;
	public static Sprite FIRE_PLANE;
	public static Sprite FIRE_PLANE_MENU;
	public static Sprite FIRE_PLANE_MENU_DISABLED;
	public static Sprite TOWER_GREEN;
	public static Sprite PATH_GREY1;
	public static Sprite PATH_GREY2;
	public static Sprite PATH_BROWN;
	public static Sprite ENEMY_GREEN;
	public static Sprite ENEMY_RED;
	public static Sprite ENEMY_BLUE;
	public static Sprite ENEMY_YELLOW;
	public static Sprite ENEMY_GREEN_SLOWED;
	public static Sprite ICE_TOWER;
	public static Sprite ICE_TOWER_MENU;
	public static Sprite ICE_TOWER_MENU_DISABLED;
	public static Sprite ELECTRIC_TOWER;
	public static Sprite FIRE_BULLET;
	public static Sprite ICE_BULLET;
	public static Sprite MENU;

	public static Sprite ATTACK_MENU_ITEM;
	public static Sprite ATTAKC_MENU_ITEM_PRESSED;
	public static Sprite ATTAKC_MENU_ITEM_DISABLED;
	public static Sprite RANGE_MENU_ITEM;
	public static Sprite RANGE_MENU_ITEM_PRESSED;
	public static Sprite RANGE_MENU_ITEM_DISABLED;
	public static Sprite SPEED_MENU_ITEM;
	public static Sprite SPEED_MENU_ITEM_PRESSED;
	public static Sprite SPEED_MENU_ITEM_DISABLED;

	public static Sprite ELECTRIC_MENU_ITEM;
	public static Sprite ELECTRIC_MENU_ITEM_DISABLED;

	public static Sprite MENU_BUTTON;
	public static Sprite MENU_BUTTON_PRESSED;

	public static Sprite PLAY_BUTTON;
	public static Sprite REPLAY_BUTTON;
	public static Sprite REPLAY_BUTTON_PRESSED;

	public static Sprite PAUSE_MENU_ITEM;
	public static Sprite RESUME_MENU_ITEM;

	public static Sprite REMAKE_MENU_ITEM;
	public static Sprite REMAKE_MENU_ITEM_PRESSED;
	public static Sprite QUIT_MENU_ITEM;
	public static Sprite QUIT_MENU_ITEM_PRESSED;

	public static Sprite MENU_ITEM_2X;
	public static Sprite MENU_ITEM_2X_PRESSED;

	public static Sound shootSound;
	public static Sound shootSoundIce;
	public static Music gameMusic;

	public static Array<AtlasRegion> coingRegions;

	public static void init() {

		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("pack.atlas"));
		shootSound = Gdx.audio.newSound(Gdx.files.internal("boom2.wav"));

		shootSoundIce = Gdx.audio.newSound(Gdx.files.internal("boom1.wav"));
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("music.ogg"));
		gameMusic.setLooping(true);
		gameMusic.setVolume(0.01f);

		coingRegions = atlas.findRegions("coin");

		GRASS = new Sprite(atlas.findRegion("grass"));
		FIRE_PLANE = new Sprite(atlas.findRegion("fire_plane"));
		FIRE_PLANE.flip(false, true);
		FIRE_PLANE_MENU = new Sprite(atlas.findRegion("fire_plane_menu"));
		FIRE_PLANE_MENU.flip(false, true);
		FIRE_PLANE_MENU_DISABLED = new Sprite(atlas.findRegion("fire_plane_menu_disabled"));
		FIRE_PLANE_MENU_DISABLED.flip(false, true);

		TOWER_GREEN = new Sprite(atlas.findRegion("tower_green"));
		TOWER_GREEN.flip(false, true);

		PATH_BROWN = new Sprite(atlas.findRegion("path_brown"));
		PATH_BROWN.flip(false, true);
		PATH_GREY1 = new Sprite(atlas.findRegion("path_grey1"));
		PATH_GREY1.flip(false, true);
		PATH_GREY2 = new Sprite(atlas.findRegion("path_grey2"));
		PATH_GREY2.flip(false, true);

		ENEMY_GREEN = new Sprite(atlas.findRegion("enemy_green"));
		ENEMY_GREEN.flip(false, true);
		ENEMY_GREEN_SLOWED = new Sprite(atlas.findRegion("enemy_green_slowed"));
		ENEMY_GREEN_SLOWED.flip(false, true);

		ELECTRIC_TOWER = new Sprite(atlas.findRegion("electric_tower"));
		ELECTRIC_TOWER.flip(false, true);

		ELECTRIC_MENU_ITEM = new Sprite(atlas.findRegion("menu_item_electric"));
		ELECTRIC_MENU_ITEM.flip(false, true);
		ELECTRIC_MENU_ITEM_DISABLED = new Sprite(atlas.findRegion("menu_item_electric_disabled"));
		ELECTRIC_MENU_ITEM_DISABLED.flip(false, true);

		ICE_TOWER = new Sprite(atlas.findRegion("ice_tower"));
		ICE_TOWER.flip(false, true);
		ICE_TOWER_MENU = new Sprite(atlas.findRegion("ice_tower_menu"));
		ICE_TOWER_MENU.flip(false, true);
		ICE_TOWER_MENU_DISABLED = new Sprite(atlas.findRegion("ice_tower_menu_disabled"));
		ICE_TOWER_MENU_DISABLED.flip(false, true);

		FIRE_BULLET = new Sprite(atlas.findRegion("bullet_fire"));
		FIRE_BULLET.flip(false, true);
		ICE_BULLET = new Sprite(atlas.findRegion("ice_bullet"));
		ICE_BULLET.flip(false, true);
		MENU = new Sprite(atlas.findRegion("menu"));
		MENU.flip(false, true);

		ATTACK_MENU_ITEM = new Sprite(atlas.findRegion("attack_menu"));
		ATTACK_MENU_ITEM.flip(false, true);
		ATTAKC_MENU_ITEM_PRESSED = new Sprite(atlas.findRegion("attack_menu_pressed"));
		ATTAKC_MENU_ITEM_PRESSED.flip(false, true);
		ATTAKC_MENU_ITEM_DISABLED = new Sprite(atlas.findRegion("attack_menu_disabled"));
		ATTAKC_MENU_ITEM_DISABLED.flip(false, true);
		RANGE_MENU_ITEM = new Sprite(atlas.findRegion("range_menu"));
		RANGE_MENU_ITEM.flip(false, true);
		RANGE_MENU_ITEM_PRESSED = new Sprite(atlas.findRegion("range_menu_pressed"));
		RANGE_MENU_ITEM_PRESSED.flip(false, true);
		RANGE_MENU_ITEM_DISABLED = new Sprite(atlas.findRegion("range_menu_disabled"));
		RANGE_MENU_ITEM_PRESSED.flip(false, true);
		SPEED_MENU_ITEM = new Sprite(atlas.findRegion("speed_menu"));
		SPEED_MENU_ITEM.flip(false, true);
		SPEED_MENU_ITEM_PRESSED = new Sprite(atlas.findRegion("speed_menu_pressed"));
		SPEED_MENU_ITEM_PRESSED.flip(false, true);
		SPEED_MENU_ITEM_DISABLED = new Sprite(atlas.findRegion("speed_menu_disabled"));
		SPEED_MENU_ITEM_DISABLED.flip(false, true);
		PAUSE_MENU_ITEM = new Sprite(atlas.findRegion("pause_menu_item"));
		PAUSE_MENU_ITEM.flip(false, true);
		RESUME_MENU_ITEM = new Sprite(atlas.findRegion("resume_menu_item"));
		RESUME_MENU_ITEM.flip(false, true);

		MENU_ITEM_2X = new Sprite(atlas.findRegion("menu_item_2x"));
		MENU_ITEM_2X.flip(false, true);
		MENU_ITEM_2X_PRESSED = new Sprite(atlas.findRegion("menu_item_2x_pressed"));
		MENU_ITEM_2X_PRESSED.flip(false, true);

		MENU_BUTTON = new Sprite(atlas.findRegion("menu_button"));
		MENU_BUTTON.flip(false, true);
		MENU_BUTTON_PRESSED = new Sprite(atlas.findRegion("menu_button_pressed"));
		MENU_BUTTON_PRESSED.flip(false, true);

		PLAY_BUTTON = new Sprite(atlas.findRegion("play"));
		PLAY_BUTTON.flip(false, true);

		REPLAY_BUTTON = new Sprite(atlas.findRegion("replay_button"));
		REPLAY_BUTTON.flip(false, true);
		REPLAY_BUTTON_PRESSED = new Sprite(atlas.findRegion("replay_button_pressed"));
		REPLAY_BUTTON_PRESSED.flip(false, true);

		REMAKE_MENU_ITEM = new Sprite(atlas.findRegion("btn_remake"));
		REMAKE_MENU_ITEM.flip(false, true);
		REMAKE_MENU_ITEM_PRESSED = new Sprite(atlas.findRegion("btn_remake_pressed"));
		REMAKE_MENU_ITEM_PRESSED.flip(false, true);

		QUIT_MENU_ITEM = new Sprite(atlas.findRegion("btn_quit"));
		QUIT_MENU_ITEM.flip(false, true);
		QUIT_MENU_ITEM_PRESSED = new Sprite(atlas.findRegion("btn_quit_pressed"));
		QUIT_MENU_ITEM_PRESSED.flip(false, true);

	}

	public static void dispose() {

		shootSound.dispose();
		shootSoundIce.dispose();
	}

}
