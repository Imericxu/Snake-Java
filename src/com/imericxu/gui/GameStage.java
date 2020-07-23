package com.imericxu.gui;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameStage extends Stage
{
    private final int ROWS;
    private final int COLS;
    private final int CELL_SIZE;
    
    public GameStage(int rows, int cols)
    {
        ROWS = rows;
        COLS = cols;
        CELL_SIZE = calculateCellSize(rows, cols);
        
        final int width = cols * CELL_SIZE;
        final int height = rows * CELL_SIZE;
        
        final Canvas cnvsGrid = new Canvas(width, height);
        GraphicsContext gcGrid = cnvsGrid.getGraphicsContext2D();
        drawGrid(gcGrid);
        
        final Canvas cnvsApples = new Canvas(width, height);
        GraphicsContext gcApples = cnvsApples.getGraphicsContext2D();
        
        StackPane root = new StackPane(cnvsGrid, cnvsApples);
        Scene scene = new Scene(root);
        setScene(scene);
        setTitle("Snake " + rows + " × " + cols);
    }
    
    private void drawGrid(GraphicsContext gc)
    {
        gc.setFill(Colors.BACKGROUND);
        gc.fillRect(0, 0, CELL_SIZE * COLS, CELL_SIZE * ROWS);
        
        gc.setFill(Colors.CHECKERBOARD);
        for (int row = 0; row < ROWS; ++row)
        {
            for (int col = 0; col < COLS; ++col)
            {
                if ((row + col) % 2 == 0)
                {
                    int x = col * CELL_SIZE;
                    int y = row * CELL_SIZE;
                    gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }
    
    private int calculateCellSize(int rows, int cols)
    {
        Rectangle2D screen = Screen.getPrimary().getBounds();
        double screenRatio = screen.getWidth() / screen.getHeight();
        double mapRatio = (double) cols / rows;
        
        if (screenRatio > mapRatio)
        {
            return (int) ((screen.getHeight() - 100) / rows);
        }
        else
        {
            return (int) (screen.getWidth() / cols);
        }
    }
}
