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
    Color color1 = Color.rgb(43, 43, 43);
    Color color2 = Color.rgb(55, 55, 55);
    Color color3 = Color.rgb(75, 75, 75);
    Color color4 = Color.rgb(100, 100, 100);
    Color color5 = Color.rgb(125, 125, 125);
    Color color6 = Color.rgb(150, 150, 150);
    Color color7 = Color.rgb(175, 175, 175);
    Color color8 = Color.rgb(200, 200, 200);
    Color color9 = Color.rgb(225, 225, 225);
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
//        double max = list.get(0).get(0);
//        double min = list.get(0).get(0);
//        for (List<Double> tempList : list) {
//            double tempMax = tempList.stream().mapToDouble(a -> a).max().orElseThrow();
//            double tempMin = tempList.stream().mapToDouble(a -> a).min().orElseThrow();
//            if (tempMax > max) {
//                max = tempMax;
//            }
//            if (tempMin < min) {
//                min = tempMin;
//            }
//        }

        for (int row = 0; row < matrix[0].length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                double max = list.get(row).stream().mapToDouble(a -> a).max().orElseThrow();
                double min = list.get(row).stream().mapToDouble(a -> a).min().orElseThrow();
                double iteration = (max - min) / 11;
                double first = min + iteration;
                double second = first + iteration;
                double third = second + iteration;
                double fourth = third + iteration;
                double five = fourth + iteration;
                double six = five + iteration;
                double seven = six + iteration;
                double eight = seven + iteration;
                double nine = eight + iteration;
                double ten = nine + iteration;
                Rectangle rectangle = new Rectangle(widthOfScreen / list.size() / 1.5, heightOfScreen / list.get(0).size() / 1.5);
                double dataValue = matrix[col][row];
                if (dataValue <= first/2) {
                    rectangle.setFill(white);
                } else if (dataValue <= second) {
                    rectangle.setFill(color9);
                } else if (dataValue <= third) {
                    rectangle.setFill(color8);
                } else if (dataValue <= fourth) {
                    rectangle.setFill(color7);
                }else if (dataValue <= five) {
                    rectangle.setFill(color6);
                } else if (dataValue <= six) {
                    rectangle.setFill(color5);
                } else if (dataValue <= seven) {
                    rectangle.setFill(color4);
                } else if (dataValue <= eight) {
                    rectangle.setFill(color3);
                } else if (dataValue <= nine) {
                    rectangle.setFill(color2);
                } else if (dataValue <= ten) {
                    rectangle.setFill(color1);
                }  else {
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
