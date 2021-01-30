package tjol.ex1.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import tjol.ex1.game.sprites.Helicopter;

public class PlayState extends State{

    private final Texture background;
    private final Helicopter helicopter;

    public PlayState(){
        helicopter = new Helicopter(50,0);
        background = new Texture("background.jpg");
    }

    @Override
    public void update(float deltaTime) {
        helicopter.update(deltaTime);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        sb.draw(
                helicopter.getTexture(),
                helicopter.getX(),
                helicopter.getY(),
                helicopter.getBounds().width,
                helicopter.getBounds().height,
                0,
                0,
                (int)helicopter.getBounds().width,
                (int)helicopter.getBounds().height,
                helicopter.getFlipX(),
                false
        );

        sb.end();
    }
}
