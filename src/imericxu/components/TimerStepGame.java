package imericxu.components;

import imericxu.components.core.Apple;
import imericxu.components.core.Snake;
import javafx.animation.AnimationTimer;

public class TimerStepGame extends AnimationTimer
{
    private final Snake snake;
    private final Apple apple;
    private final GameEndListener listener;
    private final int area;
    private long lastUpdate;
    private long delay;
    
    public TimerStepGame(Snake snake, Apple apple, int area, GameEndListener listener)
    {
        this.snake = snake;
        this.apple = apple;
        this.listener = listener;
        this.area = area;
        lastUpdate = 0;
        delay = (long) (1.5715e8 * Math.pow(0.99893, area) + 3.8915e7);
    }
    
    @Override
    public void handle(long l)
    {
        if (l - lastUpdate >= delay)
        {
            Dir tempDir = snake.nextDir();
            if (tempDir != null)
            {
                snake.setDir(tempDir);
            }
            snake.move();
            if (snake.isTouchingSelf() || snake.isOutOfBounds()) listener.gameOver();
            if (snake.getPath().size() == area) listener.win();
            if (apple.isBeingEaten())
            {
                apple.regen();
                snake.increaseLength();
                delay *= 0.995;
            }
            lastUpdate = l;
        }
    }
}
