package tjol.pong.game.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import tjol.pong.game.model.BallModel;

public class BallView extends View{

    private final ShapeRenderer shapeRenderer;
    private final BallModel ballModel;

    public BallView (BallModel ballModel) {
        this.ballModel = ballModel;
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(ballModel.getCenterX(), ballModel.getCenterY(), ballModel.getRadius());
        shapeRenderer.end();
        sb.end();
    }
}
