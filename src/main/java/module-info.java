module com.example.idealjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires commons.math3;
    requires jama;


    opens com.example.idealjavafx to javafx.fxml;
    exports com.example.idealjavafx;
}