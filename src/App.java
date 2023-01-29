import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class App {
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);

            // Meminta konfirmasi kepada user sebelum proses import dilakukan
            System.out.print("Apakah Anda yakin ingin mengimport data dari file JSON ke database? (y/n) ");
            String confirm = input.next();
            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("Proses import dibatalkan.");
                return;
            }

            // Konfigurasi koneksi ke database
            final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            final String DB_URL = "jdbc:mysql://localhost/katalog_product";
            final String USER = "root";
            final String PASS = "";
            Class.forName(JDBC_DRIVER);

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Membaca file JSON
            Gson gson = new Gson();
            File file = new File("categories.json");
            List<Category> categories = gson.fromJson(new FileReader(file), new TypeToken<List<Category>>(){}.getType());

            // Menyiapkan perintah SQL untuk menambahkan data ke tabel category
            String sql = "INSERT INTO category (id, nama) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Mengulangi setiap objek Category dan menambahkannya ke tabel category
            for (Category category : categories) {
                stmt.setInt(1, category.getId());
                stmt.setString(2, category.getNama());
                stmt.executeUpdate();
            }

            // Menutup koneksi ke database
            stmt.close();
            conn.close();
            System.out.println("Data berhasil diimpor!");
        } catch (Exception e) {
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
    