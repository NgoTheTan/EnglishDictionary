import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.border.Border;

public class MyTextField extends JTextField {
    public MyTextField(int x, int y, int width, int height, Font font, float xAlignment, float yAlignment, Border border) {
        super.setBounds(x, y, width, height);
        super.setFont(font);
        super.setAlignmentX(xAlignment);
        super.setAlignmentY(yAlignment);
        super.setBorder(border);
        super.setFocusTraversalKeysEnabled(false);
    }
}
