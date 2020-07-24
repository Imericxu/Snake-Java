package imericxu.gui;

import imericxu.components.*;
import imericxu.components.core.Food;
import imericxu.components.core.Snake;
import imericxu.gui.canvases.CanvasGame;
import imericxu.gui.canvases.CanvasGrid;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.Set;

public class StageGame extends Stage implements GameEndListener
{
    private static final Text txtScore = new Text();
    private static final PauseTransition PAUSE_SCORE = new PauseTransition(Duration.seconds(0.75));
    private static final FadeTransition FADE_IN_SCORE = new FadeTransition(Duration.seconds(0.4),
            txtScore);
    private static final FadeTransition FADE_OUT_SCORE = new FadeTransition(Duration.seconds(0.4),
            txtScore);
    private final AnimationTimer timerConstant;
    private final AnimationTimer timerStepGame;
    private int score;
    
    public StageGame(int rows, int cols)
    {
        score = 1;
        int cellSize = calculateCellSize(rows, cols);
        Snake snake = new Snake(rows, cols);
        Food food = new Food(rows, cols, snake);
        
        // Canvases
        final int width = cols * cellSize;
        final int height = rows * cellSize;
        
        final Canvas cnvsGrid = new CanvasGrid(width, height, cellSize);
        final CanvasGame cnvsGame = new CanvasGame(width, height, cellSize, snake, food);
        
        // Animation Timers
        timerConstant = new TimerRepaint(cnvsGame);
        GameEndListener listener = this;
        timerStepGame = new TimerStepGame(snake, food, rows * cols, listener);
        runTimers(true);
        
        // Score text
        txtScore.setFill(Color.WHITE);
        txtScore.setFont(Font.font("Trebuchet MS", 100));
        txtScore.setOpacity(0);
        StackPane.setMargin(txtScore, new Insets(10, 10, 0, 30));
        FADE_IN_SCORE.setToValue(1);
        FADE_OUT_SCORE.setToValue(0);
        FADE_IN_SCORE.setOnFinished(e -> PAUSE_SCORE.play());
        PAUSE_SCORE.setOnFinished(e -> FADE_OUT_SCORE.play());
        
        // Stage instantiation
        StackPane root = new StackPane(cnvsGrid, cnvsGame, txtScore);
        Scene scene = new Scene(root);
        
        Set<KeyCode> pressedKeys = new HashSet<>();
        scene.setOnKeyPressed(new KeyDownListener(pressedKeys, snake));
        scene.setOnKeyReleased(new KeyUpLIstener(pressedKeys));
        
        setScene(scene);
        setTitle("Snake " + rows + " × " + cols);
    }
    
    private int calculateCellSize(int rows, int cols)
    {
        Rectangle2D screen = Screen.getPrimary().getBounds();
        double screenRatio = screen.getWidth() / screen.getHeight();
        double mapRatio = (double) cols / rows;
        
        if (screenRatio > mapRatio)
        {
            return (int) ((screen.getHeight() - 100) / rows);
        }
        else
        {
            return (int) (screen.getWidth() / cols);
        }
    }
    
    @Override
    public void gameOver()
    {
        runTimers(false);
    }
    
    @Override
    public void win()
    {
        runTimers(false);
    }
    
    @Override
    public void increaseScore(boolean isLeft)
    {
        ++score;
        
        txtScore.setText(String.valueOf(score));
        if (isLeft) StackPane.setAlignment(txtScore, Pos.TOP_RIGHT);
        else StackPane.setAlignment(txtScore, Pos.TOP_LEFT);
        
        FADE_IN_SCORE.play();
    }
    
    private void runTimers(boolean doRun)
    {
        if (doRun)
        {
            timerConstant.start();
            timerStepGame.start();
        }
        else
        {
            timerConstant.stop();
            timerStepGame.stop();
        }
    }
}
