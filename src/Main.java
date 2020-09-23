package com.company;
import java.util.Arrays;

public class Main {
    /* MAIN PROGRAM
    *   MENU
        1. Sistem Persamaaan Linier
        2. Determinan
        3. Matriks balikan
        4. Interpolasi Polinom
        5. Regresi linier berganda
        6. Keluar
    */
    static double[][] a = { {3, -2, 0, 5, 2},
                            {4, 5, 8, 1, 4},
                            {0, 1, 2, 1, 5},
                            {2, 7, 6, 5, 7}};

    static double[][] b = { {0, 7, -1, 3, 1, 5},
                            {0, 3, 4, 1, 7, 7},
                            {6, 2, 0, 2, -1, 2},
                            {2, 1, 2, 0, 2, 3},
                            {3, 4, 1, -2, 1, 4}};

    static double[] e = {0, 5, -12};

    static double[][] f = {{0, 7, -1, 3, 1, 5}, {0, 3, 4, 1, 7, 7}, {6, 2, 0, 2, -1, 2}, {2, 1, 2, 0, 2, 3}, {3, 4, 1, -2, 1, 4}};

    static double[][] g = {{1, 1, -1, -1, 1}, {2, 5, -7, -5, -2}, {2, -1, 1, 3, 4}, {5, 2, -4, 2, 6}};

    static double[][] h = {{2, 3, 4}, {6, 8, 5}, {3, 7, 1}};

    public static void main(String[] args) {
        SPL.printMatrix2d(h);
        System.out.println(Arrays.toString(SPL.eliminasiGauss(h)));
    }
}
