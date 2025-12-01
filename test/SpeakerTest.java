import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpeakerTest {
    @Test
    void testPronounce_ValidWord() {
        assertDoesNotThrow(() -> Speaker.pronounce("hello"));
    }

    @Test
    void testPronounce_EmptyString() {
        assertDoesNotThrow(() -> Speaker.pronounce(""));
    }

    @Test
    void testPronounce_SpecialCharacters() {
        assertDoesNotThrow(() -> Speaker.pronounce("@#$%"));
    }

    @Test
    void testPronounce_LongString() {
        String longWord = "a".repeat(1000);
        assertDoesNotThrow(() -> Speaker.pronounce(longWord));
    }
}