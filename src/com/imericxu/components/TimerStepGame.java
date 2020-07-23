package com.imericxu.components;

import javafx.animation.AnimationTimer;

public class TimerStepGame extends AnimationTimer
{
    private final Snake snake;
    private long lastUpdate;
    
    public TimerStepGame(Snake snake)
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
