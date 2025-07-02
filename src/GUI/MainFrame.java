/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DBConnect.DataBase;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;




/**
 *
 * @author nara
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        loadStatusKamar();
        tampilkanDataPenyewa();
        loadRiwayatSewa(); 
        
        PanelMainMenu.setVisible(true);
        PanelPenyewaBaru.setVisible(true);
        PanelPekerja.setVisible(true);
        PanelPelajar.setVisible(true);
        PanelEditPenyewa.setVisible(false);
        PanelKamar.setVisible(false);
        PanelRiwayatSewa.setVisible(false);

        CBStatusPenyewa.addActionListener((ActionEvent e) -> {
            String selectedStatus = (String) CBStatusPenyewa.getSelectedItem();
            if ("Pekerja".equals(selectedStatus)) {
                TFNamaPerusahaan.setEditable(true);
                TFJabatan.setEditable(true);
                
                TFNamaPerusahaan.setText("");
                TFJabatan.setText("");
                
                TFNamaSekolah.setText("-");
                TFNamaSekolah.setEditable(false);
                
                TFNamaJurusan.setText("-");
                TFNamaJurusan.setEditable(false);
                
            } else if ("Pelajar".equals(selectedStatus)) {
                TFNamaSekolah.setEditable(true);
                TFNamaJurusan.setEditable(true);
                
                TFNamaSekolah.setText("");
                TFNamaJurusan.setText("");
                
                TFNamaPerusahaan.setText("-");
                TFNamaPerusahaan.setEditable(false);
                
                TFJabatan.setText("-");
                TFJabatan.setEditable(false);
                
                
                
            }else {
                TFNamaSekolah.setText("");
                TFNamaJurusan.setText("");
                TFNamaPerusahaan.setText("");
                TFJabatan.setText("");
                
                TFNamaSekolah.setEditable(true);
                TFNamaSekolah.setEditable(true);
                TFNamaPerusahaan.setEditable(true);
                TFJabatan.setEditable(true);
            }
        });  
        
        

        
    }
    
    public void loadStatusKamar() {
    try {
        Connection conn = DataBase.getKoneksi(); // atau DBConnect.getKoneksi();
        String sql = "SELECT k.id_kamar, k.no_kamar, " +
                     "CASE WHEN EXISTS (" +
                     "    SELECT 1 FROM Sewa s " +
                     "    WHERE s.id_kamar = k.id_kamar " +
                     "    AND s.status_sewa = 'Disewa' " +
                     "    AND CAST(GETDATE() AS DATE) >= s.tanggal_mulai " +
                     "    AND CAST(GETDATE() AS DATE) <= s.tanggal_berakhir" +
                     ") THEN 'Disewa' ELSE 'Kosong' END AS status " +
                     "FROM Kamar k";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int idKamar = rs.getInt("id_kamar");
            String status = rs.getString("status");

            // Cek dan warnai tombol sesuai id kamar
            if (idKamar == 101) {
                BTNKamarA1.setBackground(status.equals("Disewa") ? Color.RED : Color.GREEN);
            } else if (idKamar == 102) {
                BTNKamarA2.setBackground(status.equals("Disewa") ? Color.RED : Color.GREEN);
            } else if (idKamar == 103) {
                BTNKamarA3.setBackground(status.equals("Disewa") ? Color.RED : Color.GREEN);
            }else if (idKamar == 104) {
                BTNKamarA4.setBackground(status.equals("Disewa") ? Color.RED : Color.GREEN);
            }else if (idKamar == 105) {
                BTNKamarA5.setBackground(status.equals("Disewa") ? Color.RED : Color.GREEN);
            }else if (idKamar == 106) {
                BTNKamarA6.setBackground(status.equals("Disewa") ? Color.RED : Color.GREEN);
            }else if (idKamar == 107) {
                BTNKamarA7.setBackground(status.equals("Disewa") ? Color.RED : Color.GREEN);
            }else if (idKamar == 108) {
                BTNKamarA8.setBackground(status.equals("Disewa") ? Color.RED : Color.GREEN);
            }else if (idKamar == 201) {
                BTNKamarB1.setBackground(status.equals("Disewa") ? Color.RED : Color.GREEN);
            }else if (idKamar == 202) {
                BTNKamarB2.setBackground(status.equals("Disewa") ? Color.RED : Color.GREEN);
            }else if (idKamar == 203) {
                BTNKamarB3.setBackground(status.equals("Disewa") ? Color.RED : Color.GREEN);
            }else if (idKamar == 204) {
                BTNKamarB4.setBackground(status.equals("Disewa") ? Color.RED : Color.GREEN);
            }else if (idKamar == 205) {
                BTNKamarB5.setBackground(status.equals("Disewa") ? Color.RED : Color.GREEN);
            }else if (idKamar == 206) {
                BTNKamarB6.setBackground(status.equals("Disewa") ? Color.RED : Color.GREEN);
            }else if (idKamar == 207) {
                BTNKamarB7.setBackground(status.equals("Disewa") ? Color.RED : Color.GREEN);
            }else if (idKamar == 208) {
                BTNKamarB8.setBackground(status.equals("Disewa") ? Color.RED : Color.GREEN);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPenyewaBaru = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TFNamaPenyewa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        CBStatusPenyewa = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        CBJenisKelamin = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        TFAlamat = new javax.swing.JTextField();
        PanelPekerja = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        TFNamaPerusahaan = new javax.swing.JTextField();
        TFJabatan = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        PanelPelajar = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        TFNamaSekolah = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        TFNamaJurusan = new javax.swing.JTextField();
        BTNinputPelajar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        TFTglLahir = new javax.swing.JTextField();
        PanelMainMenu = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        BTNiNPUT = new javax.swing.JButton();
        BTNEditPenyewa = new javax.swing.JButton();
        BTNEditPenyewa1 = new javax.swing.JButton();
        BTNRiwayatSewa = new javax.swing.JButton();
        PanelEditPenyewa = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BTNHapus = new javax.swing.JButton();
        BTNUpdate = new javax.swing.JButton();
        PanelKamar = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        PanelLantai1 = new javax.swing.JPanel();
        BTNKamarA1 = new javax.swing.JButton();
        BTNKamarA2 = new javax.swing.JButton();
        BTNKamarA3 = new javax.swing.JButton();
        BTNKamarA4 = new javax.swing.JButton();
        BTNKamarA5 = new javax.swing.JButton();
        BTNKamarA7 = new javax.swing.JButton();
        BTNKamarA6 = new javax.swing.JButton();
        BTNKamarA8 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        PanelLantai2 = new javax.swing.JPanel();
        BTNKamarB1 = new javax.swing.JButton();
        BTNKamarB2 = new javax.swing.JButton();
        BTNKamarB3 = new javax.swing.JButton();
        BTNKamarB4 = new javax.swing.JButton();
        BTNKamarB5 = new javax.swing.JButton();
        BTNKamarB7 = new javax.swing.JButton();
        BTNKamarB6 = new javax.swing.JButton();
        BTNKamarB8 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        PanelRiwayatSewa = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TBRiwayatSewa = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel1.setText("KOS-KOSAN Ahmed");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel2.setText("Nama Penyewa: ");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel3.setText("Status Penyewa: ");

        CBStatusPenyewa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pekerja", "Pelajar", "Lainnya" }));
        CBStatusPenyewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBStatusPenyewaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel4.setText("Jenis Kelamin:");

        CBJenisKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-laki", "Perempuan", "Lainnya" }));
        CBJenisKelamin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBJenisKelaminActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel6.setText("alamat:");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel5.setText("Nama Perusahaan:");

        TFNamaPerusahaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFNamaPerusahaanActionPerformed(evt);
            }
        });

        TFJabatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFJabatanActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel7.setText("Jabatan");

        javax.swing.GroupLayout PanelPekerjaLayout = new javax.swing.GroupLayout(PanelPekerja);
        PanelPekerja.setLayout(PanelPekerjaLayout);
        PanelPekerjaLayout.setHorizontalGroup(
            PanelPekerjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPekerjaLayout.createSequentialGroup()
                .addGroup(PanelPekerjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPekerjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TFNamaPerusahaan, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(TFJabatan)))
        );
        PanelPekerjaLayout.setVerticalGroup(
            PanelPekerjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPekerjaLayout.createSequentialGroup()
                .addGroup(PanelPekerjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TFNamaPerusahaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPekerjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TFJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel12.setText("Nama Sekolah/Kampus :");

        TFNamaSekolah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFNamaSekolahActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel13.setText("Jurusan:");

        TFNamaJurusan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFNamaJurusanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelPelajarLayout = new javax.swing.GroupLayout(PanelPelajar);
        PanelPelajar.setLayout(PanelPelajarLayout);
        PanelPelajarLayout.setHorizontalGroup(
            PanelPelajarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPelajarLayout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TFNamaJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPelajarLayout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TFNamaSekolah, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelPelajarLayout.setVerticalGroup(
            PanelPelajarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPelajarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPelajarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(TFNamaSekolah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPelajarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(TFNamaJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        BTNinputPelajar.setBackground(new java.awt.Color(102, 255, 102));
        BTNinputPelajar.setText("Input");
        BTNinputPelajar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNinputPelajarActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel10.setText("Tgl.Lahir: ");

        TFTglLahir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFTglLahirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelPenyewaBaruLayout = new javax.swing.GroupLayout(PanelPenyewaBaru);
        PanelPenyewaBaru.setLayout(PanelPenyewaBaruLayout);
        PanelPenyewaBaruLayout.setHorizontalGroup(
            PanelPenyewaBaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPenyewaBaruLayout.createSequentialGroup()
                .addGroup(PanelPenyewaBaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPenyewaBaruLayout.createSequentialGroup()
                        .addContainerGap(138, Short.MAX_VALUE)
                        .addGroup(PanelPenyewaBaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelPenyewaBaruLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(PanelPenyewaBaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelPenyewaBaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(PanelPenyewaBaruLayout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addGap(363, 363, 363))
                                        .addComponent(PanelPekerja, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelPenyewaBaruLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(102, 102, 102)
                                        .addGroup(PanelPenyewaBaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TFAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TFTglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(PanelPelajar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addComponent(BTNinputPelajar))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelPenyewaBaruLayout.createSequentialGroup()
                                .addGroup(PanelPenyewaBaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelPenyewaBaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(PanelPenyewaBaruLayout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(311, 311, 311))
                                        .addComponent(TFNamaPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelPenyewaBaruLayout.createSequentialGroup()
                                        .addGroup(PanelPenyewaBaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addGroup(PanelPenyewaBaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PanelPenyewaBaruLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(CBJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(PanelPenyewaBaruLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(CBStatusPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(PanelPenyewaBaruLayout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );
        PanelPenyewaBaruLayout.setVerticalGroup(
            PanelPenyewaBaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPenyewaBaruLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(PanelPenyewaBaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TFNamaPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPenyewaBaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CBStatusPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPenyewaBaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CBJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(PanelPenyewaBaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPenyewaBaruLayout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(BTNinputPelajar))
                    .addGroup(PanelPenyewaBaruLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelPenyewaBaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TFAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelPenyewaBaruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(TFTglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelPekerja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelPelajar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/assets.png"))); // NOI18N

        BTNiNPUT.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        BTNiNPUT.setText("INPUT PENYEWA");
        BTNiNPUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNiNPUTActionPerformed(evt);
            }
        });

        BTNEditPenyewa.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        BTNEditPenyewa.setText("Edit Penyewa ");
        BTNEditPenyewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNEditPenyewaActionPerformed(evt);
            }
        });

        BTNEditPenyewa1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        BTNEditPenyewa1.setText("Sewa kamar");
        BTNEditPenyewa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNEditPenyewa1ActionPerformed(evt);
            }
        });

        BTNRiwayatSewa.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        BTNRiwayatSewa.setText("Riwayat Sewa");
        BTNRiwayatSewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNRiwayatSewaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelMainMenuLayout = new javax.swing.GroupLayout(PanelMainMenu);
        PanelMainMenu.setLayout(PanelMainMenuLayout);
        PanelMainMenuLayout.setHorizontalGroup(
            PanelMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainMenuLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(PanelMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BTNiNPUT, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMainMenuLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(41, 41, 41))
                    .addComponent(BTNEditPenyewa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTNEditPenyewa1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTNRiwayatSewa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        PanelMainMenuLayout.setVerticalGroup(
            PanelMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainMenuLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTNiNPUT, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BTNEditPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BTNEditPenyewa1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BTNRiwayatSewa, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel8.setText("Edit Penyewa");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Penyewa", "Status Penyewa", "Jenis Kelamin", "alamat asal", "Tgl.Lahir", "Nama Perusahaan", "Jabatan", "Nama Sekolah/Kampus", "Jurusan"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        BTNHapus.setBackground(new java.awt.Color(255, 0, 0));
        BTNHapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BTNHapus.setText("Delete");
        BTNHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNHapusActionPerformed(evt);
            }
        });

        BTNUpdate.setBackground(new java.awt.Color(255, 255, 51));
        BTNUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BTNUpdate.setText("Update");
        BTNUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelEditPenyewaLayout = new javax.swing.GroupLayout(PanelEditPenyewa);
        PanelEditPenyewa.setLayout(PanelEditPenyewaLayout);
        PanelEditPenyewaLayout.setHorizontalGroup(
            PanelEditPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEditPenyewaLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(PanelEditPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelEditPenyewaLayout.createSequentialGroup()
                        .addComponent(BTNHapus)
                        .addGap(18, 18, 18)
                        .addComponent(BTNUpdate)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelEditPenyewaLayout.createSequentialGroup()
                .addContainerGap(341, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(338, 338, 338))
        );
        PanelEditPenyewaLayout.setVerticalGroup(
            PanelEditPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEditPenyewaLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelEditPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Sewa Kamar");

        BTNKamarA1.setText("A1");
        BTNKamarA1.setActionCommand("");
        BTNKamarA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNKamarA1ActionPerformed(evt);
            }
        });

        BTNKamarA2.setText("A2");
        BTNKamarA2.setActionCommand("");
        BTNKamarA2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNKamarA2ActionPerformed(evt);
            }
        });

        BTNKamarA3.setText("A3");
        BTNKamarA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNKamarA3ActionPerformed(evt);
            }
        });

        BTNKamarA4.setText("A4");
        BTNKamarA4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNKamarA4ActionPerformed(evt);
            }
        });

        BTNKamarA5.setText("A5");
        BTNKamarA5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNKamarA5ActionPerformed(evt);
            }
        });

        BTNKamarA7.setText("A7");
        BTNKamarA7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNKamarA7ActionPerformed(evt);
            }
        });

        BTNKamarA6.setText("A6");
        BTNKamarA6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNKamarA6ActionPerformed(evt);
            }
        });

        BTNKamarA8.setText("A8");
        BTNKamarA8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNKamarA8ActionPerformed(evt);
            }
        });

        jLabel14.setText("Lantai 1");

        javax.swing.GroupLayout PanelLantai1Layout = new javax.swing.GroupLayout(PanelLantai1);
        PanelLantai1.setLayout(PanelLantai1Layout);
        PanelLantai1Layout.setHorizontalGroup(
            PanelLantai1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLantai1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PanelLantai1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTNKamarA3)
                    .addComponent(BTNKamarA4)
                    .addComponent(BTNKamarA2)
                    .addComponent(BTNKamarA1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 312, Short.MAX_VALUE)
                .addGroup(PanelLantai1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTNKamarA6)
                    .addComponent(BTNKamarA5)
                    .addComponent(BTNKamarA7)
                    .addComponent(BTNKamarA8))
                .addGap(39, 39, 39))
            .addGroup(PanelLantai1Layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelLantai1Layout.setVerticalGroup(
            PanelLantai1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLantai1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(32, 32, 32)
                .addGroup(PanelLantai1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLantai1Layout.createSequentialGroup()
                        .addComponent(BTNKamarA5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNKamarA6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNKamarA7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNKamarA8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelLantai1Layout.createSequentialGroup()
                        .addComponent(BTNKamarA1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNKamarA2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNKamarA3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNKamarA4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        BTNKamarB1.setText("B1");
        BTNKamarB1.setActionCommand("");
        BTNKamarB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNKamarB1ActionPerformed(evt);
            }
        });

        BTNKamarB2.setText("B2");
        BTNKamarB2.setActionCommand("");
        BTNKamarB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNKamarB2ActionPerformed(evt);
            }
        });

        BTNKamarB3.setText("B3");
        BTNKamarB3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNKamarB3ActionPerformed(evt);
            }
        });

        BTNKamarB4.setText("B4");
        BTNKamarB4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNKamarB4ActionPerformed(evt);
            }
        });

        BTNKamarB5.setText("B5");
        BTNKamarB5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNKamarB5ActionPerformed(evt);
            }
        });

        BTNKamarB7.setText("B7");
        BTNKamarB7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNKamarB7ActionPerformed(evt);
            }
        });

        BTNKamarB6.setText("B6");
        BTNKamarB6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNKamarB6ActionPerformed(evt);
            }
        });

        BTNKamarB8.setText("B8");
        BTNKamarB8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNKamarB8ActionPerformed(evt);
            }
        });

        jLabel15.setText("Lantai 2");

        javax.swing.GroupLayout PanelLantai2Layout = new javax.swing.GroupLayout(PanelLantai2);
        PanelLantai2.setLayout(PanelLantai2Layout);
        PanelLantai2Layout.setHorizontalGroup(
            PanelLantai2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLantai2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PanelLantai2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTNKamarB3)
                    .addComponent(BTNKamarB4)
                    .addComponent(BTNKamarB2)
                    .addComponent(BTNKamarB1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 318, Short.MAX_VALUE)
                .addGroup(PanelLantai2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTNKamarB6)
                    .addComponent(BTNKamarB5)
                    .addComponent(BTNKamarB7)
                    .addComponent(BTNKamarB8))
                .addGap(39, 39, 39))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLantai2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(231, 231, 231))
        );
        PanelLantai2Layout.setVerticalGroup(
            PanelLantai2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLantai2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(32, 32, 32)
                .addGroup(PanelLantai2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLantai2Layout.createSequentialGroup()
                        .addComponent(BTNKamarB5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNKamarB6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNKamarB7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNKamarB8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelLantai2Layout.createSequentialGroup()
                        .addComponent(BTNKamarB1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNKamarB2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNKamarB3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNKamarB4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelKamarLayout = new javax.swing.GroupLayout(PanelKamar);
        PanelKamar.setLayout(PanelKamarLayout);
        PanelKamarLayout.setHorizontalGroup(
            PanelKamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKamarLayout.createSequentialGroup()
                .addGroup(PanelKamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelKamarLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(PanelLantai1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(178, 178, 178)
                        .addComponent(PanelLantai2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelKamarLayout.createSequentialGroup()
                        .addGap(611, 611, 611)
                        .addComponent(jLabel11)))
                .addGap(0, 24, Short.MAX_VALUE))
        );
        PanelKamarLayout.setVerticalGroup(
            PanelKamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKamarLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel11)
                .addGap(49, 49, 49)
                .addGroup(PanelKamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelLantai1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelLantai2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        TBRiwayatSewa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(TBRiwayatSewa);

        javax.swing.GroupLayout PanelRiwayatSewaLayout = new javax.swing.GroupLayout(PanelRiwayatSewa);
        PanelRiwayatSewa.setLayout(PanelRiwayatSewaLayout);
        PanelRiwayatSewaLayout.setHorizontalGroup(
            PanelRiwayatSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRiwayatSewaLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        PanelRiwayatSewaLayout.setVerticalGroup(
            PanelRiwayatSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRiwayatSewaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(PanelMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelEditPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelPenyewaBaru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelRiwayatSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelEditPenyewa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelPenyewaBaru, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelRiwayatSewa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CBStatusPenyewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBStatusPenyewaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBStatusPenyewaActionPerformed

    private void CBJenisKelaminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBJenisKelaminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBJenisKelaminActionPerformed

    private void TFNamaPerusahaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFNamaPerusahaanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFNamaPerusahaanActionPerformed

    private void TFJabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFJabatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFJabatanActionPerformed

    private void BTNinputPelajarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNinputPelajarActionPerformed
        try {
            SimpleDateFormat dateS = new SimpleDateFormat("yyyy-MM-dd");
            String nama = TFNamaPenyewa.getText();
            String alamat = TFAlamat.getText();
            String Status = (String) CBStatusPenyewa.getSelectedItem();
            String JK = (String) CBJenisKelamin.getSelectedItem();
            String TglLahir = TFTglLahir.getText().trim();
            String NamaPerusahaan = TFNamaPerusahaan.getText();
            String Jabatan = TFJabatan.getText();
            String Kampus = TFNamaSekolah.getText();
            String Jurusan = TFNamaJurusan.getText();
            String id_penyewa = null;
            
            Connection kon = DataBase.getKoneksi();
            kon.setAutoCommit(false);
            
        java.util.Date utilDate = dateS.parse(TglLahir);
        
        // Convert to java.sql.Date for database
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
            try {
                String sql1 = "INSERT INTO penyewa (nama, alamat, jenis_kelamin, tanggal_lahir, asal_kampus, jurusan, nama_perusahaan, jabatan) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement st1 = kon.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS); 
                st1.setString(1, nama);
                st1.setString(2, alamat);
                st1.setString(3, JK);
                st1.setDate(4, sqlDate);
                st1.setString(5, Kampus);
                st1.setString(6, Jurusan);
                st1.setString(7, NamaPerusahaan);
                st1.setString(8, Jabatan);
                st1.executeUpdate();
                if (id_penyewa == null){
                ResultSet rs = st1.getGeneratedKeys();
                if(rs.next()){
                int generatedId = rs.getInt(1);
                kon.commit();
                }
                }
                
            } catch (SQLException e) {
            // Rollback if any error occurs
            kon.rollback();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Reset auto-commit and close connection
            kon.setAutoCommit(true);
            kon.close();
}
            
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.addRow(new Object[]{nama, Status, JK, alamat, TglLahir, NamaPerusahaan, Jabatan, Kampus, Jurusan});
            
            TFNamaPenyewa.setText("");
            TFAlamat.setText("");
            CBStatusPenyewa.setSelectedItem("Pekerja");
            CBJenisKelamin.setSelectedItem("Laki-laki");
            TFTglLahir.setText("");
            TFNamaPerusahaan.setText("");
            TFJabatan.setText("");
            TFNamaSekolah.setText("");
            TFNamaJurusan.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BTNinputPelajarActionPerformed

    private void BTNiNPUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNiNPUTActionPerformed
        PanelMainMenu.setVisible(true);
        PanelPenyewaBaru.setVisible(true);
        PanelPekerja.setVisible(true);
        PanelEditPenyewa.setVisible(false);
        PanelKamar.setVisible(false);
        PanelRiwayatSewa.setVisible(false);
    }//GEN-LAST:event_BTNiNPUTActionPerformed

    private void BTNEditPenyewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNEditPenyewaActionPerformed
        PanelMainMenu.setVisible(true);
        PanelPenyewaBaru.setVisible(false);
        PanelEditPenyewa.setVisible(true);
        PanelKamar.setVisible(false);
        PanelRiwayatSewa.setVisible(false);
    }//GEN-LAST:event_BTNEditPenyewaActionPerformed

    private void TFNamaSekolahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFNamaSekolahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFNamaSekolahActionPerformed

    private void TFNamaJurusanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFNamaJurusanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFNamaJurusanActionPerformed

    private void TFTglLahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFTglLahirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFTglLahirActionPerformed


    private void BTNUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNUpdateActionPerformed
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Pilih baris terlebih dahulu!");
            return;
        }

        String id = jTable1.getValueAt(row, 0).toString();
        String nama = jTable1.getValueAt(row, 1).toString();
        String alamat = jTable1.getValueAt(row, 2).toString();
        String kelamin = jTable1.getValueAt(row, 3).toString();
        String tglLahir = jTable1.getValueAt(row, 4).toString();
        String jurusan = jTable1.getValueAt(row, 5).toString();
        String asalKampus = jTable1.getValueAt(row, 6).toString();
        String jabatan = jTable1.getValueAt(row, 7).toString();
        String perusahaan = jTable1.getValueAt(row, 8).toString();


        DialogUpdate dialog = new DialogUpdate(id, nama, alamat, kelamin, tglLahir, jurusan, asalKampus, jabatan, perusahaan);
        dialog.setVisible(true);

      
        loadTable();
    }//GEN-LAST:event_BTNUpdateActionPerformed
private void hapusDataPenyewa() {
    int selectedRow = jTable1.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Pilih baris data yang ingin dihapus terlebih dahulu.");
    } else {
        int konfirmasi = JOptionPane.showConfirmDialog(null,
            "Yakin ingin menghapus data ini?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                // Ambil ID dari kolom pertama (kolom ID)
                int idPenyewa = (int) jTable1.getValueAt(selectedRow, 0);

                // Hapus dari database
                Connection conn = DataBase.getKoneksi();
                String sql = "DELETE FROM Penyewa WHERE id_penyewa = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, idPenyewa);
                int affectedRows = ps.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus dari database.");
                    tampilkanDataPenyewa(); // Refresh tabel
                } else {
                    JOptionPane.showMessageDialog(null, "Data gagal dihapus dari database.");
                }

                ps.close();
                conn.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghapus data: " + e.getMessage());
            }
        }
    }
}
    private void BTNHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNHapusActionPerformed
        hapusDataPenyewa();
    }//GEN-LAST:event_BTNHapusActionPerformed
private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Vector<Vector> TableData = model.getDataVector();
        
        try {
        FileOutputStream file = new FileOutputStream("file.bin");
        ObjectOutputStream output =  new ObjectOutputStream(file);
        
        output.writeObject(TableData);
        
        output.close();
        file.close();
        }catch (Exception ex){
        ex.printStackTrace();
        }
    }//GEN-LAST:event_formWindowClosing

    private void BTNEditPenyewa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNEditPenyewa1ActionPerformed
        PanelMainMenu.setVisible(true);
        PanelPenyewaBaru.setVisible(false);
        PanelEditPenyewa.setVisible(false);
        PanelKamar.setVisible(true);
        PanelRiwayatSewa.setVisible(false);
    }//GEN-LAST:event_BTNEditPenyewa1ActionPerformed

    private void BTNKamarA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNKamarA1ActionPerformed
        try {
        Connection conn = DataBase.getKoneksi();
        String sql = "SELECT * FROM Kamar WHERE id_kamar = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 101); // contoh ID kamar
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DialogSewa dlg = new DialogSewa(this, true);
            dlg.setDataKamar(
                rs.getInt("id_kamar"),
                rs.getString("no_kamar"),
                rs.getInt("lantai"),
                rs.getString("jenis_kamar"),
                rs.getDouble("harga_perbulan"),
                rs.getString("fasilitas"),
                rs.getInt("id_pemilik")
            );
            dlg.setVisible(true);
             if (dlg.isBerhasil() && dlg.getStatus().equalsIgnoreCase("Disewa")) {
                BTNKamarA1.setBackground(Color.RED);
            } else {
                BTNKamarA1.setBackground(Color.GREEN); // opsional
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_BTNKamarA1ActionPerformed

    private void BTNKamarA2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNKamarA2ActionPerformed
        try {
        Connection conn = DataBase.getKoneksi();
        String sql = "SELECT * FROM Kamar WHERE id_kamar = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 102); // contoh ID kamar
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DialogSewa dlg = new DialogSewa(this, true);
            dlg.setDataKamar(
                rs.getInt("id_kamar"),
                rs.getString("no_kamar"),
                rs.getInt("lantai"),
                rs.getString("jenis_kamar"),
                rs.getDouble("harga_perbulan"),
                rs.getString("fasilitas"),
                rs.getInt("id_pemilik")
            );
            dlg.setVisible(true);
             if (dlg.isBerhasil() && dlg.getStatus().equalsIgnoreCase("Disewa")) {
                BTNKamarA2.setBackground(Color.RED);
            } else {
                BTNKamarA2.setBackground(Color.GREEN); // opsional
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_BTNKamarA2ActionPerformed

    private void BTNKamarA3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNKamarA3ActionPerformed
        try {
        Connection conn = DataBase.getKoneksi();
        String sql = "SELECT * FROM Kamar WHERE id_kamar = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 103); // contoh ID kamar
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DialogSewa dlg = new DialogSewa(this, true);
            dlg.setDataKamar(
                rs.getInt("id_kamar"),
                rs.getString("no_kamar"),
                rs.getInt("lantai"),
                rs.getString("jenis_kamar"),
                rs.getDouble("harga_perbulan"),
                rs.getString("fasilitas"),
                rs.getInt("id_pemilik")
            );
            dlg.setVisible(true);
             if (dlg.isBerhasil() && dlg.getStatus().equalsIgnoreCase("Disewa")) {
                BTNKamarA3.setBackground(Color.RED);
            } else {
                BTNKamarA3.setBackground(Color.GREEN); // opsional
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_BTNKamarA3ActionPerformed

    private void BTNKamarA4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNKamarA4ActionPerformed
        try {
        Connection conn = DataBase.getKoneksi();
        String sql = "SELECT * FROM Kamar WHERE id_kamar = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 104); // contoh ID kamar
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DialogSewa dlg = new DialogSewa(this, true);
            dlg.setDataKamar(
                rs.getInt("id_kamar"),
                rs.getString("no_kamar"),
                rs.getInt("lantai"),
                rs.getString("jenis_kamar"),
                rs.getDouble("harga_perbulan"),
                rs.getString("fasilitas"),
                rs.getInt("id_pemilik")
            );
            dlg.setVisible(true);
             if (dlg.isBerhasil() && dlg.getStatus().equalsIgnoreCase("Disewa")) {
                BTNKamarA4.setBackground(Color.RED);
            } else {
                BTNKamarA4.setBackground(Color.GREEN); // opsional
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_BTNKamarA4ActionPerformed

    private void BTNKamarA8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNKamarA8ActionPerformed
        try {
        Connection conn = DataBase.getKoneksi();
        String sql = "SELECT * FROM Kamar WHERE id_kamar = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 108); // contoh ID kamar
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DialogSewa dlg = new DialogSewa(this, true);
            dlg.setDataKamar(
                rs.getInt("id_kamar"),
                rs.getString("no_kamar"),
                rs.getInt("lantai"),
                rs.getString("jenis_kamar"),
                rs.getDouble("harga_perbulan"),
                rs.getString("fasilitas"),
                rs.getInt("id_pemilik")
            );
            dlg.setVisible(true);
             if (dlg.isBerhasil() && dlg.getStatus().equalsIgnoreCase("Disewa")) {
                BTNKamarA8.setBackground(Color.RED);
            } else {
                BTNKamarA8.setBackground(Color.GREEN); // opsional
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_BTNKamarA8ActionPerformed

    private void BTNKamarA7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNKamarA7ActionPerformed
        try {
        Connection conn = DataBase.getKoneksi();
        String sql = "SELECT * FROM Kamar WHERE id_kamar = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 107); // contoh ID kamar
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DialogSewa dlg = new DialogSewa(this, true);
            dlg.setDataKamar(
                rs.getInt("id_kamar"),
                rs.getString("no_kamar"),
                rs.getInt("lantai"),
                rs.getString("jenis_kamar"),
                rs.getDouble("harga_perbulan"),
                rs.getString("fasilitas"),
                rs.getInt("id_pemilik")
            );
            dlg.setVisible(true);
             if (dlg.isBerhasil() && dlg.getStatus().equalsIgnoreCase("Disewa")) {
                BTNKamarA7.setBackground(Color.RED);
            } else {
                BTNKamarA7.setBackground(Color.GREEN); // opsional
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_BTNKamarA7ActionPerformed

    private void BTNKamarA6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNKamarA6ActionPerformed
        try {
        Connection conn = DataBase.getKoneksi();
        String sql = "SELECT * FROM Kamar WHERE id_kamar = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 106); // contoh ID kamar
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DialogSewa dlg = new DialogSewa(this, true);
            dlg.setDataKamar(
                rs.getInt("id_kamar"),
                rs.getString("no_kamar"),
                rs.getInt("lantai"),
                rs.getString("jenis_kamar"),
                rs.getDouble("harga_perbulan"),
                rs.getString("fasilitas"),
                rs.getInt("id_pemilik")
            );
            dlg.setVisible(true);
             if (dlg.isBerhasil() && dlg.getStatus().equalsIgnoreCase("Disewa")) {
                BTNKamarA6.setBackground(Color.RED);
            } else {
                BTNKamarA6.setBackground(Color.GREEN); // opsional
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_BTNKamarA6ActionPerformed

    private void BTNKamarA5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNKamarA5ActionPerformed
        try {
        Connection conn = DataBase.getKoneksi();
        String sql = "SELECT * FROM Kamar WHERE id_kamar = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 105); // contoh ID kamar
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DialogSewa dlg = new DialogSewa(this, true);
            dlg.setDataKamar(
                rs.getInt("id_kamar"),
                rs.getString("no_kamar"),
                rs.getInt("lantai"),
                rs.getString("jenis_kamar"),
                rs.getDouble("harga_perbulan"),
                rs.getString("fasilitas"),
                rs.getInt("id_pemilik")
            );
            dlg.setVisible(true);
             if (dlg.isBerhasil() && dlg.getStatus().equalsIgnoreCase("Disewa")) {
                BTNKamarA5.setBackground(Color.RED);
            } else {
                BTNKamarA5.setBackground(Color.GREEN); // opsional
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_BTNKamarA5ActionPerformed

    private void BTNKamarB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNKamarB1ActionPerformed
        try {
        Connection conn = DataBase.getKoneksi();
        String sql = "SELECT * FROM Kamar WHERE id_kamar = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 201); // contoh ID kamar
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DialogSewa dlg = new DialogSewa(this, true);
            dlg.setDataKamar(
                rs.getInt("id_kamar"),
                rs.getString("no_kamar"),
                rs.getInt("lantai"),
                rs.getString("jenis_kamar"),
                rs.getDouble("harga_perbulan"),
                rs.getString("fasilitas"),
                rs.getInt("id_pemilik")
            );
            dlg.setVisible(true);
             if (dlg.isBerhasil() && dlg.getStatus().equalsIgnoreCase("Disewa")) {
                BTNKamarB1.setBackground(Color.RED);
            } else {
                BTNKamarB1.setBackground(Color.GREEN); // opsional
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_BTNKamarB1ActionPerformed

    private void BTNKamarB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNKamarB2ActionPerformed
       try {
        Connection conn = DataBase.getKoneksi();
        String sql = "SELECT * FROM Kamar WHERE id_kamar = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 201); // contoh ID kamar
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DialogSewa dlg = new DialogSewa(this, true);
            dlg.setDataKamar(
                rs.getInt("id_kamar"),
                rs.getString("no_kamar"),
                rs.getInt("lantai"),
                rs.getString("jenis_kamar"),
                rs.getDouble("harga_perbulan"),
                rs.getString("fasilitas"),
                rs.getInt("id_pemilik")
            );
            dlg.setVisible(true);
             if (dlg.isBerhasil() && dlg.getStatus().equalsIgnoreCase("Disewa")) {
                BTNKamarB1.setBackground(Color.RED);
            } else {
                BTNKamarB1.setBackground(Color.GREEN); // opsional
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_BTNKamarB2ActionPerformed

    private void BTNKamarB3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNKamarB3ActionPerformed
        try {
        Connection conn = DataBase.getKoneksi();
        String sql = "SELECT * FROM Kamar WHERE id_kamar = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 203); // contoh ID kamar
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DialogSewa dlg = new DialogSewa(this, true);
            dlg.setDataKamar(
                rs.getInt("id_kamar"),
                rs.getString("no_kamar"),
                rs.getInt("lantai"),
                rs.getString("jenis_kamar"),
                rs.getDouble("harga_perbulan"),
                rs.getString("fasilitas"),
                rs.getInt("id_pemilik")
            );
            dlg.setVisible(true);
             if (dlg.isBerhasil() && dlg.getStatus().equalsIgnoreCase("Disewa")) {
                BTNKamarB3.setBackground(Color.RED);
            } else {
                BTNKamarB3.setBackground(Color.GREEN); // opsional
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_BTNKamarB3ActionPerformed

    private void BTNKamarB4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNKamarB4ActionPerformed
        try {
        Connection conn = DataBase.getKoneksi();
        String sql = "SELECT * FROM Kamar WHERE id_kamar = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 204); // contoh ID kamar
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DialogSewa dlg = new DialogSewa(this, true);
            dlg.setDataKamar(
                rs.getInt("id_kamar"),
                rs.getString("no_kamar"),
                rs.getInt("lantai"),
                rs.getString("jenis_kamar"),
                rs.getDouble("harga_perbulan"),
                rs.getString("fasilitas"),
                rs.getInt("id_pemilik")
            );
            dlg.setVisible(true);
             if (dlg.isBerhasil() && dlg.getStatus().equalsIgnoreCase("Disewa")) {
                BTNKamarB4.setBackground(Color.RED);
            } else {
                BTNKamarB4.setBackground(Color.GREEN); // opsional
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_BTNKamarB4ActionPerformed

    private void BTNKamarB5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNKamarB5ActionPerformed
        try {
        Connection conn = DataBase.getKoneksi();
        String sql = "SELECT * FROM Kamar WHERE id_kamar = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 205); // contoh ID kamar
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DialogSewa dlg = new DialogSewa(this, true);
            dlg.setDataKamar(
                rs.getInt("id_kamar"),
                rs.getString("no_kamar"),
                rs.getInt("lantai"),
                rs.getString("jenis_kamar"),
                rs.getDouble("harga_perbulan"),
                rs.getString("fasilitas"),
                rs.getInt("id_pemilik")
            );
            dlg.setVisible(true);
             if (dlg.isBerhasil() && dlg.getStatus().equalsIgnoreCase("Disewa")) {
                BTNKamarB5.setBackground(Color.RED);
            } else {
                BTNKamarB5.setBackground(Color.GREEN); // opsional
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_BTNKamarB5ActionPerformed

    private void BTNKamarB7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNKamarB7ActionPerformed
        try {
        Connection conn = DataBase.getKoneksi();
        String sql = "SELECT * FROM Kamar WHERE id_kamar = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 207); // contoh ID kamar
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DialogSewa dlg = new DialogSewa(this, true);
            dlg.setDataKamar(
                rs.getInt("id_kamar"),
                rs.getString("no_kamar"),
                rs.getInt("lantai"),
                rs.getString("jenis_kamar"),
                rs.getDouble("harga_perbulan"),
                rs.getString("fasilitas"),
                rs.getInt("id_pemilik")
            );
            dlg.setVisible(true);
             if (dlg.isBerhasil() && dlg.getStatus().equalsIgnoreCase("Disewa")) {
                BTNKamarB7.setBackground(Color.RED);
            } else {
                BTNKamarB7.setBackground(Color.GREEN); // opsional
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_BTNKamarB7ActionPerformed

    private void BTNKamarB6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNKamarB6ActionPerformed
        try {
        Connection conn = DataBase.getKoneksi();
        String sql = "SELECT * FROM Kamar WHERE id_kamar = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 206); // contoh ID kamar
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DialogSewa dlg = new DialogSewa(this, true);
            dlg.setDataKamar(
                rs.getInt("id_kamar"),
                rs.getString("no_kamar"),
                rs.getInt("lantai"),
                rs.getString("jenis_kamar"),
                rs.getDouble("harga_perbulan"),
                rs.getString("fasilitas"),
                rs.getInt("id_pemilik")
            );
            dlg.setVisible(true);
             if (dlg.isBerhasil() && dlg.getStatus().equalsIgnoreCase("Disewa")) {
                BTNKamarB6.setBackground(Color.RED);
            } else {
                BTNKamarB6.setBackground(Color.GREEN); // opsional
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_BTNKamarB6ActionPerformed

    private void BTNKamarB8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNKamarB8ActionPerformed
        try {
        Connection conn = DataBase.getKoneksi();
        String sql = "SELECT * FROM Kamar WHERE id_kamar = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 208); // contoh ID kamar
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DialogSewa dlg = new DialogSewa(this, true);
            dlg.setDataKamar(
                rs.getInt("id_kamar"),
                rs.getString("no_kamar"),
                rs.getInt("lantai"),
                rs.getString("jenis_kamar"),
                rs.getDouble("harga_perbulan"),
                rs.getString("fasilitas"),
                rs.getInt("id_pemilik")
            );
            dlg.setVisible(true);
             if (dlg.isBerhasil() && dlg.getStatus().equalsIgnoreCase("Disewa")) {
                BTNKamarB8.setBackground(Color.RED);
            } else {
                BTNKamarB8.setBackground(Color.GREEN); // opsional
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_BTNKamarB8ActionPerformed

    private void BTNRiwayatSewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRiwayatSewaActionPerformed
         PanelMainMenu.setVisible(true);
        PanelPenyewaBaru.setVisible(false);
        PanelEditPenyewa.setVisible(false);
        PanelKamar.setVisible(false);
        PanelRiwayatSewa.setVisible(true);
    }//GEN-LAST:event_BTNRiwayatSewaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {                                  
        try {
        FileInputStream file = new FileInputStream("file.bin");
        ObjectInputStream input =  new ObjectInputStream(file);
        
         Vector<Vector> TableData =  (Vector<Vector>)input.readObject();
        
        input.close();
        file.close();
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        for(int i = 0; i< TableData.size(); i++){
        Vector row = TableData.get(i);
        model.addRow(new Object[]{row.get(0),row.get(1),row.get(2),row.get(3)});
        }
        }catch (Exception ex){
        ex.printStackTrace();
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
            
        });
        
    }
private void tampilkanDataPenyewa() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID");
    model.addColumn("Nama");
    model.addColumn("Alamat");
    model.addColumn("Jenis Kelamin");
    model.addColumn("Tanggal Lahir");
    model.addColumn("Jurusan");
    model.addColumn("Asal Kampus");
    model.addColumn("Jabatan");
    model.addColumn("Nama Perusahaan");

    try {
        Connection conn = DataBase.getKoneksi();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Penyewa");

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id_penyewa"),
                rs.getString("nama"),
                rs.getString("alamat"),
                rs.getString("jenis_kelamin"),
                rs.getDate("tanggal_lahir"),
                rs.getString("jurusan"),
                rs.getString("asal_kampus"),
                rs.getString("jabatan"),
                rs.getString("nama_perusahaan")
            });
        }

        jTable1.setModel(model);
      

        stmt.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal menampilkan data: " + e.getMessage());
    }
}
public void loadTable() {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
model.setRowCount(0); // Clear data lama

try {
    Connection conn = DataBase.getKoneksi();
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery("SELECT * FROM penyewa");

    while (rs.next()) {
        model.addRow(new Object[]{
            rs.getInt("id_penyewa"),
            rs.getString("nama"),
            rs.getString("alamat"),
            rs.getString("jenis_kelamin"),
            rs.getString("tanggal_lahir"),
            rs.getString("jurusan"),
            rs.getString("asal_kampus"),
            rs.getString("jabatan"),
            rs.getString("nama_perusahaan")
        });
    }
} catch (Exception e) {
    e.printStackTrace();
}
}
public void loadRiwayatSewa() {
    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(new String[] {
        "ID Sewa", "ID Penyewa", "ID Kamar", "Mulai", "Berakhir", "Durasi", "Harga", "Total", "Status"
    });

    try {
        Connection conn = DataBase.getKoneksi(); // atau DBConnect.getKoneksi();
        String sql = "SELECT * FROM Sewa";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[] {
                rs.getInt("id_sewa"),
                rs.getInt("id_penyewa"),
                rs.getInt("id_kamar"),
                rs.getDate("tanggal_mulai"),
                rs.getDate("tanggal_berakhir"),
                rs.getInt("durasi_bulan"),
                rs.getDouble("harga_perbulan"),
                rs.getDouble("total_biaya"),
                rs.getString("status_sewa")
            });
        }

        TBRiwayatSewa.setModel(model);
        TBRiwayatSewa   .setEnabled(false); // Biar tidak bisa diubah
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNEditPenyewa;
    private javax.swing.JButton BTNEditPenyewa1;
    private javax.swing.JButton BTNHapus;
    private javax.swing.JButton BTNKamarA1;
    private javax.swing.JButton BTNKamarA2;
    private javax.swing.JButton BTNKamarA3;
    private javax.swing.JButton BTNKamarA4;
    private javax.swing.JButton BTNKamarA5;
    private javax.swing.JButton BTNKamarA6;
    private javax.swing.JButton BTNKamarA7;
    private javax.swing.JButton BTNKamarA8;
    private javax.swing.JButton BTNKamarB1;
    private javax.swing.JButton BTNKamarB2;
    private javax.swing.JButton BTNKamarB3;
    private javax.swing.JButton BTNKamarB4;
    private javax.swing.JButton BTNKamarB5;
    private javax.swing.JButton BTNKamarB6;
    private javax.swing.JButton BTNKamarB7;
    private javax.swing.JButton BTNKamarB8;
    private javax.swing.JButton BTNRiwayatSewa;
    private javax.swing.JButton BTNUpdate;
    private javax.swing.JButton BTNiNPUT;
    private javax.swing.JButton BTNinputPelajar;
    private javax.swing.JComboBox<String> CBJenisKelamin;
    private javax.swing.JComboBox<String> CBStatusPenyewa;
    private javax.swing.JPanel PanelEditPenyewa;
    private javax.swing.JPanel PanelKamar;
    private javax.swing.JPanel PanelLantai1;
    private javax.swing.JPanel PanelLantai2;
    private javax.swing.JPanel PanelMainMenu;
    private javax.swing.JPanel PanelPekerja;
    private javax.swing.JPanel PanelPelajar;
    private javax.swing.JPanel PanelPenyewaBaru;
    private javax.swing.JPanel PanelRiwayatSewa;
    private javax.swing.JTable TBRiwayatSewa;
    private javax.swing.JTextField TFAlamat;
    private javax.swing.JTextField TFJabatan;
    private javax.swing.JTextField TFNamaJurusan;
    private javax.swing.JTextField TFNamaPenyewa;
    private javax.swing.JTextField TFNamaPerusahaan;
    private javax.swing.JTextField TFNamaSekolah;
    private javax.swing.JTextField TFTglLahir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

