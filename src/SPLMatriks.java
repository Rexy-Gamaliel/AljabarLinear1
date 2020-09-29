import java.text.DecimalFormat;

public class SPLMatriks {

    /* Variabel untuk mengetahui sudah berapa kali baris matriks ditukar */
    public static int peubah = 0;

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
                    swapRow(matriks, i, scanUnderPivot(matriks, i));
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

        }
        return matriks;
    }

    /* Untuk mereduksi elemen-elemen matrix sehingga terbentuk matrix eselon
     *  Cara penggunaan SPL.reduksiOBEJordan(isiMatrix)
     */
    public static Matriks reduksiOBEJordan(Matriks matriks) {
        int len1D = matriks.getRow();
        int len2D = matriks.getCol();
        int geser = 0;

        for (int i = 0; i < len1D; i++) {
            double pivot = matriks.getElement(i, i);

            double factor = 0;
            if (pivot == 0) {
                if (scanUnderPivot(matriks, i) == -1) {
                    pivot = matriks.getElement(i, i+1);
                    geser = 1;
                } else {
                    swapRow(matriks, i, scanUnderPivot(matriks, i));
                    pivot = matriks.getElement(i, i);
                }
            }

            if (pivot != 1 && pivot !=0) {
                for (int l=i; l<len2D; l++) {
                    matriks.setElement(i, l, matriks.getElement(i, l) / pivot);
                }
            }

            for (int k = 0; k < len1D; k++) {
                if (pivot != 0 && k != i) {
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
            }
        }
        return matriks;
    }

    public static void eliminasiGauss(Matriks matriks) {
        Matriks hasilOBEMatriks = reduksiOBE(matriks);

        if (isNotHaveSolution(hasilOBEMatriks)) {
            System.out.println("SPL tidak ada solusi");
        } else if (isParametrik(hasilOBEMatriks) != -1) {
            int row = isParametrik(hasilOBEMatriks);
            printParametrik(matriks, row);
        } else {
            double[] coefficient = SPLMatriks.getCoefficient(matriks);
            double[] hasil = backSubtituion(matriks, coefficient);

            printHasil(hasil);
        }
    }

    public static void eliminasiGaussJordan(Matriks matriks) {
        Matriks hasilOBEMatriks = reduksiOBEJordan(matriks);

        if (isNotHaveSolution(hasilOBEMatriks)) {
            System.out.println("SPL tidak ada solusi");
        } else if (isParametrik(hasilOBEMatriks) != -1) {
            int row = isParametrik(hasilOBEMatriks);
            printParametrik(matriks, row);
        } else {
            double[] coefficient = SPLMatriks.getCoefficient(matriks);
            double[] hasil = backSubtituion(matriks, coefficient);

            printHasil(hasil);
        }
    }

    public static int isParametrik(Matriks matriks) {
        int len1D = matriks.getRow();
        int len2D = matriks.getCol();

        int rowParametrik = -1;
        int total = 0;
        int totalNotZero = 0;

        for (int j=len2D-2; j>=0; j--) {
            if (matriks.getElement(len1D-1, j) != 0) {
                totalNotZero += 1;
            }
        }

        if (totalNotZero > 1) {
            rowParametrik = -2;
        }

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
        boolean isNotHaveSolution = false;

        if (isParametrik(matriks) == -1) {
            isNotHaveSolution = (matriks.getElement(len1D-1, len2D-2) == 0);
        }

        return isNotHaveSolution;
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

    /* Helper function untuk mencari pivot di dalam matriks yang != 0
     *  setelah itu tukar baris yang mempunyai pivot 0 dengan baris dibawahnya
     *  sampai pivotnya != 0
     *  Cara penggunaan SPL.scanUnderPivot(isiMatrix, index)
     *  index = index yang pivotnya 0
     */
    public static int scanUnderPivot(Matriks matriks, int idx) {
        int len1D = matriks.getRow();
        int foundIdxPivot = -1;

        for (int i=idx+1; i<len1D; i++) {
            if (matriks.getElement(i, idx) != 0) {
                foundIdxPivot = i;
                break;
            }
        }

        return foundIdxPivot;
    }

    /* Helper function untuk mendapatkan variabel dari SPL */
    public static double[] backSubtituion(Matriks matriks, double[] b) {
        int len1D = matriks.getRow();

        double[] hasil = new double[len1D];
        hasil[len1D-1] = b[len1D-1]/matriks.getElement(len1D-1, len1D-1);

        for (int i=len1D-2; i>=0; i--) {
            double sum = 0;
            for (int j=i+1; j<len1D; j++) {
                sum += matriks.getElement(i, j)*hasil[j];
            }

            hasil[i]=(b[i]-sum)/matriks.getElement(i, i);
        }

        return hasil;
    }

    /* Helper function untuk menukar baris di dalam matriks jika pivot nya = 0 */
    public static void swapRow(Matriks matriks, int rowX, int rowY) {
        int len1D = matriks.getRow();
        int len2D = matriks.getCol();

        if (rowX != len1D-1) {
            for (int k=0; k<len2D; k++) {
                double temp = matriks.getElement(rowX, k);
                matriks.setElement(rowX, k, matriks.getElement(rowY, k));
                matriks.setElement(rowY, k, temp);
            }
        }

        peubah += 1;
    }

    /* Helper function untuk nge-print matriks 1D */
    public static void printMatrix2d(Matriks matriks) {
        int len1D = matriks.getRow();
        int len2D = matriks.getCol();

        for (int i=0; i<len1D; i++) {
            System.out.print("[");
            for (int j = 0; j < len2D; j++) {
                System.out.printf("%.2f", matriks.getElement(i, j));
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
    public static void printParametrik(Matriks matriks, int rowParametrik) {
        int len1D = matriks.getRow();
        int len2D = matriks.getCol();
        StringBuilder hasil = new StringBuilder("Solusi Parametrik: \n");

//        System.out.println("Solusi Parametrik: ");
        for (int i=0; i<len1D; i++) {
            for (int j=0; j<len2D-1; j++) {
                if (matriks.getElement(i, j) != 0) {
//                    System.out.printf("(%.2fx%d)", matriks.getElement(i, j), j+1);

                    hasil.append("(");
                    hasil.append(matriks.getElement(i, j));
                    hasil.append("x");
                    hasil.append(j+1);
                    hasil.append(")");

                    if (j != len2D-2) {
//                        System.out.print(" + ");
                        hasil.append(" + ");
                    } else {
//                        System.out.print("");
                        hasil.append(" ");
                    }
                }
            }

            if (rowParametrik != i) {
//                System.out.printf(" = %.2f", matriks.getElement(i, len2D-1));

                hasil.append(" = ");
                hasil.append("(");
                hasil.append(matriks.getElement(i, len2D-1));
                hasil.append(")");
            }
            hasil.append("\n");
            System.out.println();;
        }

        System.out.println(hasil);

        WriteFile.DelFileExist();
        WriteFile.SaveFile("Solusi SPL adalah \n\n");
        WriteFile.SaveFile(hasil.toString());
        WriteFile.SaveSuccess();
    }

    /* Helper function untuk nge-print matriks yang solusi SPL nya tunggal */
    public static void printHasil(double[] matrix) {
        int len1D = matrix.length;
        StringBuilder hasil = new StringBuilder("Solusi Tunggal: \n");
//        System.out.println("Solusi Tunggal: ");

        for (int i=0; i<len1D; i++) {
            if (i != len1D-1) {
                hasil.append("x");
                hasil.append(i+1);
                hasil.append(" = ");
                hasil.append(matrix[i]);
                hasil.append(" ");

//                System.out.printf("x%d = %.5f, ", i+1, matrix[i]);
            } else {
                hasil.append("x");
                hasil.append(i+1);
                hasil.append(" = ");
                hasil.append(matrix[i]);
                hasil.append(" ");

//                System.out.printf("x%d = %.5f ", i+1, matrix[i]);
            }
        }
//        System.out.println();
        System.out.println(hasil);
        System.out.println();

        WriteFile.DelFileExist();
        WriteFile.SaveFile("Solusi SPL adalah \n\n");
        WriteFile.SaveFile(hasil.toString());
        WriteFile.SaveSuccess();
    }
}
