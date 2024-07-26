import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class DictionaryApplication extends Dictionary {
    private static String input = "";
    public static final Font BIG_FONT = new Font("Montserrat", Font.BOLD, 25);
    public static final Font MED_FONT = new Font("Montserrat", Font.BOLD, 20);
    private static MyPanel myPanel = new MyPanel(200, 10, 300, 200, Color.WHITE, null, true);
    private static MyLabel english = new MyLabel(0, 0, 300, 50, null, BIG_FONT, Color.BLUE, JLabel.LEFT, JLabel.TOP);
    private static MyLabel meaning = new MyLabel(0, 75, 300, 200, null, MED_FONT, Color.BLACK, JLabel.LEFT, JLabel.TOP);
    private static MyButton word1 = new MyButton(10, 60, 150, 50, null, null, null, false, false);
    private static MyButton word2 = new MyButton(10, 110, 150, 50, null, null, null, false, false);
    private static MyButton word3 = new MyButton(10, 160, 150, 50, null, null, null, false, false);
    private static MyButton word4 = new MyButton(10, 210, 150, 50, null, null, null, false, false);
    private static MyButton word5 = new MyButton(10, 260, 150, 50, null, null, null, false, false);
    private static List<MyButton> buttonList = new ArrayList<>();

    private static List<String> appSearcher(String input) {
        List<String> found = new ArrayList<>();
        if (input != null) {
            for (String key : map.keySet()) {
                if (key.startsWith(input)) {
                    found.add(key);
                }
            }
        }
        Collections.sort(found);
        return found;
    }

    public static void runApplication() {
        buttonList.add(word1);
        buttonList.add(word2);
        buttonList.add(word3);
        buttonList.add(word4);
        buttonList.add(word5);

        myPanel.add(english);
        myPanel.add(meaning);
        MyFrame myFrame = new MyFrame();
        JTextField textField = new JTextField();
        textField.setFont(MED_FONT);
        textField.setBounds(10, 10, 150, 50);
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                for (int i = buttonList.size() - 1; i >= 0; i--) {
                    buttonList.get(i).setVisible(false);
                    buttonList.get(i).setEnabled(false);
                    buttonList.get(i).setText(null);
                    ActionListener[] needRemove = buttonList.get(i).getActionListeners();
                    for (ActionListener l : needRemove) {
                        buttonList.get(i).removeActionListener(l);
                    }
                }
                if (e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z') {
                    input = input + e.getKeyChar();
                } else {
                    input = textField.getText();
                }
                if (!input.equalsIgnoreCase("")) {
                    List<String> found = appSearcher(input);
                    System.out.println(found);
                    for (int i = 0; i < found.size(); i++) {
                        if (i >= buttonList.size()) {
                            break;
                        }
                        String temp1 = found.get(i);
                        System.out.println(temp1);
                        String temp2 = map.get(temp1).getMeaning();
                        buttonList.get(i).setEnabled(true);
                        buttonList.get(i).setVisible(true);
                        buttonList.get(i).setText(temp1);
                        buttonList.get(i).addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                myPanel.setVisible(true);
                                english.setVisible(true);
                                english.setText(temp1);
                                meaning.setVisible(true);
                                meaning.setText(temp2);
                                textField.setText(temp1);
                                for (int i = buttonList.size() - 1; i >= 0; i--) {
                                    buttonList.get(i).setVisible(false);
                                    buttonList.get(i).setEnabled(false);
                                    buttonList.get(i).setText(null);
                                }
                            }
                        });
                    }
                    for (int i = buttonList.size() - 1; i >= found.size(); i--) {
                        buttonList.get(i).setVisible(false);
                        buttonList.get(i).setEnabled(false);
                        buttonList.get(i).setText(null);
                        buttonList.get(i).removeAll();
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
        
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        myFrame.add(word1);
        myFrame.add(word2);
        myFrame.add(word3);
        myFrame.add(word4);
        myFrame.add(word5);
        myFrame.add(myPanel);
        myFrame.add(textField);
        myFrame.setVisible(true);
    }
}
