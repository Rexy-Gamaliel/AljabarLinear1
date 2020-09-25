public class Cramer {
    public double Determinan(Matriks matriks, int variable) {
        // mengembalikan nilai determinan dengan cara cramer's rule
        // variable = -1, maka kembalikan determinan utama
        // variable = 0, kembalikan nilai determinan untuk mencari X1

        double determinan;
        if(variable == -1)
            determinan = SPL.Determinan(matriks);
        else{
            int i,j;
            // buat matriks tanpa kolom b
            Matriks newMatriks = Matriks.createMatriks(matriks.getRow(), matriks.getCol()-1);

            // siapkan array b
            double[] columnB = new double[matriks.getRow()]; // berukuran sepanjang row
            for(i = 0; i < matriks.getRow();i++){
                columnB[i] = matriks.getElement( i, matriks.getCol()-1);
            }

            // copy isi matriks
            double value;
            for(i = 0; i < matriks.getRow(); i++){
                for (j = 0; j < matriks.getCol()-1; j++){
                    value = matriks.getElement(i,j);
                    newMatriks.setElement(i,j, value);
                }
            }

            // swap column b dengan colom variable
            for(i = 0; i < newMatriks.getRow(); i++){
                value = columnB[i];
                newMatriks.setElement(i, variable, value);
            }

            determinan = SPL.Determinan(newMatriks);
        }
        return determinan;
    }
}