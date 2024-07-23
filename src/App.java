import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            DictionaryManagement.insertFromFile();
            DictionaryCommandline.dictionaryBasic();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
