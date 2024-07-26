import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            DictionaryManagement.insertFromFile();
            String[] responses = { "Commandline", "Application" };
            ImageIcon image = new ImageIcon("D:\\Study\\java\\EnglishDictionary\\src\\image\\hello.jpg");
            int answer = JOptionPane.showOptionDialog(null, "This is my English - Vietnamese dictionary.\n"
                    + "There are two version, which one do you want to use?", "Hello!", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, image, responses, 0);
            if (answer == JOptionPane.YES_OPTION) {
                DictionaryCommandline.dictionaryCommand();
            } else if (answer == JOptionPane.NO_OPTION) {
                DictionaryApplication.runApplication();
            } else {
                return;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
