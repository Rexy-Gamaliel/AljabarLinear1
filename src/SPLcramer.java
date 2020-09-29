public class SPLcramer {
    public static void Solusi(Matriks matriks) {
        //Menampilkan solusi Xn

        // cek matriks utama apakah 0
        Cramer cramer = new Cramer();
        double detUtama = cramer.Determinan(matriks, -1);
        int idxVar;
        if (detUtama == 0) {
            System.out.println("Determinan utama 0");
            System.out.println("Solusi tidak unik, silakan gunakan metode lain");

            WriteFile.DelFileExist();
            WriteFile.SaveFile("Determinan utama 0");
            WriteFile.addNewline();
            WriteFile.SaveFile("Solusi tidak unik, silakan gunakan metode lain");
            WriteFile.SaveSuccess();
        } else {
            System.out.println("Solusi unik : ");
            double detX;
            double X;
            // clean file sebelum create
            WriteFile.DelFileExist();
            for (idxVar = 0; idxVar < matriks.getCol() - 1; idxVar++) {
                detX = cramer.Determinan(matriks, idxVar);
                System.out.print("X" + (idxVar + 1) + " = ");
                X = detX / detUtama;
                System.out.format("%.2f", X);
                System.out.println();

                String var = "X" + idxVar + " =";
                WriteFile.SaveFile(var);
                WriteFile.SaveFile(Double.toString(X));
                WriteFile.addNewline();
            }
            WriteFile.SaveSuccess();
        }
    }
}
