package Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is a search hook intended to interface with RottenTomatoes' website at time of writing. Given a query, it is
 * searched with RottenTomatoes' web search tool. Each query returned is processed in parallel to find its links to
 * streaming platforms.
 *
 * @author Patrick Thomas
 * @see Hook
 * @see SearchResult
 */
class RottenTomatoes extends Hook {
    private static final String searchUrl = "https://www.rottentomatoes.com/search/?search=";
    private static final String movieUrl = "https://www.rottentomatoes.com";
    private static final String subStrStart = "\"movies\":";
    private static final String subStrEnd = ",\"tvCount\":";

    /**
     * Default constructor. Same as Hook(String query).
     *
     * @see Hook
     *
     * @param query String to search for.
     */
    RottenTomatoes(String query) {
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
//                        System.out.println(jsonConcat.substring(startIndex, endIndex+1));
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

                outList.add(new SearchResult(name, year, url));
            }
        }

        // now parse individual sites, looking for site links
        List<RottenTomatoesSpecificSearch> callables = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (SearchResult result : outList) {
            callables.add(new RottenTomatoesSpecificSearch(result));
        }

        // try to invoke all search threads at once
        try {
            service.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // return found list, may be empty
        return outList;
    }

    private class RottenTomatoesSpecificSearch implements Callable<SearchResult> {
        private final static String linkSectionStart = "<div class=\"movie_links\">";
        private final static String linkSectionEnd = "function trackAffiliateEvent(affiliate) {";

        SearchResult result;

        RottenTomatoesSpecificSearch(SearchResult result) {
            this.result = result; // intentionally use reference
        }

        @Override
        public SearchResult call() {
            // get the source of the website
            String source = Hook.retrieveWebsite(this.result.getQueryUrl());
            BufferedReader reader = new BufferedReader(new StringReader(source));
            String line;
            boolean linkSectionStartFound = false;
            List<String> foundLinks = new ArrayList<>();
            try {
                while (true) {
                    line = reader.readLine();

                    // loop and input control
                    if (!linkSectionStartFound && line.contains(linkSectionStart)) {
                        linkSectionStartFound = true;
                    } else if (linkSectionStartFound && line.contains(linkSectionEnd)) {
                        break;
                    }

                    // do some string parsing
                    if (linkSectionStartFound) {
                        // grab all links
                        if (line.contains("http")) {
                            String[] split = line.split("\"");
//                            System.out.println(split[split.length - 2]);
                            foundLinks.add(split[split.length - 2]);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (String link : foundLinks) {
                for (String platform : AllSearch.allPlatforms) {
                    if (link.contains(platform)) {
                        result.availabilitySet.add(platform);
                        result.siteLinksMap.put(platform, link);
                    }
                }
            }

            return result;
        }
    }
}
