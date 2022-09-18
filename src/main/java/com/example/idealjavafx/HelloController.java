package com.example.idealjavafx;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.io.BufferedReader;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class HelloController {
    static ArrayList arrayList = new ArrayList();
    //new block:
    static ArrayList arrayListNumber1 = new ArrayList();
    static ArrayList arrayListNumber2 = new ArrayList();
    static ArrayList arrayListNumber3 = new ArrayList();
    static ArrayList arrayListNumber4 = new ArrayList();
    static ArrayList arrayListNumber5 = new ArrayList();
    //
    static Map<Double, Integer> treeMap = new TreeMap();
    static int numberOfClass;
    static double kvantil = 1.96;
    static double alfa = 0.3;

    static double expD = 0.012;
    static double laplD = 0.019;
    static double normD = 0.00439;
    static double ravnD = 0.0103;
    static double veiblD = 0.01708;
    //ініціалізація:
    @FXML
    private TextField stringOfNumberOfClasses;
    @FXML
    private TextField stringOfKvantil;
    @FXML
    private TextField stringOfAlfa;
    @FXML
    private BarChart barChartFunctionOfShilnist;
    @FXML
    private BarChart barChartEmpiritionFunction;
    @FXML
    private AreaChart areaChartFunctionOfShilnist;
    @FXML
    private AreaChart areaChartEmpiritionFunction;
    @FXML
    private TableView tableView;
    @FXML
    private CheckBox depend;
    @FXML
    private CheckBox independ;
    @FXML
    private CheckBox checkBox1;
    @FXML
    private CheckBox checkBox2;
    @FXML
    private CheckBox checkBox3;
    @FXML
    private CheckBox checkBox4;
    @FXML
    private CheckBox checkBox5;

    @FXML
    protected void test() {
        tableView.getItems().clear();
        tableView.getColumns().clear();
    }

    //звичайні графіки:
    @FXML
    protected void barChartSimpleGraph(ActionEvent event) {
        cleanBarCharts();
        Helper.drawBarChartForEmpirationFunction(barChartEmpiritionFunction, numberOfClass, arrayList, (TreeMap) Helper.returnMap(arrayList));
        Helper.drawBarChartForShilnist(barChartFunctionOfShilnist, numberOfClass, arrayList, (TreeMap) Helper.returnMap(arrayList));
    }

    @FXML
    protected void barChartAnomalGraph(ActionEvent event) {
        ArrayList arr1 = (ArrayList) Helper.returnDeleteAnomalData(arrayList);
        TreeMap tree1 = (TreeMap) Helper.returnMap(arr1);
        cleanBarCharts();
        Helper.drawBarChartForEmpirationFunction(barChartEmpiritionFunction, numberOfClass, arr1, tree1);
        Helper.drawBarChartForShilnist(barChartFunctionOfShilnist, numberOfClass, arr1, tree1);
    }

    @FXML
    protected void barChartStandartizationGraph(ActionEvent event) {
        ArrayList arr1 = Helper.standartization(arrayList);
        TreeMap tree1 = (TreeMap) Helper.returnMap(arr1);
        cleanBarCharts();
        Helper.drawBarChartForEmpirationFunction(barChartEmpiritionFunction, numberOfClass, arr1, tree1);
        Helper.drawBarChartForShilnist(barChartFunctionOfShilnist, numberOfClass, arr1, tree1);
    }

    @FXML
    protected void barChartAnomalPlusZsuvAndLogarifmGraph(ActionEvent event) {
        ArrayList arr1 = (ArrayList) Helper.returnDeleteAnomalData(arrayList);
        ArrayList arr2 = Helper.zsuvAndLogarifm(arr1);
        TreeMap tree2 = (TreeMap) Helper.returnMap(arr2);
        cleanBarCharts();
        Helper.drawBarChartForEmpirationFunction(barChartEmpiritionFunction, numberOfClass, arr2, tree2);
        Helper.drawBarChartForShilnist(barChartFunctionOfShilnist, numberOfClass, arr2, tree2);
    }

    @FXML
    protected void barChartAnomalPlusStandartizationGraph(ActionEvent event) {
        ArrayList arr1 = (ArrayList) Helper.returnDeleteAnomalData(arrayList);
        ArrayList arr2 = Helper.standartization(arr1);
        TreeMap tree2 = (TreeMap) Helper.returnMap(arr2);
        cleanBarCharts();
        Helper.drawBarChartForEmpirationFunction(barChartEmpiritionFunction, numberOfClass, arr2, tree2);
        Helper.drawBarChartForShilnist(barChartFunctionOfShilnist, numberOfClass, arr2, tree2);
    }

    @FXML
    protected void barChartZsuvAndLogarifmGraph(ActionEvent event) {
        ArrayList arr1 = Helper.zsuvAndLogarifm(arrayList);
        TreeMap tree1 = (TreeMap) Helper.returnMap(arr1);
        cleanBarCharts();
        Helper.drawBarChartForEmpirationFunction(barChartEmpiritionFunction, numberOfClass, arr1, tree1);
        Helper.drawBarChartForShilnist(barChartFunctionOfShilnist, numberOfClass, arr1, tree1);
    }

    @FXML
    protected void cleanBarCharts() {
        barChartFunctionOfShilnist.getData().clear();
        barChartFunctionOfShilnist.layout();

        barChartEmpiritionFunction.getData().clear();
        barChartEmpiritionFunction.layout();
    }

    //складні графіки:
    @FXML
    protected void areaChartEksponetial(ActionEvent event) {
        cleanAreaChart();
        Helper.drawAreaChartForShilnist(areaChartFunctionOfShilnist, numberOfClass, arrayList, kvantil, expD);
        Helper.drawAreaChartForEmpirationFunction(areaChartEmpiritionFunction, numberOfClass, arrayList, kvantil, expD);
    }

    @FXML
    protected void areaChartLaplasa(ActionEvent event) {
        cleanAreaChart();
        Helper.drawAreaChartForShilnist(areaChartFunctionOfShilnist, numberOfClass, arrayList, kvantil, laplD);
        Helper.drawAreaChartForEmpirationFunction(areaChartEmpiritionFunction, numberOfClass, arrayList, kvantil, laplD);
    }

    @FXML
    protected void areaChartNorm(ActionEvent event) {
        cleanAreaChart();
        Helper.drawAreaChartForShilnist(areaChartFunctionOfShilnist, numberOfClass, arrayList, kvantil, normD);
        Helper.drawAreaChartForEmpirationFunction(areaChartEmpiritionFunction, numberOfClass, arrayList, kvantil, normD);
    }

    @FXML
    protected void areaChartRivnomirniy(ActionEvent event) {
        cleanAreaChart();
        Helper.drawAreaChartForShilnist(areaChartFunctionOfShilnist, numberOfClass, arrayList, kvantil, ravnD);
        Helper.drawAreaChartForEmpirationFunction(areaChartEmpiritionFunction, numberOfClass, arrayList, kvantil, ravnD);
    }

    @FXML
    protected void areaChartVeibula(ActionEvent event) {
        cleanAreaChart();
        Helper.drawAreaChartForShilnist(areaChartFunctionOfShilnist, numberOfClass, arrayList, kvantil, veiblD);
        Helper.drawAreaChartForEmpirationFunction(areaChartEmpiritionFunction, numberOfClass, arrayList, kvantil, veiblD);
    }

    @FXML
    protected void cleanAreaChart() {
        areaChartFunctionOfShilnist.getData().clear();
        areaChartFunctionOfShilnist.layout();

        areaChartEmpiritionFunction.getData().clear();
        areaChartEmpiritionFunction.layout();
    }


    //файл:
    @FXML
    protected void chooseFileForNumber1(ActionEvent event) {
        arrayListNumber1.clear();
        JFileChooser fileopen = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int ret = fileopen.showDialog(null, "Виберіть текстовий файл");
        File file = fileopen.getSelectedFile();
        String s = file.getPath();
        List<String> listString = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Path.of(s))) {
            listString = br.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String stringValue : listString) {
            try {
                arrayListNumber1.add(Double.parseDouble(stringValue));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        arrayListNumber1.sort(Comparator.naturalOrder());
    }

    @FXML
    protected void chooseFileForNumber2(ActionEvent event) {
        arrayListNumber2.clear();
        JFileChooser fileopen = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int ret = fileopen.showDialog(null, "Виберіть текстовий файл");
        File file = fileopen.getSelectedFile();
        String s = file.getPath();
        List<String> listString = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Path.of(s))) {
            listString = br.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String stringValue : listString) {
            try {
                arrayListNumber2.add(Double.parseDouble(stringValue));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        arrayListNumber2.sort(Comparator.naturalOrder());
    }

    @FXML
    protected void chooseFileForNumber3(ActionEvent event) {
        arrayListNumber3.clear();
        JFileChooser fileopen = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int ret = fileopen.showDialog(null, "Виберіть текстовий файл");
        File file = fileopen.getSelectedFile();
        String s = file.getPath();
        List<String> listString = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Path.of(s))) {
            listString = br.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String stringValue : listString) {
            try {
                arrayListNumber3.add(Double.parseDouble(stringValue));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        arrayListNumber3.sort(Comparator.naturalOrder());
    }

    @FXML
    protected void chooseFileForNumber4(ActionEvent event) {
        arrayListNumber4.clear();
        JFileChooser fileopen = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int ret = fileopen.showDialog(null, "Виберіть текстовий файл");
        File file = fileopen.getSelectedFile();
        String s = file.getPath();
        List<String> listString = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Path.of(s))) {
            listString = br.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String stringValue : listString) {
            try {
                arrayListNumber4.add(Double.parseDouble(stringValue));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        arrayListNumber4.sort(Comparator.naturalOrder());
    }

    @FXML
    protected void chooseFileForNumber5(ActionEvent event) {
        arrayListNumber5.clear();
        JFileChooser fileopen = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int ret = fileopen.showDialog(null, "Виберіть текстовий файл");
        File file = fileopen.getSelectedFile();
        String s = file.getPath();
        List<String> listString = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Path.of(s))) {
            listString = br.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String stringValue : listString) {
            try {
                arrayListNumber5.add(Double.parseDouble(stringValue));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        arrayListNumber5.sort(Comparator.naturalOrder());
    }

    @FXML
    protected void saveOfMainData(ActionEvent event) {
        try {
            numberOfClass = Integer.parseInt(stringOfNumberOfClasses.getText());
            if (!stringOfKvantil.getText().isEmpty()) {
                kvantil = Double.parseDouble(stringOfKvantil.getText());
            }
            if (!stringOfAlfa.getText().isEmpty()) {
                alfa = Double.parseDouble(stringOfAlfa.getText());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Введіть коректно дані", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    //дані:
    @FXML
    protected void showVarRowList() {
        tableView.getItems().clear();
        tableView.getColumns().clear();
        HashMap hashMap = Helper.varRow();
        ArrayList arr1 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            arr1.add(arrayList.get(i));
        }
        int size = arr1.size();
        final ObservableList<VariationRowForData> data = FXCollections.observableArrayList();
        for (int i = 0; i < arr1.size(); i++) {
            data.add(new VariationRowForData((double) arr1.get(i), (double) hashMap.get(arr1.get(i))));
        }
        //Creating columns
        TableColumn columnForNumber = new TableColumn("Число");
        columnForNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        TableColumn columnForFrequency = new TableColumn("Частота");
        columnForFrequency.setCellValueFactory(new PropertyValueFactory("frequency"));


        //Adding data to the table
        ObservableList<String> list = FXCollections.observableArrayList();
        tableView.setItems(data);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.getColumns().addAll(columnForNumber, columnForFrequency);

    }

    @FXML
    protected void shownMainCharacteristic(ActionEvent event) {
        tableView.getItems().clear();
        tableView.getColumns().clear();
        ArrayList arrayList1 = arrayList;
        int size = arrayList1.size();
        List<Double> list = Helper.data();
        double kvantil1 = kvantil;

        double q1 = kvantil1 * (double) list.get(3) / Math.sqrt(size);
        q1 = BigDecimal.valueOf(q1).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        double q2 = kvantil1 * ((double) list.get(2) / Math.sqrt(2 * size));
        q2 = BigDecimal.valueOf(q2).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        double q3 = kvantil1 * (double) list.get(3) / Math.sqrt(2 * size);
        q3 = BigDecimal.valueOf(q3).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        double q5 = kvantil1 * Math.sqrt((double) 6 * (size - 2) / ((size + 1) * (size + 3)));
        q5 = BigDecimal.valueOf(q5).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        double q7 = kvantil1 * (24.0 / size) * (1 - (225.0 / (15 * size + 124)));
        q7 = BigDecimal.valueOf(q7).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        double q9 = kvantil1 * Math.sqrt((double) list.get(8) / (29 * size)) * Math.pow(Math.pow(Math.abs((1 + Math.pow((double) list.get(8), 2))), 3), 0.25);
        q9 = BigDecimal.valueOf(q9).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();
        double q10 = kvantil1 * (double) list.get(10) * (Math.sqrt((1 + 2 * Math.pow((double) list.get(10), 2)) / (2 * size)));
        q10 = BigDecimal.valueOf(q10).setScale(4, BigDecimal.ROUND_CEILING).doubleValue();

        final ObservableList<MainCharactericticForData> data = FXCollections.observableArrayList(
                new MainCharactericticForData("Середнє арифметичне", list.get(0) - q1, list.get(0), list.get(0) + q1, BigDecimal.valueOf(list.get(3) / Math.sqrt(size)).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()),
                new MainCharactericticForData("Дисперсія незсунена", list.get(1) - q2, (double) list.get(1), list.get(1) + q2, BigDecimal.valueOf((list.get(2) / Math.sqrt(2 * size))).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()),
                new MainCharactericticForData("Дисперсія зсунена", 0, list.get(2), 0, 0),
                new MainCharactericticForData("Середнє квадратичне", BigDecimal.valueOf(list.get(3) - q3).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(), list.get(3), list.get(3) + q3, BigDecimal.valueOf(list.get(3) / Math.sqrt(2 * size)).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()),
                new MainCharactericticForData("Усічене  середнє", 0, list.get(4), 0, 0),
                new MainCharactericticForData("Асиметрія незсунена", list.get(5) - q5, list.get(5), list.get(5) + q5, BigDecimal.valueOf(Math.sqrt((double) 6 * (size - 2) / ((size + 1) * (size + 3)))).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()),
                new MainCharactericticForData("Асиметрія зсунена", 0, list.get(6), 0, 0),
                new MainCharactericticForData("Ексцес незсунений", list.get(7) - q7, list.get(7), list.get(7) + q7, BigDecimal.valueOf((24.0 / size) * (1 - (225.0 / (15 * size + 124)))).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()),
                new MainCharactericticForData("Ексцес зсунений", 0, list.get(8), 0, 0),
                new MainCharactericticForData("Контрексцес", list.get(9) - q9, list.get(9), BigDecimal.valueOf(list.get(9) + q9).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(), BigDecimal.valueOf(Math.sqrt(list.get(8) / (29 * size)) * Math.pow(Math.pow(Math.abs((1 + Math.pow((double) list.get(8), 2))), 3), 0.25)).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()),
                new MainCharactericticForData("Пірсона", (double) list.get(10) - q10, list.get(10), BigDecimal.valueOf((double) list.get(10) + q10).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(), BigDecimal.valueOf((double) list.get(10) * (Math.sqrt((1 + 2 * Math.pow((double) list.get(10), 2)) / (2 * size)))).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()),
                new MainCharactericticForData("MED", 0, list.get(11), 0, 0),
                new MainCharactericticForData("MAD", 0, list.get(12), 0, 0),
                new MainCharactericticForData("Непараметричний коефіцієнт варіації", 0, list.get(13), 0, 0),
                new MainCharactericticForData("MED Уолша", 0, list.get(14), 0, 0)
        );

        TableColumn columnCharacteristic = new TableColumn("Характеристика");
        columnCharacteristic.setCellValueFactory(new PropertyValueFactory<>("characteristic"));
        TableColumn columnINF = new TableColumn("INF");
        columnINF.setCellValueFactory(new PropertyValueFactory("inf"));
        TableColumn columnForValue = new TableColumn("Значення");
        columnForValue.setCellValueFactory(new PropertyValueFactory("values"));
        TableColumn columnSUP = new TableColumn("SUP");
        columnSUP.setCellValueFactory(new PropertyValueFactory("sup"));
        TableColumn columnForAverage = new TableColumn("Сер.квадр");
        columnForAverage.setCellValueFactory(new PropertyValueFactory("average"));

        tableView.setItems(data);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.getColumns().addAll(columnCharacteristic, columnINF, columnForValue, columnSUP, columnForAverage);
    }

    @FXML
    protected void kriteriiOdnoridnosti(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "Виберіть два чекбокси", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String message = Helper.returnFtestMessage(arr1, arr2);
                if (depend.isSelected()) {
                    if (arr1.size() != arr2.size()) {
                        JOptionPane.showMessageDialog(null, "Помилка! Різні розміри файлів", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        message += Helper.returnTtestForDepends(arr1, arr2);
                        JOptionPane.showMessageDialog(null, message, "Критерії однорідності", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (independ.isSelected()) {
                    message += Helper.returnTtestForInDepends(arr1, arr2);
                    JOptionPane.showMessageDialog(null, message, "Критерії однорідності", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Оберіть залежна чи незалежна вибірка", "Warn", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

    }

    //Про програму:
    @FXML
    protected void aboutMenu(ActionEvent event) {
        String message = "Program created by Oleksandr Pyvovar";
        JOptionPane.showMessageDialog(null, message, "About", JOptionPane.INFORMATION_MESSAGE);
    }

    //файл
    @FXML
    protected void changeMenuNumber1(ActionEvent event) {
        arrayList.clear();
        for (int i = 0; i < arrayListNumber1.size(); i++) {
            arrayList.add(arrayListNumber1.get(i));
        }
        if (!arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ви успішно обрали файл №1. Розмір файлу:" + arrayListNumber1.size(), "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "About", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    protected void changeMenuNumber2(ActionEvent event) {
        arrayList.clear();
        for (int i = 0; i < arrayListNumber2.size(); i++) {
            arrayList.add(arrayListNumber2.get(i));
        }
        if (!arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ви успішно обрали файл №2. Розмір файлу:" + arrayListNumber2.size(), "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "About", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    protected void changeMenuNumber3(ActionEvent event) {
        arrayList.clear();
        for (int i = 0; i < arrayListNumber3.size(); i++) {
            arrayList.add(arrayListNumber3.get(i));
        }
        if (!arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ви успішно обрали файл №3.Розмір файлу:" + arrayListNumber3.size(), "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "About", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    protected void changeMenuNumber4(ActionEvent event) {
        arrayList.clear();
        for (int i = 0; i < arrayListNumber4.size(); i++) {
            arrayList.add(arrayListNumber4.get(i));
        }
        if (!arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ви успішно обрали файл №4.Розмір файлу:" + arrayListNumber4.size(), "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "About", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    protected void changeMenuNumber5(ActionEvent event) {
        arrayList.clear();
        for (int i = 0; i < arrayListNumber5.size(); i++) {
            arrayList.add(arrayListNumber5.get(i));
        }
        if (!arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ви успішно обрали файл №5.Розмір файлу:" + arrayListNumber5.size(), "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "About", JOptionPane.ERROR_MESSAGE);
        }
    }


}