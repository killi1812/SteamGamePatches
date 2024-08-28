/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author fran
 */
public class Game {
    //TODO valjda netrebaju getteri i setteri

    public Game(int id, String name, String steamURL, String pictureURl) {
        idSteamGame = id;
        this.name = name;
        this.steamURL = steamURL;
        this.pictureURL = pictureURl;
    }

    public int idSteamGame;
    public String name;
    public String steamURL;
    public String pictureURL;

    @Override
    public String toString() {
        return "Game{" + "idSteamGame=" + idSteamGame + ", name=" + name + ", steamURL=" + steamURL + ", pictureURL=" + pictureURL + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.idSteamGame;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        return this.idSteamGame == other.idSteamGame;
    }

}
