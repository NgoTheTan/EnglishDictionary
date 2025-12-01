import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MyPanelTest {
    // PHÂN HOẠCH TƯƠNG ĐƯƠNG CHO CONSTRUCTOR
    // Tham số x, y (vị trí)
    @Test
    void testConstructor_PositiveXY() {
        MyPanel panel = new MyPanel(100, 50, 200, 100,
                Color.WHITE, null, true);

        assertEquals(100, panel.getX());
        assertEquals(50, panel.getY());
    }

    @Test
    void testConstructor_ZeroXY() {
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                Color.WHITE, null, true);

        assertEquals(0, panel.getX());
        assertEquals(0, panel.getY());
    }

    @Test
    void testConstructor_NegativeXY() {
        MyPanel panel = new MyPanel(-50, -30, 200, 100,
                Color.WHITE, null, true);

        assertEquals(-50, panel.getX());
        assertEquals(-30, panel.getY());
    }

    // Tham số width, height (kích thước)

    @Test
    void testConstructor_PositiveSmallWidthHeight() {
        MyPanel panel = new MyPanel(0, 0, 10, 10,
                Color.WHITE, null, true);

        assertEquals(10, panel.getWidth());
        assertEquals(10, panel.getHeight());
    }

    @Test
    void testConstructor_PositiveLargeWidthHeight() {
        MyPanel panel = new MyPanel(0, 0, 1920, 1080,
                Color.WHITE, null, true);

        assertEquals(1920, panel.getWidth());
        assertEquals(1080, panel.getHeight());
    }

    @Test
    void testConstructor_ZeroWidthHeight() {
        MyPanel panel = new MyPanel(0, 0, 0, 0,
                Color.WHITE, null, true);

        assertEquals(0, panel.getWidth());
        assertEquals(0, panel.getHeight());
    }

    @Test
    void testConstructor_NegativeWidthHeight() {
        MyPanel panel = new MyPanel(0, 0, -100, -50,
                Color.WHITE, null, true);

        assertEquals(-100, panel.getWidth());
        assertEquals(-50, panel.getHeight());
    }

    // tham số color (màu nền)

    @Test
    void testConstructor_NullColor() {
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                null, null, true);

        assertNull(panel.getBackground());
    }

    @Test
    void testConstructor_WhiteColor() {
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                Color.WHITE, null, true);

        assertEquals(Color.WHITE, panel.getBackground());
    }

    @Test
    void testConstructor_BlackColor() {
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                Color.BLACK, null, true);

        assertEquals(Color.BLACK, panel.getBackground());
    }

    @Test
    void testConstructor_RedColor() {
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                Color.RED, null, true);

        assertEquals(Color.RED, panel.getBackground());
    }

    @Test
    void testConstructor_CustomColor() {
        Color custom = new Color(128, 64, 192);
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                custom, null, true);

        assertEquals(custom, panel.getBackground());
    }

    @Test
    void testConstructor_TransparentColor() {
        Color transparent = new Color(255, 0, 0, 128);
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                transparent, null, true);

        assertEquals(transparent, panel.getBackground());
    }

    // tham số layoutManager

    @Test
    void testConstructor_NullLayout() {
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                Color.WHITE, null, true);

        assertNull(panel.getLayout());
    }

    @Test
    void testConstructor_FlowLayout() {
        FlowLayout layout = new FlowLayout();
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                Color.WHITE, layout, true);

        assertEquals(layout, panel.getLayout());
    }

    @Test
    void testConstructor_BorderLayout() {
        BorderLayout layout = new BorderLayout();
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                Color.WHITE, layout, true);

        assertEquals(layout, panel.getLayout());
    }

    @Test
    void testConstructor_GridLayout() {
        GridLayout layout = new GridLayout(2, 3);
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                Color.WHITE, layout, true);

        assertEquals(layout, panel.getLayout());
    }

    @Test
    void testConstructor_BoxLayout() {
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                Color.WHITE, null, true);
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        assertEquals(layout, panel.getLayout());
    }

    @Test
    void testConstructor_FlowLayoutWithParameters() {
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 10, 5);
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                Color.WHITE, layout, true);

        assertEquals(layout, panel.getLayout());
    }

    // tham số visible

    @Test
    void testConstructor_VisibleTrue() {
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                Color.WHITE, null, true);

        assertTrue(panel.isVisible());
    }

    @Test
    void testConstructor_VisibleFalse() {
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                Color.WHITE, null, false);

        assertFalse(panel.isVisible());
    }

    // phân hoạch tương đương cho setPanelEnabledAndVisible()
    // test với panel rỗng (không có component)
    @Test
    void testSetPanelEnabledAndVisible_EmptyPanel_SetTrue() {
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                Color.WHITE, null, false);
        panel.setEnabled(false);

        panel.setPanelEnabledAndVisible(true);

        assertTrue(panel.isEnabled());
        assertTrue(panel.isVisible());
    }

    @Test
    void testSetPanelEnabledAndVisible_EmptyPanel_SetFalse() {
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                Color.WHITE, null, true);
        panel.setEnabled(true);

        panel.setPanelEnabledAndVisible(false);

        assertFalse(panel.isEnabled());
        assertFalse(panel.isVisible());
    }

    // test với một component
    @Test
    void testSetPanelEnabledAndVisible_OneJButton_SetTrue() {
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                Color.WHITE, null, false);
        JButton button = new JButton("Test");
        button.setEnabled(false);
        button.setVisible(false);
        panel.add(button);

        panel.setPanelEnabledAndVisible(true);

        assertTrue(panel.isEnabled());
        assertTrue(panel.isVisible());
        assertTrue(button.isEnabled());
        assertTrue(button.isVisible());
    }

    @Test
    void testSetPanelEnabledAndVisible_OneJButton_SetFalse() {
        MyPanel panel = new MyPanel(0, 0, 200, 100,
                Color.WHITE, null, true);
        JButton button = new JButton("Test");
        button.setEnabled(true);
        button.setVisible(true);
        panel.add(button);

        panel.setPanelEnabledAndVisible(false);

        assertFalse(panel.isEnabled());
        assertFalse(panel.isVisible());
        assertFalse(button.isEnabled());
        assertFalse(button.isVisible());
    }

    // test với nhiều component
    @Test
    void testSetPanelEnabledAndVisible_MultipleComponents_SetTrue() {
        MyPanel panel = new MyPanel(0, 0, 400, 200,
                Color.WHITE, null, false);

        JButton button = new JButton("Button");
        JLabel label = new JLabel("Label");
        MyTextField textField = new MyTextField(0, 0, 100, 30,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, null);
        textField.setText("Test Text");

        button.setEnabled(false);
        button.setVisible(false);
        label.setEnabled(false);
        label.setVisible(false);
        textField.setEnabled(false);
        textField.setVisible(false);

        panel.add(button);
        panel.add(label);
        panel.add(textField);

        panel.setPanelEnabledAndVisible(true);

        assertTrue(panel.isEnabled());
        assertTrue(panel.isVisible());
        assertTrue(button.isEnabled());
        assertTrue(button.isVisible());
        assertTrue(label.isEnabled());
        assertTrue(label.isVisible());
        assertTrue(textField.isEnabled());
        assertTrue(textField.isVisible());
    }

    @Test
    void testSetPanelEnabledAndVisible_MultipleComponents_SetFalse() {
        MyPanel panel = new MyPanel(0, 0, 400, 200,
                Color.WHITE, null, true);

        JButton button = new JButton("Button");
        JLabel label = new JLabel("Label");
        MyTextField textField = new MyTextField(0, 0, 100, 30,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, null);
        textField.setText("Test Text");

        button.setEnabled(true);
        button.setVisible(true);
        label.setEnabled(true);
        label.setVisible(true);
        textField.setEnabled(true);
        textField.setVisible(true);

        panel.add(button);
        panel.add(label);
        panel.add(textField);

        panel.setPanelEnabledAndVisible(false);

        assertFalse(panel.isEnabled());
        assertFalse(panel.isVisible());
        assertFalse(button.isEnabled());
        assertFalse(button.isVisible());
        assertFalse(label.isEnabled());
        assertFalse(label.isVisible());
        assertFalse(textField.isEnabled());
        assertFalse(textField.isVisible());
    }

    @Test
    void testSetPanelEnabledAndVisible_MultipleMyTextFields() {
        MyPanel panel = new MyPanel(0, 0, 400, 200,
                Color.WHITE, null, true);

        MyTextField field1 = new MyTextField(0, 0, 100, 30,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, null);
        MyTextField field2 = new MyTextField(0, 30, 100, 30,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, null);
        MyTextField field3 = new MyTextField(0, 60, 100, 30,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, null);

        field1.setText("Text 1");
        field2.setText("Text 2");
        field3.setText("Text 3");

        panel.add(field1);
        panel.add(field2);
        panel.add(field3);

        panel.setPanelEnabledAndVisible(true);
    }

    @Test
    void testSetPanelEnabledAndVisible_MixedComponents() {
        MyPanel panel = new MyPanel(0, 0, 400, 200,
                Color.WHITE, null, true);

        JButton button1 = new JButton("Button 1");
        MyTextField textField1 = new MyTextField(0, 0, 100, 30,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, null);
        JLabel label = new JLabel("Label");
        MyTextField textField2 = new MyTextField(0, 30, 100, 30,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, null);
        JButton button2 = new JButton("Button 2");

        textField1.setText("Field 1 Text");
        textField2.setText("Field 2 Text");

        panel.add(button1);
        panel.add(textField1);
        panel.add(label);
        panel.add(textField2);
        panel.add(button2);

        panel.setPanelEnabledAndVisible(false);

        assertFalse(button1.isEnabled());
        assertFalse(button1.isVisible());
        assertFalse(textField1.isEnabled());
        assertFalse(textField1.isVisible());
        assertFalse(label.isEnabled());
        assertFalse(label.isVisible());
        assertFalse(textField2.isEnabled());
        assertFalse(textField2.isVisible());
        assertFalse(button2.isEnabled());
        assertFalse(button2.isVisible());
    }
}