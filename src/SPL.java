package com.company;

import java.text.DecimalFormat;

public class SPL {

    /* Buat mencari SPL lewat eliminasi Gauss dengan OBE */
    public static void eliminasiGauss(double[][] matrix) {
        reduksiOBE(matrix);

        int len1D = matrix.length;
        int len2D = matrix[0].length;
        double[]  coefficient = new double[len1D];
        boolean isParametrik = false;
        int totalTiapBaris = 0;
        double lastVariable = matrix[len1D-1][len2D-2];

        for (int i=0; i<len1D; i++) {
            coefficient[i] = matrix[i][len2D-1];
        }

        for (int i=0; i<len1D; i++) {
            for (int j=0; j<len2D; j++) {
                totalTiapBaris += matrix[i][j];
            }

            if (totalTiapBaris == 0) {
                isParametrik = true;
                break;
            }
            totalTiapBaris = 0;
        }

        if (isParametrik) {
            System.out.println("SPL berbentuk parametrik");
        } else {
            if (lastVariable == 0) {
                System.out.println("SPL tidak memiliki solusi");
            } else {
                double[] hasil = subtitusiMundur(matrix, coefficient);
                printMatrix1d(hasil);
            }
        }
    }

    /* Buat menghitung matriks ukuran apapun dengan menggunakan reduksi baris elementer */
    public static double Determinan(double[][] matrix) {
        int lenBaris = matrix.length;
        int lenKolom = matrix[0].length;
        double determinan = 1;

        if (lenBaris == lenKolom) {
            for (int i=0; i<lenBaris; i++) {
                determinan *= matrix[i][i];
            }
        } else {
            System.out.println("Ukuran matriks harus N x N");
        }

        return determinan;
    }

    /* Buat menghitung operasi OBE pada matriks */
    public static double[][] reduksiOBE(double[][] matrix) {
        int len1D = matrix.length;
        int len2D = matrix[0].length;

        for (int i = 0; i < len1D; i++) {

            tukarZeroPivot(matrix, i);
            double pivot = matrix[i][i];

            for (int k = i + 1; k < len1D; k++) {
                double factor = matrix[k][i] / pivot;

                if (matrix[k][i] != 0) {
                    for (int j = 0; j < len2D; j++) {
                        String hasilStr = new DecimalFormat("##.##").format(matrix[k][j] - matrix[i][j] * factor);
                        matrix[k][j] = Double.parseDouble(hasilStr);
                    }
//                    System.out.print(factor);
//                    System.out.print(", ");
//                    System.out.println();
                }
//                printMatrix2d(matrix);
            }
        }

        return matrix;
    }

    /* Helper function untuk nyari pivot di dalam matriks != 0 */
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

    /* Helper function untuk mendapatkan variabel dari SPL */
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

    /* Helper function untuk menukar baris di dalam matriks jika pivot nya = 0 */
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

    /* Helper function untuk nge-print matriks 2D */
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

    /* Helper function untuk nge-print matriks 1D */
    public static void printMatrix1d(double[] matrix) {
        int len1D = matrix.length;
        System.out.print("[");

        for (int i=0; i<len1D; i++) {
                String hasilStr = new DecimalFormat("##.##").format(matrix[i]);
                System.out.print(hasilStr);
                if (i!=len1D-1) {
                    System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
