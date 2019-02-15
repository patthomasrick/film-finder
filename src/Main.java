import Search.AllSearch;
import Search.SearchResult;

import java.util.List;
import java.util.Scanner;

/**
 * Peter Sepulveda and Patrick Thomas
 */

public class Main {
    public static void main(String[] args) {

        System.out.println("AllSearch example.");

        Scanner reader = new Scanner(System.in); // reading from command line
        AllSearch searcher = new AllSearch(); // create searcher
        List<SearchResult> resultsList; // list that results will be stored in

        while (true) {
            System.out.print("\n\t>\t");
            String s = reader.nextLine();
            if (s.equals("quit"))
                break; // quit if search is "quit"
            else
                resultsList = searcher.search(s); // get results and store them
        }

        // once quit has been typed
        reader.close();
    }
}
