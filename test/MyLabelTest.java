import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class MyLabelTest {

    private Font testFont;
    private ImageIcon testIcon;

    @BeforeEach
    public void setUp() {
        testFont = new Font("Arial", Font.PLAIN, 12);
        // tạo ImageIcon đơn giản cho test
        testIcon = new ImageIcon(new byte[0]);
    }

    // test constructor 1: TEXT ONLY

    @Test
    public void testTextConstructor_ValidPositiveCoordinates() {
        // tọa độ dương, kích thước dương
        MyLabel label = new MyLabel(10, 20, 100, 50, "Test", testFont,
                Color.BLACK, SwingConstants.CENTER, SwingConstants.CENTER, Color.WHITE);

        assertEquals(10, label.getX());
        assertEquals(20, label.getY());
        assertEquals(100, label.getWidth());
        assertEquals(50, label.getHeight());
        assertEquals("Test", label.getText());
        assertEquals(testFont, label.getFont());
        assertEquals(Color.BLACK, label.getForeground());
        assertEquals(Color.WHITE, label.getBackground());
        assertEquals(SwingConstants.CENTER, label.getHorizontalAlignment());
        assertEquals(SwingConstants.CENTER, label.getVerticalAlignment());
        assertTrue(label.isOpaque());
    }

    @Test
    public void testTextConstructor_ZeroCoordinates() {
        // tọa độ = 0
        MyLabel label = new MyLabel(0, 0, 100, 50, "Test", testFont,
                Color.RED, SwingConstants.LEFT, SwingConstants.TOP, Color.YELLOW);

        assertEquals(0, label.getX());
        assertEquals(0, label.getY());
        assertEquals("Test", label.getText());
    }

    @Test
    public void testTextConstructor_NegativeCoordinates() {
        // tọa độ âm
        MyLabel label = new MyLabel(-10, -20, 100, 50, "Test", testFont,
                Color.BLUE, SwingConstants.RIGHT, SwingConstants.BOTTOM, Color.GREEN);

        assertEquals(-10, label.getX());
        assertEquals(-20, label.getY());
        assertEquals(SwingConstants.RIGHT, label.getHorizontalAlignment());
        assertEquals(SwingConstants.BOTTOM, label.getVerticalAlignment());
    }

    @Test
    public void testTextConstructor_ZeroDimensions() {
        // kích thước = 0
        MyLabel label = new MyLabel(10, 10, 0, 0, "Test", testFont,
                Color.BLACK, SwingConstants.CENTER, SwingConstants.CENTER, Color.WHITE);

        assertEquals(0, label.getWidth());
        assertEquals(0, label.getHeight());
    }

    @Test
    public void testTextConstructor_EmptyText() {
        // text rỗng
        MyLabel label = new MyLabel(10, 10, 100, 50, "", testFont,
                Color.BLACK, SwingConstants.CENTER, SwingConstants.CENTER, Color.WHITE);

        assertEquals("", label.getText());
    }

    @Test
    public void testTextConstructor_NullText() {
        // text = null
        MyLabel label = new MyLabel(10, 10, 100, 50, null, testFont,
                Color.BLACK, SwingConstants.CENTER, SwingConstants.CENTER, Color.WHITE);

        assertNull(label.getText());
    }

    @Test
    public void testTextConstructor_NullFont() {
        // font = null
        MyLabel label = new MyLabel(10, 10, 100, 50, "Test", null,
                Color.BLACK, SwingConstants.CENTER, SwingConstants.CENTER, Color.WHITE);

        assertNull(label.getFont());
    }

    @Test
    public void testTextConstructor_NullColors() {
        // màu = null
        MyLabel label = new MyLabel(10, 10, 100, 50, "Test", testFont,
                null, SwingConstants.CENTER, SwingConstants.CENTER, null);

        assertNull(label.getForeground());
        assertNull(label.getBackground());
    }

    @Test
    public void testTextConstructor_DifferentAlignments() {
        // các giá trị alignment hợp lệ khác nhau
        int[] horizontalAlignments = {SwingConstants.LEFT, SwingConstants.CENTER, SwingConstants.RIGHT};
        int[] verticalAlignments = {SwingConstants.TOP, SwingConstants.CENTER, SwingConstants.BOTTOM};

        for (int hAlign : horizontalAlignments) {
            for (int vAlign : verticalAlignments) {
                MyLabel label = new MyLabel(10, 10, 100, 50, "Test", testFont,
                        Color.BLACK, hAlign, vAlign, Color.WHITE);

                assertEquals(hAlign, label.getHorizontalAlignment());
                assertEquals(vAlign, label.getVerticalAlignment());
            }
        }
    }


    // test consturctor 2 : IMAGE ONLY

    @Test
    public void testImageConstructor_ValidParameters() {
        // tất cả tham số hợp lệ
        MyLabel label = new MyLabel(15, 25, 80, 60,
                SwingConstants.CENTER, SwingConstants.CENTER, testIcon);

        assertEquals(15, label.getX());
        assertEquals(25, label.getY());
        assertEquals(80, label.getWidth());
        assertEquals(60, label.getHeight());
        assertEquals(testIcon, label.getIcon());
        assertEquals(SwingConstants.CENTER, label.getHorizontalAlignment());
        assertEquals(SwingConstants.CENTER, label.getVerticalAlignment());
    }

    @Test
    public void testImageConstructor_NullIcon() {
        // icon = null
        MyLabel label = new MyLabel(10, 10, 100, 50,
                SwingConstants.LEFT, SwingConstants.TOP, null);

        assertNull(label.getIcon());
    }

    @Test
    public void testImageConstructor_DifferentAlignments() {
        // các alignment khác nhau
        MyLabel label = new MyLabel(10, 10, 100, 50,
                SwingConstants.RIGHT, SwingConstants.BOTTOM, testIcon);

        assertEquals(SwingConstants.RIGHT, label.getHorizontalAlignment());
        assertEquals(SwingConstants.BOTTOM, label.getVerticalAlignment());
    }

    @Test
    public void testImageConstructor_ZeroCoordinatesAndDimensions() {
        // tọa độ và kích thước = 0
        MyLabel label = new MyLabel(0, 0, 0, 0,
                SwingConstants.CENTER, SwingConstants.CENTER, testIcon);

        assertEquals(0, label.getX());
        assertEquals(0, label.getY());
        assertEquals(0, label.getWidth());
        assertEquals(0, label.getHeight());
    }

    // test constructor 3: TEXT AND IMAGE

    @Test
    public void testTextImageConstructor_ValidParameters() {
        // tất cả tham số hợp lệ
        MyLabel label = new MyLabel(5, 10, 120, 70, "Label Text", testFont, Color.BLUE,
                SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.RIGHT, SwingConstants.BOTTOM, testIcon, Color.LIGHT_GRAY);

        assertEquals(5, label.getX());
        assertEquals(10, label.getY());
        assertEquals(120, label.getWidth());
        assertEquals(70, label.getHeight());
        assertEquals("Label Text", label.getText());
        assertEquals(testFont, label.getFont());
        assertEquals(Color.BLUE, label.getForeground());
        assertEquals(Color.LIGHT_GRAY, label.getBackground());
        assertEquals(testIcon, label.getIcon());
        assertEquals(SwingConstants.CENTER, label.getHorizontalAlignment());
        assertEquals(SwingConstants.CENTER, label.getVerticalAlignment());
        assertEquals(SwingConstants.RIGHT, label.getHorizontalTextPosition());
        assertEquals(SwingConstants.BOTTOM, label.getVerticalTextPosition());
        assertTrue(label.isOpaque());
    }

    @Test
    public void testTextImageConstructor_NullText() {
        // text = null
        MyLabel label = new MyLabel(10, 10, 100, 50, null, testFont, Color.BLACK,
                SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, testIcon, Color.WHITE);

        assertNull(label.getText());
        assertEquals(testIcon, label.getIcon());
    }

    @Test
    public void testTextImageConstructor_NullIcon() {
        // icon = null
        MyLabel label = new MyLabel(10, 10, 100, 50, "Test", testFont, Color.BLACK,
                SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, null, Color.WHITE);

        assertEquals("Test", label.getText());
        assertNull(label.getIcon());
    }

    @Test
    public void testTextImageConstructor_NullFont() {
        // font = null
        MyLabel label = new MyLabel(10, 10, 100, 50, "Test", null, Color.BLACK,
                SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, testIcon, Color.WHITE);

        assertNull(label.getFont());
    }

    @Test
    public void testTextImageConstructor_NullColors() {
        // màu = null
        MyLabel label = new MyLabel(10, 10, 100, 50, "Test", testFont, null,
                SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, testIcon, null);

        assertNull(label.getForeground());
        assertNull(label.getBackground());
    }

    @Test
    public void testTextImageConstructor_DifferentTextPositions() {
        // các vị trí text khác nhau
        int[] horizontalPositions = {SwingConstants.LEFT, SwingConstants.CENTER, SwingConstants.RIGHT};
        int[] verticalPositions = {SwingConstants.TOP, SwingConstants.CENTER, SwingConstants.BOTTOM};

        for (int hPos : horizontalPositions) {
            for (int vPos : verticalPositions) {
                MyLabel label = new MyLabel(10, 10, 100, 50, "Test", testFont, Color.BLACK,
                        SwingConstants.CENTER, SwingConstants.CENTER,
                        hPos, vPos, testIcon, Color.WHITE);

                assertEquals(hPos, label.getHorizontalTextPosition());
                assertEquals(vPos, label.getVerticalTextPosition());
            }
        }
    }

    @Test
    public void testTextImageConstructor_EmptyText() {
        // text rỗng
        MyLabel label = new MyLabel(10, 10, 100, 50, "", testFont, Color.BLACK,
                SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, testIcon, Color.WHITE);

        assertEquals("", label.getText());
    }

    @Test
    public void testTextImageConstructor_NegativeCoordinates() {
        // tọa độ âm
        MyLabel label = new MyLabel(-50, -100, 100, 50, "Test", testFont, Color.BLACK,
                SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, testIcon, Color.WHITE);

        assertEquals(-50, label.getX());
        assertEquals(-100, label.getY());
    }

    @Test
    public void testTextImageConstructor_LargeDimensions() {
        // kích thước lớn
        MyLabel label = new MyLabel(0, 0, 1920, 1080, "Test", testFont, Color.BLACK,
                SwingConstants.CENTER, SwingConstants.CENTER,
                SwingConstants.LEFT, SwingConstants.TOP, testIcon, Color.WHITE);

        assertEquals(1920, label.getWidth());
        assertEquals(1080, label.getHeight());
    }

    @Test
    public void testTextImageConstructor_AllAlignmentCombinations() {
        // kiểm tra tất cả các kết hợp alignment và text position
        MyLabel label = new MyLabel(10, 10, 100, 50, "Test", testFont, Color.BLACK,
                SwingConstants.LEFT, SwingConstants.TOP,
                SwingConstants.RIGHT, SwingConstants.BOTTOM, testIcon, Color.WHITE);

        assertEquals(SwingConstants.LEFT, label.getHorizontalAlignment());
        assertEquals(SwingConstants.TOP, label.getVerticalAlignment());
        assertEquals(SwingConstants.RIGHT, label.getHorizontalTextPosition());
        assertEquals(SwingConstants.BOTTOM, label.getVerticalTextPosition());
    }
}