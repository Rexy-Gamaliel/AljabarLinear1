public class Cramer {
    public double Determinan(Matriks matriks, int variable) {
        // mengembalikan nilai determinan dengan cara cramer's rule

        // variable = -1, maka kembalikan determinan utama
        // variable = 0, kembalikan nilai determinan untuk mencari X1

        // buat matriks dengan kolom yang ditambah /lebih besar
        int newRow = matriks.getRow();
        int newCol = ((matriks.getCol() - 1) * 2) - 1;
        Matriks mainMatriks = Matriks.createMatriks(newRow, newCol);

        // copy dahulu sesuai ukuran matriks koefisien
        int i, j;
        for (i = 0; i < newRow; i++) {
            for (j = 0; j < matriks.getCol(); j++) {
                mainMatriks.setElement(i, j, matriks.getElement(i, j));
            }
        }

        int restCol;
        if (variable == -1) {
            for (i = 0; i < newRow; i++) {
                restCol = 0;
                for (j = matriks.getCol() - 1; j < newCol; j++) {
                    mainMatriks.setElement(i, j, mainMatriks.getElement(i, restCol));
                    restCol++;
                }
            }
        } else {
            // swap column b dengan idx = variable
            mainMatriks.swapCol(variable, matriks.getCol() - 1);

            // gunakan cara cramer mengisi kolom tambahan
            for (i = 0; i < newRow; i++) {
                restCol = 0;
                for (j = matriks.getCol() - 1; j < newCol; j++) {
                    mainMatriks.setElement(i, j, mainMatriks.getElement(i, restCol));
                    restCol++;
                }
            }
        }

        OutputMatrix output = new OutputMatrix();
        output.run(mainMatriks);

        // hitung determinan

        //diagonal positif
        double sum = 0;
        double mult = 1;
        int k;
        for (j = 0; j < matriks.getCol() - 1; j++) {
            k = j;
            mult = 1;
            for (i = 0; i < mainMatriks.getRow(); i++) {
                mult *= mainMatriks.getElement(i, k);
                k++;
            }
            sum += mult;
        }

        // diagonal negatif

        for (j = mainMatriks.getCol() - 1; j > ((mainMatriks.getCol() / 2) - 1); j--) {
            k = j;
            mult = 1;
            for (i = 0; i < mainMatriks.getRow(); i++) {
                mult *= mainMatriks.getElement(i, k);
                k--;
            }
            sum -= mult;
        }

        return sum;
    }
}
