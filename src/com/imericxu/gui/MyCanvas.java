package com.imericxu.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class MyCanvas extends Canvas
{
    protected final int cellSize;
    protected final int rows;
    protected final int cols;
    protected final GraphicsContext gc;
    
    protected MyCanvas(int width, int height, int cellSize)
    {
        super(width, height);
        this.cellSize = cellSize;
        rows = height / cellSize;
        cols = width / cellSize;
        gc = getGraphicsContext2D();
    }
    
    protected void fillSquare(int row, int col)
    {
        int x = col * cellSize + 1;
        int y = row * cellSize + 1;
        gc.fillRect(x, y, cellSize - 1, cellSize - 1);
    }
    
    protected void fillSquare(int row, int col, Color color)
    {
        gc.setFill(color);
        int x = col * cellSize + 1;
        int y = row * cellSize + 1;
        gc.fillRect(x, y, cellSize - 1, cellSize - 1);
    }
}
