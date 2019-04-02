package Search;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * SearchResult provides a concise way to return a bundle of data from an associated search.
 * <p>
 *
 * @author Patrick Thomas
 * <p>
 * TODO Make method or constructor to combine two search results if they are similar enough.
 */
public class SearchResult {
    /// Sites that a movie is found available on.
    TreeSet<String> availabilitySet;
    /// Mapping of available site to link to respective site.
    HashMap<String, String> siteLinksMap;
    /// The movie's title, such as "Shrek II"
//    private String title;
//    private int year;
    private Map<MovieAttributes, String> movieAttributes = new HashMap();
    /// The URL returned from the search. This should go to the movie's web page on a site.
    private String queryUrl;

    /**
     *
     * @param title
     * @param year
     * @param queryUrl
     */
    SearchResult(String title, int year, String queryUrl) {
        this.movieAttributes.put(MovieAttributes.TITLE, title);
        this.movieAttributes.put(MovieAttributes.YEAR, Integer.toString(year));
        this.queryUrl = queryUrl;
        this.availabilitySet = new TreeSet<>();
        this.siteLinksMap = new HashMap<>();
    }

    /**
     * Returns the movie title
     *
     * @return String
     */
    public String getMovieTitle() {
        return (String) this.movieAttributes.get(MovieAttributes.TITLE);
    }

    public int getYear() {
        return Integer.parseInt(this.movieAttributes.get(MovieAttributes.YEAR));
    }

    /**
     * Returns where the data was originally scraped from
     *
     * @return String
     */
    public String getQueryUrl() {
        return this.queryUrl;
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

//    public void addAvailable(String platform) {
//        this.availabilitySet.add(platform);
//    }

    public String getSiteLink(String platform) {
        return this.siteLinksMap.get(platform);
    }

//    public void addSiteLink(String platform, String url) {
//        this.siteLinksMap.put(platform, url);
//    }

    /**
     * Returns the search results in a nice, readable string format.
     *
     * @return "movie_title, url"
     */
    @Override
    public String toString() {
        return String.format("%s (%d), %s", this.getMovieTitle(), this.getYear(), this.getQueryUrl());
    }

    public enum MovieAttributes {
        TITLE, YEAR, DIRECTOR, GENRE, LENGTH
    }
}
