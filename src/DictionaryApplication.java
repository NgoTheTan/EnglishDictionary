import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
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
    public static final Font SMALL_FONT = new Font("Montserrat", Font.BOLD, 10);

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

        MyLayerdPane rightPane = new MyLayerdPane(210, 0, 340, 400, Color.WHITE, true);
        // Searched word panel.
        ImageIcon arrow = new ImageIcon("D:\\Study\\java\\EnglishDictionary\\src\\image\\arrow.png");
        MyLabel english = new MyLabel(10, 10, 330, 35, null, BIG_FONT, Color.BLUE, JLabel.LEFT, JLabel.TOP,
                Color.WHITE);
        MyLabel pronunciation = new MyLabel(10, 50, 330, 25, null, MED_FONT, Color.BLUE, JLabel.LEFT, 
                JLabel.TOP, Color.WHITE);
        MyLabel type1 = new MyLabel(10, 80, 330, 25, null, MED_FONT, new Color(0x999999),
                JLabel.LEFT, JLabel.TOP, JLabel.RIGHT, JLabel.CENTER, arrow, Color.WHITE);
        MyLabel meaning1 = new MyLabel(20, 110, 330, 25, null, MED_FONT, Color.BLACK, JLabel.LEFT, JLabel.TOP,
                Color.WHITE);
        MyLabel type2 = new MyLabel(10, 140, 330, 25, null, MED_FONT, new Color(0x999999),
                JLabel.LEFT, JLabel.TOP, JLabel.RIGHT, JLabel.CENTER, arrow, Color.WHITE);
        MyLabel meaning2 = new MyLabel(20, 170, 330, 25, null, MED_FONT, Color.BLACK, JLabel.LEFT, JLabel.TOP,
                Color.WHITE);
        MyLabel type3 = new MyLabel(10, 200, 330, 25, null, MED_FONT, new Color(0x999999),
                JLabel.LEFT, JLabel.TOP, JLabel.RIGHT, JLabel.CENTER, arrow, Color.WHITE);
        MyLabel meaning3 = new MyLabel(20, 230, 330, 25, null, MED_FONT, Color.BLACK, JLabel.LEFT, JLabel.TOP,
                Color.WHITE);
        MyLabel header = new MyLabel(0, 0, 340, 40, null, null, null, JLabel.CENTER, JLabel.CENTER,
                new Color(0x003399));
        MyLabel headerText = new MyLabel(10, 0, 340, 40, null, BIG_FONT, new Color(0xFBFAF5),
                JLabel.LEFT, JLabel.CENTER, new Color(0x003399));
        MyPanel wordPanel = new MyPanel(0, 40, 340, 400, Color.WHITE, null, false, true);
        rightPane.add(header, Integer.valueOf(0));
        rightPane.add(headerText, Integer.valueOf(1));
        wordPanel.add(english);
        wordPanel.add(pronunciation);
        wordPanel.add(type1);
        wordPanel.add(type2);
        wordPanel.add(type3);
        wordPanel.add(meaning1);
        wordPanel.add(meaning2);
        wordPanel.add(meaning3);
        wordPanel.setPanelEnabledAndVisible(false);
        List<MyLabel> meaningList = List.of(meaning1, meaning2, meaning3);
        List<MyLabel> typeList = List.of(type1, type2, type3);
        rightPane.add(wordPanel);

        MyLabel note = new MyLabel(10, 290, 330, 150,
                "<html>Chú ý: - Các loại từ gồm n (danh từ), v (động từ),adj (tính từ),<br/> &emsp adv (trạng từ), pron (đại từ), prep (giới từ), conj (liên từ),<br/> &emsp det (từ hạn định) và interjection (thán từ).<br/> &emsp &emsp - Mỗi từ loại cần đi cùng với nghĩa tương ứng.<br/>&emsp &emsp - Ngăn cách giữa các từ loại, nghĩa bằng dấu /.</html>",
                SMALL_FONT, Color.RED, JLabel.LEFT, JLabel.TOP, Color.WHITE);
        // Add word panel.
        MyPanel addPanel = new MyPanel(0, 40, 340, 250, Color.WHITE, null, false, true);
        MyTextField addEnglish = new MyTextField(10, 10, 250, 45, MED_FONT, JTextField.LEFT_ALIGNMENT,
                JTextField.RIGHT_ALIGNMENT, BorderFactory.createTitledBorder("Từ tiếng anh"));
        MyTextField addPronuciation = new MyTextField(10, 60, 250, 45, MED_FONT, JTextField.LEFT_ALIGNMENT,
                JTextField.RIGHT_ALIGNMENT, BorderFactory.createTitledBorder("Phát âm (có thể bỏ qua)"));
        MyTextField addType = new MyTextField(10, 110, 250, 45, MED_FONT, JTextField.LEFT_ALIGNMENT,
                JTextField.RIGHT_ALIGNMENT, BorderFactory.createTitledBorder("Từ loại"));
        MyTextField addMeaning = new MyTextField(10, 160, 250, 45, MED_FONT, JTextField.LEFT_ALIGNMENT,
                JTextField.RIGHT_ALIGNMENT, BorderFactory.createTitledBorder("Nghĩa tiếng Việt"));
        MyButton confirmAdd = new MyButton(10, 210, 150, 35, "Thêm vào từ điển", MED_FONT, false, false,
                JButton.CENTER, JButton.CENTER, Color.WHITE,
                BorderFactory.createMatteBorder(3, 5, 3, 2, new Color(0x4169E1)));
        confirmAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == confirmAdd) {
                    if (addEnglish.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(myFrame, "Hãy điền từ tiếng anh!",
                                "Thao tác không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
                    } else if (addType.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(myFrame, "Hãy điền từ loại!",
                                "Thao tác không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
                    } else if (addMeaning.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(myFrame, "Hãy điền nghĩa tiếng Việt!",
                                "Thao tác không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
                    } else if (map.keySet().contains(addEnglish.getText().toLowerCase())) {
                        JOptionPane.showMessageDialog(myFrame, "Từ " + addEnglish.getText() + " đã có trong từ điển.",
                                "Thao tác không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
                        addEnglish.setText(null);
                        addMeaning.setText(null);
                        addType.setText(null);
                        addPronuciation.setText(null);
                    } else {
                        String[] temp1 = addType.getText().toLowerCase().split("/");
                        String[] temp2 = addMeaning.getText().toLowerCase().split("/");
                        if (Integer.compare(temp1.length, temp2.length) != 0) {
                            JOptionPane.showMessageDialog(myFrame, "Số từ loại và số ngữ nghĩa không tương đồng",
                                    "Thao tác không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            List<String> type = new ArrayList<>(Arrays.asList(temp1));
                            List<String> meaning = new ArrayList<>(Arrays.asList(temp2));
                            insertWord(addEnglish.getText().toLowerCase(), meaning, type, 
                                addPronuciation.getText());
                            JOptionPane.showMessageDialog(myFrame, "Từ mới " + addEnglish.getText()
                                    + " đã được thêm vào", "Thao tác thành công",
                                    JOptionPane.INFORMATION_MESSAGE);
                            addEnglish.setText(null);
                            addMeaning.setText(null);
                            addType.setText(null);
                            addPronuciation.setText(null);
                        }
                    }

                }
            }
        });
        addPanel.add(confirmAdd);
        addPanel.add(addEnglish);
        addPanel.add(addPronuciation);
        addPanel.add(addMeaning);
        addPanel.add(addType);
        addPanel.setPanelEnabledAndVisible(false);
        rightPane.add(addPanel);
        // Adjust word panel.
        MyPanel adjustPanel = new MyPanel(0, 40, 340, 250, Color.WHITE, null, false, true);
        MyTextField adjustEnglish = new MyTextField(10, 10, 250, 45, MED_FONT, JTextField.LEFT_ALIGNMENT,
                JTextField.RIGHT_ALIGNMENT, BorderFactory.createTitledBorder("Từ tiếng anh"));
        MyTextField adjustPronuciation = new MyTextField(10, 60, 250, 45, MED_FONT, JTextField.LEFT_ALIGNMENT,
                JTextField.RIGHT_ALIGNMENT, BorderFactory.createTitledBorder("Phát âm (có thể bỏ qua)"));
        MyTextField adjustType = new MyTextField(10, 110, 250, 45, MED_FONT, JTextField.LEFT_ALIGNMENT,
                JTextField.RIGHT_ALIGNMENT, BorderFactory.createTitledBorder("Từ loại"));
        MyTextField adjustMeaning = new MyTextField(10, 160, 250, 45, MED_FONT, JTextField.LEFT_ALIGNMENT,
                JTextField.RIGHT_ALIGNMENT, BorderFactory.createTitledBorder("Nghĩa tiếng Việt mới"));
        MyButton confirmAdjust = new MyButton(10, 210, 150, 35, "Sửa từ", MED_FONT, false, false,
                JButton.CENTER, JButton.CENTER, Color.WHITE,
                BorderFactory.createMatteBorder(3, 5, 3, 2, new Color(0xFEC20C)));
        confirmAdjust.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == confirmAdjust) {
                    if (adjustEnglish.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(myFrame, "Hãy điền từ tiếng anh!",
                                "Thao tác không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
                    } else if (adjustType.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(myFrame, "Hãy điền từ loại!",
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
                        adjustType.setText(null);
                        adjustPronuciation.setText(null);
                    } else {
                        String[] temp1 = adjustType.getText().toLowerCase().split("/");
                        String[] temp2 = adjustMeaning.getText().toLowerCase().split("/");
                        if (Integer.compare(temp1.length, temp2.length) != 0) {
                            JOptionPane.showMessageDialog(myFrame, "Số từ loại và số ngữ nghĩa không tương đồng",
                                    "Thao tác không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            List<String> type = new ArrayList<>(Arrays.asList(temp1));
                            List<String> meaning = new ArrayList<>(Arrays.asList(temp2));
                            adjustWord(adjustEnglish.getText().toLowerCase(), meaning, type, adjustPronuciation.getText());
                            JOptionPane.showMessageDialog(myFrame, "Nghĩa của từ " + adjustEnglish.getText()
                                    + " đã được sửa.", "Thao tác thành công", JOptionPane.INFORMATION_MESSAGE);
                            adjustEnglish.setText(null);
                            adjustMeaning.setText(null);
                            adjustType.setText(null);
                            adjustPronuciation.setText(null);
                        }

                    }

                }
            }
        });
        adjustPanel.add(adjustEnglish);
        adjustPanel.add(adjustType);
        adjustPanel.add(adjustMeaning);
        adjustPanel.add(confirmAdjust);
        adjustPanel.add(adjustPronuciation);
        adjustPanel.setPanelEnabledAndVisible(false);
        rightPane.add(adjustPanel);
        // Remove word panel
        MyPanel removePanel = new MyPanel(0, 40, 340, 210, Color.WHITE, null, false, true);
        MyTextField removeEnglish = new MyTextField(10, 20, 250, 45, MED_FONT, JTextField.LEFT_ALIGNMENT,
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
        rightPane.add(note);
        note.setVisible(false);
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
        List<MyButton> wordList = List.of(word1, word2, word3, word4, word5, word6, word7, word8, word9);
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
                        wordList.get(i).setEnabled(true);
                        wordList.get(i).setVisible(true);
                        wordList.get(i).setText(found.get(i));
                        MyButton temp = wordList.get(i);
                        wordList.get(i).addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (e.getSource() == temp) {
                                    if (note.isVisible()) {
                                        note.setVisible(false);
                                    }
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
                                        headerText.setText("Định nghĩa");
                                    }
                                    english.setText("<HTML><U>" + temp.getText() + "</U></HTML>");
                                    pronunciation.setText(map.get(temp.getText()).getPronuciation());
                                    for (int i = 0; i < map.get(temp.getText()).getType().size(); i++) {
                                        if (map.get(temp.getText()).getType().get(i).equalsIgnoreCase("n")) {
                                            typeList.get(i).setText("<HTML><U>" + "Danh từ" + "</U></HTML>");
                                        } else if (map.get(temp.getText()).getType().get(i).equalsIgnoreCase("v")) {
                                            typeList.get(i).setText("<HTML><U>" + "Động từ" + "</U></HTML>");
                                        } else if (map.get(temp.getText()).getType().get(i).equalsIgnoreCase("adj")) {
                                            typeList.get(i).setText("<HTML><U>" + "Tính từ" + "</U></HTML>");
                                        } else if (map.get(temp.getText()).getType().get(i).equalsIgnoreCase("adv")) {
                                            typeList.get(i).setText("<HTML><U>" + "Trạng từ" + "</U></HTML>");
                                        } else if (map.get(temp.getText()).getType().get(i).equalsIgnoreCase("pron")) {
                                            typeList.get(i).setText("<HTML><U>" + "Đại từ" + "</U></HTML>");
                                        } else if (map.get(temp.getText()).getType().get(i).equalsIgnoreCase("prep")) {
                                            typeList.get(i).setText("<HTML><U>" + "Giới từ" + "</U></HTML>");
                                        } else if (map.get(temp.getText()).getType().get(i).equalsIgnoreCase("det")) {
                                            typeList.get(i).setText("<HTML><U>" + "Từ hạn định" + "</U></HTML>");
                                        } else if (map.get(temp.getText()).getType().get(i).equalsIgnoreCase("conj")) {
                                            typeList.get(i).setText("<HTML><U>" + "Liên từ" + "</U></HTML>");
                                        } else if (map.get(temp.getText()).getType().get(i).equalsIgnoreCase("interjection")) {
                                            typeList.get(i).setText("<HTML><U>" + "Thán từ" + "</U></HTML>");
                                        } else {
                                            typeList.get(i).setText("<HTML><U>" + "*Từ loại không phù hợp, vui lòng sửa"
                                                    + "</U></HTML>");
                                        }
                                        meaningList.get(i).setText(map.get(temp.getText()).getMeaning().get(i));
                                        typeList.get(i).setVisible(true);
                                        meaningList.get(i).setVisible(true);
                                    }
                                    for (int i = map.get(temp.getText()).getType().size(); i < typeList.size(); i++) {
                                        typeList.get(i).setVisible(false);
                                        meaningList.get(i).setVisible(false);
                                    }
                                    searcher.setText(temp.getText());
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
                if (e.getKeyCode() == 10 && !appSearcher(input).isEmpty()) {
                    if (note.isVisible()) {
                        note.setVisible(false);
                    }
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
                        headerText.setText("Định nghĩa");
                    }
                    english.setText("<HTML><U>" + word1.getText() + "</U></HTML>");
                    pronunciation.setText(map.get(word1.getText()).getPronuciation());
                    for (int i = 0; i < map.get(word1.getText()).getType().size(); i++) {
                        if (map.get(word1.getText()).getType().get(i).equalsIgnoreCase("n")) {
                            typeList.get(i).setText("<HTML><U>" + "Danh từ" + "</U></HTML>");
                        } else if (map.get(word1.getText()).getType().get(i).equalsIgnoreCase("v")) {
                            typeList.get(i).setText("<HTML><U>" + "Động từ" + "</U></HTML>");
                        } else if (map.get(word1.getText()).getType().get(i).equalsIgnoreCase("adj")) {
                            typeList.get(i).setText("<HTML><U>" + "Tính từ" + "</U></HTML>");
                        } else if (map.get(word1.getText()).getType().get(i).equalsIgnoreCase("adv")) {
                            typeList.get(i).setText("<HTML><U>" + "Trạng từ" + "</U></HTML>");
                        } else if (map.get(word1.getText()).getType().get(i).equalsIgnoreCase("pron")) {
                            typeList.get(i).setText("<HTML><U>" + "Đại từ" + "</U></HTML>");
                        } else if (map.get(word1.getText()).getType().get(i).equalsIgnoreCase("prep")) {
                            typeList.get(i).setText("<HTML><U>" + "Giới từ" + "</U></HTML>");
                        } else if (map.get(word1.getText()).getType().get(i).equalsIgnoreCase("det")) {
                            typeList.get(i).setText("<HTML><U>" + "Từ hạn định" + "</U></HTML>");
                        } else if (map.get(word1.getText()).getType().get(i).equalsIgnoreCase("conj")) {
                            typeList.get(i).setText("<HTML><U>" + "Liên từ" + "</U></HTML>");
                        } else if (map.get(word1.getText()).getType().get(i).equalsIgnoreCase("interjection")) {
                            typeList.get(i).setText("<HTML><U>" + "Thán từ" + "</U></HTML>");
                        } else {
                            typeList.get(i)
                                    .setText("<HTML><U>" + "*Từ loại không phù hợp, vui lòng sửa" + "</U></HTML>");
                        }
                        meaningList.get(i).setText(map.get(word1.getText()).getMeaning().get(i));
                        typeList.get(i).setVisible(true);
                        meaningList.get(i).setVisible(true);
                    }
                    for (int i = map.get(word1.getText()).getType().size(); i < typeList.size(); i++) {
                        typeList.get(i).setVisible(false);
                        meaningList.get(i).setVisible(false);
                    }
                    searcher.setText(word1.getText());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10 && !appSearcher(input).isEmpty()) {
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
        MyLabel upperSearch = new MyLabel(0, 0, 210, 40, "Anh - Việt", BIG_FONT, new Color(0xFBFAF5),
                JLabel.CENTER, JLabel.CENTER, JLabel.LEFT, JLabel.CENTER,
                new ImageIcon("D:\\Study\\java\\EnglishDictionary\\src\\image\\searchIcon.png"),
                new Color(0x093161));
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
        MyButton addWord = new MyButton(10, 100, 190, 35, "Thêm từ", BIG_FONT,
                new ImageIcon("D:\\Study\\java\\EnglishDictionary\\src\\image\\add.png"), true,
                true, JButton.LEFT, JButton.CENTER, JButton.RIGHT, JButton.CENTER, Color.WHITE,
                BorderFactory.createMatteBorder(3, 5, 3, 2, Color.GREEN));
        leftPane.add(addWord, Integer.valueOf(1));
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
                    if (removePanel.isVisible()) {
                        removePanel.setPanelEnabledAndVisible(false);
                    }
                    if (!note.isVisible()) {
                        note.setVisible(true);
                    }
                    if (!addPanel.isVisible()) {
                        addPanel.setPanelEnabledAndVisible(true);
                        headerText.setText("Thêm từ");
                    }
                }
            }
        });
        // Adjust word button
        MyButton adjustWord = new MyButton(10, 160, 190, 35, "Sửa từ", BIG_FONT,
                new ImageIcon("D:\\Study\\java\\EnglishDictionary\\src\\image\\adjust.png"), true,
                true, JButton.LEFT, JButton.CENTER, JButton.RIGHT, JButton.CENTER, Color.WHITE,
                BorderFactory.createMatteBorder(3, 5, 3, 2, new Color(0xFEC20C)));
        leftPane.add(adjustWord, Integer.valueOf(1));
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
                    if (!note.isVisible()) {
                        note.setVisible(true);
                    }
                    if (!adjustPanel.isVisible()) {
                        adjustPanel.setPanelEnabledAndVisible(true);
                        headerText.setText("Sửa từ");
                    }
                }
            }
        });
        // Remove word button
        MyButton removeWord = new MyButton(10, 220, 190, 35, "Xóa từ", BIG_FONT,
                new ImageIcon("D:\\Study\\java\\EnglishDictionary\\src\\image\\remove.png"), true,
                true, JButton.LEFT, JButton.CENTER, JButton.RIGHT, JButton.CENTER, Color.WHITE,
                BorderFactory.createMatteBorder(3, 5, 3, 2, Color.RED));
        leftPane.add(removeWord, Integer.valueOf(1));
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
                    if (note.isVisible()) {
                        note.setVisible(false);
                    }
                    if (!removePanel.isVisible()) {
                        removePanel.setPanelEnabledAndVisible(true);
                        headerText.setText("Xóa từ");
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
                JOptionPane.showMessageDialog(myFrame, "This is my English - Vietnamese Dictionary"
                        + ",a project for my OOP class.\nI'm still a freshman so there can be some"
                        + " mistakes :(.\nHope you will like it and I can have a satisfactory mark :D.",
                        "Hello!", JOptionPane.INFORMATION_MESSAGE, image);
                try {
                    insertFromFile();
                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                }
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