import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DictionaryManagement extends Dictionary {
    private static boolean change = false;

    /**
     * Insert words from the command line to the dictionary.
     */
    public static void insertWord(String english, String meaning) {
        Word newWord = new Word(english, meaning);
        map.put(english, newWord);
        change = true;
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
     * Adjust a meaning of a word in the dictionary.
     */
    public static void adjustWord(String english, String meaning) {
        Word newWord = new Word(english, meaning);
        map.put(english, newWord);
        change = true;
    }

    /**
     * Remove a word from the dictionary.
     */
    public static void removeWord(String english) {
        map.remove(english.toLowerCase());
        change = true;
    }

    public static boolean somethingChanged() {
        return change;
    }

    public static void exportToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("D:\\Study\\java\\EnglishDictionary\\src\\dictionaries.txt"));
            for (String key : map.keySet()) {
                bw.write(key);
                bw.write(";");
                bw.write(map.get(key).getMeaning());
                bw.write("\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}