package com.example.idealjavafx;

import org.apache.commons.math3.linear.*;
import java.util.List;

public class MainFunction {
    //матиматичне сподівання
    public static double matSpodivan(List<Double> arr1) {
        return arr1.stream().mapToDouble(a -> a).average().orElseThrow();
    }

    //дисперсія
    public static double duspersia(List<Double> arr1) {
        double resultSA = arr1.stream().mapToDouble(a -> a).average().orElseThrow();
        double dus = 0;
        for (int i = 0; i < arr1.size(); i++) {
            dus += Math.pow((arr1.get(i) - resultSA), 2) / ((arr1.size() - 1));
        }
        return dus;
    }

    //середнє квадратичне
    public static double serKva(List<Double> arr1) {
        return Math.sqrt(duspersia(arr1));
    }

    //R для дисперсійно-коваріаційнії матриці
    //todo: double-check this method
    public static double rForKovMatrix(List<Double> arr1, List<Double> arr2) {
        double tempResultSA1AndSA2 = 0;
        for (int i = 0; i < arr1.size(); i++) {
            tempResultSA1AndSA2 += arr1.get(i) * arr2.get(i);
        }
        double resultSA1AndSA2 = tempResultSA1AndSA2 / arr1.size();//xy_
        double r = (arr1.size() / (arr1.size() - 1)) *
                ((resultSA1AndSA2 - matSpodivan(arr1) * matSpodivan(arr2)) / (serKva(arr1) * serKva(arr2)));
        return r;
    }

    //знаходження визначника для матриці:
    public static double findDetermination(double arr[][]) {
        RealMatrix realMatrix = MatrixUtils.createRealMatrix(arr);
        LUDecomposition luDecomposition = new LUDecomposition(realMatrix);
        double determinant = luDecomposition.getDeterminant();
        return determinant;
    }

    //DC матриця
    public static double[][] findDCForDuspKovMatrixForManyVibirok(List<List<Double>> list) {
        double serKva1;
        double serKva2;
        double r;
        double arrDC[][] = new double[list.size()][list.size()];
        for (int i = 0; i < list.size(); i++) {
            serKva1 = MainFunction.serKva(list.get(i));
            for (int j = 0; j < list.size(); j++) {
                r = MainFunction.rForKovMatrix(list.get(i), list.get(j));
                serKva2 = MainFunction.serKva(list.get(j));
                if (i == j) {
                    arrDC[i][i] = serKva1 * serKva2;
                } else {
                    arrDC[i][j] = serKva1 * serKva2 * r;
                    arrDC[j][i] = serKva1 * serKva2 * r;
                }
            }

        }
        return arrDC;
    }

    //R матриця
    public static double[][] findRForDuspKovMatrixForManyVibirok(List<List<Double>> list) {
        double r;
        double arrR[][] = new double[list.size()][list.size()];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                r = MainFunction.rForKovMatrix(list.get(i), list.get(j));
                if (i == j) {
                    arrR[i][i] = 1;
                } else {
                    arrR[i][j] = r;
                    arrR[j][i] = r;
                }
            }

        }
        return arrR;
    }
}
