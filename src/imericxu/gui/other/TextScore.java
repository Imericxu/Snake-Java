package imericxu.gui.other;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TextScore extends Text
{
    private final FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.4), this);
    private final PauseTransition pause = new PauseTransition(Duration.seconds(0.75));
    private final FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.4), this);
    
    public TextScore()
    {
        setFill(Color.WHITE);
        setFont(Font.font("Trebuchet MS", 100));
        setOpacity(0);
        StackPane.setMargin(this, new Insets(10, 10, 0, 30));
        
        fadeIn.setToValue(1);
        fadeOut.setToValue(0);
        fadeIn.setOnFinished(e -> pause.play());
        pause.setOnFinished(e -> fadeOut.play());
    }
    
    public void setAndShow(int score, boolean isLeft)
    {
        if (isLeft) StackPane.setAlignment(this, Pos.TOP_RIGHT);
        else StackPane.setAlignment(this, Pos.TOP_LEFT);
        setText(String.valueOf(score));
        fadeIn.play();
    }
}
