package com.imericxu.controllers;

import com.imericxu.components.GameStage;
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
    private int rows, cols;
    
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
        
        fieldRows.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), null, integerFilter));
        fieldCols.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), null, integerFilter));
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
     * Called when "Start" button is pressed<br/>
     * Attempts to launch the game
     */
    public void launchGame() throws IOException
    {
        Stage root = (Stage) fieldRows.getScene().getWindow();
        if (isValidInput(root))
        {
            Stage gameStage = new GameStage(rows, cols);
            gameStage.show();
        }
    }
    
    private boolean isValidInput(Stage root) throws IOException
    {
        try
        {
            rows = Integer.parseInt(fieldRows.getText());
        }
        catch (NumberFormatException e)
        {
            Toast.makeToast(root, "Please enter rows");
            fieldRows.requestFocus();
            return false;
        }
    
        try
        {
            cols = Integer.parseInt(fieldCols.getText());
        }
        catch (NumberFormatException e)
        {
            Toast.makeToast(root, "Please enter columns", 1000, 200, 200);
            fieldCols.requestFocus();
            return false;
        }
    
        if (rows < 5 || rows > 50)
        {
            Toast.makeToast(root, "Enter a row between 5 and 50");
            fieldRows.requestFocus();
            return false;
        }
        else if (cols < 5 || cols > 50)
        {
            Toast.makeToast(root, "Enter a column between 5 and 50");
            fieldCols.requestFocus();
            return false;
        }
        
        return true;
    }
}
