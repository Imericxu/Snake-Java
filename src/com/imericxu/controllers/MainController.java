package com.imericxu.controllers;

import com.imericxu.components.Toast;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.util.function.UnaryOperator;

public class MainController
{
    @FXML
    private TextField fieldRows;
    @FXML
    private TextField fieldCols;
    
    @FXML
    public void initialize()
    {
        UnaryOperator<TextFormatter.Change> integerFilter = change ->
        {
            String newText = change.getControlNewText();
            if (newText.matches("\\d+"))
            {
                return change;
            }
            return null;
        };
        
        fieldRows.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), null,
                integerFilter));
        fieldCols.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), null,
                integerFilter));
    }
    
    /**
     * Removes focus from text fields when clicked outside
     */
    public void sceneClicked()
    {
        fieldRows.getScene().getRoot().requestFocus();
    }
    
    /**
     * Called when "Enter" key is pressed in text fields<br/>
     * Attempts to launch the game
     */
    public void launchFromEnter() throws IOException
    {
        Stage root = (Stage) fieldRows.getScene().getWindow();
        int rows, cols;
        
        try
        {
            rows = Integer.parseInt(fieldRows.getText());
        }
        catch (NumberFormatException e)
        {
            Toast.makeToast(root, "Please enter rows");
            return;
        }
        
        try
        {
            cols = Integer.parseInt(fieldCols.getText());
        }
        catch (NumberFormatException e)
        {
            Toast.makeToast(root, "Please enter columns", 1000, 200, 200);
            return;
        }
        
        if (rows < 5 || rows > 50)
        {
            Toast.makeToast(root, "Enter a row between 5 and 50");
        }
        else if (cols < 5 || cols > 50)
        {
            Toast.makeToast(root, "Enter a column between 5 and 50");
            fieldRows.requestFocus();
        }
        else
        {
            fieldRows.getScene().getRoot().requestFocus();
            // Start game
        }
    }
    
    /**
     * Attempts to launch game when button is clicked
     */
    public void launchFromButton()
    {
    
    }
}
