//import java.util.Arrays;

public class SPLinvers {
    private static boolean IsInversValid = true;

    // Getter/setter
    public static boolean getIsInversValid() {
        return IsInversValid;
    }

    public static void setIsInversValid(boolean x) {
        IsInversValid = x;
    }

    /* Melakukan split pada matriks augmented */
    public static void SplitMatriks(Matriks MKiri, Matriks MKanan) {
        /**
         * I.S.: MKiri merupakan matriks augmented berukuran n x m, yang merupakan penggabungan antara
         * matriks X pada m-1 kolom pertama dan matriks Y pada kolom terakhir
         * MKanan merupakan matriks sembarang berukuran n x 1 untuk menampung matriks Y
         * F.S.: MKiri merupakan matriks X berukuran n x m-1, MKanan merupakan matriks Y berukuran n x 1
         */
        int n = MKiri.getRow();
        int m = MKiri.getCol();

        // Menampung isi matriks Y pada MKanan
        for (int i = 0; i < n; i++) {
            MKanan.setElement(i, 0, MKiri.getElement(i, m - 1));
        }
        MKiri.setCol(m - 1);
        // Menampung isi matriks X pada MTemp untuk kemudian dipindahkan ke MKiri
        /*
        Matriks MTemp = new Matriks(n, m - 1, new double[n][m - 1]);
        for (int i = 0; i < MTemp.getRow(); i++) {
            for (int j = 0; j < MTemp.getCol(); j++) {
                MTemp.setElement(i, j, MKiri.getElement(i, j));
            }
        }
        MKiri = MTemp;
        */
    }

    /* Menghasilkan invers dari matriks */
    public static Matriks inverseMatriks(Matriks MInput) {
        /* Prekondisi: MInput merupakan matriks persegi */
        /**
         * Kamus Lokal:
         * N              : int         { ukuran matrix }
         * MInputExtended : double[][]  { komponen array dari MInput, diextend dengan matriks identitas di sebelah kanan }
         * MProses        : Matriks     { matriks yang dioperasikan untuk menghasilkan matriks invers }
         * MInvers        : Matriks     { hasil matriks invers }
         */
        int N = MInput.getCol();
        Matriks MProses, MInvers;
        double[][] MInputExtended = new double[N][2*N];
        int i, j;
    
        // Menginisialisasi MInvers
        for (i=0; i<N; i++) {
          for (j=0; j<N; j++) {
            if (i==j) {
                MInputExtended[i][j] = MInput.getElement(i, j);
                MInputExtended[i][j+N] = 1;
            }
            else {
                MInputExtended[i][j] = MInput.getElement(i, j);
                MInputExtended[i][j+N] = 0;
            }
          }
        }
        MProses = new Matriks(N, 2*N, MInputExtended);
    
        MProses = SPLMatriks.reduksiOBE(MProses);
        MProses = SPLMatriks.reduksiOBEJordan(MProses);
        
        MInvers = new Matriks(N, N, new double[N][N]);
        for (i=0; i<N; i++) {
          for (j=0; j<N; j++) {
            MInvers.setElement(i, j, MProses.getElement(i, j+N));
          }
        }
    
        return MInvers;
      }

    /* Menampilkan solusi SPL */
    public static Matriks solusiSPLInverse(Matriks MAugmented) {
        /**Menentukan solusi SPL menggunakan metode invers
         * Diberikan matriks Ax = B,
         * maka x = A^(-1) B
         */
        /**MAugmented berukuran n x m terdefinisi dan merupakan input user
         * Lalu fungsi mengembalikan matriks x berukuran n x 1
         * Proses: memecah matriks MAugmented menjadi hanya matriks X,
         *  mencari inversnya, untuk invers yg valid dihitung x = A^(-1) B
         */
        int n = MAugmented.getRow();
        int m = MAugmented.getCol();

        // Melakukan spliting terhadap MAugmented
        Matriks B = new Matriks(n, 1, new double[n][1]);
        Matriks A = new Matriks(n, m, new double[n][m]);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                A.setElement(i, j, MAugmented.getElement(i, j));
            }
        }
        SplitMatriks(A, B);

        // buat matriks baru penampung A untuk di cek determinannya
        Matriks matriksTemp = Matriks.createMatriks(A.getRow(), A.getCol());
        for (int i = 0; i < matriksTemp.getRow(); i++) {
            for (int j = 0; j < matriksTemp.getCol(); j++) {
                matriksTemp.setElement(i, j, A.getElement(i, j));
            }
        }

        if (kofaktor.cofactor(matriksTemp) == 0) {
            // tidak memiliki determinan
            setIsInversValid(false);
        }
        Matriks AInvers = inverseMatriks(A);

        Matriks MatriksX = new Matriks(A.getRow(), 1, new double[A.getRow()][1]);

        MatriksX = Matriks.KaliMatriks(AInvers, B);
        return MatriksX;
    }

    public static void printSolusiSPLInvers(Matriks MatriksX) {
        WriteFile.DelFileExist();
        if (!(getIsInversValid())) {
            WriteFile.SaveFile("Matriks tidak memiliki invers dan tidak dapat dicari hasil SPL-nya menggunakan metode ini.");
            System.out.println("Matriks tidak memiliki invers dan tidak dapat dicari hasil SPL-nya menggunakan metode ini.");
        } else {
            // memberikan hasil unik
            System.out.println("Solusi unik:");
            double Xi;
            String stringXi;
            for (int i = 0; i < MatriksX.getRow(); i++) {
                Xi = MatriksX.getElement(i, 0);
                System.out.print("x_" + (i + 1) + " = ");
                System.out.format("%.4f", Xi);
                System.out.println();
                stringXi = "x_" + i + " = ";
                WriteFile.SaveFile(stringXi);
                WriteFile.SaveFile(Double.toString(Xi));
            }
        }
        WriteFile.SaveSuccess();
    }
}