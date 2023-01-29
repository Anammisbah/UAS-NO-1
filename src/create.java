import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

public class create {
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);

            // Meminta input jumlah data yang akan dibuat
            System.out.print("Masukkan jumlah data yang akan dibuat: ");
            int jumlahData = input.nextInt();

            // Membuat list untuk menyimpan objek Category
            ArrayList<Category> categories = new ArrayList<Category>();

            // Mengulangi jumlah data yang ditentukan untuk meminta input dari user
            for (int i = 0; i < jumlahData; i++) {
                System.out.println("Data ke-" + (i + 1));

                // Meminta input id dan nama dari user
                System.out.print("Masukkan id: ");
                int id = input.nextInt();
                System.out.print("Masukkan nama: ");
                String nama = input.next();

                // Membuat objek Category dan menambahkannya ke list
                Category category = new Category();
                category.setId(id);
                category.setNama(nama);
                categories.add(category);
            }

            // Mengubah list menjadi format JSON
            Gson gson = new Gson();
            String json = gson.toJson(categories);

            // Menuliskan data JSON ke file
            FileWriter writer = new FileWriter("categories.json");
            writer.write(json);
            writer.close();

            System.out.println("Data berhasil ditulis ke file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Category {
    private int id;
    private String nama;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
