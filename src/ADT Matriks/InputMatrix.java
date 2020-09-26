import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputMatrix {
    // Contoh
    // InputMatrix input = new InputMatrix();
    // input.SPLrun(matriks);

    public Matriks readFile() {
        // membaca file dan memasukan nilainya ke matriks,
        // Mengembalikan matriks augmented yang terbaca dari file
        Scanner input = new Scanner(System.in);
        System.out.println("Masukkan path dari file : ");
        String filename = input.nextLine();

        File file = new File(filename);
        Scanner read = null;
        try {
            read = new Scanner(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Cari ada berapa baris dalam text
        int row = 0;
        while (read.hasNextLine()) {
            row++;
            read.nextLine();
        }

        Scanner readSumData = null;
        try {
            readSumData = new Scanner(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int data = 0;
        while (readSumData.hasNext()) {
            data++;
            readSumData.next();
        }
        int col = data / row;


        Scanner readData = null;
        try {
            readData = new Scanner(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Matriks augmented = Matriks.createMatriks(row, col);
        int i, j;
        double value;
        for (i = 0; i < augmented.getRow(); i++) {
            for (j = 0; j < augmented.getCol(); j++) {
                value = readData.nextDouble();
                augmented.setElement(i, j, value);
            }
        }
        System.out.println("Matriks augmented : ");
        System.out.println(augmented);
        return augmented;
    }

    // Matriks sebelumnya sudah meminta memasukan n jumlah variabel dan m persamaan
    // nanti ukuran matriks diassign dengan
    // Matriks matriks = Matriks.createMatriks(m, n+1);
    public void SPLrun(Matriks matriks) {
        Scanner scanner = new Scanner(System.in);
        int i, j;
        for (i = 0; i < matriks.getRow(); i++) {
            for (j = 0; j < matriks.getCol(); j++) {
                // elemen terakhir input diberi pesan
                if (j == matriks.getCol() - 1) {
                    System.out.print("Masukkan elemen b" + i + " : ");
                } else {
                    System.out.print("Masukkan elemen a" + i + j + " : ");
                }
                double value = scanner.nextDouble();
                matriks.setElement(i, j, value);
            }
        }
    }

    // input untuk determinan dan matriks balikan
    // masukan dari keyboard adalah n dan koefisien aij
    // sehingga buat matriks berukuran matriks[n][n]
    public void DetRun(Matriks matriks) {
        Scanner scanner = new Scanner(System.in);
        int i, j;
        for (i = 0; i < matriks.getRow(); i++) {
            for (j = 0; j < matriks.getCol(); j++) {
                System.out.print("Masukkan elemen a" + i + j + " : ");
                double value = scanner.nextDouble();
                matriks.setElement(i, j, value);
            }
        }
    }

    // input interpolasi
    // ukuran matriks[n+1][2]
    // input x yang ditaksir ada di main
    public void interpolasiRun(Matriks matriks) {
        Scanner scanner = new Scanner(System.in);
        int i, j;
        for (i = 0; i < matriks.getRow(); i++) {
            for (j = 0; j < matriks.getCol(); j++) {
                if (j == 0) {
                    System.out.print("Masukkan nilai X" + i + " : ");
                } else if (j == 1) {
                    System.out.print("Masukkan nilai Y" + i + " : ");
                }
                double value = scanner.nextDouble();
                matriks.setElement(i, j, value);
            }
        }
    }

    // input n peubah diluar
    // input k diluar
    // spl run regresi
}
