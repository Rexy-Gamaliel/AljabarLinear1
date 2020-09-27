//import java.util.Arrays;

public class invers {

  public static Matriks inverseMatriks(Matriks MInput) {
    /* Prekondisi: MInput merupakan matriks persegi */
    /**
     * Kamus Lokal:
     * N              : int         { ukuran matrix }
     * MInputExtended : double[][]  { komponen array dari MInput, diextend dengan matriks identitas di sebelah kanan }
     * MProses        : Matriks     { matriks yang dioperasikan untuk menghasilkan matriks invers }
     * MInvers        : Matriks     { hasil matriks invers }
     */
    int N = MInput.getCol();
    Matriks MProses, MInvers;
    double[][] MInputExtended = new double[N][2*N];
    int i, j;

    // Menginisialisasi MInvers
    for (i=0; i<N; i++) {
      for (j=0; j<N; j++) {
        if (i==j) {
            MInputExtended[i][j] = MInput.getElement(i, j);
            MInputExtended[i][j+N] = 1;
        }
        else {
            MInputExtended[i][j] = MInput.getElement(i, j);
            MInputExtended[i][j+N] = 0;
        }
      }
    }
    MProses = new Matriks(N, 2*N, MInputExtended);

    MProses = SPLMatriks.reduksiOBE(MProses);
    MProses = SPLMatriks.reduksiOBEJordan(MProses);
    
    MInvers = new Matriks(N, N, new double[N][N]);
    for (i=0; i<N; i++) {
      for (j=0; j<N; j++) {
        MInvers.setElement(i, j, MProses.getElement(i, j+N));
      }
    }

    return MInvers;
  }
}
