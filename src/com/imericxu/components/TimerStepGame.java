package com.imericxu.components;

import javafx.animation.AnimationTimer;

public class TimerStepGame extends AnimationTimer
{
    private final Snake snake;
    private final Apple apple;
    private final GameEndListener listener;
    private long lastUpdate;
    private long delay;
    
    public TimerStepGame(Snake snake, Apple apple, int area, GameEndListener listener)
    {
        this.snake = snake;
        this.apple = apple;
        this.listener = listener;
        lastUpdate = 0;
        delay = (long) (1.5715e8 * Math.pow(0.99893, area) + 3.8915e7);
    }
    
    @Override
    public void handle(long l)
    {
        if (l - lastUpdate >= delay)
        {
            snake.setDir(snake.getTempDir());
            snake.move();
            if (snake.isTouchingSelf() || snake.isOutOfBounds()) listener.gameOver();
            if (apple.isTouchingSnake())
            {
                apple.regen();
                snake.increaseLength();
                delay *= 0.965;
            }
            lastUpdate = l;
        }
    }
}
