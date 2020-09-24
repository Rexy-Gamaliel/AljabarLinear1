package com.company;

import java.text.DecimalFormat;
import java.lang.Math;

public class SPL {

    // Variabel untuk mengetahui sudah berapa kali baris matriks ditukar
    public static int peubah = 0;

    /* Buat mencari SPL lewat eliminasi Gauss dengan OBE
    * Cara penggunaan tinggal panggil SPL.eliminasiGauss(isiMatrix) */
    public static void eliminasiGauss(double[][] matrix) {
        int gauss = 0;
        hasilGauss(matrix, gauss);
    }

    /* Buat mencari SPL lewat eliminasi Gauss-Jordan dengan OBE tereduksi
     * Cara penggunaan tinggal panggil SPL.eliminasiGaussJordan(isiMatrix)
     */
    public static void eliminasiGaussJordan(double[][] matrix) {
        int gaussJordan = 1;
        hasilGauss(matrix, gaussJordan);
    }

    /* Helper function untuk menentukan apakah SPL mempunyai solusi tunggal, banyak
       atau bahkan tidak ada solusinya lewat elminasiGauss dan eliminasiGaussJordan
     */
    public static void hasilGauss(double[][] matrix, int status) {
        int len1D = matrix.length;
        int len2D = matrix[0].length;
        double[]  coefficient = new double[len1D];
        double[] hasil;
        int rowParametrik = 9999;
        boolean isParametrik = true;
        boolean notHaveSolution = false;

        reduksiOBE(matrix);

        for (int i=0; i<len1D; i++) {
            coefficient[i] = matrix[i][len2D-1];
        }

        for (int i=len1D-1; i>=0; i--) {
            for (int j=len2D-1; j>=0; j--) {
                if (matrix[i][j] != 0) {
                    isParametrik = false;
                    break;
                }
            }

            if (isParametrik) {
                rowParametrik = i;
                break;
            } else {
                double lastVariable = matrix[i][len2D-1];
                if (matrix[i][i] == 0 && lastVariable !=0) {
                    notHaveSolution = true;
                }
            }
        }

        if (isParametrik) {
            printParametrik(matrix, rowParametrik);
        } else {
            if (notHaveSolution) {
                System.out.println("SPL tidak memiliki solusi");
            } else {
                if (status == 0) {
                    reduksiOBE(matrix);
                    hasil = subtitusiMundur(matrix, coefficient);
                } else {
                    reduksiOBEJordan(matrix);
                    hasil = subtitusiMundurJordan(matrix, coefficient);
                }
//                printMatrix2d(matrix);
//                printMatrix1d(hasil);
                printHasil(hasil);
            }
        }
    }

    /* Untuk menghitung determinan matriks ukuran N x N dengan menggunakan OBE
    *  Cara penggunaan tinggal pangil SPL.Determinan(isiMatrix)
    */
    public static double Determinan(double[][] matrix) {
        reduksiOBE(matrix);
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

        return determinan * Math.pow(-1, peubah);
    }

    /* Untuk mereduksi elemen-elemen matrix sehingga terbentuk matrix segitiga atas
     *  Cara penggunaan SPL.reduksiOBE(isiMatrix)
     */
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

        //printMatrix2d(matrix);
        return matrix;
    }

    /* Untuk mereduksi elemen-elemen matrix sehingga terbentuk matrix eselon
     *  Cara penggunaan SPL.reduksiOBEJordan(isiMatrix)
     */
    public static double[][] reduksiOBEJordan(double[][] matrix) {
        int len1D = matrix.length;
        int len2D = matrix[0].length;

        for (int i = 1; i < len1D; i++) {

            //tukarZeroPivot(matrix, i);
            double pivot = matrix[i][i];

            for (int k = 0; k < i; k++) {
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

//        printMatrix2d(matrix);
        return matrix;
    }

    /* Helper function untuk mencari pivot di dalam matriks yang != 0
    *  setelah itu tukar baris yang mempunyai pivot 0 dengan baris dibawahnya
    *  sampai pivotnya != 0
    *  Cara penggunaan SPL.tukarZeroPivot(isiMatrix, index)
    *  index = index yang pivotnya 0
    */
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

    public static double[] subtitusiMundurJordan(double[][] matrix, double[] b) {
        int len1D = matrix.length;
        int len2D = matrix[0].length;
        double[] hasil = new double[len1D];

        for (int i=len1D-1; i>=0; i--) {
            hasil[i] = b[i]/matrix[i][i];
            for (int j=len2D; j>=0; j--) {
                matrix[i][i] /= matrix[i][i];
            }

            matrix[i][len2D-1] = hasil[i];
        }

//        printMatrix2d(matrix);
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

        peubah += 1;
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

    /* Helper function untuk nge-print matriks yang solusi SPL nya berbentuk parametrik */
    public static void printParametrik(double[][] matrix, int rowParametrik) {
        int len1D = matrix.length;
        int len2D = matrix[0].length;

        System.out.println("Solusi Parametrik: ");
        for (int i=0; i<len1D; i++) {
            if (i != rowParametrik) {
                for (int j=0; j<len2D-1; j++) {
                    if (j != len2D-1) {
                        if (matrix[i][j] != 0) {
                            if (matrix[i][j] != 1) {
                                System.out.printf("%.1fx%d + ", matrix[i][j], j+1);
                            } else {
                                System.out.printf("x%d + ", j+1);
                            }
                        }
                    }
                }
                System.out.printf(" = %.1f", matrix[i][len2D-1]);
                System.out.println();;
            }
        }
    }

    /* Helper function untuk nge-print matriks yang solusi SPL nya tunggal */
    public static void printHasil(double[] matrix) {
        int len1D = matrix.length;
        System.out.println("Solusi Tunggal: ");
        for (int i=0; i<len1D; i++) {
            if (i != len1D-1) {
                System.out.printf("x%d = %.1f, ", i+1, matrix[i]);
            } else {
                System.out.printf("x%d = %.1f ", i+1, matrix[i]);
            }
        }
        System.out.println();
    }
}
