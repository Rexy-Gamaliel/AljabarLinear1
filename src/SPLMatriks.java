import java.text.DecimalFormat;

public class SPLMatriks {
    // Variabel untuk mengetahui sudah berapa kali baris matriks ditukar
    public static int peubah = 0;

    /* Buat mencari SPL lewat eliminasi Gauss dengan OBE
     * Cara penggunaan tinggal panggil SPL.eliminasiGauss(isiMatrix) */
    public static void eliminasiGauss(Matriks matriksClass) {
        int gauss = 0;
        hasilGauss(matriksClass, gauss);
    }

    /* Buat mencari SPL lewat eliminasi Gauss-Jordan dengan OBE tereduksi
     * Cara penggunaan tinggal panggil SPL.eliminasiGaussJordan(isiMatrix)
     */
    public static void eliminasiGaussJordan(Matriks matriksClass) {
        int gaussJordan = 1;
        hasilGauss(matriksClass, gaussJordan);
    }

    public static double[] getCoefficient(Matriks matriksClass) {
        int len1D = matriksClass.getRow();
        int len2D = matriksClass.getCol();
        double[] coefficient = new double[len1D];

        for (int i=0; i<len1D; i++) {
            coefficient[i] = matriksClass.getElement(i, len2D-1);
        }

        return coefficient;
    }

    /* Helper function untuk menentukan apakah SPL mempunyai solusi tunggal, banyak
       atau bahkan tidak ada solusinya lewat elminasiGauss dan eliminasiGaussJordan
     */
    public static void hasilGauss(Matriks matriksClass, int status) {
        int len1D = matriksClass.getRow();
        int len2D = matriksClass.getCol();
        double[]  coefficient = new double[len1D];
        double[] hasil;
        int rowParametrik = 9999;
        boolean isParametrik = true;
        boolean notHaveSolution = false;

        reduksiOBE(matriksClass);

        for (int i=len1D-1; i>=0; i--) {
            for (int j=len2D-1; j>=0; j--) {
                if (matriksClass.getElement(i, j) != 0) {
                    isParametrik = false;
                    break;
                }
            }

            if (isParametrik) {
                rowParametrik = i;
                break;
            } else {
                double lastVariable = matriksClass.getElement(i, len2D-1);
                if (matriksClass.getElement(i, i) == 0 && lastVariable !=0) {
                    notHaveSolution = true;
                }
            }
        }

        if (isParametrik) {
            printParametrik(matriksClass, rowParametrik);
        } else {
            if (notHaveSolution) {
                System.out.println("SPL tidak memiliki solusi");
            } else {
                if (status == 0) {
                    reduksiOBE(matriksClass);
                    coefficient = SPLMatriks.getCoefficient(matriksClass);
                    hasil = subtitusiMundur(matriksClass, coefficient);
                } else {
                    reduksiOBEJordan(matriksClass);
                    coefficient = SPLMatriks.getCoefficient(matriksClass);
                    hasil = subtitusiMundurJordan(matriksClass, coefficient);
                }
//                printMatrix2d(matrix);
//                printMatrix1d(hasil);
                printHasil(hasil);
            }
        }
    }

    /* Untuk menghitung determinan matriks ukuran N x N dengan menggunakan OBE
     *  Cara penggunaan tinggal pangil SPL.Determinan(isiMatrix)
     */
    public static double Determinan(Matriks matriksClass) {
        reduksiOBE(matriksClass);

        int len1D = matriksClass.getRow();
        int len2D = matriksClass.getCol();
        double determinan = 1;

        if (len1D == len2D) {
            for (int i=0; i<len1D; i++) {
                determinan *= matriksClass.getElement(i, i);
            }
        } else {
            System.out.println("Ukuran matriks harus N x N");
        }

        determinan *= Math.pow(-1, peubah);
        return determinan;
    }

    /* Untuk mereduksi elemen-elemen matrix sehingga terbentuk matrix segitiga atas
     *  Cara penggunaan SPL.reduksiOBE(isiMatrix)
     */
    public static Matriks reduksiOBE(Matriks matriksClass) {
        int len1D = matriksClass.getRow();
        int len2D = matriksClass.getCol();

        for (int i = 0; i < len1D; i++) {

            tukarZeroPivot(matriksClass, i);
            double pivot = matriksClass.getElement(i, i);

            for (int k = i + 1; k < len1D; k++) {
                double factor = matriksClass.getElement(k, i) / pivot;

                if (matriksClass.getElement(k, i) != 0) {
                    for (int j = 0; j < len2D; j++) {
//                        String hasilStr = new DecimalFormat("##.#####").format(matriksClass.getElement(k, j) - matriksClass.getElement(i, j) * factor);
                        matriksClass.setElement(k, j, matriksClass.getElement(k, j) - matriksClass.getElement(i, j) * factor);
                    }
//                    System.out.print(factor);
//                    System.out.print(", ");
//                    System.out.println();
                }
//                printMatrix2d(matriksClass);
            }
        }

        //printMatrix2d(matrix);
        return matriksClass;
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

    /* Helper function untuk mencari pivot di dalam matriks yang != 0
     *  setelah itu tukar baris yang mempunyai pivot 0 dengan baris dibawahnya
     *  sampai pivotnya != 0
     *  Cara penggunaan SPL.tukarZeroPivot(isiMatrix, index)
     *  index = index yang pivotnya 0
     */
    public static void tukarZeroPivot(Matriks matriksClass, int idx) {
        int len1D = matriksClass.getRow();
        double pivot = matriksClass.getElement(idx, idx);
        int foundIdxPivot = 0;

        for (int i=idx+1; i<len1D; i++) {
            if (matriksClass.getElement(i, idx) != 0) {
                foundIdxPivot = i;
                break;
            }
        }

        if (pivot == 0) {
            tukarBaris(matriksClass, idx, foundIdxPivot);
        }
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
            }
        }

        peubah += 1;
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
