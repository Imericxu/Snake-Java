package com.imericxu.gui;

import javafx.animation.AnimationTimer;

public class ATimerRender extends AnimationTimer
{
    private final CanvasGame canvas;
    
    public ATimerRender(CanvasGame canvas)
    {
        this.canvas = canvas;
    }
    
    @Override
    public void handle(long l)
    {
        canvas.drawSnake();
    }
}
