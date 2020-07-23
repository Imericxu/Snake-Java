package com.imericxu.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GridCanvas extends Canvas
{
    private final int cellSize;
    private final int rows;
    private final int cols;
    
    public GridCanvas(double width, double height, int cellSize)
    {
        super(width, height);
        this.cellSize = cellSize;
        rows = (int) (height / cellSize);
        cols = (int) (width / cellSize);
        GraphicsContext gc = getGraphicsContext2D();
        drawGrid(gc);
    }
    
    private void drawGrid(GraphicsContext gc)
    {
        gc.setFill(Colors.BACKGROUND);
        gc.fillRect(0, 0, cellSize * cols, cellSize * rows);
        
        gc.setFill(Colors.CHECKERBOARD);
        for (int row = 0; row < rows; ++row)
        {
            for (int col = 0; col < cols; ++col)
            {
                if ((row + col) % 2 == 0)
                {
                    int x = col * cellSize;
                    int y = row * cellSize;
                    gc.fillRect(x, y, cellSize, cellSize);
                }
            }
        }
    }
}
