import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    protected static List<String> learning = new ArrayList<>();
    private static boolean changed = false;
    private static boolean learned = false;

    /**
     * Read words from a file.
     * 
     * @throws IOException Exception.
     */
    public static void insertFromFile() throws IOException {
        File dictionaries = new File("D:\\Study\\java\\EnglishDictionary\\src\\data\\dictionaries.txt");
        Scanner scanner = new Scanner(dictionaries);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] temp = line.split(";");
            String english = temp[0];
            List<String> meaning = new ArrayList<>();
            List<String> type = new ArrayList<>();
            if (temp[2].contains("/")) {
                String[] typeAndMeaning = temp[2].split("/");
                for (int i = 0; i < typeAndMeaning.length; i++) {
                    String[] temp2 = typeAndMeaning[i].split(":");
                    type.add(temp2[0]);
                    meaning.add(temp2[1]);
                }
            } else {
                String[] typeAndMeaning = temp[2].split(":");
                type.add(typeAndMeaning[0]);
                meaning.add(typeAndMeaning[1]);
            }
            String pronunciation = temp[1];
            Word newWord = new Word(english, meaning, type, pronunciation);
            map.put(english, newWord);
        }
        scanner.close();
    }

    /**
     * Load learning words from file.
     * 
     * @throws IOException
     */
    public static void loadLearning() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\Study\\java\\EnglishDictionary\\src\\data\\learning.txt"));
        String line = "";
        while ((line = br.readLine()) != null) {
            learning.add(line);
        }
        br.close();
    }

    public static boolean learnedSomething() {
        return learned;
    }

    /**
     * Insert words from the command line to the dictionary.
     */
    public static void insertWord(String english, List<String> meaning, List<String> type, String pronuciation) {
        Word newWord = new Word(english, meaning, type, pronuciation);
        map.put(english, newWord);
        changed = true;
    }

    /**
     * Adjust a meaning of a word in the dictionary.
     */
    public static void adjustWord(String english, List<String> meaning, List<String> type, String pronunciation) {
        Word newWord = new Word(english, meaning, type, pronunciation);
        map.put(english, newWord);
        changed = true;
    }

    /**
     * Remove a word from the dictionary.
     */
    public static void removeWord(String english) {
        map.remove(english.toLowerCase());
        changed = true;
    }

    /**
     * Add a word to learning list.
     * @param word
     */
    public static void addToLearning(String word) {
        learning.add(0, word);
        learned = true;
    }

    /**
     * Remove a word from learning list.
     * @param word
     */
    public static void doneLearning(String word) {
        learning.remove(word);
        learned = true;
    }

    /**
     * Export current data to file.
     */
    public static void exportToFile() throws IOException{
        if (changed) {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("D:\\Study\\java\\EnglishDictionary\\src\\dictionaries.txt"));
            for (String key : map.keySet()) {
                bw.write(key);
                bw.write(";");
                bw.write(map.get(key).getPronuciation());
                    bw.write(";");
                int temp = map.get(key).getType().size();
                for (int i = 0; i < temp - 1; i++) {
                    bw.write(map.get(key).getType().get(i));
                    bw.write(":");
                    bw.write(map.get(key).getMeaning().get(i));
                    bw.write("/");
                }
                bw.write(map.get(key).getType().get(temp - 1));
                bw.write(":");
                bw.write(map.get(key).getMeaning().get(temp - 1));
                bw.write("\n");
            }
            bw.close();
        }
        if (learned && learning.size() > 0) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\Study\\java\\EnglishDictionary\\src\\data\\learning.txt"));
            for (int i= 0; i < learning.size() - 1; i++) {
                bw.write(learning.get(i));
                bw.write("\n");
            }
            bw.write(learning.get(learning.size() - 1));
            bw.close();
        }
    }
}