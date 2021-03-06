import java.util.Arrays;
import java.util.*;

public class Main {
    public static String batas = "=====================================";

    public static int Menu() {
        // Menampilkan pilihan menu dan mengembalikan nilai pilihannya

        Scanner in = new Scanner(System.in);
        System.out.println(batas);
        System.out.println("  MENU : ");
        System.out.println("  1. Sistem Persamaaan Linier");
        System.out.println("  2. Determinan");
        System.out.println("  3. Matriks balikan");
        System.out.println("  4. Interpolasi Polinom");
        System.out.println("  5. Regresi linier berganda");
        System.out.println("  6. Keluar");
        System.out.println();

        // minta masukan pilihan
        System.out.print("  Pilih menu : ");
        int choose = in.nextInt();
        while (choose < 1 || choose > 6) {
            System.out.println();
            System.out.println("  Menu salah, silakan ulangi");
            System.out.println(batas);
            System.out.println("  MENU : ");
            System.out.println("  1. Sistem Persamaaan Linier");
            System.out.println("  2. Determinan");
            System.out.println("  3. Matriks balikan");
            System.out.println("  4. Interpolasi Polinom");
            System.out.println("  5. Regresi linier berganda");
            System.out.println("  6. Keluar");

            System.out.println(batas);
            System.out.print("  Pilih menu : ");
            choose = in.nextInt();
        }
        return choose;
    }

    public static int SPLMenu() {
        Scanner in = new Scanner(System.in);
        System.out.println(batas);
        System.out.println("  Sistem Persamaan Linear");
        System.out.println(batas);
        System.out.println("  MENU : ");
        System.out.println("  1.Metode eliminasi Gauss");
        System.out.println("  2.Metode eliminasi Gauss-Jordan");
        System.out.println("  3.Metode matriks balikan");
        System.out.println("  4.Kaidah Cramer");
        System.out.println();

        // minta masukan pilihan
        System.out.print("  Pilih menu : ");
        int choose = in.nextInt();
        while (choose < 1 || choose > 4) {
            System.out.println();
            System.out.println("  Menu salah, silakan ulangi");
            System.out.println(batas);
            System.out.println("  MENU : ");
            System.out.println("  1.Metode eliminasi Gauss");
            System.out.println("  2.Metode eliminasi Gauss-Jordan");
            System.out.println("  3.Metode matriks balikan");
            System.out.println("  4.Kaidah Cramer");

            System.out.println(batas);
            System.out.print("  Pilih menu : ");
            choose = in.nextInt();
        }
        return choose;
    }

