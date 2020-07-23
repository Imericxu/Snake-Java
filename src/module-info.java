module Snake {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens imericxu.gui.controllers to javafx.fxml;
    exports imericxu;
}