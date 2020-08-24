package com.javakaian.game.states;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.javakaian.game.map.GameConstants;

public class OptionState extends State {

	private CharSequence stateName = "OPTION SCREEN";

	public OptionState(StateController stateController) {
		super(stateController);
	}

	@Override
	public void render() {

		sr.begin(ShapeType.Line);

		sr.end();

		sb.begin();

		bitmapFont.draw(sb, stateName, GameConstants.SCREEN_WIDTH / 2 - glipLayout.width / 2,
				GameConstants.SCREEN_HEIGHT / 2 - glipLayout.height / 2);
		sb.end();

	}

	@Override
	public void update(float deltaTime) {

	}

	@Override
	public void updateInputs(float x, float y) {
		// TODO Auto-generated method stub

	}

}
