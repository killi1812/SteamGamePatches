/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.sql.Date;

/**
 *
 * @author fran
 */
public class Patch {

    public Patch() {
    }

    public Patch(int idPatch, String title, String description, String link, Date pubDate, int authorId, int gameId) {
        this.idPatch = idPatch;
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
        this.authorId = authorId;
        this.gameId = gameId;
    }
        public Patch(String title, String description, String link, Date pubDate, int authorId, int gameId) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
        this.authorId = authorId;
        this.gameId = gameId;
    }

    public int idPatch;
    public String title;
    public String description;
    public String link;
    //TODO check if good date is used there is one in sql models
    public Date pubDate;
    public int authorId;
    public int gameId;
}
