package imericxu.components;

import imericxu.gui.canvases.CanvasGame;
import javafx.animation.AnimationTimer;

public class TimerRepaint extends AnimationTimer
{
    private final CanvasGame canvas;
    
    public TimerRepaint(CanvasGame canvas)
    {
        this.canvas = canvas;
    }
    
    @Override
    public void handle(long l)
    {
        canvas.draw();
    }
}
