package com.imericxu.components;

import javafx.animation.AnimationTimer;

public class TimerStepGame extends AnimationTimer
{
    private final Snake snake;
    private final Apple apple;
    private long lastUpdate;
    
    public TimerStepGame(Snake snake, Apple apple)
    {
        this.snake = snake;
        this.apple = apple;
        lastUpdate = 0;
    }
    
    @Override
    public void handle(long l)
    {
        if (l - lastUpdate >= 100_000_000)
        {
            snake.setDir(snake.getTempDir());
            snake.move();
            if (apple.isTouchingSnake())
            {
                apple.regen();
                snake.increaseLength();
            }
            lastUpdate = l;
        }
    }
}
