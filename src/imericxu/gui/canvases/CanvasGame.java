package imericxu.gui.canvases;

import imericxu.components.core.Apple;
import imericxu.components.Pos;
import imericxu.components.core.Snake;
import imericxu.gui.other.Colors;

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
        
        gc.setFill(Colors.SNAKE);
        for (Pos pos : path)
        {
            fillSquare(pos.getRow(), pos.getCol());
        }
    }
}
