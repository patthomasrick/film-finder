package Search;

import Search.Searchers.RottenTomatoes;

import java.util.Scanner;

public class OmniSearch {
    public static void main(String[] args) {

        System.out.println("Film Finder Search Test\nPatrick Thomas\nType a query and press enter to search.");

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        RottenTomatoes rt = new RottenTomatoes();

        while (true) {
            System.out.printf("\n\t>\t");
            String s = reader.nextLine();
            s = s.replace(" ", "%20").trim();
            if (s.equals("quit")) break;
            else rt.search(s);
        }
        //once finished
        reader.close();
    }
}
