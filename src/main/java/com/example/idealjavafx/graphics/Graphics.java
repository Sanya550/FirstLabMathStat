package com.example.idealjavafx.graphics;

import com.example.idealjavafx.HelloController;
import com.example.idealjavafx.Helper;
import com.example.idealjavafx.MainFunction;
import javafx.scene.chart.*;
import javafx.scene.control.Label;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
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
            double min = list1.get(i).stream().mapToDouble(a -> a).min().orElseThrow();
            List<Double> tempList = new ArrayList<>();
            for (int j = 0; j < list1.get(i).size(); j++) {
                tempList.add((list1.get(i).get(j) - min) / (max - min));
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


    //бульбашкова діаграма
    public static void bubbleDiagram(List<List<Double>> listNotSorted, List<List<Double>> listSorted, BubbleChart bubbleChart, NumberAxis xAxis, NumberAxis yAxis) {
        if (listSorted.size() != 3) {
            JOptionPane.showMessageDialog(null, "Кількість ознак має бути 3", "Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            bubbleChart.getData().clear();
            bubbleChart.layout();
            xAxis.setAutoRanging(false);
            xAxis.setLowerBound(listSorted.get(0).get(0) - 10);
            xAxis.setUpperBound(listSorted.get(0).get(listSorted.get(0).size() - 1) + 10);

            yAxis.setAutoRanging(false);
            yAxis.setLowerBound(listSorted.get(1).get(0) - 10);
            yAxis.setUpperBound(listSorted.get(1).get(listSorted.get(1).size() - 1) + 10);

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
        scatterChart.getData().clear();
        scatterChart.layout();

        xAxis.setLowerBound(listNotSorted.get(listNotSorted.size() - 1).stream().mapToDouble(a -> a).min().orElseThrow());
        xAxis.setUpperBound(listNotSorted.get(listNotSorted.size() - 1).stream().mapToDouble(a -> a).max().orElseThrow());
        xAxis.setLabel("y");
        yAxis.setLabel("ɛ");

        double[][] xMatrix = new double[listNotSorted.size() - 1][listNotSorted.get(0).size()];
        double[][] x_ = new double[1][listNotSorted.size() - 1];
        for (int i = 0; i < listNotSorted.size() - 1; i++) {
            for (int j = 0; j < listNotSorted.get(0).size(); j++) {
                xMatrix[i][j] = listNotSorted.get(i).get(j);
            }
            x_[0][i] = listNotSorted.get(i).stream().mapToDouble(a -> a).average().orElseThrow();
        }
        double[][] y0Matrix = new double[1][listNotSorted.get(0).size()];
        for (int i = 0; i < listNotSorted.get(0).size(); i++) {
            y0Matrix[0][i] = listNotSorted.get(listNotSorted.size() - 1).get(i);
        }
        var aMatrix = MainFunction.multiplyMatrixOnMatrix(MainFunction.getInverseMatrix(MainFunction.multiplyMatrixOnMatrix(xMatrix, MainFunction.transposeMatrix(xMatrix))),
                MainFunction.multiplyMatrixOnMatrix(xMatrix, MainFunction.transposeMatrix(y0Matrix)));
        double a1 = aMatrix[0][0];
        double a2 = aMatrix[1][0];
        double a0 = listNotSorted.get(listNotSorted.size() - 1).stream().mapToDouble(s -> s).average().orElseThrow() - a1 * x_[0][0] - a2 * x_[0][1];
        double a3 = 0;
        double a4 = 0;
        double a5 = 0;
        if (listNotSorted.size() == 4) {
            a3 = aMatrix[2][0];
            a0 -= a3 * x_[0][2];
        }
        if (listNotSorted.size() == 5) {
            a4 = aMatrix[3][0];
            a0 -= a4 * x_[0][3];
        }
        if (listNotSorted.size() == 6) {
            a5 = aMatrix[4][0];
            a0 -= a5 * x_[0][4];
        }

        double minYAxis = (listNotSorted.get(2).get(0) - a0 - a1 * listNotSorted.get(0).get(0) + a2 * listNotSorted.get(1).get(0)) / 10;
        double maxYAxis = (listNotSorted.get(2).get(0) - a0 - a1 * listNotSorted.get(0).get(0) + a2 * listNotSorted.get(1).get(0)) / 10;

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Діагностична діаграма");
        for (int i = 0; i < listNotSorted.get(2).size(); i++) {
            double temp = 0;
            if (listNotSorted.size() == 3) {
                temp = a1 * listNotSorted.get(0).get(i) + a2 * listNotSorted.get(1).get(i);
            } else if (listNotSorted.size() == 4) {
                temp = a1 * listNotSorted.get(0).get(i) + a2 * listNotSorted.get(1).get(i) + a3 * listNotSorted.get(2).get(i);
            } else if (listNotSorted.size() == 5) {
                temp = a1 * listNotSorted.get(0).get(i) + a2 * listNotSorted.get(1).get(i) + a3 * listNotSorted.get(2).get(i) + a4 * listNotSorted.get(3).get(i);
            } else {
                temp = a1 * listNotSorted.get(0).get(i) + a2 * listNotSorted.get(1).get(i) + a3 * listNotSorted.get(2).get(i) + a4 * listNotSorted.get(3).get(i) + a5 * listNotSorted.get(4).get(i);
            }
            double eps = listNotSorted.get(listNotSorted.size() - 1).get(i) - a0 - temp;
            series1.getData().add(new XYChart.Data(listNotSorted.get(2).get(i), eps));
            if (eps < minYAxis) {
                minYAxis = eps;
            }
            if (eps > maxYAxis) {
                maxYAxis = eps;
            }
        }
        yAxis.setLowerBound(minYAxis);
        yAxis.setUpperBound(maxYAxis);
        scatterChart.getData().addAll(series1);
    }

    //графік зламаної тростини
    public static void brokenStickVizual(LineChart lineChart, List<List<Double>> listNotSorted) {
        lineChart.getData().clear();
        lineChart.layout();
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Графік зламаної тростини");
        for (int i = 0; i < HelloController.vlasniiValues.size(); i++) {
            series1.getData().add(new XYChart.Data(i + 1, HelloController.vlasniiValues.get(i)));
        }
        lineChart.getData().addAll(series1);
    }

    //часовий ряд
    public static void timeRowVisual(LineChart lineChart,NumberAxis xAxis, NumberAxis yAxis,  List<List<Double>> list) {
        var time = list.get(0);
        var parameters = list.get(1);
        XYChart.Series series1 = new XYChart.Series();
        for (int i = 0; i < time.size(); i++) {
            series1.getData().add(new XYChart.Data(time.get(i), parameters.get(i)));
        }
        lineChart.getData().addAll(series1);
        lineChart.getXAxis().setAutoRanging(true);
        lineChart.getYAxis().setAutoRanging(true);

        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);

        xAxis.setForceZeroInRange(false); // Вимкнення примусового нуля
        yAxis.setForceZeroInRange(false);
    }
}
