package tjol.pong.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import tjol.pong.game.model.ScoreModel;

public class ScoreView extends View{
    private final ScoreModel scoreModel;
    private final BitmapFont bitmapFont;

    public ScoreView(ScoreModel scoreModel) {
        this.scoreModel = scoreModel;
        this.bitmapFont = new BitmapFont();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        bitmapFont.getData().setScale(2);
        bitmapFont.setColor(Color.WHITE);
        bitmapFont.draw(sb, Integer.toString(scoreModel.getPlayerLeftScore()), Gdx.graphics.getWidth() / 2f - 100, Gdx.graphics.getHeight() - 40 );
        bitmapFont.draw(sb, Integer.toString(scoreModel.getPlayerRightScore()), Gdx.graphics.getWidth() / 2f + 100, Gdx.graphics.getHeight() - 40);
        sb.end();
    }
}
