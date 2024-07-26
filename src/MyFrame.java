import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame extends JFrame {
    public int i = 0;

    public MyFrame() {
        super("English Dictionary");
        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        ImageIcon image = new ImageIcon("D:\\Study\\java\\EnglishDictionary\\src\\image\\icon.jpg");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
    }
}
