/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.Patch;
import hr.algebra.model.Author;
import hr.algebra.model.Game;
import hr.algebra.model.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author fran
 */
public interface Repository {
    //Games crud
    int createGame(Game game) throws Exception;
    void updateGame(int id, Game game) throws Exception;
    void deleteGame(int id) throws Exception;
    Optional<Game> getGame(int id) throws Exception;
    List<Game> getGames() throws Exception;
    
    //Author crud
    int createAuthor(Author author) throws Exception;
    void updateAuthor(int id, Author author) throws Exception;
    void deleteAuthor(int id) throws Exception;
    Optional<Author> getAuthor(int id) throws Exception;
    List<Author> getAuthors() throws Exception;
    
    //User crud
    int createUser(User user) throws Exception;
    void updateUser(int id, User user) throws Exception;
    void deleteUser(int id) throws Exception;
    Optional<User> getUser(int id) throws Exception;
    List<User> getUsers() throws Exception;
    
    //Patch crud
    int createPatch(Patch patch) throws Exception;
    int createPatches(List<Patch> patchs) throws Exception;
    void updatePatch(int id, Patch patch) throws Exception;
    void deletePatch(int id) throws Exception;
    Optional<Patch> getPatch(int id) throws Exception;
    List<Patch> getPatches() throws Exception;
    
}
