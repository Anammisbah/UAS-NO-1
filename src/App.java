import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/katalog_product";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static PreparedStatement stmt;
    static ResultSet rs;

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    static void showProduct() throws Exception {
        String sql = "SELECT * FROM product";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        System.out.println("| DATA PRODUCT |");
        while (rs.next()){
            int id = rs.getInt("id");
            int category_id = rs.getInt("category_id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            double price = rs.getDouble("price");
            String status = rs.getString("status");
            System.out.println(String.format("%d. %s ===> %s %s %s %s", id, name, category_id, description, price, status));
        }
    }

    static void insertProduct() throws Exception {
        System.out.print("Masukkan category_id: ");
        int category_id = Integer.parseInt(input.readLine());
        System.out.print("Masukkan name: ");
        String name = input.readLine();
        System.out.print("Masukkan description: ");
        String description = input.readLine();
        System.out.print("Masukkan price: ");
        double price = Double.parseDouble(input.readLine());
        System.out.print("Masukkan status: ");
        String status = input.readLine();
        String sql = "INSERT INTO product (category_id, name, description, price, status) VALUES (?, ?, ?, ?, ?)";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, category_id);
        stmt.setString(2, name);
        stmt.setString(3, description);
        stmt.setDouble(4, price);
        stmt.setString(5, status);
        stmt.executeUpdate();
        System.out.println("Data product berhasil ditambahkan!");
    }

    static void updateProduct() throws Exception {
        System.out.print("Masukkan ID product yang ingin diubah: ");
        int id = Integer.parseInt(input.readLine());
        System.out.print("Masukkan category_id: ");
        int category_id = Integer.parseInt

(input.readLine());
System.out.print("Masukkan name: ");
String name = input.readLine();
System.out.print("Masukkan description: ");
String description = input.readLine();
System.out.print("Masukkan price: ");
double price = Double.parseDouble(input.readLine());
System.out.print("Masukkan status: ");
String status = input.readLine();
    String sql = "UPDATE product SET category_id = ?, name = ?, description = ?, price = ?, status = ? WHERE id = ?";
    stmt = conn.prepareStatement(sql);
    stmt.setInt(1, category_id);
    stmt.setString(2, name);
    stmt.setString(3, description);
    stmt.setDouble(4, price);
    stmt.setString(5, status);
    stmt.setInt(6, id);
    stmt.executeUpdate();
    System.out.println("Data product berhasil diubah!");
}

static void deleteProduct() throws Exception {
    System.out.print("Masukkan ID product yang ingin dihapus: ");
    int id = Integer.parseInt(input.readLine());
    String sql = "DELETE FROM product WHERE id = ?";
    stmt = conn.prepareStatement(sql);
    stmt.setInt(1, id);
    stmt.executeUpdate();
    System.out.println("Data product berhasil dihapus!");
}

public static void main(String[] args) throws Exception {
    Class.forName(JDBC_DRIVER);
    conn = DriverManager.getConnection(DB_URL, USER, PASS);
    System.out.println("Koneksi ke database berhasil!");
    while (true) {
    System.out.println("1. Tampilkan data product");
    System.out.println("2. Tambah data product");
    System.out.println("3. Ubah data product");
    System.out.println("4. Hapus data product");
    System.out.println("5. Keluar");
    System.out.print("Pilih menu: ");
    int pilih = Integer.parseInt(input.readLine());
    if (pilih == 1) {
    showProduct();
    } else if (pilih == 2) {
    insertProduct();
    } else if (pilih == 3) {
    updateProduct();
    } else if (pilih == 4) {
    deleteProduct();
    } else if (pilih == 5) {
    System.out.println("Terima kasih telah menggunakan program ini.");
    break;
    } else {
    System.out.println("Menu tidak valid!");
    }
    }
    }
    }