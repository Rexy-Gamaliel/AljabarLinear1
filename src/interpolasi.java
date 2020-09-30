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
        System.out.println("Matriks augmented untuk interpolasi : ");
        System.out.println(newMatriks);
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
        Matriks hasil = Matriks.createMatriks(matriksInterpolasi.getRow(), 1);
        hasil = SPLinvers.solusiSPLInverse(matriksInterpolasi);
        System.out.println(hasil);
        double result = 0;
        int power = 0;
        double X;
        double koefisienSolusi;
        int i;
        for (i = 0; i < hasil.getRow(); i++) {
            X = Math.pow(taksiranX, power);
            koefisienSolusi = hasil.getElement(i, 0);
            result += (koefisienSolusi * X);
            power++;
        }
        return result;
    }


    public static double HasilTaksiranGaussJordan(Matriks matriksInterpolasi, double taksiranX) {
        // I.S : Matriks harus sudah bentuk matriks interpolasi
        // menghitung SPL interpolasi dengan gauss-jordan
        double result = 0;

        matriksInterpolasi = SPLMatriks.reduksiOBEJordan(matriksInterpolasi);

        // salin hasil ke dalam matriks baru
        double value;
        Matriks MatriksSolusi = Matriks.createMatriks(matriksInterpolasi.getRow(), 1);
        for (int i = 0; i < matriksInterpolasi.getRow(); i++) {
            value = matriksInterpolasi.getElement(i, matriksInterpolasi.getCol() - 1);
            MatriksSolusi.setElement(i, 0, value);
        }

        if (SPLMatriks.isNotHaveSolution(matriksInterpolasi)) {
            System.out.println("SPL tidak ada solusi");
        } else if (SPLMatriks.isParametrik(matriksInterpolasi) != -1) {
            int row = SPLMatriks.isParametrik(matriksInterpolasi);
            SPLMatriks.printParametrik(matriksInterpolasi, row);
        } else {
            int power = 0;
            double X, koefisienSolusi;
            for (int i = 0; i < MatriksSolusi.getRow(); i++) {
                X = Math.pow(taksiranX, power);
                koefisienSolusi = MatriksSolusi.getElement(i, 0);
                result += (koefisienSolusi * X);
                power++;
            }
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