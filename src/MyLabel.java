import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.JLabel;

public class MyLabel extends JLabel {
    /**
     * Text constructor.
     * 
     * @param text      String.
     * @param font      Font.
     * @param textColor Color.
     */
    public MyLabel(int x, int y, int width, int height, String text, Font font, Color textColor, int xAlignment, int yAlignment) {
        super.setBounds(x, y, width, height);
        super.setText(text);
        super.setFont(font);
        super.setForeground(textColor);
        super.setVerticalAlignment(yAlignment);
        super.setHorizontalAlignment(xAlignment);
    }
}