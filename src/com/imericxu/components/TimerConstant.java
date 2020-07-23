package com.imericxu.components;

import com.imericxu.gui.CanvasGame;
import javafx.animation.AnimationTimer;

public class TimerConstant extends AnimationTimer
{
    private final CanvasGame canvas;
    
    public TimerConstant(CanvasGame canvas)
    {
        this.canvas = canvas;
    }
    
    @Override
    public void handle(long l)
    {
        canvas.drawSnake();
    }
}
