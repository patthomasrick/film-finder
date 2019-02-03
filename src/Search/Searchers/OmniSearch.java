package Search.Searchers;

import java.util.Scanner;

public class OmniSearch {
    public static void main(String args[]) {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        RottenTomatoes rt = new RottenTomatoes();

        while (true) {
            String s = reader.next(); // Scans the next token of the input as an int.
            if (s.equals("quit")) break;
            else rt.search(s);
        }
        //once finished
        reader.close();
    }
}
