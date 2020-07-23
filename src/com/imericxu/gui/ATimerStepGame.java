package com.imericxu.gui;

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
        if (l - lastUpdate >= 250_000_000)
        {
            snake.move();
            lastUpdate = l;
        }
    }
}
