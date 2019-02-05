package Search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RottenTomatoes extends Hook {
    private static String searchUrl = "https://www.rottentomatoes.com/search/?search=";
    private static String movieUrl = "https://www.rottentomatoes.com";
    private static String subStrStart = "\"movies\":";
    private static String subStrEnd = ",\"tvCount\":";

    /**
     * Variable to store the search query so this can be threaded.
     */
    private String query;

    /**
     * Default constructor. Takes the query.
     *
     * @param query String to search webpage for.
     */
    public RottenTomatoes(String query) {
        this.query = query;
    }

    @Override
    public List<SearchResult> search(String query) {
        // output search results
        List<SearchResult> outList = new ArrayList<>();

        // get the source of the website
        String source = Hook.retrieveWebsite(String.format("%s%s", searchUrl, query));

        // process if it even contains results
        if (source.contains(subStrStart) && source.contains(subStrEnd)) {
            int subStrStartIndex = source.indexOf(subStrStart) + subStrStart.length() + 1;
            int subStrEndIndex = source.indexOf(subStrEnd) - 1;
            String jsonConcat = source.substring(subStrStartIndex, subStrEndIndex);
            // System.out.println(jsonConcat);
            ArrayList<String> jsonArray = new ArrayList<>();

            // split that string by the JSON parts
            int numOpeningBrackets = 0, startIndex = 0, endIndex = 0;
            for (int i = 0; i < jsonConcat.length(); i++) {
                char c = jsonConcat.charAt(i);
                if (c == '{') {
                    if (numOpeningBrackets == 0) startIndex = i;
                    numOpeningBrackets++;
                } else if (c == '}') {
                    numOpeningBrackets--;
                    if (numOpeningBrackets == 0) {
                        endIndex = i;
                        jsonArray.add(jsonConcat.substring(startIndex, endIndex+1));
                        // System.out.println(jsonConcat.substring(startIndex, endIndex+1));
                    }
                }
            }

            // parse json
            ArrayList<SearchResult> out;
            Scanner scanner;
            int start, end;
            System.out.println("FOUND RESULTS:");
            for (String s : jsonArray) {
                scanner = new Scanner(s);
                String name, url;
                int year;

                scanner.skip("\\{\"name\":\"");
                name = scanner.findInLine("[^\"]+");
                scanner.skip("\",\"year\":");
                year = Integer.parseInt(scanner.findInLine("[^,]+"));
                scanner.skip(",\"url\":\"");
                url = movieUrl + scanner.findInLine("[^\"]+");
                // System.out.printf("%s, %d, %s\n", name, year, url);

                outList.add(new SearchResult(name, url, null, null));
            }
        }
        // return found list, may be empty
        return outList;
    }

    @Override
    public List<SearchResult> call() {
        return this.search(query);
    }
}