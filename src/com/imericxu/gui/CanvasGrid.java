package com.imericxu.gui;

public class CanvasGrid extends MyCanvas
{
    public CanvasGrid(int width, int height, int cellSize)
    {
        super(width, height, cellSize);
        drawGrid();
    }
    
    private void drawGrid()
    {
        gc.setFill(Colors.BACKGROUND);
        gc.fillRect(0, 0, cellSize * cols, cellSize * rows);
        
        gc.setFill(Colors.CHECKERBOARD);
        for (int row = 0; row < rows; ++row)
        {
            for (int col = 0; col < cols; ++col)
            {
                if ((row + col) % 2 == 0) fillSquare(row, col);
            }
        }
    }
}
