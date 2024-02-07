package com.example.idealjavafx;


import com.example.idealjavafx.ai.AILab2;
import com.example.idealjavafx.ai.Klasification;
import com.example.idealjavafx.ai.Klastarization;
import com.example.idealjavafx.graphics.Glif;
import com.example.idealjavafx.graphics.Graphics;
import com.example.idealjavafx.graphics.HeatMapVisualization;
import com.example.idealjavafx.logicHelper.FactorHelper;
import com.example.idealjavafx.logicHelper.TimeRowHelper;
import com.example.idealjavafx.models.MainCharactericticForData;
import com.example.idealjavafx.models.VariationMatrix;
import com.example.idealjavafx.models.VariationRowForData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.math3.linear.RealVector;

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
    static ArrayList currentArrayList = new ArrayList();
    static ArrayList arrayList = new ArrayList();
    static ArrayList arrayListNumber1 = new ArrayList();
    static ArrayList arrayListNumber2 = new ArrayList();
    static ArrayList arrayListNumber3 = new ArrayList();
    static ArrayList arrayListNumber4 = new ArrayList();
    static ArrayList arrayListNumber5 = new ArrayList();
    static ArrayList arrayListNumber6 = new ArrayList();
    static ArrayList arrayListGeneral = new ArrayList();
    static ArrayList listForDvomirnixVibirok = new ArrayList();

    static ArrayList withoutSortingArrayListNumber1 = new ArrayList();
    static ArrayList withoutSortingArrayListNumber2 = new ArrayList();
    static ArrayList withoutSortingArrayListNumber3 = new ArrayList();
    static ArrayList withoutSortingArrayListNumber4 = new ArrayList();
    static ArrayList withoutSortingArrayListNumber5 = new ArrayList();
    static ArrayList withoutSortingArrayListNumber6 = new ArrayList();

    //for saving:
    static ArrayList savingListNumber1 = new ArrayList();
    static ArrayList savingListNumber2 = new ArrayList();
    static ArrayList savingListNumber3 = new ArrayList();
    static ArrayList savingListNumber4 = new ArrayList();
    static ArrayList savingListNumber5 = new ArrayList();
    static ArrayList savingListNumber6 = new ArrayList();

    //sukupnosti:
    static ArrayList<List<Double>> sukupnist1 = new ArrayList<>();
    static ArrayList<List<Double>> sukupnist2 = new ArrayList<>();
