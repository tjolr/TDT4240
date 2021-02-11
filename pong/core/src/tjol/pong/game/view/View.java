package tjol.pong.game.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class View {
    protected static ShapeRenderer shapeRenderer = new ShapeRenderer();
    public abstract void render(SpriteBatch sb);
}
