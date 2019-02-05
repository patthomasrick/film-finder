package Search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class OmniSearch {

    public static void main(String[] args) {

        System.out.println("Film Finder Search Test\nPatrick Thomas\nType a query and press enter to search.");

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        OmniSearch searcher = new OmniSearch();

        while (true) {
            System.out.print("\n\t>\t");
            String s = reader.nextLine();
            s = s.replace(" ", "%20").trim();
            if (s.equals("quit")) break;
            else searcher.search(s);
        }

        // once finished
        reader.close();
    }

    public List<SearchResult> search(String query) {
        List<Hook> hookList = new ArrayList<>();
        List<SearchResult> allResults = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // add search hooks
        hookList.add(new RottenTomatoes(query));

        // try to invoke all search threads at once
        try {
            List<Future<List<SearchResult>>> futures = service.invokeAll(hookList);
            for (Future<List<SearchResult>> futureList : futures) {
                List<SearchResult> srList = futureList.get();
                allResults.addAll(srList);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        service.shutdown();

        for (SearchResult searchResult : allResults) {
            System.out.println(searchResult.toString());
        }

        return allResults;
    }
}
