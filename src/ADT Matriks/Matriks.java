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

    public double[][] getM() {
        return M;
    }

    // contoh deklarasi matriksnya
    // Matriks <nama matriks> = Matriks.createMatriks(row, col)
    public static Matriks createMatriks(int newRow, int newCol) {
        return new Matriks(newRow, newCol, new double[newRow][newCol]);
    }

    public void setElement(int row, int col, double value) {
        M[row][col] = value;
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

    public void swapCol(int col1, int col2) {
        int i;
        for (i = 0; i < row; i++) {
            double temp = M[i][col1];
            double b = M[i][col2];
            M[i][col1] = b;
            M[i][col2] = temp;
        }
    }
}
