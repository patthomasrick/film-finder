package Search;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * @author Patrick Thomas
 * <p>
 * SearchResult provides a consise way to return a bundle of data from an associated search.
 * <p>
 * TODO Make method or constructor to combine two search results if they are similar enough.
 */
public class SearchResult {
    /**
     * The movie's title, such as "Shrek II"
     */
    private String movieTitle;

    /**
     * The URL returned from the search. This should go to the movie's webpage on a site.
     */
    private String queryUrl;

    /**
     * Sites that a movie is found available on.
     */
    private TreeSet<String> availabilitySet = null;

    /**
     * Mapping of available site to link to respective site.
     */
    private HashMap<String, String> siteLinksMap = null;

    /**
     * Constructor.
     *
     * @param movieTitle      The movie's title
     * @param queryUrl        The movie's URL on a streaming platform
     * @param availabilitySet The movie's availabilitySet on a streaming platform
     * @param siteLinksMap    Mapping of platform name to link
     */
    public SearchResult(String movieTitle, String queryUrl, TreeSet<String> availabilitySet,
                        HashMap<String, String> siteLinksMap) {
        this.movieTitle = movieTitle;
        this.queryUrl = queryUrl;
        if (availabilitySet != null) this.availabilitySet = new TreeSet<>(availabilitySet);
        if (siteLinksMap != null) this.siteLinksMap = new HashMap<>(siteLinksMap);
    }

    /**
     * Returns the movie title
     *
     * @return String
     */
    public String getMovieTitle() {
        return movieTitle;
    }

    /**
     * Returns where the data was originally scraped from
     *
     * @return String
     */
    public String getQueryUrl() {
        return queryUrl;
    }

    /**
     * Given the name of a platform, return whether the movie is available on that platform or not.
     *
     * @param platform String, name of platform, such as "netflix".
     * @return boolean, true for movie is available, false otherwise.
     */
    public boolean isAvailable(String platform) {
        return this.availabilitySet.contains(platform);
    }

    public String getSiteLink(String platform) {
        return this.siteLinksMap.get(platform);
    }

    /**
     * Returns the search results in a nice, readable string format.
     *
     * @return "movie_title, url"
     */
    @Override
    public String toString() {
        return String.format("%s, %s", this.getMovieTitle(), this.getQueryUrl());
    }
}
