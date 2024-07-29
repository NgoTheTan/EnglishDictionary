import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DictionaryApplication extends DictionaryManagement {
    public static final Set<Character> NOT_ALPHABET = Set.of('`', '~', '1', '!', '2', '@', '3', '#', '4', '$', '5', '%',
            '6', '^', '7', '&', '8', '*', '9', '(', '0', ')', '-', '_', '=', '+', '{', '[', ']', '}', '|', ':', ';',
            '"', ',', '<', '.', '>', '/', '?', ' ');
    public static final Font BIG_FONT = new Font("Montserrat", Font.BOLD, 25);
    public static final Font MED_FONT = new Font("Montserrat", Font.BOLD, 15);

    private static String input = "";

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
        MyFrame myFrame = new MyFrame();

        MyLayerdPane rightPane = new MyLayerdPane(210, 0, 300, 400, Color.WHITE, true);
        // Searched word panel.
        MyLabel english = new MyLabel(10, 10, 300, 50, null, BIG_FONT, Color.BLUE, JLabel.LEFT, JLabel.TOP);
        MyLabel meaning = new MyLabel(10, 50, 300, 100, null, MED_FONT, Color.BLACK, JLabel.LEFT, JLabel.TOP);
        MyLabel header = new MyLabel(0, 0, 300, 40, new Color(0x003399));
        MyPanel wordPanel = new MyPanel(0, 40, 300, 400, Color.WHITE, null, false, true);
        rightPane.add(header, Integer.valueOf(0));
        wordPanel.add(english);
        wordPanel.add(meaning);
        rightPane.add(wordPanel);
        // Add word panel.
        MyPanel addPanel = new MyPanel(0, 40, 300, 400, Color.WHITE, null, false, true);
        MyTextField addEnglish = new MyTextField(10, 20, 200, 45, MED_FONT, JTextField.LEFT_ALIGNMENT,
                JTextField.RIGHT_ALIGNMENT, BorderFactory.createTitledBorder("Từ tiếng anh"));
        MyTextField addMeaning = new MyTextField(10, 70, 200, 45, MED_FONT, JTextField.LEFT_ALIGNMENT,
                JTextField.RIGHT_ALIGNMENT, BorderFactory.createTitledBorder("Nghĩa tiếng Việt"));
        MyButton confirmAdd = new MyButton(10, 120, 150, 35, "Thêm vào từ điển", MED_FONT, false, false,
                JButton.CENTER, JButton.CENTER, Color.WHITE, BorderFactory.createMatteBorder(3, 5, 3, 2, new Color(0x4169E1)));
        confirmAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == confirmAdd) {
                    if (addEnglish.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(myFrame, "Hãy điền từ tiếng anh!",
                                "Thao tác không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
                    } else if (addMeaning.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(myFrame, "Hãy điền nghĩa tiếng Việt!",
                                "Thao tác không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
                    } else if (map.keySet().contains(addEnglish.getText().toLowerCase())) {
                        JOptionPane.showMessageDialog(myFrame, "Từ " + addEnglish.getText() + " đã có trong từ điển.",
                                "Thao tác không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
                        addEnglish.setText(null);
                        addMeaning.setText(null);
                    } else {
                        JOptionPane.showMessageDialog(myFrame, "Từ mới " + addEnglish.getText() + " đã được thêm vào",
                                "Thao tác thành công", JOptionPane.INFORMATION_MESSAGE);
                        insertWord(addEnglish.getText().toLowerCase(), addMeaning.getText().toLowerCase());
                        addEnglish.setText(null);
                        addMeaning.setText(null);
                    }

                }
            }
        });
        addPanel.add(confirmAdd);
        addPanel.add(addEnglish);
        addPanel.add(addMeaning);
        addPanel.setPanelEnabledAndVisible(false);
        rightPane.add(addPanel);
        // Adjust word panel.
        MyPanel adjustPanel = new MyPanel(0, 40, 300, 400, Color.WHITE, null, false, true);
        MyTextField adjustEnglish = new MyTextField(10, 20, 200, 45, MED_FONT, JTextField.LEFT_ALIGNMENT,
                JTextField.RIGHT_ALIGNMENT, BorderFactory.createTitledBorder("Từ tiếng anh"));
        MyTextField adjustMeaning = new MyTextField(10, 70, 200, 45, MED_FONT, JTextField.LEFT_ALIGNMENT,
                JTextField.RIGHT_ALIGNMENT, BorderFactory.createTitledBorder("Nghĩa tiếng Việt mới"));
        MyButton confirmAdjust = new MyButton(10, 120, 150, 35, "Sửa từ", MED_FONT, false, false,
                JButton.CENTER, JButton.CENTER, Color.WHITE, BorderFactory.createMatteBorder(3, 5, 3, 2, new Color(0xFEC20C)));
        confirmAdjust.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == confirmAdjust) {
                    if (adjustEnglish.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(myFrame, "Hãy điền từ tiếng anh!",
                                "Thao tác không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
                    } else if (adjustMeaning.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(myFrame, "Hãy điền nghĩa tiếng Việt mới!",
                                "Thao tác không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
                    } else if (!map.keySet().contains(adjustEnglish.getText().toLowerCase())) {
                        JOptionPane.showMessageDialog(myFrame, "Từ " + adjustEnglish.getText()
                                + " không tồn tại trong từ điển.", "Thao tác không hợp lệ",
                                JOptionPane.INFORMATION_MESSAGE);
                        adjustEnglish.setText(null);
                        adjustMeaning.setText(null);
                    } else if (map.get(adjustEnglish.getText().toLowerCase()).getMeaning()
                            .contains(adjustMeaning.getText().toLowerCase())) {
                        JOptionPane.showMessageDialog(myFrame, "Nghĩa mới của từ không có sự khác biệt.",
                                "Thao tác không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
                        adjustEnglish.setText(null);
                        adjustMeaning.setText(null);
                    } else {
                        JOptionPane.showMessageDialog(myFrame, "Nghĩa của từ " + adjustEnglish.getText()
                                + " đã được sửa.", "Thao tác thành công", JOptionPane.INFORMATION_MESSAGE);
                        adjustWord(adjustEnglish.getText().toLowerCase(), adjustMeaning.getText().toLowerCase());
                        adjustEnglish.setText(null);
                        adjustMeaning.setText(null);
                    }

                }
            }
        });
        adjustPanel.add(adjustEnglish);
        adjustPanel.add(adjustMeaning);
        adjustPanel.add(confirmAdjust);
        adjustPanel.setPanelEnabledAndVisible(false);
        rightPane.add(adjustPanel);
        // Remove word panel
        MyPanel removePanel = new MyPanel(0, 40, 300, 400, Color.WHITE, null, false, true);
        MyTextField removeEnglish = new MyTextField(10, 20, 200, 45, MED_FONT, JTextField.LEFT_ALIGNMENT,
                JTextField.RIGHT_ALIGNMENT, BorderFactory.createTitledBorder("Từ tiếng anh"));
        MyButton confirmRemove = new MyButton(10, 120, 150, 35, "Xóa từ", MED_FONT, false, false,
                JButton.CENTER, JButton.CENTER, Color.WHITE, BorderFactory.createMatteBorder(3, 5, 3, 2, Color.RED));
        confirmRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == confirmRemove) {
                    if (removeEnglish.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(myFrame, "Hãy điền từ tiếng anh!",
                                "Thao tác không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
                    } else if (!map.containsKey(removeEnglish.getText().toLowerCase())) {
                        JOptionPane.showMessageDialog(myFrame, "Từ " + removeEnglish.getText() +
                                " không tồn tại trong từ điển.", "Thao tác không hợp lệ",
                                JOptionPane.INFORMATION_MESSAGE);
                        removeEnglish.setText(null);
                    } else {
                        JOptionPane.showMessageDialog(myFrame, "Đã xóa từ " + removeEnglish.getText()
                                + " khỏi từ điển.", "Thao tác thành công", JOptionPane.INFORMATION_MESSAGE);
                        removeWord(removeEnglish.getText().toLowerCase());
                        removeEnglish.setText(null);
                    }
                }
            }
        });
        removePanel.add(removeEnglish);
        removePanel.add(confirmRemove);
        removePanel.setPanelEnabledAndVisible(false);
        rightPane.add(removePanel);
        // Searched word list to select
        MyButton word1 = new MyButton(9, 83, 191, 30, null, MED_FONT, false, false, JButton.LEFT,
                JButton.TOP, Color.WHITE, BorderFactory.createEmptyBorder());
        MyButton word2 = new MyButton(9, 113, 191, 30, null, MED_FONT, false, false, JButton.LEFT,
                JButton.TOP, Color.WHITE, BorderFactory.createEmptyBorder());
        MyButton word3 = new MyButton(9, 143, 191, 30, null, MED_FONT, false, false, JButton.LEFT,
                JButton.TOP, Color.WHITE, BorderFactory.createEmptyBorder());
        MyButton word4 = new MyButton(9, 173, 191, 30, null, MED_FONT, false, false, JButton.LEFT,
                JButton.TOP, Color.WHITE, BorderFactory.createEmptyBorder());
        MyButton word5 = new MyButton(9, 203, 191, 30, null, MED_FONT, false, false, JButton.LEFT,
                JButton.TOP, Color.WHITE, BorderFactory.createEmptyBorder());
        MyButton word6 = new MyButton(9, 233, 191, 30, null, MED_FONT, false, false, JButton.LEFT,
                JButton.TOP, Color.WHITE, BorderFactory.createEmptyBorder());
        MyButton word7 = new MyButton(9, 263, 191, 30, null, MED_FONT, false, false, JButton.LEFT,
                JButton.TOP, Color.WHITE, BorderFactory.createEmptyBorder());
        MyButton word8 = new MyButton(9, 293, 191, 30, null, MED_FONT, false, false, JButton.LEFT,
                JButton.TOP, Color.WHITE, BorderFactory.createEmptyBorder());
        MyButton word9 = new MyButton(9, 323, 191, 30, null, MED_FONT, false, false, JButton.LEFT,
                JButton.TOP, Color.WHITE, BorderFactory.createEmptyBorder());
        List<MyButton> wordList = new ArrayList<>();
        wordList.add(word1);
        wordList.add(word2);
        wordList.add(word3);
        wordList.add(word4);
        wordList.add(word5);
        wordList.add(word6);
        wordList.add(word7);
        wordList.add(word8);
        wordList.add(word9);
        // text field to search
        MyTextField searcher = new MyTextField(10, 50, 170, 30, MED_FONT, JTextField.LEFT_ALIGNMENT,
                JTextField.TOP_ALIGNMENT, BorderFactory.createEmptyBorder());
        searcher.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                for (int i = wordList.size() - 1; i >= 0; i--) {
                    wordList.get(i).setVisible(false);
                    wordList.get(i).setEnabled(false);
                    wordList.get(i).setText(null);
                    ActionListener[] needRemove = wordList.get(i).getActionListeners();
                    for (ActionListener l : needRemove) {
                        wordList.get(i).removeActionListener(l);
                    }
                }
                if ((e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z') || (e.getKeyChar() >= 'A' && e.getKeyChar() <= 'z')
                        || NOT_ALPHABET.contains(e.getKeyChar())) {
                    input = input + e.getKeyChar();
                } else {
                    input = searcher.getText();
                }
                input = input.toLowerCase();
                if (!input.equalsIgnoreCase("")) {
                    List<String> found = appSearcher(input);
                    for (int i = 0; i < found.size(); i++) {
                        if (i >= wordList.size()) {
                            break;
                        }
                        String temp1 = found.get(i);
                        String temp2 = map.get(temp1).getMeaning();
                        wordList.get(i).setEnabled(true);
                        wordList.get(i).setVisible(true);
                        wordList.get(i).setText(temp1);
                        MyButton temp = wordList.get(i);
                        wordList.get(i).addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (e.getSource() == temp) {
                                    if (addPanel.isVisible()) {
                                        addPanel.setPanelEnabledAndVisible(false);
                                    }
                                    if (adjustPanel.isVisible()) {
                                        adjustPanel.setPanelEnabledAndVisible(false);
                                    }
                                    if (removePanel.isVisible()) {
                                        removePanel.setPanelEnabledAndVisible(false);
                                    }
                                    if (!wordPanel.isVisible()) {
                                        wordPanel.setPanelEnabledAndVisible(true);
                                    }
                                    english.setText(temp1);
                                    meaning.setText(temp2);
                                    searcher.setText(temp1);
                                    for (int i = wordList.size() - 1; i >= 0; i--) {
                                        wordList.get(i).setVisible(false);
                                        wordList.get(i).setEnabled(false);
                                        wordList.get(i).setText(null);
                                    }
                                }

                            }
                        });
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10 && !input.equalsIgnoreCase("")) {
                    if (addPanel.isVisible()) {
                        addPanel.setPanelEnabledAndVisible(false);
                    }
                    if (adjustPanel.isVisible()) {
                        adjustPanel.setPanelEnabledAndVisible(false);
                    }
                    if (removePanel.isVisible()) {
                        removePanel.setPanelEnabledAndVisible(false);
                    }
                    if (!wordPanel.isVisible()) {
                        wordPanel.setPanelEnabledAndVisible(true);
                    }
                    english.setText(word1.getText());
                    meaning.setText(map.get(word1.getText()).getMeaning());
                    searcher.setText(word1.getText());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    for (int i = wordList.size() - 1; i >= 0; i--) {
                        wordList.get(i).setVisible(false);
                        wordList.get(i).setEnabled(false);
                        wordList.get(i).setText(null);
                        ActionListener[] needRemove = wordList.get(i).getActionListeners();
                        for (ActionListener l : needRemove) {
                            wordList.get(i).removeActionListener(l);
                        }
                    }
                }
            }
        });
        MyButton clear = new MyButton(181, 58, 15, 15, true, true, JButton.CENTER, JButton.CENTER,
                new ImageIcon("D:\\Study\\java\\EnglishDictionary\\src\\image\\clear.png"),
                Color.WHITE, BorderFactory.createEmptyBorder());
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                input = "";
                searcher.setText("");
                for (int i = wordList.size() - 1; i >= 0; i--) {
                    wordList.get(i).setVisible(false);
                    wordList.get(i).setEnabled(false);
                    wordList.get(i).setText(null);
                }
            }
        });
        MyLabel searchBackground = new MyLabel(8, 48, 192, 35, JLabel.LEFT, JLabel.TOP,
                new ImageIcon("D:\\Study\\java\\EnglishDictionary\\src\\image\\searchBorder.png"));
        MyLabel upperSearch = new MyLabel(0, 0, 210, 40, new Color(0x093161));

        MyLayerdPane leftPane = new MyLayerdPane(0, 0, 210, 400, new Color(0xCCCCCC), true);
        leftPane.add(word1, Integer.valueOf(1));
        leftPane.add(word2, Integer.valueOf(1));
        leftPane.add(word3, Integer.valueOf(1));
        leftPane.add(word4, Integer.valueOf(1));
        leftPane.add(word5, Integer.valueOf(1));
        leftPane.add(word6, Integer.valueOf(1));
        leftPane.add(word7, Integer.valueOf(1));
        leftPane.add(word8, Integer.valueOf(1));
        leftPane.add(word9, Integer.valueOf(1));
        leftPane.add(searcher, Integer.valueOf(1));
        leftPane.add(clear, Integer.valueOf(2));
        leftPane.add(searchBackground, Integer.valueOf(0));
        leftPane.add(upperSearch, Integer.valueOf(0));
        // Add word button
        MyButton addWord = new MyButton(10, 100, 190, 30, "Thêm từ", BIG_FONT, true, true, JButton.CENTER,
                JButton.CENTER, Color.WHITE, null);
        leftPane.add(addWord);
        addWord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addWord) {
                    if (!input.equalsIgnoreCase("")) {
                        input = "";
                    }
                    if (!searcher.getText().equalsIgnoreCase("")) {
                        searcher.setText("");
                    }
                    if (wordPanel.isVisible()) {
                        wordPanel.setPanelEnabledAndVisible(false);
                    }
                    if (adjustPanel.isVisible()) {
                        adjustPanel.setPanelEnabledAndVisible(false);
                    }
                    if (!removePanel.isVisible()) {
                        removePanel.setPanelEnabledAndVisible(false);
                    }
                    if (!addPanel.isVisible()) {
                        addPanel.setPanelEnabledAndVisible(true);
                    }
                }
            }
        });
        // Adjust word button
        MyButton adjustWord = new MyButton(10, 160, 190, 30, "Sửa từ", BIG_FONT, true, true, JButton.CENTER,
                JButton.CENTER, Color.WHITE, null);
        leftPane.add(adjustWord);
        adjustWord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == adjustWord) {
                    if (!input.equalsIgnoreCase("")) {
                        input = "";
                    }
                    if (!searcher.getText().equalsIgnoreCase("")) {
                        searcher.setText("");
                    }
                    if (wordPanel.isVisible()) {
                        wordPanel.setPanelEnabledAndVisible(false);
                    }
                    if (addPanel.isVisible()) {
                        addPanel.setPanelEnabledAndVisible(false);
                    }
                    if (removePanel.isVisible()) {
                        removePanel.setPanelEnabledAndVisible(false);
                    }
                    if (!adjustPanel.isVisible()) {
                        adjustPanel.setPanelEnabledAndVisible(true);
                    }
                }
            }
        });
        //Remove word button
        MyButton removeWord = new MyButton(10, 220, 190, 30, "Xóa từ", BIG_FONT, true, true, JButton.CENTER,
                JButton.CENTER, Color.WHITE, null);
        leftPane.add(removeWord);
        removeWord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == removeWord) {
                    if (!input.equalsIgnoreCase("")) {
                        input = "";
                    }
                    if (!searcher.getText().equalsIgnoreCase("")) {
                        searcher.setText("");
                    }
                    if (wordPanel.isVisible()) {
                        wordPanel.setPanelEnabledAndVisible(false);
                    }
                    if (addPanel.isVisible()) {
                        addPanel.setPanelEnabledAndVisible(false);
                    }
                    if (adjustPanel.isVisible()) {
                        adjustPanel.setPanelEnabledAndVisible(false);
                    }
                    if (!removePanel.isVisible()) {
                        removePanel.setPanelEnabledAndVisible(true);
                    }
                }
            }
        });

        myFrame.add(rightPane);
        myFrame.add(leftPane);
        myFrame.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                ImageIcon image = new ImageIcon("D:\\Study\\java\\EnglishDictionary\\src\\image\\hello.jpg");
                JOptionPane.showMessageDialog(myFrame, "This is my English - Vietnamese Dictionary",
                        "Hello!", JOptionPane.INFORMATION_MESSAGE, image);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                if (somethingChanged()) {
                    exportToFile();
                }
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });
        myFrame.setVisible(true);
    }
}