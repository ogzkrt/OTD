package com.javakaian.game.states;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.javakaian.game.buttons.OButton;
import com.javakaian.game.buttons.OButtonListener;
import com.javakaian.game.input.MenuStateInput;
import com.javakaian.game.resources.MusicHandler;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.util.GameConstants;
import com.javakaian.game.util.GameUtils;

public class MenuState extends State {

	private String stateName = "MAIN MENU";

	private OButton btnPlay;
	private OButton btnOptions;
	private OButton btnCredits;

	private OButton selectedButton;

	private List<OButton> menuItems;

	public MenuState(StateController stateController) {
		super(stateController);

		inputProcessor = new MenuStateInput(this);

		glipLayout.setText(bitmapFont, stateName);

		menuItems = new ArrayList<OButton>();

		initButtons();
		setListeners();

		selectedButton = null;

		menuItems.add(btnPlay);
		menuItems.add(btnOptions);
		menuItems.add(btnCredits);
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

		GameUtils.renderCenter(stateName, sb, bitmapFont);
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

		float positionX = GameConstants.GRID_WIDTH * 4f;
		float positionY = GameConstants.GRID_HEIGHT * 5;
		float width = GameConstants.GRID_WIDTH * 1.5f;
		float height = GameConstants.GRID_HEIGHT * 1.5f;

		float space = GameConstants.GRID_WIDTH * 3.0f;

		btnPlay = new OButton(positionX, positionY, width, height);
		btnPlay.setIcon(MyAtlas.MENU_PLAY);
		btnPlay.setPressedIcon(MyAtlas.MENU_PLAY_PRESSED);

		positionX += space;
		btnOptions = new OButton(positionX, positionY, width, height);
		btnOptions.setIcon(MyAtlas.EMPTY_BUTTON);
		btnOptions.setPressedIcon(MyAtlas.EMPTY_BUTTON_PRESSED);
		btnOptions.setText("OPTIONS");
		btnOptions.setTextPositionCenter(true);

		positionX += space;
		btnCredits = new OButton(positionX, positionY, width, height);
		btnCredits.setIcon(MyAtlas.EMPTY_BUTTON);
		btnCredits.setPressedIcon(MyAtlas.EMPTY_BUTTON_PRESSED);
		btnCredits.setText("CREDITS");
		btnCredits.setTextPositionCenter(true);

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

		btnPlay.setButtonListener(new OButtonListener() {

			@Override
			public void touchRelease(float x, float y) {
				// TODO Auto-generated method stub
				if (btnPlay.getBoundRect().contains(x, y)) {
					getStateController().setState(StateEnum.PlayState);
					MusicHandler.playBackgroundMusic();
					MusicHandler.stopMenuMusic();
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

		btnOptions.setButtonListener(new OButtonListener() {

			@Override
			public void touchRelease(float x, float y) {

				if (btnOptions.getBoundRect().contains(x, y)) {
					getStateController().setState(StateEnum.OptionState);
				}
			}

			@Override
			public void touchDown(float x, float y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void dragged(float x, float y) {
				// TODO Auto-generated method stub

			}
		});

		btnCredits.setButtonListener(new OButtonListener() {

			@Override
			public void touchRelease(float x, float y) {

				if (btnCredits.getBoundRect().contains(x, y)) {

					getStateController().setState(StateEnum.CreditsState);
				}
			}

			@Override
			public void touchDown(float x, float y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void dragged(float x, float y) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public void updateInputs(float x, float y) {
		// TODO Auto-generated method stub

	}

}
