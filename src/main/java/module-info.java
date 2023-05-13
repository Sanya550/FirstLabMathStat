module com.example.idealjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires commons.math3;
    requires jama;
    requires lombok;


    opens com.example.idealjavafx to javafx.fxml;
    exports com.example.idealjavafx;
    exports com.example.idealjavafx.models;
    opens com.example.idealjavafx.models to javafx.fxml;


    exports com.example.idealjavafx.graphics to javafx.graphics;
    requires javafx.graphics;
}