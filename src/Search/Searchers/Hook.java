package Search.Searchers;

import javax.naming.directory.SearchResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public abstract class Hook {
    public static String retrieveWebsite(String strUrl) {
        URL url;
        StringBuilder out = new StringBuilder();

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

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return out.toString();
    }

    public ArrayList<SearchResult> search(String query) {
        return null;
    }

}
