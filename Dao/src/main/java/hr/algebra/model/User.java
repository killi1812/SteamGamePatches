/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author fran
 */
public class User {

    public int IdUser;
    public String username;
    public String password;
    public boolean isAdmin;

    @Override
    public String toString() {
        return isAdmin ? username + " (Admin)" : username;
    }

    public User(int IdUser, String username, String password, boolean isAdmin) {
        this.IdUser = IdUser;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

}
