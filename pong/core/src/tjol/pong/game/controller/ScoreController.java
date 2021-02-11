package tjol.pong.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import tjol.pong.game.model.ScoreModel;
import tjol.pong.game.view.ScoreView;

public class ScoreController extends Controller{
    // ScoreController is implemented as a Singleton class

    private static final ScoreController scoreController = new ScoreController();

    private final ScoreModel scoreModel;
    private final ScoreView scoreView;

    private ScoreController() {
        this.scoreModel = new ScoreModel();
        this.scoreView = new ScoreView(scoreModel);
    }

    public static ScoreController getInstance() {
        return scoreController;
    }

    public ScoreModel getModel() {
        return scoreModel;
    }

    public void incrementPlayerLeftScore() {
        scoreModel.incrementPlayerLeftScore();
        checkIfWon(scoreModel.getPlayerLeftScore());
    }

    public void incrementPlayerRightScore() {
        scoreModel.incrementPlayerRightScore();
        checkIfWon(scoreModel.getPlayerRightScore());
    }

    private void checkIfWon(int playerScore) {
        if (playerScore >= ScoreModel.MAX_SCORE) {
            scoreModel.setGameOver(true);
        }
    }

    @Override
    public void update() { }

    @Override
    public void render(SpriteBatch sb) {
        scoreView.render(sb);
    }
}
