public class sigma {
    public static double Sigma1(Matriks matriks, int indeks) {
        // Menghitung sigma x(indeks)i, i dari 0 sampai n-1
        // Untuk mendapat Elemen-Elemen baris pertama dan kolom pertama pada matriks regresi
        /**
         * indeks        indeks (baris pada kolom ke-0) atau (kolom pada baris ke-0) pada matriks regresi
         * matriks       matriks input user
        */
        double result = 0;
        int n = matriks.getRow();

        int i;
        for (i = 0; i < n; i++) {
            result += matriks.getElement(i, indeks-1);
        }
        return result;
    }


    public static double Sigma4(Matriks matriks, int baris) {
        /**matriks merupakan matriks augmented input, baris don kolom merupakan
         * indeks elemen pada matriks regresi
         * Menghitung sigma (x(baris)i * yi)
         */
        double result = 0;
        int n = matriks.getRow();
        int i;
        for (i = 0; i < n; i++) {
            result += (matriks.getElement(i, baris-1) * matriks.getElement(i, matriks.getCol()-1)); // tergantung kolom y berada dimana
        }
        return result;
    }

    public static double Sigma23 (Matriks matriks, int baris, int kolom) {
        /**matriks merupakan matriks augmented input, baris don kolom merupakan
         * indeks elemen pada matriks regresi
         * Menghitung sigma (x(baris)i * x(kolom)i)
         */
        double result = 0;
        int n = matriks.getRow();
        int i;
        for (i=0; i<n; i++) {
            result += matriks.getElement(i, baris-1) * matriks.getElement(i, kolom-1);
        }
        return result;
    }
}
