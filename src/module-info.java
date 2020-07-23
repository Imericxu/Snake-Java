module Snake {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens com.imericxu.gui.controllers to javafx.fxml;
    exports com.imericxu;
}