/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.factory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author fran
 */
public class UrlConnectionFactory {

    private static final int TIMEOUT = 10000;
    private static final String REQUEST_METHOD = "GET";
    private static final String USER_AGENT = "User-Agent";
    private static final String MOZILLA = "Mozilla/5.0";
    private static final String PATH = "https://steamcommunity.com/games/%s/rss/";

    private UrlConnectionFactory() {
    }

    public static HttpURLConnection getHttpUrlConnection(int IdGame) throws MalformedURLException, IOException {
        URL url = new URL(String.format(PATH, IdGame));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(TIMEOUT);
        con.setReadTimeout(TIMEOUT);
        con.setRequestMethod(REQUEST_METHOD);
        con.addRequestProperty(USER_AGENT, MOZILLA);
        return con;
    }
}
