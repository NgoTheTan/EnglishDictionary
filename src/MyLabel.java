import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyLabel extends JLabel {
    /**
     * Constructor with colored background. 
     * 
     * @param x          x.
     * @param y          y.
     * @param width      width.
     * @param height     height.
     * @param background background color.
     */
    public MyLabel(int x, int y, int width, int height, Color background) {
        super.setBounds(x, y, width, height);
        super.setBackground(background);
        super.setOpaque(true);
    }

    /**
     * Text constructor.
     * 
     * @param x          x.
     * @param y          y.
     * @param width      width.
     * @param height     height.
     * @param text       String.
     * @param font       Font.
     * @param textColor  Color.
     * @param xAlignment horizontal alignment.
     * @param yAlignment vertical alignment.
     */
    public MyLabel(int x, int y, int width, int height, String text, Font font, Color textColor, int xAlignment,
            int yAlignment) {
        super.setBounds(x, y, width, height);
        super.setText(text);
        super.setFont(font);
        super.setForeground(textColor);
        super.setVerticalAlignment(yAlignment);
        super.setHorizontalAlignment(xAlignment);
    }

    /**
     * Image constructor.
     * 
     * @param x          x.
     * @param y          y.
     * @param width      width.
     * @param height     height.
     * @param xAlignment horizontal alignment.
     * @param yAlignment vertical alignment.
     * @param icon       Image.
     */
    public MyLabel(int x, int y, int width, int height, int xAlignment, int yAlignment, ImageIcon icon) {
        super.setBounds(x, y, width, height);
        super.setHorizontalAlignment(xAlignment);
        super.setVerticalAlignment(yAlignment);
        super.setIcon(icon);
    }
}