import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;

public class MyButtonTest {

    private Font testFont;
    private ImageIcon testIcon;
    private Border testBorder;
    private Border emptyBorder;

    @BeforeEach
    public void setUp() {
        testFont = new Font("Arial", Font.BOLD, 14);
        testIcon = new ImageIcon(new byte[0]);
        testBorder = new LineBorder(Color.BLACK, 2);
        emptyBorder = new EmptyBorder(5, 5, 5, 5);
    }

    // test constructor 1: TEXT ONLY

    @Test
    public void testTextConstructor_ValidPositiveCoordinates() {
        // tọa độ dương, kích thước dương, visible=true, usable=true
        MyButton button = new MyButton(10, 20, 100, 50, "Click Me", testFont,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                Color.BLUE, testBorder);

        assertEquals(10, button.getX());
        assertEquals(20, button.getY());
        assertEquals(100, button.getWidth());
        assertEquals(50, button.getHeight());
        assertEquals("Click Me", button.getText());
        assertEquals(testFont, button.getFont());
        assertTrue(button.isVisible());
        assertTrue(button.isEnabled());
        assertEquals(Color.BLUE, button.getBackground());
        assertEquals(SwingConstants.CENTER, button.getHorizontalAlignment());
        assertEquals(SwingConstants.CENTER, button.getVerticalAlignment());
        assertEquals(testBorder, button.getBorder());
    }

    @Test
    public void testTextConstructor_ZeroCoordinates() {
        // tọa độ = 0
        MyButton button = new MyButton(0, 0, 100, 50, "Button", testFont,
                true, true, SwingConstants.LEFT, SwingConstants.TOP,
                Color.RED, testBorder);

        assertEquals(0, button.getX());
        assertEquals(0, button.getY());
    }

    @Test
    public void testTextConstructor_NegativeCoordinates() {
        // tọa độ âm
        MyButton button = new MyButton(-10, -20, 100, 50, "Button", testFont,
                true, true, SwingConstants.RIGHT, SwingConstants.BOTTOM,
                Color.GREEN, testBorder);

        assertEquals(-10, button.getX());
        assertEquals(-20, button.getY());
    }

    @Test
    public void testTextConstructor_ZeroDimensions() {
        // kích thước = 0
        MyButton button = new MyButton(10, 10, 0, 0, "Button", testFont,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                Color.WHITE, testBorder);

        assertEquals(0, button.getWidth());
        assertEquals(0, button.getHeight());
    }

    @Test
    public void testTextConstructor_LargeDimensions() {
        // kích thước lớn
        MyButton button = new MyButton(0, 0, 800, 600, "Button", testFont,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                Color.GRAY, testBorder);

        assertEquals(800, button.getWidth());
        assertEquals(600, button.getHeight());
    }

    @Test
    public void testTextConstructor_InvisibleButton() {
        // visible = false
        MyButton button = new MyButton(10, 10, 100, 50, "Button", testFont,
                false, true, SwingConstants.CENTER, SwingConstants.CENTER,
                Color.BLUE, testBorder);

        assertFalse(button.isVisible());
        assertTrue(button.isEnabled());
    }

    @Test
    public void testTextConstructor_DisabledButton() {
        // usable = false
        MyButton button = new MyButton(10, 10, 100, 50, "Button", testFont,
                true, false, SwingConstants.CENTER, SwingConstants.CENTER,
                Color.BLUE, testBorder);

        assertTrue(button.isVisible());
        assertFalse(button.isEnabled());
    }

    @Test
    public void testTextConstructor_InvisibleAndDisabled() {
        // visible = false, usable = false
        MyButton button = new MyButton(10, 10, 100, 50, "Button", testFont,
                false, false, SwingConstants.CENTER, SwingConstants.CENTER,
                Color.BLUE, testBorder);

        assertFalse(button.isVisible());
        assertFalse(button.isEnabled());
    }

    @Test
    public void testTextConstructor_EmptyText() {
        // text rỗng
        MyButton button = new MyButton(10, 10, 100, 50, "", testFont,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                Color.BLUE, testBorder);

        assertEquals("", button.getText());
    }

    @Test
    public void testTextConstructor_NullText() {
        // text = null
        MyButton button = new MyButton(10, 10, 100, 50, null, testFont,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                Color.BLUE, testBorder);

        assertNull(button.getText());
    }

