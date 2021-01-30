package tjol.ex1.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Helicopter {

    private static final int MAX_SPEED = 5;
    private final int SPEED_X = (int) (Math.random() * MAX_SPEED);
    private final int SPEED_Y = (int) (Math.random() * MAX_SPEED);

    private final Vector2 position;
    private final Vector2 velocity;
    private final Rectangle bounds;
    private boolean flipX;
    private final Animation<TextureRegion> heliAnimation;
    float elapsedTime;


    public Helicopter(int xInitial, int yInitial) {
        position = new Vector2(xInitial, yInitial);

        velocity = new Vector2(SPEED_X, SPEED_Y);
        flipX = true;
        Texture texture = new Texture("heliSpritesheet.png");

        TextureRegion[][] tmpFrames = TextureRegion.split(texture, 162, 65);

        TextureRegion[] animationFrames = new TextureRegion[4];

        System.arraycopy(tmpFrames[0], 0, animationFrames, 0, 4);

        heliAnimation = new Animation<>(1f/10f, animationFrames);
        bounds = new Rectangle(xInitial, yInitial, texture.getWidth() / 4f, texture.getHeight());
    }

    public void update(float deltaTime) {
        elapsedTime += deltaTime;

        if (position.x < 0){
            turnRight();
        }
        if (position.x + bounds.getWidth() > Gdx.graphics.getWidth()) {
            turnLeft();
        }
        if (position.y < 0) {
            turnUp();
        }
        if (position.y + bounds.getHeight() > Gdx.graphics.getHeight()) {
            turnDown();
        }

        position.add(velocity.x,velocity.y);
        updateBounds();
    }

    private void turnRight() {
        velocity.set(SPEED_X, velocity.y);
        setFlipX(true);
    }

    private void turnLeft() {
        velocity.set(-SPEED_X, velocity.y);
        setFlipX(false);
    }

    private void turnUp() {
        velocity.set(velocity.x, SPEED_Y);
    }

    private void turnDown() {
        velocity.set(velocity.x, -SPEED_Y);
    }

    public void collision(Rectangle other) {
        float distanceLeft = Math.abs(other.x + other.width - bounds.x);
        float distanceRight = Math.abs(other.x - bounds.x - bounds.width);
        float distanceBottom = Math.abs(other.y + other.height - bounds.y);
        float distanceTop = Math.abs(other.y - bounds.y - bounds.height);
        System.out.println(distanceBottom + " " + distanceTop + " " + distanceLeft + " " + distanceRight);

        float minX = Math.min(distanceLeft, distanceRight);
        float minY = Math.min(distanceBottom, distanceTop);

        if (minX < minY) {
            if (distanceLeft < distanceRight) {
                turnRight();
            } else {
                turnLeft();
            }
        } else {
            if (distanceBottom < distanceTop) {
                turnUp();
            } else {
                turnDown();
            }
        }
    }

    public float getX(){
        if (flipX) {
            return position.x + bounds.getWidth();
        }
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public float getWidth() {
        return flipX ? -bounds.getWidth() : bounds.getWidth();
    }

    public Animation<TextureRegion> getAnimation() {
        return this.heliAnimation;
    }

    public void updateBounds() {
        bounds.setPosition(position.x, position.y);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    private void setFlipX(boolean flipX) {
        this.flipX = flipX;

    }
}
