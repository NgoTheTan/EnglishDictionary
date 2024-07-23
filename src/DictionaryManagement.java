import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    /**
     * Insert words from the command line to the dictionary.
     */
    public static void insertFromCommandline() {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        for (int i = 0; i < n; i++) {
            String english = input.nextLine();
            String meaning = input.nextLine();
            Word newWord = new Word(english, meaning);
            map.put(english, newWord);
        }
        input.close();
    }

    /**
     * Read words from a file.
     * 
     * @throws IOException Exception.
     */
    public static void insertFromFile() throws IOException {
        File dictionaries = new File("D:\\Study\\java\\EnglishDictionary\\src\\dictionaries.txt");
        Scanner scanner = new Scanner(dictionaries);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] temp = line.split(";");
            String english = temp[0];
            String meaning = temp[1];
            Word newWord = new Word(english, meaning);
            map.put(english, newWord);
        }
        scanner.close();
    }

    /**
     * Read all the words in the dictionary.
     */
    public static void readAllWords() {
        System.out.format("%-5s %-2s %-15s %-2s %-20s\n", "No", "|", "English", "|", "Vietnamese");
        for (String key : map.keySet()) {
            int i = 1;
            System.out.format("%-5s %-2s %-15s %-2s %-20s\n", String.valueOf(i), "|", key, "|",
                    map.get(key).getMeaning());
            i++;
        }
    }
}
