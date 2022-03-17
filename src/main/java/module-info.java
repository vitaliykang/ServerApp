module com.example.serverapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.example.serverapp to javafx.fxml;
    exports com.example.serverapp;
}