    @Test
    public void testTextConstructor_NullFont() {
        // font = null
        MyButton button = new MyButton(10, 10, 100, 50, "Button", null,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                Color.BLUE, testBorder);

        assertNull(button.getFont());
    }

    @Test
    public void testTextConstructor_NullBackground() {
        // background = null
        MyButton button = new MyButton(10, 10, 100, 50, "Button", testFont,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                null, testBorder);

        assertNull(button.getBackground());
    }

    @Test
    public void testTextConstructor_NullBorder() {
        // border = null
        MyButton button = new MyButton(10, 10, 100, 50, "Button", testFont,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                Color.BLUE, null);

        assertNull(button.getBorder());
    }

    @Test
    public void testTextConstructor_DifferentAlignments() {
        // các giá trị alignment khác nhau
        int[] hAlignments = {SwingConstants.LEFT, SwingConstants.CENTER, SwingConstants.RIGHT};
        int[] vAlignments = {SwingConstants.TOP, SwingConstants.CENTER, SwingConstants.BOTTOM};

        for (int hAlign : hAlignments) {
            for (int vAlign : vAlignments) {
                MyButton button = new MyButton(10, 10, 100, 50, "Button", testFont,
                        true, true, hAlign, vAlign, Color.BLUE, testBorder);

                assertEquals(hAlign, button.getHorizontalAlignment());
                assertEquals(vAlign, button.getVerticalAlignment());
            }
        }
    }

    @Test
    public void testTextConstructor_DifferentBorderTypes() {
        // các loại border khác nhau
        MyButton button1 = new MyButton(10, 10, 100, 50, "Button", testFont,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                Color.BLUE, testBorder);
        assertEquals(testBorder, button1.getBorder());

        MyButton button2 = new MyButton(10, 10, 100, 50, "Button", testFont,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                Color.BLUE, emptyBorder);
        assertEquals(emptyBorder, button2.getBorder());
    }

    // test constructor 2: IMAGE ONLY

    @Test
    public void testImageConstructor_ValidParameters() {
        // Phân hoạch: tất cả tham số hợp lệ
        MyButton button = new MyButton(15, 25, 80, 60, true, true,
                SwingConstants.CENTER, SwingConstants.CENTER, testIcon,
                Color.YELLOW, testBorder);

        assertEquals(15, button.getX());
        assertEquals(25, button.getY());
        assertEquals(80, button.getWidth());
        assertEquals(60, button.getHeight());
        assertTrue(button.isVisible());
        assertTrue(button.isEnabled());
        assertEquals(testIcon, button.getIcon());
        assertEquals(Color.YELLOW, button.getBackground());
        assertEquals(SwingConstants.CENTER, button.getHorizontalAlignment());
        assertEquals(SwingConstants.CENTER, button.getVerticalAlignment());
        assertEquals(testBorder, button.getBorder());
    }

    @Test
    public void testImageConstructor_NullIcon() {
        // icon = null
        MyButton button = new MyButton(10, 10, 100, 50, true, true,
                SwingConstants.LEFT, SwingConstants.TOP, null,
                Color.PINK, testBorder);

        assertNull(button.getIcon());
    }

    @Test
    public void testImageConstructor_InvisibleButton() {
        // visible = false
        MyButton button = new MyButton(10, 10, 100, 50, false, true,
                SwingConstants.CENTER, SwingConstants.CENTER, testIcon,
                Color.ORANGE, testBorder);

        assertFalse(button.isVisible());
        assertTrue(button.isEnabled());
    }

    @Test
    public void testImageConstructor_DisabledButton() {
        // usable = false
        MyButton button = new MyButton(10, 10, 100, 50, true, false,
                SwingConstants.CENTER, SwingConstants.CENTER, testIcon,
                Color.CYAN, testBorder);

        assertTrue(button.isVisible());
        assertFalse(button.isEnabled());
    }

    @Test
    public void testImageConstructor_InvisibleAndDisabled() {
        // visible = false, usable = false
        MyButton button = new MyButton(10, 10, 100, 50, false, false,
                SwingConstants.CENTER, SwingConstants.CENTER, testIcon,
                Color.MAGENTA, testBorder);

        assertFalse(button.isVisible());
        assertFalse(button.isEnabled());
    }

