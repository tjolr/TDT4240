package tjol.pong.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import tjol.pong.game.model.PlayerModel;
import tjol.pong.game.view.PlayerView;

public class PlayerController extends Controller {
    private PlayerModel playerModel;
    private PlayerView playerView;

    public PlayerController(Vector2 position) {
        this.playerModel = new PlayerModel(position);
        this.playerView = new PlayerView(playerModel);
    }

    public PlayerModel getModel() {
        return this.playerModel;
    }

    public void move(float yTouchPixel) {
        float yVelocity = ((Gdx.graphics.getHeight() - yTouchPixel) - playerModel.getY()) / PlayerModel.DECREASE_SPEED;
        playerModel.setVelocity(0, yVelocity);
    }

    public void resetVelocity () {
        playerModel.setVelocity(0,0);
    }

    @Override
    public void update() {
        playerModel.addPosition(0, playerModel.getVelocity().y);

        if(playerModel.getY() < 0) {
            playerModel.setY(0);
        } else if (playerModel.getY() + PlayerModel.HEIGHT > Gdx.graphics.getHeight()) {
            playerModel.setY(Gdx.graphics.getHeight() - PlayerModel.HEIGHT);
        }

        playerModel.updateBounds();
    }

    @Override
    public void render(SpriteBatch sb) {
        playerView.render(sb);
    }
}
