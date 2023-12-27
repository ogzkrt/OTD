package com.javakaian.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.javakaian.game.util.GameConstants;

public class TowerDefenseMain {

    public static void main(String[] args) {

        OTDGame game = new OTDGame();
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = (int) (GameConstants.SCREEN_WIDTH
                * GameConstants.GAME_SCALE);
        config.height = (int) (GameConstants.SCREEN_HEIGHT
                * GameConstants.GAME_SCALE);
        config.resizable = false;
        new LwjglApplication(game, config);
    }

}
