public class Cramer {
    public double Determinan(Matriks matriks, int variable) {
        // prekondisi ukuran matriks n x n+1
        // mengembalikan nilai determinan dengan cara cramer's rule
        // variable = -1, maka kembalikan determinan utama
        // variable = 0, kembalikan nilai determinan untuk mencari X1

        int i, j;
        double determinan;
        double value;

        Matriks newMatriks = Matriks.createMatriks(matriks.getRow(), matriks.getRow());

        if (variable != -1) {
            for (i = 0; i < newMatriks.getRow(); i++) {
                for (j = 0; j < newMatriks.getCol(); j++) {
                    if(j == variable){
                        value = matriks.getElement(i, matriks.getCol()-1);
                    } else{
                        value = matriks.getElement(i, j);
                    }
                    newMatriks.setElement(i, j, value);
                }
            }
        } else {
            for (i = 0; i < newMatriks.getRow(); i++) {
                for (j = 0; j < newMatriks.getCol(); j++) {
                    value = matriks.getElement(i, j);
                    newMatriks.setElement(i, j, value);
                }
            }
        }
        determinan = SPLMatriks.Determinan(newMatriks);
        return determinan;
    }
}

/*

     double value;
        for (i = 0; i < matriks.getRow(); i++) {
            for (j = 0; j < matriks.getCol() - 1; j++) {
                value = matriks.getElement(i, j);
                newMatriks.setElement(i, j, value);
            }
        }

        double determinan = 1;
        if (variable >= 0 && variable < matriks.getCol() - 1) {
            // siapkan array b
            double[] columnB = new double[matriks.getRow()]; // berukuran sepanjang row
            for (i = 0; i < matriks.getRow(); i++) {
                columnB[i] = matriks.getElement(i, matriks.getCol() - 1);
            }

            // swap column b dengan colom variable
            for (i = 0; i < newMatriks.getRow(); i++) {
                value = columnB[i];
                newMatriks.setElement(i, variable, value);
            }
        }
 */