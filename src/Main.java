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

        Menu 1
        1.Metode eliminasi Gauss
        2.Metode eliminasi Gauss-Jordan
        3.Metode matriks balikan
        4.Kaidah Cramer
    */
    public static String batas = "=====================================";

    public static int Menu() {
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
        System.out.print("Pilih menu :");
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


    static double[][] a = {{3, -2, 5, 0, 2},
            {4, 5, 8, 1, 4},
            {1, 1, 2, 1, 5},
            {2, 7, 6, 5, 7}};

    static double[] b = new double[a.length];

    public static void main(String[] args) {
        int menu = Menu();

        // if(menu == 1){
        //     int spl = SPLMenu();
        // } else if(){
        //     // masih kosong
        // }

        SPL.printMatrix2d(a);
        SPL.elminasiGauss(a);
        SPL.printMatrix2d(a);
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(SPL.subtitusiMundur(a, b)));
    }
}
