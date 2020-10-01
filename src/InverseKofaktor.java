public class InverseKofaktor {

    /* Fungsi untuk menghitung kofaktor tiap minor matriks */
    static void hitungKofaktor(Matriks matriks, Matriks matriksTemp, int p, int q, int n) {
        int i = 0, j = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                if (row != p && col != q) {
                    matriksTemp.setElement(i, j++, matriks.getElement(row, col));

                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    /* Fungsi untuk menghitung tiap determinan kofaktor */
    static double hitunDeterminan(Matriks matriks, int n, int N) {
        double D = 0;

        if (n == 1)
            return matriks.getElement(0, 0);

        Matriks matriksTemp = Matriks.createMatriks(N, N);

        int sign = 1;

        for (int f = 0; f < n; f++) {
            hitungKofaktor(matriks, matriksTemp, 0, f, n);
            D += sign * matriks.getElement(0, f) * hitunDeterminan(matriksTemp, n-1, N);

            sign = -sign;
        }

        return D;
    }

    /* Fungsi untuk menghitung Adjoin hasil transpose dari kofaktor dan minor */
    static void hitungAdjoin(Matriks matriks, Matriks matriksAdj, int N) {
        if (N == 1) {
            matriksAdj.setElement(0, 0, 1);
            return;
        }

        int sign = 1;
        Matriks matriksTemp = Matriks.createMatriks(N, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                hitungKofaktor(matriks, matriksTemp, i, j, N);

                sign = ((i + j) % 2 == 0)? 1: -1;

                matriksAdj.setElement(j, i, (sign)*(hitunDeterminan(matriksTemp, N-1, N)));
            }
        }
    }

    /* Fungsi untuk menghitung inverse dari suatu Matriks */
    /* Inverse Matriks dirumuskan Inverse = 1/Determinan * Adjoin */
    static boolean hitungInverse(Matriks matriks, Matriks matriksInverse, int N) {
        double det = hitunDeterminan(matriks, N, N);
        if (det == 0) {
            System.out.print("Matriks singular, tidak bisa dihitung inversenya");
            return false;
        }

        Matriks matriksAdj = Matriks.createMatriks(N, N);
        hitungAdjoin(matriks, matriksAdj, N);

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                matriksInverse.setElement(i, j, matriksAdj.getElement(i, j)/det);

        return true;
    }
} 