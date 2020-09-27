package com.javakaian.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.javakaian.game.OTDGame;
import com.javakaian.game.util.GameConstants;

public class DesktopLauncher {
	public static void main(String[] arg) {

		OTDGame game = new OTDGame();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) (GameConstants.SCREEN_WIDTH * GameConstants.GAME_SCALE);
		config.height = (int) (GameConstants.SCREEN_HEIGHT * GameConstants.GAME_SCALE);
		config.resizable = false;
		// config.fullscreen = true;
		config.x = 3230;
		config.x = 130;
		config.y = 50;
		System.out.println(config.width);
		System.out.println(config.height);
		new LwjglApplication(game, config);
	}
}
