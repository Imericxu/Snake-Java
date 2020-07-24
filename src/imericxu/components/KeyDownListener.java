package imericxu.components;

import imericxu.components.core.Snake;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Set;

public class KeyDownListener implements EventHandler<KeyEvent>
{
    private final Set<KeyCode> pressedKeys;
    private final Snake snake;
    
    public KeyDownListener(Set<KeyCode> pressedKeys, Snake snake)
    {
        this.pressedKeys = pressedKeys;
        this.snake = snake;
    }
    
    @Override
    public void handle(KeyEvent event)
    {
        KeyCode key = event.getCode();
        
        switch (key)
        {
        case UP -> {
            if (!pressedKeys.contains(key))
            {
                pressedKeys.add(key);
                snake.pushQueue(Dir.UP);
            }
        }
        case DOWN -> {
            if (!pressedKeys.contains(key))
            {
                pressedKeys.add(key);
                snake.pushQueue(Dir.DOWN);
            }
        }
        case LEFT -> {
            if (!pressedKeys.contains(key))
            {
                pressedKeys.add(key);
                snake.pushQueue(Dir.LEFT);
            }
        }
        case RIGHT -> {
            if (!pressedKeys.contains(key))
            {
                pressedKeys.add(key);
                snake.pushQueue(Dir.RIGHT);
            }
        }
        }
    }
}
