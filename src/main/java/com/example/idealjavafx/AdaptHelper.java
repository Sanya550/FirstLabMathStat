package com.example.idealjavafx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.IntStream;

import static com.example.idealjavafx.logicHelper.TimeRowHelper.changeValuesForStaticLinkedHashMap;

public class AdaptHelper {
    // Пороговые коэффициенты (можно вынести в параметры/конфиг)
    private static final double THRESH_TREND_HIGH = 0.7;
    private static final double THRESH_TREND_LOW = 0.3;
    private static final double THRESH_CV_HIGH = 1.0;   // высокий «шум»
    private static final double THRESH_CV_LOW = 0.3;   // низкий «шум»

    private static final int KOVZ_VALUE = 4;
    private static final int MNK_VALUE = 5;

    /**
     * Выбирает метод сглаживания на основании
     * коэффициента вариации и корреляции тренда.
     */
    public static SmoothingType selectMethod(LinkedHashMap<Integer, Double> rawData) {
        int n = rawData.size();
        double[] x = IntStream.range(0, n).asDoubleStream().toArray();
        double[] y = rawData.values().stream().mapToDouble(d -> d).toArray();

        // 1) Расчёт тренда
        double trendScore = pearsonCorrelation(x, y);

        // 2) Расчёт коэффициента вариации
        double mean = Arrays.stream(y).average().orElse(0.0);
        double std = Math.sqrt(Arrays.stream(y).map(v -> (v - mean) * (v - mean)).sum() / n);
        double cv = mean != 0.0 ? std / Math.abs(mean) : Double.POSITIVE_INFINITY;

        // 3) Сначала смотрим на выраженность тренда
        if (Math.abs(trendScore) > THRESH_TREND_HIGH) {
            return SmoothingType.MNK;
        }
        // 4) Затем — на шум
        if (cv > THRESH_CV_HIGH) {
            return SmoothingType.MEDIAN;
        }
        // 5) Умеренные шум/тренд → двойное сглаживание
        if (cv >= THRESH_CV_LOW && Math.abs(trendScore) >= THRESH_TREND_LOW) {
            return SmoothingType.DMA;
        }
        // 6) Малый шум + небольшой тренд → тройное
        if (cv < THRESH_CV_LOW && Math.abs(trendScore) >= THRESH_TREND_LOW) {
            return SmoothingType.TMA;
        }
        // 7) Малый шум + нет тренда → EMA
        if (cv < THRESH_CV_LOW && Math.abs(trendScore) < THRESH_TREND_LOW) {
            return SmoothingType.EMA;
        }
        // 8) Во всех прочих случаях — SMA
        return SmoothingType.SMA;
    }

    public static void applySmoothing(LinkedHashMap<Integer, Double> rawData) {
        var method = selectMethod(rawData);
        var values = new ArrayList(rawData.values());
        var newValues = new ArrayList<Double>();
        switch (method){
            case MNK:
                newValues = new ArrayList(MNK(values, MNK_VALUE));
                break;
            case EMA:
                newValues = new ArrayList(EMA(values, KOVZ_VALUE));
                break;
            case DMA:
                newValues = new ArrayList(DMA(values, KOVZ_VALUE));
                break;
            case TMA:
                newValues = new ArrayList(TMA(values, KOVZ_VALUE));
                break;
            case SMA:
                newValues = new ArrayList(SMA(values, KOVZ_VALUE));
                break;
            case MEDIAN:
                newValues = new ArrayList(median(values));
                break;
        }
        changeValuesForStaticLinkedHashMap(rawData, newValues);
    }

    private static List<Double> median(List<Double> initialElements) {
        var elements = new ArrayList<>(initialElements);
        var medianResult = new ArrayList<Double>();
        medianResult.add(elements.get(0));
        for (int i = 1; i < elements.size() - 1; i++) {
            medianResult.add(0.33 * (elements.get(i - 1) + elements.get(i) + elements.get(i + 1)));
        }
        medianResult.add(elements.get(elements.size() - 1));
        return medianResult;
    }

    private static List<Double> SMA(List<Double> initialElements, int kovz) {
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

    private static List<Double> MNK(List<Double> initialElements, int k) {
        var resultElements = new ArrayList<java.lang.Double>();
        var elements = new ArrayList<>(initialElements);
        for (int i = (k - 1) / 2; i < elements.size() - (k - 1) / 2; i++) {
            resultElements.add(getA0Value(initialElements, k, i));
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

    private static List<Double> EMA(List<Double> initialElements, int kKovz) {
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

    private static List<Double> DMA(List<Double> initialElements, int kovz) {
        var elements = new ArrayList<>(initialElements);
        var emaList = EMA(elements, kovz);
        return EMA(emaList, kovz);
    }

    private static List<Double> TMA(List<Double> initialElements, int kovz) {
        var elements = new ArrayList<>(initialElements);
        var emaList = EMA(elements, kovz);
        var dmaList = EMA(emaList, kovz);
        return EMA(dmaList, kovz);
    }

    private static double getA0Value(List<Double> initialList, int k, int index) {
        var elements = new ArrayList<>(initialList);
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

    private static double pearsonCorrelation(double[] x, double[] y) {
        int n = x.length;
        double mx = Arrays.stream(x).average().orElse(0.0);
        double my = Arrays.stream(y).average().orElse(0.0);

        double num = 0.0, sx = 0.0, sy = 0.0;
        for (int i = 0; i < n; i++) {
            double dx = x[i] - mx;
            double dy = y[i] - my;
            num += dx * dy;
            sx += dx * dx;
            sy += dy * dy;
        }
        if (sx == 0.0 || sy == 0.0) {
            return 0.0;
        }
        return num / Math.sqrt(sx * sy);
    }
}
