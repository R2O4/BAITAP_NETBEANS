/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuchanhtuan2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author trann
 */
public class Bai2 extends javax.swing.JFrame {

    /**
     * Creates new form Bai2
     */
    public Bai2() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNhapChuoi = new javax.swing.JTextField();
        txtNoiDung = new javax.swing.JTextField();
        txtTenTapTin = new javax.swing.JTextField();
        btnDocFileNP = new javax.swing.JButton();
        btnDocFileVanBan = new javax.swing.JButton();
        btnGhiFileVanBan = new javax.swing.JButton();
        btnGhiFileNP = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Nhập chuỗi");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Nhập tên tập tin:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Nội dung File");

        txtNhapChuoi.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        txtNoiDung.setEditable(false);
        txtNoiDung.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        txtTenTapTin.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        btnDocFileNP.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnDocFileNP.setText("Đọc File Nhị Phân");
        btnDocFileNP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocFileNPActionPerformed(evt);
            }
        });

        btnDocFileVanBan.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnDocFileVanBan.setText("Đọc File Văn Bản");
        btnDocFileVanBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocFileVanBanActionPerformed(evt);
            }
        });

        btnGhiFileVanBan.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnGhiFileVanBan.setText("Ghi File Văn Bản");
        btnGhiFileVanBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGhiFileVanBanActionPerformed(evt);
            }
        });

        btnGhiFileNP.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnGhiFileNP.setText("Ghi File Nhị Phân");
        btnGhiFileNP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGhiFileNPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTenTapTin, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNhapChuoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNoiDung, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(68, 68, 68))
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDocFileVanBan)
                    .addComponent(btnDocFileNP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGhiFileNP)
                    .addComponent(btnGhiFileVanBan))
                .addGap(98, 98, 98))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(61, 61, 61)
                    .addComponent(jLabel2)
                    .addContainerGap(584, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(txtTenTapTin, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNhapChuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDocFileNP)
                    .addComponent(btnGhiFileNP))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDocFileVanBan)
                    .addComponent(btnGhiFileVanBan))
                .addContainerGap(70, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(74, 74, 74)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(395, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGhiFileNPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGhiFileNPActionPerformed
        // TODO add your handling code here:
        byte a[] = new byte[20];
        File  file;
        int i;
        char s[] = txtNhapChuoi.getText().toCharArray();
        for( i = 0 ; i < s.length; i++){
            a[i] = (byte)s[i];
        }
        
        try{
            file = new File(txtTenTapTin.getText());
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(a);
            fo.close();
        } catch (IOException e ) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_btnGhiFileNPActionPerformed

    private void btnDocFileNPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocFileNPActionPerformed
        // TODO add your handling code here:
        byte a[];
        File file;
        try {
            file = new File (txtTenTapTin.getText());
            FileInputStream fi = new FileInputStream(file);
            a = new byte[fi.available()];
            fi.read(a);
            fi.close();
            
            txtNoiDung.setText(new String(a));
        } catch (Exception e) {}
    }//GEN-LAST:event_btnDocFileNPActionPerformed

    private void btnGhiFileVanBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGhiFileVanBanActionPerformed
        // TODO add your handling code here:
        try {
            FileWriter fw = new FileWriter(new File(txtTenTapTin.getText()));
            fw.write(txtNhapChuoi.getText());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnGhiFileVanBanActionPerformed

    private void btnDocFileVanBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocFileVanBanActionPerformed
        // TODO add your handling code here:
        try {
            FileReader fr = new  FileReader(new File(txtTenTapTin.getText()));
            StringBuffer sb = new StringBuffer();
            char ca[] = new char[5];
            while(fr.ready()){
                int len = fr.read(ca);
                sb.append(ca,0,len);
            }
            fr.close();
            txtNoiDung.setText(sb+"");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDocFileVanBanActionPerformed

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
            java.util.logging.Logger.getLogger(Bai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bai2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDocFileNP;
    private javax.swing.JButton btnDocFileVanBan;
    private javax.swing.JButton btnGhiFileNP;
    private javax.swing.JButton btnGhiFileVanBan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtNhapChuoi;
    private javax.swing.JTextField txtNoiDung;
    private javax.swing.JTextField txtTenTapTin;
    // End of variables declaration//GEN-END:variables
}