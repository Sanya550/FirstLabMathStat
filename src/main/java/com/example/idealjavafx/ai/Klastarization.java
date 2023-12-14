package com.example.idealjavafx.ai;

import com.example.idealjavafx.MainFunction;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class Klastarization {
    private static final double epsForKAverageMethod = 0.1;
    private static final double alForLansViliamsMethod = 0.5;
    private static final double ahForLansViliamsMethod = 0.5;
    private static final double nUForLansViliamsMethod = 0;

    public void graphicKlasterizationForKAverage(ScatterChart<Number, Number> scatterChart, NumberAxis xAxis, NumberAxis yAxis, List<List<Double>> listOfLists) {
        findKlasterizationGraph(scatterChart, xAxis, yAxis, listOfLists, false);
    }

    public void graphicKlasterizationForAglomirative(ScatterChart<Number, Number> scatterChart, NumberAxis xAxis, NumberAxis yAxis, List<List<Double>> listOfLists) {
        findKlasterizationGraph(scatterChart, xAxis, yAxis, listOfLists, true);
    }

    public String getKlasterizationGradeOfKlastersForKAverage(List<List<Double>> listOfLists) {
        return findKlasterizationGradeOfKlasters(listOfLists, false);
    }

    public String getKlasterizationGradeOfKlastersForAglomirative(List<List<Double>> listOfLists) {
        return findKlasterizationGradeOfKlasters(listOfLists, true);
    }

    private String findKlasterizationGradeOfKlasters(List<List<Double>> listOfLists, boolean isAglomerative) {
        var quantityOfKlasterization = Integer.parseInt(JOptionPane.showInputDialog("Введіть к-сть кластерів"));
        var listOfIndexesInput = Arrays.stream(JOptionPane.showInputDialog("Введіть два числа(№ ознак) через пробіл(наприклад: 1 2)").trim().split(" ")).map(v -> Integer.parseInt(v) - 1).collect(Collectors.toList());
        var typeOfMethodInput = Integer.parseInt(JOptionPane.showInputDialog("Оберіть метрику(Введіть число від 1 до 4):" +
                "\n1-Евклідова,\n2-Манхетенська,\n3-Чебишова,\n4-Мехаланобіса"));

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

        List<List<Integer>> klasterixationIndexes;
        if (isAglomerative) {
            klasterixationIndexes = getKlastarizationListsByAglomirativeMethod(dotsList, quantityOfKlasterization, typeOfMethodInput);
        } else {
            klasterixationIndexes = getKlastarizationListsByKAverage(dotsList, quantityOfKlasterization, typeOfMethodInput);
        }
        return gradeOfKlasters(klasterixationIndexes, listOfLists, dotsList, typeOfMethodInput, listOfIndexesInput);
    }


    private void findKlasterizationGraph(ScatterChart<Number, Number> scatterChart, NumberAxis xAxis, NumberAxis yAxis, List<List<Double>> listOfLists, boolean isAglomerative) {
        var quantityOfKlasterization = Integer.parseInt(JOptionPane.showInputDialog("Введіть к-сть кластерів"));
        var listOfIndexesInput = Arrays.stream(JOptionPane.showInputDialog("Введіть два числа(№ ознак) через пробіл(наприклад: 1 2)").trim().split(" ")).map(v -> Integer.parseInt(v) - 1).collect(Collectors.toList());
        var typeOfMethodInput = Integer.parseInt(JOptionPane.showInputDialog("Оберіть метрику(Введіть число від 1 до 4):" +
                "\n1-Евклідова,\n2-Манхетенська,\n3-Чебишова,\n4-Мехаланобіса"));

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

        List<List<Integer>> klasterixationIndexes;
        if (isAglomerative) {
            klasterixationIndexes = getKlastarizationListsByAglomirativeMethod(dotsList, quantityOfKlasterization, typeOfMethodInput);
        } else {
            klasterixationIndexes = getKlastarizationListsByKAverage(dotsList, quantityOfKlasterization, typeOfMethodInput);
        }
        visualizationOnKorialiationGraphic(scatterChart, xAxis, yAxis, dotsMap, dotsList, klasterixationIndexes, listOfIndexesInput);
    }


    private void visualizationOnKorialiationGraphic(ScatterChart<Number, Number> scatterChart, NumberAxis xAxis,
                                                    NumberAxis yAxis, LinkedHashMap<Integer, ArrayList<Double>> mapOfData, List<List<Double>> dotsList,
                                                    List<List<Integer>> indexesOfData, List<Integer> indexOfVibirok) {
        //clear and set border:
        scatterChart.layout();
        scatterChart.getData().clear();
        var xIndex = indexOfVibirok.get(0);
        var yIndex = indexOfVibirok.get(1);
        var xListForBorder = new ArrayList<Double>();
        for (int i = 0; i < dotsList.size(); i++) {
            xListForBorder.add(dotsList.get(i).get(xIndex));
        }
        var minX = xListForBorder.stream().mapToDouble(v -> v).min().orElseThrow() - 0.1;
        var maxX = xListForBorder.stream().mapToDouble(v -> v).max().orElseThrow() + 0.1;
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

    //агломеративний метод:
    private List<List<Integer>> getKlastarizationListsByAglomirativeMethod(ArrayList<List<Double>> dotsList, int quantityOfKlastarization, int typeOfMethod) {
        var listOfKlasters = new ArrayList<List<Integer>>();
        for (int i = 0; i < dotsList.size(); i++) {
            listOfKlasters.add(new ArrayList<>(List.of(i)));
        }

        double[][] distanceMatrix = new double[dotsList.size() + 1][dotsList.size() + 1];
        for (int i = 0; i <= dotsList.size(); i++) {
            for (int j = 0; j <= listOfKlasters.size(); j++) {
                if (i == j) {
                    distanceMatrix[i][j] = -1d;
                } else if (i == 0) {
                    distanceMatrix[i][j] = j - 1;
                } else if (j == 0) {
                    distanceMatrix[i][j] = i - 1;
                } else {
                    distanceMatrix[i][j] = getDistanceBetweenDots(typeOfMethod, dotsList.get(i - 1), dotsList.get(j - 1));
                }
            }
        }


        while (listOfKlasters.size() != quantityOfKlastarization) {
            var indexes = getIndexesOfMinValue(distanceMatrix);
            distanceMatrix = refactorIndexesFromMatrix(distanceMatrix, indexes);
            for (int i = 0; i < listOfKlasters.size(); i++) {
                if (listOfKlasters.get(i).contains(indexes.get(0) - 1)) {
                    for (int j = 0; j < listOfKlasters.size(); j++) {
                        if (listOfKlasters.get(j).contains(indexes.get(1) - 1)) {
                            var klasterForUnite = new ArrayList<Integer>(new ArrayList(listOfKlasters.get(j)));
                            for (int k = 0; k < klasterForUnite.size(); k++) {
                                listOfKlasters.get(i).add(klasterForUnite.get(k));
                            }
                            listOfKlasters.remove(j);
                            break;
                        }
                    }
                    break;
                }
            }
        }

        return listOfKlasters;
    }

    private double[][] refactorIndexesFromMatrix(double[][] oldMatrix, List<Integer> indexes) {
        var matrix = new double[oldMatrix.length][oldMatrix.length];
        for (int i = 0; i < oldMatrix.length; i++) {
            for (int j = 0; j < oldMatrix.length; j++) {
                matrix[i][j] = oldMatrix[i][j];
            }
        }

        for (int j = 1; j < oldMatrix.length; j++) {
            var distanceBetweenKlasters = getDistanceBetweenKlasters(oldMatrix[j][indexes.get(0)], oldMatrix[j][indexes.get(1)]);
            matrix[indexes.get(0)][j] = distanceBetweenKlasters;
            matrix[j][indexes.get(0)] = distanceBetweenKlasters;
        }


        for (int i = 0; i < oldMatrix.length; i++) {
            if (i == indexes.get(1)) {
                for (int j = 0; j < oldMatrix.length; j++) {
                    matrix[i][j] = -1;
                    matrix[j][i] = -1;
                }
            }
        }
        return matrix;
    }

    private List<Integer> getIndexesOfMinValue(double[][] matrix) {
        var map = new LinkedHashMap<List<Integer>, Double>();
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                map.put(List.of(i, j), matrix[i][j]);
            }
        }
        List<Integer> minKey = null;
        var minValue = 1000d;

        for (Map.Entry<List<Integer>, Double> entry : map.entrySet()) {
            if (entry.getValue() < minValue && entry.getValue() >= 0) {
                minKey = entry.getKey();
                minValue = entry.getValue();
            }
        }
        return minKey;
    }

    private String gradeOfKlasters(List<List<Integer>> indexes, List<List<Double>> listOfLists, ArrayList<List<Double>> dotsList, int typeOfMethod, List<Integer> oznaki) {
        String klastresResult = "";
        var sum = 0d;
        for (int i = 0; i < indexes.size(); i++) {

            //for average:
            var originList1 = new ArrayList<Double>();
            for (int l = 0; l < indexes.get(i).size(); l++) {
                originList1.add(listOfLists.get(oznaki.get(0)).get(indexes.get(i).get(l)));
            }

            var originList2 = new ArrayList<Double>();
            for (int l = 0; l < indexes.get(i).size(); l++) {
                originList2.add(listOfLists.get(oznaki.get(1)).get(indexes.get(i).get(l)));
            }
//for Q1:
            var q1List = new ArrayList<List<Double>>();
            for (int l = 0; l < indexes.get(i).size(); l++) {
                q1List.add(dotsList.get(indexes.get(i).get(l)));
            }


            var q1ListReversed = new ArrayList<List<Double>>();
            for (int j = 0; j < q1List.get(0).size(); j++) {
                var tempList = new ArrayList<Double>();
                for (int k = 0; k < q1List.size(); k++) {
                    tempList.add(q1List.get(k).get(j));
                }
                q1ListReversed.add(tempList);
            }

            var average = new ArrayList<Double>();
            for (int j = 0; j < q1ListReversed.size(); j++) {
                average.add(q1List.get(j).stream().mapToDouble(v -> v).average().orElseThrow());
            }

            klastresResult += "--------------------------------------\n";
            klastresResult += String.format("Кластер №%d:\n Ознака %d середнє значення = %.2f, середнє квадратичне = %.2f\n Ознака %d середнє значення = %.2f, середнє квадратичне = %.2f\n",
                    i + 1, oznaki.get(0), MainFunction.matSpodivan(originList1), MainFunction.serKva(originList1),
                    oznaki.get(1), MainFunction.matSpodivan(originList2), MainFunction.serKva(originList2));
            for (int j = 0; j < q1List.size(); j++) {
                sum += getDistanceBetweenDots(typeOfMethod, q1List.get(j), average);
            }
        }
        String result = String.format("Q1(S) = %.2f\n", sum) + klastresResult;

        return result;
    }

    //метод k-середніх
    private List<List<Integer>> getKlastarizationListsByKAverage(ArrayList<List<Double>> dotsList, int quantityOfKlastarization, int typeOfMethod) {
        var klasterResultList = new ArrayList<List<Integer>>();
        for (int i = 0; i < quantityOfKlastarization; i++) {
            klasterResultList.add(new ArrayList<>());
        }

        int counter = 0;
        var klasterResultListPrevious = new ArrayList<List<Integer>>();
        while (true) {
            var randomList = new ArrayList<List<Double>>();
            if (counter == 0) {
                for (int i = 0; i < quantityOfKlastarization; i++) {
                    randomList.add(dotsList.get(i));
                }
            } else {
                randomList = new ArrayList<>(findNewCenterOfKlastersForKAverage(dotsList, klasterResultList));
                klasterResultList.clear();
                for (int i = 0; i < quantityOfKlastarization; i++) {
                    klasterResultList.add(new ArrayList<>());
                }
            }

            for (int j = 0; j < dotsList.size(); j++) {
                var tempMap = new HashMap<Integer, Double>();
                for (int i = 0; i < quantityOfKlastarization; i++) {
                    tempMap.put(i, getDistanceBetweenDots(typeOfMethod, dotsList.get(j), randomList.get(i)));
                }

                //find min:
                Map.Entry<Integer, Double> minEntry = null;
                for (var entry : tempMap.entrySet()) {
                    if (minEntry == null || entry.getValue() < minEntry.getValue()) {
                        minEntry = entry;
                    }
                }

                klasterResultList.get(minEntry.getKey()).add(j);
            }
            if (counter == 0) {
                klasterResultListPrevious = new ArrayList<>(klasterResultList);
            } else {
                if (verificationOfExitForKAverage(dotsList, klasterResultList, klasterResultListPrevious, typeOfMethod) || counter == 10000) {
                    break;
                } else {
                    klasterResultListPrevious = new ArrayList<>(klasterResultList);
                }
            }
            counter++;
        }

        return klasterResultList;
    }

    private boolean verificationOfExitForKAverage(List<List<Double>> dots, List<List<Integer>> current, List<List<Integer>> previous, int type) {
        boolean flag = true;
        var cr = new ArrayList<List<List<Double>>>();
        var pr = new ArrayList<List<List<Double>>>();
        for (int i = 0; i < current.size(); i++) {
            ArrayList<List<Double>> tempListCr = new ArrayList<>();
            for (int j = 0; j < current.get(i).size(); j++) {
                tempListCr.add(new ArrayList(dots.get(current.get(i).get(j))));
            }
            cr.add(tempListCr);
        }

        for (int i = 0; i < previous.size(); i++) {
            ArrayList<List<Double>> tempListPr = new ArrayList<>();
            for (int j = 0; j < previous.get(i).size(); j++) {
                tempListPr.add(new ArrayList(dots.get(previous.get(i).get(j))));
            }
            pr.add(tempListPr);
        }

        var crReversed0 = new ArrayList<List<Double>>();
            for (int j = 0; j < dots.get(0).size(); j++) {
                var tempList = new ArrayList<Double>();
                for (int k = 0; k < cr.get(0).size(); k++) {
                    tempList.add(cr.get(0).get(k).get(j));
                }
                crReversed0.add(tempList);
            }


        var prReversed0 = new ArrayList<List<Double>>();
        for (int j = 0; j < dots.get(0).size(); j++) {
            var tempList = new ArrayList<Double>();
            for (int k = 0; k < pr.get(0).size(); k++) {
                tempList.add(pr.get(0).get(k).get(j));
            }
            prReversed0.add(tempList);
        }

//        for (int i = 0; i < current.size(); i++) {
        var currentAverage = new ArrayList<Double>();
        for (int i = 0; i < crReversed0.size(); i++) {
            currentAverage.add(crReversed0.get(i).stream().mapToDouble(v->v).average().orElseThrow());
        }

        var previousAverage = new ArrayList<Double>();
        for (int i = 0; i < crReversed0.size(); i++) {
            previousAverage.add(prReversed0.get(i).stream().mapToDouble(v->v).average().orElseThrow());
        }

//            var currentAverage = current.get(i).stream().mapToInt(v -> v).average().orElseThrow();
//            var previousAverage = previous.get(i).stream().mapToInt(v -> v).average().orElseThrow();
            if (getDistanceBetweenDots(type,currentAverage,previousAverage) > epsForKAverageMethod) {
                flag = false;
//                break;
            }
//        }
        return flag;
    }

    private List<List<Double>> findNewCenterOfKlastersForKAverage(List<List<Double>> dotsList, List<List<Integer>> klasters) {
        var newCenterList = new ArrayList<List<Double>>();
        for (int i = 0; i < klasters.size(); i++) {
            ArrayList<List<Double>> tempList = new ArrayList<>();
            for (int j = 0; j < klasters.get(i).size(); j++) {
                tempList.add(dotsList.get(klasters.get(i).get(j)));
            }

            var values = new ArrayList<Double>();
            for (int j = 0; j < tempList.get(0).size(); j++) {
                double tSum = 0d;
                for (int k = 0; k < tempList.size(); k++) {
                    tSum += tempList.get(k).get(j);
                }
                values.add(tSum / tempList.size());
            }
            newCenterList.add(values);
        }
        return newCenterList;
    }

    private double getDistanceBetweenKlasters(double distanceToNewKlaster1, double distanceToNewKlaster2) {
        return alForLansViliamsMethod * distanceToNewKlaster1 +
                ahForLansViliamsMethod * distanceToNewKlaster2 +
                nUForLansViliamsMethod * Math.abs(distanceToNewKlaster1 - distanceToNewKlaster2);
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
        if (xi.size() == xh.size()) {
            return (evklidovaDistance(xi, xh) + manhetenDistance(xi, xh)) / 2;
        }
        var vMatrix = MainFunction.findRForDuspKovMatrixForManyVibirok(List.of(xi, xh));
        var temp = new double[xi.size()][0];
        for (int i = 0; i < xi.size(); i++) {
            temp[i][0] = xi.get(i) - xh.get(i);
        }
        var a = MainFunction.multiplyMatrixOnMatrix(MainFunction.multiplyMatrixOnMatrix(MainFunction.getInverseMatrix(vMatrix), temp), MainFunction.transposeMatrix(temp));
        return a[0][0];
        //matrix
    }
}
