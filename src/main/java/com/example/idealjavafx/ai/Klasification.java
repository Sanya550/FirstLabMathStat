package com.example.idealjavafx.ai;

import com.example.idealjavafx.MainFunction;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Klasification {
    private static final int typeOfMethod = 1;

    public void logisticRegression(ScatterChart<Number, Number> scatterChart, NumberAxis xAxis, NumberAxis yAxis, List<List<Double>> listOfListInitial) {
        var listOfIndexesInput = Arrays.stream(JOptionPane.showInputDialog("Введіть два числа(№ ознак) через пробіл(наприклад: 1 2)").trim().split(" ")).map(v -> Integer.parseInt(v) - 1).collect(Collectors.toList());
        var listOfDotInput = Arrays.stream(JOptionPane.showInputDialog("Введіть координати точки через пробіл(наприклад: 1 2)").trim().split(" ")).map(v -> Double.parseDouble(v)).collect(Collectors.toList());
//        var listOfKlasters = Arrays.stream(JOptionPane.showInputDialog("Введіть номера кластерів через пробіл(наприклад: 1 2)").trim().split(" ")).map(v -> Integer.parseInt(v) - 1).collect(Collectors.toList());
        var listOfKlasters = List.of(0, 1);
        //clear and set border:
        var xDot = listOfDotInput.get(0);
        var yDot = listOfDotInput.get(1);
        scatterChart.layout();
        scatterChart.getData().clear();
        var xIndex = listOfIndexesInput.get(0);
        var yIndex = listOfIndexesInput.get(1);
        var xListForBorder = new ArrayList<Double>();
        var dotsList = new ArrayList<List<Double>>();
        for (int i = 0; i < listOfListInitial.get(0).size(); i++) {
            var tempList = new ArrayList<Double>();
            for (int j = 0; j < listOfListInitial.size(); j++) {
                tempList.add(listOfListInitial.get(j).get(i));
            }
            dotsList.add(tempList);
        }
        for (int i = 0; i < dotsList.size(); i++) {
            xListForBorder.add(dotsList.get(i).get(xIndex));
        }
        var minX = xListForBorder.stream().mapToDouble(v -> v).min().orElseThrow() - 0.1;
        var minForRegr = minX;
        if (minX > xDot) {
            minX = xDot - 0.1;
        }
        var maxX = xListForBorder.stream().mapToDouble(v -> v).max().orElseThrow() + 0.1;
        var maxForRegr = maxX;
        if (maxX < xDot) {
            maxX = xDot + 0.1;
        }
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(minX);
        xAxis.setUpperBound(maxX);
        xAxis.setTickUnit((maxX + minX) / 10);

        var yListForBorder = new ArrayList<Double>();
        for (int i = 0; i < dotsList.size(); i++) {
            yListForBorder.add(dotsList.get(i).get(yIndex));
        }
        var minY = yListForBorder.stream().mapToDouble(v -> v).min().orElseThrow() - 0.1;
        var maxY = yListForBorder.stream().mapToDouble(v -> v).max().orElseThrow() + 0.1;
        if (minY > yDot) {
            minY = yDot - 0.1;
        }
        if (maxY < yDot) {
            maxY = yDot + 0.1;
        }
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(minY);
        yAxis.setUpperBound(maxY);
        yAxis.setTickUnit((maxY + minY) / 10);

        List<List<Integer>> klasterixationIndexes = new ArrayList<>();
        var temp1 = new ArrayList<Integer>();
        var temp2 = new ArrayList<Integer>();
        var temp3 = new ArrayList<Integer>();
        for (int i = 0; i < listOfListInitial.get(0).size(); i++) {
            var val = listOfListInitial.get(listOfListInitial.size() - 1).get(i).intValue();
            if (val == 0) {
                temp1.add(i);
            } else if (val == 1) {
                temp2.add(i);
            } else if (val == 2) {
                temp3.add(i);
            }
        }
        if (listOfKlasters.contains(0)) {
            klasterixationIndexes.add(temp1);
        }
        if (listOfKlasters.contains(1)) {
            klasterixationIndexes.add(temp2);
        }
        if (listOfKlasters.contains(2)) {
            klasterixationIndexes.add(temp3);
        }

        //logic:
        double[][] xMatrix = new double[klasterixationIndexes.get(0).size() + klasterixationIndexes.get(1).size()][2];
        double[] yMatrix = new double[klasterixationIndexes.get(0).size() + klasterixationIndexes.get(1).size()];
        int counter = 0;
        for (int i = 0; i < klasterixationIndexes.size(); i++) {
            XYChart.Series series = new XYChart.Series();
            series.setName(String.format("Кластер %d", i + 1));
            for (int j = 0; j < klasterixationIndexes.get(i).size(); j++) {
                xMatrix[counter][0] = listOfListInitial.get(listOfIndexesInput.get(0)).get(klasterixationIndexes.get(i).get(j));
                xMatrix[counter][1] = listOfListInitial.get(listOfIndexesInput.get(1)).get(klasterixationIndexes.get(i).get(j));
                yMatrix[counter] = listOfListInitial.get(listOfListInitial.size() - 1).get(klasterixationIndexes.get(i).get(j));
                series.getData().add(new XYChart.Data(
                        listOfListInitial.get(listOfIndexesInput.get(0)).get(klasterixationIndexes.get(i).get(j)),
                        listOfListInitial.get(listOfIndexesInput.get(1)).get(klasterixationIndexes.get(i).get(j))));
                counter++;
            }
            scatterChart.getData().add(series);
        }

        double learningRate = 0.1;
        var wArray = new double[2];
        wArray[0] = 0d;
        wArray[1] = 0d;
        double b = 0d;
        for (int i = 0; i < 250; i++) {
            var zArray = findZList(b, wArray, xMatrix);
            var prediction = MainFunction.sigmoid(zArray);
            double[] dz = new double[yMatrix.length];
            for (int j = 0; j < yMatrix.length; j++) {
                dz[j] = prediction[j] - yMatrix[j];
            }
            //dw:
            var dw = MainFunction.multiplyVectorOnDigit(MainFunction.multiplyMatrixOnVector(MainFunction.transposeMatrix(xMatrix), dz), (double) 1 / yMatrix.length);
            var db = Arrays.stream(dz).average().orElseThrow();
            wArray = MainFunction.subtractVectors(wArray, MainFunction.multiplyVectorOnDigit(dw, learningRate));
            b -= learningRate * db;
        }

        double val = (double) 1 / (1 + Math.pow(2.73, -wArray[0] * xDot + wArray[1] * yDot + b));
        var x1 = listOfListInitial.get(listOfIndexesInput.get(0)).get(klasterixationIndexes.get(0).get(0));
        var x2 = listOfListInitial.get(listOfIndexesInput.get(1)).get(klasterixationIndexes.get(0).get(0));

        var y1 = listOfListInitial.get(listOfIndexesInput.get(0)).get(klasterixationIndexes.get(1).get(0));
        var y2 = listOfListInitial.get(listOfIndexesInput.get(1)).get(klasterixationIndexes.get(1).get(0));
        listOfListInitial.get(listOfIndexesInput.get(1)).get(klasterixationIndexes.get(1).get(0));
        XYChart.Series seriesDot = new XYChart.Series();
        if (evklidovaDistance(List.of(xDot, yDot), List.of(x1, x2)) > evklidovaDistance(List.of(xDot, yDot), List.of(y1, y2))) {
            seriesDot.setName("Точка кластер2");
        } else {
            seriesDot.setName("Точка кластер1");
        }
        seriesDot.getData().add(new XYChart.Data(xDot, yDot));
        scatterChart.getData().add(seriesDot);

        var listX = new ArrayList<Double>();
        for (int i = 0; i < klasterixationIndexes.get(0).size(); i++) {
            listX.add(xMatrix[i][0]);
        }

        XYChart.Series seriesRegr = new XYChart.Series();
        seriesRegr.setName("Логістична регресія");
//        for (int i = 0; i < listX.size(); i ++) {
//            seriesRegr.getData().add(new XYChart.Data(listX.get(i), -(wArray[0]*listX.get(i)+b)/wArray[1]));
//        }
        for (double i = minForRegr; i < maxForRegr; i += 0.05) {
            seriesRegr.getData().add(new XYChart.Data(i, -(wArray[0] * i + b) / wArray[1]));
        }
        scatterChart.getData().add(seriesRegr);
        var refactoredList = dotsList.stream().filter(v -> v.get(v.size() - 1) != 2).collect(Collectors.toList());
        refactoredList = refactoredList.stream()
                .map(innerList ->
                        innerList.subList(0, innerList.size() - 1))
                .collect(Collectors.toList());
        var gr = Integer.parseInt(JOptionPane.showInputDialog("Вивести оцінки?(Якщо так введіть 1)"));
        JOptionPane.showMessageDialog(null, String.format("%.2f*x1 + %.2f*x2 + %.2f", wArray[0], wArray[1], b));
        if (gr == 1) {
            JOptionPane.showMessageDialog(null, gradeOfModelFor2(refactoredList, klasterixationIndexes), "Оцінка якості класифікації", JOptionPane.INFORMATION_MESSAGE);
        }
//        scatterChart.getXAxis().autoRangingProperty().set(true);
//        scatterChart.getYAxis().autoRangingProperty().set(true);
        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);

        xAxis.setForceZeroInRange(false); // Вимкнення примусового нуля
        yAxis.setForceZeroInRange(false);
    }

    private double[] findZList(double b, double[] w, double[][] x) {
        var t = MainFunction.multiplyMatrixOnVector(x, w);
        var res = new double[t.length];
        for (int i = 0; i < t.length; i++) {
            res[i] = t[i] + b;
        }
        return res;
    }


    public void methodNeighbourhoodSimple(ScatterChart<Number, Number> scatterChart, NumberAxis xAxis, NumberAxis yAxis, List<List<Double>> listOfListInitial) {
        var listOfIndexesInput = Arrays.stream(JOptionPane.showInputDialog("Введіть два числа(№ ознак) через пробіл(наприклад: 1 2)").trim().split(" ")).map(v -> Integer.parseInt(v) - 1).collect(Collectors.toList());
        var listOfDotInput = Arrays.stream(JOptionPane.showInputDialog("Введіть координати точки через пробіл(наприклад: 1 2 0 0)").trim().split(" ")).map(v -> Double.parseDouble(v)).collect(Collectors.toList());

        var listOfLists = new ArrayList<List<Double>>(new ArrayList<>(listOfListInitial));//without klasters
        listOfLists.remove(listOfListInitial.size() - 1);
        var dotsMap = new LinkedHashMap<Integer, ArrayList<Double>>();
        var dotsList = new ArrayList<List<Double>>();
        for (int i = 0; i < listOfLists.get(0).size(); i++) {
            var tempList = new ArrayList<Double>();
            for (int j = 0; j < listOfLists.size(); j++) {
                tempList.add(listOfLists.get(j).get(i));
            }
            dotsMap.put(i, tempList);
            dotsList.add(tempList);
        }

        //note: only for 3:
        List<List<Integer>> klasterixationIndexes = new ArrayList<>();
        var temp1 = new ArrayList<Integer>();
        var temp2 = new ArrayList<Integer>();
        var temp3 = new ArrayList<Integer>();
        for (int i = 0; i < listOfListInitial.get(0).size(); i++) {
            var val = listOfListInitial.get(listOfListInitial.size() - 1).get(i).intValue();
            if (val == 0) {
                temp1.add(i);
            } else if (val == 1) {
                temp2.add(i);
            } else if (val == 2) {
                temp3.add(i);
            }
        }
        klasterixationIndexes.add(temp1);
        klasterixationIndexes.add(temp2);
        klasterixationIndexes.add(temp3);
        visualizationOnKorialiationGraphicWithDotSimple(scatterChart, xAxis, yAxis, dotsMap, dotsList, klasterixationIndexes, listOfIndexesInput, listOfDotInput);
    }

    public void methodNeighbourhoodForKQuantity(ScatterChart<Number, Number> scatterChart, NumberAxis xAxis, NumberAxis yAxis, List<List<Double>> listOfListInitial) {
        var listOfIndexesInput = Arrays.stream(JOptionPane.showInputDialog("Введіть два числа(№ ознак) через пробіл(наприклад: 1 2)").trim().split(" ")).map(v -> Integer.parseInt(v) - 1).collect(Collectors.toList());
        var kDotsNumber = Integer.parseInt(JOptionPane.showInputDialog("Введіть кількість К(наприклад: 3)"));
        var listOfDotInput = Arrays.stream(JOptionPane.showInputDialog("Введіть координати точки через пробіл(наприклад: 1 2 0 0)").trim().split(" ")).map(v -> Double.parseDouble(v)).collect(Collectors.toList());

        var listOfLists = new ArrayList<List<Double>>(new ArrayList<>(listOfListInitial));//without klasters
        listOfLists.remove(listOfListInitial.size() - 1);
        var dotsMap = new LinkedHashMap<Integer, ArrayList<Double>>();
        var dotsList = new ArrayList<List<Double>>();
        for (int i = 0; i < listOfLists.get(0).size(); i++) {
            var tempList = new ArrayList<Double>();
            for (int j = 0; j < listOfLists.size(); j++) {
                tempList.add(listOfLists.get(j).get(i));
            }
            dotsMap.put(i, tempList);
            dotsList.add(tempList);
        }

        //note: only for 3:
        List<List<Integer>> klasterixationIndexes = new ArrayList<>();
        var temp1 = new ArrayList<Integer>();
        var temp2 = new ArrayList<Integer>();
        var temp3 = new ArrayList<Integer>();
        for (int i = 0; i < listOfListInitial.get(0).size(); i++) {
            var val = listOfListInitial.get(listOfListInitial.size() - 1).get(i).intValue();
            if (val == 0) {
                temp1.add(i);
            } else if (val == 1) {
                temp2.add(i);
            } else if (val == 2) {
                temp3.add(i);
            }
        }
        klasterixationIndexes.add(temp1);
        klasterixationIndexes.add(temp2);
        klasterixationIndexes.add(temp3);
        visualizationOnKorialiationGraphicWithKQuantityDots(scatterChart, xAxis, yAxis, dotsMap, dotsList, klasterixationIndexes, listOfIndexesInput, listOfDotInput, kDotsNumber);
    }

    public void methodNeighbourhoodModification(ScatterChart<Number, Number> scatterChart, NumberAxis xAxis, NumberAxis yAxis, List<List<Double>> listOfListInitial) {
        var listOfIndexesInput = Arrays.stream(JOptionPane.showInputDialog("Введіть два числа(№ ознак) через пробіл(наприклад: 1 2)").trim().split(" ")).map(v -> Integer.parseInt(v) - 1).collect(Collectors.toList());
        var listOfDotInput = Arrays.stream(JOptionPane.showInputDialog("Введіть координати точки через пробіл(наприклад: 1 2 0 0)").trim().split(" ")).map(v -> Double.parseDouble(v)).collect(Collectors.toList());

        var listOfLists = new ArrayList<List<Double>>(new ArrayList<>(listOfListInitial));//without klasters
        listOfLists.remove(listOfListInitial.size() - 1);
        var dotsMap = new LinkedHashMap<Integer, ArrayList<Double>>();
        var dotsList = new ArrayList<List<Double>>();
        for (int i = 0; i < listOfLists.get(0).size(); i++) {
            var tempList = new ArrayList<Double>();
            for (int j = 0; j < listOfLists.size(); j++) {
                tempList.add(listOfLists.get(j).get(i));
            }
            dotsMap.put(i, tempList);
            dotsList.add(tempList);
        }

        //note: only for 3:
        List<List<Integer>> klasterixationIndexes = new ArrayList<>();
        var temp1 = new ArrayList<Integer>();
        var temp2 = new ArrayList<Integer>();
        var temp3 = new ArrayList<Integer>();
        for (int i = 0; i < listOfListInitial.get(0).size(); i++) {
            var val = listOfListInitial.get(listOfListInitial.size() - 1).get(i).intValue();
            if (val == 0) {
                temp1.add(i);
            } else if (val == 1) {
                temp2.add(i);
            } else if (val == 2) {
                temp3.add(i);
            }
        }
        klasterixationIndexes.add(temp1);
        klasterixationIndexes.add(temp2);
        klasterixationIndexes.add(temp3);
        visualizationOnKorialiationGraphicWithDotModificat(scatterChart, xAxis, yAxis, dotsMap, dotsList, klasterixationIndexes, listOfIndexesInput, listOfDotInput);
    }

    private void visualizationOnKorialiationGraphicWithDotSimple(ScatterChart<Number, Number> scatterChart, NumberAxis xAxis,
                                                                 NumberAxis yAxis, LinkedHashMap<Integer, ArrayList<Double>> mapOfData, List<List<Double>> dotsList,
                                                                 List<List<Integer>> indexesOfData, List<Integer> indexOfVibirok, List<Double> newDot) {
        visualizationBasicGraph(scatterChart, xAxis, yAxis, mapOfData, dotsList, indexesOfData, indexOfVibirok, newDot);

        var xDot = newDot.get(indexOfVibirok.get(0));
        var yDot = newDot.get(indexOfVibirok.get(1));
        XYChart.Series seriesDot = new XYChart.Series();
        seriesDot.setName(String.format("Точка належить %d кластеру", simpleMethodRelateToWhichKlaster(newDot, dotsList, indexesOfData) + 1));
        seriesDot.getData().add(new XYChart.Data(xDot, yDot));
        scatterChart.getData().add(seriesDot);
        var gr = Integer.parseInt(JOptionPane.showInputDialog("Вивести оцінки?(Якщо так введіть 1)"));
        if (gr == 1) {
            JOptionPane.showMessageDialog(null, gradeOfModelFor3(dotsList, indexesOfData, 1, 0), "Оцінка якості класифікації", JOptionPane.INFORMATION_MESSAGE);
        }
        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);

        xAxis.setForceZeroInRange(false); // Вимкнення примусового нуля
        yAxis.setForceZeroInRange(false);
    }

    private void visualizationOnKorialiationGraphicWithKQuantityDots(ScatterChart<Number, Number> scatterChart, NumberAxis xAxis,
                                                                     NumberAxis yAxis, LinkedHashMap<Integer, ArrayList<Double>> mapOfData, List<List<Double>> dotsList,
                                                                     List<List<Integer>> indexesOfData, List<Integer> indexOfVibirok, List<Double> newDot, int k) {
        visualizationBasicGraph(scatterChart, xAxis, yAxis, mapOfData, dotsList, indexesOfData, indexOfVibirok, newDot);

        var xDot = newDot.get(indexOfVibirok.get(0));
        var yDot = newDot.get(indexOfVibirok.get(1));
        XYChart.Series seriesDot = new XYChart.Series();
        seriesDot.setName(String.format("Точка належить %d кластеру", kMethodRelateToWhichKlaster(newDot, dotsList, indexesOfData, k) + 1));
        seriesDot.getData().add(new XYChart.Data(xDot, yDot));
        scatterChart.getData().add(seriesDot);
        var gr = Integer.parseInt(JOptionPane.showInputDialog("Вивести оцінки?(Якщо так введіть 1)"));
        if (gr == 1) {
            JOptionPane.showMessageDialog(null, gradeOfModelFor3(dotsList, indexesOfData, 3, k), "Оцінка якості класифікації", JOptionPane.INFORMATION_MESSAGE);
        }
        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);

        xAxis.setForceZeroInRange(false); // Вимкнення примусового нуля
        yAxis.setForceZeroInRange(false);
    }

    private void visualizationOnKorialiationGraphicWithDotModificat(ScatterChart<Number, Number> scatterChart, NumberAxis xAxis,
                                                                    NumberAxis yAxis, LinkedHashMap<Integer, ArrayList<Double>> mapOfData, List<List<Double>> dotsList,
                                                                    List<List<Integer>> indexesOfData, List<Integer> indexOfVibirok, List<Double> newDot) {
        visualizationBasicGraph(scatterChart, xAxis, yAxis, mapOfData, dotsList, indexesOfData, indexOfVibirok, newDot);

        var xDot = newDot.get(indexOfVibirok.get(0));
        var yDot = newDot.get(indexOfVibirok.get(1));
        XYChart.Series seriesDot = new XYChart.Series();
        var val = modificationMethodRelateToWhichKlaster(newDot, dotsList, indexesOfData);
        if (val == -1) {
            seriesDot.setName(String.format("Точка не належить жодному кластеру"));
        } else {
            seriesDot.setName(String.format("Точка належить %d кластеру", val + 1));
        }
        var gr = Integer.parseInt(JOptionPane.showInputDialog("Вивести оцінки?(Якщо так введіть 1)"));
        if (gr == 1) {
            JOptionPane.showMessageDialog(null, gradeOfModelFor3(dotsList, indexesOfData, 2, 0), "Оцінка якості класифікації", JOptionPane.INFORMATION_MESSAGE);
        }
        seriesDot.getData().add(new XYChart.Data(xDot, yDot));
        scatterChart.getData().add(seriesDot);
        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);

        xAxis.setForceZeroInRange(false); // Вимкнення примусового нуля
        yAxis.setForceZeroInRange(false);
    }

    private int simpleMethodRelateToWhichKlaster(List<Double> newDot, List<List<Double>> dotsList, List<List<Integer>> klasterixationIndexes) {
        var minDist = 10000d;
        int index = -1;
        for (int i = 0; i < dotsList.size(); i++) {
            var tempDist = getDistanceBetweenDots(typeOfMethod, dotsList.get(i), newDot);
            if (minDist > tempDist) {
                minDist = tempDist;
                index = i;
            }
        }

        for (int i = 0; i < klasterixationIndexes.size(); i++) {
            if (klasterixationIndexes.get(i).contains(index)) {
                return i;
            }
        }
        return -1;
    }

    private int modificationMethodRelateToWhichKlaster(List<Double> newDot, List<List<Double>> dotsList, List<List<Integer>> klasterixationIndexes) {
        int klaster = simpleMethodRelateToWhichKlaster(newDot, dotsList, klasterixationIndexes);
        var klasterList = new ArrayList<List<Double>>();

        for (int i = 0; i < klasterixationIndexes.get(klaster).size(); i++) {
            klasterList.add(dotsList.get(klasterixationIndexes.get(klaster).get(i)));
        }

        var highestDistToDot = 0d;
        for (int i = 0; i < klasterList.size(); i++) {
            var tempDist = getDistanceBetweenDots(typeOfMethod, klasterList.get(i), newDot);
            if (highestDistToDot < tempDist) {
                highestDistToDot = tempDist;
            }
        }

        var highestDistBetweenDotsInKlaster = 0d;
        for (int i = 0; i < klasterList.size(); i++) {
            for (int j = 0; j < klasterList.size(); j++) {
                var tempDist = getDistanceBetweenDots(typeOfMethod, klasterList.get(i), klasterList.get(j));
                if (highestDistBetweenDotsInKlaster < tempDist) {
                    highestDistBetweenDotsInKlaster = tempDist;
                }
            }
        }
        if (highestDistBetweenDotsInKlaster <= highestDistToDot) {
            return -1;
        } else {
            return klaster;
        }
    }

    private int kMethodRelateToWhichKlaster(List<Double> newDot, List<List<Double>> dotsList, List<List<Integer>> klasterixationIndexes, int k) {
        int first = 0;
        int second = 0;
        int third = 0;
        for (int l = 0; l < k; l++) {
            var minDist = 10000d;
            int index = -1;
            for (int i = 0; i < dotsList.size(); i++) {
                var tempDist = getDistanceBetweenDots(typeOfMethod, dotsList.get(i), newDot);
                if (minDist > tempDist) {
                    minDist = tempDist;
                    index = i;
                }
            }
            dotsList.remove(index);

            for (int i = 0; i < klasterixationIndexes.size(); i++) {
                for (int j = 0; j < klasterixationIndexes.get(i).size(); j++) {
                    if (klasterixationIndexes.get(i).get(j) == index) {
                        if (i == 0) {
                            first++;
                        } else if (i == 1) {
                            second++;
                        } else if (i == 2) {
                            third++;
                        }
                        klasterixationIndexes.get(i).remove(j);
                        break;
                    }
                }
            }
        }
        if (first > second && first > third) {
            return 0;
        } else if (second > first && second > third) {
            return 1;
        } else {
            return 2;
        }
    }

    private void visualizationBasicGraph(ScatterChart<Number, Number> scatterChart, NumberAxis xAxis,
                                         NumberAxis yAxis, LinkedHashMap<Integer, ArrayList<Double>> mapOfData, List<List<Double>> dotsList,
                                         List<List<Integer>> indexesOfData, List<Integer> indexOfVibirok, List<Double> newDot) {
        //clear and set border:
        var xDot = newDot.get(indexOfVibirok.get(0));
        var yDot = newDot.get(indexOfVibirok.get(1));
        scatterChart.layout();
        scatterChart.getData().clear();
        var xIndex = indexOfVibirok.get(0);
        var yIndex = indexOfVibirok.get(1);
        var xListForBorder = new ArrayList<Double>();
        for (int i = 0; i < dotsList.size(); i++) {
            xListForBorder.add(dotsList.get(i).get(xIndex));
        }
        var minX = xListForBorder.stream().mapToDouble(v -> v).min().orElseThrow() - 0.1;
        if (minX > xDot) {
            minX = xDot - 0.1;
        }
        var maxX = xListForBorder.stream().mapToDouble(v -> v).max().orElseThrow() + 0.1;
        if (maxX < xDot) {
            maxX = xDot + 0.1;
        }
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(minX);
        xAxis.setUpperBound(maxX);
        xAxis.setTickUnit((maxX + minX) / 10);

        var yListForBorder = new ArrayList<Double>();
        for (int i = 0; i < dotsList.size(); i++) {
            yListForBorder.add(dotsList.get(i).get(yIndex));
        }
        var minY = yListForBorder.stream().mapToDouble(v -> v).min().orElseThrow() - 0.1;
        var maxY = yListForBorder.stream().mapToDouble(v -> v).max().orElseThrow() + 0.1;
        if (minY > yDot) {
            minY = yDot - 0.1;
        }
        if (maxY < yDot) {
            maxY = yDot + 0.1;
        }
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(minY);
        yAxis.setUpperBound(maxY);
        yAxis.setTickUnit((maxY + minY) / 10);

        //group klasters by indexes of map:
        for (int i = 0; i < indexesOfData.size(); i++) {
            XYChart.Series series = new XYChart.Series();
            series.setName(String.format("Кластер %d", i + 1));
            for (int j = 0; j < indexesOfData.get(i).size(); j++) {
                series.getData().add(new XYChart.Data(
                        mapOfData.get(indexesOfData.get(i).get(j)).get(indexOfVibirok.get(0)),
                        mapOfData.get(indexesOfData.get(i).get(j)).get(indexOfVibirok.get(1))));
            }
            scatterChart.getData().add(series);
        }
    }

    private String gradeOfModelFor2(List<List<Double>> dotsList, List<List<Integer>> klasterixationIndexes) {
        var navch = new ArrayList<List<Double>>();
        var test = new ArrayList<List<Double>>();
        var testKlaster = new ArrayList<Integer>();
        for (int i = 0; i < dotsList.size(); i++) {
            if ((i + 1) % 5 == 0) {
                test.add(dotsList.get(i));
                testKlaster.add(i);
            } else {
                navch.add(dotsList.get(i));
            }
        }

        int TP = 0;
        int FP = 0;
        int FN = 0;
        int TN = 0;
        for (int i = 0; i < test.size(); i++) {
            var actual = simpleMethodRelateToWhichKlaster(test.get(i), dotsList, klasterixationIndexes);
            var expected = klasterixationIndexes.get(0).contains(testKlaster.get(i)) ? 0 : 1;
            if (actual == expected) {
                if (actual == 1) {
                    TP++;
                } else {
                    TN++;
                }
            } else {
                if (expected > actual) {
                    FN++;
                } else {
                    FP++;
                }
            }
        }
        String result = "TP = " + TP + ", FP = " + FP + ", FN = " + FN + ", TN = " + TN;
        result += String.format("\nВідносна частота істинно позитивних результатів(чутливість) = %.2f", (double) TP / (TP + FN));
        result += String.format("\nВідносна частота помилкових тривог = %.2f", (double) FP / (TN + FP));
        result += String.format("\nВідносна частота хибних результатів = %.2f", (double) FN / (TP + FN));
        result += String.format("\nЧастота позитивно-негативних результатів(специфічність) = %.2f", (double) TN / (FP + TN));
        result += String.format("\nПрогностична точність позитивного результату = %.2f", (double) TP / (FP + TP));
        result += String.format("\nПрогностична точність негативного результату = %.2f", (double) TN / (FN + TN));
        result += String.format("\nВідносна частота хибних виявлень = %.2f", (double) FP / (FP + TP));
        result += String.format("\nПрогностична точність хибних пропусків = %.2f", (double) FN / (FN + TN));
        result += String.format("\nТочність = %.2f", (double) (TP + TN) / (TP + FP + FN + TN));
        return result;
    }

    private String gradeOfModelFor3(List<List<Double>> dotsList, List<List<Integer>> klasterixationIndexes, int a, int k) {
        var navch = new ArrayList<List<Double>>();
        var test = new ArrayList<List<Double>>();
        var testKlaster = new ArrayList<Integer>();
        for (int i = 0; i < dotsList.size(); i++) {
            if ((i + 1) % 5 == 0) {
                test.add(dotsList.get(i));
                testKlaster.add(i);
            } else {
                navch.add(dotsList.get(i));
            }
        }

        int[][] kMatrix = new int[klasterixationIndexes.size()][klasterixationIndexes.size()];
        for (int i = 0; i < kMatrix.length; i++) {
            for (int j = 0; j < kMatrix.length; j++) {
                kMatrix[0][0] = 0;
            }
        }
        for (int i = 0; i < test.size(); i++) {
            int actual = -1;
            if (a == 1) {
                actual = simpleMethodRelateToWhichKlaster(test.get(i), dotsList, klasterixationIndexes);
            } else if (a == 2) {
                actual = modificationMethodRelateToWhichKlaster(test.get(i), dotsList, klasterixationIndexes);
            } else if (a == 3) {
                actual = kMethodRelateToWhichKlaster(test.get(i), dotsList, klasterixationIndexes, k);
            }
            var expected = 0;
            if (klasterixationIndexes.get(1).contains(testKlaster.get(i))) {
                expected = 1;
            } else if (klasterixationIndexes.get(2).contains(testKlaster.get(i))) {
                expected = 2;
            }
            var val = kMatrix[actual][expected];
            kMatrix[actual][expected] = val + 1;
        }
        String result = "";
        for (int i = 0; i < kMatrix.length; i++) {
            for (int j = 0; j < kMatrix.length; j++) {
                result += String.format("%d  ", kMatrix[i][j]);
            }
            result += "\n";
        }

        return result;
    }

    private double getDistanceBetweenDots(int method, List<Double> xi, List<Double> xh) {
        switch (method) {
            case 1:
                return evklidovaDistance(xi, xh);
            case 2:
                return manhetenDistance(xi, xh);
            case 3:
                return chebishevaDistance(xi, xh);
            case 4:
                return mehalanobisaDistance(xi, xh);
            default:
                System.out.println("Number for getDistance must be correct");
                return -1;
        }
    }

    //евклідова відстань(1)
    private double evklidovaDistance(List<Double> xi, List<Double> xh) {
        var sum = 0d;
        for (int i = 0; i < xi.size(); i++) {
            sum += Math.pow(xi.get(i) - xh.get(i), 2);
        }
        return Math.sqrt(sum);
    }

    //манхетенська відстань(2)
    private double manhetenDistance(List<Double> xi, List<Double> xh) {
        var sum = 0d;
        for (int i = 0; i < xi.size(); i++) {
            sum += Math.abs(xi.get(i) - xh.get(i));
        }
        return sum;
    }

    //Чебишева відстань(3)
    private double chebishevaDistance(List<Double> xi, List<Double> xh) {
        var max = 0d;
        for (int i = 0; i < xi.size(); i++) {
            double temp = Math.abs(xi.get(i) - xh.get(i));
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    //Махаланобіса відстань(4)
    private double mehalanobisaDistance(List<Double> xi, List<Double> xh) {
        return Math.pow(evklidovaDistance(xi, xh), 2);
    }
}
