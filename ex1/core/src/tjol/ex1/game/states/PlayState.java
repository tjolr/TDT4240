package tjol.ex1.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import tjol.ex1.game.sprites.Helicopter;

public class PlayState extends State{

    private final Texture background;
    private final Helicopter helicopter;
    BitmapFont font = new BitmapFont();


    public PlayState(){
        helicopter = new Helicopter(0,0);
        background = new Texture("background.jpg");
        font.getData().setScale(3);
        font.setColor(Color.BLACK);
    }

    @Override
    public void handleInput(float deltaTime) {
        if (Gdx.input.isTouched()) {
            helicopter.move(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput(deltaTime);
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

        font.draw(sb, helicopterCoordinates() , 50,Gdx.graphics.getHeight() - 50);

        sb.end();
    }

    private String helicopterCoordinates(){
        return "x: " + Math.round(helicopter.getX()) + "\ny: " + Math.round(helicopter.getY());
    }
}
