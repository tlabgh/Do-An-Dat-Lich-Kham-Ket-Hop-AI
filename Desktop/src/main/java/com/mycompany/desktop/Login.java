/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.desktop;

import com.mycompany.desktop.*;
import com.mycompany.desktop.API.APIClient;
import com.mycompany.desktop.API.AuthService;
import com.mycompany.desktop.models.TokenResponse;
import com.mycompany.desktop.utils.SessionManager;
import com.mycompany.desktop.utils.ValidationUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @author admin
 */
public class Login extends javax.swing.JFrame {

    private AuthService authService;
    private SessionManager sessionManager;
    /**
     * Creates new form RegisterDoctor
     */
    public Login() {
        initComponents();
        jLabel_thongBao.setVisible(true);
        authService = APIClient.getAuthService();
        sessionManager = new SessionManager();
        jLabel_thongBao.setText("");
        
        jTextField_login_gmail.requestFocus();

        //check if user is already logged in
        if (sessionManager.isLoggedIn()) {
            //creat void login
            return;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel_thongBao = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton_login = new javax.swing.JButton();
        jTextField_login_gmail = new javax.swing.JTextField();
        jRadioButton_bacSi = new javax.swing.JRadioButton();
        jRadioButton_admin = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField_login_password = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setAlwaysOnTop(true);
        getContentPane().setLayout(new java.awt.FlowLayout());

        jLabel_thongBao.setText("Thoong");
        jLabel_thongBao.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Bệnh Viện Đa Khoa T");

        jButton_login.setText("Đăng nhập");
        jButton_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_loginActionPerformed(evt);
            }
        });
        jButton_login.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_loginKeyPressed(evt);
            }
        });

        buttonGroup1.add(jRadioButton_bacSi);
        jRadioButton_bacSi.setText("Bác sĩ");
        jRadioButton_bacSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_bacSiActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton_admin);
        jRadioButton_admin.setText("Admin");
        jRadioButton_admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_adminActionPerformed(evt);
            }
        });

        jLabel2.setText("Tài khoản:");

        jLabel3.setText("Mật khẩu:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_thongBao)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(jButton_login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jRadioButton_bacSi)
                            .addGap(18, 18, 18)
                            .addComponent(jRadioButton_admin))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPasswordField_login_password, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_login_gmail, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_login_gmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPasswordField_login_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_bacSi)
                    .addComponent(jRadioButton_admin))
                .addGap(14, 14, 14)
                .addComponent(jLabel_thongBao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_login, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton_bacSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_bacSiActionPerformed

    }//GEN-LAST:event_jRadioButton_bacSiActionPerformed

    private void jRadioButton_adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_adminActionPerformed

    }//GEN-LAST:event_jRadioButton_adminActionPerformed

    private void jButton_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_loginActionPerformed
        String email = jTextField_login_gmail.getText().trim();
      
        char[] passChars = jPasswordField_login_password.getPassword();
        String password = new String(passChars);


        // Validate inputs
        if (!ValidationUtils.isValidEmail(email)) {
            jLabel_thongBao.setText("Please enter a valid email");
            jTextField_login_gmail.requestFocus();
            return;
        }

        if (!ValidationUtils.isValidPassword(password)) {
            jLabel_thongBao.setText("Password must be at least 8 characters");
            jPasswordField_login_password.requestFocus();
            return;
        }

        if (jRadioButton_admin.isSelected()) {
            authService.login(email, password, "Admin").enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        TokenResponse tokenResponse = response.body();
                        SessionManager.getInstance().saveToken(tokenResponse.getFullToken());
                        jLabel_thongBao.setText("Login successful!");
                        // chuyen sang trang admin
                        new AdminHome().setVisible(true);
                        Login.this.dispose();
                    } else {
                        jLabel_thongBao.setText("Login failed. Please check your credentials.");
                        if (response.code() == 401) {
                            jLabel_thongBao.setText("Invalid email or password");
                        }
                    }
                }

                @Override
                public void onFailure(Call<TokenResponse> call, Throwable thrwbl) {
                    jLabel_thongBao.setText("Network error: " + thrwbl.getMessage());
                }
            });

        } else if (jRadioButton_bacSi.isSelected()) {
            authService.login(email, password, "Doctor").enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        TokenResponse tokenResponse = response.body();
                        SessionManager.getInstance().saveToken(tokenResponse.getFullToken());
                        jLabel_thongBao.setText("Login successful!");
                        // chuyen sang trang admin
                        new DoctorHome().setVisible(true);
                        Login.this.dispose();
                    } else {
                        jLabel_thongBao.setText("Login failed. Please check your credentials.");
                        if (response.code() == 401) {
                            jLabel_thongBao.setText("Invalid email or password");
                        }
                    }
                }

                @Override
                public void onFailure(Call<TokenResponse> call, Throwable thrwbl) {
                    jLabel_thongBao.setText("Network error: " + thrwbl.getMessage());
                }
            });
        }

    }//GEN-LAST:event_jButton_loginActionPerformed

    private void jButton_loginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_loginKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_loginKeyPressed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton_login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_thongBao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField_login_password;
    private javax.swing.JRadioButton jRadioButton_admin;
    private javax.swing.JRadioButton jRadioButton_bacSi;
    private javax.swing.JTextField jTextField_login_gmail;
    // End of variables declaration//GEN-END:variables
}
