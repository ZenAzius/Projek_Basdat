/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nara
 */
    public class DataBase {

    /**
     * @param args the command line arguments
     */
    
    private static Connection koneksi;
    
    public static Connection getKoneksi(){
        if (koneksi == null) {
            try{
            String server = "192.168.1.4";
            String database = "Project_Basdat";
            String user = "ProjectBasdat";
            String password = "kelompok10_123";
            String url = "jdbc:sqlserver://" + server + ":1433;databaseName=" + database + ";encrypt=false";
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi berhasil");
            }catch (ClassNotFoundException e) {
                System.out.println("Driver tidak ditemukan: " + e.getMessage());
            }catch (SQLException e) {
                System.out.println("Koneksi gagal: " + e.getMessage());
            }
        }
        return koneksi;
    }
}

