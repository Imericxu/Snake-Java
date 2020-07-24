package imericxu.gui;

import imericxu.components.*;
import imericxu.components.core.Food;
import imericxu.components.core.Snake;
import imericxu.gui.canvases.CanvasGame;
import imericxu.gui.canvases.CanvasGrid;
import imericxu.gui.other.TextScore;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class StageGame extends Stage implements GameEndListener
{
    private static final TextScore TXT_SCORE = new TextScore();
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
        
        // Stage instantiation
        StackPane root = new StackPane(cnvsGrid, cnvsGame, TXT_SCORE);
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
        TXT_SCORE.setAndShow(++score, isLeft);
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
