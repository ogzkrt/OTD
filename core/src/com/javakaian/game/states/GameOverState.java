package com.javakaian.game.states;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.javakaian.game.input.GameOverInput;
import com.javakaian.game.resources.MusicHandler;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.ui.buttons.OButton;
import com.javakaian.game.ui.buttons.OButtonListener;
import com.javakaian.game.util.GameConstants;
import com.javakaian.game.util.GameUtils;

public class GameOverState extends State {

	private String stateName = "GAME OVER";

	private OButton btnReplay;
	private OButton btnMenu;

	private OButton selectedButton;

	private List<OButton> menuItems;

	public GameOverState(StateController stateController) {
		super(stateController);

		inputProcessor = new GameOverInput(this);

		bitmapFont = GameUtils.generateBitmapFont(100, Color.WHITE);

		glipLayout.setText(bitmapFont, stateName);

		menuItems = new ArrayList<OButton>();

		initButtons();
		setListeners();

		selectedButton = null;

		menuItems.add(btnReplay);
		menuItems.add(btnMenu);
	}

	@Override
	public void render() {

		float red = 50f;
		float green = 63f;
		float blue = 94f;

		Gdx.gl.glClearColor(red / 255f, green / 255f, blue / 255f, 0.5f);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sr.begin(ShapeType.Line);

		sr.end();

		sb.begin();

		GameUtils.render(stateName, sb, bitmapFont, GameConstants.SCREEN_WIDTH / 2, GameConstants.SCREEN_HEIGHT * 0.3f);

		for (OButton oButton : menuItems) {
			oButton.render(sb);
		}

		sb.end();

	}

	@Override
	public void update(float deltaTime) {

		for (OButton oButton : menuItems) {
			oButton.update(deltaTime);
		}

	}

	private void initButtons() {

		float positionX = GameConstants.GRID_WIDTH * 4.5f;
		float positionY = GameConstants.GRID_HEIGHT * 5;
		float width = GameConstants.GRID_WIDTH * 1.5f;
		float height = GameConstants.GRID_HEIGHT * 1.5f;

		float space = GameConstants.GRID_WIDTH * 5.0f;

		btnReplay = new OButton(positionX, positionY, width, height);
		btnReplay.setIcon(MyAtlas.REPLAY_BUTTON);
		btnReplay.setPressedIcon(MyAtlas.REPLAY_BUTTON_PRESSED);

		positionX += space;

		btnMenu = new OButton(positionX, positionY, width, height);
		btnMenu.setIcon(MyAtlas.MENU_BUTTON);
		btnMenu.setPressedIcon(MyAtlas.MENU_BUTTON_PRESSED);

	}

	public void touchDown(float x, float y) {

		for (OButton oButton : menuItems) {
			if (oButton.getBoundRect().contains(x, y)) {

				selectedButton = oButton;
				selectedButton.touchDown(x, y);

			}
		}

	}

	public void touchRelease(float x, float y) {

		if (selectedButton != null) {
			selectedButton.touchRelease(x, y);
		}
	}

	private void setListeners() {

		btnReplay.setButtonListener(new OButtonListener() {

			@Override
			public void touchRelease(float x, float y) {
				// TODO Auto-generated method stub
				if (btnReplay.getBoundRect().contains(x, y)) {
					getStateController().setState(StateEnum.PlayState);
					MusicHandler.playBackgroundMusic();
				}
			}

			@Override
			public void touchDown(float x, float y) {
				System.out.println("btn play touch down");
			}

			@Override
			public void dragged(float x, float y) {
				// TODO Auto-generated method stub

			}
		});

		btnMenu.setButtonListener(new OButtonListener() {

			@Override
			public void touchRelease(float x, float y) {
				// TODO Auto-generated method stub
				if (btnMenu.getBoundRect().contains(x, y)) {
					getStateController().setState(StateEnum.MenuState);
					MusicHandler.playMenuMusic();
				}
			}

			@Override
			public void touchDown(float x, float y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void dragged(float x, float y) {

			}
		});

	}

	@Override
	public void updateInputs(float x, float y) {
		// TODO Auto-generated method stub

	}

}
