package com.example.idealjavafx.ai;

import java.util.*;

public class Klastarization {

    public void findKlasterization(List<List<Double>> listOfLists, int quantityOfKlasterization) {
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

        var a = getKlastarizationListsByKAverage(dotsList, quantityOfKlasterization, 1);
        int a1 = 1 + 2;
    }

    //метод k-середніх
    public List<List<Integer>> getKlastarizationListsByKAverage(ArrayList<List<Double>> dotsList, int quantityOfKlastarization, int typeOfMethod) {
        var klasterResultList = new ArrayList<List<Integer>>();
        for (int i = 0; i < quantityOfKlastarization; i++) {
            klasterResultList.add(new ArrayList<>());
        }

        for (int k = 0; k < 2; k++) {
            var randomList = new ArrayList<List<Double>>();
            if (k == 0) {
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
        }

        return klasterResultList;
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
                    tSum+=tempList.get(k).get(j);
                }
                values.add(tSum/tempList.size());
            }
            newCenterList.add(values);
        }
        return newCenterList;
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
        //todo:
        return 0;
    }
}
