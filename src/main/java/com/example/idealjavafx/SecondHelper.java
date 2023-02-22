package com.example.idealjavafx;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.swing.*;
import java.util.*;

import static com.example.idealjavafx.HelloController.*;

//Helper from 5 lab
public class SecondHelper {

    public void showInitialTableHelper(CheckBox ch1, CheckBox ch2, CheckBox ch3, CheckBox ch4, CheckBox ch5, TableView tableView) {
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

    public List<List<Double>> defineWhichCheckBoxChecked(CheckBox ch1, CheckBox ch2, CheckBox ch3, CheckBox ch4, CheckBox ch5) {
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
        return resList;
    }

    //lab5:
    public String firstStaticAnalizeForManyVibirokHelper(List<List<Double>> list) {
        String resultString = "Знаходження вектора середніх: \nE = {";
        for (int i = 0; i < list.size(); i++) {
            resultString += String.format("%.2f",list.get(i).stream().mapToDouble(a -> a).average().orElseThrow());
            if (i != list.size() - 1) {
                resultString += ", ";
            }
        }
        resultString += "}";



        return resultString;
    }
}
