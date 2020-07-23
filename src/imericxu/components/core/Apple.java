package imericxu.components.core;

import imericxu.components.Pos;

public class Apple
{
    private final int rows;
    private final int cols;
    private final Snake snake;
    private int row;
    private int col;
    
    public Apple(int rows, int cols, Snake snake)
    {
        this.rows = rows;
        this.cols = cols;
        this.snake = snake;
        regen();
    }
    
    public void regen()
    {
        do
        {
            row = (int) (Math.random() * rows);
            col = (int) (Math.random() * cols);
        } while (isTouchingSnake());
    }
    
    public boolean isTouchingSnake()
    {
        for (Pos pos : snake.getPath())
        {
            if (row == pos.getRow() && col == pos.getCol()) return true;
        }
        return false;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }
}
