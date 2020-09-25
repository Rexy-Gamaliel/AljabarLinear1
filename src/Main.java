import java.util.Arrays;
import java.util.*;

public class Main {
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
            System.out.print("Pilih menu : ");
            choose = in.nextInt();
        }
        return choose;
    }

    public static int SPLMenu() {
        clearScreen();
        Scanner in = new Scanner(System.in);
        System.out.println(batas);
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
            System.out.print("Pilih menu : ");
            choose = in.nextInt();
        }
        return choose;
    }

    // Mengembalikan jenis input apakah file atau console
    public static int JenisInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.File");
        System.out.println("2.Console");
        System.out.print("Pilih jenis input :");
        int jenisinput = scanner.nextInt();
        while (jenisinput < 1 && jenisinput > 2) {
            clearScreen();
            System.out.println(batas);
            System.out.println("Masukkan salah");
            System.out.println(batas);
            System.out.println("1.File");
            System.out.println("2.Console");
            System.out.print("Pilih jenis input :");
        }
        return jenisinput;
    }

    /* Buat clear screen setiap pemanggilan menu */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    /* Buat clear screen setiap pemanggilan menu */

    /* TEST ARRAY BUAT MENGHITUNG SPL lewat GAUSS dan DETERMINAN */
    static double[][] a = {{3, -2, 0, 5, 2},
            {4, 5, 8, 1, 4},
            {0, 1, 2, 1, 5},
            {2, 7, 6, 5, 7}};

    static double[][] b = {{0, 7, -1, 3, 1, 5},
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
    static double[][] j = {{0, 1, 5}, {3, -6, 9}, {5, 9, 1}};
    static double[][] k = {{1, 1, 1, 0}, {2, 3, 1, 1}, {3, 1, 2, 1}};
    static double[][] l = {{1, 1, 2, 4}, {2, -1, 1, 2}, {1, 2, 3, 6}};
    static double[][] m = {{1, 1, 2, 4}, {2, -1, 1, 2}, {1, 2, 3, 7}};
    /* TEST ARRAY BUAT MENGHITUNG SPL lewat GAUSS dan DETERMINAN */

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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputMatrix input = new InputMatrix();
        Matriks matriks;
        int jenisinput;
        int menu = Menu();

        if (menu == 1) {
            int spl = SPLMenu();
            clearScreen();
            System.out.println();

            jenisinput = JenisInput();
            if (jenisinput == 1) {
                matriks = input.readFile();
            } else {
                int n, m;
                System.out.print("Masukkan banyaknya peubah n :");
                n = scanner.nextInt();
                System.out.print("Masukkan banyaknya persamaan m : ");
                m = scanner.nextInt();
                matriks = Matriks.createMatriks(m, n + 1);
                input.SPLrun(matriks);
            }

            //matriks sudah terisi
            if (spl == 1) {
                // Eliminasi Gauss
                SPLMatriks.eliminasiGauss(matriks);
            } else if (spl == 2) {
                // eliminasi gauss jordan
                SPLMatriks.eliminasiGaussJordan(matriks);
            } else if (spl == 3) {
                // metode matriks balikan
            } else if (spl == 4) {
                // metode cramer
                SPLcramer.Solusi(matriks);
            }

        } else if (menu == 2) {
            // Determinan
            jenisinput = JenisInput();
            if (jenisinput == 1) {
                matriks = input.readFile();
            } else {
                System.out.print("Masukkan ukuran matriks[n][n] n: ");
                int n = scanner.nextInt();
                matriks = Matriks.createMatriks(n, n);
                input.DetRun(matriks);
                System.out.println("Matriks : ");
                System.out.println(matriks);
            }
            clearScreen();
            if (matriks.getRow() == matriks.getCol()) {
                double determinan = SPLMatriks.Determinan(matriks);
                System.out.println("Nilai determinan = " + determinan);
            } else {
                System.out.println("Matriks harus berukuran N x N");
            }
        } else if (menu == 3) {

        }

        // Matriks matriks = new Matriks(3, 3, f);
        //     SPLMatriks.Determinan(matriks)
        //   SPLMatriks.eliminasiGaussJordan(matriks);
    }
}
