//import java.util.Arrays;

public class SPLinvers {
  private static boolean IsInversValid = true;

  // Getter/setter
  public static boolean getIsInversValid() {
    return IsInversValid;
  }
  public static void setIsInversValid(boolean x) {
    IsInversValid = x;
  }

  /* Melakukan split pada matriks augmented */
  public static void SplitMatriks (Matriks MKiri, Matriks MKanan) {
    /**
     * I.S.: MKiri merupakan matriks augmented berukuran n x m, yang merupakan penggabungan antara
     * matriks X pada m-1 kolom pertama dan matriks Y pada kolom terakhir
     * MKanan merupakan matriks sembarang berukuran n x 1 untuk menampung matriks Y
     * F.S.: MKiri merupakan matriks X berukuran n x m-1, MKanan merupakan matriks Y berukuran n x 1
     */
    int n = MKiri.getRow();
    int m = MKiri.getCol();
    
    // Menampung isi matriks Y pada MKanan
    for (int i=0; i<n; i++) {
      MKanan.setElement(i, 0, MKiri.getElement(i, m-1));
    }
    // Menampung isi matriks X pada MTemp untuk kemudian dipindahkan ke MKiri
    Matriks MTemp = new Matriks(n, m-1, new double[n][m-1]);
    for (int i=0; i<MTemp.getRow(); i++) {
      for (int j=0; j<MTemp.getCol(); j++) {
        MTemp.setElement(i, j, MKiri.getElement(i,j));
      }
    }
    MKiri = MTemp;
  }

  /* Menghasilkan invers dari matriks */
  public static Matriks inverseMatriks(Matriks MInput) {
    /** Prekondisi: MInput merupakan matriks X setelah di-split dari matriks augmented
     * Dicek terlebih dahulu apakah matriks berbentuk persegi atau tidak
     * Jika tidak memiliki invers, mengembalikin matriks dengan semua elemen bernilai 0
    */
    /**
     * Kamus Lokal:
     * NB, NK         : int         { ukuran baris dan kolom MInput }
     * MInputExtended : double[][]  { komponen array dari MInput (tidak augmented), diextend dengan matriks identitas di sebelah kanan }
     * MProses        : Matriks     { matriks yang dioperasikan untuk menghasilkan matriks invers }
     * MInvers        : Matriks     { hasil matriks invers }
     */
    int NB = MInput.getRow();
    int NK = MInput.getCol();

    if (NB != NK) {   // tidak bisa dicari matriks inversnya
      setIsInversValid(false);
      Matriks MInvers = new Matriks(NB, NB, new double[NB][NB]);
      for (int i=0; i<NB; i++) {
        for (int j=0; j<NB; j++) {
          MInvers.setElement(i, j, 0);
        }
      }
      return MInvers;
    }
    else {
      Matriks MProses, MInvers;
      double[][] MInputExtended = new double[NB][2*(NK-1)];
      int i, j;
  
      // Menginisialisasi MInvers
      for (i=0; i<NB; i++) {
        for (j=0; j<NB; j++) {
          if (i==j) {
              MInputExtended[i][j] = MInput.getElement(i, j);
              MInputExtended[i][j+NB] = 1;
          }
          else {
              MInputExtended[i][j] = MInput.getElement(i, j);
              MInputExtended[i][j+NB] = 0;
          }
        }
      }
      MProses = new Matriks(NB, 2*NB, MInputExtended);
  
      MProses = SPLMatriks.reduksiOBE(MProses);
      MProses = SPLMatriks.reduksiOBEJordan(MProses);
  
      // mengecek apakah terdapat elemen diagonal 0
      setIsInversValid(true);
      //boolean IsValid = getIsInversValid();
      i = 0;
      while (i < NB && getIsInversValid()) {
        if (MProses.getElement(i,i) == 0) {
          setIsInversValid(false);
        }
        i++;
      }
      
      if (getIsInversValid()) {
        MInvers = new Matriks(NB, NB, new double[NB][NB]);
        for (i=0; i<NB; i++) {
          for (j=0; j<NB; j++) {
            MInvers.setElement(i, j, MProses.getElement(i, j+NB));
          }
        }
      }
      else {
        MInvers = new Matriks(NB, NB, new double[NB][NB]);
        for (i=0; i<NB; i++) {
          for (j=0; j<NB; j++) {
            MInvers.setElement(i, j, 0);
          }
        }
      }
      return MInvers;
    }
  }

  /* Mengalikan 2 matriks
  public static Matriks KaliMatriks (Matriks M1, Matriks M2) {
     Menghasilkan hasil kali matriks M1 berukuran a x b dan M2 berukuran b x c
     Prekondisi: ukuran kolom M1 == ukuran barus M2
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
  */


  /* Menampilkan solusi SPL */
  public static Matriks solusiSPLInverse(Matriks MAugmented) {
    /**Menentukan solusi SPL menggunakan metode invers
     * Diberikan matriks Ax = B, 
     * maka x = A^(-1) B
     */
    /**MAugmented berukuran n x m terdefinisi dan merupakan input user
     * Lalu fungsi mengembalikan matriks x berukuran n x 1
     * Proses: memecah matriks MAugmented menjadi hanya matriks X,
     *  mencari inversnya, untuk invers yg valid dihitung x = A^(-1) B
     */
    int n = MAugmented.getRow();
    //int m = MAugmented.getCol();

    // Melakukan spliting terhadap MAugmented
    Matriks B = new Matriks (n, 1, new double[n][1]);
    Matriks A = MAugmented;
    SplitMatriks(A, B);

    Matriks AInvers = inverseMatriks(A);

    Matriks MatriksX = new Matriks(A.getRow(), 1, new double[A.getRow()][1]);
    
    MatriksX = Matriks.KaliMatriks(AInvers, B);

    return MatriksX;
  }

  public static void printSolusiSPLInvers (Matriks MatriksX) {
    if (!(getIsInversValid())) {
      System.out.println("Matriks tidak memiliki invers dan tidak dapat dicari hasil SPL-nya menggunakan metode ini.");
    }
    else {
      // memberikan hasil unik      
      System.out.println("Solusi unik:");
      for (int i=0; i<MatriksX.getCol(); i++) {
        System.out.print("x_" + (i+1) + " = ");
        System.out.format("%.4f", MatriksX.getElement(i,0));
        System.out.println();
      }
    }
  }
}
