import java.util.Arrays;

public class regresi {
    public static Matriks koefisienRegresi(Matriks X, Matriks Y) {
        /**Regresi Linear dengan n record, yang masing2 record ke-i terdapat
         * k variabel independen x_(i,k) dan k+1 koefisien regresi b_(i,k) dinyatakan dalam
         * y_i = b_(i,0) + b_(i,1)*x_(i,1) + ... + b_(i,k)*x_(i,k).
         *    i..[1..n]
         *
         * Persamaan dapat dinyatakan dalam perkalian matriks
         * Y = Xb
         * dengan Y = [y_1, y_2, ..., y_n], Y berukuran n x 1
         * X = [[1, x_(1,1), x_(1,2), ..., x_(1,k)], ..., [1, x_(n,1), ..., x_(n,k)]]
         * b = [b_1, b_2, b_3, ..., b_n]
         *
         * Akan dicari
         * b = (X^T X)^(-1) X^T Y
         *
         * Kamus Lokal:
         * XTranspose : Matriks       { X^T }
         * tempMatriks: Matriks       { (X^T X)^(-1) }
         */

        Matriks XTranspose = Matriks.TransposeMatriks(X);
        Matriks tempMatriks = Matriks.KaliMatriks(XTranspose, X);
        tempMatriks = SPLinvers.inverseMatriks(tempMatriks);

        Matriks b = Matriks.KaliMatriks(tempMatriks, XTranspose);
        b = Matriks.KaliMatriks(b, Y);

        return b;
    }
}
