package com.company;
import java.util.Arrays;

public class Main {
    /* MAIN PROGRAM
        MENU
        1. Sistem Persamaaan Linier
        2. Determinan
        3. Matriks balikan
        4. Interpolasi Polinom
        5. Regresi linier berganda
        6. Keluar
    */
    static double[][] a = { {3, -2, 5, 0, 2},
            {4, 5, 8, 1, 4},
            {1, 1, 2, 1, 5},
            {2, 7, 6, 5, 7}};

    static double[] b = new double[a.length];

    public static void main(String[] args) {
        SPL.printMatrix2d(a);
        SPL.elminasiGauss(a, b);
        SPL.printMatrix2d(a);
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(SPL.subtitusiMundur(a, b)));
    }
}
