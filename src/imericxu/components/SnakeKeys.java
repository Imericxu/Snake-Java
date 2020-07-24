package imericxu.components;

import imericxu.components.core.Snake;
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
        case UP -> snake.pushQueue(Dir.UP);
        case DOWN -> snake.pushQueue(Dir.DOWN);
        case LEFT -> snake.pushQueue(Dir.LEFT);
        case RIGHT -> snake.pushQueue(Dir.RIGHT);
        }
    }
}
