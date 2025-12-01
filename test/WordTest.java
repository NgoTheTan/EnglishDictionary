import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordTest {

    @Test
    public void testWordConstructor_ValidInputs() {
        List<String> meanings = Arrays.asList("con mèo");
        List<String> types = Arrays.asList("n");
        Word word = new Word("cat", meanings, types, "/kæt/");

        assertEquals("cat", word.getEnglish());
        assertEquals(meanings, word.getMeaning());
        assertEquals(types, word.getType());
        assertEquals("/kæt/", word.getPronuciation());
    }

    @Test
    public void testWordConstructor_UppercaseInput() {
        List<String> meanings = Arrays.asList("con chó");
        List<String> types = Arrays.asList("n");
        Word word = new Word("DOG", meanings, types, "/dɒɡ/");

        // Kiểm tra chuyển thành lowercase
        assertEquals("dog", word.getEnglish());
    }

    @Test
    public void testWordConstructor_MultipleTypesAndMeanings() {
        List<String> meanings = Arrays.asList("chạy", "điều hành");
        List<String> types = Arrays.asList("v", "v");
        Word word = new Word("run", meanings, types, "/rʌn/");

        assertEquals(2, word.getMeaning().size());
        assertEquals(2, word.getType().size());
    }

    @Test
    public void testWordConstructor_EmptyPronunciation() {
        List<String> meanings = Arrays.asList("xin chào");
        List<String> types = Arrays.asList("interjection");
        Word word = new Word("hello", meanings, types, "");

        assertEquals("", word.getPronuciation());
    }

    @Test
    public void testCompareTo_FirstWordSmaller() {
        Word word1 = new Word("apple", Arrays.asList("táo"), Arrays.asList("n"), "");
        Word word2 = new Word("banana", Arrays.asList("chuối"), Arrays.asList("n"), "");

        assertTrue(word1.compareTo(word2) < 0);
    }

    @Test
    public void testCompareTo_FirstWordLarger() {
        Word word1 = new Word("zebra", Arrays.asList("ngựa vằn"), Arrays.asList("n"), "");
        Word word2 = new Word("apple", Arrays.asList("táo"), Arrays.asList("n"), "");

        assertTrue(word1.compareTo(word2) > 0);
    }

    @Test
    public void testCompareTo_EqualWords() {
        Word word1 = new Word("book", Arrays.asList("sách"), Arrays.asList("n"), "");
        Word word2 = new Word("book", Arrays.asList("cuốn sách"), Arrays.asList("n"), "");

        assertEquals(0, word1.compareTo(word2));
    }
}