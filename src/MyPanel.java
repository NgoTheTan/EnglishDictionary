import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class MyPanel extends JPanel{
    public MyPanel(int x, int y, int width, int height, Color background, LayoutManager layout, boolean visible) {
        super.setBounds(x, y, width, height);
        super.setBackground(background);
        super.setVisible(visible);
    }
}
