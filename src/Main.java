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

        System.out.println("Currently available platforms:");
        for (String s : AllSearch.allPlatforms) {
            System.out.printf("\t%s\n", s);
        }

        Scanner reader = new Scanner(System.in); // reading from command line
        List<SearchResult> resultsList; // list that results will be stored in

        while (true) {
            System.out.print("\nquery\t>\t");
            String s = reader.nextLine();
            if (s.equals("quit"))
                break; // quit if search is "quit"
            else {
                resultsList = AllSearch.search(s); // get results and store them
                for (SearchResult sr : resultsList) {
                    System.out.printf("%s\n", sr.getMovieTitle());
                    for (String platform : AllSearch.allPlatforms) {
                        System.out.printf("\t%-12s\t%s\n", platform, sr.getSiteLink(platform));
                    }
                }
            }
        }

        // once quit has been typed
        reader.close();
    }
}
