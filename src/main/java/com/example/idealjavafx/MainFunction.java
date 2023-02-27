package com.example.idealjavafx;

import java.math.BigDecimal;
import java.util.List;

public class MainFunction {
    //матиматичне сподівання
    public static double matSpodivan(List<Double> arr1){
        return arr1.stream().mapToDouble(a->a).average().orElseThrow();
    }

    //дисперсія
    public static double duspersia(List<Double> arr1){
        double resultSA = arr1.stream().mapToDouble(a->a).average().orElseThrow();
        double dus = 0;
        for (int i = 0; i < arr1.size(); i++) {
            dus += Math.pow((arr1.get(i) - resultSA), 2) / ((arr1.size() - 1));
        }
        return dus;
    }

    //середнє квадратичне
    public static double serKva(List<Double> arr1){
        return Math.sqrt(duspersia(arr1));
    }

    //R для дисперсійно-коваріаційнії матриці
    public static double rForKovMatrix(List<Double> arr1, List<Double> arr2){
        double tempResultSA1AndSA2 = 0;
        for (int i = 0; i < arr1.size(); i++) {
            tempResultSA1AndSA2 += arr1.get(i) * arr2.get(i);
        }
        double resultSA1AndSA2 = tempResultSA1AndSA2 / arr1.size();//xy_
        double r = (arr1.size() / (arr1.size() - 1)) *
                ((resultSA1AndSA2 - matSpodivan(arr1) * matSpodivan(arr2)) / (serKva(arr1) * serKva(arr2)));
        return r;
    }
}