//    static ArrayList<List<Double>> sukupnist3 = new ArrayList<>();

    public static List<RealVector> vlasniiVektors = new ArrayList<>();
    public static List<Double> vlasniiValues = new ArrayList<>();

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
    static double alfaForAnomalData = 0;

    private final TimeRowHelper timeRowHelper = new TimeRowHelper();

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
    private LineChart lineChart;
    @FXML
    private BubbleChart bubbleChart;
    @FXML
    private ScatterChart<Number, Number> scatterChartForKorilationField;
    @FXML
    private ScatterChart<Number, Number> scatterChartForFrequencyOfHystograma;
    @FXML
    private NumberAxis xAxisForBubbleChart;
    @FXML
    private NumberAxis yAxisForBubbleChart;
    @FXML
    private NumberAxis xAxisForScatterChartForKorilationField;
    @FXML
    private NumberAxis yAxisForScatterChartForKorilationField;
    @FXML
    private NumberAxis xAxisForScatterChartForFrequencyOfHystograma;
    @FXML
    private NumberAxis yAxisForScatterChartForFrequencyOfHystograma;
    @FXML
    private NumberAxis xAxisForLineChart;
    @FXML
    private NumberAxis yAxisForLineChart;
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
    private CheckBox checkBox6;
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
    @FXML
    private TextField anomalStr;
    //діаграма розкиду
    @FXML
    private BarChart barChartRozkid1;
    @FXML
    private BarChart barChartRozkid2;
    @FXML
    private BarChart barChartRozkid3;
    @FXML
    private BarChart barChartRozkid4;
    @FXML
    private BarChart barChartRozkid5;
    @FXML
    private BarChart barChartRozkid6;
    @FXML
    private ScatterChart<Number, Number> scatterChartRozkid21;
    @FXML
    private NumberAxis xAxisForScatterChartForRozkid21;
    @FXML
    private NumberAxis yAxisForScatterChartForRozkid21;
    @FXML
    private ScatterChart<Number, Number> scatterChartRozkid31;
    @FXML
    private NumberAxis xAxisForScatterChartForRozkid31;
    @FXML
    private NumberAxis yAxisForScatterChartForRozkid31;
    @FXML
    private ScatterChart<Number, Number> scatterChartRozkid32;
    @FXML
    private NumberAxis xAxisForScatterChartForRozkid32;
    @FXML
    private NumberAxis yAxisForScatterChartForRozkid32;
    @FXML
    private ScatterChart<Number, Number> scatterChartRozkid41;
    @FXML
    private NumberAxis xAxisForScatterChartForRozkid41;
    @FXML
    private NumberAxis yAxisForScatterChartForRozkid41;
    @FXML
    private ScatterChart<Number, Number> scatterChartRozkid42;
    @FXML
    private NumberAxis xAxisForScatterChartForRozkid42;
    @FXML
    private NumberAxis yAxisForScatterChartForRozkid42;
    @FXML
    private ScatterChart<Number, Number> scatterChartRozkid43;
    @FXML
    private NumberAxis xAxisForScatterChartForRozkid43;
    @FXML
    private NumberAxis yAxisForScatterChartForRozkid43;
    @FXML
    private ScatterChart<Number, Number> scatterChartRozkid51;
    @FXML
    private NumberAxis xAxisForScatterChartForRozkid51;
    @FXML
    private NumberAxis yAxisForScatterChartForRozkid51;
    @FXML
    private ScatterChart<Number, Number> scatterChartRozkid52;
    @FXML
    private NumberAxis xAxisForScatterChartForRozkid52;
    @FXML
    private NumberAxis yAxisForScatterChartForRozkid52;
    @FXML
    private ScatterChart<Number, Number> scatterChartRozkid53;
    @FXML
    private NumberAxis xAxisForScatterChartForRozkid53;
    @FXML
    private NumberAxis yAxisForScatterChartForRozkid53;
    @FXML
    private ScatterChart<Number, Number> scatterChartRozkid54;
    @FXML
    private NumberAxis xAxisForScatterChartForRozkid54;
    @FXML
    private NumberAxis yAxisForScatterChartForRozkid54;
    @FXML
    private ScatterChart<Number, Number> scatterChartRozkid61;
    @FXML
    private NumberAxis xAxisForScatterChartForRozkid61;
    @FXML
    private NumberAxis yAxisForScatterChartForRozkid61;
    @FXML
    private ScatterChart<Number, Number> scatterChartRozkid62;
    @FXML
    private NumberAxis xAxisForScatterChartForRozkid62;
    @FXML
    private NumberAxis yAxisForScatterChartForRozkid62;
    @FXML
    private ScatterChart<Number, Number> scatterChartRozkid63;
    @FXML
    private NumberAxis xAxisForScatterChartForRozkid63;
    @FXML
    private NumberAxis yAxisForScatterChartForRozkid63;
    @FXML
    private ScatterChart<Number, Number> scatterChartRozkid64;
    @FXML
    private NumberAxis xAxisForScatterChartForRozkid64;
    @FXML
    private NumberAxis yAxisForScatterChartForRozkid64;
    @FXML
    private ScatterChart<Number, Number> scatterChartRozkid65;
    @FXML
    private NumberAxis xAxisForScatterChartForRozkid65;
    @FXML
    private NumberAxis yAxisForScatterChartForRozkid65;
    @FXML
    private Label labelKorilation12;
    @FXML
    private Label labelKorilation13;
    @FXML
    private Label labelKorilation14;
    @FXML
    private Label labelKorilation15;
    @FXML
    private Label labelKorilation16;
    @FXML
    private Label labelKorilation23;
    @FXML
    private Label labelKorilation24;
    @FXML
    private Label labelKorilation25;
    @FXML
    private Label labelKorilation26;
    @FXML
    private Label labelKorilation34;
    @FXML
    private Label labelKorilation35;
    @FXML
    private Label labelKorilation36;
    @FXML
    private Label labelKorilation45;
    @FXML
    private Label labelKorilation46;
    @FXML
    private Label labelKorilation56;
    @FXML
    private TextField indexOfManyRegresia;
    @FXML
    private TextField x1ForManyRegr;
    @FXML
    private TextField x2ForManyRegr;
    @FXML
    private TextField x3ForManyRegr;
    @FXML
    private TextField x4ForManyRegr;
    @FXML
    private TextField x5ForManyRegr;
    @FXML
    private TextField returnInitialTextField;

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
        withoutSortingArrayListNumber1.clear();
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
        for (int i = 0; i < arrayListNumber1.size(); i++) {
            withoutSortingArrayListNumber1.add(arrayListNumber1.get(i));
        }
        arrayListNumber1.sort(Comparator.naturalOrder());
    }

    @FXML
    protected void chooseFileForNumber2(ActionEvent event) {
        arrayListNumber2.clear();
        withoutSortingArrayListNumber2.clear();
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
        for (int i = 0; i < arrayListNumber2.size(); i++) {
            withoutSortingArrayListNumber2.add(arrayListNumber2.get(i));
        }
        arrayListNumber2.sort(Comparator.naturalOrder());
    }

    @FXML
    protected void chooseFileForNumber3(ActionEvent event) {
        arrayListNumber3.clear();
        withoutSortingArrayListNumber3.clear();
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
        for (int i = 0; i < arrayListNumber3.size(); i++) {
            withoutSortingArrayListNumber3.add(arrayListNumber3.get(i));
        }
        arrayListNumber3.sort(Comparator.naturalOrder());
    }

    @FXML
    protected void chooseFileForNumber4(ActionEvent event) {
        arrayListNumber4.clear();
        withoutSortingArrayListNumber4.clear();
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
        for (int i = 0; i < arrayListNumber4.size(); i++) {
            withoutSortingArrayListNumber4.add(arrayListNumber4.get(i));
        }
        arrayListNumber4.sort(Comparator.naturalOrder());
    }

    @FXML
    protected void chooseFileForNumber5(ActionEvent event) {
        arrayListNumber5.clear();
        withoutSortingArrayListNumber5.clear();
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
        for (int i = 0; i < arrayListNumber5.size(); i++) {
            withoutSortingArrayListNumber5.add(arrayListNumber5.get(i));
        }
        arrayListNumber5.sort(Comparator.naturalOrder());
    }

    @FXML
    protected void chooseFileForNumber6(ActionEvent event) {
        arrayListNumber6.clear();
        withoutSortingArrayListNumber6.clear();
        JFileChooser fileopen = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileopen.showDialog(null, "Виберіть текстовий файл");
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
                arrayListNumber6.add(Double.parseDouble(stringValue));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < arrayListNumber6.size(); i++) {
            withoutSortingArrayListNumber6.add(arrayListNumber6.get(i));
        }
        arrayListNumber6.sort(Comparator.naturalOrder());
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

    //файл:
    @FXML
    protected void chooseFileForDvovomirnihVibirok(ActionEvent event) {
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

        int numberOfColumns = 0;
        listString = listString.stream().map(String::trim).collect(Collectors.toList());
        numberOfColumns = (int) listString.get(0).replaceAll("\\s+", " ").chars().filter(c -> c == (int) ' ').count() + 1;

        switch (numberOfColumns) {
            case 1:
                for (String stringValue : listString) {
                    try {
                        arrayListNumber1.add(Double.parseDouble(stringValue));
                        savingListNumber1.add(Double.parseDouble(stringValue));
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
                    savingListNumber1.add(arrayListNumber2.get(i));
                    savingListNumber2.add(arrayListNumber2.get(i));
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
                    savingListNumber1.add(arrayListNumber1.get(i));
                    savingListNumber2.add(arrayListNumber2.get(i));
                    savingListNumber3.add(arrayListNumber3.get(i));
                }
                for (int i = 0; i < arrayListGeneral.size(); i++) {
                    arrayList.add(arrayListGeneral.get(i));
                }
                arrayListNumber1.sort(Comparator.naturalOrder());
                arrayListNumber2.sort(Comparator.naturalOrder());
                arrayListNumber3.sort(Comparator.naturalOrder());
                break;
            case 4:
                List space2 = new ArrayList();

                for (int i = 0; i < listString.size(); i++) {
                    for (int j = 0; j < listString.get(i).replaceAll("\\s+", " ").length(); j++) {
                        if (listString.get(i).replaceAll("\\s+", " ").charAt(j) == ' ') {
                            space2.add(j);
                            if (space2.size() == 3) {
                                arrayListNumber1.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring(0, (Integer) space2.get(0))));
                                arrayListNumber2.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space2.get(0), (Integer) space2.get(1))));
                                arrayListNumber3.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space2.get(1), (Integer) space2.get(2))));
                                arrayListNumber4.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space2.get(2))));
                                space2.clear();
                                break;
                            }
                        }
                    }
                }
                for (int i = 0; i < arrayListNumber1.size(); i++) {
                    arrayListGeneral.add(arrayListNumber1.get(i));
                    arrayListGeneral.add(arrayListNumber2.get(i));
                    arrayListGeneral.add(arrayListNumber3.get(i));
                    arrayListGeneral.add(arrayListNumber4.get(i));
                    withoutSortingArrayListNumber1.add(arrayListNumber1.get(i));
                    withoutSortingArrayListNumber2.add(arrayListNumber2.get(i));
                    withoutSortingArrayListNumber3.add(arrayListNumber3.get(i));
                    withoutSortingArrayListNumber4.add(arrayListNumber4.get(i));
                    savingListNumber1.add(arrayListNumber1.get(i));
                    savingListNumber2.add(arrayListNumber2.get(i));
                    savingListNumber3.add(arrayListNumber3.get(i));
                    savingListNumber4.add(arrayListNumber4.get(i));
                }
                for (int i = 0; i < arrayListGeneral.size(); i++) {
                    arrayList.add(arrayListGeneral.get(i));
                }
                arrayListNumber1.sort(Comparator.naturalOrder());
                arrayListNumber2.sort(Comparator.naturalOrder());
                arrayListNumber3.sort(Comparator.naturalOrder());
                arrayListNumber4.sort(Comparator.naturalOrder());
                break;
            case 5:
                List space3 = new ArrayList();

                for (int i = 0; i < listString.size(); i++) {
                    for (int j = 0; j < listString.get(i).replaceAll("\\s+", " ").length(); j++) {
                        if (listString.get(i).replaceAll("\\s+", " ").charAt(j) == ' ') {
                            space3.add(j);
                            if (space3.size() == 4) {
                                arrayListNumber1.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring(0, (Integer) space3.get(0))));
                                arrayListNumber2.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space3.get(0), (Integer) space3.get(1))));
                                arrayListNumber3.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space3.get(1), (Integer) space3.get(2))));
                                arrayListNumber4.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space3.get(2), (Integer) space3.get(3))));
                                arrayListNumber5.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space3.get(3))));
                                space3.clear();
                                break;
                            }
                        }
                    }
                }
                for (int i = 0; i < arrayListNumber1.size(); i++) {
                    arrayListGeneral.add(arrayListNumber1.get(i));
                    arrayListGeneral.add(arrayListNumber2.get(i));
                    arrayListGeneral.add(arrayListNumber3.get(i));
                    arrayListGeneral.add(arrayListNumber4.get(i));
                    arrayListGeneral.add(arrayListNumber5.get(i));
                    withoutSortingArrayListNumber1.add(arrayListNumber1.get(i));
                    withoutSortingArrayListNumber2.add(arrayListNumber2.get(i));
                    withoutSortingArrayListNumber3.add(arrayListNumber3.get(i));
                    withoutSortingArrayListNumber4.add(arrayListNumber4.get(i));
                    withoutSortingArrayListNumber5.add(arrayListNumber5.get(i));
                    savingListNumber1.add(arrayListNumber1.get(i));
                    savingListNumber2.add(arrayListNumber2.get(i));
                    savingListNumber3.add(arrayListNumber3.get(i));
                    savingListNumber4.add(arrayListNumber4.get(i));
                    savingListNumber5.add(arrayListNumber5.get(i));
                }
                for (int i = 0; i < arrayListGeneral.size(); i++) {
                    arrayList.add(arrayListGeneral.get(i));
                }
                arrayListNumber1.sort(Comparator.naturalOrder());
                arrayListNumber2.sort(Comparator.naturalOrder());
                arrayListNumber3.sort(Comparator.naturalOrder());
                arrayListNumber4.sort(Comparator.naturalOrder());
                arrayListNumber5.sort(Comparator.naturalOrder());
                break;
            case 6:
                List space4 = new ArrayList();

                for (int i = 0; i < listString.size(); i++) {
                    for (int j = 0; j < listString.get(i).replaceAll("\\s+", " ").length(); j++) {
                        if (listString.get(i).replaceAll("\\s+", " ").charAt(j) == ' ') {
                            space4.add(j);
                            if (space4.size() == 5) {
                                arrayListNumber1.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring(0, (Integer) space4.get(0))));
                                arrayListNumber2.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space4.get(0), (Integer) space4.get(1))));
                                arrayListNumber3.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space4.get(1), (Integer) space4.get(2))));
                                arrayListNumber4.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space4.get(2), (Integer) space4.get(3))));
                                arrayListNumber5.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space4.get(3), (Integer) space4.get(4))));
                                arrayListNumber6.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space4.get(4))));
                                space4.clear();
                                break;
                            }
                        }
                    }
                }
                for (int i = 0; i < arrayListNumber1.size(); i++) {
                    arrayListGeneral.add(arrayListNumber1.get(i));
                    arrayListGeneral.add(arrayListNumber2.get(i));
                    arrayListGeneral.add(arrayListNumber3.get(i));
                    arrayListGeneral.add(arrayListNumber4.get(i));
                    arrayListGeneral.add(arrayListNumber5.get(i));
                    arrayListGeneral.add(arrayListNumber6.get(i));
                    withoutSortingArrayListNumber1.add(arrayListNumber1.get(i));
                    withoutSortingArrayListNumber2.add(arrayListNumber2.get(i));
                    withoutSortingArrayListNumber3.add(arrayListNumber3.get(i));
                    withoutSortingArrayListNumber4.add(arrayListNumber4.get(i));
                    withoutSortingArrayListNumber5.add(arrayListNumber5.get(i));
                    withoutSortingArrayListNumber6.add(arrayListNumber6.get(i));
                    savingListNumber1.add(arrayListNumber1.get(i));
                    savingListNumber2.add(arrayListNumber2.get(i));
                    savingListNumber3.add(arrayListNumber3.get(i));
                    savingListNumber4.add(arrayListNumber4.get(i));
                    savingListNumber5.add(arrayListNumber5.get(i));
                    savingListNumber6.add(arrayListNumber6.get(i));
                }
                for (int i = 0; i < arrayListGeneral.size(); i++) {
                    arrayList.add(arrayListGeneral.get(i));
                }
                arrayListNumber1.sort(Comparator.naturalOrder());
                arrayListNumber2.sort(Comparator.naturalOrder());
                arrayListNumber3.sort(Comparator.naturalOrder());
                arrayListNumber4.sort(Comparator.naturalOrder());
                arrayListNumber5.sort(Comparator.naturalOrder());
                arrayListNumber6.sort(Comparator.naturalOrder());
                break;
            default:
                var listOfIndexesInput = Arrays.stream(JOptionPane.showInputDialog(
                                String.format("Так як кількість ознак = %d, то виберіть 6 ознак номера яких будуть записані через пробіл", numberOfColumns), "1 2 3 4 5 6")
                        .trim().split(" ")).map(v -> Integer.parseInt(v) - 1).collect(Collectors.toList());
                if (listOfIndexesInput.size() != 6) {
                    JOptionPane.showMessageDialog(null, "Error! Size must be 6", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    List space5 = new ArrayList();
                    for (int i = 0; i < listString.size(); i++) {
                        for (int j = 0; j < listString.get(i).replaceAll("\\s+", " ").length(); j++) {
                            if (listString.get(i).replaceAll("\\s+", " ").charAt(j) == ' ') {
                                space5.add(j);
                                if (space5.size() == numberOfColumns - 1) {
                                    if (listOfIndexesInput.contains(0)) {
                                        arrayListNumber1.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring(0, (Integer) space5.get(0))));
                                    } else {
                                        arrayListNumber1.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space5.get(listOfIndexesInput.get(0) - 1), (Integer) space5.get(listOfIndexesInput.get(0)))));
                                    }
                                    if (listOfIndexesInput.contains(numberOfColumns - 1)) {
                                        arrayListNumber6.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space5.get(space5.size() - 1))));
                                    } else {
                                        arrayListNumber6.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space5.get(listOfIndexesInput.get(5) - 1), (Integer) space5.get(listOfIndexesInput.get(5)))));
                                    }
                                    arrayListNumber2.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space5.get(listOfIndexesInput.get(1) - 1), (Integer) space5.get(listOfIndexesInput.get(1)))));
                                    arrayListNumber3.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space5.get(listOfIndexesInput.get(2) - 1), (Integer) space5.get(listOfIndexesInput.get(2)))));
                                    arrayListNumber4.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space5.get(listOfIndexesInput.get(3) - 1), (Integer) space5.get(listOfIndexesInput.get(3)))));
                                    arrayListNumber5.add(Double.parseDouble(listString.get(i).replaceAll("\\s+", " ").substring((Integer) space5.get(listOfIndexesInput.get(4) - 1), (Integer) space5.get(listOfIndexesInput.get(4)))));

                                    space5.clear();
                                    break;
                                }
                            }
                        }
                    }
                    for (int i = 0; i < arrayListNumber1.size(); i++) {
                        arrayListGeneral.add(arrayListNumber1.get(i));
                        arrayListGeneral.add(arrayListNumber2.get(i));
                        arrayListGeneral.add(arrayListNumber3.get(i));
                        arrayListGeneral.add(arrayListNumber4.get(i));
                        arrayListGeneral.add(arrayListNumber5.get(i));
                        arrayListGeneral.add(arrayListNumber6.get(i));
                        withoutSortingArrayListNumber1.add(arrayListNumber1.get(i));
                        withoutSortingArrayListNumber2.add(arrayListNumber2.get(i));
                        withoutSortingArrayListNumber3.add(arrayListNumber3.get(i));
                        withoutSortingArrayListNumber4.add(arrayListNumber4.get(i));
                        withoutSortingArrayListNumber5.add(arrayListNumber5.get(i));
                        withoutSortingArrayListNumber6.add(arrayListNumber6.get(i));
                        savingListNumber1.add(arrayListNumber1.get(i));
                        savingListNumber2.add(arrayListNumber2.get(i));
                        savingListNumber3.add(arrayListNumber3.get(i));
                        savingListNumber4.add(arrayListNumber4.get(i));
                        savingListNumber5.add(arrayListNumber5.get(i));
                        savingListNumber6.add(arrayListNumber6.get(i));
                    }
                    for (int i = 0; i < arrayListGeneral.size(); i++) {
                        arrayList.add(arrayListGeneral.get(i));
                    }
                    arrayListNumber1.sort(Comparator.naturalOrder());
                    arrayListNumber2.sort(Comparator.naturalOrder());
                    arrayListNumber3.sort(Comparator.naturalOrder());
                    arrayListNumber4.sort(Comparator.naturalOrder());
                    arrayListNumber5.sort(Comparator.naturalOrder());
                    arrayListNumber6.sort(Comparator.naturalOrder());
                    break;
                }
        }

        arrayList.sort(Comparator.naturalOrder());
    }

    @FXML
    protected void clearVibirok() {
        arrayList.clear();
        arrayListGeneral.clear();
        arrayListNumber1.clear();
        arrayListNumber2.clear();
        arrayListNumber3.clear();
        arrayListNumber4.clear();
        arrayListNumber5.clear();
        arrayListNumber6.clear();
        withoutSortingArrayListNumber1.clear();
        withoutSortingArrayListNumber2.clear();
        withoutSortingArrayListNumber3.clear();
        withoutSortingArrayListNumber4.clear();
        withoutSortingArrayListNumber5.clear();
        withoutSortingArrayListNumber6.clear();
        savingListNumber1.clear();
        savingListNumber2.clear();
        savingListNumber3.clear();
        savingListNumber4.clear();
        savingListNumber5.clear();
        savingListNumber6.clear();
        vlasniiVektors.clear();
        vlasniiValues.clear();
        JOptionPane.showMessageDialog(null, "Вибірки очишені");
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
        ArrayList arrayList1 = new ArrayList(currentArrayList);
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
                new MainCharactericticForData("Середнє арифметичне", Double.parseDouble(String.format("%.3f", list.get(0) - q1)), Double.parseDouble(String.format("%.3f", list.get(0))), Double.parseDouble(String.format("%.3f", list.get(0) + q1)), Double.parseDouble(String.format("%.3f", BigDecimal.valueOf(list.get(3) / Math.sqrt(size)).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()))),
                new MainCharactericticForData("Дисперсія незсунена", Double.parseDouble(String.format("%.3f", list.get(1) - q2)), Double.parseDouble(String.format("%.3f", list.get(1))), Double.parseDouble(String.format("%.3f", list.get(1) + q2)), Double.parseDouble(String.format("%.3f", BigDecimal.valueOf((list.get(2) / Math.sqrt(2 * size))).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()))),
                new MainCharactericticForData("Дисперсія зсунена", 0, Double.parseDouble(String.format("%.3f", list.get(2))), 0, 0),
                new MainCharactericticForData("Середнє квадратичне", Double.parseDouble(String.format("%.3f", BigDecimal.valueOf(list.get(3) - q3).setScale(4, BigDecimal.ROUND_CEILING).doubleValue())), Double.parseDouble(String.format("%.3f", list.get(3))), Double.parseDouble(String.format("%.3f", list.get(3) + q3)), Double.parseDouble(String.format("%.3f", BigDecimal.valueOf(list.get(3) / Math.sqrt(2 * size)).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()))),
                new MainCharactericticForData("Усічене  середнє", 0, Double.parseDouble(String.format("%.3f", list.get(4))), 0, 0),
                new MainCharactericticForData("Асиметрія незсунена", Double.parseDouble(String.format("%.3f", list.get(5) - q5)), Double.parseDouble(String.format("%.3f", list.get(5))), Double.parseDouble(String.format("%.3f", list.get(5) + q5)), Double.parseDouble(String.format("%.3f", BigDecimal.valueOf(Math.sqrt((double) 6 * (size - 2) / ((size + 1) * (size + 3)))).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()))),
                new MainCharactericticForData("Асиметрія зсунена", 0, Double.parseDouble(String.format("%.3f", list.get(6))), 0, 0),
                new MainCharactericticForData("Ексцес незсунений", Double.parseDouble(String.format("%.3f", list.get(7) - q7)), Double.parseDouble(String.format("%.3f", list.get(7))), Double.parseDouble(String.format("%.3f", list.get(7) + q7)), Double.parseDouble(String.format("%.3f", BigDecimal.valueOf((24.0 / size) * (1 - (225.0 / (15 * size + 124)))).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()))),
                new MainCharactericticForData("Ексцес зсунений", 0, Double.parseDouble(String.format("%.3f", list.get(8))), 0, 0),
                new MainCharactericticForData("Контрексцес", Double.parseDouble(String.format("%.3f", list.get(9) - q9)), Double.parseDouble(String.format("%.3f", list.get(9))), Double.parseDouble(String.format("%.3f", BigDecimal.valueOf(list.get(9) + q9).setScale(4, BigDecimal.ROUND_CEILING).doubleValue())), Double.parseDouble(String.format("%.3f", BigDecimal.valueOf(Math.sqrt(list.get(8) / (29 * size)) * Math.pow(Math.pow(Math.abs((1 + Math.pow((double) list.get(8), 2))), 3), 0.25)).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()))),
                new MainCharactericticForData("Пірсона", Double.parseDouble(String.format("%.3f", list.get(10) - q10)), Double.parseDouble(String.format("%.3f", list.get(10))), Double.parseDouble(String.format("%.3f", BigDecimal.valueOf((double) list.get(10) + q10).setScale(4, BigDecimal.ROUND_CEILING).doubleValue())), Double.parseDouble(String.format("%.3f", BigDecimal.valueOf((double) list.get(10) * (Math.sqrt((1 + 2 * Math.pow((double) list.get(10), 2)) / (2 * size)))).setScale(4, BigDecimal.ROUND_CEILING).doubleValue()))),
                new MainCharactericticForData("MED", 0, Double.parseDouble(String.format("%.3f", list.get(11))), 0, 0),
                new MainCharactericticForData("MAD", 0, Double.parseDouble(String.format("%.3f", list.get(12))), 0, 0),
                new MainCharactericticForData("Непараметричний коефіцієнт варіації", 0, Double.parseDouble(String.format("%.3f", list.get(13))), 0, 0),
                new MainCharactericticForData("MED Уолша", 0, Double.parseDouble(String.format("%.3f", list.get(14))), 0, 0)
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

    //критерії однорідності
    //залежні вибірки:
    @FXML
    protected void tTestForDepens(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5, arrayListNumber6);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "Виберіть два чекбокси", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            String message = "";
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (arr1.size() != arr2.size()) {
                    JOptionPane.showMessageDialog(null, "Помилка! Різні розміри файлів", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    //f-test:
                    message += Helper.messageTtestForInDepends(arr1, arr2);
                    JOptionPane.showMessageDialog(null, message, "Критерії однорідності", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    @FXML
    protected void odnoFactDusAnalyze(ActionEvent event) throws Exception {
        String message = "";
        message += Helper.messageForOdnoFactorniyDuspersniyAnaliz(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5, arrayListNumber6);
        JOptionPane.showMessageDialog(null, message, "Критерії однорідності", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void kriteriiZnakiv(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5, arrayListNumber6);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "Виберіть два чекбокси", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            String message = "";
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (arr1.size() != arr2.size()) {
                    JOptionPane.showMessageDialog(null, "Помилка! Різні розміри файлів", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    //f-test:
                    message += Helper.messageForKriteriiZnakiv((ArrayList) arr1, (ArrayList) arr2);
                    JOptionPane.showMessageDialog(null, message, "Критерії однорідності", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    @FXML
    protected void QKriterii(ActionEvent event) throws Exception {
        String message = "";
        message += Helper.messageForQKohren(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5, arrayListNumber6);
        JOptionPane.showMessageDialog(null, message, "Критерії однорідності", JOptionPane.INFORMATION_MESSAGE);
    }

    //незалежні вибірки:
    @FXML
    protected void tTestForInDepens(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5, arrayListNumber6);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "Виберіть два чекбокси", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            String message = "";
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                //f-test:
                message += Helper.messageTtestForInDepends(arr1, arr2);
                JOptionPane.showMessageDialog(null, message, "Критерії однорідності", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @FXML
    protected void fTest(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5, arrayListNumber6);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "Виберіть два чекбокси", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            String message = "";
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                //f-test:
                message += Helper.messageFtestMessage(arr1, arr2);
                JOptionPane.showMessageDialog(null, message, "Критерії однорідності", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @FXML
    protected void bartlet(ActionEvent event) throws Exception {
        String message = "";
        message += Helper.messageForBartlet(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5, arrayListNumber6);
        JOptionPane.showMessageDialog(null, message, "Критерії однорідності", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void abbe(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5, arrayListNumber6);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "Виберіть два чекбокси", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            String message = "";
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                //f-test:
                message += Helper.messageForAbbe((ArrayList) arr1);
                JOptionPane.showMessageDialog(null, message, "Критерії однорідності", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @FXML
    protected void kolmogorovaSmirnova(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5, arrayListNumber6);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "Виберіть два чекбокси", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            String message = "";
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                //f-test:
                message += Helper.messageForKolmogorovaSmirnova((ArrayList) arr1, (ArrayList) arr2);
                JOptionPane.showMessageDialog(null, message, "Критерії однорідності", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    //рангові:
    @FXML
    protected void vilkoksona(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5, arrayListNumber6);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "Виберіть два чекбокси", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            String message = "";
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                //f-test:
                message += Helper.messageForVilksona(Helper.rangRow((ArrayList<Double>) arr1, (ArrayList<Double>) arr2));
                JOptionPane.showMessageDialog(null, message, "Критерії однорідності", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @FXML
    protected void riznSerRangiv(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5, arrayListNumber6);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "Виберіть два чекбокси", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            String message = "";
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                //f-test:
                message += Helper.messageForRiznSerednihRangiv(Helper.rangRow((ArrayList<Double>) arr1, (ArrayList<Double>) arr2));
                JOptionPane.showMessageDialog(null, message, "Критерії однорідності", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @FXML
    protected void Hkriterrii(ActionEvent event) throws Exception {
        String message = "";
        message += Helper.messageForHKruskalaUolis(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5, arrayListNumber6);
        JOptionPane.showMessageDialog(null, message, "Критерії однорідності", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void rangTable(ActionEvent event) {
        List listOfCheckBox = Helper.returnTwoCheckBox(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, arrayListNumber1, arrayListNumber2, arrayListNumber3, arrayListNumber4, arrayListNumber5, arrayListNumber6);
        if (listOfCheckBox.size() != 2) {
            JOptionPane.showMessageDialog(null, "Виберіть два чекбокси", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List arr1 = (List) listOfCheckBox.get(0);
            List arr2 = (List) listOfCheckBox.get(1);
            if (arr1.isEmpty() || arr2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Helper.showRangRowList(tableView, (ArrayList) arr1, (ArrayList) arr2);
            }
        }
    }

    //Про програму:
    @FXML
    protected void aboutMenu(ActionEvent event) {
        String message = "Program created by Oleksandr Pyvovar";
        JOptionPane.showMessageDialog(null, message, "About", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void quantityOfLists() {
        int quantity = 0;
        if (!arrayListNumber1.isEmpty()) {
            quantity++;
        }
        if (!arrayListNumber2.isEmpty()) {
            quantity++;
        }
        if (!arrayListNumber3.isEmpty()) {
            quantity++;
        }
        if (!arrayListNumber4.isEmpty()) {
            quantity++;
        }
        if (!arrayListNumber5.isEmpty()) {
            quantity++;
        }
        if (!arrayListNumber6.isEmpty()) {
            quantity++;
        }
        JOptionPane.showMessageDialog(null, String.format("Кількість ознак = %d", quantity), "Кількість ознак", JOptionPane.INFORMATION_MESSAGE);
    }

    //файл
    @FXML
    protected void changeMenuNumber1(ActionEvent event) {
        currentArrayList.clear();
        arrayList.clear();
        for (int i = 0; i < arrayListNumber1.size(); i++) {
            arrayList.add(arrayListNumber1.get(i));
        }
        if (!arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ви успішно обрали файл №1. Розмір файлу:" + arrayListNumber1.size(), "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "About", JOptionPane.ERROR_MESSAGE);
        }
        currentArrayList = new ArrayList(withoutSortingArrayListNumber1);
    }

    @FXML
    protected void changeMenuNumber2(ActionEvent event) {
        currentArrayList.clear();
        arrayList.clear();
        for (int i = 0; i < arrayListNumber2.size(); i++) {
            arrayList.add(arrayListNumber2.get(i));
        }
        if (!arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ви успішно обрали файл №2. Розмір файлу:" + arrayListNumber2.size(), "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "About", JOptionPane.ERROR_MESSAGE);
        }
        currentArrayList = new ArrayList(withoutSortingArrayListNumber2);
    }

    @FXML
    protected void changeMenuNumber3(ActionEvent event) {
        currentArrayList.clear();
        arrayList.clear();
        for (int i = 0; i < arrayListNumber3.size(); i++) {
            arrayList.add(arrayListNumber3.get(i));
        }
        if (!arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ви успішно обрали файл №3.Розмір файлу:" + arrayListNumber3.size(), "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "About", JOptionPane.ERROR_MESSAGE);
        }
        currentArrayList = new ArrayList(withoutSortingArrayListNumber3);
    }

    @FXML
    protected void changeMenuNumber4(ActionEvent event) {
        currentArrayList.clear();
        arrayList.clear();
        for (int i = 0; i < arrayListNumber4.size(); i++) {
            arrayList.add(arrayListNumber4.get(i));
        }
        if (!arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ви успішно обрали файл №4.Розмір файлу:" + arrayListNumber4.size(), "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "About", JOptionPane.ERROR_MESSAGE);
        }
        currentArrayList = new ArrayList(withoutSortingArrayListNumber4);
    }

    @FXML
    protected void changeMenuNumber5(ActionEvent event) {
        currentArrayList.clear();
        arrayList.clear();
        for (int i = 0; i < arrayListNumber5.size(); i++) {
            arrayList.add(arrayListNumber5.get(i));
        }
        if (!arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ви успішно обрали файл №5.Розмір файлу:" + arrayListNumber5.size(), "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "About", JOptionPane.ERROR_MESSAGE);
        }
        currentArrayList = new ArrayList(withoutSortingArrayListNumber5);
    }

    @FXML
    protected void changeMenuNumber6(ActionEvent event) {
        currentArrayList.clear();
        arrayList.clear();
        for (int i = 0; i < arrayListNumber6.size(); i++) {
            arrayList.add(arrayListNumber6.get(i));
        }
        if (!arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ви успішно обрали файл №6.Розмір файлу:" + arrayListNumber6.size(), "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "About", JOptionPane.ERROR_MESSAGE);
        }
        currentArrayList = new ArrayList(withoutSortingArrayListNumber6);
    }

    //двовимірні вибірки:
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
            JOptionPane.showMessageDialog(null, "Ви успішно обрали вибірку №1 i 2", "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "About", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Ви успішно обрали вибірку №1 i 3", "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "About", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Ви успішно обрали вибірку №2 i 3", "About", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "About", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    protected void readFullMenu(ActionEvent event) {
        arrayList.clear();
        for (int i = 0; i < arrayListGeneral.size(); i++) {
            arrayList.add(arrayListGeneral.get(i));
        }
        if (!arrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ви успішно обрали пряме зчитування", "About", JOptionPane.INFORMATION_MESSAGE);
            showVarRowList();
        } else {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу завантажте файл", "About", JOptionPane.ERROR_MESSAGE);
        }
    }

    //моделювання вибірок:
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
            JOptionPane.showMessageDialog(null, "Оберіть вірно параметри N, m та ρ", "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Оберіть вірно параметри N та ʎ", "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Оберіть вірно параметри", "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Оберіть вірно параметри", "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Оберіть вірно параметри", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    //Аналіз двовимірних даних:
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
            JOptionPane.showMessageDialog(null, "Помилка!Спершу виберіть завантажте файл і в розділі 'Файл'->'Двовимірне зчитування' оберіть коректний варіант(перші три опції)", "About", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, Helper.firstAnalyze(listOfLists.get(2), listOfLists.get(3)), "Первинний статистичний аналіз", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    @FXML
    protected void firstStaticAnalyzeAdekvat(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        if (listOfLists.size() != 4) {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу виберіть завантажте файл і в розділі 'Файл'->'Двовимірне зчитування' оберіть коректний варіант(перші три опції)", "About", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, Helper.firstAnalyzeAdekv(listOfLists.get(2), listOfLists.get(3)), "Первинний статистичний аналіз", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    @FXML
    protected void scatterChartForKorilationField1(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        if (listOfLists.size() != 4) {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу виберіть завантажте файл і в розділі 'Файл'->'Двовимірне зчитування' оберіть коректний варіант(перші три опції)", "About", JOptionPane.ERROR_MESSAGE);
        } else {
            Helper.drawScatterChartForKorilationField(scatterChartForKorilationField, xAxisForScatterChartForKorilationField, yAxisForScatterChartForKorilationField, listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3));
        }

    }

    @FXML
    protected void scatterChartForFrequencyOfHystograma1(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        if (listOfLists.size() != 4) {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу виберіть завантажте файл і в розділі 'Файл'->'Двовимірне зчитування' оберіть коректний варіант(перші три опції)", "About", JOptionPane.ERROR_MESSAGE);
        } else {
            Helper.drawScatterChartForFrequencyOfHystograma(scatterChartForFrequencyOfHystograma, xAxisForScatterChartForFrequencyOfHystograma, yAxisForScatterChartForFrequencyOfHystograma, listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3));
        }
    }

    @FXML
    protected void dataForKorilationAnalize(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        JOptionPane.showMessageDialog(null, Helper.korilationKoefData(listOfLists.get(2), listOfLists.get(3)), "Коефіцієнт кореляції", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void dataForLKoefKorilationVidnos(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        JOptionPane.showMessageDialog(null, Helper.korilationVidnoshenKoefData(listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3)), "Коефіцієнт кореляційного відношення", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void dataForRangKorilation(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        JOptionPane.showMessageDialog(null, Helper.rangOfKorilation(listOfLists.get(2), listOfLists.get(3)), "Рангова кореляція", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void dataForSpolTable(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        JOptionPane.showMessageDialog(null, Helper.koefOfSpolTable(listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3)), "Коефіцієнти сполучень таблиць", JOptionPane.INFORMATION_MESSAGE);
    }

    //додатково:
    @FXML
    protected void additionalForReturn(ActionEvent event) {
        SecondHelper secondHelper = new SecondHelper();
        var listOfLists = secondHelper.defineWhichCheckBoxChecked(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        var listOfWithoutSorted = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        var listOfSaved = secondHelper.defineWhichCheckBoxCheckedForSaved(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        for (int i = 0; i < listOfLists.size(); i++) {
            ArrayList<Double> currentList = (ArrayList<Double>) listOfLists.get(i);
            ArrayList<Double> currentWithoutSortedList = (ArrayList<Double>) listOfWithoutSorted.get(i);
            ArrayList<Double> currentSaved = (ArrayList<Double>) listOfSaved.get(i);
            currentList.clear();
            currentWithoutSortedList.clear();
            currentWithoutSortedList.addAll(currentSaved);
            currentList.addAll(currentSaved);
            currentList.sort(Comparator.naturalOrder());
        }

        JOptionPane.showMessageDialog(null, "Повернено", "About", JOptionPane.INFORMATION_MESSAGE);

    }

    @FXML
    protected void additionalStardantization() {
        SecondHelper secondHelper = new SecondHelper();
        var listOfLists = secondHelper.defineWhichCheckBoxChecked(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        var listOfWithoutSorted = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        for (var list : listOfLists) {
            double resultSA = MainFunction.matSpodivan(list);
            double serKva = MainFunction.serKva(list);
            list.replaceAll(a -> (a - resultSA) / serKva);
        }
        for (var list : listOfWithoutSorted) {
            double resultSA = MainFunction.matSpodivan(list);
            double serKva = MainFunction.serKva(list);
            list.replaceAll(a -> (a - resultSA) / serKva);
        }
        JOptionPane.showMessageDialog(null, "Збережено", "About", JOptionPane.INFORMATION_MESSAGE);
    }


    @FXML
    protected void additionalDeleteAnomal() {
        alfaForAnomalData = Double.parseDouble(anomalStr.getText());
        SecondHelper secondHelper = new SecondHelper();
        var listOfWithoutSorted = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        var listOfLists = secondHelper.defineWhichCheckBoxChecked(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        SecondHelper.deleteAnomalValue(listOfLists, listOfWithoutSorted);
        JOptionPane.showMessageDialog(null, "Збережено", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void additionalZsuvAndLogarifm(ActionEvent event) {
        SecondHelper secondHelper = new SecondHelper();
        var listOfLists = secondHelper.defineWhichCheckBoxChecked(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        var listOfWithoutSorted = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        for (var list : listOfLists) {
            double modulMin = Math.abs(list.get(0));
            list.replaceAll(a -> Math.log(a + modulMin + 0.01) + modulMin + 0.01);
        }
        for (var list : listOfWithoutSorted) {
            double modulMin = Math.abs(list.get(0));
            list.replaceAll(a -> Math.log(a + modulMin + 0.01) + modulMin + 0.01);
        }
        JOptionPane.showMessageDialog(null, "Збережено", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void centeruvan() {
        SecondHelper secondHelper = new SecondHelper();
//        var listOfLists = secondHelper.defineWhichCheckBoxChecked(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        var listOfWithoutSorted = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
//        for (var list : listOfLists) {
//            double resultSA = MainFunction.matSpodivan(list);
//            list.replaceAll(a -> (a - resultSA));
//        }
//        for (var list : listOfWithoutSorted) {
//            double resultSA = MainFunction.matSpodivan(list);
//            list.replaceAll(a -> (a - resultSA));
//        }
        SecondHelper.centruvanVl(listOfWithoutSorted);
        JOptionPane.showMessageDialog(null, "Збережено", "About", JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Помилка!Спершу виберіть завантажте файл і в розділі 'Файл'->'Двовимірне зчитування' оберіть коректний варіант(перші три опції)", "About", JOptionPane.ERROR_MESSAGE);
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
//            JOptionPane.showMessageDialog(null, "Помилка!Спершу виберіть завантажте файл і в розділі 'Файл'->'Двовимірне зчитування' оберіть коректний варіант(перші три опції)", "About", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Помилка!Спершу виберіть завантажте файл і в розділі 'Файл'->'Двовимірне зчитування' оберіть коректний варіант(перші три опції)", "About", JOptionPane.ERROR_MESSAGE);
        } else {
            Helper.drawLiniinaRegresiaTeila(listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3), scatterChartForKorilationField, xAxisForScatterChartForKorilationField, yAxisForScatterChartForKorilationField, aForT, bForT);
        }
    }

    @FXML
    protected void findDusperiu(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        if (listOfLists.size() != 4) {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу виберіть завантажте файл і в розділі 'Файл'->'Двовимірне зчитування' оберіть коректний варіант(перші три опції)", "About", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, Helper.checkDuspersia(listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3)), "About", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    protected void otherDetail(ActionEvent event) {
        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        if (listOfLists.size() != 4) {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу виберіть завантажте файл і в розділі 'Файл'->'Двовимірне зчитування' оберіть коректний варіант(перші три опції)", "About", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Помилка!Спершу виберіть завантажте файл і в розділі 'Файл'->'Двовимірне зчитування' оберіть коректний варіант(перші три опції)", "About", JOptionPane.ERROR_MESSAGE);
        } else {
            Helper.drawParabolRegresia(listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3), scatterChartForKorilationField, xAxisForScatterChartForKorilationField, yAxisForScatterChartForKorilationField, aForT, bForT, cForT);
        }
    }

    @FXML
    protected void scatterKvazilininaRegressia(ActionEvent event) {
        if (!aRegressia.getText().isEmpty()) {
            aForT = Double.parseDouble(aRegressia.getText());
        }
        if (!bRegressia.getText().isEmpty()) {
            bForT = Double.parseDouble(bRegressia.getText());
        }

        List<ArrayList> listOfLists = Helper.returnTwoListForDvomirnixVibirok(arrayListNumber1, arrayListNumber2, arrayListNumber3, withoutSortingArrayListNumber1, withoutSortingArrayListNumber2, withoutSortingArrayListNumber3, listForDvomirnixVibirok);
        if (listOfLists.size() != 4) {
            JOptionPane.showMessageDialog(null, "Помилка!Спершу виберіть завантажте файл і в розділі 'Файл'->'Двовимірне зчитування' оберіть коректний варіант(перші три опції)", "About", JOptionPane.ERROR_MESSAGE);
        } else {
            Helper.drawKvaziRegresia(listOfLists.get(0), listOfLists.get(1), listOfLists.get(2), listOfLists.get(3), scatterChartForKorilationField, xAxisForScatterChartForKorilationField, yAxisForScatterChartForKorilationField, aForT, bForT);
        }
    }

    //lab5:

    //вивести таблицю:
    @FXML
    protected void showInitialTable() {
        tableView.getItems().clear();
        tableView.getColumns().clear();
        SecondHelper secondHelper = new SecondHelper();
        try {
            secondHelper.showInitialTableHelper(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, tableView);
        } catch (IndexOutOfBoundsException e) {
        }
    }

    @FXML
    protected void firstStaticAnalizeForManyVibirok() {
        SecondHelper secondHelper = new SecondHelper();
        JOptionPane.showMessageDialog(null, secondHelper.firstStaticAnalizeForManyVibirokHelper(
                        secondHelper.defineWhichCheckBoxChecked(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6)),
                "Первинний статистичний аналіз",
                JOptionPane.INFORMATION_MESSAGE);
    }

    //сукупності
    @FXML
    protected void addSukupnost1() {
        sukupnist1.clear();
        SecondHelper secondHelper = new SecondHelper();
        sukupnist1 = secondHelper.rewriteListsForSukupnist(secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6));
        JOptionPane.showMessageDialog(null, "Вибірки були додані до сукупності №1", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void addSukupnost2() {
        sukupnist2.clear();
        SecondHelper secondHelper = new SecondHelper();
        sukupnist2 = secondHelper.rewriteListsForSukupnist(secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6));
        JOptionPane.showMessageDialog(null, "Вибірки були додані до сукупності №2", "About", JOptionPane.INFORMATION_MESSAGE);
    }

//    @FXML
//    protected void addSukupnost3() {
//        sukupnist3.clear();
//        SecondHelper secondHelper = new SecondHelper();
//        sukupnist3 = secondHelper.rewriteListsForSukupnist(secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6));
//        JOptionPane.showMessageDialog(null, "Вибірки були додані до сукупності №3", "About", JOptionPane.INFORMATION_MESSAGE);
//    }

    //Перевірка збігу параметрів
    @FXML
    protected void checkParametersOfSukupnists() {
        SecondHelper secondHelper = new SecondHelper();
        JOptionPane.showMessageDialog(null,
                secondHelper.checkParametersOfSukupnistsHelper(sukupnist1, sukupnist2),
                "Перевірка збігу параметрів",
                JOptionPane.INFORMATION_MESSAGE);
    }

    //Багатовимірний кореляційний аналіз
    //ДК матриця:
    @FXML
    protected void dkMatrix() {
        SecondHelper secondHelper = new SecondHelper();
        var listForDC = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        secondHelper.showMatrixInTableViewViaArray(tableView, MainFunction.findDCForDuspKovMatrixForManyVibirok(listForDC));
    }

    //кореляційна матриця
    @FXML
    protected void koreliationMatrix() {
        SecondHelper secondHelper = new SecondHelper();
        var listForR = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        secondHelper.showMatrixInTableViewViaArray(tableView, MainFunction.findRForDuspKovMatrixForManyVibirok(listForR));
    }

    //багатовимірний варіаційний ряд
    @FXML
    protected void showVarRowForMany() {
        SecondHelper secondHelper = new SecondHelper();
        var list = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        secondHelper.showVarRowForManyVibirok(tableView, list);
    }

    //вивести дані у виді таблиці
    @FXML
    protected void showTableForMany() {
        SecondHelper secondHelper = new SecondHelper();
        var listForR = secondHelper.defineWhichCheckBoxChecked(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        secondHelper.showMatrixInTableViewViaArray(tableView, secondHelper.returnArrayForManyVarRow(listForR));
    }

    //часткові кореляції
    @FXML
    protected void partKorilationForMany() {
        SecondHelper secondHelper = new SecondHelper();
        var list = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        secondHelper.showPartKorilation(tableView, list);
    }

    //множинні кореляції
    @FXML
    protected void fullKorilationForMany() {
        SecondHelper secondHelper = new SecondHelper();
        var list = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        secondHelper.showFullKorilation(tableView, list);
    }

    //Лінійна регресія
    @FXML
    protected void diagnosticDiagramVizual() {
        SecondHelper secondHelper = new SecondHelper();
        var listNotSorted = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        int intLinReg = Integer.parseInt(indexOfManyRegresia.getText());
        if (intLinReg < 1 || intLinReg > listNotSorted.size()) {
            JOptionPane.showMessageDialog(null, String.format("Y має бути від 1 до %d", listNotSorted.size()), "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Graphics.diagnosticDiagram(secondHelper.permutaionOfListsForRegressia(listNotSorted, intLinReg), scatterChartForFrequencyOfHystograma, xAxisForScatterChartForFrequencyOfHystograma, yAxisForScatterChartForFrequencyOfHystograma);
        }
    }

    @FXML
    protected void characteristicForManyLiniinaRegresia() {
        SecondHelper secondHelper = new SecondHelper();
        var listNotSorted = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        int intLinReg = Integer.parseInt(indexOfManyRegresia.getText());
        if (intLinReg < 1 || intLinReg > listNotSorted.size()) {
            JOptionPane.showMessageDialog(null, "Y має бути від 1 до 3", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (x1ForManyRegr.getText().isEmpty() || x2ForManyRegr.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, " Поля x1ForManyRegr і x2ForManyRegr мають бути заповнені", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, secondHelper.dataForManyLiniianaRegressia(secondHelper.permutaionOfListsForRegressia(listNotSorted, intLinReg), Integer.parseInt(x1ForManyRegr.getText()), Integer.parseInt(x2ForManyRegr.getText()), x3ForManyRegr, x4ForManyRegr, x5ForManyRegr), "Лінійна регресія", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    protected void liniinaRegresiaBasedOnGradient() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            Graphics.liniinaGradientVisual(listNotSorted, scatterChartForFrequencyOfHystograma, xAxisForScatterChartForFrequencyOfHystograma, yAxisForScatterChartForFrequencyOfHystograma);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //візуалізація:
    //Матриця діаграм розкиду
    @FXML
    protected void matrixRozkVizual() {
        SecondHelper secondHelper = new SecondHelper();
        var listNotSorted = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        var listSorted = secondHelper.defineWhichCheckBoxChecked(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        Graphics.matrixRozkid(listSorted, listNotSorted, labelKorilation12, labelKorilation13, labelKorilation14, labelKorilation15, labelKorilation16,
                labelKorilation23, labelKorilation24, labelKorilation25, labelKorilation26, labelKorilation34, labelKorilation35, labelKorilation36,
                labelKorilation45, labelKorilation46, labelKorilation56, barChartRozkid1, barChartRozkid2, barChartRozkid3, barChartRozkid4, barChartRozkid5, barChartRozkid6,
                scatterChartRozkid21, scatterChartRozkid31, scatterChartRozkid32, scatterChartRozkid41, scatterChartRozkid42, scatterChartRozkid43, scatterChartRozkid51,
                scatterChartRozkid52, scatterChartRozkid53, scatterChartRozkid54, scatterChartRozkid61, scatterChartRozkid62, scatterChartRozkid63, scatterChartRozkid64, scatterChartRozkid65,
                xAxisForScatterChartForRozkid21, yAxisForScatterChartForRozkid21, xAxisForScatterChartForRozkid31, yAxisForScatterChartForRozkid31, xAxisForScatterChartForRozkid32, yAxisForScatterChartForRozkid32, xAxisForScatterChartForRozkid41, yAxisForScatterChartForRozkid41,
                xAxisForScatterChartForRozkid42, yAxisForScatterChartForRozkid42, xAxisForScatterChartForRozkid43, yAxisForScatterChartForRozkid43, xAxisForScatterChartForRozkid51, yAxisForScatterChartForRozkid51, xAxisForScatterChartForRozkid52, yAxisForScatterChartForRozkid52, xAxisForScatterChartForRozkid53, yAxisForScatterChartForRozkid53, xAxisForScatterChartForRozkid54, yAxisForScatterChartForRozkid54,
                xAxisForScatterChartForRozkid61, yAxisForScatterChartForRozkid61, xAxisForScatterChartForRozkid62, yAxisForScatterChartForRozkid62, xAxisForScatterChartForRozkid63, yAxisForScatterChartForRozkid63, xAxisForScatterChartForRozkid64, yAxisForScatterChartForRozkid64, xAxisForScatterChartForRozkid65, yAxisForScatterChartForRozkid65);
    }

    //Паралельні координати
    @FXML
    protected void paralelKoordinatVizual() {
        SecondHelper secondHelper = new SecondHelper();
        var list = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        Graphics.drawParalelKoordinatVizual(lineChart, list);
    }

    //Теплова карта
    @FXML
    protected void teplovaMapVizual() {
        SecondHelper secondHelper = new SecondHelper();
        var list = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        HeatMapVisualization.launchHeatMapVisualization(list);
    }

    //Бульбашкова діаграма
    @FXML
    protected void bubbleVizual() {
        SecondHelper secondHelper = new SecondHelper();
        var listNotSorted = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        var listSorted = secondHelper.defineWhichCheckBoxChecked(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        Graphics.bubbleDiagram(listNotSorted, listSorted, bubbleChart, xAxisForBubbleChart, yAxisForBubbleChart);
    }

    //Гліф
    @FXML
    protected void glifVizual() {
        SecondHelper secondHelper = new SecondHelper();
        var listNotSorted = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        var listSorted = secondHelper.defineWhichCheckBoxChecked(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listSorted.size() == 2) {
            Glif.launchGlifVisualization(List.of(listSorted.get(0), listSorted.get(1), listNotSorted.get(0), listNotSorted.get(1)));
        } else {
            JOptionPane.showMessageDialog(null, "Кількість ознак має дорівнювати 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //lab6:
    //Компонентний та факторний аналіз
    @FXML
    protected void resultMGK() {
        SecondHelper secondHelper = new SecondHelper();
        var listNotSorted = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        SecondHelper.getMGKMatrix(tableView, secondHelper.centruvanData(listNotSorted));
    }

    @FXML
    protected void brokenStickGraph() {
        SecondHelper secondHelper = new SecondHelper();
        var listNotSorted = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        Graphics.brokenStickVizual(lineChart, secondHelper.centruvanData(listNotSorted));
    }

    @FXML
    protected void goToNezhalezhniSystemOfKoordinat() {
        SecondHelper secondHelper = new SecondHelper();
        var listNotSorted = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        var listSorted = secondHelper.defineWhichCheckBoxChecked(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        var newNotSortedList = secondHelper.goToNezhalezhniSystemOfKoordinatHelper(listNotSorted);

        for (int i = 0; i < listNotSorted.size(); i++) {
            var staticList = listNotSorted.get(i);
            var newStaticList = newNotSortedList.get(i);
            Collections.copy(staticList, newStaticList);
        }

        var tempList = new ArrayList<List<Double>>();
        for (int i = 0; i < listNotSorted.size(); i++) {
            var l = new ArrayList(listNotSorted.get(i));
            l.sort(Comparator.naturalOrder());
            tempList.add(l);
        }

        for (int i = 0; i < tempList.size(); i++) {
            var staticList = listSorted.get(i);
            var newStaticList = tempList.get(i);
            Collections.copy(staticList, newStaticList);
        }
        JOptionPane.showMessageDialog(null, "Збережено", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void returnToInitialSystemOfKoordinat() {
        SecondHelper secondHelper = new SecondHelper();
        var listNotSorted = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        var listSorted = secondHelper.defineWhichCheckBoxChecked(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        var newNotSortedList = secondHelper.returnToInitialSystemOfKoordinatHelper(listNotSorted, Integer.parseInt(returnInitialTextField.getText()));

        for (int i = 0; i < listNotSorted.size(); i++) {
            var staticList = listNotSorted.get(i);
            var newStaticList = newNotSortedList.get(i);
            Collections.copy(staticList, newStaticList);
        }

        var tempList = new ArrayList<List<Double>>();
        for (int i = 0; i < listNotSorted.size(); i++) {
            var l = new ArrayList(listNotSorted.get(i));
            l.sort(Comparator.naturalOrder());
            tempList.add(l);
        }

        for (int i = 0; i < tempList.size(); i++) {
            var staticList = listSorted.get(i);
            var newStaticList = tempList.get(i);
            Collections.copy(staticList, newStaticList);
        }
        JOptionPane.showMessageDialog(null, "Збережено", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    public void liniinaRoznomanitia() {
        var secondHelper = new SecondHelper();
        int intLinReg = Integer.parseInt(indexOfManyRegresia.getText());
        var listNotSorted = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (intLinReg < 1 || intLinReg > listNotSorted.size()) {
            JOptionPane.showMessageDialog(null, "Y має бути від 1 до 3", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, secondHelper.getFormulaForLiniinaRoznomanitia(secondHelper.permutaionOfListsForRegressia(listNotSorted, intLinReg)), "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    public void matrixFactor() {
        var secondHelper = new SecondHelper();
        var listNotSorted = secondHelper.defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        var matrixResult = new FactorHelper().findMatrixOfFactorDisplay(listNotSorted);
        JOptionPane.showMessageDialog(null, matrixResult, "Матриця факторного відображення", JOptionPane.INFORMATION_MESSAGE);
    }

    //часові ряди:
    @FXML
    public void mainCharactericticTimeRow() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            JOptionPane.showMessageDialog(null, timeRowHelper.getMainCharacteristics(listNotSorted.get(1)), "Головні характеристики", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void graphicTimeRow() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            Graphics.timeRowVisual(lineChart, xAxisForLineChart, yAxisForLineChart, listNotSorted);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void autokovariationTimeRow() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            timeRowHelper.autokovariation(lineChart, xAxisForLineChart, yAxisForLineChart, listNotSorted);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void autokorilationTimeRow() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            timeRowHelper.autoKorilation(lineChart, xAxisForLineChart, yAxisForLineChart, listNotSorted);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void clearGraphicTimeRow() {
        lineChart.getData().clear();
        lineChart.layout();
    }

    @FXML
    public void removeAnomalTimeRow() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            timeRowHelper.changeAnomalData(listNotSorted.get(1));
            JOptionPane.showMessageDialog(null, "Збережено", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //критерій серій:
    @FXML
    public void criteriiZnakivTimeRow() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            JOptionPane.showMessageDialog(null, timeRowHelper.criteriaZnakiv(listNotSorted.get(1)), "Критерій знаків", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void criteriiMannaTimeRow() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            JOptionPane.showMessageDialog(null, timeRowHelper.criteriaManna(listNotSorted.get(1)), "Критерій Манна", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void criteriiSeriesTimeRow() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            JOptionPane.showMessageDialog(null, timeRowHelper.criteriaSeries(listNotSorted.get(1)), "Критерій серій", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void criteriiSeriesOfGrowAndFallTimeRow() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            JOptionPane.showMessageDialog(null, timeRowHelper.criteriaSeriesOfGrowAndFall(listNotSorted.get(1)), "Критерій зростаючих та падаючих серій", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void criteriiAbbeTimeRow() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            JOptionPane.showMessageDialog(null, timeRowHelper.criteriaAbbe(listNotSorted.get(1)), "Критерій Аббе", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //тренд:
    @FXML
    public void liniinaTrendTimeRow() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            timeRowHelper.liniinaTrend(listNotSorted, lineChart);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void parabolTrendTimeRow() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            timeRowHelper.parabolTrend(listNotSorted, lineChart);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //згладжування:
    @FXML
    public void medianZgl() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            timeRowHelper.drawMedianZgl(lineChart, listNotSorted);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void smaZgl() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            timeRowHelper.smaZgl(lineChart, listNotSorted);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void emaZgl() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            timeRowHelper.emaZgl(lineChart, listNotSorted);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void dmaZgl() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            timeRowHelper.dmaZgl(lineChart, listNotSorted);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void tmaZgl() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            timeRowHelper.tmaZgl(lineChart, listNotSorted);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void mnkZgl() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            timeRowHelper.mnkZgl(lineChart, listNotSorted);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //метод Гусені
    @FXML
    public void decompositionMethodSSA() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            JOptionPane.showMessageDialog(null, timeRowHelper.decomposition(listNotSorted), "Декомпозиція", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void reconstructionMethodSSA() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            timeRowHelper.reconstruction(lineChart, listNotSorted);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    public void forecastingMethodSSA() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        if (listNotSorted.size() == 2) {
            timeRowHelper.forecacting(lineChart, listNotSorted);
        } else {
            JOptionPane.showMessageDialog(null, "Size must be 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //AI:
    @FXML
    public void findZalForNotPerehresne() {
        double n = Double.parseDouble(strModelN.getText());
        double min = Double.parseDouble(minRegressia.getText());
        double max = Double.parseDouble(maxRegressia.getText());
        double a = Double.parseDouble(aRegressia.getText());
        double b = Double.parseDouble(bRegressia.getText());
        double c = Double.parseDouble(cRegressia.getText());
        double dus = Double.parseDouble(duspersiaRegressia.getText());
        new AILab2().resultTableSZalForNotPerehresne(n, min, max, a, b, c, dus, tableView, lineChart);
    }

    @FXML
    public void findZalForPerehresne() {
        double n = Double.parseDouble(strModelN.getText());
        double min = Double.parseDouble(minRegressia.getText());
        double max = Double.parseDouble(maxRegressia.getText());
        double a = Double.parseDouble(aRegressia.getText());
        double b = Double.parseDouble(bRegressia.getText());
        double c = Double.parseDouble(cRegressia.getText());
        double dus = Double.parseDouble(duspersiaRegressia.getText());
        new AILab2().resultTableSZalForPerehresne(n, min, max, a, b, c, dus, tableView, lineChart);
    }

    //кластеризація
    @FXML
    public void graphKAverage() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        new Klastarization().graphicKlasterizationForKAverage(scatterChartForKorilationField, xAxisForScatterChartForKorilationField, yAxisForScatterChartForKorilationField, listNotSorted);
    }

    @FXML
    public void graphAglomirative() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        new Klastarization().graphicKlasterizationForAglomirative(scatterChartForKorilationField, xAxisForScatterChartForKorilationField, yAxisForScatterChartForKorilationField, listNotSorted);
    }

    @FXML
    public void gradeQualityAglomirative() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        JOptionPane.showMessageDialog(null, new Klastarization().getKlasterizationGradeOfKlastersForAglomirative(listNotSorted), "Оцінка якості кластеризації", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    public void gradeQualityKAverage() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        JOptionPane.showMessageDialog(null, new Klastarization().getKlasterizationGradeOfKlastersForKAverage(listNotSorted), "Оцінка якості кластеризації", JOptionPane.INFORMATION_MESSAGE);
    }

    //класифікація:
    @FXML
    public void methodNeighbourSimple() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        new Klasification().methodNeighbourhoodSimple(scatterChartForKorilationField, xAxisForScatterChartForKorilationField, yAxisForScatterChartForKorilationField, listNotSorted);
    }

    @FXML
    public void methodNeighbourModification() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        new Klasification().methodNeighbourhoodModification(scatterChartForKorilationField, xAxisForScatterChartForKorilationField, yAxisForScatterChartForKorilationField, listNotSorted);
    }

    @FXML
    public void methodKClosestNeighbour() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        new Klasification().methodNeighbourhoodForKQuantity(scatterChartForKorilationField, xAxisForScatterChartForKorilationField, yAxisForScatterChartForKorilationField, listNotSorted);
    }

    @FXML
    public void logisticRegression() {
        var listNotSorted = new SecondHelper().defineWhichCheckBoxCheckedForWithoutSorted(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
        new Klasification().logisticRegression(scatterChartForKorilationField, xAxisForScatterChartForKorilationField, yAxisForScatterChartForKorilationField, listNotSorted);
    }
}