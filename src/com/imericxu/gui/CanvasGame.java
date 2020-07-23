package com.imericxu.gui;

import com.imericxu.components.Pos;
import com.imericxu.components.Snake;

import java.util.ArrayList;

public class CanvasGame extends MyCanvas
{
    private final ArrayList<Pos> path;
    
    public CanvasGame(int width, int height, int cellSize, Snake snake)
    {
        super(width, height, cellSize);
        path = snake.getPath();
    }
    
    public void drawSnake()
    {
        gc.clearRect(0, 0, getWidth(), getHeight());
        gc.setFill(Colors.SNAKE);
        for (Pos pos : path)
        {
            fillSquare(pos.getRow(), pos.getCol());
        }
    }
}
