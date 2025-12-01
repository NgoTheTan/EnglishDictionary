import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryApplicationTest {

    @BeforeEach
    public void setUp() {
        Dictionary.map.clear();
        // Thêm một số từ mẫu
        DictionaryManagement.insertWord("apple",
                Arrays.asList("táo"),
                Arrays.asList("n"),
                "/ˈæp.əl/");
        DictionaryManagement.insertWord("application",
                Arrays.asList("ứng dụng"),
                Arrays.asList("n"),
                "");
        DictionaryManagement.insertWord("apply",
                Arrays.asList("áp dụng"),
                Arrays.asList("v"),
                "");
    }

    @AfterEach
    public void tearDown() {
        Dictionary.map.clear();
    }

    // Tests cho appSearcher()
    @Test
    public void testAppSearcher_ValidPrefix() {
        List<String> results = DictionaryApplication.appSearcher("app");

        assertEquals(3, results.size());
        assertTrue(results.contains("apple"));
        assertTrue(results.contains("application"));
        assertTrue(results.contains("apply"));
    }

    @Test
    public void testAppSearcher_SingleMatch() {
        List<String> results = DictionaryApplication.appSearcher("apple");

        assertEquals(1, results.size());
        assertEquals("apple", results.get(0));
    }

    @Test
    public void testAppSearcher_NoMatch() {
        List<String> results = DictionaryApplication.appSearcher("xyz");

        assertEquals(0, results.size());
    }

    @Test
    public void testAppSearcher_EmptyInput() {
        List<String> results = DictionaryApplication.appSearcher("");

        // Với input rỗng, trả về tất cả từ
        assertEquals(Dictionary.map.size(), results.size());
    }

    @Test
    public void testAppSearcher_NullInput() {
        List<String> results = DictionaryApplication.appSearcher(null);

        assertEquals(0, results.size());
    }

    @Test
    public void testAppSearcher_ResultsSorted() {
        DictionaryManagement.insertWord("banana",
                Arrays.asList("chuối"),
                Arrays.asList("n"),
                "");
        DictionaryManagement.insertWord("back",
                Arrays.asList("lưng"),
                Arrays.asList("n"),
                "");
        DictionaryManagement.insertWord("ball",
                Arrays.asList("quả bóng"),
                Arrays.asList("n"),
                "");

        List<String> results = DictionaryApplication.appSearcher("b");

        // Kết quả phải được sắp xếp
        assertEquals("back", results.get(0));
        assertEquals("ball", results.get(1));
        assertEquals("banana", results.get(2));
    }

    @Test
    public void testAppSearcher_CaseSensitive() {
        List<String> results = DictionaryApplication.appSearcher("APP");

        // Tìm kiếm có phân biệt hoa thường, không nên tìm thấy
        assertEquals(0, results.size());
    }

    // Tests cho NOT_ALPHABET Set

    @Test
    public void testNotAlphabet_ContainsNumbers() {
        assertTrue(DictionaryApplication.NOT_ALPHABET.contains('1'));
        assertTrue(DictionaryApplication.NOT_ALPHABET.contains('5'));
        assertTrue(DictionaryApplication.NOT_ALPHABET.contains('0'));
    }

    @Test
    public void testNotAlphabet_ContainsSpecialChars() {
        assertTrue(DictionaryApplication.NOT_ALPHABET.contains('!'));
        assertTrue(DictionaryApplication.NOT_ALPHABET.contains('@'));
        assertTrue(DictionaryApplication.NOT_ALPHABET.contains('#'));
    }

    @Test
    public void testNotAlphabet_ContainsVietnameseChars() {
        assertTrue(DictionaryApplication.NOT_ALPHABET.contains('ă'));
        assertTrue(DictionaryApplication.NOT_ALPHABET.contains('ê'));
        assertTrue(DictionaryApplication.NOT_ALPHABET.contains('ơ'));
    }

    @Test
    public void testNotAlphabet_NotContainsEnglishLetters() {
        assertFalse(DictionaryApplication.NOT_ALPHABET.contains('a'));
        assertFalse(DictionaryApplication.NOT_ALPHABET.contains('Z'));
        assertFalse(DictionaryApplication.NOT_ALPHABET.contains('m'));
    }
}