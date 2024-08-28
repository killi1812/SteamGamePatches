/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author fran
 */
public class Author {

    public int idAuthor;
    public String name;

    @Override
    public String toString() {
        return "Author{" + "name=" + name + '}';
    }

    public Author(int id, String name) {

    }
}
