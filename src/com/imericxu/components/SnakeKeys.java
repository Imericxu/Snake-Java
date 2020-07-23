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
        case UP -> snake.setDirection(Direction.NORTH);
        case DOWN -> snake.setDirection(Direction.SOUTH);
        case LEFT -> snake.setDirection(Direction.WEST);
        case RIGHT -> snake.setDirection(Direction.EAST);
        }
    }
}
