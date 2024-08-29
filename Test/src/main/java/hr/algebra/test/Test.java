/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package hr.algebra.test;

import hr.algebra.dal.RepositoryFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.model.Game;
import hr.algebra.parser.GamePatchParser;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 *
 * @author fran
 */
public class Test {
    
    public static void main(String[] args) {
        
        var repo = RepositoryFactory.getInstance();
//
//        try {
//            repo.deleteGame(914710);
//        } catch (Exception e) {
//            System.out.println(e);
//        }

//        try {
//            Game g = new Game(914710, "Cat Quest II", "https://store.steampowered.com/app/914710/Cat_Quest_II/", "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/914710/capsule_231x87.jpg?t=1708441428");
//            repo.createGame(g);
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        try {
//            var g = repo.getGame(1286580);
//            if (!g.isEmpty()) {
//                System.out.println(g.get().name);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        try {
            List<Game> games = repo.getGames();
            games.forEach(g -> System.out.println(g.name));
        } catch (Exception e) {
            System.out.println(e);
        }

//        try {
//            var conn = UrlConnectionFactory.getHttpUrlConnection(914710);
//            var rez = new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
//            System.out.println(rez);
//        } catch (Exception e) {
//            System.out.println(e);
//
//        }
        try {
            GamePatchParser.parse(394360);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
