package tjol.pong.game.sprites;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import tjol.pong.game.states.PlayState;

public class Ball {

    private static final int SPEED = 5;

    private Vector2 position;
    private Vector2 velocity;
    private Rectangle bounds;


    public Ball(float xInital, float yInitial, float radius) {
        position = new Vector2(xInital, yInitial);
        velocity = new Vector2(SPEED, SPEED);
        bounds = new Rectangle(xInital,yInitial, radius*2, radius*2);
    }

    public void collision(PlayState.Sides side){
        if (side == PlayState.Sides.RIGHT || side == PlayState.Sides.LEFT) {
            velocity.set(-velocity.x, velocity.y);
        }
        if (side == PlayState.Sides.BOTTOM || side == PlayState.Sides.TOP) {
            velocity.set(velocity.x, -velocity.y);
        }
    }

    public void reset(PlayState.Sides side) {
        position.set(Gdx.graphics.getWidth()/2 - bounds.getWidth() / 2, Gdx.graphics.getHeight()/2 - bounds.getHeight()/2);

        if (side == PlayState.Sides.RIGHT) {
            velocity.set(SPEED,-SPEED);
        } else if (side == PlayState.Sides.LEFT) {
            velocity.set(-SPEED,SPEED);
        }
    }

    public void update(float deltaTime) {
        position.add(velocity.x, velocity.y);
        updateBounds();
    }



    public void updateBounds() {
        bounds.setPosition(position.x, position.y);
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public float getRadius() {
        return bounds.getWidth() / 2;
    }

    public float getDiameter() {
        return bounds.getWidth();
    }

    public Rectangle getBounds() {
        return bounds;
    }


}