    // Mengembalikan jenis input apakah file atau console
    public static int JenisInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("  1.File");
        System.out.println("  2.Console");
        System.out.print("  Pilih jenis input : ");
        int jenisinput = scanner.nextInt();
        while (jenisinput < 1 && jenisinput > 2) {
            System.out.println(batas);
            System.out.println("  Masukkan salah");
            System.out.println(batas);
            System.out.println("  1.File");
            System.out.println("  2.Console");
            System.out.print("  Pilih jenis input :");
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
            System.out.println();

            jenisinput = JenisInput();
            if (jenisinput == 1) {
                matriks = input.readFile();
            } else {
                int n, m;
                System.out.print("  Masukkan banyaknya peubah n :");
                n = scanner.nextInt();
                System.out.print("  Masukkan banyaknya persamaan m : ");
                m = scanner.nextInt();
                matriks = Matriks.createMatriks(m, n + 1);
                input.SPLrun(matriks);
            }

            /** >>> Nambahin variabel buat nampung hasil
             * Saran: bikin variabel tipe Matriks buat nyimpen hasilnya (biar bisa di-save)
             *        bikin juga prosedur buat nge print hasilnya itu
             * (Liat contoh yg metode balikan)
             */

            Matriks HasilSPL = new Matriks(matriks.getRow(), 1, new double[matriks.getRow()][1]);
            //matriks sudah terisi
            if (spl == 1) {
                // Eliminasi Gauss
                SPLMatriks.eliminasiGauss(matriks, 1);
            } else if (spl == 2) {
                // eliminasi gauss jordan
                SPLMatriks.eliminasiGaussJordan(matriks, 1);
            } else if (spl == 3) {
                // metode matriks balikan
                if ((matriks.getRow() + 1) == (matriks.getCol())) {
                    HasilSPL = SPLinvers.solusiSPLInverse(matriks);
                    SPLinvers.printSolusiSPLInvers(HasilSPL);
                } else {
                    System.out.println("  Tidak dapat menggunakan metode ini");
                }
            } else if (spl == 4) {
                // metode cramer
                if ((matriks.getRow() + 1) == matriks.getCol()) {
                    SPLcramer.Solusi(matriks);
                } else {
                    System.out.println("  Tidak dapat menggunakan metode ini");
                }
            }


        } else if (menu == 2) {
            // determinan
            System.out.println(batas);
            System.out.println("  Determinan");
            System.out.println(batas);

            System.out.println("  1. Determinan Reduksi OBE ");
            System.out.println("  2. Determinan Kofaktor ");
            System.out.print("  Pilih menu: ");
            int pil = scanner.nextInt();

            while (pil > 2 || pil < 1) {
                System.out.println("  Inputan Salah. Silahkan masukkam inputan lagi");
                System.out.println();
                System.out.println("  1. Determinan Reduksi OBE ");
                System.out.println("  2. Determinan Kofaktor ");
                System.out.print("  Pilih menu: ");
                pil = scanner.nextInt();
            }

            jenisinput = JenisInput();
            if (jenisinput == 1) {
                matriks = input.readFile();
            } else {
                System.out.print("  Masukkan ukuran matriks[n][n] n: ");
                int n = scanner.nextInt();
                matriks = Matriks.createMatriks(n, n);
                input.DetRun(matriks);
                System.out.println("  Matriks : ");
                System.out.println(matriks);
            }

            double determinan = 0;
            if (pil == 1) {
                determinan = SPLMatriks.Determinan(matriks);
                System.out.println("  Nilai determinan = " + determinan);
            } else if (pil == 2) {
                determinan = kofaktor.cofactor(matriks);
                System.out.println("  Nilai determinan = " + determinan);
            }

            // bagian save file
            WriteFile.DelFileExist();
            WriteFile.SaveFile("Nilai determinan matriks adalah :");
            WriteFile.SaveFile(Double.toString(determinan));
            WriteFile.SaveSuccess();


        } else if (menu == 3) { //invers
            System.out.println(batas);
            System.out.println("  Matriks Balikan");
            System.out.println(batas);
            jenisinput = JenisInput();
            if (jenisinput == 1) {
                matriks = input.readFile();
            } else {
                System.out.print("  Masukkan ukuran matriks[n][n] n: ");
                int n = scanner.nextInt();
                matriks = Matriks.createMatriks(n, n);
                input.DetRun(matriks);
                System.out.println("  Matriks : ");
                System.out.println(matriks);
            }

            // matriks cek determinan
            Matriks tempMatriks = Matriks.createMatriks(matriks.getRow(), matriks.getCol());
            int i, j;
            for (i = 0; i < matriks.getRow(); i++) {
                for (j = 0; j < matriks.getCol(); j++) {
                    tempMatriks.setElement(i, j, matriks.getElement(i, j));
                }
            }

            double determinan = SPLMatriks.Determinan(tempMatriks);
            if (determinan == 0) {
                System.out.println("  Matriks tidak memiliki balikan");
            } else {
                System.out.println("  1. Matriks metode OBE ");
                System.out.println("  2. Matriks metode adjoint ");
                System.out.print("  Pilih metode invers : ");
                int pil = scanner.nextInt();
                while (pil > 2 || pil < 1) {
                    System.out.println("  Inputan Salah. Silahkan masukkam inputan lagi");
                    System.out.println();
                    System.out.println("  1. Matriks metode OBE ");
                    System.out.println("  2. Matriks metode adjoint ");
                    System.out.print("  Pilih menu: ");
                    pil = scanner.nextInt();
                }

                int N = matriks.getRow();
                Matriks matriksInv = Matriks.createMatriks(N, N);

                if (pil == 1) {
                    // metode OBE
                    matriks = SPLinvers.inverseMatriks(matriks);
                } else if (pil == 2) {
                    // metode cofactor
                    InverseKofaktor.hitungInverse(matriks, matriksInv, N);
                    matriks = matriksInv;
                }

                // print matriks balikan
                System.out.println("  Matriks balikan : ");
//                System.out.println(matriks);
                SPLMatriks.printMatrix2d(matriks);

                // save file ke result.txt
                WriteFile.DelFileExist();
                WriteFile.SaveFile("Matriks inverse : ");
                WriteFile.addNewline();
                for (i = 0; i < matriks.getRow(); i++) {
                    for (j = 0; j < matriks.getCol(); j++) {
                        WriteFile.SaveFile(Double.toString(matriks.getElement(i, j)));
                    }
                    WriteFile.addNewline();
                }
                WriteFile.SaveSuccess();
            }
        } else if (menu == 4) {
            // interpolasi
            System.out.println(batas);
            System.out.println("  Interpolasi");
            System.out.println(batas);
            jenisinput = JenisInput();
            if (jenisinput == 1) {
                matriks = input.readFile();
            } else {
                System.out.print("  Masukkan nilai derajat interpolasi n : ");
                int n = scanner.nextInt();
                matriks = Matriks.createMatriks(n + 1, 2);
                input.interpolasiRun(matriks);
            }

            // ubah menjadi matriks augmented interpolasi
            matriks = interpolasi.MakeInterpolasi(matriks);
            System.out.print("  Masukkan nilai yang akan ditaksir X : ");
            double X = scanner.nextDouble();

            // cek determinan
            Matriks tempMatriks = Matriks.createMatriks(matriks.getRow(), matriks.getCol());
            int i, j;
            for (i = 0; i < matriks.getRow(); i++) {
                for (j = 0; j < matriks.getCol(); j++) {
                    tempMatriks.setElement(i, j, matriks.getElement(i, j));
                }
            }
            Cramer funct = new Cramer();
            double determinan = funct.Determinan(tempMatriks, -1);

            // menu SPL
            int spl = SPLMenu();
            double result = 0;
            if (determinan == 0) {
                System.out.println("  Tidak dapat menaksir X, dikarenakan solusi tidak unik");
            } else {
                if (spl == 1) {
                    // Eliminasi Gauss
                    result = interpolasi.HasilTaksiranGauss(matriks, X);
                } else if (spl == 2) {
                    // eliminasi gauss jordan
                    result = interpolasi.HasilTaksiranGaussJordan(matriks, X);
                } else if (spl == 3) {
                    // metode matriks balikan
                    result = interpolasi.HasilTaksiranInverse(matriks, X);
                } else if (spl == 4) {
                    // metode cramer
                    result = interpolasi.HasilTaksiran(matriks, X);
                }

                System.out.print("  Hasil taksiran P(x), untuk x = " + X + " adalah ");
                System.out.format("%.4f", result);
                System.out.println();

                String x = Double.toString(result);
                WriteFile.DelFileExist();
                WriteFile.SaveFile("Hasil taksiran P(x) adalah");
                WriteFile.SaveFile(x);
                WriteFile.addNewline();
                WriteFile.SaveFile("Untuk X =");
                WriteFile.SaveFile(Double.toString(X));
                System.out.println();
                WriteFile.SaveSuccess();
            }


        } else if (menu == 5) { // regresi linear
            System.out.println(batas);
            System.out.println("  Regresi Linear");
            System.out.println(batas);

            jenisinput = JenisInput();
            /* Menginput ukuran matriks */
            Matriks MAugmentedReg;
            if (jenisinput == 1) {      // input file
                MAugmentedReg = input.readFile();
            } else {                    // input manual
                System.out.println("  Masukkan nilai k (banyaknya variabel independen x), k = ");
                int k = scanner.nextInt();
                System.out.println("  Masukkan nilai n (banyaknya persamaan), n = ");
                int n = scanner.nextInt();
                /* Membuat matriks Augmented */
                MAugmentedReg = Matriks.createMatriks(n, k + 1);
                input.regresiRunMan(MAugmentedReg);
            }
            // elemen MAugmentedReg terdefinisi
            // proses
            Matriks MTest = Matriks.createMatriks(1, MAugmentedReg.getCol());
            //Matriks MTest;
            input.regresiTestMan(MTest);
            regresi.ComputeRegresi(MAugmentedReg, MTest);

        } else {
            System.out.println();
            System.out.println("  Terima kasih telah menggunakan progam kami");
            System.out.println("============ Semoga Bermanfaat ===========");
        }
    }
}