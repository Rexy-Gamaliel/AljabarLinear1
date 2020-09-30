import java.util.Arrays;

public class regresi {
    public static void ComputeRegresi (Matriks MAugmentedReg) {
        /**Menentukan matriks regresi berdasarkan input
         * Menghitung koefisien regresi
         * Mencetak persamaan r
         * Menerima input xj untuk j [1..k]
         * Menghitung y berdasarkan xj dan koefisien regresi
         * 
         * MAugmentedReg    : Matriks input dalam bentuk matriks augmented
         *                    ukuran n x k+2
         * MReg             : Matriks regresi yang merepresentasikan persamaan Normal Equation for Multiple Linear Regression
         *                    ukuran n x k+2
         * B                : Matriks yang menampung koefisien regresi b0, b1, b2, ..., bk
         *                    ukuran k+1 x 1
         * MTest            : Matriks yang menampung variabel independen x_j untuk ditentukan hasilnya
         *                    ukuran 1 x k+1
         */
        // Membuat Matriks Regresi
        Matriks MReg = Matriks.createMatriks(MAugmentedReg.getRow(), MAugmentedReg.getCol()+1);
        MReg = makeMatriksRegresi(MAugmentedReg);

        // Menghitung koefisien regresi
        Matriks B = Matriks.createMatriks(MAugmentedReg.getCol()-1,1);
        B = KoefisienRegresi(MReg);

        // Mencetak persamaan regresi
        PrintPersRegresi(B);

        // Meng-input xj dari user
        Matriks MTest = Matriks.createMatriks(1, MAugmentedReg.getCol()-1);
        InputMatrix Input = new InputMatrix();
        Input.regresiTestMan(MTest);

        // Menghitung y dari MTest dan B
        double y = ResultRegresi(MTest, B);
        System.out.print("y = " + y);
        System.out.println();
    }

    public static Matriks makeMatriksRegresi (Matriks MAugmentedReg) {
        /**Menyusun matriks regresi yang merepresentasikan persamaan untuk
         * Normal Estimation Equation for Multiple Linear Regression
         * MAugmentedReg adalah matriks augmented berukuran n x k+1 yang menampung
         * nilai xji dan yj untuk j [1..k] dan i [1..n]
         * MReg adalah matriks regresi berukuran n x k+2
         * 
         * Kamus Lokal:
         * baris, kolom         { indeks MAugmentedReg }
         * MReg                 { hasil: matriks regresi }
         */
        int baris = MAugmentedReg.getRow();       // = n
        int kolom = MAugmentedReg.getCol()+1;     // = k+2
        Matriks MReg = Matriks.createMatriks(baris, kolom);

        for (int b=0; b<baris; b++) {
            for (int k=0; k<kolom-1; k++) {
                System.out.printf("%.2f ", MAugmentedReg.getElement(b, k));
            }
            System.out.println();
        }

        for (int i=0; i<baris; i++) {
            for (int j=0; j<kolom; j++) {
                if (j<kolom-1) {            // ruas kiri persamaan
                    if (i==0 || j==0) {     // baris pertama atau kolom pertama
                        // baris pertama, kolom sebagai argumen Sigma1
                        if (j!=0) { MReg.setElement(0, j, sigma.Sigma1(MAugmentedReg, j)); }
                        // kolom pertama, baris sebagai argumen Sigma1
                        else if (i!=0) { MReg.setElement(i, 0, sigma.Sigma1(MAugmentedReg, i)); }
                        else { MReg.setElement(0, 0, baris); } // kasus khusus untuk i==0 dan j==0, n==baris
                    }
                    else {                  // elemen matriks di dalam
                        MReg.setElement(i, j, sigma.Sigma23(MAugmentedReg, i, j));
                    }
                }
                else {                      // ruas kanan persamaan
                    if (i == 0) { MReg.setElement(0, kolom-1, sigma.Sigma1(MAugmentedReg, kolom-1)); }
                    else { MReg.setElement(i, kolom-1, sigma.Sigma4(MAugmentedReg, i)); }
                }
            }
        }
        return MReg; 
    }
    
    public static void PrintMatriksRegresi (Matriks MReg) {
        /** Mencetak persamaan Multiple Linear Regression yang direpresentasikan MReg 
         * dengan format:
         * MReg[0][0].b0 + MReg[0][1].b1 + MReg[0][2].b2 + ... + MReg[0][k].bk = MReg[0][k+1]
         * MReg[1][0].b0 + MReg[1][1].b1 + MReg[1][2].b2 + ... + MReg[1][k].bk = MReg[1][k+1]
         * ...
         * MReg[n-1][0].b0 + MReg[n-1][1].b1 + MReg[n-1][2].b2 + ... + MReg[n-1][k].bk = MReg[n-1][k+1]
        */
        int baris = MReg.getRow();
        int kolom = MReg.getCol();
        int i, j;

        for (i=0; i<baris; i++) {
            // suku pertama tiap baris
            System.out.printf("(%.4f)b%d", MReg.getElement(i, 0), 0);
            for (j=1; j<kolom; j++) {
                if (j < kolom-1) {  // ruas kiri
                    System.out.printf(" + (%.4f)b%d", MReg.getElement(i, j), j);
                }
                else {              // ruas kanan
                    System.out.printf(" = (%.4f)", MReg.getElement(i, j));
                }
            }
            System.out.println();
        }
    }

    public static Matriks KoefisienRegresi (Matriks MReg) {
        /**Menentukan solusi koefisien regresi dari matriks regresi yang diberikan
         * MReg berukuran n x k+2, ditampung solusinya dengan metode Gauss-Jordan
         * ke dalam array arrKoefB berukuran k+1
         * return Matriks B berukuran k+1 x 1 yang menampung koefisien regresi b0, b1, ..., bk
         */

        double[] arrKoefB = new double[MReg.getCol()-1];
        arrKoefB = SPLMatriks.eliminasiGaussJordan(MReg, 0);

        Matriks B = Matriks.createMatriks(MReg.getCol()-1, 1);

        for (int j=0; j<B.getCol(); j++) {
            B.setElement(0, j, arrKoefB[j]);
        }

        return B;
    }

    public static void PrintPersRegresi (Matriks B) {
        /**Menampilkan persamaan akhir regresi dalam format 
         * y = b0 + b1.x1 + b2.x2 + ... + bk.xk
         * 
         * Matriks B berukuran k+1 x 1 menyimpan koefisien regresi
        */
        int baris = B.getRow();
        int i;
        System.out.printf("y = %.4f", B.getElement(0, 0));
        for (i=1; i<baris; i++) {
            System.out.printf(" + (%.4f)x%d", B.getElement(i, 0), i);
        }
    }

    public static double ResultRegresi (Matriks MTest, Matriks B) {
        /**Menghitung hasil regresi berdasarkan input xk pada MTest dan koefisien regresi pada B
         * My adalah matriks temp 1x1 untuk menampung hasil kali MXk ukuran dan B dalam bentuk matriks
        */
        double y;
        Matriks My = Matriks.createMatriks(1, 1);
        My = Matriks.KaliMatriks(MTest, B);
        y = My.getElement(0, 0);
        return y;
    } 


}
