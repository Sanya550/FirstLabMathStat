package com.example.idealjavafx.logicHelper;

import com.example.idealjavafx.MainFunction;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class TimeRowHelper {
    private static final double kvantilZna = 1.96;

    public String getMainCharacteristics(List<Double> elements) {
        //todo: автоковаріація
        return String.format("Математичне сподівання = %.2f,\nДисперсія = %.2f", MainFunction.matSpodivan(elements), MainFunction.duspersia(elements));
    }

    public List<Double> changeAnomalData(List<Double> initialElements, int k) {
        var elements = new ArrayList<>(initialElements);
        var matSpodivan = MainFunction.matSpodivan(elements);
        var duspersia = MainFunction.duspersia(elements);
        for (int i = 1; i < elements.size() - 1; i++) {
            if (matSpodivan - k * duspersia > elements.get(i + 1) || elements.get(i + 1) > matSpodivan + k * duspersia) {
                elements.set(i + 1, 2 * elements.get(i) - elements.get(i - 1));
            }
        }
        return elements;
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
        for (int i = 0; i < elements.size(); i++) {
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
    //медіане згладжування
    public List<Double> medianIroning(List<Double> initialElements) {
        var elements = new ArrayList<>(initialElements);
        var medianResult = new ArrayList<Double>();
        medianResult.add(elements.get(0));
        for (int i = 1; i < elements.size(); i++) {
            medianResult.add(0.33 * (elements.get(i - 1) + elements.get(i) + elements.get(i + 1)));
        }
        medianResult.add(elements.get(elements.size() - 1));
        return medianResult;
    }

    public List<Double> EMA(List<Double> initialElements, int kKovz) {
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

    public List<Double> DMA(List<Double> initialElements, int kovz) {
        var elements = new ArrayList<>(initialElements);
        var emaList = EMA(elements, kovz);
        return EMA(emaList, kovz);
    }

    public List<Double> TMA(List<Double> initialElements, int kovz) {
        var elements = new ArrayList<>(initialElements);
        var emaList = EMA(elements, kovz);
        var dmaList = EMA(emaList, kovz);
        return EMA(dmaList, kovz);
    }

    //тренд лінійний
    public void liniinaTrend(List<List<Double>> initialElements) {//t and elements
        if (initialElements.size() != 2) {
            JOptionPane.showMessageDialog(null, "size must be 2");
        } else {
            var tList = new ArrayList<>(initialElements.get(0));
            var paremetersList = new ArrayList<>(initialElements.get(1));
            //ініціалізація
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
            //todo: graphic a0 + a1*t;
        }
    }
}
