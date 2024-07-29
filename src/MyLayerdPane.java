import java.awt.Color;

import javax.swing.JLayeredPane;

public class MyLayerdPane extends JLayeredPane{
    public MyLayerdPane(int x, int y, int width, int height, Color background, boolean opaque) {
        super.setBounds(x, y, width, height);
        super.setBackground(background);
        super.setOpaque(opaque);
    }
}
