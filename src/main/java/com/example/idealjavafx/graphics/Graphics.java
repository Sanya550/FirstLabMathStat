package com.example.idealjavafx.graphics;

import com.example.idealjavafx.Helper;
import com.example.idealjavafx.MainFunction;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;
import javax.swing.*;
import java.util.ArrayList;
import java.util.TreeMap;

//Note: here locate graphics started from lab5
public class Graphics {
    //паралельні координати
    public static void drawParalelKoordinatVizual(LineChart chart, List<List<Double>> list1) {
        chart.getData().clear();
        chart.layout();

        List<List<Double>> listWithRightData = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            double max = list1.get(i).stream().mapToDouble(a -> a).max().orElseThrow();
            List<Double> tempList = new ArrayList<>();
            for (int j = 0; j < list1.get(i).size(); j++) {
                tempList.add(list1.get(i).get(j) / max);
            }
            listWithRightData.add(tempList);
        }

        for (int i = 0; i < listWithRightData.get(0).size(); i++) {
            XYChart.Series series1 = new XYChart.Series();
            for (int j = 0; j < listWithRightData.size(); j++) {
                series1.getData().add(new XYChart.Data(String.valueOf(j + 1), listWithRightData.get(j).get(i)));
            }
            chart.getData().addAll(series1);
        }
    }

    //матриця дыаграм розкиду
    public static void matrixRozkid(List<List<Double>> listSorted, List<List<Double>> listNotSorted, Label labelKorilation12, Label labelKorilation13, Label labelKorilation14, Label labelKorilation15, Label labelKorilation16,
                                    Label labelKorilation23, Label labelKorilation24, Label labelKorilation25, Label labelKorilation26, Label labelKorilation34, Label labelKorilation35, Label labelKorilation36,
                                    Label labelKorilation45, Label labelKorilation46, Label labelKorilation56, BarChart barChartRozkid1, BarChart barChartRozkid2, BarChart barChartRozkid3, BarChart barChartRozkid4, BarChart barChartRozkid5, BarChart barChartRozkid6,
                                    ScatterChart scatterChartRozkid21, ScatterChart scatterChartRozkid31, ScatterChart scatterChartRozkid32, ScatterChart scatterChartRozkid41, ScatterChart scatterChartRozkid42, ScatterChart scatterChartRozkid43, ScatterChart scatterChartRozkid51,
                                    ScatterChart scatterChartRozkid52, ScatterChart scatterChartRozkid53, ScatterChart scatterChartRozkid54, ScatterChart scatterChartRozkid61, ScatterChart scatterChartRozkid62, ScatterChart scatterChartRozkid63, ScatterChart scatterChartRozkid64, ScatterChart scatterChartRozkid65,
                                    NumberAxis xAxisForScatterChartForRozkid21, NumberAxis yAxisForScatterChartForRozkid21, NumberAxis xAxisForScatterChartForRozkid31, NumberAxis yAxisForScatterChartForRozkid31, NumberAxis xAxisForScatterChartForRozkid32, NumberAxis yAxisForScatterChartForRozkid32, NumberAxis xAxisForScatterChartForRozkid41, NumberAxis yAxisForScatterChartForRozkid41,
                                    NumberAxis xAxisForScatterChartForRozkid42, NumberAxis yAxisForScatterChartForRozkid42, NumberAxis xAxisForScatterChartForRozkid43, NumberAxis yAxisForScatterChartForRozkid43, NumberAxis xAxisForScatterChartForRozkid51, NumberAxis yAxisForScatterChartForRozkid51, NumberAxis xAxisForScatterChartForRozkid52, NumberAxis yAxisForScatterChartForRozkid52, NumberAxis xAxisForScatterChartForRozkid53, NumberAxis yAxisForScatterChartForRozkid53, NumberAxis xAxisForScatterChartForRozkid54, NumberAxis yAxisForScatterChartForRozkid54,
                                    NumberAxis xAxisForScatterChartForRozkid61, NumberAxis yAxisForScatterChartForRozkid61, NumberAxis xAxisForScatterChartForRozkid62, NumberAxis yAxisForScatterChartForRozkid62, NumberAxis xAxisForScatterChartForRozkid63, NumberAxis yAxisForScatterChartForRozkid63, NumberAxis xAxisForScatterChartForRozkid64, NumberAxis yAxisForScatterChartForRozkid64, NumberAxis xAxisForScatterChartForRozkid65, NumberAxis yAxisForScatterChartForRozkid65) {
        barChartRozkid1.getData().clear();
        barChartRozkid2.getData().clear();
        barChartRozkid3.getData().clear();
        barChartRozkid4.getData().clear();
        barChartRozkid5.getData().clear();
        barChartRozkid6.getData().clear();
        var arrayListSorted = new ArrayList<ArrayList>();
        var arrayListNotSorted = new ArrayList<>();
        for (int i = 0; i < listSorted.size(); i++) {
            arrayListSorted.add(new ArrayList<>(listSorted.get(i)));
            arrayListNotSorted.add(new ArrayList<>(listNotSorted.get(i)));
        }

        if (listSorted.size() == 3) {
            var korilationMatrix = MainFunction.findRForDuspKovMatrixForManyVibirok(listNotSorted);
            labelKorilation12.setText(String.format("Cor: %.2f", korilationMatrix[0][1]));
            labelKorilation13.setText(String.format("Cor: %.2f", korilationMatrix[0][2]));
            labelKorilation23.setText(String.format("Cor: %.2f", korilationMatrix[1][2]));
            Helper.drawBarChartForShilnist(barChartRozkid1, (int) Math.cbrt(listSorted.get(0).size()), arrayListSorted.get(0), (TreeMap) Helper.returnMap((ArrayList) listSorted.get(0)));
            Helper.drawBarChartForShilnist(barChartRozkid2, (int) Math.cbrt(listSorted.get(1).size()), arrayListSorted.get(1), (TreeMap) Helper.returnMap((ArrayList) listSorted.get(1)));
            Helper.drawBarChartForShilnist(barChartRozkid3, (int) Math.cbrt(listSorted.get(2).size()), arrayListSorted.get(2), (TreeMap) Helper.returnMap((ArrayList) listSorted.get(2)));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid21, xAxisForScatterChartForRozkid21, yAxisForScatterChartForRozkid21, (ArrayList<Double>) listSorted.get(0), (ArrayList<Double>) listSorted.get(1), (ArrayList<Double>) listNotSorted.get(0), (ArrayList<Double>) listNotSorted.get(1));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid31, xAxisForScatterChartForRozkid31, yAxisForScatterChartForRozkid31, (ArrayList<Double>) listSorted.get(0), (ArrayList<Double>) listSorted.get(2), (ArrayList<Double>) listNotSorted.get(0), (ArrayList<Double>) listNotSorted.get(2));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid32, xAxisForScatterChartForRozkid32, yAxisForScatterChartForRozkid32, (ArrayList<Double>) listSorted.get(1), (ArrayList<Double>) listSorted.get(2), (ArrayList<Double>) listNotSorted.get(1), (ArrayList<Double>) listNotSorted.get(2));
        } else if (listSorted.size() == 4) {
            var korilationMatrix = MainFunction.findRForDuspKovMatrixForManyVibirok(listNotSorted);
            labelKorilation12.setText(String.format("Cor: %.2f", korilationMatrix[0][1]));
            labelKorilation13.setText(String.format("Cor: %.2f", korilationMatrix[0][2]));
            labelKorilation23.setText(String.format("Cor: %.2f", korilationMatrix[1][2]));
            labelKorilation14.setText(String.format("Cor: %.2f", korilationMatrix[0][3]));
            labelKorilation24.setText(String.format("Cor: %.2f", korilationMatrix[1][3]));
            labelKorilation34.setText(String.format("Cor: %.2f", korilationMatrix[2][3]));
            Helper.drawBarChartForShilnist(barChartRozkid1, (int) Math.cbrt(listSorted.get(0).size()), arrayListSorted.get(0), (TreeMap) Helper.returnMap((ArrayList) listSorted.get(0)));
            Helper.drawBarChartForShilnist(barChartRozkid2, (int) Math.cbrt(listSorted.get(1).size()), arrayListSorted.get(1), (TreeMap) Helper.returnMap((ArrayList) listSorted.get(1)));
            Helper.drawBarChartForShilnist(barChartRozkid3, (int) Math.cbrt(listSorted.get(2).size()), arrayListSorted.get(2), (TreeMap) Helper.returnMap((ArrayList) listSorted.get(2)));
            Helper.drawBarChartForShilnist(barChartRozkid4, (int) Math.cbrt(listSorted.get(3).size()), arrayListSorted.get(3), (TreeMap) Helper.returnMap((ArrayList) listSorted.get(3)));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid21, xAxisForScatterChartForRozkid21, yAxisForScatterChartForRozkid21, (ArrayList<Double>) listSorted.get(0), (ArrayList<Double>) listSorted.get(1), (ArrayList<Double>) listNotSorted.get(0), (ArrayList<Double>) listNotSorted.get(1));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid31, xAxisForScatterChartForRozkid31, yAxisForScatterChartForRozkid31, (ArrayList<Double>) listSorted.get(0), (ArrayList<Double>) listSorted.get(2), (ArrayList<Double>) listNotSorted.get(0), (ArrayList<Double>) listNotSorted.get(2));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid32, xAxisForScatterChartForRozkid32, yAxisForScatterChartForRozkid32, (ArrayList<Double>) listSorted.get(1), (ArrayList<Double>) listSorted.get(2), (ArrayList<Double>) listNotSorted.get(1), (ArrayList<Double>) listNotSorted.get(2));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid41, xAxisForScatterChartForRozkid41, yAxisForScatterChartForRozkid41, (ArrayList<Double>) listSorted.get(0), (ArrayList<Double>) listSorted.get(3), (ArrayList<Double>) listNotSorted.get(0), (ArrayList<Double>) listNotSorted.get(3));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid42, xAxisForScatterChartForRozkid42, yAxisForScatterChartForRozkid42, (ArrayList<Double>) listSorted.get(1), (ArrayList<Double>) listSorted.get(3), (ArrayList<Double>) listNotSorted.get(1), (ArrayList<Double>) listNotSorted.get(3));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid43, xAxisForScatterChartForRozkid43, yAxisForScatterChartForRozkid43, (ArrayList<Double>) listSorted.get(2), (ArrayList<Double>) listSorted.get(3), (ArrayList<Double>) listNotSorted.get(2), (ArrayList<Double>) listNotSorted.get(3));
        } else if (listSorted.size() == 5) {
            var korilationMatrix = MainFunction.findRForDuspKovMatrixForManyVibirok(listNotSorted);
            labelKorilation12.setText(String.format("Cor: %.2f", korilationMatrix[0][1]));
            labelKorilation13.setText(String.format("Cor: %.2f", korilationMatrix[0][2]));
            labelKorilation23.setText(String.format("Cor: %.2f", korilationMatrix[1][2]));
            labelKorilation14.setText(String.format("Cor: %.2f", korilationMatrix[0][3]));
            labelKorilation24.setText(String.format("Cor: %.2f", korilationMatrix[1][3]));
            labelKorilation34.setText(String.format("Cor: %.2f", korilationMatrix[2][3]));
            labelKorilation15.setText(String.format("Cor: %.2f", korilationMatrix[0][4]));
            labelKorilation25.setText(String.format("Cor: %.2f", korilationMatrix[1][4]));
            labelKorilation35.setText(String.format("Cor: %.2f", korilationMatrix[1][4]));
            labelKorilation45.setText(String.format("Cor: %.2f", korilationMatrix[1][4]));
            Helper.drawBarChartForShilnist(barChartRozkid1, (int) Math.cbrt(listSorted.get(0).size()), arrayListSorted.get(0), (TreeMap) Helper.returnMap((ArrayList) listSorted.get(0)));
            Helper.drawBarChartForShilnist(barChartRozkid2, (int) Math.cbrt(listSorted.get(1).size()), arrayListSorted.get(1), (TreeMap) Helper.returnMap((ArrayList) listSorted.get(1)));
            Helper.drawBarChartForShilnist(barChartRozkid3, (int) Math.cbrt(listSorted.get(2).size()), arrayListSorted.get(2), (TreeMap) Helper.returnMap((ArrayList) listSorted.get(2)));
            Helper.drawBarChartForShilnist(barChartRozkid4, (int) Math.cbrt(listSorted.get(3).size()), arrayListSorted.get(3), (TreeMap) Helper.returnMap((ArrayList) listSorted.get(3)));
            Helper.drawBarChartForShilnist(barChartRozkid5, (int) Math.cbrt(listSorted.get(4).size()), arrayListSorted.get(4), (TreeMap) Helper.returnMap((ArrayList) listSorted.get(4)));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid21, xAxisForScatterChartForRozkid21, yAxisForScatterChartForRozkid21, (ArrayList<Double>) listSorted.get(0), (ArrayList<Double>) listSorted.get(1), (ArrayList<Double>) listNotSorted.get(0), (ArrayList<Double>) listNotSorted.get(1));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid31, xAxisForScatterChartForRozkid31, yAxisForScatterChartForRozkid31, (ArrayList<Double>) listSorted.get(0), (ArrayList<Double>) listSorted.get(2), (ArrayList<Double>) listNotSorted.get(0), (ArrayList<Double>) listNotSorted.get(2));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid32, xAxisForScatterChartForRozkid32, yAxisForScatterChartForRozkid32, (ArrayList<Double>) listSorted.get(1), (ArrayList<Double>) listSorted.get(2), (ArrayList<Double>) listNotSorted.get(1), (ArrayList<Double>) listNotSorted.get(2));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid41, xAxisForScatterChartForRozkid41, yAxisForScatterChartForRozkid41, (ArrayList<Double>) listSorted.get(0), (ArrayList<Double>) listSorted.get(3), (ArrayList<Double>) listNotSorted.get(0), (ArrayList<Double>) listNotSorted.get(3));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid42, xAxisForScatterChartForRozkid42, yAxisForScatterChartForRozkid42, (ArrayList<Double>) listSorted.get(1), (ArrayList<Double>) listSorted.get(3), (ArrayList<Double>) listNotSorted.get(1), (ArrayList<Double>) listNotSorted.get(3));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid43, xAxisForScatterChartForRozkid43, yAxisForScatterChartForRozkid43, (ArrayList<Double>) listSorted.get(2), (ArrayList<Double>) listSorted.get(3), (ArrayList<Double>) listNotSorted.get(2), (ArrayList<Double>) listNotSorted.get(3));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid51, xAxisForScatterChartForRozkid51, yAxisForScatterChartForRozkid51, (ArrayList<Double>) listSorted.get(0), (ArrayList<Double>) listSorted.get(4), (ArrayList<Double>) listNotSorted.get(0), (ArrayList<Double>) listNotSorted.get(4));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid52, xAxisForScatterChartForRozkid52, yAxisForScatterChartForRozkid52, (ArrayList<Double>) listSorted.get(1), (ArrayList<Double>) listSorted.get(4), (ArrayList<Double>) listNotSorted.get(1), (ArrayList<Double>) listNotSorted.get(4));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid53, xAxisForScatterChartForRozkid53, yAxisForScatterChartForRozkid53, (ArrayList<Double>) listSorted.get(2), (ArrayList<Double>) listSorted.get(4), (ArrayList<Double>) listNotSorted.get(2), (ArrayList<Double>) listNotSorted.get(4));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid54, xAxisForScatterChartForRozkid54, yAxisForScatterChartForRozkid54, (ArrayList<Double>) listSorted.get(3), (ArrayList<Double>) listSorted.get(4), (ArrayList<Double>) listNotSorted.get(3), (ArrayList<Double>) listNotSorted.get(4));
        } else if (listSorted.size() == 6) {
            var korilationMatrix = MainFunction.findRForDuspKovMatrixForManyVibirok(listNotSorted);
            labelKorilation12.setText(String.format("Cor: %.2f", korilationMatrix[0][1]));
            labelKorilation13.setText(String.format("Cor: %.2f", korilationMatrix[0][2]));
            labelKorilation23.setText(String.format("Cor: %.2f", korilationMatrix[1][2]));
            labelKorilation14.setText(String.format("Cor: %.2f", korilationMatrix[0][3]));
            labelKorilation24.setText(String.format("Cor: %.2f", korilationMatrix[1][3]));
            labelKorilation34.setText(String.format("Cor: %.2f", korilationMatrix[2][3]));
            labelKorilation15.setText(String.format("Cor: %.2f", korilationMatrix[0][4]));
            labelKorilation25.setText(String.format("Cor: %.2f", korilationMatrix[1][4]));
            labelKorilation35.setText(String.format("Cor: %.2f", korilationMatrix[2][4]));
            labelKorilation45.setText(String.format("Cor: %.2f", korilationMatrix[3][4]));
            labelKorilation16.setText(String.format("Cor: %.2f", korilationMatrix[0][5]));
            labelKorilation26.setText(String.format("Cor: %.2f", korilationMatrix[1][5]));
            labelKorilation36.setText(String.format("Cor: %.2f", korilationMatrix[2][5]));
            labelKorilation46.setText(String.format("Cor: %.2f", korilationMatrix[3][5]));
            labelKorilation56.setText(String.format("Cor: %.2f", korilationMatrix[4][5]));
            Helper.drawBarChartForShilnist(barChartRozkid1, (int) Math.cbrt(listSorted.get(0).size()), arrayListSorted.get(0), (TreeMap) Helper.returnMap((ArrayList) listSorted.get(0)));
            Helper.drawBarChartForShilnist(barChartRozkid2, (int) Math.cbrt(listSorted.get(1).size()), arrayListSorted.get(1), (TreeMap) Helper.returnMap((ArrayList) listSorted.get(1)));
            Helper.drawBarChartForShilnist(barChartRozkid3, (int) Math.cbrt(listSorted.get(2).size()), arrayListSorted.get(2), (TreeMap) Helper.returnMap((ArrayList) listSorted.get(2)));
            Helper.drawBarChartForShilnist(barChartRozkid4, (int) Math.cbrt(listSorted.get(3).size()), arrayListSorted.get(3), (TreeMap) Helper.returnMap((ArrayList) listSorted.get(3)));
            Helper.drawBarChartForShilnist(barChartRozkid5, (int) Math.cbrt(listSorted.get(4).size()), arrayListSorted.get(4), (TreeMap) Helper.returnMap((ArrayList) listSorted.get(4)));
            Helper.drawBarChartForShilnist(barChartRozkid6, (int) Math.cbrt(listSorted.get(5).size()), arrayListSorted.get(5), (TreeMap) Helper.returnMap((ArrayList) listSorted.get(5)));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid21, xAxisForScatterChartForRozkid21, yAxisForScatterChartForRozkid21, (ArrayList<Double>) listSorted.get(0), (ArrayList<Double>) listSorted.get(1), (ArrayList<Double>) listNotSorted.get(0), (ArrayList<Double>) listNotSorted.get(1));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid31, xAxisForScatterChartForRozkid31, yAxisForScatterChartForRozkid31, (ArrayList<Double>) listSorted.get(0), (ArrayList<Double>) listSorted.get(2), (ArrayList<Double>) listNotSorted.get(0), (ArrayList<Double>) listNotSorted.get(2));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid32, xAxisForScatterChartForRozkid32, yAxisForScatterChartForRozkid32, (ArrayList<Double>) listSorted.get(1), (ArrayList<Double>) listSorted.get(2), (ArrayList<Double>) listNotSorted.get(1), (ArrayList<Double>) listNotSorted.get(2));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid41, xAxisForScatterChartForRozkid41, yAxisForScatterChartForRozkid41, (ArrayList<Double>) listSorted.get(0), (ArrayList<Double>) listSorted.get(3), (ArrayList<Double>) listNotSorted.get(0), (ArrayList<Double>) listNotSorted.get(3));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid42, xAxisForScatterChartForRozkid42, yAxisForScatterChartForRozkid42, (ArrayList<Double>) listSorted.get(1), (ArrayList<Double>) listSorted.get(3), (ArrayList<Double>) listNotSorted.get(1), (ArrayList<Double>) listNotSorted.get(3));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid43, xAxisForScatterChartForRozkid43, yAxisForScatterChartForRozkid43, (ArrayList<Double>) listSorted.get(2), (ArrayList<Double>) listSorted.get(3), (ArrayList<Double>) listNotSorted.get(2), (ArrayList<Double>) listNotSorted.get(3));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid51, xAxisForScatterChartForRozkid51, yAxisForScatterChartForRozkid51, (ArrayList<Double>) listSorted.get(0), (ArrayList<Double>) listSorted.get(4), (ArrayList<Double>) listNotSorted.get(0), (ArrayList<Double>) listNotSorted.get(4));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid52, xAxisForScatterChartForRozkid52, yAxisForScatterChartForRozkid52, (ArrayList<Double>) listSorted.get(1), (ArrayList<Double>) listSorted.get(4), (ArrayList<Double>) listNotSorted.get(1), (ArrayList<Double>) listNotSorted.get(4));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid53, xAxisForScatterChartForRozkid53, yAxisForScatterChartForRozkid53, (ArrayList<Double>) listSorted.get(2), (ArrayList<Double>) listSorted.get(4), (ArrayList<Double>) listNotSorted.get(2), (ArrayList<Double>) listNotSorted.get(4));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid54, xAxisForScatterChartForRozkid54, yAxisForScatterChartForRozkid54, (ArrayList<Double>) listSorted.get(3), (ArrayList<Double>) listSorted.get(4), (ArrayList<Double>) listNotSorted.get(3), (ArrayList<Double>) listNotSorted.get(4));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid61, xAxisForScatterChartForRozkid61, yAxisForScatterChartForRozkid61, (ArrayList<Double>) listSorted.get(0), (ArrayList<Double>) listSorted.get(5), (ArrayList<Double>) listNotSorted.get(0), (ArrayList<Double>) listNotSorted.get(5));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid62, xAxisForScatterChartForRozkid62, yAxisForScatterChartForRozkid62, (ArrayList<Double>) listSorted.get(1), (ArrayList<Double>) listSorted.get(5), (ArrayList<Double>) listNotSorted.get(1), (ArrayList<Double>) listNotSorted.get(5));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid63, xAxisForScatterChartForRozkid63, yAxisForScatterChartForRozkid63, (ArrayList<Double>) listSorted.get(2), (ArrayList<Double>) listSorted.get(5), (ArrayList<Double>) listNotSorted.get(2), (ArrayList<Double>) listNotSorted.get(5));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid64, xAxisForScatterChartForRozkid64, yAxisForScatterChartForRozkid64, (ArrayList<Double>) listSorted.get(3), (ArrayList<Double>) listSorted.get(5), (ArrayList<Double>) listNotSorted.get(3), (ArrayList<Double>) listNotSorted.get(5));
            Helper.drawScatterChartForKorilationField(scatterChartRozkid65, xAxisForScatterChartForRozkid65, yAxisForScatterChartForRozkid65, (ArrayList<Double>) listSorted.get(4), (ArrayList<Double>) listSorted.get(5), (ArrayList<Double>) listNotSorted.get(4), (ArrayList<Double>) listNotSorted.get(5));
        } else {
            JOptionPane.showMessageDialog(null, "Виберіть мінімум 3 вибірки", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //теплова карта
    //todo: needs updates
    public static void heatMap(List<List<Double>> list, ScatterChart scatterChart, NumberAxis xAxis, NumberAxis yAxis) {
        //clear:
        scatterChart.getData().clear();
        scatterChart.layout();
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(list.size());
        xAxis.setTickUnit(1);

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(list.get(0).size());
        yAxis.setTickUnit(1);

        double max = list.get(0).get(0);
        double min = list.get(0).get(0);

        for (List<Double> sublist : list) {
            for (Double value : sublist) {
                if (value > max) {
                    max = value;
                }
                if (value < min) {
                    min = value;
                }
            }
        }

        double iteration = (max - min) / 5;
        double first = min + iteration;
        double second = first + iteration;
        double third = second + iteration;
        double fourth = third + iteration;

        // создаем цвета
        Color darkBlue = Color.rgb(0, 119, 190); // темно-синий
        Color lightBlue = Color.rgb(52, 152, 219); // светло-синий
        Color skyBlue = Color.rgb(127, 179, 213); // голубой
        Color lightSkyBlue = Color.rgb(184, 216, 228); // светло-голубой
        Color whiteBlue = Color.rgb(234, 244, 252); // белый синий

        double cellWidth = xAxis.getWidth() / xAxis.getTickUnit();
        double cellHeight = yAxis.getHeight() / yAxis.getTickUnit();

// create a custom node with the cell width and height
        Rectangle node1 = new Rectangle(cellWidth, cellHeight);
        Rectangle node2 = new Rectangle(cellWidth, cellHeight);
        Rectangle node3 = new Rectangle(cellWidth, cellHeight);
        Rectangle node4 = new Rectangle(cellWidth, cellHeight);
        Rectangle node5 = new Rectangle(cellWidth, cellHeight);
        node1.setFill(whiteBlue);
        node2.setFill(lightSkyBlue);
        node3.setFill(skyBlue);
        node4.setFill(lightBlue);
        node5.setFill(darkBlue);

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        XYChart.Series series4 = new XYChart.Series();
        XYChart.Series series5 = new XYChart.Series();
        //  scatterChart.setId("frequency-hysograma-scatter");
        series1.setName(String.format("%.2f - %.2f", min, first));
        series2.setName(String.format("%.2f - %.2f", first, second));
        series3.setName(String.format("%.2f - %.2f", second, third));
        series4.setName(String.format("%.2f - %.2f", third, fourth));
        series5.setName(String.format("%.2f - %.2f", fourth, max));

        series1.setNode(node1);
        series2.setNode(node2);
        series3.setNode(node3);
        series4.setNode(node4);
        series5.setNode(node5);

        for (int x = 0; x < list.size(); x++) {
            for (int y = 0; y < list.get(x).size(); y++) {
                double dataValue = list.get(x).get(y);
                if (dataValue <= first) {
                    series1.getData().add(new XYChart.Data((x + 1) / 2, (y + 1) / 2));
                } else if (dataValue <= second) {
                    series2.getData().add(new XYChart.Data((x + 1) / 2, (y + 1) / 2));
                } else if (dataValue <= third) {
                    series3.getData().add(new XYChart.Data((x + 1) / 2, (y + 1) / 2));
                } else if (dataValue <= fourth) {
                    series4.getData().add(new XYChart.Data((x + 1) / 2, (y + 1) / 2));
                } else {
                    series5.getData().add(new XYChart.Data((x + 1) / 2, (y + 1) / 2));
                }
            }
        }
        scatterChart.getData().addAll(series1);
        scatterChart.getData().addAll(series2);
        scatterChart.getData().addAll(series3);
        scatterChart.getData().addAll(series4);
        scatterChart.getData().addAll(series5);
    }

    //бульбашкова діаграма
    public static void bubbleDiagram(List<List<Double>> listNotSorted, List<List<Double>> listSorted, BubbleChart bubbleChart, NumberAxis xAxis, NumberAxis yAxis) {
        if (listSorted.size() != 3) {
            JOptionPane.showMessageDialog(null, "Кількість ознак має бути 3", "Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            bubbleChart.getData().clear();
            bubbleChart.layout();
            xAxis.setAutoRanging(false);
            xAxis.setLowerBound(listSorted.get(0).get(0));
            xAxis.setUpperBound(listSorted.get(0).get(listSorted.get(0).size() - 1));

            yAxis.setAutoRanging(false);
            yAxis.setLowerBound(listSorted.get(1).get(0));
            yAxis.setUpperBound(listSorted.get(1).get(listSorted.get(1).size() - 1));

            XYChart.Series series = new XYChart.Series();
            series.setName("Бульбашкова діграма");
            for (int i = 0; i < listNotSorted.get(0).size(); i++) {
                series.getData().add(new XYChart.Data(listNotSorted.get(0).get(i), listNotSorted.get(1).get(i), Math.sqrt(listNotSorted.get(2).get(i) / Math.PI)));
            }
            bubbleChart.getData().add(series);
        }
    }

    //лінійна регресія
    public static void diagnosticDiagram(List<List<Double>> listNotSorted, ScatterChart scatterChart, NumberAxis xAxis, NumberAxis yAxis) {
        if (listNotSorted.size() != 3) {
            JOptionPane.showMessageDialog(null, "Кількість ознак має бути 3", "Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            xAxis.setLowerBound(listNotSorted.get(2).stream().mapToDouble(a -> a).min().orElseThrow());
            xAxis.setUpperBound(listNotSorted.get(2).stream().mapToDouble(a -> a).max().orElseThrow());
            xAxis.setLabel("y");
            yAxis.setLabel("ɛ");
            double x1_2 = 0;
            double x2_2 = 0;
            double x1_y = 0;
            double x2_y = 0;
            double x1x2_ = 0;
            for (int i = 0; i < listNotSorted.get(0).size(); i++) {
                x1x2_ += listNotSorted.get(0).get(i) * listNotSorted.get(1).get(i);
                x1_2 += Math.pow(listNotSorted.get(0).get(i), 2);
                x2_2 += Math.pow(listNotSorted.get(1).get(i), 2);
                x1_y += listNotSorted.get(0).get(i) * listNotSorted.get(2).get(i);
                x2_y += listNotSorted.get(1).get(i) * listNotSorted.get(2).get(i);
            }
            x1x2_ /= listNotSorted.get(0).size();
            x1_2 /= listNotSorted.get(0).size();
            x2_2 /= listNotSorted.get(0).size();

            var triangleGeneral = new double[3][3];
            triangleGeneral[0][0] = 1;
            triangleGeneral[0][1] = MainFunction.matSpodivan(listNotSorted.get(0));
            triangleGeneral[0][2] = MainFunction.matSpodivan(listNotSorted.get(1));
            triangleGeneral[1][0] = MainFunction.matSpodivan(listNotSorted.get(0));
            triangleGeneral[1][1] = x1_2;
            triangleGeneral[1][2] = x1x2_;
            triangleGeneral[2][0] = MainFunction.matSpodivan(listNotSorted.get(1));
            triangleGeneral[2][1] = x1x2_;
            triangleGeneral[2][2] = x2_2;

            var triangle0 = new double[3][3];
            triangle0[0][0] = MainFunction.matSpodivan(listNotSorted.get(2));
            triangle0[0][1] = MainFunction.matSpodivan(listNotSorted.get(0));
            triangle0[0][2] = MainFunction.matSpodivan(listNotSorted.get(1));
            triangle0[1][0] = x1_y;
            triangle0[1][1] = x1_2;
            triangle0[1][2] = x1x2_;
            triangle0[2][0] = x2_y;
            triangle0[2][1] = x1x2_;
            triangle0[2][2] = x2_2;

            var triangle1 = new double[3][3];
            triangle1[0][0] = 1;
            triangle1[0][1] = MainFunction.matSpodivan(listNotSorted.get(2));
            triangle1[0][2] = MainFunction.matSpodivan(listNotSorted.get(1));
            triangle1[1][0] = MainFunction.matSpodivan(listNotSorted.get(0));
            triangle1[1][1] = x1_y;
            triangle1[1][2] = x1x2_;
            triangle1[2][0] = MainFunction.matSpodivan(listNotSorted.get(1));
            triangle1[2][1] = x2_y;
            triangle1[2][2] = x2_2;

            var triangle2 = new double[3][3];
            triangle1[0][0] = 1;
            triangle1[0][1] = MainFunction.matSpodivan(listNotSorted.get(0));
            triangle1[0][2] = MainFunction.matSpodivan(listNotSorted.get(2));
            triangle1[1][0] = MainFunction.matSpodivan(listNotSorted.get(0));
            triangle1[1][1] = x1_2;
            triangle1[1][2] = x1_y;
            triangle1[2][0] = MainFunction.matSpodivan(listNotSorted.get(1));
            triangle1[2][1] = x1x2_;
            triangle1[2][2] = x2_y;

            double a0 = MainFunction.findDetermination(triangle0) / MainFunction.findDetermination(triangleGeneral);
            double a1 = MainFunction.findDetermination(triangle1) / MainFunction.findDetermination(triangleGeneral);
            double a2 = MainFunction.findDetermination(triangle2) / MainFunction.findDetermination(triangleGeneral);

            double minYAxis = listNotSorted.get(2).get(0) - a0 - a1 * listNotSorted.get(0).get(0) + a2 * listNotSorted.get(1).get(0);
            double maxYAxis = listNotSorted.get(2).get(0) - a0 - a1 * listNotSorted.get(0).get(0) + a2 * listNotSorted.get(1).get(0);;
            XYChart.Series series1 = new XYChart.Series();
            series1.setName("Діагностична діаграма");
            for (int i = 0; i < listNotSorted.get(2).size(); i++) {
                double temp = a1 * listNotSorted.get(0).get(i) + a2 * listNotSorted.get(1).get(i);
                double eps = listNotSorted.get(2).get(i) - a0 - temp;
                series1.getData().add(new XYChart.Data(listNotSorted.get(2).get(i), eps));
                if (eps<minYAxis){
                    minYAxis = eps;
                }
                if (eps>maxYAxis){
                    maxYAxis = eps;
                }
            }
            yAxis.setLowerBound(minYAxis);
            yAxis.setUpperBound(maxYAxis);
            scatterChart.getData().addAll(series1);
        }
    }
}
