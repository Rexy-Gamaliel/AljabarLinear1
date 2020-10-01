public class SPLcramer {
    public static void Solusi(Matriks matriks) {
        //Menampilkan solusi Xn

        // cek matriks utama apakah 0
        Cramer cramer = new Cramer();
        Matriks MatriksDetUtama = matriks.createDuplicate();
        double detUtama = cramer.Determinan(MatriksDetUtama, -1);
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
            double detX, det;
            double X;
            // clean file sebelum create
            WriteFile.DelFileExist();
            Matriks cloneMatriks = matriks.createDuplicate();
            detX = cramer.Determinan(cloneMatriks, 0);
            det = (detX / detUtama);
            System.out.println("X1 = " + det);
            WriteFile.SaveFile("X1 =");
            WriteFile.SaveFile(Double.toString(det));
            WriteFile.addNewline();

            for (idxVar = 0; idxVar < matriks.getCol() - 1; idxVar++) {
                Matriks newMatriks = matriks.createDuplicate();
                det = cramer.Determinan(newMatriks, idxVar);
                if (idxVar != 0) {
                    System.out.print("X" + (idxVar + 1) + " = ");
                    X = (det / detUtama);
                    System.out.format("%.2f", X);
                    System.out.println();

                    String var = "X" + idxVar + " =";
                    WriteFile.SaveFile(var);
                    WriteFile.SaveFile(Double.toString(X));
                    WriteFile.addNewline();
                }
            }
            WriteFile.SaveSuccess();
        }
    }
}
