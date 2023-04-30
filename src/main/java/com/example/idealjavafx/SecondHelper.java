package com.example.idealjavafx;

import com.example.idealjavafx.models.DataRowForFullKorilation;
import com.example.idealjavafx.models.DataRowForPartKorilation;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.util.*;

import static com.example.idealjavafx.HelloController.*;

//Helper from 5 lab
public class SecondHelper {
    private double pohibkaForcheckParametersOfSukupnistsHelperForFirstNum1 = 7.8;//should be deleted
    private double pohibkaForcheckParametersOfSukupnistsHelperForFirstNum2 = 12.6;//should be deleted

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
    public String checkParametersOfSukupnistsHelper(List<List<Double>> list1, List<List<Double>> list2) {
        if (list1.isEmpty() || list2.isEmpty()) {
            return "Error! list1 and list2 cannot be empty";
        } else {
            String str = checkParametersOfSukupnistsHelperForFirst(list1, list2);
            str += checkParametersOfSukupnistsHelperForSecondAndThird(list1, list2);
            return str;
        }
    }

    //method for checkParametersOfSukupnistsHelper
    private String checkParametersOfSukupnistsHelperForFirst(List<List<Double>> list1, List<List<Double>> list2) {
        int generalN = 2;//quantity of sukupnist
        double[][] dc0 = MainFunction.findDCForDuspKovMatrixForManyVibirok(list1);
        double[][] dc1 = MainFunction.findDCForDuspKovMatrixForManyVibirok(list2);
        double n0 = list1.size();
        double n1 = list2.size();
        double s0[][] = new double[dc0.length][dc0.length];
        double s1[][] = new double[dc1.length][dc1.length];

        for (int i = 0; i < dc0.length; i++) {
            for (int j = 0; j < dc0.length; j++) {
                s0[i][j] = findValueForSValue(i, j, dc0, dc1, n0, n1).get(0);
            }
        }

        for (int i = 0; i < dc1.length; i++) {
            for (int j = 0; j < dc1.length; j++) {
                s1[i][j] = findValueForSValue(i, j, dc0, dc1, n0, n1).get(1);
            }
        }

        double v = -(n0 + n1 - 2 - generalN) * Math.log(Math.abs(MainFunction.findDetermination(s0) / MainFunction.findDetermination(s1)));
        String str = "Рівність двох багатовимірних середніх у разі рівних ДК матриць:\n";
        str += String.format("V = %.3f, ", Math.abs(v));

        if (pohibkaForcheckParametersOfSukupnistsHelperForFirstNum1 >= Math.abs(v)) {
            str += "нульову гіпотезу підтверджено.\n";
        } else {
            str += "нульову гіпотезу відхилено.\n";
        }
        return str;
    }

