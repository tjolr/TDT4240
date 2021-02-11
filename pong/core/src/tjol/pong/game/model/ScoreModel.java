package tjol.pong.game.model;

public class ScoreModel {
    public static final int MAX_SCORE = 21;

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    private boolean gameOver;

    public int getPlayerLeftScore() {
        return playerLeftScore;
    }

    public int getPlayerRightScore() {
        return playerRightScore;
    }

    private int playerLeftScore;
    private int playerRightScore;

    public ScoreModel() {
        this.gameOver = false;
        this.playerLeftScore = 0;
        this.playerRightScore = 0;
    }

    public void incrementPlayerLeftScore() {
        playerLeftScore++;
    }

    public void incrementPlayerRightScore() {
        playerRightScore++;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
