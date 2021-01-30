package tjol.ex1.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Helicopter {

    private static final int SPEED = 5;

    private Vector2 position;
    private Vector2 velocity;
    private Texture texture;
    private Rectangle bounds;
    private boolean flipX;



    public Helicopter(int xInitial, int yInitial) {
        position = new Vector2(xInitial, yInitial);
        velocity = new Vector2(SPEED,SPEED);
        texture = new Texture("attackhelicopter.PNG");
        bounds = new Rectangle(xInitial, yInitial, texture.getWidth(), texture.getHeight());
        flipX = true;
    }

    public void update(float deltaTime) {

        if (position.x < 0){
            velocity.set(SPEED, velocity.y);
            setFlipX(true);
        }
        if (position.x + bounds.getWidth() > Gdx.graphics.getWidth()) {
            velocity.set(-SPEED,velocity.y);
            setFlipX(false);
        }
        if (position.y < 0) {
            velocity.set(velocity.x, SPEED);
        }
        if (position.y + bounds.getHeight() > Gdx.graphics.getHeight()) {
            velocity.set(velocity.x,-SPEED);
        }

        position.add(velocity.x,velocity.y);
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
