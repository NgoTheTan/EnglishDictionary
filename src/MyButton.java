import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MyButton extends JButton{
    public MyButton(int boundsX, int boundsY, int width, int length, String text, String tipText, 
    ActionListener action, boolean visible, boolean usable) {
        super.setBounds(boundsX, boundsY, width, length);
        super.setText(text);
        super.setToolTipText(tipText);
        super.addActionListener(action);
        super.setVisible(visible);
        super.setEnabled(usable);
    }
}