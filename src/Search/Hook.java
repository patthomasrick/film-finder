package Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Hooks are the overarching style of how data will be scraped from web pages. They are abstract and not meant to be
 * used as-is.
 *
 * @author Patrick Thomas
 * @see SearchResult
 */
abstract class Hook implements Callable<List<SearchResult>> {
    /**
     * String to search for when called.
     */
    private String query;

    /**
     * Default constructor. Takes a query.
     *
     * @param query String to search the implemented web page for.
     */
    Hook(String query) {
        this.query = query;
    }

    /**
     * Given an URL, download the website's source.
     *
     * @param strUrl the website to download's URL as a string
     * @return string of entire webpage's source
     */
    static String retrieveWebsite(String strUrl) {
        URL url;
        StringBuilder out;

        out = new StringBuilder();

        try {
            // get URL content
            url = new URL(strUrl);
            URLConnection connection = url.openConnection();

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                out.append(inputLine);
                out.append("\n");
            }

            br.close();
        } catch (IOException e) {
            // in the event of the url not being valid
            e.printStackTrace();
        }

        return out.toString();
    }

    /**
     * This is the hook's main way of giving data back to who requested the search.
     *
     * @param query string to enter into a webpage's search, such as "shrek" or "star wars"
     * @return ArrayList of all search results scraped from the webpage
     */
    public List<SearchResult> search(String query) {
        return null;
    }


    /**
     * Implementation of Callable. Simply uses Hook.search().
     *
     * @return List of SearchResults.
     */
    public List<SearchResult> call() {
        return search(query);
    }

}
