/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.util.Date;

/**
 *
 * @author fran
 */
public class Patch {

    public int idPatch;
    public String title;
    public String description;
    public String link;
    //TODO check if good date is used there is one in sql models
    public Date pubDate;
    public Author author;
    public int gameId;
}
