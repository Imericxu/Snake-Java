package imericxu.gui.other;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public final class Colors
{
    public static final Color BACKGROUND = Color.rgb(156, 255, 122);
    public static final LinearGradient FOREGROUND = new LinearGradient(0, 0, 1, 1, true,
            CycleMethod.NO_CYCLE, new Stop(0, Color.rgb(156, 255, 122)), new Stop(1, Color.rgb(49
            , 148, 90)));
    public static final Color SNAKE = Color.rgb(255, 207, 64);
    public static final Color FOOD = Color.rgb(0, 123, 137);
}
