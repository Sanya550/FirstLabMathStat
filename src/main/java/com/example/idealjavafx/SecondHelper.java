package com.example.idealjavafx;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.swing.*;
import java.util.*;

import static com.example.idealjavafx.HelloController.*;

//Helper from 5 lab
public class SecondHelper {

    public void showInitialTableHelper(CheckBox ch1, CheckBox ch2, CheckBox ch3, CheckBox ch4, CheckBox ch5, CheckBox ch6, TableView tableView) {
        var list = new ArrayList<ArrayList<Double>>();
        if (ch1.isSelected()) {
            list.add(withoutSortingArrayListNumber1);
        }
        if (ch2.isSelected()) {
            list.add(withoutSortingArrayListNumber2);
        }
        if (ch3.isSelected()) {
            list.add(withoutSortingArrayListNumber3);
        }
        if (ch4.isSelected()) {
            list.add(withoutSortingArrayListNumber4);
        }
        if (ch5.isSelected()) {
            list.add(withoutSortingArrayListNumber5);
        }
        if (ch6.isSelected()) {
            list.add(withoutSortingArrayListNumber6);
        }


        int errorCounter = (int) list.stream().filter(ArrayList::isEmpty).count();
        if (errorCounter > 0) {
            JOptionPane.showMessageDialog(null, "Одна чи більше вибірок не існує у файлі", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
        list.stream().filter(t -> !t.isEmpty());
        ArrayList<ArrayList<Double>> data = new ArrayList<>();
        if (!list.isEmpty()) {
            for (int i = 0; i < list.get(0).size(); i++) {
                if (list.size() == 1) {
                    ArrayList<Double> row = new ArrayList<>(Arrays.asList(list.get(0).get(i)));
                    data.add(row);
                } else if (list.size() == 2) {
                    ArrayList<Double> row = new ArrayList<>(Arrays.asList(list.get(0).get(i), list.get(1).get(i)));
                    data.add(row);
                } else if (list.size() == 3) {
                    ArrayList<Double> row = new ArrayList<>(Arrays.asList(list.get(0).get(i), list.get(1).get(i), list.get(2).get(i)));
                    data.add(row);
                } else if (list.size() == 4) {
                    ArrayList<Double> row = new ArrayList<>(Arrays.asList(list.get(0).get(i), list.get(1).get(i), list.get(2).get(i), list.get(3).get(i)));
                    data.add(row);
                } else if (list.size() == 5) {
                    ArrayList<Double> row = new ArrayList<>(Arrays.asList(list.get(0).get(i), list.get(1).get(i), list.get(2).get(i), list.get(3).get(i), list.get(4).get(i)));
                    data.add(row);
                } else if (list.size() == 6) {
                    ArrayList<Double> row = new ArrayList(Arrays.asList(list.get(0).get(i), list.get(1).get(i), list.get(2).get(i), list.get(3).get(i), list.get(4).get(i), list.get(5)));
                    data.add(row);
                }
            }
        }

        for (int i = 0; i < data.get(0).size(); i++) {
            final int colIndex = i;
            TableColumn<List<Double>, Double> column = new TableColumn<>("Column " + (i + 1));
            column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(colIndex)));
            tableView.getColumns().add(column);
        }
        tableView.setItems(FXCollections.observableArrayList(data));
    }

    public List<List<Double>> defineWhichCheckBoxChecked(CheckBox ch1, CheckBox ch2, CheckBox ch3, CheckBox ch4, CheckBox ch5, CheckBox ch6) {
        List<List<Double>> resList = new ArrayList<>();
        if (ch1.isSelected()) {
            resList.add(arrayListNumber1);
        }
        if (ch2.isSelected()) {
            resList.add(arrayListNumber2);
        }
        if (ch3.isSelected()) {
            resList.add(arrayListNumber3);
        }
        if (ch4.isSelected()) {
            resList.add(arrayListNumber4);
        }
        if (ch5.isSelected()) {
            resList.add(arrayListNumber5);
        }
        if (ch6.isSelected()) {
            resList.add(arrayListNumber6);
        }
        return resList;
    }


    public List<List<Double>> defineWhichCheckBoxCheckedForWithoutSorted(CheckBox ch1, CheckBox ch2, CheckBox ch3, CheckBox ch4, CheckBox ch5, CheckBox ch6) {
        List<List<Double>> resList = new ArrayList<>();
        if (ch1.isSelected()) {
            resList.add(withoutSortingArrayListNumber1);
        }
        if (ch2.isSelected()) {
            resList.add(withoutSortingArrayListNumber2);
        }
        if (ch3.isSelected()) {
            resList.add(withoutSortingArrayListNumber3);
        }
        if (ch4.isSelected()) {
            resList.add(withoutSortingArrayListNumber4);
        }
        if (ch5.isSelected()) {
            resList.add(withoutSortingArrayListNumber5);
        }
        if (ch6.isSelected()) {
            resList.add(withoutSortingArrayListNumber6);
        }
        return resList;
    }

