import java.util.Arrays;

public class invers {

  public static Matriks inverseMatriks(Matriks matrix) {
    /* Prekondisi: matrix merupakan matriks persegi */
    /**
     * Kamus Lokal:
     * N          : int         { ukuran matrix }
     * invMatrix  : Matriks     { hasil matriks invers }
     * invBuilder : double[][]  { komponen array dr matriks invers sebelum dioperasikan }
     * tempInv    : Matriks     { matriks yang dioperasikan untuk menghasilkan matriks invers }
     * invBuilder2: double[][]  { komponen array matriks hasil invers }
     */
    int N = matrix.getCol();
    Matriks tempInv, invMatriks;
    double[][] invBuilder = new double[N][2*N];
    int i, j;

    // Menginisialisasi invMatrix
    for (i=0; i<N; i++) {
      for (j=0; j<N; j++) {
        if (i==j) {
            invBuilder[i][j] = matrix.getElement(i, j);
            invBuilder[i][j+N] = 1;
        }
        else {
            invBuilder[i][j] = matrix.getElement(i, j);
            invBuilder[i][j+N] = 0;
        }
      }
    }
    tempInv = new Matriks(N, 2*N, invBuilder);

    tempInv = SPLMatriks.reduksiOBE(tempInv);
    tempInv = SPLMatriks.reduksiOBEJordan(tempInv);
    //SPLMatriks.printMatrix2d(tempInv);

    double[][] tempInv2 = new double[N][N];
    for (i=0; i<N; i++) {
        for (j=0; j<N; j++) {
            tempInv2[i][j] = tempInv.getElement(i, j+N);
        }
    }
    
    invMatriks = new Matriks(N, N, tempInv2);

    return invMatriks;
  }
  
  //public static Matriks inverseMatriks(Matriks matrix) {
  //  /* Prekondisi: matrix merupakan matriks persegi */
  //  /**
  //   * Kamus Lokal:
  //   * N          : int       { ukuran matrix }
  //   * invMatrix  : Matrix
  //   */
  //  int N = Matriks.getArray().length;
  //  Matriks tempInv, invMatriks;
  //  double[][] invBuilder;
  //  int i, j;
//
  //  // Menginisialisasi invMatrix
  //  for (i=0; i<N; i++) {
  //    for (j=0; j<N; j++) {
  //      if (i==j) {
  //        invBuilder[i][j] = 0;
  //        invBuilder[i][j+N] = 1;
  //      }
  //      else {
  //        invBuilder[i][j] = 0;
  //        invBuilder[i][j+N] = 0;
  //      }
  //    }
  //  }
  //  tempInv = new Matriks(N, 2*N, invBuilder);
//
  //  SPLMatriks.eliminasiGaussJordan(tempInv);
  //  SPLMatriks.printMatrix2d(tempInv);
  //  
  //  //return invMatrix;
  //}


}
