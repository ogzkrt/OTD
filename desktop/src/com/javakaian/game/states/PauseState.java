package com.javakaian.game.states;

import static com.javakaian.game.util.GameConstants.ALPHA;
import static com.javakaian.game.util.GameConstants.BLUE;
import static com.javakaian.game.util.GameConstants.GREEN;
import static com.javakaian.game.util.GameConstants.RED;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.ui.buttons.OButton;
import com.javakaian.game.ui.buttons.OButtonListener;
import com.javakaian.game.ui.components.SimpleLayout;
import com.javakaian.game.util.GameConstants;
import com.javakaian.game.util.GameUtils;

import java.util.ArrayList;
import java.util.List;

public class PauseState extends State {

    private final String stateName = "PAUSED MENU";
    private OButton btnResume;
    private OButton btnRestart;
    private OButton btnOptions;
    private final List<OButton> buttons;
    private final SimpleLayout layout;

    public PauseState(StateController stateController) {
        super(stateController);
        glyphLayout.setText(bitmapFont, stateName);
        layout = new SimpleLayout(
                GameConstants.GRID_WIDTH * 3,
                GameConstants.GRID_HEIGHT * 4,
                GameConstants.GRID_WIDTH * 10,
                GameConstants.GRID_HEIGHT * 3,
                110,
                50
        );
        buttons = new ArrayList<>();
        initButtons();
        setListeners();
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {
        super.render(sb, sr);
        Gdx.gl.glClearColor(RED, GREEN, BLUE, ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        GameUtils.renderCenter(stateName, sb, bitmapFont);
        layout.render(sb);
        sb.end();
    }

    @Override
    public void update(float deltaTime) {
    }

    private void initButtons() {

        float width = GameConstants.GRID_WIDTH * 1.5f;
        float height = GameConstants.GRID_HEIGHT * 1.5f;

        btnRestart = new OButton(width, height);
        btnRestart.setIcon(MyAtlas.RESTART_GAME);

        btnResume = new OButton(width, height);
        btnResume.setIcon(MyAtlas.RESUME_GAME);

        btnOptions = new OButton(width, height);
        btnOptions.setIcon(MyAtlas.GENERIC_BUTTON);
        btnOptions.setText("Options");
        btnOptions.setSetTextCenter(true);

        buttons.add(btnRestart);
        buttons.add(btnResume);
        buttons.add(btnOptions);

        layout.addComponents(buttons);
        layout.pack();

    }


    @Override
    public void updateInputs(float x, float y) {
    }

    @Override
    public void touchDown(float x, float y, int pointer, int button) {
        buttons.stream()
                .filter(b -> b.contains(x, y))
                .findFirst()
                .ifPresent(b -> b.touchDown(x, y));
    }

    @Override
    public void touchUp(float x, float y, int pointer, int button) {
        buttons.forEach(b -> b.setPressed(false));
        buttons.stream()
                .filter(b -> b.contains(x, y))
                .findFirst()
                .ifPresent(b -> b.touchRelease(x, y));
    }

    @Override
    public void scrolled(int amount) {

    }

    private void setListeners() {
        btnRestart.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE) {
                PlayState state = (PlayState) getStateController().getState(StateEnum.PlayState);
                getStateController().setState(StateEnum.PlayState);
                state.restart();
            }
        });
        btnResume.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE)
                getStateController().setState(StateEnum.PlayState);
        });
        btnOptions.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE)
                getStateController().setState(StateEnum.OptionState);
        });
    }
}
