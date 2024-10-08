/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Author;
import hr.algebra.model.Game;
import hr.algebra.model.Patch;
import hr.algebra.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class SqlRepository implements Repository {
    //Game 
    private static final String ID_STEAM_GAME = "idSteamGame";
    private static final String NAME = "name";
    private static final String STEAM_URL = "steamURL";
    private static final String PICTURE_URL = "pictureURL";

    private static final String CREATE_GAME = "{ CALL CreateGame (?,?,?,?) }";
    private static final String UPDATE_GAME = "{ CALL UpdateGame (?,?,?,?) }";
    private static final String DELETE_GAME = "{ CALL DeleteGame (?) }";
    private static final String GET_GAME = "{ CALL ReadGame (?) }";
    private static final String GET_GAMES = "{ CALL ReadGames }";

    @Override
    public int createGame(Game game) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_GAME)) {

            stmt.setInt(ID_STEAM_GAME, game.idSteamGame);
            stmt.setString(NAME, game.name);
            stmt.setString(PICTURE_URL, game.pictureURL);
            stmt.setString(STEAM_URL, game.steamURL);

//TODO check what is out parametar look at bele script
//            stmt.registerOutParameter(ID_STEAM_GAME, Types.INTEGER);
            stmt.executeUpdate();
            return game.idSteamGame;
        }
    }

    @Override
    public void updateGame(int id, Game game) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_GAME)) {

            stmt.setInt(ID_STEAM_GAME, id);
            stmt.setString(NAME, game.name);
            stmt.setString(PICTURE_URL, game.pictureURL);
            stmt.setString(STEAM_URL, game.steamURL);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteGame(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_GAME)) {
            stmt.setInt(ID_STEAM_GAME, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Game> getGame(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_GAME)) {
            stmt.setInt(ID_STEAM_GAME, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Game(
                            rs.getInt(ID_STEAM_GAME),
                            rs.getString(NAME),
                            rs.getString(STEAM_URL),
                            rs.getString(PICTURE_URL)
                    ));
                }
            }

        }
        return Optional.empty();
    }

    @Override
    public List<Game> getGames() throws Exception {
        List<Game> games = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_GAMES)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    games.add(new Game(
                            rs.getInt(ID_STEAM_GAME),
                            rs.getString(NAME),
                            rs.getString(STEAM_URL),
                            rs.getString(PICTURE_URL)
                    ));
                }
            }
        }
        return games;
    }

    //Author
    private static final String ID_AUTHOR = "idAuthor";
