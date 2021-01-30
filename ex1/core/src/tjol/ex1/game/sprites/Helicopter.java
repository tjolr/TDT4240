package tjol.ex1.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Helicopter {
    private static final int SPEED_FACTOR = 25;

    private final Vector2 position;
    private final Vector2 velocity;
    private final Texture texture;
    private final Rectangle bounds;
    private boolean flipX;



    public Helicopter(int xInitial, int yInitial) {
        position = new Vector2(xInitial, yInitial);
        velocity = new Vector2(0, 0);
        texture = new Texture("attackhelicopter.PNG");
        bounds = new Rectangle(xInitial, yInitial, texture.getWidth(), texture.getHeight());
        flipX = true;
    }

    public void move(float xTouchPixel, float yTouchPixel) {

        float xVelocity = (xTouchPixel - position.x) / (Gdx.graphics.getWidth() / SPEED_FACTOR);
        float yVelocity = (yTouchPixel - position.y)/ (Gdx.graphics.getHeight() / SPEED_FACTOR);

        velocity.set(xVelocity, yVelocity);
    }

    public void update(float deltaTime) {
        position.add(velocity.x,velocity.y);

        if (velocity.x > 0 && !flipX) {
            setFlipX(true);
        } else if (velocity.x < 0 && flipX) {
            setFlipX(false);
        }

        if (position.x < 0){
            position.x = 0;
            setFlipX(true);
        }
        if (position.x + bounds.getWidth() > Gdx.graphics.getWidth()) {
            position.x = Gdx.graphics.getWidth()-bounds.getWidth();
            setFlipX(false);
        }
        if (position.y < 0) {
            position.y = 0;
        }
        if (position.y + bounds.getHeight() > Gdx.graphics.getHeight()) {
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

    public Texture getTexture() {
        return texture;
    }

    public void updateBounds() {
        bounds.setPosition(position.x, position.y);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    private void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }

    public boolean getFlipX() {
        return this.flipX;
    }



}
