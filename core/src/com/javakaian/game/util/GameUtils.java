package com.javakaian.game.util;

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
		parameter.magFilter = TextureFilter.Linear;
		parameter.minFilter = TextureFilter.Linear;
		return generator.generateFont(parameter);
	}

	public static void renderCenter(String text, SpriteBatch sb, BitmapFont font) {

		GlyphLayout gl = new GlyphLayout(font, text);
		font.draw(sb, text, GameConstants.SCREEN_WIDTH / 2 - gl.width / 2,
				GameConstants.SCREEN_HEIGHT * 0.3f - gl.height / 2);

	}

	public static void renderUp(String text, SpriteBatch sb, BitmapFont font) {

		GlyphLayout gl = new GlyphLayout(font, text);
		font.draw(sb, text, GameConstants.SCREEN_WIDTH / 2 - gl.width / 2,
				GameConstants.SCREEN_HEIGHT * 0.1f - gl.height / 2);

	}

	public static void renderCenterWithY(String text, SpriteBatch sb, BitmapFont font, float y) {

		GlyphLayout gl = new GlyphLayout(font, text);
		font.draw(sb, text, GameConstants.SCREEN_WIDTH / 2 - gl.width / 2, y - gl.height / 2);

	}

	public static void render(String text, SpriteBatch sb, BitmapFont font, float x, float y) {

		GlyphLayout gl = new GlyphLayout(font, text);
		font.draw(sb, text, x - gl.width / 2, y - gl.height / 2);

	}

	public static void renderText(String text, SpriteBatch sb, BitmapFont font) {

		GlyphLayout gl = new GlyphLayout(font, text);
		font.draw(sb, text, GameConstants.SCREEN_WIDTH / 2 - gl.width / 2,
				GameConstants.SCREEN_HEIGHT * 0.3f - gl.height / 2);

	}
}
