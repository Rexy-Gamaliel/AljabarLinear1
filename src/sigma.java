public class sigma {
    public static double Sigma1(Matriks matriks, int indeks) {
        //Menghitung sigma x(kolom)i, i dari 0 sampai n-1
        // Untuk mendapat Elemen-Elemen baris pertama dan kolom pertama, matriks regresi
        double result = 0;
        int n = matriks.getRow();

        int i;
        for (i = 0; i < n; i++) {
            result += matriks.getElement(indeks, i);
        }
        return result;
    }


    public static double Sigma4(Matriks matriks, int indeks) {
        // sigma (x(baris)i kali yi)
        double result = 0;
        int n = matriks.getRow();
        int j;
        for (j = 0; j < matriks.getRow(); j++) {
            result += (matriks.getElement(indeks, j) * matriks.getElement(j, matriks.getCol() - 1)); // tergantung kolom y berada dimana
        }
        return result;
    }
}
