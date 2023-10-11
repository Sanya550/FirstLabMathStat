package com.example.idealjavafx.graphics;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;

public class Glif extends Application {

    // Helper method to create a gradient rectangle
    private Rectangle createGradientRectangle() {
        Rectangle rectangle = new Rectangle(400, 80);
        rectangle.setFill(Color.WHITE);

        // Create a linear gradient
        Color startColor = Color.WHITE;
        Color endColor = Color.BLACK;
        rectangle.setFill(new javafx.scene.paint.LinearGradient(0, 0, 1, 0, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
                new javafx.scene.paint.Stop(0, startColor), new javafx.scene.paint.Stop(1, endColor)));

        return rectangle;
    }

    static final int RECTANGULAR_SIZE = 80;

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
        var l1 = list.get(0);
        var l2 = list.get(1);
        var maxL1 = l1.stream().mapToDouble(a -> a).max().orElseThrow();
        var minL1 = l1.stream().mapToDouble(a -> a).min().orElseThrow();
        var maxL2 = l2.stream().mapToDouble(a -> a).max().orElseThrow();
        var minL2 = l2.stream().mapToDouble(a -> a).min().orElseThrow();
        double max = maxL1 > maxL2 ? maxL1 : maxL2;
        double min = minL1 < minL2 ? minL1 : minL2;
        double interval = (max - min) / 4;
        double first = min + interval;
        double second = first + interval;
        double third = second + interval;

        GridPane gridPane = createHeatMap(list);
        gridPane.setPadding(new Insets(10));

        // Create a gradient rectangle
        Rectangle gradientRectangle = createGradientRectangle();

        // Add the gradient rectangle to the grid
        gridPane.add(gradientRectangle, 11, 0);
        primaryStage.setScene(new Scene(gridPane));
        primaryStage.show();
    }

    private GridPane createHeatMap(List<List<Double>> list) {
        GridPane gridPane = new GridPane();
        var arr1Sorted = list.get(0);
        var arr2Sorted = list.get(1);
        var arr1NotSorted = list.get(2);
        var arr2NotSorted = list.get(3);
        //const:
        double range1 = 0.015;
        double range2 = 0.035;
        double range3 = 0.045;
        double range4 = 0.065;

        double numOfClass = (int) Math.cbrt(arr1Sorted.size());
        if ((int) Math.cbrt(arr1Sorted.size()) % 2 == 0) {
            numOfClass = (int) Math.cbrt(arr1Sorted.size()) - 1;
        }
        double tickUnitForArr1 = (arr1Sorted.get(arr1Sorted.size() - 1) - arr1Sorted.get(0)) / numOfClass;
        double tickUnitForArr2 = (arr2Sorted.get(arr2Sorted.size() - 1) - arr2Sorted.get(0)) / numOfClass;

        double startOfX = arr1Sorted.get(0);
        double endOfX = arr1Sorted.get(arr1Sorted.size() - 1);
        int shagX = 0;
        while (startOfX < endOfX) {
            endOfX -= tickUnitForArr1;
            shagX++;
        }
        double startOfY = arr2Sorted.get(0);
        double endOfY = arr2Sorted.get(arr2Sorted.size() - 1);
        int shagY = 0;
        while (startOfY < endOfY) {
            endOfY -= tickUnitForArr2;
            shagY++;
        }

        double tempOfFrequency = 0;
        double rangeStartForX = arr1Sorted.get(0);
        double rangeEndForX = arr1Sorted.get(0) + tickUnitForArr1;
        double rangeStartForY = arr2Sorted.get(0);
        double rangeEndForY = arr2Sorted.get(0) + tickUnitForArr2;
        for (int i = 0; i <= shagY; i++) {//y
            for (int j = 0; j <= shagX; j++) {//x
                for (int k = 0; k < arr1NotSorted.size(); k++) {
                    if (arr1NotSorted.get(k) < rangeEndForX && arr1NotSorted.get(k) > rangeStartForX && arr2NotSorted.get(k) < rangeEndForY && arr2NotSorted.get(k) > rangeStartForY) {
                        tempOfFrequency++;
                    }
                    if (k == arr1NotSorted.size() - 1) {
                        Rectangle rectangle = new Rectangle(RECTANGULAR_SIZE, RECTANGULAR_SIZE);
                        rectangle.setStroke(Color.BLACK);
                        rectangle.setStrokeWidth(1);
                        double freq = tempOfFrequency / arr1Sorted.size();
                        if (freq < range1) {
                            rectangle.setFill(white);
                        } else if (freq > range1 && freq < range2) {
                            rectangle.setFill(lightGrey);
                        } else if (freq > range2 && freq < range3) {
                            rectangle.setFill(grey);
                        } else if (freq > range3 && freq < range4) {
                            rectangle.setFill(lightBlack);
                        } else if (freq > range4) {
                            rectangle.setFill(black);
                        }
                        gridPane.add(rectangle, i, j);
                        rangeStartForX = rangeEndForX;
                        rangeEndForX += tickUnitForArr1;
                        tempOfFrequency = 0;
                    }
                }
            }
            rangeStartForX = arr1Sorted.get(0);
            rangeEndForX = arr1Sorted.get(0) + tickUnitForArr1;
            rangeStartForY = rangeEndForY;
            rangeEndForY += tickUnitForArr2;
        }
        return gridPane;
    }

    public static void launchGlifVisualization(List<List<Double>> list) {
        Platform.runLater(() -> {
            Glif app = new Glif();
            app.setDataList(list);
            app.start(new Stage());
        });
    }
}
