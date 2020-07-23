package com.imericxu.gui;

import com.imericxu.components.Pos;
import com.imericxu.components.Snake;
import com.imericxu.components.SnakeKeys;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameStage extends Stage
{
    private final int rows;
    private final int cols;
    private final int cellSize;
    private final ArrayList<Pos> snakePath;
    
    public GameStage(int rows, int cols)
    {
        // Field initialization
        this.rows = rows;
        this.cols = cols;
        cellSize = calculateCellSize(rows, cols);
        Snake snake = new Snake(rows / 2, cols / 2);
        snakePath = snake.getPath();
        
        // Canvases
        final int width = cols * cellSize;
        final int height = rows * cellSize;
        
        final Canvas cnvsGrid = new Canvas(width, height);
        GraphicsContext gcGrid = cnvsGrid.getGraphicsContext2D();
        drawGrid(gcGrid);
        
        final Canvas cnvsSnake = new Canvas(width, height);
        GraphicsContext gcSnake = cnvsSnake.getGraphicsContext2D();
        
        // Stage instantiation
        StackPane root = new StackPane(cnvsGrid, cnvsSnake);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(new SnakeKeys(snake));
        setScene(scene);
        setTitle("Snake " + rows + " × " + cols);
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
