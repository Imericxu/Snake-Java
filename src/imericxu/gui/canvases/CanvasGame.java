package imericxu.gui.canvases;

import imericxu.components.Pos;
import imericxu.components.core.Food;
import imericxu.components.core.Snake;
import imericxu.gui.other.Colors;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CanvasGame extends MyCanvas
{
    private final ArrayList<Pos> path;
    private final Food food;
    
    public CanvasGame(int width, int height, int cellSize, Snake snake, Food food)
    {
        super(width, height, cellSize);
        path = snake.getPath();
        this.food = food;
    }
    
    public void draw()
    {
        gc.clearRect(0, 0, getWidth(), getHeight());
        
        fillSquare(food.getRow(), food.getCol(), Colors.FOOD);
        
        Color colorSnake = Colors.SNAKE;
        double brightnessFactor = 0.998 / (1 + Math.exp(-0.02 * (path.size() + 165)));
    
        for (Pos pos : path)
        {
            gc.setFill(colorSnake);
//            fillSquare(pos.getRow(), pos.getCol());
            int x = pos.getCol() * cellSize;
            int y = pos.getRow() * cellSize;
            gc.fillRect(x, y, cellSize + 1, cellSize + 1);
            colorSnake = colorSnake.deriveColor(-3, 1, brightnessFactor, 1);
        }
    }
}
