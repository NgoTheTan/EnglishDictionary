import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
    public MyFrame() {
        super("English Dictionary");
        this.setSize(550, 400);
        this.setResizable(false);
        ImageIcon image = new ImageIcon("D:\\Study\\java\\EnglishDictionary\\src\\image\\icon.jpg");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
    }
}
