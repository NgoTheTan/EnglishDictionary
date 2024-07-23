import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

public class DictionaryCommandline extends DictionaryManagement {
    /**
     * Read all the words in the dictionary.
     */
    public static void showAllWords() {
        System.out.format("%-5s %-2s %-15s %-2s %-20s\n", "No", "|", "English", "|", "Vietnamese");
        Set<String> keySet = map.keySet();
        String[] keyArray = keySet.toArray(new String[0]);
        Arrays.sort(keyArray);
        for (int i = 1; i <= numberOfWords; i++) {
            System.out.printf("%-5s %-2s %-15s %-2s %-20s\n", i, "|", keyArray[i - 1], "|",
                    map.get(keyArray[i - 1]).getMeaning());
        }
    }

    public static void dictionaryBasic() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Hello to my English - Vietnamese dictionary:\n 1. Add\n 2. Show all words\n 0. Exit\n");
            int i = input.nextInt();
            if (i == 0) {
                System.out.println("See you again!");
                input.close();
                break;
            } else if (i == 1) {
                insertFromCommandline();
            } else if (i == 2) {
                showAllWords();
            }
        }
    }
}