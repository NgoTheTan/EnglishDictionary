import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Speaker {
    /**
     * Pronounce the word.
     * 
     * @param word
     */
    public static void pronounce(String word) {
        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            Voice voice = VoiceManager.getInstance().getVoice("kevin16");
            voice.allocate();
            voice.speak(word);
            voice.deallocate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}