    public List<List<Double>> defineWhichCheckBoxCheckedForSaved(CheckBox ch1, CheckBox ch2, CheckBox ch3, CheckBox ch4, CheckBox ch5, CheckBox ch6) {
        List<List<Double>> resList = new ArrayList<>();
        if (ch1.isSelected()) {
            resList.add(savingListNumber1);
        }
        if (ch2.isSelected()) {
            resList.add(savingListNumber2);
        }
        if (ch3.isSelected()) {
            resList.add(savingListNumber3);
        }
        if (ch4.isSelected()) {
            resList.add(savingListNumber4);
        }
        if (ch5.isSelected()) {
            resList.add(savingListNumber5);
        }
        if (ch6.isSelected()) {
            resList.add(savingListNumber6);
        }
        return resList;
    }

    //lab5:
    //Первинний статистичний аналіз
    public String firstStaticAnalizeForManyVibirokHelper(List<List<Double>> list) {
        String resultString = "Знаходження вектора середніх: \nE = {";
        for (int i = 0; i < list.size(); i++) {
            resultString += String.format("%.2f", MainFunction.matSpodivan(list.get(i)));
            if (i != list.size() - 1) {
                resultString += ", ";
            }
        }
        resultString += "}";

        resultString += "\nЗнаходження вектора середньоквадратичних: \nT = {";
        for (int i = 0; i < list.size(); i++) {
            resultString += String.format("%.2f", MainFunction.serKva(list.get(i)));
            if (i != list.size() - 1) {
                resultString += ", ";
            }
        }
        resultString += "}";
        return resultString;
    }


    //Перевірка збігу параметрів
    //todo: needs updates
    public String checkParametersOfSukupnistsHelper(List<List<Double>> list1, List<List<Double>> list2) {
        List<ArrayList<Double>> tempList = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            tempList.add((ArrayList<Double>) list1.get(i));
        }


        double determinationS0 = MainFunction.findDetermination(MainFunction.findDCForDuspKovMatrixForManyVibirok(list1));
        double determinationS1 = MainFunction.findDetermination(MainFunction.findDCForDuspKovMatrixForManyVibirok(list2));
        //todo: //(list1.size() + list2.size()) / 2 = n/2
        // n - количество выборок
        // не сортировать
        //Nd = 500;
        //x_ = 3 мат сподіван
        //Xl - 500 штук
        //xd - матрица из мат спов сукупностей 2 x 3
        String str = "Рівність двох багатовимірних середніх у разі рівних ДК матриць:\n";
        double v1 = -(list1.size() + list2.size() - 2 - (list1.size() + list2.size()) / 2)
                * Math.log(Math.abs(determinationS1 / determinationS0));
        str += String.format("V = %.2f, ", v1);

        if (Helper.koefForBartletAndKohrena(tempList) >= v1) {
            str += "нульову гіпотезу підтверджено.\n";
        } else {
            str += "нульову гіпотезу відхилено.\n";
        }

        str += "Збіг ДК матриць:\n";
        str += "V =   \n";

        str += "Збіг k-вимірних при розбіжності ДК матриць:\n";
        str += "V =   \n";


        return str;
    }

    //відображення матриці в TableView
    public void showMatrixInTableView(TableView tableView, double[][] array) {
        tableView.getItems().clear();
        tableView.getColumns().clear();

        int numColumns = array[1].length;
        for (int i = 0; i < numColumns; i++) {
            TableColumn<ObservableList<String>, String> column = new TableColumn<>("");
            final int columnIndex = i;
            column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(columnIndex)));
            tableView.getColumns().add(column);
        }

        for (int i = 0; i < array.length; i++) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int j = 0; j < array[i].length; j++) {
                row.add(String.format("%.2f", array[i][j]));
            }
            tableView.getItems().add(row);
        }
    }

    public ArrayList<List<Double>> rewriteListsForSukupnist(List<List<Double>> list) {
        var resultList = new ArrayList<List<Double>>();
        for (List list1 : list) {
            resultList.add(new ArrayList<>(list1));
        }
        return resultList;
    }

    public double[][] returnArrayForManyVarRow(List<List<Double>> list) {
        int numberOfClasses = (int) Math.cbrt(list.get(0).size());
        double array[][] = new double[list.size()][numberOfClasses];

        for (int i = 0; i < list.size(); i++) {
            double h = (list.get(i).get(list.get(i).size() - 1) - list.get(i).get(0)) / numberOfClasses;
            double start = list.get(i).get(0);
            double finish = list.get(i).get(0) + h;
            for (int j = 0; j < numberOfClasses; j++) {
                array[i][j] = (start + finish) / 2;
                start = finish;
                finish += h;
            }
        }
        return array;
    }


    //NOTE: it can hurt on other process
    public static void deleteAnomalValue(List<Double> list) {
        var numOfClass = (int) Math.cbrt(list.size());
        int size = list.size();
        double lastElement = list.get(size - 1);
        int frequency = 0;
        double step = (list.get(size - 1) - list.get(0)) / numOfClass;
        double start = list.get(0);
        double end = list.get(0) + step;
        List<Double> rubish = new ArrayList<>();
        while (lastElement > start) {
            for (Double value : list) {
                if (value >= start && value < end) {
                    frequency++;
                    rubish.add(value);
                }
            }

            if ((double)frequency/size <= alfaForAnomalData) {
                list.removeAll(rubish);
            }
            start = end;
            end += step;
            rubish.clear();
            frequency = 0;
        }
    }
}
