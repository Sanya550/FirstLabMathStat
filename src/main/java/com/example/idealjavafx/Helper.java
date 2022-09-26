package com.example.idealjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.*;

public class Helper {
    //u:
    static double u = 0;
    //f const:
    static double fFrom200To500 = 1;//DATA FROM TABLE 4;
    //t const:
    static double t1 = 1.96;//DATA FROM TABLE 2;

    //const:
    static double koefForBartlet(List<ArrayList> list) {
        double kva = 0;
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                count++;
            }
        }
        double temp = count / list.size();
        if (temp >= 500) {
            if (list.size() == 5) {
                kva = 9.49;
            } else if (list.size() == 4) {
                kva = 7.81;
            } else if (list.size() == 3) {
                kva = 5.99;
            } else if (list.size() == 2) {
                kva = 3.84;
            }
        } else if ((temp < 500) && (temp >= 200)) {
            if (list.size() == 5) {
                kva = 7.78;
            } else if (list.size() == 4) {
                kva = 6.25;
            } else if (list.size() == 3) {
                kva = 4.61;
            } else if (list.size() == 2) {
                kva = 2.71;
            }
        } else if (temp < 200) {
            if (list.size() == 5) {
                kva = 3.36;
            } else if (list.size() == 4) {
                kva = 2.37;
            } else if (list.size() == 3) {
                kva = 1.39;
            } else if (list.size() == 2) {
                kva = 0.455;
            }
        }
        return kva;
    }

    public static Map<Double, Integer> returnMap(ArrayList arrayList) {
        ArrayList arrayList1 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList1.add(arrayList.get(i));
        }
        Map<Double, Integer> dictT = new HashMap<Double, Integer>();
        for (int i = 0; i < arrayList1.size(); i++) {
            int count = dictT.getOrDefault(arrayList1.get(i), 0);
            dictT.put((Double) arrayList1.get(i), count + 1);
        }

        Map<Double, Integer> treeMap = new TreeMap<>(dictT);
        return treeMap;
    }

    static HashMap<Double, Double> varRow() {
        TreeMap treeMap1 = (TreeMap) Helper.returnMap(HelloController.arrayList);
        ArrayList arrayList1 = HelloController.arrayList;
        HashMap<Double, Double> hashMap = new HashMap<>();
        Set<String> set = new HashSet<>(arrayList1); //удаление дубликатов
        arrayList1.clear();
        arrayList1.addAll(set);
        arrayList1.sort(Comparator.naturalOrder());

        //summary general key:
        int generalKey = 0;
        for (int i = 0; i < treeMap1.size(); i++) {
            generalKey = generalKey + (int) treeMap1.get(arrayList1.get(i));
        }
        System.out.println("Var row: ");
        System.out.println("Числа|Частота");
        for (int j = 0; j < treeMap1.size(); j++) {
            int k = (int) treeMap1.get(arrayList1.get(j));
            double t = k;
            hashMap.put((Double) arrayList1.get(j), t / generalKey);

            System.out.println(arrayList1.get(j) + " ---> " + (t / generalKey));
        }
        return hashMap;
    }

    static ArrayList<Double> data() {
        ArrayList arrayList1 = HelloController.arrayList;
        int size = arrayList1.size();
        double alpha1 = HelloController.alfa;
        double kvantil1 = HelloController.kvantil;

        List<Double> list = new ArrayList();
        Set<String> set = new HashSet<>(arrayList1); //удаление дубликатов
        arrayList1.clear();
        arrayList1.addAll(set);
        arrayList1.sort(Comparator.naturalOrder());
        ArrayList arr1 = new ArrayList();
        ArrayList arr2 = new ArrayList();
        for (int i = 0; i < arrayList1.size(); i++) {
            arr1.add(arrayList1.get(i));
        }
        ArrayList arr3 = new ArrayList();
        for (int i = 0; i < arrayList1.size(); i++) {
            arr3.add(arrayList1.get(i));
        }
        arr3.sort(Comparator.naturalOrder());
        //---------------------------------->>>>>>>>>>>>>>>>
        //среднее арифметичне:
        double sa = 0;
        for (int i = 0; i < size - 2; i++) {
            if (!arr1.isEmpty()) {
                sa = sa + (double) arr1.get(i);
            }
        }
        double resultSA = sa / size;
        resultSA = BigDecimal.valueOf(resultSA).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        //дисперсія і середнє квадратичне відхилення:
        //Зсунене:
        double dusZs = 0;
        double summ = 0;
        for (int j = 0; j < size - 2; j++) {
            summ = summ + Math.pow((double) arr1.get(j), 2);
        }
        dusZs = summ / size - Math.pow(resultSA, 2);
        dusZs = BigDecimal.valueOf(dusZs).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        //НЕзсун:
        double dus = 0;
        for (int i = 0; i < arr1.size(); i++) {
            dus += Math.pow(((double) arr1.get(i) - resultSA), 2) / ((arr1.size() - 1));
        }
        dus = BigDecimal.valueOf(dus).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        //усічене середнє
        if (alpha1 == 0) {
            alpha1 = 0.3;
        }
        double k = alpha1 * size;
        double UsSer = 0;
        for (double i1 = k + 1; i1 < size - k; i1++) {
            UsSer += (i1) / (size - 2 * k);
        }
        UsSer = BigDecimal.valueOf(UsSer).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        //Асиметрія:
        //зсунена
        double asimetriaZs = 0;
        for (int i2 = 0; i2 < size - 2; i2++) {
            asimetriaZs += Math.pow(((double) arr1.get(i2) - resultSA), 3) / (size * Math.pow(Math.sqrt(dus), 3));
        }
        asimetriaZs = BigDecimal.valueOf(asimetriaZs).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        //незсунена:
        double asimetria = asimetriaZs * ((Math.sqrt(size * (size - 1))) / (size - 2));
        asimetria = BigDecimal.valueOf(asimetria).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();

        //ексцес
        //зсунен
        double ekszesZs = 0;
        for (int i3 = 0; i3 < size - 2; i3++) {
            ekszesZs += Math.pow(((double) arr1.get(i3) - resultSA), 4) / (size * Math.pow(Math.sqrt(dus), 4));
        }
        ekszesZs = BigDecimal.valueOf(ekszesZs).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        //незсунен
        double ekszes = ((Math.pow(size, 2) - 1) / ((size - 2) * (size - 3)) * ((ekszesZs - 3) + (6 / (size + 1))));
        ekszes = BigDecimal.valueOf(ekszes).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        //контрексцес
        double kontrEkszes = 1 / Math.sqrt(Math.abs(ekszes));
        kontrEkszes = BigDecimal.valueOf(kontrEkszes).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        //коеф Пірсона:
        double pirsona = Math.sqrt(dus) / resultSA;
        pirsona = BigDecimal.valueOf(pirsona).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        //MED(вибіркова медіана)
        double MED = 0;
        if (arr1.size() % 2 == 0) {
            MED = ((double) arr1.get((arr1.size() - 1) / 2) + (double) arr1.get(((arr1.size() - 1) / 2) + 1)) / 2;
        } else {
            MED = (double) arr1.get(((arr1.size() - 1) / 2) + 1);
        }
        MED = BigDecimal.valueOf(MED).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        //MAD(медіана абсолютних відхилень)
        for (int i = 0; i < arr1.size(); i++) {
            arr2.add(Math.abs((double) arr1.get(i) - MED));
        }
        double MEDforMAD = 0;

        if (arr2.size() % 2 == 0) {
            MEDforMAD = ((double) arr2.get((arr2.size() - 1) / 2) + (double) arr2.get(((arr2.size() - 1) / 2) + 1)) / 2;
        } else {
            MEDforMAD = (double) arr2.get(((arr2.size() - 1) / 2) + 1);
        }
        double MAD = 1.483 * MEDforMAD;
        MAD = BigDecimal.valueOf(MAD).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        //W(непараметричний коефіцієнт варіації)
        double W = MAD / MED;
        W = BigDecimal.valueOf(W).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        //Медіана Уолша:
        double MEDYolsha = 0;
        List<Double> listForMedYolsha = new ArrayList();
        for (int i = 0; i < arr3.size(); i++) {
            for (int j = 0; j < arr3.size(); j++) {
                if (j >= i) {
                    listForMedYolsha.add((((double) arr3.get(i) + (double) arr3.get(j)) * 0.5));
                }
            }
        }
        listForMedYolsha.sort(Comparator.naturalOrder());
        if (listForMedYolsha.size() % 2 == 0) {
            MEDYolsha = ((double) listForMedYolsha.get((listForMedYolsha.size() - 1) / 2) + (double) listForMedYolsha.get(((listForMedYolsha.size() - 1) / 2) + 1)) / 2;
        } else {
            MEDYolsha = (double) listForMedYolsha.get(((listForMedYolsha.size() - 1) / 2) + 1);
        }
        MEDYolsha = BigDecimal.valueOf(MEDYolsha).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        //-------------------------------------------->>>>>>>>
        list.add(0, resultSA);
        list.add(1, dus);
        list.add(2, dusZs);
        list.add(3, BigDecimal.valueOf((Math.sqrt(dus))).setScale(4, BigDecimal.ROUND_CEILING).doubleValue());
        list.add(4, UsSer);
        list.add(5, asimetria);
        list.add(6, asimetriaZs);
        list.add(7, ekszes);
        list.add(8, ekszesZs);
        list.add(9, kontrEkszes);
        list.add(10, pirsona);
        list.add(11, MED);
        list.add(12, MAD);
        list.add(13, W);
        list.add(14, MEDYolsha);
        return (ArrayList<Double>) list;
    }

    static List returnDeleteAnomalData(ArrayList arrayList) {
        ArrayList arrHelp = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            arrHelp.add(arrayList.get(i));
        }
        int size = arrHelp.size();
        Set<String> set = new HashSet<>(arrHelp); //удаление дубликатов
        arrHelp.clear();
        arrHelp.addAll(set);
        arrHelp.sort(Comparator.naturalOrder());

        ArrayList arr1 = new ArrayList();
        for (int i = 0; i < arrHelp.size(); i++) {
            arr1.add(arrayList.get(i));
        }
        arr1.sort(Comparator.naturalOrder());
        //дані:
        double sa = 0;
        for (int i = 0; i < arrHelp.size(); i++) {
            if (!arrHelp.isEmpty()) {
                sa = sa + (double) arrHelp.get(i);
            }
        }
        double resultSA = sa / size;

        double dus = 0;
        for (int i = 0; i < arr1.size(); i++) {
            dus += Math.pow(((double) arr1.get(i) - resultSA), 2) / ((arr1.size() - 1));
        }

        double asimetriaZs = 0;
        for (double i2 = 0; i2 < size; i2++) {
            asimetriaZs += Math.pow((i2 - resultSA), 3) / (size * Math.pow(Math.sqrt(dus), 3));
        }
        double asimetria = asimetriaZs * ((Math.sqrt(size * (size - 1))) / (size - 2));

        double ekszesZs = 0;
        for (double i3 = 0; i3 < size; i3++) {
            ekszesZs = Math.pow((i3 - resultSA), 4) / (size * Math.pow(Math.sqrt(dus), 4));
        }
        double ekszes = ((Math.pow(size, 2) - 1) / ((size - 2) * (size - 3)) * ((ekszesZs - 3) + (6 / (size + 1))));
        double t1 = 2 + 0.2 * Math.log10(0.04 * size);
        double t2 = Math.sqrt(19 * Math.sqrt(ekszes + 2) + 1);
        double a = 0;
        double b = 0;
        if (asimetria > 0.2) {
            a = resultSA - t1 * Math.sqrt(dus);
            b = resultSA + t2 * Math.sqrt(dus);
        } else if (asimetria < (-0.2)) {
            a = resultSA - t2 * Math.sqrt(dus);
            b = resultSA + t1 * Math.sqrt(dus);
        } else {
            a = resultSA - t1 * Math.sqrt(dus);
            b = resultSA + t1 * Math.sqrt(dus);
        }

        ArrayList<Double> rubish = new ArrayList();
        for (int i = 0; i < arr1.size(); i++) {
            if (((double) arr1.get(i) < a) || ((double) arr1.get(i) > b)) {
                rubish.add((double) arr1.get(i));
            }
        }
        arr1.removeAll(rubish);
        return arr1;
    }

    static ArrayList standartization(ArrayList arrayList) {
        ArrayList arr1 = new ArrayList();
        ArrayList arr2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            arr1.add(arrayList.get(i));
        }
        int size = arr1.size();
        //дані:
        double sa = 0;
        for (int i = 0; i < size; i++) {
            if (!arrayList.isEmpty()) {
                sa = sa + (double) arrayList.get(i);
            }
        }
        double resultSA = sa / size;
        double dus = 0;
        for (int i = 0; i < arr1.size(); i++) {
            dus += Math.pow(((double) arr1.get(i) - resultSA), 2) / ((arr1.size() - 1));
        }
        //
        for (int i = 0; i < arr1.size(); i++) {
            arr2.add(((double) arr1.get(i) - resultSA) / Math.sqrt(dus));
        }
        arr2.sort(Comparator.naturalOrder());
        return arr2;
    }

    static ArrayList zsuvAndLogarifm(ArrayList arrayList) {
        ArrayList arr1 = new ArrayList();
        ArrayList arr2 = new ArrayList();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arr1.add(arrayList.get(i));
        }
        double modulMin = Math.abs((double) arr1.get(0));

        for (int i = 0; i < arr1.size(); i++) {

            if ((double) arr1.get(i) <= 0) {
                arr2.add((Math.log((Double) arr1.get(i) + modulMin + 0.01)) + modulMin + 0.01);
            } else {
                arr2.add((Math.log((Double) arr1.get(i)) + 0.01) + modulMin + 0.01);
            }
        }
        arr2.sort(Comparator.naturalOrder());
        return arr2;
    }

    static void drawBarChartForShilnist(BarChart barChart, int numberOfClass, ArrayList arrayList, TreeMap treeMap) {
        int size = arrayList.size();
        XYChart.Series series = new XYChart.Series();
        series.setName("Гістограма");
        ArrayList arr = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            arr.add(arrayList.get(i));
        }
        double h = ((double) arr.get(arr.size() - 1) - (double) arr.get(0)) / (double) numberOfClass;
        double averageForColumn = 0;
        double start = 0;
        double finish = 0;
        double sumFrequency = 0;
        ArrayList<Double> rubish = new ArrayList<>();
        boolean flag = false;


        for (int i = 0; i < numberOfClass; i = i + 1) {
            try {
                start = (double) arr.get(0);
                finish = start + h;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Shil");
            }
            for (int j = 0; j < arr.size(); j++) {

                if ((start <= (double) arr.get(j)) && ((double) arr.get(j) < finish) && (!rubish.contains((double) arr.get(j)))) {

                    sumFrequency = sumFrequency + (int) treeMap.get(arr.get(j));

                    rubish.add((double) arr.get(j));
                    averageForColumn = BigDecimal.valueOf((finish + start) / 2).setScale(1, BigDecimal.ROUND_CEILING).doubleValue();
                    if (arr.get(j) == arr.get(arr.size() - 1)) {
                        flag = true;
                    }
                } else {
                    flag = true;
                }
                if ((rubish.size() != 0) && (flag == true)) {
                    double frequency = sumFrequency / size;
                    String rowKey = String.valueOf(i + 1);
                    String columnKey = String.valueOf(averageForColumn);
                    series.getData().add(new XYChart.Data(columnKey, frequency));
                    sumFrequency = 0;
                    arr.removeAll(rubish);
                    rubish.removeAll(rubish);
                    j = arr.size();
                    flag = false;

                }
            }
        }
        barChart.getData().addAll(series);
    }


    static void drawBarChartForEmpirationFunction(BarChart barChart, int numberOfClass, ArrayList arrayList, TreeMap treeMap) {
        int size = arrayList.size();
        XYChart.Series series = new XYChart.Series();
        series.setName("Емпірична функція");
        ArrayList arr = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            arr.add(arrayList.get(i));
        }
        double h = ((double) arr.get(arr.size() - 1) - (double) arr.get(0)) / (double) numberOfClass;
        double averageForColumn = 0;
        double start = 0;
        double finish = 0;
        double sumFrequency = 0;
        ArrayList<Double> rubish = new ArrayList<>();
        boolean flag = false;


        for (int i = 0; i < numberOfClass; i = i + 1) {
            try {
                start = (double) arr.get(0);
                finish = start + h;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Emp");
            }
            for (int j = 0; j < arr.size(); j++) {

                if ((start <= (double) arr.get(j)) && ((double) arr.get(j) < finish) && (!rubish.contains((double) arr.get(j)))) {

                    sumFrequency = sumFrequency + (int) treeMap.get(arr.get(j));

                    rubish.add((double) arr.get(j));
                    averageForColumn = BigDecimal.valueOf((finish + start) / 2).setScale(1, BigDecimal.ROUND_CEILING).doubleValue();
                    if (arr.get(j) == arr.get(arr.size() - 1)) {
                        flag = true;
                    }
                } else {
                    flag = true;
                }
                if ((rubish.size() != 0) && (flag == true)) {
                    double frequency = sumFrequency / size;
                    String rowKey = String.valueOf(i + 1);
                    String columnKey = String.valueOf(averageForColumn);
                    series.getData().add(new XYChart.Data(columnKey, frequency));
                    arr.removeAll(rubish);
                    rubish.removeAll(rubish);
                    j = arr.size();
                    flag = false;

                }
            }
        }
        barChart.getData().addAll(series);
    }

    static void drawAreaChartForShilnist(AreaChart areaChart, int numberOfClass, ArrayList arrayList, double kva, double dus) {
        double f1 = 0.0;
        double f2 = 0.0;
        ArrayList<Double> arr = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            arr.add((Double) arrayList.get(i));
        }
        double h = ((double) arr.get(arr.size() - 1) - (double) arr.get(0)) / (double) numberOfClass;
        double fir = arr.get(0);
        double end = fir + h;
        double z = Math.sqrt(arr.size()) * kva * dus;
        double kol = 1.0;
        for (int i = 1; i < 5; i++) {
            f1 = i * i - 0.5 * (1 - Math.pow((-1), i));
            f2 = 5 * i * i + 22 - 7.5 * (1 - Math.pow((-1), i));
            kol += 2 * (Math.pow(-1, i) * (Math.exp((-2) * Math.pow(i, 2) * Math.pow(z, 2)))) * (1 - ((2 * i * i * z) / (3 * Math.sqrt(arr.size()))) - ((f1 - 4 * (f1 + 3)) * i * i * z * z + 8 * Math.pow(i, 4) * Math.pow(z, 4)) / (18 * arr.size()) +
                    +i * i * z / (27 * Math.sqrt(Math.pow(arr.size(), 3))) * ((Math.pow(f2, 2) / 5) - (4 * (f2 + 45) * i * i * z * z / 15) + 8 * Math.pow(i, 4) * Math.pow(z, 4)));
        }

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Щільності");
        double count = 0.0;
        fir = arr.get(0);
        end = fir + h;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) <= end) {
                count++;
            } else {
                series1.getData().add(new XYChart.Data(String.valueOf(BigDecimal.valueOf((fir + end) / 2).setScale(1, BigDecimal.ROUND_CEILING).doubleValue()), count / arrayList.size()));
                count = 0;
                fir = end;
                end = fir + h;
            }
        }
        areaChart.getData().addAll(series1);
    }

    static void drawAreaChartForEmpirationFunction(AreaChart areaChart, int numberOfClass, ArrayList arrayList, double kva, double dus) {
        double pirson = 0.0;
        double nWithI = 0.0;
        double nCircle = 0.0;
        double f1 = 0.0;
        double f2 = 0.0;
        ArrayList<Double> arr = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            arr.add((Double) arrayList.get(i));
        }
        double h = ((double) arr.get(arr.size() - 1) - (double) arr.get(0)) / (double) numberOfClass;
        double fir = arr.get(0);
        double end = fir + h;
        double z = Math.sqrt(arr.size()) * kva * dus;
        double kol = 1.0;
        for (int i = 1; i < 5; i++) {
            f1 = i * i - 0.5 * (1 - Math.pow((-1), i));
            f2 = 5 * i * i + 22 - 7.5 * (1 - Math.pow((-1), i));
            kol += 2 * (Math.pow(-1, i) * (Math.exp((-2) * Math.pow(i, 2) * Math.pow(z, 2)))) * (1 - ((2 * i * i * z) / (3 * Math.sqrt(arr.size()))) - ((f1 - 4 * (f1 + 3)) * i * i * z * z + 8 * Math.pow(i, 4) * Math.pow(z, 4)) / (18 * arr.size()) +
                    +i * i * z / (27 * Math.sqrt(Math.pow(arr.size(), 3))) * ((Math.pow(f2, 2) / 5) - (4 * (f2 + 45) * i * i * z * z / 15) + 8 * Math.pow(i, 4) * Math.pow(z, 4)));
        }

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Емпірична");

        XYChart.Series seriesUp = new XYChart.Series();
        seriesUp.setName("Колмогорова: " + BigDecimal.valueOf(kol).setScale(4, BigDecimal.ROUND_CEILING).doubleValue());
        double count = 0.0;
        int schet = 0;
        ArrayList<Double> list = new ArrayList();
        count = 0.0;
        fir = arr.get(0);
        end = fir + h;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) <= end) {
                count++;
            } else {
                fir = end;
                end = fir + h;
                list.add(count / arrayList.size());
                if (schet != 0) {
                    nCircle = (list.get(schet) - list.get(schet - 1)) * arr.size();
                    if (nCircle != 0) {
                        pirson += Math.pow((list.get(schet) - nCircle), 2) / nCircle;
                    }
                }
                schet++;
            }
        }
        XYChart.Series seriesDown = new XYChart.Series();
        seriesDown.setName("Пірсона: " + BigDecimal.valueOf(pirson).setScale(4, BigDecimal.ROUND_CEILING).doubleValue());
