import java.util.ArrayList;
import java.util.List;

public class Word implements Comparable<Word> {
    private String english;
    private List<String> meaning = new ArrayList<>();
    private List<String> type = new ArrayList<>();
    private String pronuciation;

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
    public List<String> getMeaning() {
        return meaning;
    }

    public List<String> getType() {
        return type;
    }

    public String getPronuciation() {
        return pronuciation;
    }
    /**
     * Constructor.
     * 
     * @param english The english word.
     * @param meaning The meaning of the word.
     */
    public Word(String english, List<String> meaning, List<String> type, String pronunciation) {
        this.english = english.toLowerCase();
        this.meaning = meaning;
        this.type = type;
        this.pronuciation = pronunciation;
    }
    
    public int compareTo(Word o) {
        return this.english.compareTo(o.english);
    }
}