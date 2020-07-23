package com.imericxu.components;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class SnakeKeys implements EventHandler<KeyEvent>
{
    private final Snake snake;
    
    public SnakeKeys(Snake snake)
    {
        this.snake = snake;
    }
    
    @Override
    public void handle(KeyEvent event)
    {
        switch (event.getCode())
        {
        case UP -> {
            if (snake.getDir() != Dir.DOWN) snake.setTempDir(Dir.UP);
        }
        case DOWN -> {
            if (snake.getDir() != Dir.UP) snake.setTempDir(Dir.DOWN);
        }
        case LEFT -> {
            if (snake.getDir() != Dir.RIGHT) snake.setTempDir(Dir.LEFT);
        }
        case RIGHT -> {
            if (snake.getDir() != Dir.LEFT) snake.setTempDir(Dir.RIGHT);
        }
        }
    }
}
