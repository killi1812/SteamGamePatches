/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.algebra.steamgamepatch.views;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.User;
import hr.algebra.utilities.MessageUtils;

/**
 *
 * @author fran
 */
public class Login extends javax.swing.JPanel {

    public static User loggedInUser = null;
    public final Repository repo;

    public static boolean IsLoggedIn() {
        return loggedInUser != null;
    }

    public static boolean IsAdmin() {
        if (loggedInUser == null) {
            return Boolean.FALSE;
        }
        return loggedInUser.isAdmin;
    }

    /**
     * Creates new form Login
     */
    //TODO mby singleton
    public Login() {
        repo = RepositoryFactory.getInstance();
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

        tfUsername = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        tfPass = new javax.swing.JPasswordField();

        tfUsername.setText("username");
        tfUsername.setToolTipText("Write your username");

        btnLogin.setText("Login");
        btnLogin.setToolTipText("Login into your account");
        btnLogin.setAlignmentX(0.5F);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        tfPass.setToolTipText("Write your password");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(133, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(tfPass))
                        .addGap(127, 127, 127))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(158, 158, 158))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(tfPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogin)
                .addGap(82, 82, 82))
        );

        getAccessibleContext().setAccessibleName("LoginVew");
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:

        var username = tfUsername.getText();
        //TODO ne storaj u string jel ce biti cashiran
        var password = new String(tfPass.getPassword());
        User user = null;
        try {
            user = repo.getUsers().stream()
                    .filter(u -> u.username.equals(username))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            MessageUtils.showErrorMessage("ERROR", "Failed to get users");
            return;
        }
        if (user == null) {
            MessageUtils.showErrorMessage(
                    "User doesn't exist",
                    String.format("User: %d doesn't exist", username)
            );
            return;
        }

        if (user.password == password) {
            loggedInUser = user;
            return;
        }
        MessageUtils.showErrorMessage(
                "Wrong password",
                String.format("Wrong password for user: %d", username));


    }//GEN-LAST:event_btnLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JPasswordField tfPass;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables
}
