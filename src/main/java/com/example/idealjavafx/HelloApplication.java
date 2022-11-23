package com.example.idealjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class HelloApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("Chart.css");
        stage.setTitle("App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}