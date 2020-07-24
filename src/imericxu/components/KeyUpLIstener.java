package imericxu.components;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Set;

public class KeyUpLIstener implements EventHandler<KeyEvent>
{
    private final Set<KeyCode> pressedKeys;
    
    public KeyUpLIstener(Set<KeyCode> pressedKeys)
    {
        this.pressedKeys = pressedKeys;
    }
    
    @Override
    public void handle(KeyEvent keyEvent)
    {
        KeyCode key = keyEvent.getCode();
        
        switch (key)
        {
        case UP, DOWN, LEFT, RIGHT -> pressedKeys.remove(key);
        }
    }
}
