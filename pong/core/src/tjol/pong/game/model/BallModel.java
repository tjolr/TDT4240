package tjol.pong.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BallModel {
    private static final int SPEED = 3;
    public static final float RADIUS = 10;

    private final Vector2 position;
    private final Vector2 velocity;
    private final Rectangle bounds;

    public BallModel(float xInitial, float yInitial) {
        position = new Vector2(xInitial, yInitial);
        velocity = new Vector2(SPEED, SPEED);
        bounds = new Rectangle(xInitial, yInitial, RADIUS*2, RADIUS*2);
    }

    public static int getSPEED() {
        return SPEED;
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public void addPosition(float x, float y) {
        position.add(x, y);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(float x, float y) {
        velocity.set(x, y);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public float getRadius() {
        return bounds.width / 2;
    }

    public float getDiameter() {
        return  bounds.width;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getCenterX() {
        return position.x + this.getRadius();
    }

    public float getCenterY() {
        return position.y + this.getRadius();
    }

    public void updateBounds() {
        bounds.setPosition(position.x, position.y);
    }
}
