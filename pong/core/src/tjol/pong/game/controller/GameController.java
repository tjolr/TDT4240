package tjol.pong.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import tjol.pong.game.model.GameModel;
import tjol.pong.game.model.PlayerModel;

public class GameController extends Controller{
    private static final float PLAYER_X_OFFSET = 25;
    private static final float PLAYER_Y_POSITION = Gdx.graphics.getHeight() / 2f - PlayerModel.HEIGHT / 2f;

    private final BallController ballController;
    private final PlayerController playerLeftController;
    private final PlayerController playerRightController;
    private final ScoreController scoreController;

    public GameController() {
        this.ballController = new BallController();
        this.playerLeftController = new PlayerController(
                new Vector2(PLAYER_X_OFFSET, PLAYER_Y_POSITION)
        );
        this.playerRightController = new PlayerController(
                new Vector2(
                        Gdx.graphics.getWidth() - PLAYER_X_OFFSET - PlayerModel.WIDTH,
                        PLAYER_Y_POSITION
                )
        );
        this.scoreController = ScoreController.getInstance();
    }

    public void handleInput() {
        if(Gdx.input.isTouched()) {
            if (Gdx.input.getX() < PLAYER_X_OFFSET * 3) {
                playerLeftController.move(Gdx.input.getY());
            }
            if (Gdx.input.getX() > Gdx.graphics.getWidth() - PLAYER_X_OFFSET * 3) {
                playerRightController.move(Gdx.input.getY());
            }
        } else {
            playerLeftController.resetVelocity();
            playerRightController.resetVelocity();
        }
    }

    @Override
    public void update() {
        handleInput();
        ballController.update();
        playerRightController.update();
        playerLeftController.update();

        // Check if ball collides with one of the paddles
        if(playerRightController.getModel().getBounds().overlaps(ballController.getModel().getBounds())) {
            ballController.collision(GameModel.Sides.RIGHT);
        } else if(playerLeftController.getModel().getBounds().overlaps(ballController.getModel().getBounds())) {
            ballController.collision(GameModel.Sides.LEFT);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        ballController.render(sb);
        playerLeftController.render(sb);
        playerRightController.render(sb);
        scoreController.render(sb);
    }
}
