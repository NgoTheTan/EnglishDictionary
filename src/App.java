import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            DictionaryManagement.insertFromFile();
            DictionaryApplication.runApplication();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
