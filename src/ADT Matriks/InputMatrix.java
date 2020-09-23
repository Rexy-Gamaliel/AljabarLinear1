import java.util.Scanner;

public class InputMatrix {
    // Contoh
    // InputMatrix input = new InputMatrix();
    // input.SPLrun(matriks);


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
                    System.out.println("Masukkan elemen b" + i + " : ");
                } else {
                    System.out.println("Masukkan elemen a" + i + j + " : ");
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
                System.out.println("Masukkan elemen a" + i + j + " : ");
                double value = scanner.nextDouble();
                matriks.setElement(i, j, value);
            }
        }
    }
}
