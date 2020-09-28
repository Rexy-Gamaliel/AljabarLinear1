public class SPLinvers {
    public static Matriks SPLinvers(Matriks matriks) {
        // Prekondisi : Determinan matriks koefisien != 0 / matriks koefisisen memiliki invers

        // mengembalikan matriks solusi berukuran  matriks.getrow() x 1
        // rumus : AX = B
        //  A^-1AX = A^-1B
        // X = A^-1 B
        // ambil matriks keofisisen
        Matriks koefMatriks = Matriks.createMatriks(matriks.getRow(), matriks.getCol() - 1);
        int i, j;
        double value;
        for (i = 0; i < koefMatriks.getRow(); i++) {
            for (j = 0; j < koefMatriks.getCol(); j++) {
                value = matriks.getElement(i, j);
                koefMatriks.setElement(i, j, value);
            }
        }

        // simpan nilai matriks hasil
        Matriks hasil = Matriks.createMatriks(matriks.getRow(), 1);
        for (i = 0; i < matriks.getRow(); i++) {
            value = matriks.getElement(i, matriks.getCol() - 1);
            hasil.setElement(i, 0, value);
        }

        // inverse matriks koefisisen
        koefMatriks = invers.inverseMatriks(koefMatriks);

        Matriks solutionMatriks = Matriks.createMatriks(matriks.getRow(), 1);
        solutionMatriks = Matriks.KaliMatriks(koefMatriks, hasil);
        return solutionMatriks;
    }


    public static void InverseSolution(Matriks matriks) {
        // menerima matriks hasil dari fungsi SPLInvers
        // menampilkan output solusi
        int i;
        System.out.println("Solusi dari Sistem Persamaan linear tersebut adalah : ");
        for (i = 0; i < matriks.getRow(); i++) {
            System.out.print("X" + i + " = ");
            System.out.print(matriks.getElement(i, 0));
            System.out.println();
        }
    }
}
