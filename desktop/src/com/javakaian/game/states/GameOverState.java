package com.javakaian.game.states;

import static com.javakaian.game.util.GameConstants.ALPHA;
import static com.javakaian.game.util.GameConstants.BLUE;
import static com.javakaian.game.util.GameConstants.GREEN;
import static com.javakaian.game.util.GameConstants.GRID_HEIGHT;
import static com.javakaian.game.util.GameConstants.GRID_WIDTH;
import static com.javakaian.game.util.GameConstants.RED;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.game.resources.MusicHandler;
import com.javakaian.game.resources.MyAtlas;
import com.javakaian.game.ui.buttons.ButtonFactory;
import com.javakaian.game.ui.buttons.OButton;
import com.javakaian.game.ui.buttons.OButtonListener;
import com.javakaian.game.ui.components.SimpleLayout;
import com.javakaian.game.util.GameConstants;
import com.javakaian.game.util.GameUtils;

import java.util.ArrayList;
import java.util.List;

public class GameOverState extends State {

    private final String stateName = "GAME OVER";

    private OButton btnReplay;
    private OButton btnMenu;
    private final List<OButton> buttons;
    private final SimpleLayout layout;

    public GameOverState(StateController stateController) {
        super(stateController);

        bitmapFont = GameUtils.generateBitmapFont(100, Color.WHITE);
        glyphLayout.setText(bitmapFont, stateName);
        layout = new SimpleLayout(
                GameConstants.GRID_WIDTH * 3,
                GameConstants.GRID_HEIGHT * 4,
                GameConstants.GRID_WIDTH * 10,
                GameConstants.GRID_HEIGHT * 3,
                180,
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

        final ButtonFactory bf = new ButtonFactory(GRID_WIDTH * 1.5f, GRID_HEIGHT * 1.5f);
        btnReplay = bf.createOButton(MyAtlas.RESTART_GAME);
        btnMenu = bf.createOButton(MyAtlas.MENU_BUTTON);

        buttons.add(btnReplay);
        buttons.add(btnMenu);

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
        btnReplay.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE) {
                getStateController().setState(StateEnum.PlayState);
                MusicHandler.playBackgroundMusic();
            }
        });
        btnMenu.setButtonListener((event, x, y) -> {
            if (event == OButtonListener.TouchEvent.RELEASE) {
                getStateController().setState(StateEnum.MenuState);
                MusicHandler.playMenuMusic();
            }
        });

    }

}
