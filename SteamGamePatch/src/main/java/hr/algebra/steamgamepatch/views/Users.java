/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.algebra.steamgamepatch.views;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.User;
import hr.algebra.utilities.MessageUtils;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author fran
 */
public class Users extends javax.swing.JPanel {

    private final Repository repo;
    private User selectedUser;

    public Users() {
        repo = RepositoryFactory.getInstance();
        initComponents();
        GetUsers();
    }

    private void GetUsers() {
        try {
            var users = repo.getUsers();
            DefaultListModel m = new DefaultListModel();
            m.addAll(users);
            lsUsers.setModel(m);
        } catch (Exception e) {
            System.out.println(e);
            MessageUtils.showErrorMessage("ERROR", "Failed to get users");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lsUsers = new javax.swing.JList<>();
        tfUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        Admin = new javax.swing.JCheckBox();
        btnCreate = new javax.swing.JButton();
        tfPassword = new javax.swing.JPasswordField();

        jLabel2.setText("Selected User");

        lsUsers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lsUsers.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lsUsersValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lsUsers);

        jLabel3.setText("Username:");

        jLabel5.setText("Password:");

        jLabel6.setText("Admin:");

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        Admin.setText("Is Admin");

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfUsername)
                            .addComponent(Admin)
                            .addComponent(tfPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btnCreate)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)))
                .addContainerGap(225, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(Admin))))
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEdit)
                            .addComponent(btnDelete)
                            .addComponent(btnCreate))
                        .addGap(0, 99, Short.MAX_VALUE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lsUsersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lsUsersValueChanged
        if (!evt.getValueIsAdjusting()) {
            var user = lsUsers.getSelectedValue();
            if (user != null) {
                SetUser(user);
            }
        }
    }//GEN-LAST:event_lsUsersValueChanged

    private void SetUser(User user) {
        selectedUser = user;
        tfUsername.setText(selectedUser.username);
        tfPassword.setText(selectedUser.password);
        Admin.setSelected(selectedUser.isAdmin);
    }

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        try {
            if (FormInvalid()) {
                MessageUtils.showErrorMessage("Error", "Can't create a user that exists");
                return;
            }
            var newUser = new User(
                    selectedUser.IdUser,
                    tfUsername.getText(),
                    tfPassword.getText(),
                    Admin.getSelectedObjects()[0] != null);
            repo.updateUser(selectedUser.IdUser, newUser);
        } catch (Exception e) {
            System.out.println(e);
            MessageUtils.showErrorMessage("ERROR", String.format("Failed to update user %d", selectedUser.username));
        } finally {
            GetUsers();
            ClearSelected();
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (selectedUser == null) {
            MessageUtils.showErrorMessage("Error", "Can't create a user that exists");
            return;
        }
        try {
            repo.deleteUser(selectedUser.IdUser);
        } catch (Exception e) {
            System.out.println(e);
            MessageUtils.showErrorMessage("ERROR", String.format("Failed to update user %d", selectedUser.username));

        } finally {
            GetUsers();
            ClearSelected();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if (selectedUser != null) {
            MessageUtils.showErrorMessage("Error", "Can't create a user that exists");
            return;
        }
        try {
            if (FormInvalid()) {
                MessageUtils.showErrorMessage("Error", "Can't create a user that exists");
                return;
            }
            var exists = repo.getUsers().stream()
                    .anyMatch(u -> u.username.equals(tfUsername.getText()));
            if (exists) {
                MessageUtils.showErrorMessage("Error", "Can't create a user that exists");
                return;
            }

            var newUser = new User(
                    tfUsername.getText(),
                    tfPassword.getText(),
                    Admin.getSelectedObjects()[0] != null);
            repo.createUser(newUser);
        } catch (Exception e) {
            System.out.println(e);
            MessageUtils.showErrorMessage("ERROR", String.format("Failed to Create user"));
        } finally {
            GetUsers();
            ClearSelected();
        }
    }//GEN-LAST:event_btnCreateActionPerformed
    private void ClearSelected() {
        tfPassword.setText("");
        tfUsername.setText("");
        Admin.setSelected(false);
        selectedUser = null;

    }

    private boolean FormInvalid() {
        var pass = tfPassword.getText();
        var usr = tfUsername.getText();
        //TODO implement
        MessageUtils.showErrorMessage("Not Implemented", "Validation is not implemented always returns true");
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Admin;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<User> lsUsers;
    private javax.swing.JPasswordField tfPassword;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables

}
