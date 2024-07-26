import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static void commandSearcher() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            boolean found = false;
            System.out.print("Word to search: ");
            String temp = br.readLine();
            int i = 0;
            String res = "";
            for (String key : map.keySet()) {
                if (key.startsWith(temp)) {
                    found = true;
                    i++;
                    res = res + String.format("%-5s %-2s %-15s %-2s %-20s\n", i, "|", key,
                            "|", map.get(key).getMeaning());
                }
            }
            if (found) {
                System.out.println("Found:");
                System.out.format("%-5s %-2s %-15s %-2s %-20s\n", "No", "|", "English", "|",
                        "Vietnamese");
                System.out.print(res);
            } else {
                System.out.println("Can't find the word in the dictionary!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Do command.
     */
    public static void dictionaryCommand() {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello to my English - Vietnamese dictionary!");
        while (true) {
            System.out.println("List of command:");
            System.out.println("[1] Search a word");
            System.out.println("[2] Show all words");
            System.out.println("[3] Add a number of words");
            System.out.println("[4] Remove a word");
            System.out.println("[5] Adjust a word");
            System.out.println("[0] Exit");
            System.out.print("Your command: ");
            int i = input.nextInt();
            if (i == 0) {
                System.out.println("See you again!");
                input.close();
                break;
            } else if (i == 1) {
                commandSearcher();
            } else if (i == 3) {
                insertFromCommandline();
            } else if (i == 2) {
                showAllWords();
            } else if (i == 5) {
                adjustWord();
            } else if (i == 4) {
                removeWord();
            } else {
                System.out.println("That command is not appropriate!");
            }
            System.out.println("Anything else you want to do?");
        }
    }
}