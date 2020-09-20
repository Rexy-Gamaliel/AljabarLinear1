package com.company;

import java.text.DecimalFormat;

public class SPL {
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

    public static void elminasiGauss(double[][] a, double[] b) {
        int len1D = a.length;
        int len2D = a[0].length;

        for (int i=0; i<len1D; i++) {
            double pivot = a[i][i];

            if (pivot == 0) {
                tukarBaris(a, i, i+1);
                pivot = a[i][i];
            }

            for (int k=i+1; k<len1D; k++) {
                double N = (pivot/a[k][i]);

                for (int j=0; j<len2D; j++) {
                    String hasilStr = new DecimalFormat("##.##").format(a[i][j] - a[k][j]*N);
                    a[k][j] = Double.parseDouble(hasilStr);
                }
            }
            b[i] = a[i][4];
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

    public static void tukarBaris(double[][] mat, int a, int b) {
        int len_row = mat.length;

        for (int k=0; k<=len_row; k++) {
            double temp = mat[a][k];
            mat[a][k] = mat[b][k];
            mat[b][k] = temp;
        }
    }
}
