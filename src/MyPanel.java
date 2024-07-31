import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class MyPanel extends JPanel{
    public MyPanel(int x, int y, int width, int height, Color background, LayoutManager layout, boolean visible, boolean opaque) {
        super.setBounds(x, y, width, height);
        super.setBackground(background);
        super.setVisible(visible);
        super.setLayout(layout);
        super.setOpaque(opaque);
    }

    /**
     * Set all the components' enability and visibility in the panel.
     * 
     * @param set
     */
    public void setPanelEnabledAndVisible(boolean set) {
        this.setEnabled(set);
        this.setVisible(set);
        for (Component c : this.getComponents()) {
            c.setEnabled(set);
            c.setVisible(set);
            if (c instanceof MyTextField) {
                MyTextField t = (MyTextField) c;
                t.setText(null);
            }
        }
    }
}