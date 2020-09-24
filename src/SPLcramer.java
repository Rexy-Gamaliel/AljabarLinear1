public class SPLcramer {
    public void Solusi(Matriks matriks){
        // cek matriks utama apakah 0
        Cramer cramer = new Cramer();
        double detUtama = cramer.Determinan(matriks, -1);
        int idxVar;
        if(detUtama == 0){
            System.out.println("Determinan utama 0");
            System.out.println("Solusi tidak unik, silakan gunakan metode lain");
        } else{
            System.out.println("Solusi unik : ");
            double detX;
            for(idxVar = 0; idxVar < matriks.getCol()-1; idxVar++){
                detX = cramer.Determinan(matriks, idxVar);
                System.out.print("X" + (idxVar+1) + " = ");
                System.out.println(detX / detUtama);
            }
        }

    }
}
