package com.imericxu.gui.canvases;

import com.imericxu.components.core.Apple;
import com.imericxu.components.Pos;
import com.imericxu.components.core.Snake;
import com.imericxu.gui.other.Colors;

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
