package com.example.idealjavafx.logicHelper;

import com.example.idealjavafx.MainFunction;
import org.apache.commons.math3.linear.RealVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FactorHelper {
    private static final double eps = 0.001;

    //Пошук W matrix:
    public double[][] findWMatrix(double[][] matrix, double limit) {
        var resultVectors = new ArrayList<RealVector>();
        var vlasniVectors = MainFunction.getVlasniiVectors(matrix);
        var vlasniNumbers = MainFunction.getVlasniiValues(matrix);
        for (int i = 0; i < vlasniNumbers.size(); i++) {
            if (vlasniNumbers.get(i) > limit) {
                resultVectors.add(vlasniVectors.get(i));
            }
        }
        return MainFunction.convectListOfRealVectorsToRwoDimensionArray(resultVectors);
    }

    //Метод максимальних кореляцій:
    public List<Double> hByMethodMaxKorilation(double[][] korilationMatrix) {
        var result = new ArrayList<Double>();
        for (int i = 0; i < korilationMatrix.length; i++) {
            var max = -1.0;
            for (int j = 0; j < korilationMatrix[0].length; j++) {
                if (max < korilationMatrix[i][j] && korilationMatrix[i][j] != 1) {
                    max = korilationMatrix[i][j];
                }
            }
            result.add(max);
        }
        return result;
    }

    //Метод тріад:
    public List<Double> hByMethodTriad(double[][] korilationMatrix) {
        var result = new ArrayList<Double>();
        for (int i = 0; i < korilationMatrix.length; i++) {
            var tempList = new ArrayList<Double>();
            for (int j = 0; j < korilationMatrix.length; j++) {
                if (i != j) {
                    tempList.add(korilationMatrix[i][j]);
                }
            }
            var firstMax = tempList.stream().mapToDouble(a -> a).max().orElseThrow();
            var secondMax = tempList.stream().mapToDouble(a -> a).filter(a -> a != firstMax).max().orElseThrow();
            var index1 = -1;
            var index2 = -1;
            for (int j = 0; j < korilationMatrix.length; j++) {
                if (korilationMatrix[i][j] == firstMax) {
                    index1 = j;
                }
                if (korilationMatrix[i][j] == secondMax) {
                    index2 = j;
                }
            }
            result.add(Math.abs(firstMax * secondMax / korilationMatrix[index1][index2]));
        }
        return result;
    }

    //Метод усереднення:
    public List<Double> hByMethodOfAverage(double[][] korilationMatrix) {
        var result = new ArrayList<Double>();
        for (int i = 0; i < korilationMatrix.length; i++) {
            var sum = 0.0;
            for (int j = 0; j < korilationMatrix[0].length; j++) {
                if (korilationMatrix[i][j] != 1) {
                    sum += Math.abs(korilationMatrix[i][j]);
                }
            }
            result.add(sum / (korilationMatrix.length - 1));
        }
        return result;
    }

    //Центроїдний метод:
    public List<Double> hByMethodOfZentroid(double[][] korilationMatrix) {
        var result = new ArrayList<Double>();
        var newMatrix = new double[korilationMatrix.length][korilationMatrix.length];
        for (int i = 0; i < korilationMatrix.length; i++) {
            //filling diagonal elements:
            var tempList = new ArrayList<Double>();
            for (int j = 0; j < korilationMatrix.length; j++) {
                tempList.add(korilationMatrix[i][j]);
            }
            newMatrix[i][i] = tempList.stream().mapToDouble(a -> a).filter(a -> a != 1).max().orElseThrow();
            //filling other elements:
            for (int j = 0; j < korilationMatrix.length; j++) {
                if (i != j) {
                    newMatrix[i][j] = korilationMatrix[i][j];
                }
            }
        }
        //finding h2:
        var belowPart = 0.0;
        var higherPart = new ArrayList<Double>();
        var temp = 0.0;
        for (int i = 0; i < newMatrix.length; i++) {
            temp = 0.0;
            for (int j = 0; j < newMatrix.length; j++) {
                belowPart += Math.abs(newMatrix[i][j]);
                temp += Math.abs(newMatrix[i][j]);
            }
            higherPart.add(Math.pow(temp, 2));
        }

        for (int i = 0; i < higherPart.size(); i++) {
            result.add(higherPart.get(i) / belowPart);
        }
        return result;
    }

    //Aвероїдний метод:
    public List<Double> hByMethodOfAveroid(double[][] korilationMatrix) {
        var result = new ArrayList<Double>();
        var higherPart = new ArrayList<Double>();
        var belowPart = new ArrayList<Double>();
        var tempHigh = 0.0;
        var tempBelow = 0.0;
        for (int k = 0; k < korilationMatrix.length; k++) {
            tempHigh = 0.0;
            tempBelow = 0.0;
            for (int i = 0; i < korilationMatrix.length; i++) {
                for (int j = 0; j < korilationMatrix.length; j++) {
                    if (i != j && k != i) {
                        tempBelow += Math.abs(korilationMatrix[i][j]);
                    }
                }
            }
            belowPart.add(tempBelow);

            for (int i = 0; i < korilationMatrix.length; i++) {
                if (k != i) {
                    tempHigh += Math.abs(korilationMatrix[k][i]);
                }
            }
            higherPart.add(Math.pow(tempHigh, 2));
        }

        for (int i = 0; i < higherPart.size(); i++) {
            result.add(((double) korilationMatrix.length / (korilationMatrix.length - 1)) * higherPart.get(i) / belowPart.get(i));
        }
        return result;
    }

    //Метод, що ґрунтується на підсумках МГК:
    public List<Double> hByMethodBasicOnMGK(int w, List<List<Double>> list) {
        var dcMatrix = getCentruvanDcMatrix(list);
        var result = new ArrayList<Double>();
        var vlasVektors = MainFunction.getVlasniiVectors(dcMatrix);
        for (int i = 0; i < vlasVektors.size(); i++) {
            var sum = 0.0;
            for (int j = 0; j < w; j++) {
                sum += Math.pow(vlasVektors.get(j).getEntry(i), 2);
            }
            result.add(sum);
        }
        return result;
    }

    public double[][] findRHMatrix(double[][] korilationMatrix, List<Double> hList) {
        var array = new double[korilationMatrix.length][korilationMatrix[0].length];
        for (int i = 0; i < korilationMatrix.length; i++) {
            for (int j = 0; j < korilationMatrix[0].length; j++) {
                if (i != j) {
                    array[i][j] = korilationMatrix[i][j];
                } else {
                    array[i][j] = hList.get(i);
                }
            }
        }
        return array;
    }

    public double findFOfRzal(double[][] rHMatrix) {
        var matrixA = MainFunction.convectListOfRealVectorsToRwoDimensionArray(MainFunction.getVlasniiVectors(rHMatrix));
        var rZalMatrix = MainFunction.subtractMatrix(rHMatrix,
                MainFunction.multiplyMatrixOnMatrix(matrixA, MainFunction.transposeMatrix(matrixA)));

        var sum = 0.0;
        for (int i = 0; i < rZalMatrix.length; i++) {
            for (int j = 0; j < rZalMatrix[0].length; j++) {
                if (i != j) {
                    sum += Math.pow(rZalMatrix[i][j], 2);
                }
            }
        }
        return sum;
    }

    //знаходження матриці факторного відображення
    public String findMatrixOfFactorDisplay(List<List<Double>> listNotSorted) {
        double[][] korilationMatrix = (MainFunction.findRForDuspKovMatrixForManyVibirok(listNotSorted));
        var wMatrixFromKorilation = findWMatrix(korilationMatrix, 1);

        var firstMap = getActualRHAndWFirst(korilationMatrix, listNotSorted, wMatrixFromKorilation.length);
        int wValue = firstMap.entrySet().iterator().next().getKey().get(0).intValue();
        var matrix = firstMap.entrySet().iterator().next().getValue();
        var newVlArray = getNewVlMatrixWithHkOnDiagonals(matrix, wValue);
        var secondMap = getActualRHAndWSecond(newVlArray, wMatrixFromKorilation.length);
        while (isConditionAccepted(firstMap, secondMap)) {
            firstMap = secondMap;
            wValue = firstMap.entrySet().iterator().next().getKey().get(0).intValue();
            matrix = firstMap.entrySet().iterator().next().getValue();
            newVlArray = getNewVlMatrixWithHkOnDiagonals(matrix, wValue);
            secondMap = getActualRHAndWSecond(newVlArray, wMatrixFromKorilation.length);
        }


        var matrixResult = secondMap.entrySet().iterator().next().getValue();
        var quantityOfFactors = secondMap.entrySet().iterator().next().getKey().get(0).intValue();
        String result = "  ";
        for (int i = 0; i < quantityOfFactors; i++) {
            result += String.format("      F%d  ", i + 1);
        }
        result += "    h_k  \n";
        int r = 1;
        for (int i = 0; i < matrixResult.length; i++) {
            var sum = 0d;
            for (int j = 0; j < quantityOfFactors; j++) {
                if (j == 0) {
                    result += String.format("X%d  ", r);
                }
                var val = matrixResult[i][j];
                if (val > 0) {
                    result += " ";
                }
                result += String.format("%.3f  ", val);
                sum += Math.pow(val, 2);
            }
            result += String.format("%.3f\n", sum);
            r++;
        }
        return result;
    }

    private double[][] getNewVlMatrixWithHkOnDiagonals(double[][] matrix, int wValue) {
        var vlMatrixExpandedWithOldHk = MainFunction.convectListOfRealVectorsToRwoDimensionArray(MainFunction.getVlasniiVectors(matrix));
        double[] hk = getHkFromMatrix(wValue, vlMatrixExpandedWithOldHk);
        var vlMatrixExpandedWithNeHkOnDiagonal = new double[vlMatrixExpandedWithOldHk.length][vlMatrixExpandedWithOldHk.length];
        for (int i = 0; i < vlMatrixExpandedWithOldHk.length; i++) {
            for (int j = 0; j < vlMatrixExpandedWithOldHk.length; j++) {
                if (i != j) {
                    vlMatrixExpandedWithNeHkOnDiagonal[i][j] = vlMatrixExpandedWithOldHk[i][j];
                } else {
                    vlMatrixExpandedWithNeHkOnDiagonal[i][j] = hk[i];
                }
            }
        }
        return vlMatrixExpandedWithNeHkOnDiagonal;
    }

    private double[] getHkFromMatrix(int wValue, double[][] matrix) {
        double[] hk = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            double tempSum = 0.0;
            for (int j = 0; j < wValue; j++) {
                tempSum = Math.pow(matrix[i][j], 2);
            }
            hk[i] = tempSum;
        }
        return hk;
    }

    private HashMap<List<Double>, double[][]> getActualRHAndWFirst(double[][] matrix, List<List<Double>> listNotSorted, int wInitialLength) {
        var mapOfRhAndF = new HashMap<Double, double[][]>();
        var tempValueForMin = findFOfRzal(findRHMatrix(matrix, hByMethodMaxKorilation(matrix)));
        var rHMatrixMaxKorilation = findRHMatrix(matrix, hByMethodMaxKorilation(matrix));
        var rHMatrixTriad = findRHMatrix(matrix, hByMethodTriad(matrix));
        var rHMatrixAverage = findRHMatrix(matrix, hByMethodOfAverage(matrix));
        var rHMatrixZentroid = findRHMatrix(matrix, hByMethodOfZentroid(matrix));
        var rHMatrixAveroid = findRHMatrix(matrix, hByMethodOfAveroid(matrix));
        var rHMatrixMgk = findRHMatrix(matrix, hByMethodBasicOnMGK(wInitialLength, listNotSorted));
        mapOfRhAndF.put(findFOfRzal(rHMatrixMaxKorilation), rHMatrixMaxKorilation);
        mapOfRhAndF.put(findFOfRzal(rHMatrixTriad), rHMatrixTriad);
        mapOfRhAndF.put(findFOfRzal(rHMatrixAverage), rHMatrixAverage);
        mapOfRhAndF.put(findFOfRzal(rHMatrixZentroid), rHMatrixZentroid);
        mapOfRhAndF.put(findFOfRzal(rHMatrixAveroid), rHMatrixAveroid);
        mapOfRhAndF.put(findFOfRzal(rHMatrixMgk), rHMatrixMgk);

        for (var el : mapOfRhAndF.entrySet()) {
            if (el.getKey() < tempValueForMin) {
                tempValueForMin = el.getKey();
            }
        }
        var actualRHMatrix = mapOfRhAndF.get(tempValueForMin);
        var averageOfVlasniNumbers = MainFunction.getVlasniiValues(actualRHMatrix).stream().mapToDouble(a -> a).average().orElseThrow();
        var wMatrixFromRh = findWMatrix(actualRHMatrix, averageOfVlasniNumbers);
        var higherW = 0;
        if (wInitialLength > wMatrixFromRh.length) {
            higherW = wInitialLength;
        } else {
            higherW = wMatrixFromRh.length;
        }
        var map = new HashMap<List<Double>, double[][]>();
        map.put(Arrays.asList((double) higherW, tempValueForMin), actualRHMatrix);//w value(first) and f value(second)
        return map;
    }


    private HashMap<List<Double>, double[][]> getActualRHAndWSecond(double[][] matrix, int wInitialLength) {
        var mapOfRhAndF = new HashMap<Double, double[][]>();
        var tempValueForMin = findFOfRzal(findRHMatrix(matrix, hByMethodMaxKorilation(matrix)));
        var rHMatrixMaxKorilation = findRHMatrix(matrix, hByMethodMaxKorilation(matrix));
        var rHMatrixTriad = findRHMatrix(matrix, hByMethodTriad(matrix));
        var rHMatrixAverage = findRHMatrix(matrix, hByMethodOfAverage(matrix));
        var rHMatrixZentroid = findRHMatrix(matrix, hByMethodOfZentroid(matrix));
        var rHMatrixAveroid = findRHMatrix(matrix, hByMethodOfAveroid(matrix));
        mapOfRhAndF.put(findFOfRzal(rHMatrixMaxKorilation), rHMatrixMaxKorilation);
        mapOfRhAndF.put(findFOfRzal(rHMatrixTriad), rHMatrixTriad);
        mapOfRhAndF.put(findFOfRzal(rHMatrixAverage), rHMatrixAverage);
        mapOfRhAndF.put(findFOfRzal(rHMatrixZentroid), rHMatrixZentroid);
        mapOfRhAndF.put(findFOfRzal(rHMatrixAveroid), rHMatrixAveroid);

        for (var el : mapOfRhAndF.entrySet()) {
            if (el.getKey() < tempValueForMin) {
                tempValueForMin = el.getKey();
            }
        }
        var actualRHMatrix = mapOfRhAndF.get(tempValueForMin);
        var averageOfVlasniNumbers = MainFunction.getVlasniiValues(actualRHMatrix).stream().mapToDouble(a -> a).average().orElseThrow();
        var wMatrixFromRh = findWMatrix(actualRHMatrix, averageOfVlasniNumbers);
        int higherW = 0;
        if (wInitialLength > wMatrixFromRh.length) {
            higherW = wInitialLength;
        } else {
            higherW = wMatrixFromRh.length;
        }
        var map = new HashMap<List<Double>, double[][]>();
        map.put(Arrays.asList((double) higherW, tempValueForMin), actualRHMatrix);//w value(first) and f value(second)
        return map;
    }

    private boolean isConditionAccepted(HashMap<List<Double>, double[][]> oldMap, HashMap<List<Double>, double[][]> newMap) {
        //check f(i+1)<f(i)
        if (oldMap.entrySet().iterator().next().getKey().get(1) < newMap.entrySet().iterator().next().getKey().get(1)) {
            return false;
        }

        var matrixAOldArray = oldMap.entrySet().iterator().next().getValue();
        var matrixANewArray = newMap.entrySet().iterator().next().getValue();

        var tempSum = 0d;
        for (int i = 0; i < matrixAOldArray.length; i++) {
            for (int j = 0; j < matrixANewArray.length; j++) {
                tempSum += Math.pow(matrixANewArray[i][j] - matrixAOldArray[i][j], 2);
            }
        }

        if (eps > tempSum) {
            return false;
        }

        var hk = getHkFromMatrix(newMap.entrySet().iterator().next().getKey().get(0).intValue(), matrixANewArray);
        if (Arrays.stream(hk).sum() > 1.01) {
            return false;
        }
        return true;
    }

//    var vl = MainFunction.getVlasniiVectors(matrix);
    //обрезацию матрицу vl
    //нахожу аш
    // ставлю на диагонали RH
    // на опять f
    //повторяю
    // res: обрезаная матрица vl и

    private double[][] getCentruvanDcMatrix(List<List<Double>> listNotSorted) {
        List<List<Double>> list1 = new ArrayList<>();
        for (int i = 0; i < listNotSorted.size(); i++) {
            list1.add(new ArrayList<>(listNotSorted.get(i)));
        }
        for (var list : list1) {
            double resultSA = MainFunction.matSpodivan(list);
            list.replaceAll(a -> (a - resultSA));
        }
        double[][] matrixForInitialDC = new double[list1.size()][list1.get(0).size()];
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list1.get(0).size(); j++) {
                matrixForInitialDC[i][j] = list1.get(i).get(j);
            }
        }

        var dcMatrix = MainFunction.multiplyMatrixOnDigit(MainFunction.multiplyMatrixOnMatrix(matrixForInitialDC, MainFunction.transposeMatrix(matrixForInitialDC)),
                (double) 1 / listNotSorted.get(0).size());
        return dcMatrix;
    }

}