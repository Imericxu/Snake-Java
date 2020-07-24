package imericxu.gui.canvases;

import imericxu.components.Pos;
import imericxu.components.core.Apple;
import imericxu.components.core.Snake;
import imericxu.gui.other.Colors;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CanvasGame extends MyCanvas
{
    private final ArrayList<Pos> path;
    private final Apple apple;
    
    public CanvasGame(int width, int height, int cellSize, Snake snake, Apple apple)
    {
        super(width, height, cellSize);
        path = snake.getPath();
        this.apple = apple;
    }
    
    public void draw()
    {
        gc.clearRect(0, 0, getWidth(), getHeight());
        
        fillSquare(apple.getRow(), apple.getCol(), Colors.APPLE);
        
        Color colorSnake = Colors.SNAKE;
        for (Pos pos : path)
        {
            gc.setFill(colorSnake);
            fillSquare(pos.getRow(), pos.getCol());
            double brightnessFactor = 0.998 / (1 + Math.exp(-0.02 * (path.size() + 165)));
            colorSnake = colorSnake.deriveColor(-3, 1, brightnessFactor, 1);
        }
    }
}
