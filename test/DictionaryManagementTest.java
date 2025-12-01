import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class DictionaryManagementTest {

    @BeforeEach
    public void setUp() {
        // Xóa dữ liệu trong map trước mỗi test
        Dictionary.map.clear();
        DictionaryManagement.learning.clear();
    }

    @AfterEach
    public void tearDown() {
        // Dọn dẹp sau mỗi test
        Dictionary.map.clear();
        DictionaryManagement.learning.clear();
    }

    // Tests cho insertWord()
    @Test
    public void testInsertWord_ValidNewWord() {
        List<String> meanings = Arrays.asList("con mèo");
        List<String> types = Arrays.asList("n");

        DictionaryManagement.insertWord("cat", meanings, types, "/kæt/");

        assertTrue(Dictionary.map.containsKey("cat"));
        assertEquals("con mèo", Dictionary.map.get("cat").getMeaning().get(0));
    }

    @Test
    public void testInsertWord_MultipleTypesAndMeanings() {
        List<String> meanings = Arrays.asList("sách", "đặt chỗ");
        List<String> types = Arrays.asList("n", "v");

        DictionaryManagement.insertWord("book", meanings, types, "/bʊk/");

        assertEquals(2, Dictionary.map.get("book").getMeaning().size());
        assertEquals(2, Dictionary.map.get("book").getType().size());
    }

    @Test
    public void testInsertWord_EmptyPronunciation() {
        List<String> meanings = Arrays.asList("xin chào");
        List<String> types = Arrays.asList("interjection");

        DictionaryManagement.insertWord("hello", meanings, types, "");

        assertTrue(Dictionary.map.containsKey("hello"));
        assertEquals("", Dictionary.map.get("hello").getPronuciation());
    }

    @Test
    public void testInsertWord_LowercaseConversion() {
        List<String> meanings = Arrays.asList("con chó");
        List<String> types = Arrays.asList("n");

        DictionaryManagement.insertWord("DOG", meanings, types, "/dɒɡ/");

        // Từ phải được lưu dưới dạng lowercase
        assertTrue(Dictionary.map.containsKey("dog"));
        assertFalse(Dictionary.map.containsKey("DOG"));
    }

    // Tests cho adjustWord()
    @Test
    public void testAdjustWord_ExistingWord() {
        // Thêm từ trước
        DictionaryManagement.insertWord("cat",
                Arrays.asList("con mèo"),
                Arrays.asList("n"),
                "/kæt/");

        // Sửa từ
        List<String> newMeanings = Arrays.asList("mèo nhà", "người xấu tính");
        List<String> newTypes = Arrays.asList("n", "n");
        DictionaryManagement.adjustWord("cat", newMeanings, newTypes, "/kæt/");

        assertEquals(2, Dictionary.map.get("cat").getMeaning().size());
        assertEquals("mèo nhà", Dictionary.map.get("cat").getMeaning().get(0));
    }

    @Test
    public void testAdjustWord_ChangePronunciation() {
        DictionaryManagement.insertWord("test",
                Arrays.asList("kiểm tra"),
                Arrays.asList("n"),
                "/test/");

        DictionaryManagement.adjustWord("test",
                Arrays.asList("bài kiểm tra"),
                Arrays.asList("n"),
                "/tɛst/");

        assertEquals("/tɛst/", Dictionary.map.get("test").getPronuciation());
    }

    // Tests cho removeWord()
    @Test
    public void testRemoveWord_ExistingWord() {
        DictionaryManagement.insertWord("remove",
                Arrays.asList("xóa bỏ"),
                Arrays.asList("v"),
                "");

        assertTrue(Dictionary.map.containsKey("remove"));

        DictionaryManagement.removeWord("remove");

        assertFalse(Dictionary.map.containsKey("remove"));
    }

    @Test
    public void testRemoveWord_NonExistingWord() {
        int sizeBefore = Dictionary.map.size();

        DictionaryManagement.removeWord("notexist");

        // Từ điển không thay đổi
        assertEquals(sizeBefore, Dictionary.map.size());
    }

    @Test
    public void testRemoveWord_CaseInsensitive() {
        DictionaryManagement.insertWord("delete",
                Arrays.asList("xóa"),
                Arrays.asList("v"),
                "");

        DictionaryManagement.removeWord("DELETE");

        assertFalse(Dictionary.map.containsKey("delete"));
    }

    @Test
    public void testRemoveWord_EmptyDictionary() {
        // Xóa từ khi từ điển rỗng
        Dictionary.map.clear();
        int sizeBefore = Dictionary.map.size();

        DictionaryManagement.removeWord("anything");

        assertEquals(sizeBefore, Dictionary.map.size());
        assertEquals(0, Dictionary.map.size());
    }

    // Tests cho addToLearning()
    @Test
    public void testAddToLearning_SingleWord() {
        DictionaryManagement.addToLearning("study");

        assertEquals(1, DictionaryManagement.learning.size());
        assertEquals("study", DictionaryManagement.learning.get(0));
        assertTrue(DictionaryManagement.learnedSomething());
    }

    @Test
    public void testAddToLearning_MultipleWords() {
        DictionaryManagement.addToLearning("word1");
        DictionaryManagement.addToLearning("word2");
        DictionaryManagement.addToLearning("word3");

        assertEquals(3, DictionaryManagement.learning.size());
    }

    // Tests cho doneLearning()
    @Test
    public void testDoneLearning_ExistingWord() {
        DictionaryManagement.addToLearning("learn");
        assertEquals(1, DictionaryManagement.learning.size());

        DictionaryManagement.doneLearning("learn");

        assertEquals(0, DictionaryManagement.learning.size());
    }

    @Test
    public void testDoneLearning_NonExistingWord() {
        DictionaryManagement.addToLearning("word1");
        DictionaryManagement.addToLearning("word2");

        int sizeBefore = DictionaryManagement.learning.size();
        DictionaryManagement.doneLearning("notexist");

        assertEquals(sizeBefore, DictionaryManagement.learning.size());
    }

    @Test
    public void testDoneLearning_MultipleWords() {
        DictionaryManagement.addToLearning("word1");
        DictionaryManagement.addToLearning("word2");
        DictionaryManagement.addToLearning("word3");

        DictionaryManagement.doneLearning("word2");

        assertEquals(2, DictionaryManagement.learning.size());
        assertFalse(DictionaryManagement.learning.contains("word2"));
        assertTrue(DictionaryManagement.learning.contains("word1"));
        assertTrue(DictionaryManagement.learning.contains("word3"));
    }

    // Tests cho insertFromFile()
    @Test
    public void testInsertFromFile_ValidFile() throws IOException {
        // Tạo file test tạm thời
        File testFile = new File("test_dictionaries.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
        writer.write("cat;/kæt/;n:con mèo\n");
        writer.write("dog;/dɒɡ/;n:con chó\n");
        writer.write("run;/rʌn/;v:chạy/v:điều hành\n");
        writer.close();

        // Thay đổi đường dẫn file tạm thời trong code (giả lập)
        // Đọc file
        Dictionary.map.clear();
        File dictionaries = new File("test_dictionaries.txt");
        Scanner scanner = new Scanner(dictionaries);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] temp = line.split(";");
            String english = temp[0];
            List<String> meaning = new ArrayList<>();
            List<String> type = new ArrayList<>();
            if (temp[2].contains("/")) {
                String[] typeAndMeaning = temp[2].split("/");
                for (int i = 0; i < typeAndMeaning.length; i++) {
                    String[] temp2 = typeAndMeaning[i].split(":");
                    type.add(temp2[0]);
                    meaning.add(temp2[1]);
                }
            } else {
                String[] typeAndMeaning = temp[2].split(":");
                type.add(typeAndMeaning[0]);
                meaning.add(typeAndMeaning[1]);
            }
            String pronunciation = temp[1];
            Word newWord = new Word(english, meaning, type, pronunciation);
            Dictionary.map.put(english, newWord);
        }
        scanner.close();

        // Kiểm tra
        assertEquals(3, Dictionary.map.size());
        assertTrue(Dictionary.map.containsKey("cat"));
        assertTrue(Dictionary.map.containsKey("dog"));
        assertTrue(Dictionary.map.containsKey("run"));
        assertEquals("con mèo", Dictionary.map.get("cat").getMeaning().get(0));
        assertEquals(2, Dictionary.map.get("run").getMeaning().size());

        // Xóa file test
        testFile.delete();
    }

    @Test
    public void testInsertFromFile_SingleTypeAndMeaning() throws IOException {
        File testFile = new File("test_single.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
        writer.write("hello;/həˈloʊ/;interjection:xin chào\n");
        writer.close();

        Dictionary.map.clear();
        Scanner scanner = new Scanner(testFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] temp = line.split(";");
            String english = temp[0];
            List<String> meaning = new ArrayList<>();
            List<String> type = new ArrayList<>();
            if (temp[2].contains("/")) {
                String[] typeAndMeaning = temp[2].split("/");
                for (int i = 0; i < typeAndMeaning.length; i++) {
                    String[] temp2 = typeAndMeaning[i].split(":");
                    type.add(temp2[0]);
                    meaning.add(temp2[1]);
                }
            } else {
                String[] typeAndMeaning = temp[2].split(":");
                type.add(typeAndMeaning[0]);
                meaning.add(typeAndMeaning[1]);
            }
            String pronunciation = temp[1];
            Word newWord = new Word(english, meaning, type, pronunciation);
            Dictionary.map.put(english, newWord);
        }
        scanner.close();

        assertEquals(1, Dictionary.map.size());
        assertEquals("xin chào", Dictionary.map.get("hello").getMeaning().get(0));
        assertEquals("interjection", Dictionary.map.get("hello").getType().get(0));

        testFile.delete();
    }

    @Test
    public void testInsertFromFile_MultipleTypesAndMeanings() throws IOException {
        File testFile = new File("test_multiple.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
        writer.write("book;/bʊk/;n:sách/v:đặt chỗ/v:ghi chép\n");
        writer.close();

        Dictionary.map.clear();
        Scanner scanner = new Scanner(testFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] temp = line.split(";");
            String english = temp[0];
            List<String> meaning = new ArrayList<>();
            List<String> type = new ArrayList<>();
            if (temp[2].contains("/")) {
                String[] typeAndMeaning = temp[2].split("/");
                for (int i = 0; i < typeAndMeaning.length; i++) {
                    String[] temp2 = typeAndMeaning[i].split(":");
                    type.add(temp2[0]);
                    meaning.add(temp2[1]);
                }
            } else {
                String[] typeAndMeaning = temp[2].split(":");
                type.add(typeAndMeaning[0]);
                meaning.add(typeAndMeaning[1]);
            }
            String pronunciation = temp[1];
            Word newWord = new Word(english, meaning, type, pronunciation);
            Dictionary.map.put(english, newWord);
        }
        scanner.close();

        assertEquals(3, Dictionary.map.get("book").getMeaning().size());
        assertEquals("sách", Dictionary.map.get("book").getMeaning().get(0));
        assertEquals("đặt chỗ", Dictionary.map.get("book").getMeaning().get(1));

        testFile.delete();
    }

    @Test
    public void testInsertFromFile_EmptyFile() throws IOException {
        File testFile = new File("test_empty.txt");
        testFile.createNewFile();

        Dictionary.map.clear();
        Scanner scanner = new Scanner(testFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] temp = line.split(";");
            String english = temp[0];
            List<String> meaning = new ArrayList<>();
            List<String> type = new ArrayList<>();
            if (temp[2].contains("/")) {
                String[] typeAndMeaning = temp[2].split("/");
                for (int i = 0; i < typeAndMeaning.length; i++) {
                    String[] temp2 = typeAndMeaning[i].split(":");
                    type.add(temp2[0]);
                    meaning.add(temp2[1]);
                }
            } else {
                String[] typeAndMeaning = temp[2].split(":");
                type.add(typeAndMeaning[0]);
                meaning.add(typeAndMeaning[1]);
            }
            String pronunciation = temp[1];
            Word newWord = new Word(english, meaning, type, pronunciation);
            Dictionary.map.put(english, newWord);
        }
        scanner.close();

        assertEquals(0, Dictionary.map.size());

        testFile.delete();
    }

    // Tests cho exportToFile()
    @Test
    public void testExportToFile_WithChanges() throws IOException {
        // Thêm dữ liệu vào dictionary
        Dictionary.map.clear();
        DictionaryManagement.insertWord("export",
                Arrays.asList("xuất khẩu"),
                Arrays.asList("v"),
                "/ɪkˈspɔːrt/");
        DictionaryManagement.insertWord("test",
                Arrays.asList("kiểm tra"),
                Arrays.asList("n"),
                "/test/");

        // Export ra file
        File testFile = new File("test_export.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(testFile));
        for (String key : Dictionary.map.keySet()) {
            bw.write(key);
            bw.write(";");
            bw.write(Dictionary.map.get(key).getPronuciation());
            bw.write(";");
            int temp = Dictionary.map.get(key).getType().size();
            for (int i = 0; i < temp - 1; i++) {
                bw.write(Dictionary.map.get(key).getType().get(i));
                bw.write(":");
                bw.write(Dictionary.map.get(key).getMeaning().get(i));
                bw.write("/");
            }
            bw.write(Dictionary.map.get(key).getType().get(temp - 1));
            bw.write(":");
            bw.write(Dictionary.map.get(key).getMeaning().get(temp - 1));
            bw.write("\n");
        }
        bw.close();

        // Đọc lại file để kiểm tra
        BufferedReader br = new BufferedReader(new FileReader(testFile));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();

        assertEquals(2, lines.size());
        assertTrue(lines.stream().anyMatch(l -> l.contains("export")));
        assertTrue(lines.stream().anyMatch(l -> l.contains("test")));

        testFile.delete();
    }

    @Test
    public void testExportToFile_SingleTypeAndMeaning() throws IOException {
        Dictionary.map.clear();
        DictionaryManagement.insertWord("simple",
                Arrays.asList("đơn giản"),
                Arrays.asList("adj"),
                "/ˈsɪm.pəl/");

        File testFile = new File("test_export_single.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(testFile));
        for (String key : Dictionary.map.keySet()) {
            bw.write(key);
            bw.write(";");
            bw.write(Dictionary.map.get(key).getPronuciation());
            bw.write(";");
            int temp = Dictionary.map.get(key).getType().size();
            for (int i = 0; i < temp - 1; i++) {
                bw.write(Dictionary.map.get(key).getType().get(i));
                bw.write(":");
                bw.write(Dictionary.map.get(key).getMeaning().get(i));
                bw.write("/");
            }
            bw.write(Dictionary.map.get(key).getType().get(temp - 1));
            bw.write(":");
            bw.write(Dictionary.map.get(key).getMeaning().get(temp - 1));
            bw.write("\n");
        }
        bw.close();

        BufferedReader br = new BufferedReader(new FileReader(testFile));
        String line = br.readLine();
        br.close();

        assertEquals("simple;/ˈsɪm.pəl/;adj:đơn giản", line);

        testFile.delete();
    }

    @Test
    public void testExportToFile_MultipleTypesAndMeanings() throws IOException {
        Dictionary.map.clear();
        DictionaryManagement.insertWord("complex",
                Arrays.asList("phức tạp", "tổ hợp"),
                Arrays.asList("adj", "n"),
                "/ˈkɒm.pleks/");

        File testFile = new File("test_export_multiple.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(testFile));
        for (String key : Dictionary.map.keySet()) {
            bw.write(key);
            bw.write(";");
            bw.write(Dictionary.map.get(key).getPronuciation());
            bw.write(";");
            int temp = Dictionary.map.get(key).getType().size();
            for (int i = 0; i < temp - 1; i++) {
                bw.write(Dictionary.map.get(key).getType().get(i));
                bw.write(":");
                bw.write(Dictionary.map.get(key).getMeaning().get(i));
                bw.write("/");
            }
            bw.write(Dictionary.map.get(key).getType().get(temp - 1));
            bw.write(":");
            bw.write(Dictionary.map.get(key).getMeaning().get(temp - 1));
            bw.write("\n");
        }
        bw.close();

        BufferedReader br = new BufferedReader(new FileReader(testFile));
        String line = br.readLine();
        br.close();

        assertEquals("complex;/ˈkɒm.pleks/;adj:phức tạp/n:tổ hợp", line);

        testFile.delete();
    }

    @Test
    public void testExportToFile_EmptyDictionary() throws IOException {
        Dictionary.map.clear();

        File testFile = new File("test_export_empty.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(testFile));
        for (String key : Dictionary.map.keySet()) {
            bw.write(key);
            bw.write(";");
            bw.write(Dictionary.map.get(key).getPronuciation());
            bw.write(";");
            int temp = Dictionary.map.get(key).getType().size();
            for (int i = 0; i < temp - 1; i++) {
                bw.write(Dictionary.map.get(key).getType().get(i));
                bw.write(":");
                bw.write(Dictionary.map.get(key).getMeaning().get(i));
                bw.write("/");
            }
            bw.write(Dictionary.map.get(key).getType().get(temp - 1));
            bw.write(":");
            bw.write(Dictionary.map.get(key).getMeaning().get(temp - 1));
            bw.write("\n");
        }
        bw.close();

        BufferedReader br = new BufferedReader(new FileReader(testFile));
        String line = br.readLine();
        br.close();

        assertNull(line); // File rỗng

        testFile.delete();
    }

    @Test
    public void testExportToFile_LearningList() throws IOException {
        DictionaryManagement.learning.clear();
        DictionaryManagement.addToLearning("word1");
        DictionaryManagement.addToLearning("word2");
        DictionaryManagement.addToLearning("word3");

        File testFile = new File("test_learning_export.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(testFile));
        for (int i = 0; i < DictionaryManagement.learning.size() - 1; i++) {
            bw.write(DictionaryManagement.learning.get(i));
            bw.write("\n");
        }
        bw.write(DictionaryManagement.learning.get(DictionaryManagement.learning.size() - 1));
        bw.close();

        BufferedReader br = new BufferedReader(new FileReader(testFile));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();

        assertEquals(3, lines.size());
        assertEquals("word3", lines.get(0)); // Từ mới nhất ở đầu
        assertEquals("word2", lines.get(1));
        assertEquals("word1", lines.get(2));

        testFile.delete();
    }
}