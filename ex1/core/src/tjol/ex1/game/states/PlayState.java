package tjol.ex1.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import tjol.ex1.game.sprites.Helicopter;

public class PlayState extends State{

    private final Texture background;
    private final Helicopter[] helicopters;

    public PlayState(){
        int heliCount = 4;
        helicopters = new Helicopter[heliCount];
        helicopters[0] = new Helicopter(
                (int)(Math.random() * Gdx.graphics.getWidth()),
                (int)(Math.random() * Gdx.graphics.getHeight())
        );
        for (int i = 1; i < heliCount; i++) {
            boolean collisionFree = false;
            while (!collisionFree) {
                helicopters[i] = new Helicopter(
                        (int) (Math.random() * Gdx.graphics.getWidth()),
                        (int) (Math.random() * Gdx.graphics.getHeight())
                );
                for (int j = 0; j < i; j++) {
                    if (helicopters[i].getBounds().overlaps(helicopters[j].getBounds())) {
                        break;
                    }
                }
                collisionFree = true;
            }
        }
        background = new Texture("background.jpg");
    }

    @Override
    public void update(float deltaTime) {
        for (int i = 0; i < helicopters.length; i++) {
            for (int j = 0; j < helicopters.length; j++) {
                if (j != i) {
                    if (helicopters[i].getBounds().overlaps(helicopters[j].getBounds())) {
                        helicopters[i].collision(helicopters[j].getBounds());
                        System.out.println(i + " with " + j);
                    }
                }
            }
        }
        for (Helicopter helicopter : helicopters) {
            helicopter.update(deltaTime);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        for (Helicopter helicopter : helicopters) {
            sb.draw(
                    helicopter.getAnimation().getKeyFrame(helicopter.getElapsedTime(), true),
                    helicopter.getX(),
                    helicopter.getY(),
                    helicopter.getWidth(),
                    helicopter.getBounds().getHeight()
            );
        }
        sb.end();
    }
}
