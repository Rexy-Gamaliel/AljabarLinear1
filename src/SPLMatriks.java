import java.text.DecimalFormat;

public class SPLMatriks {
    // Variabel untuk mengetahui sudah berapa kali baris matriks ditukar
    public static int peubah = 0;

    /* Buat mencari SPL lewat eliminasi Gauss dengan OBE
     * Cara penggunaan tinggal panggil SPL.eliminasiGauss(isiMatrix) */
    public static void eliminasiGauss(Matriks matriks) {
        Matriks hasilOBEMatriks = reduksiOBE(matriks);
        printMatrix2d(matriks);

        if (isNotHaveSolution(hasilOBEMatriks)) {
            System.out.println("SPL tidak ada solusi");
        } else if (isParametrik(hasilOBEMatriks) != -1) {
            int row = isParametrik(hasilOBEMatriks);
            printParametrik(matriks, row);
        } else {
            double[] coefficient = SPLMatriks.getCoefficient(matriks);
            double[] hasil = subtitusiMundur(matriks, coefficient);

            printHasil(hasil);
        }
    }

    /* Buat mencari SPL lewat eliminasi Gauss-Jordan dengan OBE tereduksi
     * Cara penggunaan tinggal panggil SPL.eliminasiGaussJordan(isiMatrix)
     */
    public static void eliminasiGaussJordan(Matriks matriksClass) {
        int gaussJordan = 1;
        hasilGauss(matriksClass, gaussJordan);
    }

