package imericxu.gui.other;

import imericxu.gui.controllers.ToastController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.IOException;

public final class Toast
{
    private static final String PATH = "/imericxu/resources/toast.fxml";
    
    public static void makeToast(Stage ownerStage, String message) throws IOException
    {
        makeToast(ownerStage, message, 1000, 200, 200);
    }
    
    public static void makeToast(Stage ownerStage, String message, int showTime, int fadeInTime,
            int fadeOutTime) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Toast.class.getResource(PATH));
        StackPane root = loader.load();
        ToastController controller = loader.getController();
        controller.init(message);
        
        Popup popup = new Popup();
        popup.getContent().add(root);
        popup.show(ownerStage);
        
        fadeWindow(popup, fadeInTime, showTime, fadeOutTime);
    }
    
    /**
     * @param window   window to fade
     * @param inTime   fade-in time in milliseconds
     * @param showTime show time in milliseconds
     * @param outTime  fade-out time in milliseconds
     */
    public static void fadeWindow(Window window, int inTime, int showTime, int outTime)
    {
        window.setOpacity(0);
        Timeline fadeInTimeline = new Timeline(new KeyFrame(Duration.millis(inTime),
                new KeyValue(window.opacityProperty(), 1, Interpolator.EASE_IN)));
        fadeInTimeline.setOnFinished(ae -> new Thread(() ->
        {
            try
            {
                Thread.sleep(showTime);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            Timeline fadeOutTimeline = new Timeline(new KeyFrame(Duration.millis(outTime),
                    new KeyValue(window.opacityProperty(), 0, Interpolator.EASE_OUT)));
            fadeOutTimeline.setOnFinished(ae2 -> window.hide());
            fadeOutTimeline.play();
        }).start());
        fadeInTimeline.play();
    }
}