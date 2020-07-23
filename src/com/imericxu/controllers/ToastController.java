package com.imericxu.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ToastController
{
    @FXML
    private Text txtMessage;
    
    public void init(String message)
    {
        txtMessage.setText(message);
    }
}
