package tjol.pong.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import tjol.pong.game.sprites.Ball;
import tjol.pong.game.sprites.Paddle;

public class PlayState extends State{

    private static final int PADDLE_WIDTH = 15;
    private static final int PADDLE_HEIGHT = 80;
    private static final int PADDLE_OFFSET = 25;
    private static final int BALL_RADIUS = 5;
    private static final int WIN_SCORE = 21;

    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private int player1Score = 0;
    private int player2Score = 0;
    private ShapeRenderer shapeRenderer;
    private Ball ball;
    private BitmapFont font;
    private boolean gameover;

    public enum Sides {
        LEFT,
        TOP,
        RIGHT,
        BOTTOM
    }

    public PlayState() {
        leftPaddle = new Paddle(PADDLE_OFFSET, (Gdx.graphics.getHeight() / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT);
        rightPaddle = new Paddle(Gdx.graphics.getWidth()-PADDLE_OFFSET-PADDLE_WIDTH, (Gdx.graphics.getHeight() / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT);
        shapeRenderer = new ShapeRenderer();
        ball = new Ball(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, BALL_RADIUS);
        font = new BitmapFont();
        gameover = false;
    }

    @Override
    public void handleInput(float deltaTime) {
        if(Gdx.input.isTouched()) {
            if (Gdx.input.getX() < PADDLE_OFFSET * 2) {
                leftPaddle.move(Gdx.input.getY());
            }
            if (Gdx.input.getX() > Gdx.graphics.getWidth() - PADDLE_OFFSET * 2) {
                rightPaddle.move(Gdx.input.getY());
            }
        } else {
            leftPaddle.resetVelocity();
            rightPaddle.resetVelocity();
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput(deltaTime);

        leftPaddle.update(deltaTime);
        rightPaddle.update(deltaTime);
        ball.update(deltaTime);

        // Check if ball collides with one of the paddles
        if(rightPaddle.getBounds().overlaps(ball.getBounds())) {
            ball.collision(Sides.RIGHT);
        } else if(leftPaddle.getBounds().overlaps(ball.getBounds())) {
            ball.collision(Sides.LEFT);
        }

        // Check if ball collides with top or bottom wall
        if (ball.getY() < 0) {
            ball.collision(Sides.BOTTOM);
        } else if (ball.getY() + ball.getDiameter() > Gdx.graphics.getHeight()) {
            ball.collision(Sides.TOP);
        }

        if (!isGameover()) {
            // Check if ball is out of screen
            if (ball.getX() > Gdx.graphics.getWidth()) {
                player1Score = player1Score + 1;
                ball.reset(Sides.RIGHT);
            } else if (ball.getX() + ball.getDiameter() < 0) {
                player2Score = player2Score + 1;
                ball.reset(Sides.LEFT);
            }
        }
    }


    private boolean isGameover() {
        if (player1Score >= WIN_SCORE || player2Score >= WIN_SCORE) {
            gameover = true;
        }
        return gameover;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(leftPaddle.getX(), leftPaddle.getY(), leftPaddle.getWidth(), leftPaddle.getHeight());
        shapeRenderer.rect(rightPaddle.getX(), rightPaddle.getY(), rightPaddle.getWidth(), rightPaddle.getHeight());
        if (!isGameover()) shapeRenderer.circle(ball.getX() + ball.getRadius(), ball.getY() + ball.getRadius(), ball.getRadius());
        shapeRenderer.end();
        sb.end();

        sb.begin();
        font.getData().setScale(2);
        font.setColor(Color.WHITE);
        font.draw(sb, Integer.toString(player1Score), Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() - 40 );
        font.draw(sb, Integer.toString(player2Score), Gdx.graphics.getWidth() / 2 + 100, Gdx.graphics.getHeight() - 40);
        sb.end();
    }
}
