package tjol.ex1.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import tjol.ex1.game.states.PlayState;

public class Ex1Game extends ApplicationAdapter {
	public static final String TITLE = "Helicopter fun";

	SpriteBatch sb;
	Texture img;
	private PlayState playState;

	@Override
	public void create () {
		sb = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		playState = new PlayState();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		playState.update(Gdx.graphics.getDeltaTime());
		playState.render(sb);
	}
	
	@Override
	public void dispose () {
		sb.dispose();
		img.dispose();
	}
}
