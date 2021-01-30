package tjol.ex1.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {
    protected State(){ }

    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch sb);
    public abstract void handleInput(float deltaTime);
}
