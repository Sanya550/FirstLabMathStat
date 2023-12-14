package com.example.idealjavafx;

import org.apache.commons.math3.linear.*;
import Jama.Matrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainFunction {
    //матиматичне сподівання
    public static double matSpodivan(List<Double> arr1) {
        return arr1.stream().mapToDouble(a -> a).average().orElseThrow();
    }

    //дисперсія
    public static double duspersia(List<Double> arr1) {
        double resultSA = arr1.stream().mapToDouble(a -> a).average().orElseThrow();
        double dus = 0;
        for (int i = 0; i < arr1.size(); i++) {
            dus += Math.pow((arr1.get(i) - resultSA), 2) / ((arr1.size() - 1));
        }
        return dus;
    }

    //середнє квадратичне
    public static double serKva(List<Double> arr1) {
        return Math.sqrt(duspersia(arr1));
    }

    //сігмойда:
    public static double[] sigmoid(double[] arr1) {
        double[] sigma = new double[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            sigma[i] = (double) 1 / (1 + Math.pow(Math.E, -arr1[i]));
        }
        return sigma;
    }

    //R для дисперсійно-коваріаційнії матриці
    public static double rForKovMatrix(List<Double> arr1, List<Double> arr2) {
        double tempResultSA1AndSA2 = 0;
        for (int i = 0; i < arr1.size(); i++) {
            tempResultSA1AndSA2 += arr1.get(i) * arr2.get(i);
        }
        double resultSA1AndSA2 = tempResultSA1AndSA2 / arr1.size();//xy_
        double r = (arr1.size() / (arr1.size() - 1)) *
                ((resultSA1AndSA2 - matSpodivan(arr1) * matSpodivan(arr2)) / (serKva(arr1) * serKva(arr2)));
        return r;
    }

    //знаходження визначника для матриці:
    public static double findDetermination(double arr[][]) {
        RealMatrix realMatrix = MatrixUtils.createRealMatrix(arr);
        LUDecomposition luDecomposition = new LUDecomposition(realMatrix);
        double determinant = luDecomposition.getDeterminant();
        return determinant;
    }

    //DC матриця
    public static double[][] findDCForDuspKovMatrixForManyVibirok(List<List<Double>> list) {
        double serKva1;
        double serKva2;
        double r;
        double arrDC[][] = new double[list.size()][list.size()];
        for (int i = 0; i < list.size(); i++) {
            serKva1 = MainFunction.serKva(list.get(i));
            for (int j = 0; j < list.size(); j++) {
                r = MainFunction.rForKovMatrix(list.get(i), list.get(j));
                serKva2 = MainFunction.serKva(list.get(j));
                if (i == j) {
                    arrDC[i][i] = serKva1 * serKva2;
                } else {
                    arrDC[i][j] = serKva1 * serKva2 * r;
                    arrDC[j][i] = serKva1 * serKva2 * r;
                }
            }

        }
        return arrDC;
    }

    //R матриця
    public static double[][] findRForDuspKovMatrixForManyVibirok(List<List<Double>> list) {
        double r;
        double arrR[][] = new double[list.size()][list.size()];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                r = MainFunction.rForKovMatrix(list.get(i), list.get(j));
                if (i == j) {
                    arrR[i][i] = 1;
                } else {
                    arrR[i][j] = r;
                    arrR[j][i] = r;
                }
            }

        }
        return arrR;
    }

    public static double[][] transposeMatrix(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        double[][] transposed = new double[cols][rows];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                transposed[i][j] = matrix[j][i];
            }
        }

        return transposed;
    }

    public static double[][] multiplyMatrixOnMatrix(double[][] a, double[][] b) {
        double[][] result = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    public static double[][] addingMatrixToMatrix(double[][] a, double[][] b) {
        double[][] result = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            result[i] = Arrays.copyOf(a[i], a[i].length);
            for (int j = 0; j < a[i].length; j++) {
                result[i][j] += b[i][j];
            }
        }
        return result;
    }

    public static double[][] subtractMatrix(double[][] A, double[][] B) {
        int rows = A.length;
        int cols = A[0].length;

        if (rows != B.length || cols != B[0].length) {
            throw new IllegalArgumentException("Matrices must have the same size");
        }
        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }

        return result;
    }


    public static double[][] multiplyMatrixOnDigit(double[][] a, double digit) {
        double[][] result = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = a[i][j] * digit;
            }
        }
        return result;
    }


    public static double[][] getInverseMatrix(double[][] matrix1) {
        double[][] matr1 = new double[matrix1[0].length][matrix1[1].length];
        for (int i = 0; i < matrix1[0].length; i++) {
            for (int j = 0; j < matrix1[1].length; j++) {
                matr1[i][j] = matrix1[i][j];
            }
        }
        Matrix matrix = new Matrix(matr1);
        Matrix inverse = matrix.inverse();
        return inverse.getArray();
    }

    public static double[][] minusVectorFromMatrix(double[][] matrix, double[][] vector) {
        //Note double[][] vector = double[0][matrix.length]
        int rows = matrix.length;
        int columns = matrix[0].length;

        double[][] result = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix[i][j] - vector[0][i];
            }
        }

        return result;
    }

    public static double[] subtractVectors(double[] vector1, double[] vector2) {
        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException("Vectors must have the same length for subtraction.");
        }

        int length = vector1.length;
        double[] result = new double[length];

        for (int i = 0; i < length; i++) {
            result[i] = vector1[i] - vector2[i];
        }

        return result;
    }

    public static double[] addingVectors(double[] vector1, double[] vector2) {
        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException("Vectors must have the same length for addition.");
        }

        int length = vector1.length;
        double[] result = new double[length];

        for (int i = 0; i < length; i++) {
            result[i] = vector1[i] + vector2[i];
        }

        return result;
    }

    public static double[] multiplyMatrixOnVector(double[][] matrix, double[] vector) {
        int matrixRows = matrix.length;
        int matrixCols = matrix[0].length;

        if (matrixCols != vector.length) {
            throw new IllegalArgumentException("Number of columns in the matrix must be equal to the length of the vector.");
        }

        double[] result = new double[matrixRows];

        for (int i = 0; i < matrixRows; i++) {
            double sum = 0.0;
            for (int j = 0; j < matrixCols; j++) {
                sum += matrix[i][j] * vector[j];
            }
            result[i] = sum;
        }

        return result;
    }

    public static double[] multiplyVectorOnDigit(double[] vector, double scalar) {
        int length = vector.length;
        double[] result = new double[length];

        for (int i = 0; i < length; i++) {
            result[i] = vector[i] * scalar;
        }

        return result;
    }

    //матриця мінус число
    public static double[][] minusNumberFromMatrix(double[][] matrix, double number) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        double[][] result = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix[i][j] - number;
            }
        }

        return result;
    }

    //матриця плюс число
    public static double[][] plusNumberFromMatrix(double[][] matrix, double number) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        double[][] result = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix[i][j] + number;
            }
        }

        return result;
    }


    public static double getKoefKorilationForThreeValue(List<Double> arr1, List<Double> arr2, List<Double> arr3) {
        double r = (rForKovMatrix(arr1, arr2) - rForKovMatrix(arr1, arr3) * rForKovMatrix(arr2, arr3)) / Math.sqrt((1 - Math.pow(rForKovMatrix(arr1, arr3), 2)) * (1 - Math.pow(rForKovMatrix(arr2, arr3), 2)));
        return r;
    }

    public static double getKoefKorilationForFourValue(List<Double> arr1, List<Double> arr2, List<Double> arr3, List<Double> arr4) {
        double r = (getKoefKorilationForThreeValue(arr1, arr2, arr3) - getKoefKorilationForThreeValue(arr1, arr4, arr3) * getKoefKorilationForThreeValue(arr2, arr4, arr3)) / Math.sqrt((1 - Math.pow(getKoefKorilationForThreeValue(arr1, arr4, arr3), 2)) * (1 - Math.pow(getKoefKorilationForThreeValue(arr2, arr4, arr3), 2)));
        return r;
    }

    public static double getKoefKorilationForFiveValue(List<Double> arr1, List<Double> arr2, List<Double> arr3, List<Double> arr4, List<Double> arr5) {
        double r = (getKoefKorilationForFourValue(arr1, arr2, arr3, arr4) - getKoefKorilationForFourValue(arr1, arr4, arr3, arr5) * getKoefKorilationForFourValue(arr2, arr4, arr3, arr5)) / Math.sqrt((1 - Math.pow(getKoefKorilationForFourValue(arr1, arr4, arr3, arr5), 2)) * (1 - Math.pow(getKoefKorilationForFourValue(arr2, arr4, arr3, arr5), 2)));
        return r;
    }

    public static double getMnozhinKoefKorilation(List<List<Double>> rMatrix, List<List<Double>> rMatrixKK) {
        return Math.sqrt(1 - MainFunction.findDetermination(findRForDuspKovMatrixForManyVibirok(rMatrix)) / MainFunction.findDetermination(findRForDuspKovMatrixForManyVibirok(rMatrixKK)));
    }

    //пошук власних векторів за методом Якобі-Гаусса
    public static List<RealVector> getVlasniiVectors(double[][] matrixData) {
        RealMatrix matrix = MatrixUtils.createRealMatrix(matrixData);
        EigenDecomposition eigenDecomposition = new EigenDecomposition(matrix);

        int n = matrixData.length;
        List<RealVector> eigenVectors = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            eigenVectors.add(eigenDecomposition.getEigenvector(i));
        }

        return eigenVectors;
    }

    //пошук власних чисел за методом Якобі-Гаусса
    public static List<Double> getVlasniiValues(double[][] matrixData) {
        RealMatrix matrix = MatrixUtils.createRealMatrix(matrixData);
        EigenDecomposition eigenDecomposition = new EigenDecomposition(matrix);

        int n = matrixData.length;
        List<Double> eigenValues = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            eigenValues.add(eigenDecomposition.getRealEigenvalue(i));
        }

        return eigenValues;
    }

    //List<RealVector> to array[][]
    public static double[][] convectListOfRealVectorsToRwoDimensionArray(List<RealVector> resultVectors) {
        int numRows = resultVectors.size();
        int numCols = resultVectors.get(0).getDimension();
        double[][] doubleArray = new double[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            RealVector realVector = resultVectors.get(i);
            doubleArray[i] = realVector.toArray();
        }
        return doubleArray;
    }
}
