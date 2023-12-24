package com.javakaian.game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

public class MyAtlas {

    public static Sprite FIRE_PLANE;
    public static Sprite FIRE_PLANE_MENU;
    public static Sprite FIRE_PLANE_MENU_DISABLED;
    public static Sprite PATH;
    public static Sprite LAND;
    public static Sprite ENEMY;
    public static Sprite ENEMY_SLOWED;
    public static Sprite ICE_TOWER;
    public static Sprite ICE_TOWER_MENU;
    public static Sprite ICE_TOWER_MENU_DISABLED;
    public static Sprite ELECTRIC_TOWER;
    public static Sprite FIRE_BULLET;
    public static Sprite ICE_BULLET;
    public static Sprite MENU;

    public static Sprite ATTACK_MENU_ITEM;
    public static Sprite ATTACK_MENU_ITEM_PRESSED;
    public static Sprite ATTACK_MENU_ITEM_DISABLED;
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

    public static Sprite MENU_SOUND_ON;
    public static Sprite MENU_SOUND_OFF;
    public static Sprite MENU_MUSIC_ON;
    public static Sprite MENU_MUSIC_OFF;
    public static Sprite MENU_PLAY;
    public static Sprite MENU_PLAY_PRESSED;
    public static Sprite MENU_RESUME;
    public static Sprite MENU_RESUME_PRESSED;

    public static Sprite EMPTY_BUTTON;
    public static Sprite EMPTY_BUTTON_PRESSED;

    public static Array<AtlasRegion> coinRegions;
    private static TextureAtlas atlas;

    public static void init() {

        atlas = new TextureAtlas(Gdx.files.internal("pack.atlas"));

        for (Texture t : atlas.getTextures()) {
            t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        }

        coinRegions = atlas.findRegions("coin");

        FIRE_PLANE = createSprite(atlas.findRegion("fire_plane"));
        FIRE_PLANE_MENU = createSprite(atlas.findRegion("fire_plane_menu"));
        FIRE_PLANE_MENU_DISABLED = createSprite(atlas.findRegion("fire_plane_menu_disabled"));

        LAND = createSprite(atlas.findRegion("land"));

        PATH = createSprite(atlas.findRegion("path"));

        ENEMY = createSprite(atlas.findRegion("enemy"));
        ENEMY_SLOWED = createSprite(atlas.findRegion("enemy_slowed"));

        ELECTRIC_TOWER = createSprite(atlas.findRegion("electric_tower"));

        ELECTRIC_MENU_ITEM = createSprite(atlas.findRegion("menu_item_electric"));
        ELECTRIC_MENU_ITEM_DISABLED = createSprite(atlas.findRegion("menu_item_electric_disabled"));

        ICE_TOWER = createSprite(atlas.findRegion("ice_tower"));
        ICE_TOWER_MENU = createSprite(atlas.findRegion("ice_tower_menu"));
        ICE_TOWER_MENU_DISABLED = createSprite(atlas.findRegion("ice_tower_menu_disabled"));

        FIRE_BULLET = createSprite(atlas.findRegion("bullet_fire"));
        ICE_BULLET = createSprite(atlas.findRegion("ice_bullet"));
        MENU = createSprite(atlas.findRegion("menu"));

        ATTACK_MENU_ITEM = createSprite(atlas.findRegion("attack_menu"));
        ATTACK_MENU_ITEM_PRESSED = createSprite(atlas.findRegion("attack_menu_pressed"));
        ATTACK_MENU_ITEM_DISABLED = createSprite(atlas.findRegion("attack_menu_disabled"));
        RANGE_MENU_ITEM = createSprite(atlas.findRegion("range_menu"));
        RANGE_MENU_ITEM_PRESSED = createSprite(atlas.findRegion("range_menu_pressed"));
        RANGE_MENU_ITEM_DISABLED = createSprite(atlas.findRegion("range_menu_disabled"));
        SPEED_MENU_ITEM = createSprite(atlas.findRegion("speed_menu"));
        SPEED_MENU_ITEM_PRESSED = createSprite(atlas.findRegion("speed_menu_pressed"));
        SPEED_MENU_ITEM_DISABLED = createSprite(atlas.findRegion("speed_menu_disabled"));
        PAUSE_MENU_ITEM = createSprite(atlas.findRegion("pause_menu_item"));
        RESUME_MENU_ITEM = createSprite(atlas.findRegion("resume_menu_item"));

        MENU_ITEM_2X = createSprite(atlas.findRegion("menu_item_2x"));
        MENU_ITEM_2X_PRESSED = createSprite(atlas.findRegion("menu_item_2x_pressed"));

        MENU_BUTTON = createSprite(atlas.findRegion("menu_button"));
        MENU_BUTTON_PRESSED = createSprite(atlas.findRegion("menu_button_pressed"));


        REPLAY_BUTTON = createSprite(atlas.findRegion("replay_button"));
        REPLAY_BUTTON_PRESSED = createSprite(atlas.findRegion("replay_button_pressed"));

        REMAKE_MENU_ITEM = createSprite(atlas.findRegion("btn_remake"));
        REMAKE_MENU_ITEM_PRESSED = createSprite(atlas.findRegion("btn_remake_pressed"));

        QUIT_MENU_ITEM = createSprite(atlas.findRegion("btn_quit"));
        QUIT_MENU_ITEM_PRESSED = createSprite(atlas.findRegion("btn_quit_pressed"));

        MENU_PLAY = createSprite(atlas.findRegion("play"));
        MENU_PLAY_PRESSED = createSprite(atlas.findRegion("play_pressed"));
        MENU_RESUME = createSprite(atlas.findRegion("resume"));
        MENU_RESUME_PRESSED = createSprite(atlas.findRegion("resume_pressed"));
        MENU_SOUND_ON = createSprite(atlas.findRegion("sound_on"));
        MENU_SOUND_OFF = createSprite(atlas.findRegion("sound_off"));
        MENU_MUSIC_ON = createSprite(atlas.findRegion("music_on"));
        MENU_MUSIC_OFF = createSprite(atlas.findRegion("music_off"));

        EMPTY_BUTTON = createSprite(atlas.findRegion("empty_button"));
        EMPTY_BUTTON_PRESSED = createSprite(atlas.findRegion("empty_button_pressed"));

    }

    public static Sprite createSprite(AtlasRegion region) {
        Sprite s = new Sprite(region);

        s.flip(false, true);
        return s;
    }

    public static void dispose() {
        atlas.dispose();
    }

}
