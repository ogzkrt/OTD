package com.javakaian.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class GameUtils {

	public static BitmapFont generateBitmapFont(int size, Color color) {

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.flip = true;
		parameter.size = size;
		parameter.color = color;
		parameter.magFilter = TextureFilter.Nearest;
		parameter.minFilter = TextureFilter.Nearest;
		return generator.generateFont(parameter);
	}

	public static void renderCenter(String text, SpriteBatch sb, BitmapFont font) {

		GlyphLayout gl = new GlyphLayout(font, text);
		font.draw(sb, text, GameConstants.SCREEN_WIDTH / 2 - gl.width / 2,
				GameConstants.SCREEN_HEIGHT * 0.3f - gl.height / 2);

	}

	public static void renderText(String text, SpriteBatch sb, BitmapFont font) {

		GlyphLayout gl = new GlyphLayout(font, text);
		font.draw(sb, text, GameConstants.SCREEN_WIDTH / 2 - gl.width / 2,
				GameConstants.SCREEN_HEIGHT * 0.3f - gl.height / 2);

	}
}
