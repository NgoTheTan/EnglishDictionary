import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DictionaryApplication extends DictionaryManagement {
    public static final Set<Character> NOT_ALPHABET = Set.of('`', '~', '1', '!', '2', '@', '3',
            '#', '4', '$', '5', '%', '6', '^', '7', '&', '8', '*', '9', '(', '0', ')', '-', '_',
            '=', '+', '{', '[', ']', '}', '|', ':', ';', '"', ',', '<', '.', '>', '/', '?', ' ',
            '\'', '\\', 'ă', 'â', 'á', 'à', 'ạ', 'ã', 'ả', 'ê', 'è', 'ẽ', 'ẹ', 'ẻ', 'ư', 'ú',
            'ù', 'ũ', 'ủ', 'ụ', 'í', 'ì', 'ĩ', 'ỉ', 'ị', 'ơ', 'ô', 'ò', 'õ', 'ỏ', 'ó', 'ọ',
            'ý', 'ỷ', 'ỳ', 'ỹ', 'ỵ');
    public static final Font BIG_FONT = new Font("Montserrat", Font.BOLD, 25);
    public static final Font MED_FONT = new Font("Montserrat", Font.BOLD, 15);
    public static final Font SMALL_FONT = new Font("Montserrat", Font.BOLD, 10);
    private static String input = "";
    private static String word = "";

    protected static List<String> appSearcher(String input) {
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

        MyLayerdPane rightPane = new MyLayerdPane(210, 0, 350, 400, Color.WHITE, true);
        // Searched word panel.
        ImageIcon arrow = new ImageIcon("D:\\Study\\java\\EnglishDictionary\\src\\image\\arrow.png");
        MyLabel english = new MyLabel(10, 10, 250, 35, null, BIG_FONT, Color.BLUE, JLabel.LEFT, JLabel.TOP,
                Color.WHITE);
        MyButton speaker = new MyButton(10, 45, 30, 30, false, false, JLabel.CENTER, JLabel.CENTER,
                new ImageIcon("D:\\Study\\java\\EnglishDictionary\\src\\image\\speaker.png"), null, null);
        speaker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == speaker) {
                    Speaker.pronounce(word);
                }
            }
        });
        MyLabel pronunciation = new MyLabel(50, 50, 330, 25, null, MED_FONT, Color.BLUE, JLabel.LEFT,
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
        MyLabel header = new MyLabel(0, 0, 350, 40, null, null, null, JLabel.CENTER, JLabel.CENTER,
                new Color(0x003399));
        MyLabel headerText = new MyLabel(10, 0, 340, 40, null, BIG_FONT, new Color(0xFBFAF5),
                JLabel.LEFT, JLabel.CENTER, new Color(0x003399));
        JCheckBox starred = new JCheckBox(new ImageIcon("D:\\Study\\java\\EnglishDictionary\\src\\image\\unstored.png"),
                false);
        starred.setSelectedIcon(new ImageIcon("D:\\Study\\java\\EnglishDictionary\\src\\image\\stored.png"));
        starred.setBounds(270, 5, 48, 48);
        starred.setToolTipText("Thêm/xóa khỏi danh sách từ đang học");
        starred.setAlignmentX(JCheckBox.CENTER_ALIGNMENT);
        starred.setAlignmentY(JCheckBox.CENTER_ALIGNMENT);
        starred.setBackground(Color.WHITE);
        starred.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == starred) {
                    if (!starred.isSelected()) {
                        doneLearning(word);
                    } else {
                        addToLearning(word);
                    }
                }
            }
        });
        MyPanel wordPanel = new MyPanel(0, 40, 340, 400, Color.WHITE, null, false);
        rightPane.add(header, Integer.valueOf(0));
        rightPane.add(headerText, Integer.valueOf(1));
        wordPanel.add(english);
        wordPanel.add(speaker);
        wordPanel.add(pronunciation);
        wordPanel.add(type1);
        wordPanel.add(type2);
        wordPanel.add(type3);
        wordPanel.add(meaning1);
        wordPanel.add(meaning2);
        wordPanel.add(meaning3);
        wordPanel.add(starred);
        wordPanel.setPanelEnabledAndVisible(false);
        List<MyLabel> meaningList = List.of(meaning1, meaning2, meaning3);
        List<MyLabel> typeList = List.of(type1, type2, type3);
        rightPane.add(wordPanel);
        // Note for adding or adjusting syntax
        MyLabel note = new MyLabel(10, 290, 330, 150,
                "<html>Chú ý: - Các loại từ gồm n (danh từ), v (động từ),adj (tính từ),<br/> &emsp adv (trạng từ), pron (đại từ), prep (giới từ), conj (liên từ),<br/> &emsp det (từ hạn định) và interjection (thán từ).<br/> &emsp &emsp - Mỗi từ loại cần đi cùng với nghĩa tương ứng.<br/>&emsp &emsp - Ngăn cách giữa các từ loại, nghĩa bằng dấu /.</html>",
                SMALL_FONT, Color.RED, JLabel.LEFT, JLabel.TOP, Color.WHITE);
        // Learning panel.
        MyButton learning1 = new MyButton(10, 10, 200, 30, null, MED_FONT, false, false,
                JButton.LEFT, JButton.CENTER, Color.WHITE, BorderFactory.createEmptyBorder());
        MyButton learning2 = new MyButton(10, 40, 200, 30, null, MED_FONT, false, false,
                JButton.LEFT, JButton.CENTER, Color.WHITE, BorderFactory.createEmptyBorder());
        MyButton learning3 = new MyButton(10, 70, 200, 30, null, MED_FONT, false, false,
                JButton.LEFT, JButton.CENTER, Color.WHITE, BorderFactory.createEmptyBorder());
        MyButton learning4 = new MyButton(10, 100, 200, 30, null, MED_FONT, false, false,
                JButton.LEFT, JButton.CENTER, Color.WHITE, BorderFactory.createEmptyBorder());
        MyButton learning5 = new MyButton(10, 130, 200, 30, null, MED_FONT, false, false,
                JButton.LEFT, JButton.CENTER, Color.WHITE, BorderFactory.createEmptyBorder());
        MyButton learning6 = new MyButton(10, 160, 200, 30, null, MED_FONT, false, false,
                JButton.LEFT, JButton.CENTER, Color.WHITE, BorderFactory.createEmptyBorder());
        MyButton learning7 = new MyButton(10, 190, 200, 30, null, MED_FONT, false, false,
                JButton.LEFT, JButton.CENTER, Color.WHITE, BorderFactory.createEmptyBorder());
        MyButton learning8 = new MyButton(10, 220, 200, 30, null, MED_FONT, false, false,
                JButton.LEFT, JButton.CENTER, Color.WHITE, BorderFactory.createEmptyBorder());
        MyButton learning9 = new MyButton(10, 250, 200, 30, null, MED_FONT, false, false,
                JButton.LEFT, JButton.CENTER, Color.WHITE, BorderFactory.createEmptyBorder());
        MyButton learning10 = new MyButton(10, 280, 200, 30, null, MED_FONT, false, false,
                JButton.LEFT, JButton.CENTER, Color.WHITE, BorderFactory.createEmptyBorder());
        List<MyButton> learningList = List.of(learning1, learning2, learning3, learning4, learning5, learning6,
                learning7, learning8, learning9, learning10);
        MyPanel learningPanel = new MyPanel(0, 40, 340, 400, Color.WHITE, null, false);
        learningPanel.add(learning1);
        learningPanel.add(learning2);
        learningPanel.add(learning3);
        learningPanel.add(learning4);
        learningPanel.add(learning5);
        learningPanel.add(learning6);
        learningPanel.add(learning7);
        learningPanel.add(learning8);
        learningPanel.add(learning9);
        learningPanel.add(learning10);
        learningPanel.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (learning.size() > learningList.size()) {
                    if (e.getWheelRotation() < 0 && !learning.get(0).equalsIgnoreCase(learning1.getText())) {
                        for (int i = 0; i < learningList.size(); i++) {
                            learningList.get(i)
                                    .setText(learning.get(learning.indexOf(learningList.get(i).getText()) - 1));
                        }
                    } else if (e.getWheelRotation() > 0
                            && !learning.get(learning.size() - 1).equalsIgnoreCase(learning10.getText())) {
                        for (int i = 0; i < learningList.size(); i++) {
                            learningList.get(i)
                                    .setText(learning.get(learning.indexOf(learningList.get(i).getText()) + 1));
                        }
                    }
                }
            }
        });
        for (int i = 0; i < learningList.size(); i++) {
            MyButton temp = learningList.get(i);
            temp.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == temp) {
                        learningPanel.setPanelEnabledAndVisible(false);
                        wordPanel.setPanelEnabledAndVisible(true);
                        headerText.setText("Định nghĩa");
                        starred.setSelected(true);
                        english.setText("<HTML><U>" + temp.getText() + "</U></HTML>");
                        word = temp.getText();
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
                            } else if (map.get(temp.getText()).getType().get(i)
                                    .equalsIgnoreCase("interjection")) {
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
                    }
                }
            });
        }
        learningPanel.setPanelEnabledAndVisible(false);
        rightPane.add(learningPanel);
        // Add word panel.
        MyPanel addPanel = new MyPanel(0, 40, 340, 250, Color.WHITE, null, false);
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
        MyPanel adjustPanel = new MyPanel(0, 40, 340, 250, Color.WHITE, null, false);
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
                            adjustWord(adjustEnglish.getText().toLowerCase(), meaning, type,
                                    adjustPronuciation.getText());
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
        MyPanel removePanel = new MyPanel(0, 40, 340, 210, Color.WHITE, null, false);
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
        // Text field to search
        MyTextField searcher = new MyTextField(10, 50, 170, 30, MED_FONT, JTextField.LEFT_ALIGNMENT,
                JTextField.TOP_ALIGNMENT, BorderFactory.createEmptyBorder());
        searcher.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2 || e.getClickCount() == 3) {
                    input = "";
                } else {
                    input = searcher.getText();
                }
            }
        });
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
                System.out.println(input);
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
                                    } else if (adjustPanel.isVisible()) {
                                        adjustPanel.setPanelEnabledAndVisible(false);
                                    } else if (removePanel.isVisible()) {
                                        removePanel.setPanelEnabledAndVisible(false);
                                    } else if (learningPanel.isVisible()) {
                                        learningPanel.setPanelEnabledAndVisible(false);
                                    }
                                    if (!wordPanel.isVisible()) {
                                        wordPanel.setPanelEnabledAndVisible(true);
                                        headerText.setText("Định nghĩa");
                                    }
                                    if (learning.contains(temp.getText())) {
                                        starred.setSelected(true);
                                    } else {
                                        starred.setSelected(false);
                                    }
                                    english.setText("<HTML><U>" + temp.getText() + "</U></HTML>");
                                    word = temp.getText();
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
                                        } else if (map.get(temp.getText()).getType().get(i)
                                                .equalsIgnoreCase("interjection")) {
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
                    } else if (adjustPanel.isVisible()) {
                        adjustPanel.setPanelEnabledAndVisible(false);
                    } else if (removePanel.isVisible()) {
                        removePanel.setPanelEnabledAndVisible(false);
                    } else if (learningPanel.isVisible()) {
                        learningPanel.setPanelEnabledAndVisible(false);
                    }
                    if (!wordPanel.isVisible()) {
                        wordPanel.setPanelEnabledAndVisible(true);
                        headerText.setText("Định nghĩa");
                    }
                    english.setText("<HTML><U>" + word1.getText() + "</U></HTML>");
                    word = word1.getText();
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
        leftPane.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (!input.equals("") && appSearcher(input).size() > wordList.size()) {
                    List<String> found = appSearcher(input);
                    if (e.getWheelRotation() < 0 && !found.get(0).equalsIgnoreCase(word1.getText())) {
                        for (int i = 0; i < wordList.size(); i++) {
                            wordList.get(i).setText(found.get(found.indexOf(wordList.get(i).getText()) - 1));
                        }
                    } else if (e.getWheelRotation() > 0
                            && !found.get(found.size() - 1).equalsIgnoreCase(word9.getText())) {
                        for (int i = 0; i < wordList.size(); i++) {
                            wordList.get(i).setText(found.get(found.indexOf(wordList.get(i).getText()) + 1));
                        }
                    }
                }
            }
        });
        // Show learning words button
        MyButton showLearningList = new MyButton(10, 100, 190, 35, "Từ đang học", BIG_FONT,
                new ImageIcon("D:\\Study\\java\\EnglishDictionary\\src\\image\\learning.png"), true,
                true, JButton.LEFT, JButton.CENTER, JButton.RIGHT, JButton.CENTER, Color.WHITE,
                BorderFactory.createMatteBorder(3, 5, 3, 2, new Color(0xFFD700)));
        leftPane.add(showLearningList, Integer.valueOf(1));
        showLearningList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == showLearningList) {
                    if (!input.equalsIgnoreCase("")) {
                        input = "";
                    }
                    if (!searcher.getText().equalsIgnoreCase("")) {
                        searcher.setText("");
                    }
                    if (note.isVisible()) {
                        note.setVisible(false);
                    }
                    if (addPanel.isVisible()) {
                        addPanel.setPanelEnabledAndVisible(false);
                    } else if (adjustPanel.isVisible()) {
                        adjustPanel.setPanelEnabledAndVisible(false);
                    } else if (removePanel.isVisible()) {
                        removePanel.setPanelEnabledAndVisible(false);
                    } else if (wordPanel.isVisible()) {
                        wordPanel.setPanelEnabledAndVisible(false);
                    }
                    if (!learningPanel.isVisible()) {
                        learningPanel.setPanelEnabledAndVisible(true);
                        headerText.setText("Từ đang học");
                        for (int i = 0; i < learning.size(); i++) {
                            if (i >= learningList.size()) {
                                break;
                            }
                            learningList.get(i).setText(learning.get(i));
                            learningList.get(i).setVisible(true);
                            learningList.get(i).setEnabled(true);
                        }
                        for (int i = learning.size(); i < learningList.size(); i++) {
                            learningList.get(i).setText(null);
                            learningList.get(i).setVisible(false);
                            learningList.get(i).setEnabled(false);
                        }
                    }
                }
            }
        });
        // Add word button
        MyButton addWord = new MyButton(10, 160, 190, 35, "Thêm từ", BIG_FONT,
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
                    } else if (learningPanel.isVisible()) {
                        learningPanel.setPanelEnabledAndVisible(false);
                    } else if (adjustPanel.isVisible()) {
                        adjustPanel.setPanelEnabledAndVisible(false);
                    } else if (removePanel.isVisible()) {
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
        MyButton adjustWord = new MyButton(10, 220, 190, 35, "Sửa từ", BIG_FONT,
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
                    } else if (addPanel.isVisible()) {
                        addPanel.setPanelEnabledAndVisible(false);
                    } else if (removePanel.isVisible()) {
                        removePanel.setPanelEnabledAndVisible(false);
                    } else if (learningPanel.isVisible()) {
                        learningPanel.setPanelEnabledAndVisible(false);
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
        MyButton removeWord = new MyButton(10, 280, 190, 35, "Xóa từ", BIG_FONT,
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
                    } else if (addPanel.isVisible()) {
                        addPanel.setPanelEnabledAndVisible(false);
                    } else if (adjustPanel.isVisible()) {
                        adjustPanel.setPanelEnabledAndVisible(false);
                    } else if (learningPanel.isVisible()) {
                        learningPanel.setPanelEnabledAndVisible(false);
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
        myFrame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowOpened(WindowEvent e) {
                ImageIcon image = new ImageIcon("D:\\Study\\java\\EnglishDictionary\\src\\image\\hello.jpg");
                JOptionPane.showMessageDialog(myFrame, "This is my English - Vietnamese Dictionary"
                        + ",a project for my OOP class.\nI'm still a freshman so there can be some"
                        + " mistakes :(.\nHope you will like it and I can have a satisfactory mark :D.",
                        "Hello!", JOptionPane.INFORMATION_MESSAGE, image);
                try {
                    insertFromFile();
                    loadLearning();
                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    exportToFile();
                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                }
                System.exit(0);
            }
        });
        myFrame.setVisible(true);
    }
}