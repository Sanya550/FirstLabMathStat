package com.example.idealjavafx.graphics;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HeatMapVisualization extends Application {
    Color black = Color.rgb(0, 0, 0);
    Color lightBlack = Color.rgb(66, 65, 61);
    Color grey = Color.rgb(134, 128, 113);
    Color lightGrey = Color.rgb(238, 230, 202);
    Color white = Color.rgb(255, 255, 255);

    private List<List<Double>> list;

    public void setDataList(List<List<Double>> dataList) {
        this.list = dataList;
    }

    @Override
    public void start(Stage primaryStage) {
        final int MATRIX_WIDTH = list.get(0).size();
        final int MATRIX_HEIGHT = list.size();

        double[][] matrix = new double[MATRIX_WIDTH][MATRIX_HEIGHT];
        for (int i = 0; i < MATRIX_WIDTH; i++) {
            for (int j = 0; j < MATRIX_HEIGHT; j++) {
                matrix[i][j] = list.get(j).get(i);
            }
        }
        GridPane gridPane = createHeatMap(matrix, list);
        primaryStage.setScene(new Scene(gridPane));
        primaryStage.show();
    }

    private GridPane createHeatMap(double[][] matrix, List<List<Double>> list) {
        double heightOfScreen = 1040;
        double widthOfScreen = 1840;
        GridPane gridPane = new GridPane();

        for (int row = 0; row < matrix[0].length; row++) {
            double max = list.get(row).stream().mapToDouble(a -> a).max().orElseThrow();
            double min = list.get(row).stream().mapToDouble(a -> a).min().orElseThrow();
            double iteration = (max - min) / 5;
            double first = min + iteration;
            double second = first + iteration;
            double third = second + iteration;
            double fourth = third + iteration;
            for (int col = 0; col < matrix.length; col++) {
                Rectangle rectangle = new Rectangle(widthOfScreen / list.size() / 1.5, heightOfScreen / list.get(0).size() / 1.5);
                double dataValue = matrix[col][row];
                if (dataValue <= first) {
                    rectangle.setFill(white);
                } else if (dataValue <= second) {
                    rectangle.setFill(lightGrey);
                } else if (dataValue <= third) {
                    rectangle.setFill(grey);
                } else if (dataValue <= fourth) {
                    rectangle.setFill(lightBlack);
                } else {
                    rectangle.setFill(black);
                }
                gridPane.add(rectangle, row, col);
            }
        }
        return gridPane;
    }

    public static void launchHeatMapVisualization(List<List<Double>> list) {
        Platform.runLater(() -> {
            HeatMapVisualization app = new HeatMapVisualization();
            app.setDataList(list);
            app.start(new Stage());
        });
    }
}
