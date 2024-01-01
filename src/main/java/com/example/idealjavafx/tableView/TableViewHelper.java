package com.example.idealjavafx.tableView;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TableViewHelper {
    public static void addMatrixToTableView(TableView<Map<String, Double>> tableView, double[][] matrixData) {

        // Create columns for the TableView
        for (int i = -1; i < matrixData[0].length; i++) {
            TableColumn<Map<String, Double>, Double> column = new TableColumn<>(String.valueOf(i + 1));
            final int columnIndex = i;

            column.setCellValueFactory(cellData ->
                    new ReadOnlyObjectWrapper<>(cellData.getValue().get(String.valueOf(columnIndex + 1)))
            );

            tableView.getColumns().add(column);
        }

        // Populate the data
        ObservableList<Map<String, Double>> data = FXCollections.observableArrayList();
        for (int i = 0; i < matrixData.length; i++) {
            Map<String, Double> rowData = new HashMap<>();

            // Add matrix values to other columns
            for (int j = -1; j < matrixData[i].length; j++) {
                if (j == -1){
                    rowData.put(String.valueOf(j + 1), (double) (i+1));
                }else {
                    rowData.put(String.valueOf(j + 1), BigDecimal.valueOf(matrixData[i][j]).setScale(3, BigDecimal.ROUND_CEILING).doubleValue());
                }
            }
            data.add(rowData);
        }

        tableView.setItems(data);
    }

    public static void addRowToTableView(TableView<Map<String, Object>> tableView, String nameOfNewRow, Object[] array) {
        // Create the first column for keys (String)
        TableColumn<Map<String, Object>, String> keyColumn = new TableColumn<>("0");
        keyColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().get("0").toString()));

        tableView.getColumns().add(keyColumn);

        // Create columns for the TableView
        for (int i = 0; i < array.length; i++) {
            TableColumn<Map<String, Object>, Double> column = new TableColumn<>(String.valueOf(i + 1));
            final int columnIndex = i;

            column.setCellValueFactory(cellData ->
                    new ReadOnlyObjectWrapper<>((Double) cellData.getValue().get(String.valueOf(columnIndex + 1)))
            );

            tableView.getColumns().add(column);
        }

        // Populate the data
        ObservableList<Map<String, Object>> data = FXCollections.observableArrayList();

        // Add new row
        Map<String, Object> rowData = new HashMap<>();
        rowData.put("0", nameOfNewRow);

        // Add array values to other columns
        for (int i = 0; i < array.length; i++) {
            rowData.put(String.valueOf(i + 1), array[i]);
        }
        data.add(rowData);

        tableView.setItems(data);
    }

    public static void clearTableView(TableView tableView){
        tableView.getItems().clear();
        tableView.getColumns().clear();
    }
}
