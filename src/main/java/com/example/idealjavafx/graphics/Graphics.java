package com.example.idealjavafx.graphics;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//Note: here locate graphics started from lab5
public class Graphics {
    public static void drawParalelKoordinatVizual(AreaChart areaChart, List<List<Double>> list1) {
        List<List<Double>> listWithRightData = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            double max = list1.get(i).stream().mapToDouble(a->a).max().orElseThrow();
            List<Double> tempList = new ArrayList<>();
            for (int j = 0; j < list1.get(i).size(); j++) {
                tempList.add(list1.get(i).get(j)/max);
            }
            listWithRightData.add(tempList);
        }

        for (int i = 0; i < listWithRightData.get(0).size(); i++) {
            XYChart.Series series1 = new XYChart.Series();
            for (int j = 0; j < listWithRightData.size(); j++) {
                series1.getData().add(new XYChart.Data(String.valueOf(j + 1), listWithRightData.get(j).get(i)));
            }
            areaChart.getData().addAll(series1);
        }
    }
}
