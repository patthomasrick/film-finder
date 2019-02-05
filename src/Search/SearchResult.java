package Search;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * @author Patrick Thomas
 *
 * SearchResult provides a consise way to return a bundle of data from an associated search.
 */
public class SearchResult {
    /**
     * The movie's title, such as "Shrek II"
     */
    private String query;

    /**
     * The URL returned from the search. This should go to the movie's webpage on a site.
     */
    private String queryUrl;

    /**
     * Sites that a movie is found available on.
     */
    private TreeSet<String> availability = null;

    /**
     * Mapping of available site to link to respective site.
     */
    private HashMap<String, String> siteLinks = null;

    /**
     * Constructor.
     *
     * @param query The movie's title
     * @param queryUrl The movie's URL on a streaming platform
     * @param availability The movie's availability on a streaming platform
     * @param siteLinks Mapping of platform name to link
     */
    public SearchResult(String query, String queryUrl, TreeSet<String> availability,
                        HashMap<String, String> siteLinks) {
        this.query = query;
        this.queryUrl = queryUrl;
        if (availability != null) this.availability = new TreeSet<>(availability);
        if (siteLinks != null) this.siteLinks = new HashMap<>(siteLinks);
    }

    /**
     * Returns the movie title
     * @return String
     */
    public String getQuery() {
        return query;
    }

    /**
     * Returns where the data was originally scraped from
     * @return String
     */
    public String getQueryUrl() {
        return queryUrl;
    }

    public boolean isAvailable(String platform) {
        return this.availability.contains(platform);
    }

    public String getSiteLink(String platform) {
        return this.siteLinks.get(platform);
    }
}
