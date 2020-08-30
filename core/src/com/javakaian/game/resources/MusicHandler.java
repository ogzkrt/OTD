package com.javakaian.game.resources;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class MusicHandler {

	public Sound fireShoot;
	public Sound iceShoot;
	public Sound electrikShoot;
	public Sound buttonClick;

	public Music gamePlayMusic;
	public Music menuMusic;
	public Music gameOverMusic;

	private static MusicHandler instance;

	private Set<Long> soundMap;

	private MusicHandler() {

		fireShoot = Gdx.audio.newSound(Gdx.files.internal("boom2.wav"));
		soundMap = new HashSet();
	}

	public static MusicHandler getInstance() {

		if (instance == null) {
			instance = new MusicHandler();
		}
		return instance;
	}

	public void playSound(SoundTypes type) {

		switch (type) {
		case FIRE:
			long id = fireShoot.play();
			soundMap.add(id);
			break;

		default:
			break;
		}

	}

	public void stopSound(long id) {

	}

	public enum SoundTypes {
		FIRE, ICE, ELECTRIC, BUTTON_CLICK
	}
}
