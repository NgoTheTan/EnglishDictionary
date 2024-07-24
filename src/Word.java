public class Word {
    private String english;
    private String meaning;

    /**
     * Get the english word.
     * 
     * @return String english.
     */
    public String getEnglish() {
        return english;
    }

    /**
     * Get the meaning.
     * 
     * @return String meaning.
     */
    public String getMeaning() {
        return meaning;
    }

    /**
     * Constructor.
     * 
     * @param english The english word.
     * @param meaning The meaning of the word.
     */
    public Word(String english, String meaning) {
        this.english = english.toLowerCase();
        this.meaning = meaning.toLowerCase();
    }
}