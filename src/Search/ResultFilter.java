package Search;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to contain a lot of static methods to filter any List of SearchResults by some quality.
 */
public class ResultFilter {
    /**
     * Filter a List of SearchResults, returning a List that contains movies only released in the
     * specified year. Year must be positive.
     * @param year Year to filter in.
     * @param list Original list of movies to filter.
     * @return List of movies, where all movies in the List were released in the year `year`.
     */
    public static List<SearchResult> byYear(int year, List<SearchResult> list) {
        if (year <= 0) return null;
        else {
            List<SearchResult> out = new ArrayList<>();
            for (SearchResult result : list) {
                if (result.getYear() == year) out.add(result);
            }
            return out;
        }
    }

    /**
     * Return a list of movies that only were released between two years. If the upper < lower, null
     * is returned.
     * @param lower The lower bound of years of movies returned.
     * @param upper The upper bound of years of movies returned.
     * @param list List to filter.
     * @return List of SearchResults containing only movies between lower and upper.
     */
    public static List<SearchResult> byYear(int lower, int upper, List<SearchResult> list) {
        if (upper < lower) return null;
        else {
            List<SearchResult> out = new ArrayList<>();
            for (SearchResult result : list) {
                if (lower <= result.getYear() && result.getYear() <= upper) out.add(result);
            }
            return out;
        }
    }

    public static List<SearchResult> byGenre(String genre) {
        return null;
    }
}
