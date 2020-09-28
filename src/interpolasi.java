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
}