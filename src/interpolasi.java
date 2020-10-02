public class interpolasi {
    public static Matriks MakeInterpolasi(Matriks matriks) {
        // membuat menjadi matriks interpolasi untuk bisa diproses menggunakan gauss-jordan
        Matriks newMatriks = Matriks.createMatriks(matriks.getRow(), matriks.getRow() + 1);
        int i, j;
        // isi kolom 0 dengan 1
        for (i = 0; i < newMatriks.getRow(); i++) {
            newMatriks.setElement(i, 0, 1);
        }

        // isi kolom terakhir dengan nilai y
        double value;
        double X; // untuk pengganti pangkat
        for (i = 0; i < newMatriks.getRow(); i++) {
            value = matriks.getElement(i, 1);
            newMatriks.setElement(i, newMatriks.getCol() - 1, value);
        }

        for (i = 0; i < newMatriks.getRow(); i++) {
            value = matriks.getElement(i, 0);
            X = value;
            for (j = 1; j < newMatriks.getCol() - 1; j++) {
                newMatriks.setElement(i, j, value);
                value *= X;
                // kali value sampai value^n
            }
        }
        //System.out.println("Matriks augmented untuk interpolasi : ");
        //System.out.println(newMatriks);
        return newMatriks;
    }

    // I.S matriks harus sudah bentuk MatriksInterpolasi
    // metode cramer
    public static double HasilTaksiran(Matriks MatriksInterpolasi, double taksiranX) {
        Cramer cramer = new Cramer();
        int i, j;
        double detUtama = cramer.Determinan(MatriksInterpolasi, -1);
        // asumsi selalu valid
        double detX;
        double a;
        double result = 0;
        int power = 0;
        double X;
        for (i = 0; i < MatriksInterpolasi.getCol() - 1; i++) {
            X = Math.pow(taksiranX, power);
            detX = cramer.Determinan(MatriksInterpolasi, i);
            a = (detX / detUtama);
            result += (a * X);
            power++;
        }
        return result;
        // nanti hasil dirounding
    }

    public static double HasilTaksiranInverse(Matriks matriksInterpolasi, double taksiranX) {
        // I.S : Matriks harus sudah bentuk matriks interpolasi
        // menggunakan metode inverse
        // matriks penampung solusi SPL
        int n = matriksInterpolasi.getRow();
        int m = matriksInterpolasi.getCol();

        Matriks B = new Matriks(n, 1, new double[n][1]);
        Matriks A = new Matriks(n, m, new double[n][m]);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                A.setElement(i, j, matriksInterpolasi.getElement(i, j));
            }
        }
        SPLinvers.SplitMatriks(A, B);

        int N = A.getRow();
        Matriks matriksInv = Matriks.createMatriks(N, N);
        InverseKofaktor.hitungInverse(A, matriksInv, N);

        Matriks MatriksX = new Matriks(A.getRow(), 1, new double[A.getRow()][1]);
        MatriksX = Matriks.KaliMatriks(matriksInv, B);

        double result = 0;
        int power = 0;
        double X;
        double koefisienSolusi;
        int i;
        for (i = 0; i < MatriksX.getRow(); i++) {
            X = Math.pow(taksiranX, power);
            koefisienSolusi = MatriksX.getElement(i, 0);
            result += (koefisienSolusi * X);
            power++;
        }

        return result;
    }


    public static double HasilTaksiranGaussJordan(Matriks matriksInterpolasi, double taksiranX) {
        // I.S : Matriks harus sudah bentuk matriks interpolasi
        // menghitung SPL interpolasi dengan gauss-jordan

        double[] gauss = new double[matriksInterpolasi.getRow()];
        gauss = SPLMatriks.eliminasiGaussJordan(matriksInterpolasi, 0);
        int power = 0;
        double result = 0;
        double X;
        for (int i = 0; i < matriksInterpolasi.getRow(); i++) {
            X = Math.pow(taksiranX, power);
            result += (gauss[i] * X);
            power++;
        }
        return result;
    }


    public static double HasilTaksiranGauss(Matriks matriksInterpolasi, double taksiranX) {
        // I.S : matriks sudah bentuk SPL interpolasi
        // F.S mengembalikan nilai taksiran interpolasi

        double[] gauss = new double[matriksInterpolasi.getRow()];
        gauss = SPLMatriks.eliminasiGauss(matriksInterpolasi, 0);

        int power = 0;
        double result = 0;
        double X;
        for (int i = 0; i < matriksInterpolasi.getRow(); i++) {
            X = Math.pow(taksiranX, power);
            result += (gauss[i] * X);
            power++;
        }
        return result;
    }
}