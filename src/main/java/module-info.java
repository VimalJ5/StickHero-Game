module com.example.stickhero {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires junit;


    opens com.example.stickhero to javafx.fxml;
    exports com.example.stickhero;
}