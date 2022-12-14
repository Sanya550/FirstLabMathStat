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
import java.io.FileWriter;
import java.io.IOException;
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
    static ArrayList arrayListGeneral = new ArrayList();
    static ArrayList listForDvomirnixVibirok = new ArrayList();

    static ArrayList withoutSortingArrayListNumber1 = new ArrayList();
    static ArrayList withoutSortingArrayListNumber2 = new ArrayList();
    static ArrayList withoutSortingArrayListNumber3 = new ArrayList();
    //for saving:
    static ArrayList arrayListNumber1ForSave = new ArrayList();
    static ArrayList arrayListNumber2ForSave = new ArrayList();
    static ArrayList withoutSortingArrayListNumber1ForSave = new ArrayList();
    static ArrayList withoutSortingArrayListNumber2ForSave = new ArrayList();
    //
    static int forFileIndex = 1;
    static int numberOfClass;
    static double kvantil = 1.96;
    static double alfa = 0.3;

    static double expD = 0.012;
    static double laplD = 0.019;
    static double normD = 0.00439;
    static double ravnD = 0.0103;
    static double veiblD = 0.01708;

    static double x0ForDovInterval = 1000;
    static double aForT = 0;
    static double bForT = 0;
    static double cForT = 0;

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
    private ScatterChart<Number, Number> scatterChartForKorilationField;
    @FXML
    private ScatterChart<Number, Number> scatterChartForFrequencyOfHystograma;
    @FXML
    private NumberAxis xAxisForScatterChartForKorilationField;
    @FXML
    private NumberAxis yAxisForScatterChartForKorilationField;
    @FXML
    private NumberAxis xAxisForScatterChartForFrequencyOfHystograma;
    @FXML
    private NumberAxis yAxisForScatterChartForFrequencyOfHystograma;
    @FXML
    private TableView tableView;
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
    private TextField strModelN;
    @FXML
    private TextField strModelM;
    @FXML
    private TextField strModelP;
    @FXML
    private TextField strModelL;
    @FXML
    private TextField aRegressia;
    @FXML
    private TextField bRegressia;
    @FXML
    private TextField cRegressia;
    @FXML
    private TextField minRegressia;
    @FXML
    private TextField maxRegressia;
    @FXML
    private TextField duspersiaRegressia;

    //???????????????? ??????????????:
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

    //?????????????? ??????????????:
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


    //????????:
    @FXML
    protected void chooseFileForNumber1(ActionEvent event) {
        arrayListNumber1.clear();
        JFileChooser fileopen = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int ret = fileopen.showDialog(null, "???????????????? ?????????????????? ????????");
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
        int ret = fileopen.showDialog(null, "???????????????? ?????????????????? ????????");
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
        int ret = fileopen.showDialog(null, "???????????????? ?????????????????? ????????");
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
        int ret = fileopen.showDialog(null, "???????????????? ?????????????????? ????????");
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
        int ret = fileopen.showDialog(null, "???????????????? ?????????????????? ????????");
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
            JOptionPane.showMessageDialog(null, "?????????????? ???????????????? ????????", "??????????????", JOptionPane.ERROR_MESSAGE);
        }
    }

    //????????:
    @FXML
    protected void chooseFileForDvovomirnihVibirok(ActionEvent event) {
        arrayListNumber1.clear();
        arrayListNumber2.clear();
        arrayListNumber3.clear();
        arrayListGeneral.clear();
        withoutSortingArrayListNumber1.clear();
        withoutSortingArrayListNumber2.clear();
        withoutSortingArrayListNumber3.clear();
        arrayList.clear();
        JFileChooser fileopen = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int ret = fileopen.showDialog(null, "???????????????? ?????????????????? ????????");
        File file = fileopen.getSelectedFile();
        String s = file.getPath();
        List<String> listString = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Path.of(s))) {
            listString = br.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int numberOfColumns = 0;
        listString = listString.stream().map(String::trim).collect(Collectors.toList());
        numberOfColumns = (int) listString.get(0).replaceAll("\\s+", " ").chars().filter(c -> c == (int) ' ').count() + 1;

        switch (numberOfColumns) {
            case 1:
                for (String stringValue : listString) {
                    try {
                        arrayListNumber1.add(Double.parseDouble(stringValue));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < arrayListNumber1.size(); i++) {
                    arrayListGeneral.add(arrayListNumber1.get(i));
                    arrayList.add(arrayListNumber1.get(i));
                }
                break;
            case 2:
                int space = 0;
                for (int i = 0; i < listString.size(); i++) {
                    for (int j = 0; j < listString.get(i).replaceAll("\\s+", " ").length(); j++) {
                        if (listString.get(i).replaceAll("\\s+", " ").charAt(j) == ' ') {
                            space = j;
                            arrayListNumber1.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring(0, space)));
                            arrayListNumber2.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring(space)));
                            break;
                        }
                    }

                }
                //adding to general
                for (int i = 0; i < arrayListNumber1.size(); i++) {
                    arrayListGeneral.add(arrayListNumber1.get(i));
                    withoutSortingArrayListNumber1.add(arrayListNumber1.get(i));
                    arrayListGeneral.add(arrayListNumber2.get(i));
                    withoutSortingArrayListNumber2.add(arrayListNumber2.get(i));
                }
                for (int i = 0; i < arrayListGeneral.size(); i++) {
                    arrayList.add(arrayListGeneral.get(i));
                }
                arrayListNumber1.sort(Comparator.naturalOrder());
                arrayListNumber2.sort(Comparator.naturalOrder());
                break;
            case 3:
                List space1 = new ArrayList();

                for (int i = 0; i < listString.size(); i++) {
                    for (int j = 0; j < listString.get(i).replaceAll("\\s+", " ").length(); j++) {
                        if (listString.get(i).replaceAll("\\s+", " ").charAt(j) == ' ') {
                            space1.add(j);
                            if (space1.size() == 2) {
                                arrayListNumber1.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring(0, (Integer) space1.get(0))));
                                arrayListNumber2.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space1.get(0), (Integer) space1.get(1))));
                                arrayListNumber3.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space1.get(1))));
                                space1.clear();
                                break;
                            }
                        }
                    }
                }
                for (int i = 0; i < arrayListNumber1.size(); i++) {
                    arrayListGeneral.add(arrayListNumber1.get(i));
                    arrayListGeneral.add(arrayListNumber2.get(i));
                    arrayListGeneral.add(arrayListNumber3.get(i));
                    withoutSortingArrayListNumber1.add(arrayListNumber1.get(i));
                    withoutSortingArrayListNumber2.add(arrayListNumber2.get(i));
                    withoutSortingArrayListNumber3.add(arrayListNumber3.get(i));
                }
                for (int i = 0; i < arrayListGeneral.size(); i++) {
                    arrayList.add(arrayListGeneral.get(i));
                }
                arrayListNumber1.sort(Comparator.naturalOrder());
                arrayListNumber2.sort(Comparator.naturalOrder());
                arrayListNumber3.sort(Comparator.naturalOrder());
                break;
            default:
                System.out.println("Something wrong with this value: numberOfColumns");
                break;
        }

        arrayList.sort(Comparator.naturalOrder());
    }

    //????????:
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
        TableColumn columnForNumber = new TableColumn("??????????");
        columnForNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        TableColumn columnForFrequency = new TableColumn("??????????????");
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
                new MainCharactericticForData("?????????????? ??????????????????????", list.get(0) - q1, list.get(0), list.get(0) + q1, BigDecimal.valueOf(list.get(3) / Math.sqrt(size)).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()),
                new MainCharactericticForData("?????????????????? ??????????????????", list.get(1) - q2, (double) list.get(1), list.get(1) + q2, BigDecimal.valueOf((list.get(2) / Math.sqrt(2 * size))).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()),
                new MainCharactericticForData("?????????????????? ??????????????", 0, list.get(2), 0, 0),
                new MainCharactericticForData("?????????????? ??????????????????????", BigDecimal.valueOf(list.get(3) - q3).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(), list.get(3), list.get(3) + q3, BigDecimal.valueOf(list.get(3) / Math.sqrt(2 * size)).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()),
                new MainCharactericticForData("??????????????  ??????????????", 0, list.get(4), 0, 0),
                new MainCharactericticForData("?????????????????? ??????????????????", list.get(5) - q5, list.get(5), list.get(5) + q5, BigDecimal.valueOf(Math.sqrt((double) 6 * (size - 2) / ((size + 1) * (size + 3)))).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()),
                new MainCharactericticForData("?????????????????? ??????????????", 0, list.get(6), 0, 0),
                new MainCharactericticForData("???????????? ????????????????????", list.get(7) - q7, list.get(7), list.get(7) + q7, BigDecimal.valueOf((24.0 / size) * (1 - (225.0 / (15 * size + 124)))).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()),
                new MainCharactericticForData("???????????? ????????????????", 0, list.get(8), 0, 0),
                new MainCharactericticForData("??????????????????????", list.get(9) - q9, list.get(9), BigDecimal.valueOf(list.get(9) + q9).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(), BigDecimal.valueOf(Math.sqrt(list.get(8) / (29 * size)) * Math.pow(Math.pow(Math.abs((1 + Math.pow((double) list.get(8), 2))), 3), 0.25)).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()),
                new MainCharactericticForData("??????????????", (double) list.get(10) - q10, list.get(10), BigDecimal.valueOf((double) list.get(10) + q10).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(), BigDecimal.valueOf((double) list.get(10) * (Math.sqrt((1 + 2 * Math.pow((double) list.get(10), 2)) / (2 * size)))).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()),
                new MainCharactericticForData("MED", 0, list.get(11), 0, 0),
                new MainCharactericticForData("MAD", 0, list.get(12), 0, 0),
                new MainCharactericticForData("?????????????????????????????? ???????????????????? ????????????????", 0, list.get(13), 0, 0),
                new MainCharactericticForData("MED ??????????", 0, list.get(14), 0, 0)
        );

        TableColumn columnCharacteristic = new TableColumn("????????????????????????????");
        columnCharacteristic.setCellValueFactory(new PropertyValueFactory<>("characteristic"));
        TableColumn columnINF = new TableColumn("INF");
        columnINF.setCellValueFactory(new PropertyValueFactory("inf"));
        TableColumn columnForValue = new TableColumn("????????????????");
        columnForValue.setCellValueFactory(new PropertyValueFactory("values"));
        TableColumn columnSUP = new TableColumn("SUP");
        columnSUP.setCellValueFactory(new PropertyValueFactory("sup"));
        TableColumn columnForAverage = new TableColumn("??????.??????????");
        columnForAverage.setCellValueFactory(new PropertyValueFactory("average"));

        tableView.setItems(data);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.getColumns().addAll(columnCharacteristic, columnINF, columnForValue, columnSUP, columnForAverage);
    }

    //???????????????? ????????????????????????
    //?????????????? ??????????????:
    @FXML
    protected void tTestForDepens(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "???????????????? ?????? ????????????????", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            String message = "";
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????????? ????????", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (arr1.size() != arr2.size()) {
                    JOptionPane.showMessageDialog(null, "??????????????! ?????????? ?????????????? ????????????", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    //f-test:
                    message += Helper.messageTtestForInDepends(arr1, arr2);
                    JOptionPane.showMessageDialog(null, message, "???????????????? ????????????????????????", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    @FXML
    protected void odnoFactDusAnalyze(ActionEvent event) {
        String message = "";
        message += Helper.messageForOdnoFactorniyDuspersniyAnaliz(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5);
        JOptionPane.showMessageDialog(null, message, "???????????????? ????????????????????????", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void kriteriiZnakiv(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "???????????????? ?????? ????????????????", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            String message = "";
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????????? ????????", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (arr1.size() != arr2.size()) {
                    JOptionPane.showMessageDialog(null, "??????????????! ?????????? ?????????????? ????????????", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    //f-test:
                    message += Helper.messageForKriteriiZnakiv((ArrayList) arr1, (ArrayList) arr2);
                    JOptionPane.showMessageDialog(null, message, "???????????????? ????????????????????????", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    @FXML
    protected void QKriterii(ActionEvent event) {
        String message = "";
        message += Helper.messageForQKohren(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5);
        JOptionPane.showMessageDialog(null, message, "???????????????? ????????????????????????", JOptionPane.INFORMATION_MESSAGE);
    }

    //?????????????????? ??????????????:
    @FXML
    protected void tTestForInDepens(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "???????????????? ?????? ????????????????", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            String message = "";
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????????? ????????", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                //f-test:
                message += Helper.messageTtestForInDepends(arr1, arr2);
                JOptionPane.showMessageDialog(null, message, "???????????????? ????????????????????????", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @FXML
    protected void fTest(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "???????????????? ?????? ????????????????", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            String message = "";
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????????? ????????", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                //f-test:
                message += Helper.messageFtestMessage(arr1, arr2);
                JOptionPane.showMessageDialog(null, message, "???????????????? ????????????????????????", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @FXML
    protected void bartlet(ActionEvent event) {
        String message = "";
        message += Helper.messageForBartlet(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5);
        JOptionPane.showMessageDialog(null, message, "???????????????? ????????????????????????", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void abbe(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "???????????????? ?????? ????????????????", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            String message = "";
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????????? ????????", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                //f-test:
                message += Helper.messageForAbbe((ArrayList) arr1);
                JOptionPane.showMessageDialog(null, message, "???????????????? ????????????????????????", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @FXML
    protected void kolmogorovaSmirnova(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "???????????????? ?????? ????????????????", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            String message = "";
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????????? ????????", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                //f-test:
                message += Helper.messageForKolmogorovaSmirnova((ArrayList) arr1, (ArrayList) arr2);
                JOptionPane.showMessageDialog(null, message, "???????????????? ????????????????????????", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    //??????????????:
    @FXML
    protected void vilkoksona(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "???????????????? ?????? ????????????????", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            String message = "";
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????????? ????????", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                //f-test:
                message += Helper.messageForVilksona(Helper.rangRow((ArrayList<Double>) arr1, (ArrayList<Double>) arr2));
                JOptionPane.showMessageDialog(null, message, "???????????????? ????????????????????????", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @FXML
    protected void riznSerRangiv(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "???????????????? ?????? ????????????????", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            String message = "";
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????????? ????????", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                //f-test:
                message += Helper.messageForRiznSerednihRangiv(Helper.rangRow((ArrayList<Double>) arr1, (ArrayList<Double>) arr2));
                JOptionPane.showMessageDialog(null, message, "???????????????? ????????????????????????", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @FXML
    protected void Hkriterrii(ActionEvent event) {
        String message = "";
        message += Helper.messageForHKruskalaUolis(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5);
        JOptionPane.showMessageDialog(null, message, "???????????????? ????????????????????????", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void rangTable(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "???????????????? ?????? ????????????????", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????????? ????????", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Helper.showRangRowList(tableView, (ArrayList) arr1, (ArrayList) arr2);
            }
        }
    }

    //?????? ????????????????:
    @FXML
    protected void aboutMenu(ActionEvent event) {
        String message = "Program created by Oleksandr Pyvovar";
        JOptionPane.showMessageDialog(null, message, "About", JOptionPane.INFORMATION_MESSAGE);
    }

    //????????
    @FXML
    protected void changeMenuNumber1(ActionEvent event) {
        arrayList.clear();
        for (int i = 0; i < arrayListNumber1.size(); i++) {
            arrayList.add(arrayListNumber1.get(i));
        }
        if (!arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "???? ?????????????? ???????????? ???????? ???1. ???????????? ??????????:" + arrayListNumber1.size(), "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????????? ????????", "About", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    protected void changeMenuNumber2(ActionEvent event) {
        arrayList.clear();
        for (int i = 0; i < arrayListNumber2.size(); i++) {
            arrayList.add(arrayListNumber2.get(i));
        }
        if (!arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "???? ?????????????? ???????????? ???????? ???2. ???????????? ??????????:" + arrayListNumber2.size(), "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????????? ????????", "About", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    protected void changeMenuNumber3(ActionEvent event) {
        arrayList.clear();
        for (int i = 0; i < arrayListNumber3.size(); i++) {
            arrayList.add(arrayListNumber3.get(i));
        }
        if (!arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "???? ?????????????? ???????????? ???????? ???3.???????????? ??????????:" + arrayListNumber3.size(), "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????????? ????????", "About", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    protected void changeMenuNumber4(ActionEvent event) {
        arrayList.clear();
        for (int i = 0; i < arrayListNumber4.size(); i++) {
            arrayList.add(arrayListNumber4.get(i));
        }
        if (!arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "???? ?????????????? ???????????? ???????? ???4.???????????? ??????????:" + arrayListNumber4.size(), "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????????? ????????", "About", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    protected void changeMenuNumber5(ActionEvent event) {
        arrayList.clear();
        for (int i = 0; i < arrayListNumber5.size(); i++) {
            arrayList.add(arrayListNumber5.get(i));
        }
        if (!arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "???? ?????????????? ???????????? ???????? ???5.???????????? ??????????:" + arrayListNumber5.size(), "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????????? ????????", "About", JOptionPane.ERROR_MESSAGE);
        }
    }

    //???????????????????? ??????????????:
    @FXML
    protected void readVectorMenuNumber1Plus2(ActionEvent event) {
        arrayList.clear();
        listForDvomirnixVibirok.clear();
        for (int i = 0; i < arrayListNumber2.size(); i++) {
            arrayList.add(arrayListNumber2.get(i));
        }
        for (int i = 0; i < arrayListNumber1.size(); i++) {
            arrayList.add(arrayListNumber1.get(i));
        }
        if ((!arrayListNumber1.isEmpty()) && (!arrayListNumber2.isEmpty())) {
            listForDvomirnixVibirok.add(1);
            listForDvomirnixVibirok.add(2);
            JOptionPane.showMessageDialog(null, "???? ?????????????? ???????????? ?????????????? ???1 i 2", "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????????? ????????", "About", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    protected void readVectorMenuNumber1Plus3(ActionEvent event) {
        listForDvomirnixVibirok.clear();
        arrayList.clear();
        for (int i = 0; i < arrayListNumber3.size(); i++) {
            arrayList.add(arrayListNumber3.get(i));
        }
        for (int i = 0; i < arrayListNumber1.size(); i++) {
            arrayList.add(arrayListNumber1.get(i));
        }
        if ((!arrayListNumber3.isEmpty()) && (!arrayListNumber1.isEmpty())) {
            listForDvomirnixVibirok.add(1);
            listForDvomirnixVibirok.add(3);
            JOptionPane.showMessageDialog(null, "???? ?????????????? ???????????? ?????????????? ???1 i 3", "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????????? ????????", "About", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    protected void readVectorMenuNumber2Plus3(ActionEvent event) {
        arrayList.clear();
        listForDvomirnixVibirok.clear();
        for (int i = 0; i < arrayListNumber3.size(); i++) {
            arrayList.add(arrayListNumber3.get(i));
        }
        for (int i = 0; i < arrayListNumber2.size(); i++) {
            arrayList.add(arrayListNumber2.get(i));
        }
        if ((!arrayListNumber3.isEmpty()) && (!arrayListNumber2.isEmpty())) {
            listForDvomirnixVibirok.add(2);
            listForDvomirnixVibirok.add(3);
            JOptionPane.showMessageDialog(null, "???? ?????????????? ???????????? ?????????????? ???2 i 3", "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????????? ????????", "About", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    protected void readFullMenu(ActionEvent event) {
        arrayList.clear();
        for (int i = 0; i < arrayListGeneral.size(); i++) {
            arrayList.add(arrayListGeneral.get(i));
        }
        if (!arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "???? ?????????????? ???????????? ?????????? ????????????????????", "About", JOptionPane.INFORMATION_MESSAGE);
            showVarRowList();
        } else {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????????? ????????", "About", JOptionPane.ERROR_MESSAGE);
        }
    }

    //?????????????????????? ??????????????:
    @FXML
    protected void modelNormRozpodil(ActionEvent event) {
        try {
            double n = Double.parseDouble(strModelN.getText());
            double p = Double.parseDouble(strModelP.getText());
            double m = Double.parseDouble(strModelM.getText());
            List<Double> list = Helper.returnListForModelNormalRozpodil(m, p, n);

            File dir = null;
            JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int resp = fc.showOpenDialog(null);
            if (resp == JFileChooser.APPROVE_OPTION) {
                dir = fc.getSelectedFile();
            }

            File file = new File(dir, "norm" + forFileIndex + ".txt");
            forFileIndex++;
            FileWriter fw = null;
            try {
                fw = new FileWriter(file);
                for (double a : list) {
                    fw.write(a + "\n");
                }
                fw.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (fw != null) {
                    try {
                        fw.close();
                    } catch (IOException ex) {
                    }
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "?????????????? ?????????? ?????????????????? N, m ???? ??", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    @FXML
    protected void modelEksponRozpodil(ActionEvent event) {
        try {
            double n = Double.parseDouble(strModelN.getText());
            double l = Double.parseDouble(strModelL.getText());
            List<Double> list = Helper.returnListForModelEkspontialRozpodil(n, l);

            File dir = null;
            JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int resp = fc.showOpenDialog(null);
            if (resp == JFileChooser.APPROVE_OPTION) {
                dir = fc.getSelectedFile();
            }

            File file = new File(dir, "eksp" + forFileIndex + ".txt");
            FileWriter fw = null;
            try {
                fw = new FileWriter(file);
                for (double a : list) {
                    fw.write(a + "\n");
                }
                fw.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (fw != null) {
                    try {
                        fw.close();
                    } catch (IOException ex) {
                    }
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "?????????????? ?????????? ?????????????????? N ???? ??", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    @FXML
    protected void modelLiniinaRegresiaRozpodil(ActionEvent event) {
        try {
            double n = Double.parseDouble(strModelN.getText());
            double min = Double.parseDouble(minRegressia.getText());
            double max = Double.parseDouble(maxRegressia.getText());
            double a = Double.parseDouble(aRegressia.getText());
            double b = Double.parseDouble(bRegressia.getText());
            double dus = Double.parseDouble(duspersiaRegressia.getText());
            List<List<Double>> listOfXAndY = Helper.returnTwoListOfLiniinaRegresia(min, max, a, b, dus, n);
            List<Double> listX = listOfXAndY.get(0);
            List<Double> listY = listOfXAndY.get(1);

            File dir = null;
            JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int resp = fc.showOpenDialog(null);
            if (resp == JFileChooser.APPROVE_OPTION) {
                dir = fc.getSelectedFile();
            }

            File file = new File(dir, "liniina" + forFileIndex + ".txt");
            forFileIndex++;
            FileWriter fw = null;
            try {
                fw = new FileWriter(file);
                for (int i = 0; i < listX.size(); i++) {
                    fw.write(listX.get(i) + "\t" + listY.get(i) + "\n");
                }
                fw.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (fw != null) {
                    try {
                        fw.close();
                    } catch (IOException ex) {
                    }
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "?????????????? ?????????? ??????????????????", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    @FXML
    protected void modelParobolRegresiaRozpodil(ActionEvent event) {
        try {
            double n = Double.parseDouble(strModelN.getText());
            double min = Double.parseDouble(minRegressia.getText());
            double max = Double.parseDouble(maxRegressia.getText());
            double a = Double.parseDouble(aRegressia.getText());
            double b = Double.parseDouble(bRegressia.getText());
            double c = Double.parseDouble(cRegressia.getText());
            double dus = Double.parseDouble(duspersiaRegressia.getText());
            List<List<Double>> listOfXAndY = Helper.returnTwoListOfParabolRegresia(min, max, a, b, c, dus, n);
            List<Double> listX = listOfXAndY.get(0);
            List<Double> listY = listOfXAndY.get(1);

            File dir = null;
            JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int resp = fc.showOpenDialog(null);
            if (resp == JFileChooser.APPROVE_OPTION) {
                dir = fc.getSelectedFile();
            }

            File file = new File(dir, "parabol" + forFileIndex + ".txt");
            forFileIndex++;
            FileWriter fw = null;
            try {
                fw = new FileWriter(file);
                for (int i = 0; i < listX.size(); i++) {
                    fw.write(listX.get(i) + "\t" + listY.get(i) + "\n");
                }
                fw.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (fw != null) {
                    try {
                        fw.close();
                    } catch (IOException ex) {
                    }
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "?????????????? ?????????? ??????????????????", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    @FXML
    protected void modelKvaziRegresiaRozpodil(ActionEvent event) {
        try {
            double n = Double.parseDouble(strModelN.getText());
            double min = Double.parseDouble(minRegressia.getText());
            double max = Double.parseDouble(maxRegressia.getText());
            double a = Double.parseDouble(aRegressia.getText());
            double b = Double.parseDouble(bRegressia.getText());
            double c = Double.parseDouble(cRegressia.getText());
            double dus = Double.parseDouble(duspersiaRegressia.getText());
            List<List<Double>> listOfXAndY = Helper.returnTwoListOfKvaziLiniinaRegresia(min, max, a, b, c, dus, n);
            List<Double> listX = listOfXAndY.get(0);
            List<Double> listY = listOfXAndY.get(1);

            File dir = null;
            JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int resp = fc.showOpenDialog(null);
            if (resp == JFileChooser.APPROVE_OPTION) {
                dir = fc.getSelectedFile();
            }

            File file = new File(dir, "kvazi" + forFileIndex + ".txt");
            forFileIndex++;
            FileWriter fw = null;
            try {
                fw = new FileWriter(file);
                for (int i = 0; i < listX.size(); i++) {
                    fw.write(listX.get(i) + "\t" + listY.get(i) + "\n");
                }
                fw.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (fw != null) {
                    try {
                        fw.close();
                    } catch (IOException ex) {
                    }
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "?????????????? ?????????? ??????????????????", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    //???????????? ?????????????????????? ??????????:
    @FXML
    protected void showVarRowMatrix() {
        tableView.getItems().clear();
        tableView.getColumns().clear();
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        ArrayList sort1 = listOfLists.get(0);
        ArrayList sort2 = listOfLists.get(1);
        ArrayList notSort1 = listOfLists.get(2);
        ArrayList notSort2 = listOfLists.get(3);
        List<Double> listOfVariationData = Helper.variationMatrixData(sort1, sort2, notSort1, notSort2);

        int size = sort1.size();
        final ObservableList<VariationMatrix> data = FXCollections.observableArrayList();
        data.add(new VariationMatrix("y1 ",
                listOfVariationData.get(42) + ", " + BigDecimal.valueOf(listOfVariationData.get(42) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(43) + ", " + BigDecimal.valueOf(listOfVariationData.get(43) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(44) + ", " + BigDecimal.valueOf(listOfVariationData.get(44) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(45) + ", " + BigDecimal.valueOf(listOfVariationData.get(45) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(46) + ", " + BigDecimal.valueOf(listOfVariationData.get(46) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(47) + ", " + BigDecimal.valueOf(listOfVariationData.get(47) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(48) + ", " + BigDecimal.valueOf(listOfVariationData.get(48) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()));//1

        data.add(new VariationMatrix("y2",
                listOfVariationData.get(35) + ", " + BigDecimal.valueOf(listOfVariationData.get(35) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(36) + ", " + BigDecimal.valueOf(listOfVariationData.get(36) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(37) + ", " + BigDecimal.valueOf(listOfVariationData.get(37) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(38) + ", " + BigDecimal.valueOf(listOfVariationData.get(38) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(39) + ", " + BigDecimal.valueOf(listOfVariationData.get(39) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(40) + ", " + BigDecimal.valueOf(listOfVariationData.get(40) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(41) + ", " + BigDecimal.valueOf(listOfVariationData.get(41) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()));//2

        data.add(new VariationMatrix("y3",
                listOfVariationData.get(28) + ", " + BigDecimal.valueOf(listOfVariationData.get(28) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(29) + ", " + BigDecimal.valueOf(listOfVariationData.get(29) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(30) + ", " + BigDecimal.valueOf(listOfVariationData.get(30) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(31) + ", " + BigDecimal.valueOf(listOfVariationData.get(31) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(32) + ", " + BigDecimal.valueOf(listOfVariationData.get(32) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(33) + ", " + BigDecimal.valueOf(listOfVariationData.get(33) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(34) + ", " + BigDecimal.valueOf(listOfVariationData.get(34) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()));//3

        data.add(new VariationMatrix("y4",
                listOfVariationData.get(21) + ", " + BigDecimal.valueOf(listOfVariationData.get(21) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(22) + ", " + BigDecimal.valueOf(listOfVariationData.get(22) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(23) + ", " + BigDecimal.valueOf(listOfVariationData.get(23) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(24) + ", " + BigDecimal.valueOf(listOfVariationData.get(24) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(25) + ", " + BigDecimal.valueOf(listOfVariationData.get(25) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(26) + ", " + BigDecimal.valueOf(listOfVariationData.get(26) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(27) + ", " + BigDecimal.valueOf(listOfVariationData.get(27) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()));//4

        data.add(new VariationMatrix("y5",
                listOfVariationData.get(14) + ", " + BigDecimal.valueOf(listOfVariationData.get(14) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(15) + ", " + BigDecimal.valueOf(listOfVariationData.get(15) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(16) + ", " + BigDecimal.valueOf(listOfVariationData.get(16) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(17) + ", " + BigDecimal.valueOf(listOfVariationData.get(17) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(18) + ", " + BigDecimal.valueOf(listOfVariationData.get(18) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(19) + ", " + BigDecimal.valueOf(listOfVariationData.get(19) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(20) + ", " + BigDecimal.valueOf(listOfVariationData.get(20) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()));//5

        data.add(new VariationMatrix("y6",
                listOfVariationData.get(7) + ", " + BigDecimal.valueOf(listOfVariationData.get(7) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(8) + ", " + BigDecimal.valueOf(listOfVariationData.get(8) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(9) + ", " + BigDecimal.valueOf(listOfVariationData.get(9) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(10) + ", " + BigDecimal.valueOf(listOfVariationData.get(10) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(11) + ", " + BigDecimal.valueOf(listOfVariationData.get(11) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(12) + ", " + BigDecimal.valueOf(listOfVariationData.get(12) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(13) + ", " + BigDecimal.valueOf(listOfVariationData.get(13) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()));//6

        data.add(new VariationMatrix("y7",
                listOfVariationData.get(0) + ", " + BigDecimal.valueOf(listOfVariationData.get(0) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(1) + ", " + BigDecimal.valueOf(listOfVariationData.get(1) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(2) + ", " + BigDecimal.valueOf(listOfVariationData.get(2) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(3) + ", " + BigDecimal.valueOf(listOfVariationData.get(3) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(4) + ", " + BigDecimal.valueOf(listOfVariationData.get(4) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(5) + ", " + BigDecimal.valueOf(listOfVariationData.get(5) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue(),
                listOfVariationData.get(6) + ", " + BigDecimal.valueOf(listOfVariationData.get(6) / size).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()));//7

        //Creating columns
        TableColumn columnForXAndY = new TableColumn("Y\\X");
        columnForXAndY.setCellValueFactory(new PropertyValueFactory<VariationMatrix, String>("xAndy"));
        TableColumn columnForX1 = new TableColumn("x1");
        columnForX1.setCellValueFactory(new PropertyValueFactory<>("x1"));
        TableColumn columnForX2 = new TableColumn("x2");
        columnForX2.setCellValueFactory(new PropertyValueFactory<>("x2"));
        TableColumn columnForX3 = new TableColumn("x3");
        columnForX3.setCellValueFactory(new PropertyValueFactory<>("x3"));
        TableColumn columnForX4 = new TableColumn("x4");
        columnForX4.setCellValueFactory(new PropertyValueFactory<>("x4"));
        TableColumn columnForX5 = new TableColumn("x5");
        columnForX5.setCellValueFactory(new PropertyValueFactory<>("x5"));
        TableColumn columnForX6 = new TableColumn("x6");
        columnForX6.setCellValueFactory(new PropertyValueFactory<>("x6"));
        TableColumn columnForX7 = new TableColumn("x7");
        columnForX7.setCellValueFactory(new PropertyValueFactory<>("x7"));


        //Adding data to the table
        tableView.setItems(data);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.getColumns().addAll(columnForXAndY, columnForX1, columnForX2, columnForX3, columnForX4, columnForX5, columnForX6, columnForX7);

    }

    @FXML
    protected void firstStaticAnalyzeDots(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        if (listOfLists.size() != 4) {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????? ???????????????????? ???????? ?? ?? ?????????????? '????????'->'???????????????????? ????????????????????' ?????????????? ?????????????????? ??????????????(?????????? ?????? ??????????)", "About", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, Helper.firstAnalyze(listOfLists.get(2), listOfLists.get(3)), "?????????????????? ???????????????????????? ????????????", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    @FXML
    protected void firstStaticAnalyzeAdekvat(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        if (listOfLists.size() != 4) {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????? ???????????????????? ???????? ?? ?? ?????????????? '????????'->'???????????????????? ????????????????????' ?????????????? ?????????????????? ??????????????(?????????? ?????? ??????????)", "About", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, Helper.firstAnalyzeAdekv(listOfLists.get(2), listOfLists.get(3)), "?????????????????? ???????????????????????? ????????????", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    @FXML
    protected void scatterChartForKorilationField1(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        if (listOfLists.size() != 4) {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????? ???????????????????? ???????? ?? ?? ?????????????? '????????'->'???????????????????? ????????????????????' ?????????????? ?????????????????? ??????????????(?????????? ?????? ??????????)", "About", JOptionPane.ERROR_MESSAGE);
        } else {
            Helper.drawScatterChartForKorilationField(scatterChartForKorilationField, xAxisForScatterChartForKorilationField, yAxisForScatterChartForKorilationField, listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3));
        }

    }

    @FXML
    protected void scatterChartForFrequencyOfHystograma1(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        if (listOfLists.size() != 4) {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????? ???????????????????? ???????? ?? ?? ?????????????? '????????'->'???????????????????? ????????????????????' ?????????????? ?????????????????? ??????????????(?????????? ?????? ??????????)", "About", JOptionPane.ERROR_MESSAGE);
        } else {
            Helper.drawScatterChartForFrequencyOfHystograma(scatterChartForFrequencyOfHystograma, xAxisForScatterChartForFrequencyOfHystograma, yAxisForScatterChartForFrequencyOfHystograma, listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3));
        }
    }

    @FXML
    protected void dataForKorilationAnalize(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        JOptionPane.showMessageDialog(null, Helper.korilationKoefData(listOfLists.get(2), listOfLists.get(3)), "???????????????????? ??????????????????", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void dataForLKoefKorilationVidnos(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        JOptionPane.showMessageDialog(null, Helper.korilationVidnoshenKoefData(listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3)), "???????????????????? ?????????????????????????? ????????????????????", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void dataForRangKorilation(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        JOptionPane.showMessageDialog(null, Helper.rangOfKorilation(listOfLists.get(2), listOfLists.get(3)), "?????????????? ??????????????????", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void dataForSpolTable(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        JOptionPane.showMessageDialog(null, Helper.koefOfSpolTable(listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3)), "?????????????????????? ?????????????????? ??????????????", JOptionPane.INFORMATION_MESSAGE);
    }

    //??????????????????:
    @FXML
    protected void additionalForSaving(ActionEvent event) {
        arrayListNumber1ForSave.clear();
        arrayListNumber2ForSave.clear();
        withoutSortingArrayListNumber1ForSave.clear();
        withoutSortingArrayListNumber2ForSave.clear();

        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        arrayListNumber1ForSave = listOfLists.get(0);
        arrayListNumber2ForSave = listOfLists.get(1);
        withoutSortingArrayListNumber1ForSave = listOfLists.get(2);
        withoutSortingArrayListNumber2ForSave = listOfLists.get(3);
        JOptionPane.showMessageDialog(null, "??????????????????", "About", JOptionPane.INFORMATION_MESSAGE);

    }

    @FXML
    protected void additionalForReturn(ActionEvent event) {
        arrayListNumber1.clear();
        arrayListNumber2.clear();
        arrayListNumber3.clear();
        withoutSortingArrayListNumber1.clear();
        withoutSortingArrayListNumber2.clear();
        withoutSortingArrayListNumber3.clear();
        arrayListNumber1 = arrayListNumber1ForSave;
        arrayListNumber2 = arrayListNumber2ForSave;
        withoutSortingArrayListNumber1 = withoutSortingArrayListNumber1ForSave;
        withoutSortingArrayListNumber2 = withoutSortingArrayListNumber2ForSave;
        if (arrayListNumber1ForSave.isEmpty() || arrayListNumber2ForSave.isEmpty() || withoutSortingArrayListNumber1ForSave.isEmpty() || withoutSortingArrayListNumber2ForSave.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error!!!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "??????????????????", "About", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    @FXML
    protected void additionalDeleteAnomal(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        ArrayList<ArrayList> resList = Helper.deleteAnomalForAdditional(listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3));
        arrayListNumber1 = resList.get(0);
        arrayListNumber2 = resList.get(1);
        withoutSortingArrayListNumber1 = resList.get(2);
        withoutSortingArrayListNumber2 = resList.get(3);
        JOptionPane.showMessageDialog(null, "??????????????????", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void additionalLogarifm(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        arrayListNumber1 = (ArrayList) Helper.logarifmForAdditional(listOfLists.get(0));
        arrayListNumber2 = (ArrayList) Helper.logarifmForAdditional(listOfLists.get(1));
        withoutSortingArrayListNumber1 = (ArrayList) Helper.logarifmForAdditional(listOfLists.get(2));
        withoutSortingArrayListNumber2 = (ArrayList) Helper.logarifmForAdditional(listOfLists.get(3));
        JOptionPane.showMessageDialog(null, "??????????????????", "About", JOptionPane.INFORMATION_MESSAGE);
    }


    @FXML
    protected void scatterLineRegressionMNK(ActionEvent event) {
        if (!aRegressia.getText().isEmpty()) {
            aForT = Double.parseDouble(aRegressia.getText());
        }
        if (!bRegressia.getText().isEmpty()) {
            bForT = Double.parseDouble(bRegressia.getText());
        }
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        if (listOfLists.size() != 4) {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????? ???????????????????? ???????? ?? ?? ?????????????? '????????'->'???????????????????? ????????????????????' ?????????????? ?????????????????? ??????????????(?????????? ?????? ??????????)", "About", JOptionPane.ERROR_MESSAGE);
        } else {
            Helper.drawLiniinaRegresiaMNK1(listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3), scatterChartForKorilationField, xAxisForScatterChartForKorilationField, yAxisForScatterChartForKorilationField, aForT, bForT);
        }
    }

//    @FXML
//    protected void scatterLineRegressionMNK2(ActionEvent event) {
//        if (!aRegressia.getText().isEmpty()) {
//            aForT = Double.parseDouble(aRegressia.getText());
//        }
//        if (!bRegressia.getText().isEmpty()) {
//            bForT = Double.parseDouble(bRegressia.getText());
//        }
//        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
//        if (listOfLists.size() != 4) {
//            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????? ???????????????????? ???????? ?? ?? ?????????????? '????????'->'???????????????????? ????????????????????' ?????????????? ?????????????????? ??????????????(?????????? ?????? ??????????)", "About", JOptionPane.ERROR_MESSAGE);
//        } else {
//            Helper.drawLiniinaRegresiaMNK2(listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3), scatterChartForKorilationField, xAxisForScatterChartForKorilationField, yAxisForScatterChartForKorilationField, aForT, bForT);
//        }
//    }

    @FXML
    protected void scatterLineRegressionTeula(ActionEvent event) {
        if (!aRegressia.getText().isEmpty()) {
            aForT = Double.parseDouble(aRegressia.getText());
        }
        if (!bRegressia.getText().isEmpty()) {
            bForT = Double.parseDouble(bRegressia.getText());
        }
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        if (listOfLists.size() != 4) {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????? ???????????????????? ???????? ?? ?? ?????????????? '????????'->'???????????????????? ????????????????????' ?????????????? ?????????????????? ??????????????(?????????? ?????? ??????????)", "About", JOptionPane.ERROR_MESSAGE);
        } else {
            Helper.drawLiniinaRegresiaTeila(listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3), scatterChartForKorilationField, xAxisForScatterChartForKorilationField, yAxisForScatterChartForKorilationField, aForT, bForT);
        }
    }

    @FXML
    protected void findDusperiu(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        if (listOfLists.size() != 4) {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????? ???????????????????? ???????? ?? ?? ?????????????? '????????'->'???????????????????? ????????????????????' ?????????????? ?????????????????? ??????????????(?????????? ?????? ??????????)", "About", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, Helper.checkDuspersia(listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3)), "About", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    protected void otherDetail(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        if (listOfLists.size() != 4) {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????? ???????????????????? ???????? ?? ?? ?????????????? '????????'->'???????????????????? ????????????????????' ?????????????? ?????????????????? ??????????????(?????????? ?????? ??????????)", "About", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, Helper.checkOther(listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3)), "About", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    protected void scatterParobolReggression(ActionEvent event) {
        if (!aRegressia.getText().isEmpty()) {
            aForT = Double.parseDouble(aRegressia.getText());
        }
        if (!bRegressia.getText().isEmpty()) {
            bForT = Double.parseDouble(bRegressia.getText());
        }
        if (!cRegressia.getText().isEmpty()) {
            cForT = Double.parseDouble(cRegressia.getText());
        }

        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        if (listOfLists.size() != 4) {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????? ???????????????????? ???????? ?? ?? ?????????????? '????????'->'???????????????????? ????????????????????' ?????????????? ?????????????????? ??????????????(?????????? ?????? ??????????)", "About", JOptionPane.ERROR_MESSAGE);
        } else {
            Helper.drawParabolRegresia(listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3), scatterChartForKorilationField, xAxisForScatterChartForKorilationField, yAxisForScatterChartForKorilationField, aForT, bForT, cForT);
        }
    }

    @FXML
    protected void scatterKvazilininaRegressia(ActionEvent event){
        if (!aRegressia.getText().isEmpty()) {
            aForT = Double.parseDouble(aRegressia.getText());
        }
        if (!bRegressia.getText().isEmpty()) {
            bForT = Double.parseDouble(bRegressia.getText());
        }

        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        if (listOfLists.size() != 4) {
            JOptionPane.showMessageDialog(null, "??????????????!???????????? ???????????????? ???????????????????? ???????? ?? ?? ?????????????? '????????'->'???????????????????? ????????????????????' ?????????????? ?????????????????? ??????????????(?????????? ?????? ??????????)", "About", JOptionPane.ERROR_MESSAGE);
        } else {
            Helper.drawKvaziRegresia(listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3), scatterChartForKorilationField, xAxisForScatterChartForKorilationField, yAxisForScatterChartForKorilationField, aForT, bForT);
        }
    }
}