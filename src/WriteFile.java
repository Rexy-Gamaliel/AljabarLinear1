import java.io.*;

public class WriteFile {
    public static void SaveFile(String result) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("result.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(result + " ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void DelFileExist() {
        // menghapus file sebelumnya
        //dilakukan diawal saat sebelum  pengisian data
        File f = new File("result.txt");
        if (f.exists()) {
            f.delete();
        }
    }

    public static void SaveSuccess() {
        System.out.println("Hasil berhasil disimpan kedalam file bernama result.txt");
    }


    public static void addNewline() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("result.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}