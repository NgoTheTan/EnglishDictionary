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
    public static void insertWord(String english, List<String> meaning, List<String> type, String pronuciation) {
        Word newWord = new Word(english, meaning, type, pronuciation);
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
                String[] typeAndMeaning =  temp[2].split(":");
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
     * Adjust a meaning of a word in the dictionary.
     */
    public static void adjustWord(String english, List<String> meaning, List<String> type, String pronunciation) {
        Word newWord = new Word(english, meaning, type, pronunciation);
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
                bw.write(map.get(key).getPronuciation());
                bw.write(";");
                int temp = map.get(key).getType().size();
                for (int i = 0; i < temp - 1; i++) {
                    bw.write(map.get(key).getType().get(i));
                    bw.write(":");
                    bw.write(map.get(key).getMeaning().get(i));
                    bw.write("/");
                }
                bw.write(map.get(key).getType().get(temp-1));
                bw.write(":");
                bw.write(map.get(key).getMeaning().get(temp-1));
                bw.write("\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}