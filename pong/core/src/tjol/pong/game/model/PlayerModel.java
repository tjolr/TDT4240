package tjol.pong.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PlayerModel {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 80;
    public static final int DECREASE_SPEED = 8;

    private Rectangle bounds;
    private Vector2 position;
    private Vector2 velocity;

    public PlayerModel(Vector2 position) {
        this.bounds = new Rectangle(position.x, position.y, WIDTH, HEIGHT);
        this.position = position;
        this.velocity = new Vector2(0, 0);
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public void setY(float y) {
        position.y = y;
    }

    public float getWidth() {
        return bounds.width;
    }

    public float getHeight() {
        return bounds.height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void updateBounds() {
        bounds.setPosition(position.x, position.y);
    }

    public void addPosition(float x, float y) {
        position.add(x, y);
    }

    public Vector2 getVelocity() {
        return this.velocity;
    }

    public void setVelocity(float x, float y) {
        this.velocity.set(x, y);
    }
}
