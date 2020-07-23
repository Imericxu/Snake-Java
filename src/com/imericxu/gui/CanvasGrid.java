package com.imericxu.gui;

import javafx.scene.paint.Color;

public class CanvasGrid extends MyCanvas
{
    public CanvasGrid(int width, int height, int cellSize)
    {
        super(width, height, cellSize);
        drawGrid();
    }
    
    private void drawGrid()
    {
        gc.setFill(Colors.FOREGROUND);
        gc.fillRect(0, 0, getWidth(), getHeight());
        
        gc.setStroke(Colors.BACKGROUND);
        gc.setLineWidth(1.5);
        for (int row = 1; row < rows; ++row)
        {
            double y = row * cellSize + .5;
            gc.strokeLine(0, y, getWidth(), y);
        }
        
        for (int col = 1; col < cols; ++col)
        {
            double x = col * cellSize + .5;
            gc.strokeLine(x, 0, x, getHeight());
        }
    }
}
