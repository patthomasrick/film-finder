package Search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RottenTomatoes extends Hook {
    private static final String searchUrl = "https://www.rottentomatoes.com/search/?search=";
    private static final String movieUrl = "https://www.rottentomatoes.com";
    private static final String subStrStart = "\"movies\":";
    private static final String subStrEnd = ",\"tvCount\":";

    /**
     * Simply use Hook's default constructor.
     *
     * @param query String to search for.
     */
    public RottenTomatoes(String query) {
        super(query);
    }

    @Override
    public List<SearchResult> search(String query) {
        // format input string slightly so it plays nice with HTML queries
        query = query.replace(" ", "%20").trim();

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
            int numOpeningBrackets = 0, startIndex = 0, endIndex;
            for (int i = 0; i < jsonConcat.length(); i++) {
                char c = jsonConcat.charAt(i);
                if (c == '{') {
                    if (numOpeningBrackets == 0) startIndex = i;
                    numOpeningBrackets++;
                } else if (c == '}') {
                    numOpeningBrackets--;
                    if (numOpeningBrackets == 0) {
                        endIndex = i;
                        jsonArray.add(jsonConcat.substring(startIndex, endIndex + 1));
                        // System.out.println(jsonConcat.substring(startIndex, endIndex+1));
                    }
                }
            }

            // parse json
            Scanner scanner;
            // System.out.println("FOUND RESULTS:");
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

                outList.add(new SearchResult(
                        String.format("%s (%d)", name, year),
                        url,
                        null,
                        null));
            }
        }

        // return found list, may be empty
        return outList;
    }
}