    @Test
    public void testImageConstructor_NullBackground() {
        // background = null
        MyButton button = new MyButton(10, 10, 100, 50, true, true,
                SwingConstants.CENTER, SwingConstants.CENTER, testIcon,
                null, testBorder);

        assertNull(button.getBackground());
    }

    @Test
    public void testImageConstructor_NullBorder() {
        // border = null
        MyButton button = new MyButton(10, 10, 100, 50, true, true,
                SwingConstants.CENTER, SwingConstants.CENTER, testIcon,
                Color.BLUE, null);

        assertNull(button.getBorder());
    }

    @Test
    public void testImageConstructor_ZeroCoordinatesAndDimensions() {
        // tọa độ và kích thước = 0
        MyButton button = new MyButton(0, 0, 0, 0, true, true,
                SwingConstants.CENTER, SwingConstants.CENTER, testIcon,
                Color.RED, testBorder);

        assertEquals(0, button.getX());
        assertEquals(0, button.getY());
        assertEquals(0, button.getWidth());
        assertEquals(0, button.getHeight());
    }

    @Test
    public void testImageConstructor_DifferentAlignments() {
        // các alignment khác nhau
        MyButton button = new MyButton(10, 10, 100, 50, true, true,
                SwingConstants.RIGHT, SwingConstants.BOTTOM, testIcon,
                Color.GREEN, testBorder);

        assertEquals(SwingConstants.RIGHT, button.getHorizontalAlignment());
        assertEquals(SwingConstants.BOTTOM, button.getVerticalAlignment());
    }

    // test constructor 3: TEXT AND IMAGE

    @Test
    public void testTextImageConstructor_ValidParameters() {
        // tất cả tham số hợp lệ
        MyButton button = new MyButton(5, 10, 120, 70, "Save", testFont, testIcon,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.RIGHT, SwingConstants.BOTTOM, Color.LIGHT_GRAY, testBorder);

        assertEquals(5, button.getX());
        assertEquals(10, button.getY());
        assertEquals(120, button.getWidth());
        assertEquals(70, button.getHeight());
        assertEquals("Save", button.getText());
        assertEquals(testFont, button.getFont());
        assertEquals(testIcon, button.getIcon());
        assertTrue(button.isVisible());
        assertTrue(button.isEnabled());
        assertEquals(Color.LIGHT_GRAY, button.getBackground());
        assertEquals(SwingConstants.CENTER, button.getHorizontalAlignment());
        assertEquals(SwingConstants.CENTER, button.getVerticalAlignment());
        assertEquals(SwingConstants.RIGHT, button.getHorizontalTextPosition());
        assertEquals(SwingConstants.BOTTOM, button.getVerticalTextPosition());
        assertEquals(testBorder, button.getBorder());
    }

    @Test
    public void testTextImageConstructor_NullText() {
        // text = null
        MyButton button = new MyButton(10, 10, 100, 50, null, testFont, testIcon,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, Color.WHITE, testBorder);

        assertNull(button.getText());
        assertEquals(testIcon, button.getIcon());
    }

    @Test
    public void testTextImageConstructor_EmptyText() {
        // text rỗng
        MyButton button = new MyButton(10, 10, 100, 50, "", testFont, testIcon,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, Color.WHITE, testBorder);

        assertEquals("", button.getText());
    }

    @Test
    public void testTextImageConstructor_NullIcon() {
        // icon = null
        MyButton button = new MyButton(10, 10, 100, 50, "Open", testFont, null,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, Color.WHITE, testBorder);

        assertEquals("Open", button.getText());
        assertNull(button.getIcon());
    }

    @Test
    public void testTextImageConstructor_NullFont() {
        // font = null
        MyButton button = new MyButton(10, 10, 100, 50, "Close", null, testIcon,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, Color.WHITE, testBorder);

        assertNull(button.getFont());
    }

    @Test
    public void testTextImageConstructor_InvisibleButton() {
        // visible = false
        MyButton button = new MyButton(10, 10, 100, 50, "Delete", testFont, testIcon,
                false, true, SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, Color.RED, testBorder);

        assertFalse(button.isVisible());
        assertTrue(button.isEnabled());
    }

    @Test
    public void testTextImageConstructor_DisabledButton() {
        // usable = false
        MyButton button = new MyButton(10, 10, 100, 50, "Submit", testFont, testIcon,
                true, false, SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, Color.BLUE, testBorder);

        assertTrue(button.isVisible());
        assertFalse(button.isEnabled());
    }

