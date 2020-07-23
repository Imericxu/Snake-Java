package com.imericxu.components;

import com.imericxu.controllers.ToastController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public final class Toast
{
    private static final String PATH = "/com/imericxu/resources/toast.fxml";
    
    public static void makeToast(Stage ownerStage, String message) throws IOException
    {
        makeToast(ownerStage, message, 1000, 200, 200);
    }
    
    public static void makeToast(Stage ownerStage, String message, int showTime, int fadeInTime,
            int fadeOutTime) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Toast.class.getResource(PATH));
        StackPane root = loader.load();
        root.setOpacity(0);
        ToastController controller = loader.getController();
        controller.init(message);
        
        Popup popup = new Popup();
        popup.getContent().add(root);
        popup.show(ownerStage);
        
        Timeline fadeInTimeline = new Timeline(new KeyFrame(Duration.millis(fadeInTime),
                new KeyValue(root.opacityProperty(), 1, Interpolator.EASE_IN)));
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
            Timeline fadeOutTimeline = new Timeline(new KeyFrame(Duration.millis(fadeOutTime),
                    new KeyValue(root.opacityProperty(), 0, Interpolator.EASE_OUT)));
            fadeOutTimeline.setOnFinished(ae2 -> popup.hide());
            fadeOutTimeline.play();
        }).start());
        fadeInTimeline.play();
    }
}