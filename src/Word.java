import java.util.ArrayList;
import java.util.List;

public class Word implements Comparable<Word> {
    private String english;
    private List<String> meaning = new ArrayList<>();
    private List<String> type = new ArrayList<>();
    private String pronuciation;

    public String getEnglish() {
        return english;
    }

    public List<String> getMeaning() {
        return meaning;
    }

    public List<String> getType() {
        return type;
    }

    public String getPronuciation() {
        return pronuciation;
    }

    public Word(String english, List<String> meaning, List<String> type, String pronunciation) {
        this.english = english.toLowerCase();
        this.meaning = meaning;
        this.type = type;
        this.pronuciation = pronunciation;
    }
    
    /**
     * Compare two words alphabetically base on the english word
     */
    public int compareTo(Word o) {
        return this.english.compareTo(o.english);
    }
}