    @Test
    public void testTextImageConstructor_InvisibleAndDisabled() {
        // visible = false, usable = false
        MyButton button = new MyButton(10, 10, 100, 50, "Cancel", testFont, testIcon,
                false, false, SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, Color.GRAY, testBorder);

        assertFalse(button.isVisible());
        assertFalse(button.isEnabled());
    }

    @Test
    public void testTextImageConstructor_NullBackground() {
        // background = null
        MyButton button = new MyButton(10, 10, 100, 50, "OK", testFont, testIcon,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, null, testBorder);

        assertNull(button.getBackground());
    }

    @Test
    public void testTextImageConstructor_NullBorder() {
        // border = null
        MyButton button = new MyButton(10, 10, 100, 50, "Apply", testFont, testIcon,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, Color.GREEN, null);

        assertNull(button.getBorder());
    }

    @Test
    public void testTextImageConstructor_DifferentTextPositions() {
        // các vị trí text khác nhau
        int[] hPositions = {SwingConstants.LEFT, SwingConstants.CENTER, SwingConstants.RIGHT};
        int[] vPositions = {SwingConstants.TOP, SwingConstants.CENTER, SwingConstants.BOTTOM};

        for (int hPos : hPositions) {
            for (int vPos : vPositions) {
                MyButton button = new MyButton(10, 10, 100, 50, "Test", testFont, testIcon,
                        true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                        hPos, vPos, Color.WHITE, testBorder);

                assertEquals(hPos, button.getHorizontalTextPosition());
                assertEquals(vPos, button.getVerticalTextPosition());
            }
        }
    }

    @Test
    public void testTextImageConstructor_NegativeCoordinates() {
        // tọa độ âm
        MyButton button = new MyButton(-100, -200, 100, 50, "Back", testFont, testIcon,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, Color.ORANGE, testBorder);

        assertEquals(-100, button.getX());
        assertEquals(-200, button.getY());
    }

    @Test
    public void testTextImageConstructor_ZeroDimensions() {
        // kích thước = 0
        MyButton button = new MyButton(10, 10, 0, 0, "Next", testFont, testIcon,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, Color.YELLOW, testBorder);

        assertEquals(0, button.getWidth());
        assertEquals(0, button.getHeight());
    }

    @Test
    public void testTextImageConstructor_LargeDimensions() {
        // kích thước lớn
        MyButton button = new MyButton(0, 0, 1024, 768, "Fullscreen", testFont, testIcon,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, Color.BLACK, testBorder);

        assertEquals(1024, button.getWidth());
        assertEquals(768, button.getHeight());
    }

    @Test
    public void testTextImageConstructor_AllAlignmentCombinations() {
        // kết hợp alignment và text position
        MyButton button = new MyButton(10, 10, 100, 50, "Menu", testFont, testIcon,
                true, true, SwingConstants.LEFT, SwingConstants.TOP,
                SwingConstants.RIGHT, SwingConstants.BOTTOM, Color.CYAN, testBorder);

        assertEquals(SwingConstants.LEFT, button.getHorizontalAlignment());
        assertEquals(SwingConstants.TOP, button.getVerticalAlignment());
        assertEquals(SwingConstants.RIGHT, button.getHorizontalTextPosition());
        assertEquals(SwingConstants.BOTTOM, button.getVerticalTextPosition());
    }

    @Test
    public void testTextImageConstructor_AllNullOptionalParameters() {
        // tất cả tham số optional = null
        MyButton button = new MyButton(10, 10, 100, 50, null, null, null,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, null, null);

        assertNull(button.getText());
        assertNull(button.getFont());
        assertNull(button.getIcon());
        assertNull(button.getBackground());
        assertNull(button.getBorder());
    }

    @Test
    public void testTextImageConstructor_DifferentBorderTypes() {
        // các loại border khác nhau
        MyButton button1 = new MyButton(10, 10, 100, 50, "Border1", testFont, testIcon,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, Color.WHITE, testBorder);
        assertEquals(testBorder, button1.getBorder());

        MyButton button2 = new MyButton(10, 10, 100, 50, "Border2", testFont, testIcon,
                true, true, SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, Color.WHITE, emptyBorder);
        assertEquals(emptyBorder, button2.getBorder());
    }
}