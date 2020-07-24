package imericxu.gui;

import imericxu.components.*;
import imericxu.components.core.Apple;
import imericxu.components.core.Snake;
import imericxu.gui.canvases.CanvasGame;
import imericxu.gui.canvases.CanvasGrid;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class StageGame extends Stage implements GameEndListener
{
    private final AnimationTimer timerConstant;
    private final AnimationTimer timerStepGame;
    
    public StageGame(int rows, int cols)
    {
        int cellSize = calculateCellSize(rows, cols);
        Snake snake = new Snake(rows, cols);
        Apple apple = new Apple(rows, cols, snake);
        
        // Canvases
        final int width = cols * cellSize;
        final int height = rows * cellSize;
        
        final Canvas cnvsGrid = new CanvasGrid(width, height, cellSize);
        final CanvasGame cnvsGame = new CanvasGame(width, height, cellSize, snake, apple);
        
        // Animation Timers
        timerConstant = new TimerRepaint(cnvsGame);
        GameEndListener listener = this;
        timerStepGame = new TimerStepGame(snake, apple, rows * cols, listener);
        runTimers(true);
        
        // Stage instantiation
        StackPane root = new StackPane(cnvsGrid, cnvsGame);
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
