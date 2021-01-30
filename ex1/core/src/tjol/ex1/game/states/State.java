package tjol.ex1.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {
    protected OrthographicCamera camera;

    protected State(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 240, 400);
    }

    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch sb);
}
