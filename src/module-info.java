module Snake {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens com.imericxu.controllers to javafx.fxml;
    exports com.imericxu;
}