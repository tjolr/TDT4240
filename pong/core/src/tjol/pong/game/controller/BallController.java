package tjol.pong.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import tjol.pong.game.model.BallModel;
import tjol.pong.game.model.GameModel;
import tjol.pong.game.view.BallView;

public class BallController extends Controller{

    private final BallModel ballModel;
    private final BallView ballView;
    private final ScoreController scoreController;

    public BallController() {
        this.ballModel = new BallModel(
                Gdx.graphics.getWidth() / 2f - BallModel.RADIUS,
                Gdx.graphics.getHeight() / 2f - BallModel.RADIUS
        );
        this.ballView = new BallView(this.ballModel);
        this.scoreController = ScoreController.getInstance();
    }

    public void collision(GameModel.Sides side){
        if (side == GameModel.Sides.RIGHT || side == GameModel.Sides.LEFT) {
            ballModel.setVelocity(-ballModel.getVelocity().x, ballModel.getVelocity().y);
        }
        if (side == GameModel.Sides.BOTTOM || side == GameModel.Sides.TOP) {
            ballModel.setVelocity(ballModel.getVelocity().x, -ballModel.getVelocity().y);
        }
    }

    public void reset(GameModel.Sides side) {
        ballModel.setPosition(
                Gdx.graphics.getWidth()/ 2f - ballModel.getBounds().getWidth() / 2f,
                Gdx.graphics.getHeight()/ 2f - ballModel.getBounds().getHeight() / 2f);

        if (side == GameModel.Sides.RIGHT) {
            ballModel.setVelocity(BallModel.getSPEED(),-BallModel.getSPEED());
        } else if (side == GameModel.Sides.LEFT) {
           ballModel.setVelocity(-BallModel.getSPEED(),BallModel.getSPEED());
        }
    }

    public BallModel getModel() {
        return this.ballModel;
    }

    public void update() {
        ballModel.addPosition(ballModel.getVelocity().x, ballModel.getVelocity().y);
        ballModel.updateBounds();

        // Check if ball collides with top or bottom wall
        if (ballModel.getY() < 0) {
            this.collision(GameModel.Sides.BOTTOM);
        } else if (ballModel.getY() + ballModel.getDiameter() > Gdx.graphics.getHeight()) {
            this.collision(GameModel.Sides.TOP);
        }

        if (!scoreController.getModel().isGameOver()) {
            // Check if ball is out of screen
            if (ballModel.getX() > Gdx.graphics.getWidth()) {
                scoreController.incrementPlayerLeftScore();
                if (!scoreController.getModel().isGameOver()) {
                    this.reset(GameModel.Sides.RIGHT);
                }
            } else if (ballModel.getX() + ballModel.getDiameter() < 0) {
                scoreController.incrementPlayerRightScore();
                if (!scoreController.getModel().isGameOver()) {
                    this.reset(GameModel.Sides.LEFT);
                }
            }
        }
    }

    public void render(SpriteBatch sb) {
        ballView.render(sb);
    }
}
