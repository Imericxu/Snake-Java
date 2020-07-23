package com.imericxu.gui;

import com.imericxu.components.Direction;
import com.imericxu.components.Snake;
import javafx.animation.AnimationTimer;

public class ATimerStepGame extends AnimationTimer
{
    private final Snake snake;
    private long lastUpdate;
    
    public ATimerStepGame(Snake snake)
    {
        this.snake = snake;
        lastUpdate = 0;
    }
    
    @Override
    public void handle(long l)
    {
        if (lastUpdate >= 500_000_000)
        {
            Direction dir = snake.getDirection();
            
            lastUpdate = 0;
        }
    }
}
