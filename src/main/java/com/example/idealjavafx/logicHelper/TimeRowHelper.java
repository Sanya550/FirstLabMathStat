package com.example.idealjavafx.logicHelper;

import com.example.idealjavafx.MainFunction;
import com.example.idealjavafx.graphics.Graphics;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class TimeRowHelper {
    private static final double kvantilZna = 1.96;

    public String getMainCharacteristics(List<Double> elements) {
        return String.format("Математичне сподівання = %.2f,\nДисперсія = %.2f", MainFunction.matSpodivan(elements), MainFunction.duspersia(elements));
    }

    public void changeAnomalData(List<Double> elements) {
        var k = Integer.parseInt(JOptionPane.showInputDialog("Введіть k: "));
//        var elements = new ArrayList<>(initialElements);
        var matSpodivan = MainFunction.matSpodivan(elements);
        var serKva = MainFunction.serKva(elements);
        for (int i = 1; i < elements.size() - 1; i++) {
            if (matSpodivan - k * serKva > elements.get(i + 1) || elements.get(i + 1) > matSpodivan + k * serKva) {
                elements.set(i + 1, 2 * elements.get(i) - elements.get(i - 1));
            }
        }
//        return elements;
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
                    aVector[0] + aVector[1] * tList.get(i) + Math.pow(aVector[2], 2) * tList.get(i)));
        }
        lineChart.getData().addAll(series1);
    }
}