    //method for checkParametersOfSukupnistsHelper
    private String checkParametersOfSukupnistsHelperForSecondAndThird(List<List<Double>> list1, List<List<Double>> list2) {
        int generalN = 2;//quantity of sukupnist
        List<List<Double>> xl1 = new ArrayList<>();
        List<List<Double>> xl2 = new ArrayList<>();
        List<Double> x_d1 = new ArrayList<>();
        List<Double> x_d2 = new ArrayList<>();
        int numLists1 = list1.size();
        int numElements1 = list1.get(0).size();
        int numLists2 = list2.size();
        int numElements2 = list2.get(0).size();
        for (int i = 0; i < numElements1; i++) {
            xl1.add(new ArrayList<>());
        }
        for (int i = 0; i < numElements2; i++) {
            xl2.add(new ArrayList<>());
        }

        for (int i = 0; i < numLists1; i++) {
            List<Double> innerList = list1.get(i);
            for (int j = 0; j < numElements1; j++) {
                Double element = innerList.get(j);
                xl1.get(j).add(element);
            }
        }
        for (int i = 0; i < numLists2; i++) {
            List<Double> innerList = list2.get(i);
            for (int j = 0; j < numElements2; j++) {
                Double element = innerList.get(j);
                xl2.get(j).add(element);
            }
        }

        for (int i = 0; i < list1.size(); i++) {
            x_d1.add(MainFunction.matSpodivan(list1.get(i)));
        }
        for (int i = 0; i < list2.size(); i++) {
            x_d2.add(MainFunction.matSpodivan(list2.get(i)));
        }

        double sd1[][] = new double[list1.size()][list1.size()];
        double matrix1[][] = new double[list1.size()][list1.size()];
        for (int i = 0; i < xl1.size(); i++) {
            double[][] xlMinusXd = new double[list1.size()][1];
            for (int j = 0; j < xl1.get(i).size(); j++) {
                xlMinusXd[j][0] = xl1.get(i).get(j) - x_d1.get(j);
            }
            if (i == 0) {
                matrix1 = MainFunction.multiplyMatrixOnMatrix(xlMinusXd, MainFunction.transposeMatrix(xlMinusXd));
            } else {
                var tempMatrix = MainFunction.multiplyMatrixOnMatrix(xlMinusXd, MainFunction.transposeMatrix(xlMinusXd));
                matrix1 = MainFunction.addingMatrixToMatrix(matrix1, tempMatrix);
            }
        }
        sd1 = MainFunction.multiplyMatrixOnDigit(matrix1, (double) 1 / (list1.get(0).size() - 1));

        double sd2[][] = new double[list2.size()][list2.size()];
        double matrix2[][] = new double[list2.size()][list2.size()];
        for (int i = 0; i < xl2.size(); i++) {
            double[][] xlMinusXd = new double[list2.size()][1];
            for (int j = 0; j < xl2.get(i).size(); j++) {
                xlMinusXd[j][0] = xl2.get(i).get(j) - x_d2.get(j);
            }
            if (i == 0) {
                matrix2 = MainFunction.multiplyMatrixOnMatrix(xlMinusXd, MainFunction.transposeMatrix(xlMinusXd));
            } else {
                var tempMatrix = MainFunction.multiplyMatrixOnMatrix(xlMinusXd, MainFunction.transposeMatrix(xlMinusXd));
                matrix2 = MainFunction.addingMatrixToMatrix(matrix2, tempMatrix);
            }
        }
        sd2 = MainFunction.multiplyMatrixOnDigit(matrix2, (double) 1 / (list1.get(0).size() - 1));


        double[][] x_d1Array = new double[x_d1.size()][1];
        double[][] x_d2Array = new double[x_d2.size()][1];
        for (int i = 0; i < x_d1.size(); i++) {
            x_d1Array[i][0] = x_d1.get(i);
        }
        for (int i = 0; i < x_d2.size(); i++) {
            x_d2Array[i][0] = x_d2.get(i);
        }
        var x_firstPart = MainFunction.getInverseMatrix(MainFunction.addingMatrixToMatrix(
                MainFunction.multiplyMatrixOnDigit(MainFunction.getInverseMatrix(sd1), list1.get(0).size()),
                MainFunction.multiplyMatrixOnDigit(MainFunction.getInverseMatrix(sd2), list2.get(0).size())));
        var x_SecondPart = MainFunction.addingMatrixToMatrix(MainFunction.multiplyMatrixOnMatrix(
                        MainFunction.multiplyMatrixOnDigit(MainFunction.getInverseMatrix(sd1), list1.get(0).size()), x_d1Array),
                MainFunction.multiplyMatrixOnMatrix(
                        MainFunction.multiplyMatrixOnDigit(MainFunction.getInverseMatrix(sd2), list2.get(0).size()), x_d2Array));
        var x_ = MainFunction.multiplyMatrixOnMatrix(x_firstPart, x_SecondPart);
        var xdMinusX_1 = MainFunction.subtractMatrix(x_d1Array, x_);
        var xdMinusX_2 = MainFunction.subtractMatrix(x_d2Array, x_);
        var v2 = MainFunction.addingMatrixToMatrix(
                MainFunction.multiplyMatrixOnMatrix(MainFunction.multiplyMatrixOnDigit(MainFunction.transposeMatrix(xdMinusX_1), list1.get(0).size()), MainFunction.multiplyMatrixOnMatrix(MainFunction.getInverseMatrix(sd1), xdMinusX_1)),
                MainFunction.multiplyMatrixOnMatrix(MainFunction.multiplyMatrixOnDigit(MainFunction.transposeMatrix(xdMinusX_2), list2.get(0).size()), MainFunction.multiplyMatrixOnMatrix(MainFunction.getInverseMatrix(sd2), xdMinusX_2)));

        String str = "Збіг ДК матриць:\n";
        str += String.format("V = %.3f \n", Math.abs(v2[0][0]));
        if (pohibkaForcheckParametersOfSukupnistsHelperForFirstNum2 >= Math.abs(v2[0][0])) {
            str += "нульову гіпотезу підтверджено.\n";
        } else {
            str += "нульову гіпотезу відхилено.\n";
        }

        double nGeneral = list1.get(0).size() + list2.get(0).size();
        double s[][] = new double[sd1[0].length][sd1[1].length];
        s = MainFunction.multiplyMatrixOnDigit(sd1, (list1.get(0).size() - 1));
        s = MainFunction.addingMatrixToMatrix(s, MainFunction.multiplyMatrixOnDigit(sd2, (list2.get(0).size() - 1)));
        s = MainFunction.multiplyMatrixOnDigit(s, (double) 1 / (nGeneral - generalN));

        str += "Збіг k-вимірних при розбіжності ДК матриць:\n";
        var v3 = (list1.get(0).size() - 1) / 2 * Math.log(MainFunction.findDetermination(s) / MainFunction.findDetermination(sd1)) +
                (list1.get(1).size() - 1) / 2 * Math.log(MainFunction.findDetermination(s) / MainFunction.findDetermination(sd2));
        str += String.format("V = %.3f \n", Math.abs(v3));
        if (pohibkaForcheckParametersOfSukupnistsHelperForFirstNum1 >= Math.abs(v3)) {
            str += "нульову гіпотезу підтверджено.\n";
        } else {
            str += "нульову гіпотезу відхилено.\n";
        }
        return str;
    }