//2:
        count = 0.0;
        fir = arr.get(0);
        end = fir + h;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) <= end) {
                count++;
            } else {
                seriesUp.getData().add(new XYChart.Data(String.valueOf(BigDecimal.valueOf((fir + end) / 2).setScale(1, BigDecimal.ROUND_CEILING).doubleValue()), count / (arrayList.size()) + kva * dus));
                series2.getData().add(new XYChart.Data(String.valueOf(BigDecimal.valueOf((fir + end) / 2).setScale(1, BigDecimal.ROUND_CEILING).doubleValue()), count / arrayList.size()));
                seriesDown.getData().add(new XYChart.Data(String.valueOf(BigDecimal.valueOf((fir + end) / 2).setScale(1, BigDecimal.ROUND_CEILING).doubleValue()), count / (arrayList.size()) - kva * dus));
                fir = end;
                end = fir + h;
            }
        }
        areaChart.getData().addAll(series2, seriesUp, seriesDown);
    }

    //lab3:
    static List<ArrayList> returnTwoCheckBox(CheckBox ch1, CheckBox ch2, CheckBox ch3, CheckBox ch4, CheckBox ch5, ArrayList arr1, ArrayList arr2, ArrayList arr3, ArrayList arr4, ArrayList arr5) {
        List<ArrayList> resultList = new ArrayList();
        if (ch1.isSelected() && ch2.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr2);
            return resultList;
        } else if (ch1.isSelected() && ch3.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr3);
            return resultList;
        } else if (ch1.isSelected() && ch4.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr4);
            return resultList;
        } else if (ch1.isSelected() && ch5.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr5);
            return resultList;
        } else if (ch2.isSelected() && ch3.isSelected()) {
            resultList.add(arr2);
            resultList.add(arr3);
            return resultList;
        } else if (ch2.isSelected() && ch4.isSelected()) {
            resultList.add(arr2);
            resultList.add(arr4);
            return resultList;
        } else if (ch2.isSelected() && ch5.isSelected()) {
            resultList.add(arr2);
            resultList.add(arr5);
            return resultList;
        } else if (ch3.isSelected() && ch4.isSelected()) {
            resultList.add(arr3);
            resultList.add(arr4);
            return resultList;
        } else if (ch3.isSelected() && ch5.isSelected()) {
            resultList.add(arr3);
            resultList.add(arr5);
            return resultList;
        } else if (ch4.isSelected() && ch5.isSelected()) {
            resultList.add(arr4);
            resultList.add(arr5);
            return resultList;
        } else {
            return resultList;
        }
    }

    static List<ArrayList> returnSeveralCheckBox(CheckBox ch1, CheckBox ch2, CheckBox ch3, CheckBox ch4, CheckBox ch5, ArrayList arr1, ArrayList arr2, ArrayList arr3, ArrayList arr4, ArrayList arr5) {
        List<ArrayList> resultList = new ArrayList();
        //5:
        if (ch1.isSelected() && ch2.isSelected() && ch3.isSelected() && ch4.isSelected() && ch5.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr2);
            resultList.add(arr3);
            resultList.add(arr4);
            resultList.add(arr5);
            return resultList;
        }
        //4:
        else if (ch1.isSelected() && ch2.isSelected() && ch3.isSelected() && ch4.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr2);
            resultList.add(arr3);
            resultList.add(arr4);
            return resultList;
        } else if (ch1.isSelected() && ch2.isSelected() && ch3.isSelected() && ch5.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr2);
            resultList.add(arr3);
            resultList.add(arr5);
            return resultList;
        } else if (ch1.isSelected() && ch2.isSelected() && ch4.isSelected() && ch5.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr2);
            resultList.add(arr4);
            resultList.add(arr5);
            return resultList;
        } else if (ch1.isSelected() && ch3.isSelected() && ch4.isSelected() && ch5.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr3);
            resultList.add(arr4);
            resultList.add(arr5);
            return resultList;
        } else if (ch2.isSelected() && ch3.isSelected() && ch4.isSelected() && ch5.isSelected()) {
            resultList.add(arr2);
            resultList.add(arr3);
            resultList.add(arr4);
            resultList.add(arr5);
            return resultList;
        }
        //3:
        else if (ch1.isSelected() && ch2.isSelected() && ch3.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr2);
            resultList.add(arr3);
            return resultList;
        } else if (ch1.isSelected() && ch2.isSelected() && ch4.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr2);
            resultList.add(arr4);
            return resultList;
        } else if (ch1.isSelected() && ch2.isSelected() && ch5.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr2);
            resultList.add(arr5);
            return resultList;
        } else if (ch1.isSelected() && ch3.isSelected() && ch4.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr3);
            resultList.add(arr4);
            return resultList;
        } else if (ch1.isSelected() && ch3.isSelected() && ch5.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr3);
            resultList.add(arr5);
            return resultList;
        } else if (ch1.isSelected() && ch4.isSelected() && ch5.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr4);
            resultList.add(arr5);
            return resultList;
        } else if (ch2.isSelected() && ch4.isSelected() && ch5.isSelected()) {
            resultList.add(arr2);
            resultList.add(arr4);
            resultList.add(arr5);
            return resultList;
        } else if (ch2.isSelected() && ch3.isSelected() && ch5.isSelected()) {
            resultList.add(arr2);
            resultList.add(arr3);
            resultList.add(arr5);
            return resultList;
        } else if (ch2.isSelected() && ch3.isSelected() && ch4.isSelected()) {
            resultList.add(arr2);
            resultList.add(arr3);
            resultList.add(arr4);
            return resultList;
        } else if (ch3.isSelected() && ch4.isSelected() && ch5.isSelected()) {
            resultList.add(arr3);
            resultList.add(arr4);
            resultList.add(arr5);
            return resultList;
        }
        //2:
        else if (ch1.isSelected() && ch2.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr2);
            return resultList;
        } else if (ch1.isSelected() && ch3.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr3);
            return resultList;
        } else if (ch1.isSelected() && ch4.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr4);
            return resultList;
        } else if (ch1.isSelected() && ch5.isSelected()) {
            resultList.add(arr1);
            resultList.add(arr5);
            return resultList;
        } else if (ch2.isSelected() && ch3.isSelected()) {
            resultList.add(arr2);
            resultList.add(arr3);
            return resultList;
        } else if (ch2.isSelected() && ch4.isSelected()) {
            resultList.add(arr2);
            resultList.add(arr4);
            return resultList;
        } else if (ch2.isSelected() && ch5.isSelected()) {
            resultList.add(arr2);
            resultList.add(arr5);
            return resultList;
        } else if (ch3.isSelected() && ch4.isSelected()) {
            resultList.add(arr3);
            resultList.add(arr4);
            return resultList;
        } else if (ch3.isSelected() && ch5.isSelected()) {
            resultList.add(arr3);
            resultList.add(arr5);
            return resultList;
        } else if (ch4.isSelected() && ch5.isSelected()) {
            resultList.add(arr4);
            resultList.add(arr5);
            return resultList;
        } else {
            JOptionPane.showMessageDialog(null, "Виберіть декілька чекбоксів", "Error", JOptionPane.ERROR_MESSAGE);
            return resultList;
        }
    }

    //3:
    static double fTest(List arr1, List arr2) {
        //1:
        double sa1 = 0;
        for (int i = 0; i < arr1.size() - 1; i++) {
            sa1 = sa1 + (double) arr1.get(i);
        }

        double resultSA1 = sa1 / arr1.size();
        resultSA1 = BigDecimal.valueOf(resultSA1).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        double dus1 = 0;
        for (int i = 0; i < arr1.size(); i++) {
            dus1 += Math.pow(((double) arr1.get(i) - resultSA1), 2) / ((arr1.size() - 1));
        }
        dus1 = BigDecimal.valueOf(dus1).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();

        //2:
        double sa2 = 0;
        for (int i = 0; i < arr2.size() - 1; i++) {
            sa2 = sa2 + (double) arr2.get(i);
        }

        double resultSA2 = sa2 / arr2.size();
        resultSA2 = BigDecimal.valueOf(resultSA2).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        double dus2 = 0;
        for (int i = 0; i < arr2.size(); i++) {
            dus2 += Math.pow(((double) arr2.get(i) - resultSA2), 2) / ((arr2.size() - 1));
        }
        dus2 = BigDecimal.valueOf(dus2).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();

        double f = 0;
        if (dus1 < dus2) {
            f = dus2 / dus1;
        } else {
            f = dus1 / dus2;
        }
        f = BigDecimal.valueOf(f).setScale(3, BigDecimal.ROUND_CEILING).doubleValue();
        return f;
    }

    static double tTestForDepends(List<Double> arr1, List<Double> arr2) {
        ArrayList<Double> newList = new ArrayList();
        for (int i = 0; i < arr1.size(); i++) {
            newList.add(arr1.get(i) - arr2.get(i));
        }
        double k = 0;
        for (int i = 0; i < newList.size(); i++) {
            k += newList.get(i);
        }
        double z = k / newList.size();

        double temp = 0;
        for (int i = 0; i < newList.size(); i++) {
            temp += Math.pow((newList.get(i) - z), 2);
        }
        double S2 = temp / (newList.size() - 1);
        double t = z * Math.sqrt(newList.size()) / Math.sqrt(S2);
        return BigDecimal.valueOf(Math.abs(t)).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
    }

    static double tTestForIndependence(List<Double> arr1, List<Double> arr2) {
        //1:
        double sa1 = 0;
        for (int i = 0; i < arr1.size() - 1; i++) {
            sa1 = sa1 + (double) arr1.get(i);
        }

        double resultSA1 = sa1 / arr1.size();
        resultSA1 = BigDecimal.valueOf(resultSA1).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        double dus1 = 0;
        for (int i = 0; i < arr1.size(); i++) {
            dus1 += Math.pow((arr1.get(i) - resultSA1), 2) / ((arr1.size() - 1));
        }
        dus1 = BigDecimal.valueOf(dus1).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();

        //2:
        double sa2 = 0;
        for (int i = 0; i < arr2.size() - 1; i++) {
            sa2 = sa2 + arr2.get(i);
        }

        double resultSA2 = sa2 / arr2.size();
        resultSA2 = BigDecimal.valueOf(resultSA2).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        double dus2 = 0;
        for (int i = 0; i < arr2.size(); i++) {
            dus2 += Math.pow(((double) arr2.get(i) - resultSA2), 2) / ((arr2.size() - 1));
        }
        dus2 = BigDecimal.valueOf(dus2).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        double z = resultSA1 - resultSA2;
        double Sz = (dus1 / arr1.size()) + (dus2 / arr2.size());
        double t = z / Sz;
        return BigDecimal.valueOf(Math.abs(t)).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
    }

    //4:
    //need update this method if value repeats!!!!
    static ArrayList<ArrayList> rangRow(ArrayList<Double> arr1, ArrayList<Double> arr2) {
        ArrayList<Double> listX = new ArrayList();
        ArrayList<Double> listY = new ArrayList();
        ArrayList<Double> generalList = new ArrayList();
        ArrayList<String> resultXorY = new ArrayList<>();
        ArrayList<Double> resultValue = new ArrayList<>();
        ArrayList<Double> resultRang = new ArrayList<>();
        ArrayList<ArrayList> resultList = new ArrayList<>();
        int countOfRepeat = 0;
        for (double el : arr1) {
            listX.add(el);
            generalList.add(el);
        }
        for (double el : arr2) {
            listY.add(el);
            generalList.add(el);
        }
        listX.sort(Comparator.naturalOrder());
        listY.sort(Comparator.naturalOrder());
        generalList.sort(Comparator.naturalOrder());
        int size = generalList.size();
        int countForX = 1;
        int countForY = 1;
        double element;
        for (int i = 0; i < generalList.size(); i++) {
            element = generalList.get(i);
            if (listX.contains(element)) {
                resultXorY.add("x" + countForX);
                resultValue.add(element);
                resultRang.add((double) (i + 1));
                countForX++;
            } else {
                resultXorY.add("y" + countForY);
                resultValue.add(element);
                resultRang.add((double) (i + 1));
                countForY++;
            }
        }
        resultList.add(resultXorY);
        resultList.add(resultValue);
        resultList.add(resultRang);
        return resultList;
    }

    static void showRangRowList(TableView tableView, ArrayList arr1, ArrayList arr2) {
        tableView.getItems().clear();
        tableView.getColumns().clear();
        ArrayList generalList = rangRow(arr1, arr2);
        ArrayList<String> resultXorY = (ArrayList<String>) generalList.get(0);
        ArrayList<Double> resultValue = (ArrayList<Double>) generalList.get(1);
        ArrayList<Double> resultRang = (ArrayList<Double>) generalList.get(2);

        final ObservableList<RangRowForData> data = FXCollections.observableArrayList();
        for (int i = 0; i < resultXorY.size(); i++) {
            data.add(new RangRowForData(resultXorY.get(i), resultValue.get(i), resultRang.get(i)));
        }
        //Creating columns
        TableColumn columnForXOrY = new TableColumn("Загальний варіаційний ряд");
        columnForXOrY.setCellValueFactory(new PropertyValueFactory<>("gerenalVarRow"));
        TableColumn columnForValue = new TableColumn("Значення");
        columnForValue.setCellValueFactory(new PropertyValueFactory("value"));
        TableColumn columnForRang = new TableColumn("Ранг");
        columnForRang.setCellValueFactory(new PropertyValueFactory("rang"));

        //Adding data to the table
        tableView.setItems(data);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.getColumns().addAll(columnForXOrY, columnForValue, columnForRang);
    }

    static ArrayList<Double> vilksona(ArrayList<ArrayList> resList) {
        double vilksonX = 0;
        double vilksonY = 0;
        ArrayList<String> resultXorY = (ArrayList<String>) resList.get(0);
        ArrayList<Double> resultRang = (ArrayList<Double>) resList.get(2);
        double n1 = 0;
        double n2 = 0;
        for (int i = 0; i < resultXorY.size(); i++) {
            if (resultXorY.get(i).contains("x")) {
                vilksonX += resultRang.get(i);
                n1++;
            } else if (resultXorY.get(i).contains("y")) {
                vilksonY += resultRang.get(i);
                n2++;
            }
        }

        double n = n1 + n2;//????
        double EVilk1 = n1 * (n + 1) / 2;
        double EVilk2 = n2 * (n + 1) / 2;
        double DW = (n1 * n2) * (n + 1) / 12;
        double w1 = (vilksonX - EVilk1) / Math.sqrt(DW);
        double w2 = (vilksonY - EVilk2) / Math.sqrt(DW);
        ArrayList<Double> vilksonResult = new ArrayList();
        vilksonResult.add(w1);
        vilksonResult.add(w2);
        return vilksonResult;
        //2.8
    }

    static double riznSerednihRangiv(ArrayList<ArrayList> resList) {
        double sumX = 0;
        double sumY = 0;
        double n1 = 0;
        double n2 = 0;
        ArrayList<String> resultXorY = (ArrayList<String>) resList.get(0);
        ArrayList<Double> resultRang = (ArrayList<Double>) resList.get(2);
        for (int i = 0; i < resultXorY.size(); i++) {
            if (resultXorY.get(i).contains("x")) {
                sumX += resultRang.get(i);
                n1++;
            } else if (resultXorY.get(i).contains("y")) {
                sumY += resultRang.get(i);
                n2++;
            }
        }
        double n = n1 + n2;
        double v = (sumX / n1) - (sumY / n2) / (n * Math.sqrt((n + 1) / (12 * n1 * n2)));
        return v;
    }

    static double kolmogorovaSmirnova(ArrayList arr1, ArrayList arr2) {
        int size1 = arr1.size();
        int size2 = arr2.size();
        double z = Math.abs(size1 - size2);
        int Nmin = 0;
        if (size1 >= size2) {
            Nmin = size2;
        } else {
            Nmin = size1;
        }
        double p = 1 - Math.pow(Math.E, -2 * Nmin * Nmin) * (1 - 2 * z / (3 * Math.sqrt(Nmin)) + (2 * Math.pow(z, 2) / (3 * Nmin)) * (1 - 2 * Math.pow(z, 2) / 3) +
                4 * z / (9 * Math.sqrt(Math.pow(Nmin, 3))) * (0.2 - (19 * Math.pow(z, 2) / 15) + 2 * Math.pow(z, 4) / 3));
        return (1 - z);
        //2.7
    }

    static void abbe() {
        //2.10
    }


    //5:
    static double bartleta(List<ArrayList> list) {
        double q = 0;
        double b = 0;
        double c = 0;
        if (list.size() == 5) {
            ArrayList<Double> l0 = list.get(0);
            ArrayList<Double> l1 = list.get(1);
            ArrayList<Double> l2 = list.get(2);
            ArrayList<Double> l3 = list.get(3);
            ArrayList<Double> l4 = list.get(4);

            double temp0 = 0;
            for (int i = 0; i < l0.size(); i++) {
                temp0 += l0.get(i);
            }
            double x0 = temp0 / l0.size();

            double temp1 = 0;
            for (int i = 0; i < l1.size(); i++) {
                temp1 += l1.get(i);
            }
            double x1 = temp1 / l1.size();

            double temp2 = 0;
            for (int i = 0; i < l2.size(); i++) {
                temp2 += l2.get(i);
            }
            double x2 = temp2 / l2.size();

            double temp3 = 0;
            for (int i = 0; i < l3.size(); i++) {
                temp3 += l3.get(i);
            }
            double x3 = temp3 / l3.size();

            double temp4 = 0;
            for (int i = 0; i < l4.size(); i++) {
                temp4 += l4.get(i);
            }
            double x4 = temp4 / l4.size();

            double tempS0 = 0;
            for (int i = 0; i < l0.size(); i++) {
                tempS0 += Math.pow(l0.get(i) - x0, 2);
            }
            double S2i0 = tempS0 / (l0.size() - 1);

            double tempS1 = 0;
            for (int i = 0; i < l1.size(); i++) {
                tempS1 += Math.pow(l1.get(i) - x1, 2);
            }
            double S2i1 = tempS1 / (l1.size() - 1);

            double tempS2 = 0;
            for (int i = 0; i < l2.size(); i++) {
                tempS2 += Math.pow(l2.get(i) - x2, 2);
            }
            double S2i2 = tempS2 / (l2.size() - 1);

            double tempS3 = 0;
            for (int i = 0; i < l3.size(); i++) {
                tempS3 += Math.pow(l3.get(i) - x3, 2);
            }
            double S2i3 = tempS3 / (l3.size() - 1);

            double tempS4 = 0;
            for (int i = 0; i < l4.size(); i++) {
                tempS4 += Math.pow(l4.get(i) - x4, 2);
            }
            double S2i4 = tempS4 / (l4.size() - 1);

            double S2 = 0;
            S2 += (l0.size() - 1) * S2i0 / (l0.size() - 1);
            S2 += (l1.size() - 1) * S2i1 / (l1.size() - 1);
            S2 += (l2.size() - 1) * S2i2 / (l2.size() - 1);
            S2 += (l3.size() - 1) * S2i3 / (l3.size() - 1);
            S2 += (l4.size() - 1) * S2i4 / (l4.size() - 1);

            b = (l0.size() - 1) * Math.log(S2i0 / S2);
            b += (l1.size() - 1) * Math.log(S2i1 / S2);
            b += (l2.size() - 1) * Math.log(S2i2 / S2);
            b += (l3.size() - 1) * Math.log(S2i3 / S2);
            b += (l4.size() - 1) * Math.log(S2i4 / S2);
            b *= -1;

            double tempC = 0;
            tempC += (1 / (l0.size() - 1)) - (1 / (l0.size() + l1.size() + l2.size() + l3.size() + l4.size()));
            tempC += (1 / (l1.size() - 1)) - (1 / (l0.size() + l1.size() + l2.size() + l3.size() + l4.size()));
            tempC += (1 / (l2.size() - 1)) - (1 / (l0.size() + l1.size() + l2.size() + l3.size() + l4.size()));
            tempC += (1 / (l3.size() - 1)) - (1 / (l0.size() + l1.size() + l2.size() + l3.size() + l4.size()));
            tempC += (1 / (l4.size() - 1)) - (1 / (l0.size() + l1.size() + l2.size() + l3.size() + l4.size()));
            c = 1 + (1 / (3 * (list.size() - 1))) * tempC;
        } else if (list.size() == 4) {
            ArrayList<Double> l0 = list.get(0);
            ArrayList<Double> l1 = list.get(1);
            ArrayList<Double> l2 = list.get(2);
            ArrayList<Double> l3 = list.get(3);

            double temp0 = 0;
            for (int i = 0; i < l0.size(); i++) {
                temp0 += l0.get(i);
            }
            double x0 = temp0 / l0.size();

            double temp1 = 0;
            for (int i = 0; i < l1.size(); i++) {
                temp1 += l1.get(i);
            }
            double x1 = temp1 / l1.size();

            double temp2 = 0;
            for (int i = 0; i < l2.size(); i++) {
                temp2 += l2.get(i);
            }
            double x2 = temp2 / l2.size();

            double temp3 = 0;
            for (int i = 0; i < l3.size(); i++) {
                temp3 += l3.get(i);
            }
            double x3 = temp3 / l3.size();

            double tempS0 = 0;
            for (int i = 0; i < l0.size(); i++) {
                tempS0 += Math.pow(l0.get(i) - x0, 2);
            }
            double S2i0 = tempS0 / (l0.size() - 1);

            double tempS1 = 0;
            for (int i = 0; i < l1.size(); i++) {
                tempS1 += Math.pow(l1.get(i) - x1, 2);
            }
            double S2i1 = tempS1 / (l1.size() - 1);

            double tempS2 = 0;
            for (int i = 0; i < l2.size(); i++) {
                tempS2 += Math.pow(l2.get(i) - x2, 2);
            }
            double S2i2 = tempS2 / (l2.size() - 1);

            double tempS3 = 0;
            for (int i = 0; i < l3.size(); i++) {
                tempS3 += Math.pow(l3.get(i) - x3, 2);
            }
            double S2i3 = tempS3 / (l3.size() - 1);

            double S2 = 0;
            S2 += (l0.size() - 1) * S2i0 / (l0.size() - 1);
            S2 += (l1.size() - 1) * S2i1 / (l1.size() - 1);
            S2 += (l2.size() - 1) * S2i2 / (l2.size() - 1);
            S2 += (l3.size() - 1) * S2i3 / (l3.size() - 1);

            b = (l0.size() - 1) * Math.log(S2i0 / S2);
            b += (l1.size() - 1) * Math.log(S2i1 / S2);
            b += (l2.size() - 1) * Math.log(S2i2 / S2);
            b += (l3.size() - 1) * Math.log(S2i3 / S2);
            b *= -1;

            double tempC = 0;
            tempC += (1 / (l0.size() - 1)) - (1 / (l0.size() + l1.size() + l2.size() + l3.size()));
            tempC += (1 / (l1.size() - 1)) - (1 / (l0.size() + l1.size() + l2.size() + l3.size()));
            tempC += (1 / (l2.size() - 1)) - (1 / (l0.size() + l1.size() + l2.size() + l3.size()));
            tempC += (1 / (l3.size() - 1)) - (1 / (l0.size() + l1.size() + l2.size() + l3.size()));
            c = 1 + (1 / (3 * (list.size() - 1))) * tempC;
        } else if (list.size() == 3) {
            ArrayList<Double> l0 = list.get(0);
            ArrayList<Double> l1 = list.get(1);
            ArrayList<Double> l2 = list.get(2);

            double temp0 = 0;
            for (int i = 0; i < l0.size(); i++) {
                temp0 += l0.get(i);
            }
            double x0 = temp0 / l0.size();

            double temp1 = 0;
            for (int i = 0; i < l1.size(); i++) {
                temp1 += l1.get(i);
            }
            double x1 = temp1 / l1.size();

            double temp2 = 0;
            for (int i = 0; i < l2.size(); i++) {
                temp2 += l2.get(i);
            }
            double x2 = temp2 / l2.size();

            double tempS0 = 0;
            for (int i = 0; i < l0.size(); i++) {
                tempS0 += Math.pow(l0.get(i) - x0, 2);
            }
            double S2i0 = tempS0 / (l0.size() - 1);

            double tempS1 = 0;
            for (int i = 0; i < l1.size(); i++) {
                tempS1 += Math.pow(l1.get(i) - x1, 2);
            }
            double S2i1 = tempS1 / (l1.size() - 1);

            double tempS2 = 0;
            for (int i = 0; i < l2.size(); i++) {
                tempS2 += Math.pow(l2.get(i) - x2, 2);
            }
            double S2i2 = tempS2 / (l2.size() - 1);

            double S2 = 0;
            S2 += (l0.size() - 1) * S2i0 / (l0.size() - 1);
            S2 += (l1.size() - 1) * S2i1 / (l1.size() - 1);
            S2 += (l2.size() - 1) * S2i2 / (l2.size() - 1);

            b = (l0.size() - 1) * Math.log(S2i0 / S2);
            b += (l1.size() - 1) * Math.log(S2i1 / S2);
            b += (l2.size() - 1) * Math.log(S2i2 / S2);
            b *= -1;

            double tempC = 0;
            tempC += (1 / (l0.size() - 1)) - (1 / (l0.size() + l1.size() + l2.size()));
            tempC += (1 / (l1.size() - 1)) - (1 / (l0.size() + l1.size() + l2.size()));
            tempC += (1 / (l2.size() - 1)) - (1 / (l0.size() + l1.size() + l2.size()));
            c = 1 + (1 / (3 * (list.size() - 1))) * tempC;
        } else if (list.size() == 2) {
            ArrayList<Double> l0 = list.get(0);
            ArrayList<Double> l1 = list.get(1);

            double temp0 = 0;
            for (int i = 0; i < l0.size(); i++) {
                temp0 += l0.get(i);
            }
            double x0 = temp0 / l0.size();

            double temp1 = 0;
            for (int i = 0; i < l1.size(); i++) {
                temp1 += l1.get(i);
            }
            double x1 = temp1 / l1.size();

            double tempS0 = 0;
            for (int i = 0; i < l0.size(); i++) {
                tempS0 += Math.pow(l0.get(i) - x0, 2);
            }
            double S2i0 = tempS0 / (l0.size() - 1);

            double tempS1 = 0;
            for (int i = 0; i < l1.size(); i++) {
                tempS1 += Math.pow(l1.get(i) - x1, 2);
            }
            double S2i1 = tempS1 / (l1.size() - 1);

            double S2 = 0;
            S2 += (l0.size() - 1) * S2i0 / (l0.size() - 1);
            S2 += (l1.size() - 1) * S2i1 / (l1.size() - 1);

            b = (l0.size() - 1) * Math.log(S2i0 / S2);
            b += (l1.size() - 1) * Math.log(S2i1 / S2);
            b *= -1;

            double tempC = 0;
            tempC += (1 / (l0.size() - 1)) - (1 / (l0.size() + l1.size()));
            tempC += (1 / (l1.size() - 1)) - (1 / (l0.size() + l1.size()));
            c = 1 + (1 / (3 * (list.size() - 1))) * tempC;
        } else {
            new Exception("кількіть чекбоксів < 2");
        }
        q = b / c;
        return q;
    }

    static String messageFtestMessage(List arr1, List arr2) {
        double f = fTest(arr1, arr2);
        //f-test:
        String message = "Результати проведення F-тесту для перевірки збігу дисперсій:\n";
        if (f <= fFrom200To500) {
            message += "Нульову гіпотезу підтверджено, тому дисперсії двох вибірок збігаються\n "
                    + f + "<=" + fFrom200To500;
        } else {
            message += "Нульову гіпотезу спростовано, тому дисперсії двох вибірок не збігаються\n "
                    + f + ">" + fFrom200To500;
        }
        return message;
    }

