import java.util.Arrays;

public class Matriks {

    // deklarasi atribut
    private int row, col;
    private double[][] M;

    // constructor
    public Matriks(int row, int col, double[][] M) {
        this.row = row;
        this.col = col;
        this.M = M;
    }

    // getter
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public double getElement(int idxRow, int idxCol) {
        return M[idxRow][idxCol];
    }

    //setter
    public void setRow(int newValue) {
        row = newValue;
    }
    public void setCol(int newValue) {
        col = newValue;
    }

    public void setElement(int row, int col, double value) {
        M[row][col] = value;
    }
    
    // contoh deklarasi matriksnya
    // Matriks <nama matriks> = Matriks.createMatriks(row, col)
    public static Matriks createMatriks(int newRow, int newCol) {
        return new Matriks(newRow, newCol, new double[newRow][newCol]);
    }


    // menyiapkan matriks agar bisa langsung di print semua
    // Jadi bisa langsung System.out.println(<nama matriks>);
    @Override
    public String toString() {
        StringBuilder matrixString = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrixString.append(this.M[i][j]).append(" ");
            }
            matrixString.append("\n");
        }
        return matrixString.toString();
    }

    public static Matriks TransposeMatriks(Matriks MIn) {
        /** Menerima matriks MIn berukuran B x K
         * Mengembalikan matriks MOut berukuran K x B dengan MOut[i][j] == MIn[j][i]
         */
        int B = MIn.getRow();
        int K = MIn.getCol();
        double[][] hasilTranspose = new double[B][K];

        for (int i = 0; i < B; i++) {
            for (int j = 0; j < K; j++) {
                hasilTranspose[j][i] = MIn.getElement(i, j);
            }
        }

        Matriks MOut = new Matriks(K, B, hasilTranspose);

        return MOut;
    }


    public static Matriks KaliMatriks(Matriks M1, Matriks M2) {
        /* Menghasilkan hasil kali matriks M1 berukuran a x b dan M2 berukuran b x c */
        /* Prekondisi: ukuran kolom M1 == ukuran barus M2 */
        int a = M1.getRow();
        int b = M1.getCol(); //= M2.getRow()
        int c = M2.getCol();
        Matriks MKali;
        double[][] hasilKali = new double[a][c];

        int i, j, k;
        double temp;

        for (i = 0; i < a; i++) {
            for (j = 0; j < c; j++) {
                temp = 0;
                for (k = 0; k < b; k++) {
                    temp += M1.getElement(i, k) * M2.getElement(k, j);
                }
                hasilKali[i][j] = temp;
            }
        }

        MKali = new Matriks(a, c, hasilKali);
        return MKali;
    }
}