    //method for checkParametersOfSukupnistsHelperForFirst
    private List<Double> findValueForSValue(int i, int j, double[][] dc0, double[][] dc1, double n0, double n1) {
        double tempXixj = 0;
        double tempYiYj = 0;
        double tempXi = 0;
        double tempXj = 0;
        double tempYi = 0;
        double tempYj = 0;
        for (int l = 0; l < n0; l++) {
            tempXixj += dc0[i][l] * dc0[j][l];
            tempXi += dc0[i][l];
            tempXj += dc0[j][l];
        }
        for (int l = 0; l < n1; l++) {
            tempYiYj += dc1[i][l] * dc1[j][l];
            tempYi += dc1[i][l];
            tempYj += dc1[j][l];
        }
        double s0 = (tempXixj + tempYiYj - (tempXi + tempYi) * (tempXj + tempYj) / (n0 + n1)) / (n0 + n1 - 2);
        double s1 = (tempXixj + tempYiYj - tempXi * tempXj / n0 - tempYi * tempYj / n1) / (n0 + n1 - 2);
        return List.of(s0, s1);
    }

    //відображення матриці в TableView
    public void showMatrixInTableViewViaArray(TableView tableView, double[][] array) {
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
    public static void deleteAnomalValue(List<List<Double>> listSorted, List<List<Double>> withoutSorted) {
        var numOfClass = (int) Math.cbrt(listSorted.get(0).size());
        int size = listSorted.get(0).size();
        double lastElement = listSorted.get(0).get(size - 1);
        int frequency = 0;
        double step = (listSorted.get(0).get(size - 1) - listSorted.get(0).get(0)) / numOfClass;
        double start = listSorted.get(0).get(0);
        double end = listSorted.get(0).get(0) + step;
        List<Double> rubish = new ArrayList<>();
        while (lastElement > start) {
            for (Double value : listSorted.get(0)) {
                if (value >= start && value < end) {
                    frequency++;
                    rubish.add(value);
                }
            }

            if ((double) frequency / size <= alfaForAnomalData) {
                listSorted.get(0).removeAll(rubish);
            }
            start = end;
            end += step;
            rubish.clear();
            frequency = 0;
        }

        for (int i = 0; i < withoutSorted.get(0).size(); i++) {
            if (!listSorted.get(0).contains(withoutSorted.get(0).get(i))) {
                for (int j = 0; j < withoutSorted.size(); j++) {
                    withoutSorted.get(j).remove(i);
                }
            }
        }

        for (int i = 0; i < listSorted.size(); i++) {
            for (int j = 0; j < listSorted.get(i).size(); j++) {
                if (!withoutSorted.get(i).contains(listSorted.get(i).get(j))) {
                    listSorted.get(i).remove(j);
                }
            }
        }

    }

    public void showVarRowForManyVibirok(TableView tableView, List<List<Double>> list) {
        int sizeOfList = list.size();
        int numOfClass = (int) Math.cbrt(list.get(0).size());
        double array[][] = new double[(int) Math.pow(numOfClass, sizeOfList)][2 + sizeOfList];
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        if (sizeOfList == 0 || sizeOfList == 1 || sizeOfList == 2) {
            JOptionPane.showMessageDialog(null, "Виберіть що найменше три вибірки в багатовимірному режимі", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (sizeOfList == 3) {
            var sortedListX = new ArrayList<Double>();
            var sortedListY = new ArrayList<Double>();
            var sortedListZ = new ArrayList<Double>();

            var dots = new ArrayList<List<Double>>();
            for (int i = 0; i < list.get(0).size(); i++) {
                var tempList = new ArrayList<Double>();
                tempList.add(list.get(0).get(i));
                tempList.add(list.get(1).get(i));
                tempList.add(list.get(2).get(i));
                sortedListX.add(list.get(0).get(i));
                sortedListY.add(list.get(1).get(i));
                sortedListZ.add(list.get(2).get(i));
                dots.add(new ArrayList<>(tempList));
                tempList.clear();
            }
            sortedListX.sort(Comparator.naturalOrder());
            sortedListY.sort(Comparator.naturalOrder());
            sortedListZ.sort(Comparator.naturalOrder());

            double stepX = (sortedListX.get(sortedListX.size() - 1) - sortedListX.get(0)) / numOfClass;
            double stepY = (sortedListY.get(sortedListY.size() - 1) - sortedListY.get(0)) / numOfClass;
            double stepZ = (sortedListZ.get(sortedListZ.size() - 1) - sortedListZ.get(0)) / numOfClass;
            double rangeStartForX = sortedListX.get(0);
            double rangeEndForX = rangeStartForX + stepX;
            double rangeStartForY = sortedListY.get(0);
            double rangeEndForY = rangeStartForY + stepY;
            double rangeStartForZ = sortedListZ.get(0);
            double rangeEndForZ = rangeStartForZ + stepZ;
            var tempOfFrequency = 0;
            for (int x = 0; x < numOfClass; x++) {//x
                for (int y = 0; y < numOfClass; y++) {//y
                    for (int z = 0; z < numOfClass; z++) {//z
                        for (int index = 0; index < dots.size(); index++) {
                            if (dots.get(index).get(0) < rangeEndForX && dots.get(index).get(0) > rangeStartForX &&
                                    dots.get(index).get(1) < rangeEndForY && dots.get(index).get(1) > rangeStartForY &&
                                    dots.get(index).get(2) < rangeEndForZ && dots.get(index).get(2) > rangeStartForZ) {
                                tempOfFrequency++;
                            }
                            if (index == dots.size() - 1) {
                                //обновляем Z
                                linkedHashMap.put(String.format("%d, %d, %d", x + 1, y + 1, z + 1), String.format("(%d, %.3f)", tempOfFrequency, (double) tempOfFrequency / sortedListX.size()));
                                tempOfFrequency = 0;
                                rangeStartForZ = rangeEndForZ;
                                rangeEndForZ += stepZ;
                            }
                        }
                    }
                    //обновляем Y и возвращаем Z
                    rangeStartForY = rangeEndForY;
                    rangeEndForY += stepY;
                    rangeStartForZ = sortedListZ.get(0);
                    rangeEndForZ = rangeStartForZ + stepZ;
                }
                //обновляем X и возвращаем Y
                rangeStartForX = rangeEndForX;
                rangeEndForX += stepX;
                rangeStartForY = sortedListY.get(0);
                rangeStartForY = rangeStartForY + stepY;
            }
        } else if (sizeOfList == 4) {
            var sortedListK = new ArrayList<Double>();
            var sortedListX = new ArrayList<Double>();
            var sortedListY = new ArrayList<Double>();
            var sortedListZ = new ArrayList<Double>();

            var dots = new ArrayList<List<Double>>();
            for (int i = 0; i < list.get(0).size(); i++) {
                var tempList = new ArrayList<Double>();
                tempList.add(list.get(0).get(i));
                tempList.add(list.get(1).get(i));
                tempList.add(list.get(2).get(i));
                tempList.add(list.get(3).get(i));
                sortedListK.add(list.get(0).get(i));
                sortedListX.add(list.get(1).get(i));
                sortedListY.add(list.get(2).get(i));
                sortedListZ.add(list.get(3).get(i));
                dots.add(new ArrayList<>(tempList));
                tempList.clear();
            }
            sortedListK.sort(Comparator.naturalOrder());
            sortedListX.sort(Comparator.naturalOrder());
            sortedListY.sort(Comparator.naturalOrder());
            sortedListZ.sort(Comparator.naturalOrder());

            double stepK = (sortedListK.get(sortedListK.size() - 1) - sortedListK.get(0)) / numOfClass;
            double stepX = (sortedListX.get(sortedListX.size() - 1) - sortedListX.get(0)) / numOfClass;
            double stepY = (sortedListY.get(sortedListY.size() - 1) - sortedListY.get(0)) / numOfClass;
            double stepZ = (sortedListZ.get(sortedListZ.size() - 1) - sortedListZ.get(0)) / numOfClass;
            double rangeStartForK = sortedListK.get(0);
            double rangeEndForK = rangeStartForK + stepK;
            double rangeStartForX = sortedListX.get(0);
            double rangeEndForX = rangeStartForX + stepX;
            double rangeStartForY = sortedListY.get(0);
            double rangeEndForY = rangeStartForY + stepY;
            double rangeStartForZ = sortedListZ.get(0);
            double rangeEndForZ = rangeStartForZ + stepZ;
            var tempOfFrequency = 0;
            for (int k = 0; k < numOfClass; k++) {//k
                for (int x = 0; x < numOfClass; x++) {//x
                    for (int y = 0; y < numOfClass; y++) {//y
                        for (int z = 0; z < numOfClass; z++) {//z
                            for (int index = 0; index < dots.size(); index++) {
                                if (dots.get(index).get(0) < rangeEndForK && dots.get(index).get(0) > rangeStartForK &&
                                        dots.get(index).get(1) < rangeEndForX && dots.get(index).get(1) > rangeStartForX &&
                                        dots.get(index).get(2) < rangeEndForY && dots.get(index).get(2) > rangeStartForY &&
                                        dots.get(index).get(3) < rangeEndForZ && dots.get(index).get(3) > rangeStartForZ) {
                                    tempOfFrequency++;
                                }
                                if (index == dots.size() - 1) {
                                    //обновляем Z
                                    linkedHashMap.put(String.format("%d, %d, %d, %d", k + 1, x + 1, y + 1, z + 1), String.format("(%d, %.3f)", tempOfFrequency, (double) tempOfFrequency / sortedListX.size()));
                                    tempOfFrequency = 0;
                                    rangeStartForZ = rangeEndForZ;
                                    rangeEndForZ += stepZ;
                                }
                            }
                        }
                        //обновляем Y и возвращаем Z
                        rangeStartForY = rangeEndForY;
                        rangeEndForY += stepY;
                        rangeStartForZ = sortedListZ.get(0);
                        rangeEndForZ = rangeStartForZ + stepZ;
                    }
                    //обновляем X и возвращаем Y
                    rangeStartForX = rangeEndForX;
                    rangeEndForX += stepX;
                    rangeStartForY = sortedListY.get(0);
                    rangeStartForY = rangeStartForY + stepY;
                }
                rangeStartForK = rangeEndForK;
                rangeEndForK += stepK;
                rangeStartForX = sortedListX.get(0);
                rangeStartForX = rangeStartForX + stepX;
            }
        } else if (sizeOfList == 5) {
            var sortedListP = new ArrayList<Double>();
            var sortedListK = new ArrayList<Double>();
            var sortedListX = new ArrayList<Double>();
            var sortedListY = new ArrayList<Double>();
            var sortedListZ = new ArrayList<Double>();

            var dots = new ArrayList<List<Double>>();
            for (int i = 0; i < list.get(0).size(); i++) {
                var tempList = new ArrayList<Double>();
                tempList.add(list.get(0).get(i));
                tempList.add(list.get(1).get(i));
                tempList.add(list.get(2).get(i));
                tempList.add(list.get(3).get(i));
                tempList.add(list.get(4).get(i));
                sortedListP.add(list.get(0).get(i));
                sortedListK.add(list.get(1).get(i));
                sortedListX.add(list.get(2).get(i));
                sortedListY.add(list.get(3).get(i));
                sortedListZ.add(list.get(4).get(i));
                dots.add(new ArrayList<>(tempList));
                tempList.clear();
            }
            sortedListP.sort(Comparator.naturalOrder());
            sortedListK.sort(Comparator.naturalOrder());
            sortedListX.sort(Comparator.naturalOrder());
            sortedListY.sort(Comparator.naturalOrder());
            sortedListZ.sort(Comparator.naturalOrder());

            double stepP = (sortedListP.get(sortedListP.size() - 1) - sortedListP.get(0)) / numOfClass;
            double stepK = (sortedListK.get(sortedListK.size() - 1) - sortedListK.get(0)) / numOfClass;
            double stepX = (sortedListX.get(sortedListX.size() - 1) - sortedListX.get(0)) / numOfClass;
            double stepY = (sortedListY.get(sortedListY.size() - 1) - sortedListY.get(0)) / numOfClass;
            double stepZ = (sortedListZ.get(sortedListZ.size() - 1) - sortedListZ.get(0)) / numOfClass;
            double rangeStartForP = sortedListP.get(0);
            double rangeEndForP = rangeStartForP + stepP;
            double rangeStartForK = sortedListK.get(0);
            double rangeEndForK = rangeStartForK + stepK;
            double rangeStartForX = sortedListX.get(0);
            double rangeEndForX = rangeStartForX + stepX;
            double rangeStartForY = sortedListY.get(0);
            double rangeEndForY = rangeStartForY + stepY;
            double rangeStartForZ = sortedListZ.get(0);
            double rangeEndForZ = rangeStartForZ + stepZ;
            var tempOfFrequency = 0;
            for (int p = 0; p < numOfClass; p++) {//p
                for (int k = 0; k < numOfClass; k++) {//k
                    for (int x = 0; x < numOfClass; x++) {//x
                        for (int y = 0; y < numOfClass; y++) {//y
                            for (int z = 0; z < numOfClass; z++) {//z
                                for (int index = 0; index < dots.size(); index++) {
                                    if (dots.get(index).get(0) < rangeEndForP && dots.get(index).get(0) > rangeStartForP &&
                                            dots.get(index).get(1) < rangeEndForK && dots.get(index).get(1) > rangeStartForK &&
                                            dots.get(index).get(2) < rangeEndForX && dots.get(index).get(2) > rangeStartForX &&
                                            dots.get(index).get(3) < rangeEndForY && dots.get(index).get(3) > rangeStartForY &&
                                            dots.get(index).get(4) < rangeEndForZ && dots.get(index).get(4) > rangeStartForZ) {
                                        tempOfFrequency++;
                                    }
                                    if (index == dots.size() - 1) {
                                        //обновляем Z
                                        linkedHashMap.put(String.format("%d, %d, %d, %d, %d", p + 1, k + 1, x + 1, y + 1, z + 1), String.format("(%d, %.3f)", (double) tempOfFrequency, tempOfFrequency / sortedListX.size()));
                                        tempOfFrequency = 0;
                                        rangeStartForZ = rangeEndForZ;
                                        rangeEndForZ += stepZ;
                                    }
                                }
                            }
                            //обновляем Y и возвращаем Z
                            rangeStartForY = rangeEndForY;
                            rangeEndForY += stepY;
                            rangeStartForZ = sortedListZ.get(0);
                            rangeEndForZ = rangeStartForZ + stepZ;
                        }
                        //обновляем X и возвращаем Y
                        rangeStartForX = rangeEndForX;
                        rangeEndForX += stepX;
                        rangeStartForY = sortedListY.get(0);
                        rangeStartForY = rangeStartForY + stepY;
                    }
                    rangeStartForK = rangeEndForK;
                    rangeEndForK += stepK;
                    rangeStartForX = sortedListX.get(0);
                    rangeStartForX = rangeStartForX + stepX;
                }
                rangeStartForP = rangeEndForP;
                rangeEndForP += stepP;
                rangeStartForK = sortedListK.get(0);
                rangeStartForK = rangeStartForK + stepK;
            }
        } else if (sizeOfList == 6) {
            var sortedListL = new ArrayList<Double>();
            var sortedListP = new ArrayList<Double>();
            var sortedListK = new ArrayList<Double>();
            var sortedListX = new ArrayList<Double>();
            var sortedListY = new ArrayList<Double>();
            var sortedListZ = new ArrayList<Double>();

            var dots = new ArrayList<List<Double>>();
            for (int i = 0; i < list.get(0).size(); i++) {
                var tempList = new ArrayList<Double>();
                tempList.add(list.get(0).get(i));
                tempList.add(list.get(1).get(i));
                tempList.add(list.get(2).get(i));
                tempList.add(list.get(3).get(i));
                tempList.add(list.get(4).get(i));
                tempList.add(list.get(5).get(i));
                sortedListL.add(list.get(0).get(i));
                sortedListP.add(list.get(1).get(i));
                sortedListK.add(list.get(2).get(i));
                sortedListX.add(list.get(3).get(i));
                sortedListY.add(list.get(4).get(i));
                sortedListZ.add(list.get(5).get(i));
                dots.add(new ArrayList<>(tempList));
                tempList.clear();
            }
            sortedListL.sort(Comparator.naturalOrder());
            sortedListP.sort(Comparator.naturalOrder());
            sortedListK.sort(Comparator.naturalOrder());
            sortedListX.sort(Comparator.naturalOrder());
            sortedListY.sort(Comparator.naturalOrder());
            sortedListZ.sort(Comparator.naturalOrder());

            double stepL = (sortedListL.get(sortedListL.size() - 1) - sortedListL.get(0)) / numOfClass;
            double stepP = (sortedListP.get(sortedListP.size() - 1) - sortedListP.get(0)) / numOfClass;
            double stepK = (sortedListK.get(sortedListK.size() - 1) - sortedListK.get(0)) / numOfClass;
            double stepX = (sortedListX.get(sortedListX.size() - 1) - sortedListX.get(0)) / numOfClass;
            double stepY = (sortedListY.get(sortedListY.size() - 1) - sortedListY.get(0)) / numOfClass;
            double stepZ = (sortedListZ.get(sortedListZ.size() - 1) - sortedListZ.get(0)) / numOfClass;
            double rangeStartForL = sortedListL.get(0);
            double rangeEndForL = rangeStartForL + stepL;
            double rangeStartForP = sortedListP.get(0);
            double rangeEndForP = rangeStartForP + stepP;
            double rangeStartForK = sortedListK.get(0);
            double rangeEndForK = rangeStartForK + stepK;
            double rangeStartForX = sortedListX.get(0);
            double rangeEndForX = rangeStartForX + stepX;
            double rangeStartForY = sortedListY.get(0);
            double rangeEndForY = rangeStartForY + stepY;
            double rangeStartForZ = sortedListZ.get(0);
            double rangeEndForZ = rangeStartForZ + stepZ;
            var tempOfFrequency = 0;
            for (int l = 0; l < numOfClass; l++) {//l
                for (int p = 0; p < numOfClass; p++) {//p
                    for (int k = 0; k < numOfClass; k++) {//k
                        for (int x = 0; x < numOfClass; x++) {//x
                            for (int y = 0; y < numOfClass; y++) {//y
                                for (int z = 0; z < numOfClass; z++) {//z
                                    for (int index = 0; index < dots.size(); index++) {
                                        if (dots.get(index).get(0) < rangeEndForL && dots.get(index).get(0) > rangeStartForL &&
                                                dots.get(index).get(1) < rangeEndForP && dots.get(index).get(1) > rangeStartForP &&
                                                dots.get(index).get(2) < rangeEndForK && dots.get(index).get(2) > rangeStartForK &&
                                                dots.get(index).get(3) < rangeEndForX && dots.get(index).get(3) > rangeStartForX &&
                                                dots.get(index).get(4) < rangeEndForY && dots.get(index).get(4) > rangeStartForY &&
                                                dots.get(index).get(5) < rangeEndForZ && dots.get(index).get(5) > rangeStartForZ) {
                                            tempOfFrequency++;
                                        }
                                        if (index == dots.size() - 1) {
                                            //обновляем Z
                                            linkedHashMap.put(String.format("%d, %d, %d, %d, %d, %d", l + 1, p + 1, k + 1, x + 1, y + 1, z + 1), String.format("(%d, %.3f)", (double) tempOfFrequency, tempOfFrequency / sortedListX.size()));
                                            tempOfFrequency = 0;
                                            rangeStartForZ = rangeEndForZ;
                                            rangeEndForZ += stepZ;
                                        }
                                    }
                                }
                                //обновляем Y и возвращаем Z
                                rangeStartForY = rangeEndForY;
                                rangeEndForY += stepY;
                                rangeStartForZ = sortedListZ.get(0);
                                rangeEndForZ = rangeStartForZ + stepZ;
                            }
                            //обновляем X и возвращаем Y
                            rangeStartForX = rangeEndForX;
                            rangeEndForX += stepX;
                            rangeStartForY = sortedListY.get(0);
                            rangeStartForY += rangeStartForY + stepY;
                        }
                        rangeStartForK = rangeEndForK;
                        rangeEndForK += stepK;
                        rangeStartForX = sortedListX.get(0);
                        rangeStartForX = rangeStartForX + stepX;
                    }
                    rangeStartForP = rangeEndForP;
                    rangeEndForP += stepP;
                    rangeStartForK = sortedListK.get(0);
                    rangeStartForK = rangeStartForK + stepK;
                }
                rangeStartForL = rangeEndForL;
                rangeEndForL += stepL;
                rangeStartForP = sortedListP.get(0);
                rangeStartForP = rangeStartForP + stepP;
            }
        }

        //filling table
        if (!linkedHashMap.isEmpty()) {
            tableView.getItems().clear();
            tableView.getColumns().clear();

            TableColumn<Map.Entry<String, String>, String> string1Column = new TableColumn<>("Індекси");
            string1Column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));

            TableColumn<Map.Entry<String, String>, String> string2Column = new TableColumn<>("Значення(n, p)");
            string2Column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));
            tableView.getColumns().add(string1Column);
            tableView.getColumns().add(string2Column);
            ObservableList<Map.Entry<String, String>> data = FXCollections.observableArrayList(linkedHashMap.entrySet());
            tableView.setItems(data);
        }
    }

    //for korilation methods
    public static List<List<Integer>> generatePermutations(int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n == 1) {
            result.add(new ArrayList<>());
            result.get(0).add(1);
            return result;
        }

        List<List<Integer>> prevResult = generatePermutations(n - 1);
        int num = n;

        for (List<Integer> prevList : prevResult) {
            for (int i = 0; i <= n - 1; i++) {
                List<Integer> newList = new ArrayList<>(prevList);
                newList.add(i, num);
                if (!result.contains(newList)) {
                    result.add(newList);
                }
            }
            prevList.add(num);
            if (!result.contains(prevList)) {
                result.add(prevList);
            }
        }

        return result;
    }

    public void showPartKorilation(TableView tableView, List<List<Double>> list) {
        var lisrPermut = generatePermutations(list.size());
        LinkedHashMap<String, Double> linkedHashMap = new LinkedHashMap<>();
        if (list.size() == 3) {
            for (int i = 0; i < lisrPermut.size(); i++) {
                linkedHashMap.put(String.format("%d, %d - %d", lisrPermut.get(i).get(0), lisrPermut.get(i).get(1), lisrPermut.get(i).get(2)), MainFunction.getKoefKorilationForThreeValue(list.get(lisrPermut.get(i).get(0) - 1), list.get(lisrPermut.get(i).get(1) - 1), list.get(lisrPermut.get(i).get(2) - 1)));
            }
        } else if (list.size() == 4) {
            for (int i = 0; i < lisrPermut.size(); i++) {
                linkedHashMap.put(String.format("%d, %d - {%d, %d}", lisrPermut.get(i).get(0), lisrPermut.get(i).get(1), lisrPermut.get(i).get(2), lisrPermut.get(i).get(3)), MainFunction.getKoefKorilationForFourValue(list.get(lisrPermut.get(i).get(0) - 1), list.get(lisrPermut.get(i).get(1) - 1), list.get(lisrPermut.get(i).get(3) - 1), list.get(lisrPermut.get(i).get(3) - 1)));
            }
        } else if (list.size() == 5) {
            for (int i = 0; i < lisrPermut.size(); i++) {
                linkedHashMap.put(String.format("%d, %d - {%d, %d, %d}", lisrPermut.get(i).get(0), lisrPermut.get(i).get(1), lisrPermut.get(i).get(2), lisrPermut.get(i).get(3), lisrPermut.get(i).get(4)), MainFunction.getKoefKorilationForFiveValue(list.get(lisrPermut.get(i).get(0) - 1), list.get(lisrPermut.get(i).get(1) - 1), list.get(lisrPermut.get(i).get(3) - 1), list.get(lisrPermut.get(i).get(3) - 1), list.get(lisrPermut.get(i).get(4) - 1)));
            }
        } else {
            JOptionPane.showMessageDialog(null, "Виберіть кількість вибірок від 3 до 5", "Error", JOptionPane.ERROR_MESSAGE);
        }

        tableView.getItems().clear();
        tableView.getColumns().clear();

        TableColumn columnIndex = new TableColumn("Індекси");
        columnIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        TableColumn columnForMinusValue = new TableColumn("Нижня межа");
        columnForMinusValue.setCellValueFactory(new PropertyValueFactory<>("lowValue"));
        TableColumn columnForValue = new TableColumn("Значення");
        columnForValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        TableColumn columnForPlusValue = new TableColumn("Верхня межа");
        columnForPlusValue.setCellValueFactory(new PropertyValueFactory<>("highValue"));
        TableColumn columnForT = new TableColumn("t статистика");
        columnForT.setCellValueFactory(new PropertyValueFactory<>("tValue"));
        TableColumn columnForZna = new TableColumn("Значущість");
        columnForZna.setCellValueFactory(new PropertyValueFactory<>("zna"));

        //Adding data to the table
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.getColumns().addAll(columnIndex, columnForMinusValue, columnForValue, columnForPlusValue, columnForT, columnForZna);

        String key = null;
        double lowValue;
        double value;
        double highValue;
        double tValue;
        String zna;
        ObservableList<DataRowForPartKorilation> data = FXCollections.observableArrayList();
        for (int i = 0; i < lisrPermut.size(); i++) {
            DataRowForPartKorilation row = new DataRowForPartKorilation();
            if (lisrPermut.get(i).size() == 3) {
                key = String.format("%d, %d - %d", lisrPermut.get(i).get(0), lisrPermut.get(i).get(1), lisrPermut.get(i).get(2));
            } else if (lisrPermut.get(i).size() == 4) {
                key = String.format("%d, %d - {%d, %d}", lisrPermut.get(i).get(0), lisrPermut.get(i).get(1), lisrPermut.get(i).get(2), lisrPermut.get(i).get(3));
            } else if (lisrPermut.get(i).size() == 5) {
                key = String.format("%d, %d - {%d, %d, %d}", lisrPermut.get(i).get(0), lisrPermut.get(i).get(1), lisrPermut.get(i).get(2), lisrPermut.get(i).get(3), lisrPermut.get(i).get(4));
            }
            value = linkedHashMap.get(key);
            tValue = Math.abs(value * Math.sqrt(list.get(0).size() - list.size() - 4) / Math.sqrt(1 - Math.pow(value, 2)));
            double u = 3;
            double v1 = 0.5 * Math.log((1 + value) / (1 - value)) - u/(list.get(0).size()-list.size()-5);
            double v2 = 0.5 * Math.log((1 + value) / (1 - value)) + u/(list.get(0).size()-list.size()-5);
            lowValue = (Math.exp(2 * v1) - 1) / (Math.exp(2 * v1) + 1);
            highValue = (Math.exp(2 * v2) - 1) / (Math.exp(2 * v2) + 1);
            row.setIndex(key);
            row.setLowValue(String.format("%.3f", lowValue));
            row.setValue(String.format("%.3f", value));
            row.setHighValue(String.format("%.3f", highValue));
            row.setTValue(String.format("%.3f", tValue));
            if(Helper.t1> Math.abs(tValue)) {
                row.setZna("+");
            }else{
                row.setZna("-");
            }
            data.add(row);
        }
        tableView.getItems().addAll(data);
    }

    public void showFullKorilation(TableView tableView, List<List<Double>> list) {
        LinkedHashMap<String, Double> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            List<List<Double>> sublistBefore = list.subList(0, i);
            List<List<Double>> sublistAfter = list.subList(i + 1, list.size());
            List<List<Double>> newList = new ArrayList<>(sublistBefore);
            newList.addAll(sublistAfter);
            double r = MainFunction.getMnozhinKoefKorilation(list, newList);
            linkedHashMap.put(String.format("%d", i + 1), r);
        }


        tableView.getItems().clear();
        tableView.getColumns().clear();

        TableColumn columnIndex = new TableColumn("Індекси");
        columnIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        TableColumn columnForValue = new TableColumn("Значення");
        columnForValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        TableColumn columnForF = new TableColumn("f статистика");
        columnForF.setCellValueFactory(new PropertyValueFactory<>("fValue"));
        TableColumn columnForZna = new TableColumn("Значущість");
        columnForZna.setCellValueFactory(new PropertyValueFactory<>("zna"));

        //Adding data to the table
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.getColumns().addAll(columnIndex, columnForValue, columnForF, columnForZna);

        String key = null;
        double value;
        double fValue;
        String zna = null;
        ObservableList<DataRowForFullKorilation> data = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            DataRowForFullKorilation dataRowForFullKorilation = new DataRowForFullKorilation();
            key = String.format("%d", i + 1);
            value = linkedHashMap.get(key);
            fValue = Math.abs((list.get(0).size()-list.size()-1)/list.size() * value/(1-value)/2);
            if(fValue<Math.abs(Helper.koefForFisher(list.get(0),list.get(1)))){
                zna = "+";
            }else{
                zna = "-";
            }
            dataRowForFullKorilation.setIndex(key);
            dataRowForFullKorilation.setValue(String.format("%.3f", value));
            dataRowForFullKorilation.setFValue(String.format("%.3f", fValue));
            dataRowForFullKorilation.setZna(zna);
            data.add(dataRowForFullKorilation);
        }
        tableView.getItems().addAll(data);
    }
}