//message:
    static String messageTtestForDepends(List arr1, List arr2) {
        String message = "\nРезультати проведення t-тесту для перевірки збігу середніх:\n";
        double t = tTestForDepends(arr1, arr2);
        if (t > t1) {
            message += "Нульову гіпотезу спростовано, тому середні двох вибірок не збігаються\n" +
                    t + ">" + t1;
        } else {
            message += "Нульову гіпотезу підтверджено, тому середні двох вибірок  збігаються\n" +
                    t + "<=" + t1;
        }
        return message;
    }

    static String messageTtestForInDepends(List arr1, List arr2) {
        double t = Helper.tTestForIndependence(arr1, arr2);
        String message = "\nРезультати проведення t-тесту для перевірки збігу середніх:\n";
        ;
        if (t > t1) {
            message += "Нульову гіпотезу спростовано, тому середні двох вибірок не збігаються\n" +
                    t + ">" + t1;
        } else {
            message += "Нульову гіпотезу підтверджено, тому середні двох вибірок  збігаються\n" +
                    t + "<=" + t1;
        }
        return message;
    }

    static String messageForBartlet(CheckBox ch1, CheckBox ch2, CheckBox ch3, CheckBox ch4, CheckBox ch5, ArrayList arr1, ArrayList arr2, ArrayList arr3, ArrayList arr4, ArrayList arr5) {
        List<ArrayList> list = returnSeveralCheckBox(ch1, ch2, ch3, ch4, ch5, arr1, arr2, arr3, arr4, arr5);
        double bart = bartleta(list);
        double kva = koefForBartlet(list);
        String message = "Критерій Бартлета:\n";
        if (kva > bart) {
            message += "Головну гіпотезу спростовано ";
            //  + "<- " + kva + ">" + bart;
        } else {
            message += "Головну гіпотезу підтверджено ";
            //  + "<- " + kva + "<=" + bart;
        }
        return message;
    }


}
