import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyLabel extends JLabel {
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
            int yAlignment, Color background) {
        super.setBounds(x, y, width, height);
        super.setText(text);
        super.setFont(font);
        super.setForeground(textColor);
        super.setVerticalAlignment(yAlignment);
        super.setHorizontalAlignment(xAlignment);
        super.setBackground(background);
        super.setOpaque(true);
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

    /**
     * Text and image constructor.
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     * @param text
     * @param font
     * @param textColor
     * @param xAlignment
     * @param yAlignment
     * @param xTextPosition
     * @param yTextPosition
     * @param icon
     * @param background
     */
    public MyLabel(int x, int y, int width, int height, String text, Font font, Color textColor,
            int xAlignment, int yAlignment, int xTextPosition, int yTextPosition, ImageIcon icon, Color background) {
        super.setBounds(x, y, width, height);
        super.setText(text);
        super.setFont(font);
        super.setForeground(textColor);
        super.setIcon(icon);
        super.setHorizontalTextPosition(xTextPosition);
        super.setVerticalTextPosition(yTextPosition);
        super.setHorizontalAlignment(xAlignment);
        super.setVerticalAlignment(yAlignment);
        super.setBackground(background);
        super.setOpaque(true);
    }
}