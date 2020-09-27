import java.util.Arrays;

public class regresi {

  public static Matriks KaliMatriks (Matriks M1, Matriks M2) {
    /* Menghasilkan hasil kali matriks M1 berukuran a x b dan M2 berukuran b x c */
    /* Prekondisi: ukuran kolom M1 == ukuran barus M2 */
    int a = M1.getRow();
    int b = M1.getCol(); //= M2.getRow()
    int c = M2.getCol();
    Matriks MKali;
    double[][] hasilKali = new double[a][c];

    int i, j, k;
    double temp;

    for (i=0; i<a; i++) {
      for (j=0; j<c; j++) {
        temp = 0;
        for (k=0; k<b; k++) {
          temp += M1.getElement(i, k) * M2.getElement(k, j);
        }
        hasilKali[i][j] = temp;
      }
    }

    MKali = new Matriks(a, c, hasilKali);
    return MKali;
  }

  public static Matriks TransposeMatriks (Matriks MIn) {
    /** Menerima matriks MIn berukuran B x K 
     * Mengembalikan matriks MOut berukuran K x B dengan MOut[i][j] == MIn[j][i]
    */
    int B = MIn.getRow();
    int K = MIn.getCol();
    double[][] hasilTranspose = new double[B][K];

    for (int i=0; i<B; i++) {
      for (int j=0; j<K; j++) {
        hasilTranspose[j][i] = MIn.getElement(i, j);
      }
    }

    Matriks MOut = new Matriks(K, B, hasilTranspose);

    return MOut;
  }

  public static Matriks koefisienRegresi (Matriks X, Matriks Y) {
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

    Matriks XTranspose = TransposeMatriks(X);
    Matriks tempMatriks = KaliMatriks(XTranspose, X);
    tempMatriks = invers.inverseMatriks(tempMatriks);

    Matriks b = KaliMatriks(tempMatriks, XTranspose);
    b = KaliMatriks(b, Y);

    return b;
  }
}
