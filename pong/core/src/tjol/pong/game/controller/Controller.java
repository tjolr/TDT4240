package tjol.pong.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Controller {
    public abstract void update();
    public abstract void render(SpriteBatch sb);
}
