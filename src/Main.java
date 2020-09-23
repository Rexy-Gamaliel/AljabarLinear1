package com.company;

import java.util.Arrays;
import java.util.*;

public class Main {
    /* MAIN PROGRAM
        MENU
        1. Sistem Persamaaan Linier
        2. Determinan
        3. Matriks balikan
        4. Interpolasi Polinom
        5. Regresi linier berganda
        6. Keluar

        Sub Menu 1
        1.Metode eliminasi Gauss
        2.Metode eliminasi Gauss-Jordan
        3.Metode matriks balikan
        4.Kaidah Cramer
    */
    public static String batas = "=====================================";

    public static int Menu() {
        clearScreen();
        // Menampilkan pilihan menu dan mengembalikan nilai pilihannya

        Scanner in = new Scanner(System.in);
        System.out.println("MENU : ");
        System.out.println("1. Sistem Persamaaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Regresi linier berganda");
        System.out.println("6. Keluar");


        // minta masukan pilihan
        System.out.print("Pilih menu :");
        int choose = in.nextInt();
        while (choose < 1 || choose > 6) {
            System.out.println();
            System.out.println("Menu salah, silakan ulangi");
            System.out.println(batas);
            System.out.println("MENU : ");
            System.out.println("1. Sistem Persamaaan Linier");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Regresi linier berganda");
            System.out.println("6. Keluar");

            System.out.println(batas);
            System.out.print("Pilih menu :");
            choose = in.nextInt();
        }
        return choose;
    }

    public static int SPLMenu() {
        clearScreen();
        Scanner in = new Scanner(System.in);
        System.out.println("Sistem Persamaan Linear");
        System.out.println(batas);
        System.out.println("MENU : ");
        System.out.println("1.Metode eliminasi Gauss");
        System.out.println("2.Metode eliminasi Gauss-Jordan");
        System.out.println("3.Metode matriks balikan");
        System.out.println("4.Kaidah Cramer");
        System.out.println();


        // minta masukan pilihan
        System.out.print("Pilih menu : ");
        int choose = in.nextInt();
        while (choose < 1 || choose > 4) {
            System.out.println();
            System.out.println("Menu salah, silakan ulangi");
            System.out.println(batas);
            System.out.println("MENU : ");
            System.out.println("1.Metode eliminasi Gauss");
            System.out.println("2.Metode eliminasi Gauss-Jordan");
            System.out.println("3.Metode matriks balikan");
            System.out.println("4.Kaidah Cramer");

            System.out.println(batas);
            System.out.print("Pilih menu :");
            choose = in.nextInt();
        }
        return choose;
    }

    /* Buat clear screen setiap pemanggilan menu */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    /* Buat clear screen setiap pemanggilan menu */

    /* TEST ARRAY BUAT MENGHITUNG SPL lewat GAUSS dan DETERMINAN */
    static double[][] a = { {3, -2, 0, 5, 2},
            {4, 5, 8, 1, 4},
            {0, 1, 2, 1, 5},
            {2, 7, 6, 5, 7}};

    static double[][] b = { {0, 7, -1, 3, 1, 5},
            {0, 3, 4, 1, 7, 7},
            {6, 2, 0, 2, -1, 2},
            {2, 1, 2, 0, 2, 3},
            {3, 4, 1, -2, 1, 4}};

    static double[] c = {0, 5, -12};
    static double[][] d = {{0, 7, -1, 3, 1, 5}, {0, 3, 4, 1, 7, 7}, {6, 2, 0, 2, -1, 2}, {2, 1, 2, 0, 2, 3}, {3, 4, 1, -2, 1, 4}};
    static double[][] e = {{1, 1, -1, -1, 1}, {2, 5, -7, -5, -2}, {2, -1, 1, 3, 4}, {5, 2, -4, 2, 6}};
    static double[][] f = {{2, 3, 4}, {6, 8, 5}, {3, 7, 1}};
    static double[][] g = {{2, 1, 4}, {6, 5, 0}, {1, 2, 0}};
    static double[][] h = {{1, 4, 5, 6}, {2, 0, 6, 3}, {4, 2, 1, 4}};
    static double[][] i = {{1, -1, 2, 5}, {2, -2, 4, 10}, {3, -1, 6, 5}};
    /* TEST ARRAY BUAT MENGHITUNG SPL lewat GAUSS dan DETERMINAN */

    public static void main(String[] args) {
//        int menu = Menu();
//
//         if(menu == 1) {
//             int spl = SPLMenu();
//         }

//        SPL.printMatrix2d(h);
//        System.out.println(Arrays.toString(SPL.eliminasiGauss(h)));
//        System.out.println(SPL.Determinan(h));
//        double[][] hasil = SPL.reduksiOBE(h);
//        SPL.printMatrix2d(hasil);
//        System.out.println(SPL.Determinan(h));
        SPL.eliminasiGauss(g);
    }
}
