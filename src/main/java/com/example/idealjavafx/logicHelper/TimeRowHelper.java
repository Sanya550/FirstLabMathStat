package com.example.idealjavafx.logicHelper;

import com.example.idealjavafx.MainFunction;
import com.example.idealjavafx.graphics.Graphics;
import com.example.idealjavafx.tableView.TableViewHelper;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;

import javax.swing.*;
import javax.swing.text.TabableView;
import java.util.*;
import java.util.stream.Collectors;

public class TimeRowHelper {
    private static final double kvantilZna = 1.96;
    private static double matrixDecomposition[][];//Матриця для методу Гусені

    public String getMainCharacteristics(List<Double> elements) {
        return String.format("Математичне сподівання = %.2f,\nДисперсія = %.2f", MainFunction.matSpodivan(elements), MainFunction.duspersia(elements));
    }

    public void autokovariation(LineChart lineChart, NumberAxis xAxis, NumberAxis yAxis, List<List<Double>> initialList) {
        lineChart.getData().clear();
        lineChart.layout();
        var time = new ArrayList<>(initialList.get(0));
        var elements = new ArrayList<>(initialList.get(1));
        var autoKorivariation = new ArrayList<Double>();
        var m = MainFunction.matSpodivan(elements);
        for (int i = 0; i < elements.size() - 1; i++) {
            var chisel = 0d;
            for (int j = 0; j < elements.size() - i; j++) {
                chisel += (elements.get(j) - m) * (elements.get(j + i) - m);
            }
            chisel /= (elements.size() - i);
            var znam = elements.stream().mapToDouble(v -> Math.pow(v - m, 2) / elements.size()).sum();
            autoKorivariation.add(chisel);
        }

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Автоковаріація");
        for (int i = 0; i < autoKorivariation.size(); i++) {
            series2.getData().add(new XYChart.Data(time.get(i), autoKorivariation.get(i)));
        }
        lineChart.getData().addAll(series2);
        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);
        xAxis.setForceZeroInRange(false);
        yAxis.setForceZeroInRange(false);
    }

    public void autoKorilation(LineChart lineChart, NumberAxis xAxis, NumberAxis yAxis, List<List<Double>> initialList) {
        lineChart.getData().clear();
        lineChart.layout();
        var time = new ArrayList<>(initialList.get(0));
        var elements = new ArrayList<>(initialList.get(1));
        var autoKorilation = new ArrayList<Double>();
        var m = MainFunction.matSpodivan(elements);
        for (int i = 0; i < elements.size() - 1; i++) {
            var chisel = 0d;
            for (int j = 0; j < elements.size() - i; j++) {
                chisel += (elements.get(j) - m) * (elements.get(j + i) - m);
            }
            chisel /= (elements.size() - i);
            var znam = elements.stream().mapToDouble(v -> Math.pow(v - m, 2) / elements.size()).sum();
            autoKorilation.add(chisel / znam);
        }

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Автокореляція");
        for (int i = 0; i < autoKorilation.size(); i++) {
            series1.getData().add(new XYChart.Data(time.get(i), autoKorilation.get(i)));
        }
        lineChart.getData().addAll(series1);
        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);
        xAxis.setForceZeroInRange(false);
        yAxis.setForceZeroInRange(false);
    }

    public void changeAnomalData(List<Double> elements) {
        var k = Integer.parseInt(JOptionPane.showInputDialog("Введіть k: "));
        var matSpodivan = MainFunction.matSpodivan(elements);
        var serKva = MainFunction.serKva(elements);
        for (int i = 1; i < elements.size() - 1; i++) {
            if (matSpodivan - k * serKva > elements.get(i + 1) || elements.get(i + 1) > matSpodivan + k * serKva) {
                elements.set(i + 1, 2 * elements.get(i) - elements.get(i - 1));
            }
        }
    }

    //критерій знаків
    public String criteriaZnakiv(List<Double> initialElements) {
        var elements = new ArrayList<>(initialElements);
        var set = new HashSet<>(elements);
        elements = new ArrayList<>(set);
        var c = 0;//Кількість точок зростання
        for (int i = 0; i < elements.size() - 1; i++) {
            if (elements.get(i + 1) > elements.get(i)) {
                c++;
            }
        }
        double Ec = (elements.size() - 1) / 2d;
        double Dc = (elements.size() + 1) / 12d;
        double C = (c - Ec) / Math.sqrt(Dc);
        return getCriteriaMessage(C);
    }

    //критерій Манна:
    public String criteriaManna(List<Double> initialElements) {
        var elements = new ArrayList<>(initialElements);
        double t = 0d;
        for (int i = 0; i < elements.size(); i++) {
            for (int j = i + 1; j < elements.size(); j++) {
                if (elements.get(i) < elements.get(j)) {
                    t++;
                } else if (elements.get(i) == elements.get(j)) {
                    t += 0.5;
                }
            }
        }
        double Et = 0.25 * elements.size() * (elements.size() - 1);
        double Dt = elements.size() * (2 * elements.size() + 5) * (elements.size() - 1) / 72d;
        double u = (t + 0.5 - Et) / Math.sqrt(Dt);
        return getCriteriaMessage(u);
    }

    //критерій серій
    public String criteriaSeries(List<Double> initialElements) {
        var elements = new ArrayList<>(initialElements);
        var sortedElements = new ArrayList<>(elements);
        sortedElements.sort(Comparator.naturalOrder());
        double xM = sortedElements.size() % 2 == 1 ?
                0.5 * (sortedElements.get(initialElements.size() / 2) + sortedElements.get(initialElements.size() / 2 - 1)) :
                sortedElements.get((sortedElements.size() + 1) / 2 - 1);
        List<Integer> yi = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i) < xM) {
                yi.add(-1);
            } else {
                yi.add(1);
            }
        }

        var listOfSeries = new ArrayList<Integer>();
        int counter = 1;
        for (int i = 1; i < yi.size(); i++) {
            if (yi.get(i - 1) == yi.get(i)) {
                counter++;
            } else {
                listOfSeries.add(counter);
                counter = 1;
            }
        }
        listOfSeries.add(counter);
        int vN = listOfSeries.stream().mapToInt(v -> v).max().orElseThrow();
        int dN = listOfSeries.size();
        if (0.5 * (elements.size() + 1 - kvantilZna * Math.sqrt(elements.size() - 1)) < vN && dN < 3.3 * Math.log10(elements.size() + 1)) {
            return "Гіпотеза про стаціонарність процесу приймається";
        } else {
            return "Гіпотеза про стаціонарність процесу не приймається";
        }
    }

    //критерій зростаючих і спадаючих серій
    public String criteriaSeriesOfGrowAndFall(List<Double> initialElements) {
        var elements = new ArrayList<>(initialElements);
        List<Integer> yi = new ArrayList<>();
        for (int i = 0; i < elements.size() - 1; i++) {
            if (elements.get(i + 1) < elements.get(i)) {
                yi.add(-1);
            } else {
                yi.add(1);
            }
        }

        var listOfSeries = new ArrayList<Integer>();
        int counter = 1;
        for (int i = 1; i < yi.size(); i++) {
            if (yi.get(i - 1) == yi.get(i)) {
                counter++;
            } else {
                listOfSeries.add(counter);
                counter = 1;
            }
        }
        listOfSeries.add(counter);
        int vN = listOfSeries.stream().mapToInt(v -> v).max().orElseThrow();
        int dN = listOfSeries.size();
        int d0;
        if (elements.size() <= 26) {
            d0 = 5;
        } else if (elements.size() <= 153) {
            d0 = 6;
        } else {
            d0 = 7;
        }
        if (0.33 * (elements.size() * 2 - 1) - kvantilZna * Math.sqrt(16 * elements.size() - 29) < vN && dN > d0) {
            return "Гіпотеза про стаціонарність процесу приймається";
        } else {
            return "Гіпотеза про стаціонарність процесу не приймається";
        }
    }

    //критерій Аббе
    public String criteriaAbbe(List<Double> initialElements) {
        var elements = new ArrayList<>(initialElements);
        var s2 = MainFunction.duspersia(elements);
        var q2 = 0d;
        for (int i = 0; i < elements.size() - 1; i++) {
            q2 += Math.pow(elements.get(i) - elements.get(i + 1), 2);
        }
        q2 /= elements.size() - 1;
        double nU = 0.5 * q2 / s2;
        double u = Math.sqrt((Math.pow(elements.size(), 2) - 1) / (elements.size() - 2)) * (nU - 1);
        return getCriteriaMessage(u);
    }

    private String getCriteriaMessage(double value) {
        if (Math.abs(value) <= kvantilZna) {
            return "Гіпотеза про стаціонарність процесу приймається";
        } else {
            return "Гіпотеза про стаціонарність процесу не приймається";
        }
    }

    //Згладжування даних
    public void drawMedianZgl(LineChart lineChart, List<List<Double>> initialList) {
        var newElements = medianIroning(initialList.get(1));
        drawZgladzuvan(lineChart, initialList.get(0), newElements, 1);
        for (int i = 0; i < newElements.size(); i++) {
            initialList.get(1).set(i, newElements.get(i));
        }
    }

    private List<Double> medianIroning(List<Double> initialElements) {
        var elements = new ArrayList<>(initialElements);
        var medianResult = new ArrayList<Double>();
        medianResult.add(elements.get(0));
        for (int i = 1; i < elements.size() - 1; i++) {
            medianResult.add(0.33 * (elements.get(i - 1) + elements.get(i) + elements.get(i + 1)));
        }
        medianResult.add(elements.get(elements.size() - 1));
        return medianResult;
    }

    public void emaZgl(LineChart lineChart, List<List<Double>> initialList) {
        var kovz = Integer.parseInt(JOptionPane.showInputDialog("Введіть ковзне значення(наприклад 4):"));
        var newElements = EMA(initialList.get(1), kovz);
        drawZgladzuvan(lineChart, initialList.get(0), newElements, kovz);
        for (int i = 0; i < newElements.size(); i++) {
            initialList.get(1).set(i, newElements.get(i));
        }
    }

    private List<Double> EMA(List<Double> initialElements, int kKovz) {
        var elements = new ArrayList<>(initialElements);
        double alfa = 2d / ((double) kKovz + 1);
        var resultList = new ArrayList<Double>();
        for (int i = 0; i < elements.size(); i++) {
            if (i < kKovz) {
                resultList.add(elements.get(i));
            } else if (i == kKovz) {
                int counter = 0;
                double sum = 0d;
                for (int j = 0; j <= i; j++) {
                    sum += elements.get(j);
                    counter++;
                }
                resultList.add(sum / (double) counter);
            } else {
                var emaIndex = alfa * elements.get(i) + (1 - alfa) * resultList.get(resultList.size() - 1);
                resultList.add(emaIndex);
            }
        }
        return resultList;
    }

    public void dmaZgl(LineChart lineChart, List<List<Double>> initialList) {
        var kovz = Integer.parseInt(JOptionPane.showInputDialog("Введіть ковзне значення(наприклад 4):"));
        var newElements = DMA(initialList.get(1), kovz);
        drawZgladzuvan(lineChart, initialList.get(0), newElements, kovz);
        for (int i = 0; i < newElements.size(); i++) {
            initialList.get(1).set(i, newElements.get(i));
        }
    }

    private List<Double> DMA(List<Double> initialElements, int kovz) {
        var elements = new ArrayList<>(initialElements);
        var emaList = EMA(elements, kovz);
        return EMA(emaList, kovz);
    }

    public void tmaZgl(LineChart lineChart, List<List<Double>> initialList) {
        var kovz = Integer.parseInt(JOptionPane.showInputDialog("Введіть ковзне значення(наприклад 4):"));
        var newElements = TMA(initialList.get(1), kovz);
        drawZgladzuvan(lineChart, initialList.get(0), newElements, kovz);
        for (int i = 0; i < newElements.size(); i++) {
            initialList.get(1).set(i, newElements.get(i));
        }
    }

    private List<Double> TMA(List<Double> initialElements, int kovz) {
        var elements = new ArrayList<>(initialElements);
        var emaList = EMA(elements, kovz);
        var dmaList = EMA(emaList, kovz);
        return EMA(dmaList, kovz);
    }

    public void smaZgl(LineChart lineChart, List<List<Double>> initialList) {
        var kovz = Integer.parseInt(JOptionPane.showInputDialog("Введіть ковзне значення(наприклад 4):"));
        var newElements = SMA(initialList.get(1), kovz);
        drawZgladzuvan(lineChart, initialList.get(0), newElements, kovz);
        for (int i = 0; i < newElements.size(); i++) {
            initialList.get(1).set(i, newElements.get(i));
        }
    }

    private List<Double> SMA(List<Double> initialElements, int kovz) {
        var resultElements = new ArrayList<Double>();
        var elements = new ArrayList<>(initialElements);
        for (int i = 0; i < elements.size(); i++) {
            if (i < kovz - 1) {
                resultElements.add(elements.get(i));
            } else {
                var tempList = new ArrayList<Double>();
                for (int j = 0; j < kovz; j++) {
                    tempList.add(elements.get(i - j));
                }
                resultElements.add(tempList.stream().mapToDouble(v -> v).average().orElseThrow());
            }
        }
        return resultElements;
    }

    public void mnkZgl(LineChart lineChart, List<List<Double>> initialList) {
        var k = Integer.parseInt(JOptionPane.showInputDialog("Введіть K(5, 7 або 9)", "5"));
        if (k != 5 && k != 7 && k != 9) {
            JOptionPane.showMessageDialog(null, "K має бути 5, 7 або 9", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            var newElements = MNK(initialList, k);
            drawZgladzuvan(lineChart, initialList.get(0), newElements, 0);
            for (int i = 0; i < newElements.size(); i++) {
                initialList.get(1).set(i, newElements.get(i));
            }
        }
    }

    private List<Double> MNK(List<List<Double>> initialList, int k) {
        var resultElements = new ArrayList<Double>();
        var elements = new ArrayList<>(initialList.get(1));
        for (int i = (k - 1) / 2; i < elements.size() - (k - 1) / 2; i++) {
            resultElements.add(getA0Value(initialList, k, i));
        }

        for (int i = (k - 1) / 2 - 1; i >= 2; i--) {
            resultElements.add(0, 2 * resultElements.get(0) - resultElements.get(1));
        }

        for (int i = elements.size() - (k - 1) / 2 + 1; i <= elements.size() - 2; i++) {
            resultElements.add(2 * resultElements.get(resultElements.size() - 1) - resultElements.get(resultElements.size() - 2));
        }

        var x1 = (69 * resultElements.get(0) + 4 * resultElements.get(1) - 6 * resultElements.get(2) + 4 * resultElements.get(3) - resultElements.get(4)) / 70.0;
        var x2 = 2 * (2 * resultElements.get(0) + 27 * resultElements.get(1) + 12 * resultElements.get(2) - 8 * resultElements.get(3) + 2 * resultElements.get(4)) / 70.0;
        var prelast = 2 * (2 * resultElements.get(resultElements.size() - 5) - 8 * resultElements.get(resultElements.size() - 4) + 12 * resultElements.get(resultElements.size() - 3) + 27 * resultElements.get(resultElements.size() - 2) + 2 * resultElements.get(resultElements.size() - 1)) / 70.0;
        var last = (-resultElements.get(resultElements.size() - 5) + 4 * resultElements.get(resultElements.size() - 4) - 6 * resultElements.get(resultElements.size() - 3) + 4 * resultElements.get(resultElements.size() - 2) + 69 * resultElements.get(resultElements.size() - 1)) / 70.0;

        resultElements.add(0, x1);
        resultElements.add(1, x2);
        resultElements.add(prelast);
        resultElements.add(last);

        return resultElements;
    }

    private double getA0Value(List<List<Double>> initialList, int k, int index) {
        var elements = new ArrayList<>(initialList.get(1));
        if (k == 5) {
            return (-3 * elements.get(index - 2) + 12 * elements.get(index - 1) + 17 * elements.get(index) + 12 * elements.get(index + 1) - 3 * elements.get(index + 2)) / 35.0;
        } else if (k == 7) {
            return (-2 * elements.get(index - 3) + 3 * elements.get(index - 2) + 6 * elements.get(index - 1) + 7 * elements.get(index) + 6 * elements.get(index + 1) + 3 * elements.get(index + 2) - 2 * elements.get(index + 3)) / 21.0;
        } else {
            return (-21 * elements.get(index - 4) + 14 * elements.get(index - 3) + 39 * elements.get(index - 2) +
                    54 * elements.get(index - 1) + 59 * elements.get(index) + 54 * elements.get(index + 1) +
                    39 * elements.get(index + 2) + 14 * elements.get(index + 3) - 21 * elements.get(index + 4)) / 231.0;
        }
    }

    private void drawZgladzuvan(LineChart lineChart, List<Double> tList, List<Double> elements, int kovz) {
        XYChart.Series series1 = new XYChart.Series();
        for (int i = kovz; i < tList.size(); i++) {
            series1.getData().add(new XYChart.Data(tList.get(i), elements.get(i)));
        }
        lineChart.getData().addAll(series1);
    }

    //тренд лінійний
    public void liniinaTrend(List<List<Double>> initialElements, LineChart lineChart) {//t and elements
        var tList = new ArrayList<>(initialElements.get(0));
        var paremetersList = new ArrayList<>(initialElements.get(1));
        var t_ = MainFunction.matSpodivan(tList);
        var t2_ = tList.stream().mapToDouble(v -> v * v).average().orElseThrow();
        var x_ = MainFunction.matSpodivan(paremetersList);
        var tx_ = 0d;
        for (int i = 0; i < tList.size(); i++) {
            tx_ += tList.get(i) * paremetersList.get(i);
        }
        tx_ /= tList.size();
        var a0 = (x_ * t2_ - t_ * tx_) / (t2_ - t_ * t_);
        var a1 = (tx_ - t_ * x_) / (t2_ - t_ * t_);
        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data(tList.get(0), a0 + a1 * tList.get(0)));
        series1.getData().add(new XYChart.Data(tList.get(tList.size() - 1), a0 + a1 * tList.get(tList.size() - 1)));
        lineChart.getData().addAll(series1);

        var newElements = new ArrayList<Double>();
        for (int i = 0; i < paremetersList.size(); i++) {
            newElements.add(paremetersList.get(i) - a0 - a1 * tList.get(i));
        }
        for (int i = 0; i < newElements.size(); i++) {
            initialElements.get(1).set(i, newElements.get(i));
        }
    }

    //тренд параболічний
    public void parabolTrend(List<List<Double>> initialElements, LineChart lineChart) {
        var tList = new ArrayList<>(initialElements.get(0));
        var paremetersList = new ArrayList<>(initialElements.get(1));
        var t_ = MainFunction.matSpodivan(tList);
        var t2_ = tList.stream().mapToDouble(v -> v * v).average().orElseThrow();
        var x_ = MainFunction.matSpodivan(paremetersList);
        var tx_ = 0d;
        var t2x_ = 0d;
        for (int i = 0; i < tList.size(); i++) {
            tx_ += tList.get(i) * paremetersList.get(i);
            t2x_ += Math.pow(tList.get(i), 2) * paremetersList.get(i);
        }
        tx_ /= tList.size();
        t2x_ /= tList.size();
        var t3_ = tList.stream().mapToDouble(v -> Math.pow(v, 3)).average().orElseThrow();
        var t4_ = tList.stream().mapToDouble(v -> Math.pow(v, 4)).average().orElseThrow();

        var aVector = MainFunction.findSlar(new double[][]{
                {1, t_, t2_},
                {t_, t2_, t3_},
                {t2_, t3_, t4_},
        }, new double[]{x_, tx_, t2x_});
        XYChart.Series series1 = new XYChart.Series();
        for (int i = 0; i < tList.size(); i++) {
            series1.getData().add(new XYChart.Data(tList.get(i),
                    aVector[0] + aVector[1] * tList.get(i) + Math.pow(tList.get(i), 2) * aVector[2]));
        }
        lineChart.getData().addAll(series1);

        var newElements = new ArrayList<Double>();
        for (int i = 0; i < paremetersList.size(); i++) {
            newElements.add(paremetersList.get(i) - aVector[0] - aVector[1] * tList.get(i) - Math.pow(tList.get(i), 2) * aVector[2]);
        }
        for (int i = 0; i < newElements.size(); i++) {
            initialElements.get(1).set(i, newElements.get(i));
        }
    }

    //Метод Гусені
    public String decomposition(List<List<Double>> initialElements) {
        var elements = new ArrayList<>(initialElements.get(1));
        var N = elements.size();
        var M = Integer.parseInt(JOptionPane.showInputDialog(String.format("Введіть M(більше 2, але менше %d:", (int) N / 2)));
        double[][] xMatrix = new double[M][N - M + 1];
        int tmp = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N - M + 1; j++) {
                xMatrix[i][j] = elements.get(j + tmp);
            }
            tmp++;
        }
        var dcMatrix = MainFunction.multiplyMatrixOnMatrix(xMatrix, MainFunction.transposeMatrix(xMatrix));
        matrixDecomposition = MainFunction.getSortedVlasniVectors(dcMatrix);
        var vlValues = MainFunction.getVlasniiValues(matrixDecomposition).stream().mapToDouble(v -> Math.abs(v)).boxed().collect(Collectors.toList());
        vlValues.sort(Comparator.reverseOrder());

        //Вивід результатів:
        String result = "";
        for (int i = 0; i < matrixDecomposition.length; i++) {
            for (int j = -1; j < matrixDecomposition.length; j++) {
                if (j == -1) {
                    if (i < 10) {
                        result += String.format("X%d   ", i + 1);
                    } else {
                        result += String.format("X%d  ", i + 1);
                    }
                } else {
                    if (matrixDecomposition[i][j] >= 0) {
                        result += String.format("%.3f  ", matrixDecomposition[i][j]);
                    } else {
                        result += String.format("%.3f ", matrixDecomposition[i][j]);
                    }

                    if (j == matrixDecomposition.length - 1) {
                        result += "\n";
                    }
                }
            }
        }

        var sumVl = vlValues.stream().mapToDouble(v -> v).sum();
        var nkList = vlValues.stream().mapToDouble(v -> v / sumVl).boxed().collect(Collectors.toList());
        result += "нп%  ";
        for (int i = 0; i < nkList.size(); i++) {
            if (nkList.get(i) >= 0) {
                result += String.format("%.3f  ", nkList.get(i));
            } else {
                result += String.format("%.3f ", nkList.get(i));
            }
        }

        result += "\nнк%  ";
        for (int i = 0; i < nkList.size(); i++) {
            var tempList = new ArrayList<Double>();
            for (int j = 0; j < i+1; j++) {
                tempList.add(nkList.get(j));
            }
            var tSum = tempList.stream().mapToDouble(v->v).sum();

            if (tSum >= 0) {
                result += String.format("%.3f  ", tSum);
            } else {
                result += String.format("%.3f ", tSum);
            }
        }
        return result;
    }

    //Реконструкція
    public void reconstruction() {
//       var yMatrix = MainFunction.multiplyMatrixOnMatrix(aMatrix, xMatrix); //Реконструкція
    }
}
