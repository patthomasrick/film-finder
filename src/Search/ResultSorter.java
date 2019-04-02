package Search;

import java.util.Comparator;

public class ResultSorter implements Comparator<SearchResult> {

    /// The current mode that the Sorter is sorting by. Possible values are in the enum. SortMode.
    private SortMode mode;

    /**
     * Default constructor for ResultSorter. SortMode mode is the mode that the Sorter starts in.
     *
     */
    public ResultSorter(SortMode mode) {
        this.setMode(mode);
    }

    /**
     * Sets the current sorting mode.
     * @param mode Some value of the enumerator SortMode.
     */
    public void setMode(SortMode mode) {
        this.mode = mode;
    }

    /**
     * Compare two SearchResults according to the current mode. The current mode can be set with
     * setMode(mode).
     * @param sr1 First SearchResult
     * @param sr2 Second SearchResult
     * @return Integer, follows convention.
     */
    @Override
    public int compare(SearchResult sr1, SearchResult sr2) {
        switch (this.mode) {
            case TITLE:
                return sr1.getMovieTitle().compareTo(sr2.getMovieTitle());
            case DATE:
                return Integer.compare(sr1.getYear(), sr2.getYear());
//            case GENRE:
//                return 0;
//            case LENGTH:
//                return 0;
            default:
                return 0;
        }
    }

    /**
     * Enumerator for the possible modes that movies can be sorted by.
     */
    public enum SortMode {
        TITLE, DATE, GENRE, LENGTH;
    }
}
