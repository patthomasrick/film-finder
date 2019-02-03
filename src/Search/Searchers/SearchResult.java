package Search.Searchers;

import java.util.HashMap;
import java.util.TreeSet;

public class SearchResult {

    private String query;
    private String queryUrl;
    private TreeSet<String> availability;
    private HashMap<String, String> siteLinks;

    public SearchResult(String query, String queryUrl, TreeSet<String> availability,
                        HashMap<String, String> siteLinks) {
        this.query = query;
        this.queryUrl = queryUrl;
        this.availability = new TreeSet<>(availability);
        this.siteLinks = new HashMap<>(siteLinks);
    }

    public String getQuery() {
        return query;
    }

    public String getQueryUrl() {
        return queryUrl;
    }
//
//    public TreeSet<String> getAvailability() {
//        return availability;
//    }

    public boolean isAvailable(String platform) {
        return this.availability.contains(platform);
    }

    public String getSiteLink(String platform) {
        return this.siteLinks.get(platform);
    }
//
//    public HashMap<String, String> getSiteLinks() {
//        return siteLinks;
//    }
}