    /* Mengembalikan matriks inverse dari matriks input */
    public static Matriks inverseMatriks(Matriks MInput) {
        /**
         * Prekondisi: MInput merupakan matriks persegi 
         * Menentukan invers matriks menggunakan OBE
        */
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

    /* Untuk mereduksi elemen-elemen matrix sehingga terbentuk matrix segitiga atas
     *  Cara penggunaan SPL.reduksiOBE(isiMatrix)
     */
    public static Matriks reduksiOBE(Matriks matriks) {
        int len1D = matriks.getRow();
        int len2D = matriks.getCol();
        int geser = 0;

        for (int i = 0; i < len1D; i++) {

            double pivot = matriks.getElement(i, i);
            double factor = 0;
            if (pivot == 0) {
                if (scanUnderPivot(matriks, i) == -1) {
                    geser = 1;
                } else {
                    tukarBaris(matriks, i, scanUnderPivot(matriks, i));
                    geser = 0;
                }
            } else {
                geser = 0;
            }

            for (int k = i + 1; k < len1D; k++) {
                pivot = matriks.getElement(i, i+geser);
                factor = matriks.getElement(k, i+geser) / pivot;

                if (matriks.getElement(k, i+geser) != 0) {
                    for (int j = 0; j < len2D; j++) {
                        if (geser == 1) {
                            if (j != len2D-1) {
                                matriks.setElement(k, j+geser, matriks.getElement(k, j+geser) - matriks.getElement(i, j+geser)*factor);
                            }
                        } else {
                            matriks.setElement(k, j, matriks.getElement(k, j) - matriks.getElement(i, j)*factor);
                        }
                    }
                }
            }
            printMatrix2d(matriks);
        }
        return matriks;
    }

    /* Untuk mereduksi elemen-elemen matrix sehingga terbentuk matrix eselon
     *  Cara penggunaan SPL.reduksiOBEJordan(isiMatrix)
     */
    public static Matriks reduksiOBEJordan(Matriks matriksClass) {
        int len1D = matriksClass.getRow();
        int len2D = matriksClass.getCol();

        for (int i = 1; i < len1D; i++) {

            //tukarZeroPivot(matrix, i);
            double pivot = matriksClass.getElement(i, i);

            for (int k = 0; k < i; k++) {
                double factor = matriksClass.getElement(k, i) / pivot;

                if (matriksClass.getElement(k, i) != 0) {
                    for (int j = 0; j < len2D; j++) {
//                        String hasilStr = new DecimalFormat("##.##").format(matriksClass.getElement(k, j) - matriksClass.getElement(i, j) * factor);
//                        matriksClass.setElement(k, j, Double.parseDouble(hasilStr));

                        matriksClass.setElement(k, j, matriksClass.getElement(k, j) - matriksClass.getElement(i, j) * factor);
                    }
//                    System.out.print(factor);
//                    System.out.print(", ");
//                    System.out.println();
                }
//                printMatrix2d(matriksClass);
            }
        }

//        printMatrix2d(matrix);
        return matriksClass;
    }

    /* Helper function untuk mendapatkan variabel dari SPL */
    public static double[] subtitusiMundur(Matriks matriksClass, double[] b) {
        int len1D = matriksClass.getRow();

        double[] hasil = new double[len1D];
        hasil[len1D-1] = b[len1D-1]/matriksClass.getElement(len1D-1, len1D-1);

        for (int i=len1D-2; i>=0; i--) {
            double sum = 0;
            for (int j=i+1; j<len1D; j++) {
                sum += matriksClass.getElement(i, j)*hasil[j];
            }

            hasil[i]=(b[i]-sum)/matriksClass.getElement(i, i);
        }

        return hasil;
    }

    public static double[] subtitusiMundurJordan(Matriks matriksClass, double[] b) {
        int len1D = matriksClass.getRow();
        int len2D = matriksClass.getCol();
        double[] hasil = new double[len1D];

        for (int i=len1D-1; i>=0; i--) {
            hasil[i] = b[i]/matriksClass.getElement(i, i);
            for (int j=len2D; j>=0; j--) {
                double temp = matriksClass.getElement(i,i)/matriksClass.getElement(i,i);
                matriksClass.setElement(i, i, temp);
            }
            matriksClass.setElement(i, len2D-1, hasil[i]);
        }

        return hasil;
    }

    /* Helper function untuk menukar baris di dalam matriks jika pivot nya = 0 */
    public static void tukarBaris(Matriks matriksClass, int rowX, int rowY) {
        int len1D = matriksClass.getRow();
        int len2D = matriksClass.getCol();

        if (rowX != len1D-1) {
            for (int k=0; k<len2D; k++) {
                double temp = matriksClass.getElement(rowX, k);
                matriksClass.setElement(rowX, k, matriksClass.getElement(rowY, k));
                matriksClass.setElement(rowY, k, temp);
            }
        }

        peubah += 1;
    }

    public static int isParametrik(Matriks matriks) {
        int len1D = matriks.getRow();
        int len2D = matriks.getCol();

        int rowParametrik = -1;
        int total = 0;

        for (int i=len1D-1; i>=0; i--) {
            for (int j=len2D-1; j>=0; j--) {
                total += Math.abs(matriks.getElement(i, j));
            }

            if (total == 0) {
                rowParametrik = i;
                break;
            }
        }

        return rowParametrik;
    }

    public static boolean isNotHaveSolution(Matriks matriks) {
        int len1D = matriks.getRow();
        int len2D = matriks.getCol();

        return (matriks.getElement(len1D-1, len2D-2) == 0) && (matriks.getElement(len1D-1, len2D-1) == 0);
    }

    public static double[] getCoefficient(Matriks matriks) {
        int len1D = matriks.getRow();
        int len2D = matriks.getCol();
        double[] coefficient = new double[len1D];

        for (int i=0; i<len1D; i++) {
            coefficient[i] = matriks.getElement(i, len2D-1);
        }

        return coefficient;
    }

    /* Untuk menghitung determinan matriks ukuran N x N dengan menggunakan OBE
     *  Cara penggunaan tinggal pangil SPL.Determinan(isiMatrix)
     */
    public static double Determinan(Matriks matriks) {
        reduksiOBE(matriks);

        int len1D = matriks.getRow();
        int len2D = matriks.getCol();
        double determinan = 1;

        if (len1D == len2D) {
            for (int i=0; i<len1D; i++) {
                determinan *= matriks.getElement(i, i);
            }
        } else {
            System.out.println("Ukuran matriks harus N x N");
        }

        determinan *= Math.pow(-1, peubah);
        return determinan;
    }

    /* Helper function untuk nge-print matriks 2D */
    public static void printMatrix2d(Matriks matriksClass) {
        int len1D = matriksClass.getRow();
        int len2D = matriksClass.getCol();

        for (int i=0; i<len1D; i++) {
            System.out.print("[");
            for (int j = 0; j < len2D; j++) {
//                String hasilStr = new DecimalFormat("##.##").format(matriksClass.getElement(i, j));
                System.out.printf("%.7f", matriksClass.getElement(i, j));
                if (j!=len2D-1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            System.out.println();
        }
        System.out.println();
    }

    /* Helper function untuk nge-print matriks 1D */
    public static void printMatrix1d(double[] matrix) {
        int len1D = matrix.length;
        System.out.print("[");

        for (int i=0; i<len1D; i++) {
            String hasilStr = new DecimalFormat("##.##").format(matrix[i]);
            System.out.print(hasilStr);
            if (i!=len1D-1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /* Helper function untuk nge-print matriks yang solusi SPL nya berbentuk parametrik */
    public static void printParametrik(Matriks matriksClass, int rowParametrik) {
        int len1D = matriksClass.getRow();
        int len2D = matriksClass.getCol();

        System.out.println("Solusi Parametrik: ");
        for (int i=0; i<len1D; i++) {
            if (i != rowParametrik) {
                for (int j=0; j<len2D-1; j++) {
                    if (j != len2D-1) {
                        if (matriksClass.getElement(i, j) != 0) {
                            if (matriksClass.getElement(i, j) != 1) {
                                System.out.printf("%.1fx%d + ", matriksClass.getElement(i, j), j+1);
                            } else {
                                System.out.printf("x%d ", j+1);
                            }
                        }
                    }
                }
                System.out.printf(" = %.1f", matriksClass.getElement(i, len2D-1));
                System.out.println();;
            }
        }
    }

    /* Helper function untuk nge-print matriks yang solusi SPL nya tunggal */
    public static void printHasil(double[] matrix) {
        int len1D = matrix.length;
        System.out.println("Solusi Tunggal: ");
        for (int i=0; i<len1D; i++) {
            if (i != len1D-1) {
                System.out.printf("x%d = %.5f, ", i+1, matrix[i]);
            } else {
                System.out.printf("x%d = %.5f ", i+1, matrix[i]);
            }
        }
        System.out.println();
    }
}
