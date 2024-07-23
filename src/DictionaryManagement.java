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
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\Study\\java\\EnglishDictionary\\src\\dictionaries.txt"));
            System.out.print("Number of words: ");
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                System.out.print("English: ");
                String english = br.readLine();
                System.out.print("Vietnamese: ");
                String meaning = br.readLine();
                Word newWord = new Word(english, meaning);
                map.put(english, newWord);
                numberOfWords++;
            }
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
}
