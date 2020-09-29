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
        MKiri.setCol(m-1);
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
    public static Matriks inverseMatriks(Matriks matrik) {
        // Prekondisi : Matriks berukuran nxn
        Matriks identitas = Matriks.createMatriks(matrik.getRow(), matrik.getCol());
        // isi matriks identitas

        int i, j;
        for (i = 0; i < identitas.getRow(); i++) {
            for (j = 0; j < identitas.getCol(); j++) {
                ;
                if (i == j) {
                    identitas.setElement(i, j, 1);
                } else {
                    identitas.setElement(i, j, 0);
                }
            }
        }

        // matriks yang akan diproses oleh OBE
        // ukuran 2x lebih besar karena akan diconcat dengan matriks identitas
        Matriks M = Matriks.createMatriks(matrik.getRow(), matrik.getCol() * 2);
        for (i = 0; i < matrik.getRow(); i++) {
            for (j = 0; j < matrik.getCol(); j++) {
                ;
                M.setElement(i, j, matrik.getElement(i, j));
            }
        }

        // extend identitas
        int k;
        for (i = 0; i < matrik.getRow(); i++) {
            k = 0;
            for (j = matrik.getCol(); j < M.getCol(); j++) {
                M.setElement(i, j, identitas.getElement(i, k));
                k++;
            }
        }

        M = SPLMatriks.reduksiOBE(M);
        M = SPLMatriks.reduksiOBEJordan(M);

        for (i = 0; i < matrik.getRow(); i++) {
            k = 0;
            for (j = matrik.getCol(); j < M.getCol(); j++) {
                identitas.setElement(i, k, M.getElement(i, j));
                k++;
            }
        }
        return identitas;
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
        
        for (int i=0; i<n; i++) {
          for (int j=0; j<m; j++) {
            A.setElement(i, j, MAugmented.getElement(i, j));
          }
        }
        SplitMatriks(A, B);

        Matriks AInvers = inverseMatriks(A);

        Matriks MatriksX = new Matriks(A.getRow(), 1, new double[A.getRow()][1]);

        MatriksX = Matriks.KaliMatriks(AInvers, B);

        return MatriksX;
    }

    public static void printSolusiSPLInvers(Matriks MatriksX) {
        if (!(getIsInversValid())) {
            System.out.println("Matriks tidak memiliki invers dan tidak dapat dicari hasil SPL-nya menggunakan metode ini.");
        } else {
            // memberikan hasil unik
            WriteFile.DelFileExist();
            System.out.println("Solusi unik:");
            double Xi;
            String stringXi;
            for (int i = 0; i < MatriksX.getRow(); i++) {
              Xi = MatriksX.getElement(i,0);
                System.out.print("x_" + (i + 1) + " = ");
                System.out.format("%.4f", Xi);
                System.out.println();
                stringXi = "x_" + i + " = ";
                WriteFile.SaveFile(stringXi);
                WriteFile.SaveFile(Double.toString(Xi));
            }
            WriteFile.SaveSuccess();
        }
    }
}
