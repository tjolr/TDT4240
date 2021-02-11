package tjol.pong.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import tjol.pong.game.controller.GameController;

public class PongGame extends ApplicationAdapter {
	// PongGame is implemented as a Singleton class

	private static final PongGame pongGame = new PongGame();

	// Private constructor to prevent external construction
	private PongGame() {};

	SpriteBatch sb;
	private GameController gameController;

	public static PongGame getInstance() {
		return pongGame;
	}

	@Override
	public void create () {
		sb = new SpriteBatch();
		gameController = new GameController();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameController.update();
		gameController.render(sb);
	}
	
	@Override
	public void dispose () {
		sb.dispose();
	}
}
