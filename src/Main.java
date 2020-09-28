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

    Sub Menu 2
    1. Reduksi baris
    2. Cofactor
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
            System.out.println("1. Determinan Reduksi OBE ");
            System.out.println("2. Determinan Kofaktor ");
            System.out.print("Pilih menu: ");
            int pil = scanner.nextInt();

            while (pil > 2 || pil < 1) {
                System.out.println("Inputan Salah. Silahkan masukkam inputan lagi");
                System.out.println();
                System.out.println("1. Determinan Reduksi OBE ");
                System.out.println("2. Determinan Kofaktor ");
                System.out.print("Pilih menu: ");
                pil = scanner.nextInt();
            }

            if (pil == 1) {
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

                double determinan = SPLMatriks.Determinan(matriks);
                System.out.println("Nilai determinan = " + determinan);
            } else if (pil == 2) {
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

                double determinan = kofaktor.cofactor(matriks);
                System.out.println("Nilai determinan = " + determinan);
            } else {
                System.out.println("Masukkan salah");
            }
        } else if (menu == 3) { //invers
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
            double determinan = SPLMatriks.Determinan(matriks);
            if (determinan == 0) {
                System.out.println("Matriks tidak memiliki balikan");
            } else {
                System.out.println("Matriks balikan : ");
                matriks = invers.inverseMatriks(matriks);
                System.out.println(matriks);
            }

        } else if (menu == 4) {
            // interpolasi

            jenisinput = JenisInput();
            if (jenisinput == 1) {
                matriks = input.readFile();
            } else {
                System.out.print("Masukkan nilai derajat interpolasi n : ");
                int n = scanner.nextInt();
                matriks = Matriks.createMatriks(n + 1, 2);
                input.interpolasiRun(matriks);
            }
            // ubah menjadi matriks augmented
            matriks = interpolasi.MakeInterpolasi(matriks);
            System.out.print("Masukkan nilai yang akan ditaksir X : ");
            double X = scanner.nextDouble();
            double result = interpolasi.HasilTaksiran(matriks, X);
            System.out.print("Hasil taksiran P(x), untuk x = " + X + " adalah ");
            System.out.format("%.4f", result);
        } else if (menu == 5) {
            // regresi linear
        } else {
            System.out.println();
            System.out.println("Terima kasih telah menggunakan progam kami");
            System.out.println("============ Semoga Bermanfaat ===========");
        }

    }
}
