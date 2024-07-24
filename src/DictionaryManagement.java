import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DictionaryManagement extends Dictionary {
    /**
     * Insert words from the command line to the dictionary.
     */
    public static void insertFromCommandline() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            boolean added = false;
            System.out.print("Number of words: ");
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                System.out.print("English: ");
                String english = br.readLine();
                if (map.containsKey(english.toLowerCase())) {
                    System.out.println("Already available. You can try to adjust the word.");
                    continue;
                }
                System.out.print("Vietnamese: ");
                String meaning = br.readLine();
                Word newWord = new Word(english, meaning);
                map.put(english, newWord);
                System.out.println("Added!");
                added = true;
                numberOfWords++;
            }
            if (added) {
                BufferedWriter bw = new BufferedWriter(
                        new FileWriter("D:\\Study\\java\\EnglishDictionary\\src\\dictionaries.txt"));
                for (String key : map.keySet()) {
                    bw.write(key);
                    bw.write(";");
                    bw.write(map.get(key).getMeaning());
                    bw.write("\n");
                }
                bw.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

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
            numberOfWords++;
        }
        scanner.close();
    }

    /**
     * Adjust a meaning of a word in the dictionary.
     */
    public static void adjustWord() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            boolean adjusted = false;
            System.out.print("The word you want to adjust: ");
            String english = br.readLine();
            for (String key : map.keySet()) {
                if (key.equalsIgnoreCase(english)) {
                    System.out.println("Current meaning: " + map.get(key).getMeaning());
                    System.out.print("New meaning: ");
                    String meaning = br.readLine();
                    if (meaning.equalsIgnoreCase(map.get(key).getMeaning())) {
                        System.out.println("That's the same meaning.");
                        return;
                    }
                    Word newWord = new Word(english, meaning);
                    map.put(english, newWord);
                    adjusted = true;
                }
            }
            if (adjusted) {
                BufferedWriter bw = new BufferedWriter(
                        new FileWriter("D:\\Study\\java\\EnglishDictionary\\src\\dictionaries.txt"));
                for (String key : map.keySet()) {
                    bw.write(key);
                    bw.write(";");
                    bw.write(map.get(key).getMeaning());
                    bw.write("\n");
                }
                bw.close();
            } else {
                System.out.println("That word is not available in the dictionary. Try add it first.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Remove a word from the dictionary.
     */
    public static void removeWord() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            boolean removed = false;
            System.out.print("The word you want to remove: ");
            String english = br.readLine();
            for (String key : map.keySet()) {
                if (key.equalsIgnoreCase(english)) {
                    map.remove(english.toLowerCase());
                    removed = true;
                    numberOfWords--;
                    break;
                }
            }
            if (removed) {
                System.out.println("Removed!");
                BufferedWriter bw = new BufferedWriter(
                        new FileWriter("D:\\Study\\java\\EnglishDictionary\\src\\dictionaries.txt"));
                for (String key : map.keySet()) {
                    bw.write(key);
                    bw.write(";");
                    bw.write(map.get(key).getMeaning());
                    bw.write("\n");
                }
                bw.close();
            } else {
                System.out.println("That word is not available in the dictionary in the first place.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}