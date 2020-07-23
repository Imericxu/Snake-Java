package com.imericxu.gui;

import com.imericxu.components.Apple;
import com.imericxu.components.Pos;
import com.imericxu.components.Snake;

import java.util.ArrayList;

public class CanvasGame extends MyCanvas
{
    private final ArrayList<Pos> path;
    private Apple apple;
    
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
