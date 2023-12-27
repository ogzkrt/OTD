package com.javakaian.game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

public class MyAtlas {

    public static Sprite MENU_TILE;
    public static Sprite PATH_TILE;
    public static Sprite LAND_TILE;

    public static Sprite ENEMY;
    public static Sprite ENEMY_SLOWED;

    public static Sprite FIRE_TOWER;
    public static Sprite ICE_TOWER;
    public static Sprite ELECTRIC_TOWER;
    public static Sprite FIRE_BULLET;
    public static Sprite ICE_BULLET;

    public static Sprite DAMAGE;
    public static Sprite RANGE;
    public static Sprite SPEED;

    public static Sprite MENU_BUTTON;
    public static Sprite GENERIC_BUTTON;
    public static Sprite SOUND_ON;
    public static Sprite SOUND_OFF;
    public static Sprite MUSIC_ON;
    public static Sprite MUSIC_OFF;
    public static Sprite RESUME_GAME;
    public static Sprite RESTART_GAME;
    public static Sprite MENU_PLAY;

    public static Sprite WAVE_SLOW;
    public static Sprite WAVE_FAST;
    public static Sprite WAVE_PAUSE;
    public static Sprite WAVE_RESUME;
    public static Sprite CHANGE_MAP;
    public static Sprite QUIT_X;

    public static Array<AtlasRegion> coinRegions;
    private static TextureAtlas atlas;

    public static void init() {
        atlas = new TextureAtlas(Gdx.files.internal("pack.atlas"));
        atlas.getTextures().
                forEach(t -> t.setFilter(TextureFilter.Linear, TextureFilter.Linear));
        coinRegions = atlas.findRegions("coin");

        MENU_TILE = createSprite(atlas.findRegion("menu"));
        LAND_TILE = createSprite(atlas.findRegion("land"));
        PATH_TILE = createSprite(atlas.findRegion("path"));
        ENEMY = createSprite(atlas.findRegion("enemy"));
        ENEMY_SLOWED = createSprite(atlas.findRegion("enemy_slowed"));
        FIRE_TOWER = createSprite(atlas.findRegion("fire_plane"));
        ELECTRIC_TOWER = createSprite(atlas.findRegion("electric_tower"));
        ICE_TOWER = createSprite(atlas.findRegion("ice_tower"));
        FIRE_BULLET = createSprite(atlas.findRegion("bullet_fire"));
        ICE_BULLET = createSprite(atlas.findRegion("ice_bullet"));

        DAMAGE = createSprite(atlas.findRegion("attack_menu"));
        RANGE = createSprite(atlas.findRegion("range_menu"));
        SPEED = createSprite(atlas.findRegion("speed_menu"));

        WAVE_PAUSE = createSprite(atlas.findRegion("pause_menu_item"));
        WAVE_RESUME = createSprite(atlas.findRegion("resume_menu_item"));
        WAVE_SLOW = createSprite(atlas.findRegion("menu_item_2x"));
        WAVE_FAST = createSprite(atlas.findRegion("menu_item_2x_pressed"));
        CHANGE_MAP = createSprite(atlas.findRegion("btn_remake"));
        QUIT_X = createSprite(atlas.findRegion("btn_quit"));

        MENU_BUTTON = createSprite(atlas.findRegion("menu_button"));
        RESTART_GAME = createSprite(atlas.findRegion("replay_button"));

        MENU_PLAY = createSprite(atlas.findRegion("play"));
        RESUME_GAME = createSprite(atlas.findRegion("resume"));
        SOUND_ON = createSprite(atlas.findRegion("sound_on"));
        SOUND_OFF = createSprite(atlas.findRegion("sound_off"));
        MUSIC_ON = createSprite(atlas.findRegion("music_on"));
        MUSIC_OFF = createSprite(atlas.findRegion("music_off"));
        GENERIC_BUTTON = createSprite(atlas.findRegion("empty_button"));
    }

    public static Sprite createSprite(AtlasRegion region) {
        final Sprite s = new Sprite(region);
        s.flip(false, true);
        return s;
    }

    public static void dispose() {
        atlas.dispose();
    }

}
