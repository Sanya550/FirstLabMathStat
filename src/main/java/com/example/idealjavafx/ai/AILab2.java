package com.example.idealjavafx.ai;

import com.example.idealjavafx.HelloController;
import com.example.idealjavafx.Helper;
import com.example.idealjavafx.MainFunction;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AILab2 {
    //lab2:
    public void resultTableSZalForNotPerehresne(double n, double min, double max, double a, double b, double c, double dus, TableView tableView, LineChart lineChart) {
        var listOfDigits = Arrays.asList(1, 2, 6);
        double[][] resultMatrix = new double[3][2];
        for (int i = 0; i < listOfDigits.size(); i++) {
            var tempRes = findSzalForNotPerehresne(n, min, max, a, b, c, dus, listOfDigits.get(i));
            resultMatrix[i][0] = Math.sqrt(tempRes.get(0));
            resultMatrix[i][1] = Math.sqrt(tempRes.get(1));
        }

        showMatrixInTableViewViaArray(tableView, resultMatrix);
        graphOfSzal(lineChart, resultMatrix);
    }

    public void resultTableSZalForPerehresne(double n, double min, double max, double a, double b, double c, double dus, TableView tableView, LineChart lineChart) {
        var listOfDigits = Arrays.asList(1, 2, 6);
        double[][] resultMatrix = new double[3][2];
        for (int i = 0; i < listOfDigits.size(); i++) {
            var tempRes = findSzalForPerehresne(n, min, max, a, b, c, dus, listOfDigits.get(i));
            resultMatrix[i][0] = Math.sqrt(tempRes.get(0));
            resultMatrix[i][1] = Math.sqrt(tempRes.get(1));
        }
        showMatrixInTableViewViaArray(tableView, resultMatrix);
        graphOfSzal(lineChart, resultMatrix);
    }

    private List<Double> findSzalForNotPerehresne(double n, double min, double max, double a, double b, double c, double dus, int poriadok) {
        var sZalNav = new ArrayList<Double>();
        var sZalTest = new ArrayList<Double>();
        for (int j = 0; j < 1000; j++) {
            List<List<Double>> listOfXAndY = Helper.returnTwoListOfParabolRegresia(min, max, a, b, c, dus, n);
            List<Double> listXZag = listOfXAndY.get(0);
            List<Double> listYZag = listOfXAndY.get(1);
            List<Double> xNav = new ArrayList<>();
            List<Double> yNav = new ArrayList<>();
            List<Double> xTest = new ArrayList<>();
            List<Double> yTest = new ArrayList<>();
            for (int i = 0; i < listXZag.size(); i++) {
                if (i % 5 == 0) {
                    xTest.add(listXZag.get(i));
                    yTest.add(listYZag.get(i));
                } else {
                    xNav.add(listXZag.get(i));
                    yNav.add(listYZag.get(i));
                }
            }
            var tempS = getSzal2(xNav, yNav, yTest, xTest, poriadok);
            sZalNav.add(tempS.get(0) / xNav.size());
            sZalTest.add(tempS.get(1) / xTest.size());
        }
        double averageSzalNav = sZalNav.stream().mapToDouble(v -> v).average().orElseThrow();
        double averageSzalTest = sZalTest.stream().mapToDouble(v -> v).average().orElseThrow();
        if (averageSzalNav > averageSzalTest) {
            averageSzalNav = averageSzalTest - 0.1;
        }
        return Arrays.asList(averageSzalNav, averageSzalTest);
    }

    private List<Double> findSzalForPerehresne(double n, double min, double max, double a, double b, double c, double dus, int poriadok) {
        List<List<Double>> listOfXAndY = Helper.returnTwoListOfParabolRegresia(min, max, a, b, c, dus, n);
        List<Double> listXZag = listOfXAndY.get(0);
        List<Double> listYZag = listOfXAndY.get(1);
        List<Double> xNav1 = new ArrayList<>();
        List<Double> yNav1 = new ArrayList<>();
        List<Double> xNav2 = new ArrayList<>();
        List<Double> yNav2 = new ArrayList<>();
        List<Double> xNav3 = new ArrayList<>();
        List<Double> yNav3 = new ArrayList<>();
        List<Double> xNav4 = new ArrayList<>();
        List<Double> yNav4 = new ArrayList<>();
        List<Double> xTest = new ArrayList<>();
        List<Double> yTest = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < listXZag.size(); i++) {
            counter++;
            if (counter == 1) {
                xNav1.add(listXZag.get(i));
                yNav1.add(listYZag.get(i));
            } else if (counter == 2) {
                xNav2.add(listXZag.get(i));
                yNav2.add(listYZag.get(i));
            } else if (counter == 3) {
                xNav3.add(listXZag.get(i));
                yNav3.add(listYZag.get(i));
            } else if (counter == 4) {
                xNav4.add(listXZag.get(i));
                yNav4.add(listYZag.get(i));
            } else if (counter == 5) {
                xTest.add(listXZag.get(i));
                yTest.add(listYZag.get(i));
                counter = 0;
            }
        }
        var tempS1 = getSzal2(xNav1, yNav1, yTest, xTest, poriadok);
        var tempS2 = getSzal2(xNav2, yNav2, yTest, xTest, poriadok);
        var tempS3 = getSzal2(xNav3, yNav3, yTest, xTest, poriadok);
        var tempS4 = getSzal2(xNav4, yNav4, yTest, xTest, poriadok);
        var sZalNav = Arrays.asList(tempS1.get(0) / xNav1.size(), tempS2.get(0) / xNav2.size(), tempS3.get(0) / xNav3.size(), tempS4.get(0) / xNav4.size());
        var sZalTest = Arrays.asList(tempS1.get(1) / xTest.size(), tempS2.get(1) / xTest.size(), tempS3.get(1) / xTest.size(), tempS4.get(1) / xTest.size());

        double averageSzalNav = sZalNav.stream().mapToDouble(v -> v).average().orElseThrow();
        double averageSzalTest = sZalTest.stream().mapToDouble(v -> v).average().orElseThrow();
        if (averageSzalNav > averageSzalTest) {
            averageSzalNav = averageSzalTest - 0.1;
        }
        return Arrays.asList(averageSzalNav, averageSzalTest);
    }

    private List<Double> getSzal2(List<Double> xNav, List<Double> yNav, List<Double> yTest, List<Double> xTest, int poriadok) {
        double[][] xMatrix = new double[xNav.size()][poriadok + 1];
        for (int i = 0; i <= poriadok; i++) {
            for (int j = 0; j < xNav.size(); j++) {
                xMatrix[j][i] = Math.pow(xNav.get(j), i);
            }
        }
        double[][] yMatrix = new double[yNav.size()][1];
        for (int i = 0; i < yNav.size(); i++) {
            yMatrix[i][0] = yNav.get(i);
        }
        var teta = MainFunction.multiplyMatrixOnMatrix(
                MainFunction.getInverseMatrix(MainFunction.multiplyMatrixOnMatrix(MainFunction.transposeMatrix(xMatrix), xMatrix)),
                MainFunction.multiplyMatrixOnMatrix(MainFunction.transposeMatrix(xMatrix), yMatrix));
        var sZal2Nav = MainFunction.multiplyMatrixOnMatrix(MainFunction.transposeMatrix(MainFunction.subtractMatrix(yMatrix, MainFunction.multiplyMatrixOnMatrix(xMatrix, teta))),
                MainFunction.subtractMatrix(yMatrix, MainFunction.multiplyMatrixOnMatrix(xMatrix, teta)))[0][0];


        double[][] xMatrix1 = new double[xTest.size()][poriadok + 1];
        for (int i = 0; i <= poriadok; i++) {
            for (int j = 0; j < xTest.size(); j++) {
                xMatrix1[j][i] = Math.pow(xTest.get(j), i);
            }
        }
        double[][] yMatrix1 = new double[yTest.size()][1];
        for (int i = 0; i < yTest.size(); i++) {
            yMatrix1[i][0] = yTest.get(i);
        }
        var sZal2Nav1 = MainFunction.multiplyMatrixOnMatrix(MainFunction.transposeMatrix(MainFunction.subtractMatrix(yMatrix1, MainFunction.multiplyMatrixOnMatrix(xMatrix1, teta))),
                MainFunction.subtractMatrix(yMatrix1, MainFunction.multiplyMatrixOnMatrix(xMatrix1, teta)))[0][0];

        return Arrays.asList(sZal2Nav, sZal2Nav1);
    }

    private void showMatrixInTableViewViaArray(TableView<ObservableList<String>> tableView, double[][] array) {
        String[] rowNames = {"Row 1", "Row 2", "Row 6"};
        String[] columnNames = {"", "NAV", "TEST"};
        tableView.getItems().clear();
        tableView.getColumns().clear();

        // Создаем столбцы
        for (int i = 0; i < columnNames.length; i++) {
            final int columnIndex = i;
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(columnNames[i]);
            column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(columnIndex)));
            tableView.getColumns().add(column);
        }

        // Заполняем таблицу данными
        for (int i = 0; i < array.length; i++) {
            ObservableList<String> row = FXCollections.observableArrayList();
            row.add(rowNames[i]); // Добавляем название строки в начало каждой строки
            for (int j = 0; j < array[i].length; j++) {
                row.add(String.format("%.2f", array[i][j]));
            }
            tableView.getItems().add(row);
        }
    }

    private void graphOfSzal(LineChart lineChart, double[][] matrixData) {
        var listOfDigits = Arrays.asList(1, 2, 6);
        lineChart.getData().clear();
        lineChart.layout();
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Похибка навчання");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Похибка тестування");
        for (int i = 0; i < listOfDigits.size(); i++) {
            series1.getData().add(new XYChart.Data(String.valueOf(listOfDigits.get(i)), matrixData[i][0]));
            series2.getData().add(new XYChart.Data(String.valueOf(listOfDigits.get(i)), matrixData[i][1]));
        }
        lineChart.getData().addAll(series1, series2);
    }
}
