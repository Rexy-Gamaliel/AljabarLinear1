package com.company;

import java.text.DecimalFormat;

public class SPL {

    public static double[] eliminasiGauss(double[][] matrix) {
        int len1D = matrix.length;
        int len2D = matrix[0].length;
        double[]  coefficient = new double[matrix.length];

        for (int i=0; i<len1D; i++) {

            tukarZeroPivot(matrix, i);
            double pivot = matrix[i][i];

            for (int k=i+1; k<len1D; k++) {
                double N2 = matrix[k][i]/pivot;

                if (matrix[k][i] != 0) {
                    for (int j=0; j<len2D; j++) {
                            String hasilStr = new DecimalFormat("##.##").format(matrix[k][j] - matrix[i][j]*N2);
                            matrix[k][j] = Double.parseDouble(hasilStr);
                    }
                    System.out.print(N2);
                    System.out.print(", ");
                    System.out.println();
                }
                printMatrix2d(matrix);
            }
            coefficient[i] = matrix[i][len2D-1];
        }

        return subtitusiMundur(matrix, coefficient);
    }
    
    public static void printMatrix2d(double[][] matrix) {
        int len1D = matrix.length;
        int len2D = matrix[0].length;

        for (double[] doubles : matrix) {
            System.out.print("[");
            for (int j = 0; j < len2D; j++) {
                String hasilStr = new DecimalFormat("##.##").format(doubles[j]);
                System.out.print(hasilStr);
                if (j!=len2D-1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            System.out.println();
        }
        System.out.println();
    }

    public static void tukarZeroPivot(double[][] matrix, int idx) {
        int len1D = matrix.length;
        double pivot = matrix[idx][idx];
        int foundIdxPivot = 0;

        for (int i=idx+1; i<len1D; i++) {
            if (matrix[i][idx] != 0) {
                foundIdxPivot = i;
                break;
            }
        }

        if (pivot == 0) {
            tukarBaris(matrix, idx, foundIdxPivot);
        }
    }

    public static double[] subtitusiMundur(double[][] matrix, double[] b) {
        int len1D = matrix.length;

        double[] hasil = new double[len1D];
        hasil[len1D-1] = b[len1D-1]/matrix[len1D-1][len1D-1];

        for (int i=len1D-2; i>=0; i--) {
            double sum = 0;
            for (int j=i+1; j<len1D; j++) {
                sum += matrix[i][j]*hasil[j];
            }

            hasil[i]=(b[i]-sum)/matrix[i][i];
        }

        return hasil;
    }

    public static void tukarBaris(double[][] matrix, int rowX, int rowY) {
        int len1D = matrix.length;
        int len2D = matrix[0].length;

        if (rowX != len1D-1) {
            for (int k=0; k<len2D; k++) {
                double temp = matrix[rowX][k];
                matrix[rowX][k] = matrix[rowY][k];
                matrix[rowY][k] = temp;
            }
        }
    }
}
