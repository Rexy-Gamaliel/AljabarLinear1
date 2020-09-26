public class kofaktor {
    public static double cofactor(Matriks matriks) {
        // mengembalikan nilai determinan matriks dengan cara kofaktor
        // prekondisi matriks berukuran persegi
        int i, j;
        int firstrowIdx, lastrowIdx;
        int row, col;
        int sign;

        double determinant;
        Matriks tempMatriks;

        firstrowIdx = 0;
        lastrowIdx = matriks.getRow() - 1;

        i = firstrowIdx;
        j = firstrowIdx;
        if (matriks.getRow() == 1) {
            return matriks.getElement(i, j);
        } else if (matriks.getRow() == 2) {
            return ((matriks.getElement(i, j) * matriks.getElement(i + 1, j + 1)) - (matriks.getElement(i + 1, j) * matriks.getElement(i, j + 1)));
        } else {
            // make matriks smaller
            tempMatriks = Matriks.createMatriks(matriks.getRow() - 1, matriks.getCol() - 1);
            determinant = 0;
            sign = 1;

            int x, y;
            for (i = firstrowIdx; i <= lastrowIdx; i++) {
                x = firstrowIdx;
                y = lastrowIdx;

                for (row = firstrowIdx; row <= lastrowIdx; row++) {
                    for (col = firstrowIdx; col <= lastrowIdx; col++) {
                        /* mengisi subM dengan elemen matriks yang indeks != 'pivot' perkalian kofaktor */
                        if ((row != 0) && (col != i)) {
                            tempMatriks.setElement(x, y, matriks.getElement(row, col));
                            y++;
                            if (y == lastrowIdx) {  // next baris
                                y = 0;
                                x++;
                            }
                        }
                    }
                }
            }
            // perkalian determinan
            determinant += sign * matriks.getElement(0, i) * cofactor(tempMatriks);
            sign = -1 * sign;
        }
        return determinant;
    }
}