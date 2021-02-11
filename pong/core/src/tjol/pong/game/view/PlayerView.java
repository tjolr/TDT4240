package tjol.pong.game.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import tjol.pong.game.model.PlayerModel;

public class PlayerView extends View{
    private PlayerModel playerModel;

    public PlayerView (PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(playerModel.getX(), playerModel.getY(), playerModel.getWidth(), playerModel.getHeight());
        shapeRenderer.end();
        sb.end();
    }
}
