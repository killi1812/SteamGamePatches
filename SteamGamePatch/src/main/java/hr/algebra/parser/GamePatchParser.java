/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.parser;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.model.Author;
import hr.algebra.model.Game;
import hr.algebra.model.Patch;
import hr.algebra.utilities.FileUtils;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author fran
 */
public class GamePatchParser {

    private static final String DIV = "div";
    private static final String COMMA = ",";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets";

    private static Repository repo;

    private static void handlePicture(Game game, String data) throws IOException {
        String ext = data.substring(data.lastIndexOf("."));
        if (ext.length() > 4) {
            ext = EXT;
        }

        String imageName = UUID.randomUUID() + ext;
        String localPath = DIR + File.separator + imageName;
        //FileUtils.copyFromURL(data, localPath);
        game.pictureURL = localPath;
    }

    private GamePatchParser() {
    }

    public static void parse(int idGame) throws IOException, XMLSignatureException, Exception {
        Game game = null;
        List<Patch> patches = new ArrayList<>();

        repo = RepositoryFactory.getInstance();
        //TODO make a check if the game id is right
        var con = UrlConnectionFactory.getHttpUrlConnection(idGame);

        try (var is = con.getInputStream();) {
            var reader = ParserFactory.createStaxParser(is);

            Patch patch = null;
            StartElement startElement = null;
            Optional<TagType> tagType = Optional.empty();

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT -> {
                        startElement = event.asStartElement();
                        String xName = startElement.getName().getLocalPart();
                        tagType = TagType.from(xName);
                        if (tagType.isPresent() && tagType.get().equals(TagType.ITEM)) {
                            patch = new Patch();
                            patch.gameId = idGame;
                            patches.add(patch);
                        }
                        if (tagType.isPresent() && tagType.get().equals(TagType.IMAGE)) {
                            game = new Game();
                            game.idSteamGame = idGame;
                        }
                    }
                    //TODO see for parsing a game
                    case XMLStreamConstants.CHARACTERS -> {
                        if (!tagType.isPresent()) {
                            break;
                        }
                        if (patch != null) {
                            String data = event.asCharacters().getData().trim();
                            switch (tagType.get()) {
                                case TITLE:
                                    if (data.isEmpty()) {
                                        break;
                                    }
                                    patch.title = data;
                                    break;
                                case DESCRIPTION:
                                    if (data.isEmpty()) {
                                        break;
                                    }
                                    //TODO check what is up with desc hard time parsing html
                                    patch.description = data;
                                    break;
                                case LINK:
                                    if (data.isEmpty()) {
                                        break;
                                    }
                                    patch.link = data;
                                    break;
                                case PUBDATE:
                                    if (data.isEmpty()) {
                                        break;
                                    }
                                    //TODO pars date :Thu, 08 Aug 2024 12:04:49 +0000
                                    SimpleDateFormat parser = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
                                    var date = new java.sql.Date(parser.parse(data).getTime());
                                    patch.pubDate = date;
                                    break;
                                case AUTHOR:
                                    if (data.isEmpty()) {
                                        break;
                                    }

                                    Author author = repo.getAuthors().stream()
                                            .filter(a -> a.name == data)
                                            .findFirst()
                                            .orElse(null);

                                    if (author == null) {
                                        author = new Author(data);
                                        author.idAuthor = repo.createAuthor(author);
                                    }
                                    patch.authorId = author.idAuthor;
                                    break;
                                default:
                                    break;
                            }
                        } else if (game != null) {
                            String data = event.asCharacters().getData().trim();
                            switch (tagType.get()) {
                                case URL:
                                    if (data.isEmpty()) {
                                        break;
                                    }
                                    //TODO handle download picture and save
                                    game.pictureURL = data;
                                    break;
                                case TITLE:
                                    if (data.isEmpty()) {
                                        break;
                                    }
                                    game.name = data;

                                    break;
                                case LINK:
                                    if (data.isEmpty()) {
                                        break;
                                    }
                                    game.steamURL = data;
                                    break;

                                default:
                                    break;
                            }
                        }
                    }
                }
            }
        }

        repo.createGame(game);
        repo.createPatches(patches);
    }

    private enum TagType {
        ITEM("item"),
        TITLE("title"),
        LINK("link"),
        IMAGE("image"),
        URL("url"),
        DESCRIPTION("description"),
        PUBDATE("pubDate"),
        AUTHOR("author");

        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            for (var value : values()) {
                if ((value.name.equals(name))) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }

}
