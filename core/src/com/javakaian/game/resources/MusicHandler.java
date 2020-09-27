package com.javakaian.game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class MusicHandler {

	private static Sound clickSound;
	private static Sound deadSound;
	private static Music gamePlayMusic;
	private static Music gameOverMusic;
	private static Music menuMusic;
	public static Music em;

	public static boolean soundOn = true;

	public static void init() {

		clickSound = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
		deadSound = Gdx.audio.newSound(Gdx.files.internal("dead.wav"));

		gamePlayMusic = Gdx.audio.newMusic(Gdx.files.internal("gameplay.wav"));
		gamePlayMusic.setVolume(0.5f);
		gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("gameover.wav"));
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("menumusic.wav"));

	}

	public static void playBackgroundMusic() {

		gamePlayMusic.setLooping(true);
		gamePlayMusic.play();
	}

	public static void stopBackgroundMusic() {

		gamePlayMusic.stop();
	}

	public static void playMenuMusic() {

		menuMusic.setLooping(true);
		menuMusic.play();
	}

	public static void stopMenuMusic() {

		menuMusic.stop();
	}

	public static void playGameoverMusic() {

		gameOverMusic.play();
	}

	public static void playClickSound() {
		if (soundOn) {
			clickSound.play();
		}

	}

	public static void playDeadSound() {
		if (soundOn) {
			deadSound.play();
		}

	}

	public static void setMusicOnOff(boolean isMusicOn) {
		if (isMusicOn) {
			gameOverMusic.setVolume(1);
			gamePlayMusic.setVolume(1);
			menuMusic.setVolume(1);

		} else {
			gameOverMusic.setVolume(0);
			gamePlayMusic.setVolume(0);
			menuMusic.setVolume(0);

		}
	}

	public static void setSoundOnOff(boolean isSoundOn) {
		soundOn = isSoundOn;
	}

}
