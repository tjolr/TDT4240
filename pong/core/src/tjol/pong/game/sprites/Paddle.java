package tjol.pong.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Paddle {

    private static final int DECREASE_SPEED = 8;

    private Vector2 position;
    private Vector2 velocity;
    private Rectangle bounds;

    public Paddle(float xInitial, float yInitial, float width, float height){
        position = new Vector2(xInitial, yInitial);
        velocity = new Vector2(0,0);
        bounds = new Rectangle(xInitial, yInitial, width, height);

    }

    public void move(float yTouchPixel) {
        float yVelocity = ((Gdx.graphics.getHeight() - yTouchPixel) - position.y) / DECREASE_SPEED;
        velocity.set(0, yVelocity);
    }

    public void resetVelocity () {
        velocity.set(0,0);
    }

    public void update(float deltaTime) {
        position.add(0, velocity.y);

        if(position.y < 0) {
            position.y = 0;
        } else if (position.y + bounds.getHeight() > Gdx.graphics.getHeight()) {
            position.y = Gdx.graphics.getHeight() - bounds.getHeight();
        }

        updateBounds();
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public float getWidth() {
        return bounds.width;
    }

    public float getHeight() {
        return bounds.height;
    }

    public void updateBounds() {
        bounds.setPosition(position.x, position.y);
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
