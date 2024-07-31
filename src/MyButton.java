import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

public class MyButton extends JButton {
    /**
     * Text constructor.
     * 
     * @param x 
     * @param y
     * @param width
     * @param height
     * @param text
     * @param font
     * @param visible
     * @param usable
     * @param xAlignment
     * @param yAlignment
     * @param background
     * @param border
     */
    public MyButton(int x, int y, int width, int height, String text, Font font, boolean visible,
            boolean usable, int xAlignment, int yAlignment, Color background, Border border) {
        super.setBounds(x, y, width, height);
        super.setText(text);
        super.setFont(font);
        super.setVisible(visible);
        super.setEnabled(usable);
        super.setBackground(background);
        super.setHorizontalAlignment(xAlignment);
        super.setVerticalAlignment(yAlignment);
        super.setBorder(border);
    }

    /**
     * Image constructor.
     * 
     * @param x
     * @param y
     * @param width
     * @param length
     * @param visible
     * @param usable
     * @param xAlignment
     * @param yAlignment
     * @param icon
     * @param background
     * @param border
     */
    public MyButton(int x, int y, int width, int height, boolean visible, boolean usable,
            int xAlignment, int yAlignment, ImageIcon icon, Color background, Border border) {
        super.setBounds(x, y, width, height);
        super.setVisible(visible);
        super.setEnabled(usable);
        super.setBackground(background);
        super.setHorizontalAlignment(xAlignment);
        super.setVerticalAlignment(yAlignment);
        super.setIcon(icon);
        super.setBorder(border);
    }

    public MyButton(int x, int y, int width, int height, String text, Font font, ImageIcon icon, boolean visible,
            boolean usable, int xAlignment, int yAlignment, int xTextPosition, int yTextPosition, 
            Color background, Border border) {
        super.setBounds(x, y, width, height);
        super.setVisible(visible);
        super.setEnabled(usable);
        super.setIcon(icon);
        super.setText(text);
        super.setFont(font);
        super.setBackground(background);
        super.setHorizontalAlignment(xAlignment);
        super.setVerticalAlignment(yAlignment);
        super.setHorizontalTextPosition(xTextPosition);
        super.setVerticalTextPosition(yTextPosition);
        super.setBorder(border);
    }
}