//    private static final String NAME = "name";

    private static final String CREATE_AUTHOR = "{ CALL CreateAuthor (?,?) }";
    private static final String UPDATE_AUTHOR = "{ CALL UpdateAuthor (?,?) }";
    private static final String DELETE_AUTHOR = "{ CALL DeleteAuthor (?) }";
    private static final String GET_AUTHOR = "{ CALL ReadAuthor (?) }";
    private static final String GET_AUTHORS = "{ CALL ReadAuthors }";

    @Override
    public int createAuthor(Author author) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_AUTHOR)) {

            stmt.setString(NAME, author.name);
            stmt.registerOutParameter(ID_AUTHOR, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_AUTHOR);
        }
    }

    @Override
    public void updateAuthor(int id, Author author) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_AUTHOR)) {
            stmt.setInt(ID_AUTHOR, id);
            stmt.setString(NAME, author.name);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteAuthor(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_AUTHOR)) {
            stmt.setInt(ID_AUTHOR, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Author> getAuthor(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_AUTHOR)) {
            stmt.setInt(ID_AUTHOR, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Author(id, rs.getString(NAME)));

                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void createAuthors(List<Author> authors) throws Exception {
        for (var author : authors) {
            DataSource dataSource = DataSourceSingleton.getInstance();
            try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_AUTHOR)) {

                stmt.setString(NAME, author.name);
                stmt.registerOutParameter(ID_AUTHOR, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public List<Author> getAuthors() throws Exception {
        List<Author> authors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_AUTHORS)) {
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    authors.add(new Author(rs.getInt(ID_AUTHOR), rs.getString(NAME)));

                }
            }
        }
        return authors;
    }

    //User 
    private static final String ID_USER = "idUser";
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "password";
    private static final String IS_ADMIN = "isAdmin";

    private static final String CREATE_USER = "{ CALL CreateUser (?,?,?,?) }";
    private static final String UPDATE_USER = "{ CALL UpdateUser (?,?,?,?) }";
    private static final String DELETE_USER = "{ CALL DeleteUser (?) }";
    private static final String GET_USER = "{ CALL ReadUser (?) }";
    private static final String GET_USERS = "{ CALL ReadUsers }";

    @Override
    public int createUser(User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_USER)) {

            stmt.setString(USERNAME, user.username);
            stmt.setString(PASSWORD, user.password);
            stmt.setBoolean(IS_ADMIN, user.isAdmin);

            stmt.registerOutParameter(ID_USER, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_USER);
        }
    }

    @Override
    public void updateUser(int id, User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_USER)) {

            stmt.setInt(ID_USER, id);
            stmt.setString(USERNAME, user.username);
            stmt.setString(PASSWORD, user.password);
            stmt.setBoolean(IS_ADMIN, user.isAdmin);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteUser(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_USER)) {

            stmt.setInt(ID_USER, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<User> getUser(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_USER)) {

            stmt.setInt(ID_USER, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new User(
                            rs.getInt(ID_USER),
                            rs.getString(USERNAME),
                            rs.getString(PASSWORD),
                            rs.getBoolean(IS_ADMIN)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> getUsers() throws Exception {
        List<User> users = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_USERS)) {

            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(new User(
                            rs.getInt(ID_USER),
                            rs.getString(USERNAME),
                            rs.getString(PASSWORD),
                            rs.getBoolean(IS_ADMIN)));
                }
            }
        }
        return users;
    }

    //Patch 
    private static final String ID_PATCH = "idPatch";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String LINK = "link";
    private static final String PUB_DATE = "pubDate";
    private static final String AUTHOR_ID = "authorId";
    private static final String GAME_ID = "gameId";

    private static final String CREATE_PATCH = "{ CALL CreatePatch (?,?,?,?,?,?,?) }";
    private static final String UPDATE_PATCH = "{ CALL UpdatePatch (?,?,?,?,?,?,?) }";
    private static final String DELETE_PATCH = "{ CALL DeletePatch (?) }";
    private static final String GET_PATCH = "{ CALL ReadPatch (?) }";
    private static final String GET_PATCHES = "{ CALL ReadPatches }";

    @Override

    public int createPatch(Patch patch) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_PATCH)) {

            stmt.setString(TITLE, patch.title);
            stmt.setString(DESCRIPTION, patch.description);
            stmt.setString(LINK, patch.link);
            stmt.setDate(PUB_DATE, patch.pubDate);
            stmt.setInt(AUTHOR_ID, patch.authorId);
            stmt.setInt(GAME_ID, patch.gameId);
            stmt.registerOutParameter(ID_PATCH, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_PATCH);
        }
    }

    @Override
    public void updatePatch(int id, Patch patch) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_PATCH)) {

            stmt.setInt(ID_PATCH, id);
            stmt.setString(TITLE, patch.title);
            stmt.setString(DESCRIPTION, patch.description);
            stmt.setString(LINK, patch.link);
            stmt.setDate(PUB_DATE, patch.pubDate);
            stmt.setInt(AUTHOR_ID, patch.authorId);
            stmt.setInt(GAME_ID, patch.gameId);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deletePatch(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_PATCH)) {
            stmt.setInt(ID_PATCH, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Patch> getPatch(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_PATCH)) {
            stmt.setInt(ID_PATCH, id);

            try (var rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(new Patch(
                            rs.getInt(ID_PATCH),
                            rs.getString(TITLE),
                            rs.getString(DESCRIPTION),
                            rs.getString(LINK),
                            rs.getDate(PUB_DATE),
                            rs.getInt(AUTHOR_ID),
                            rs.getInt(GAME_ID)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Patch> getPatches() throws Exception {
        List<Patch> patches = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_PATCHES)) {
            try (var rs = stmt.executeQuery();) {
                while (rs.next()) {
                    patches.add(new Patch(
                            rs.getInt(ID_PATCH),
                            rs.getString(TITLE),
                            rs.getString(DESCRIPTION),
                            rs.getString(LINK),
                            rs.getDate(PUB_DATE),
                            rs.getInt(AUTHOR_ID),
                            rs.getInt(GAME_ID)
                    ));
                }
            }
        }
        return patches;
    }

    @Override
    public void createPatches(List<Patch> patches) throws Exception {

        for (var patch : patches) {
            DataSource dataSource = DataSourceSingleton.getInstance();
            try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_PATCH)) {

                stmt.setString(TITLE, patch.title);
                stmt.setString(DESCRIPTION, patch.description);
                stmt.setString(LINK, patch.link);
                stmt.setDate(PUB_DATE, patch.pubDate);
                stmt.setInt(AUTHOR_ID, patch.authorId);
                stmt.setInt(GAME_ID, patch.gameId);
                stmt.registerOutParameter(ID_PATCH, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

}
