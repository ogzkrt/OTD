package com.javakaian.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.javakaian.game.buttons.OButton;
import com.javakaian.game.buttons.OButtonListener;
import com.javakaian.game.input.GameOverInput;
import com.javakaian.game.map.GameConstants;
import com.javakaian.game.resources.MyAtlas;

public class GameOverState extends State {

	private String stateName = "GAME OVER";
	private OButton replayButton;
	private OButton backToMenuButton;

	public GameOverState(StateController stateController) {
		super(stateController);

		glipLayout.setText(bitmapFont, stateName);

		inputProcessor = new GameOverInput(this);

		replayButton = new OButton(GameConstants.GRID_WIDTH * 6, GameConstants.GRID_WIDTH * 6, GameConstants.GRID_WIDTH,
				GameConstants.GRID_WIDTH);
		replayButton.setIcon(MyAtlas.REPLAY_BUTTON);
		replayButton.setPressedIcon(MyAtlas.REPLAY_BUTTON_PRESSED);
		replayButton.setText("Play");
		replayButton.setFont(bitmapFont);

		backToMenuButton = new OButton(GameConstants.GRID_WIDTH * 10, GameConstants.GRID_WIDTH * 6,
				GameConstants.GRID_WIDTH, GameConstants.GRID_WIDTH);
		backToMenuButton.setFont(bitmapFont);
		backToMenuButton.setText("Option");
		backToMenuButton.setIcon(MyAtlas.MENU_BUTTON);
		backToMenuButton.setPressedIcon(MyAtlas.MENU_BUTTON_PRESSED);

		setListeners();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sr.begin(ShapeType.Line);

		sr.end();

		sb.begin();
//		bitmapFont.draw(sb, stateName, GameConstants.SCREEN_WIDTH / 2 - glipLayout.width / 2,
//				GameConstants.SCREEN_HEIGHT / 2 - glipLayout.height / 2);

		replayButton.render(sb);
		backToMenuButton.render(sb);

		sb.end();
	}

	@Override
	public void update(float deltaTime) {

	}

	public void touchDown(float x, float y) {

		if (replayButton.getBoundRect().contains(x, y)) {
			replayButton.touchDown(x, y);
		}
		if (backToMenuButton.getBoundRect().contains(x, y)) {
			backToMenuButton.touchDown(x, y);
		}
	}

	public void touchRelease(float x, float y) {
		replayButton.touchRelease(x, y);
		backToMenuButton.touchRelease(x, y);
		if (replayButton.getBoundRect().contains(x, y)) {
			stateController.setState(StateEnum.PlayState);
		}
		if (backToMenuButton.getBoundRect().contains(x, y)) {
			stateController.setState(StateEnum.MenuState);
		}
	}

	private void setListeners() {

		replayButton.setButtonListener(new OButtonListener() {

			@Override
			public void touchRelease(float x, float y) {

				stateController.setState(StateEnum.PlayState);
			}

			@Override
			public void touchDown(float x, float y) {
				System.out.println("PLAY BUTTON TOUCH DOWN");
			}

			@Override
			public void dragged(float x, float y) {
				// TODO Auto-generated method stub

			}
		});
		backToMenuButton.setButtonListener(new OButtonListener() {

			@Override
			public void touchRelease(float x, float y) {
				System.out.println("OPTIONS TOUCH UP");
			}

			@Override
			public void touchDown(float x, float y) {
				System.out.println("OPTIONS TOUCH DOWN");
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
