import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MyTextFieldTest {

    // ============================================
    // PHÂN HOẠCH TƯƠNG ĐƯƠNG CHO THAM SỐ X, Y
    // ============================================
    // x, y: giá trị âm, 0, dương

    @Test
    void testConstructor_PositiveXY() {
        MyTextField field = new MyTextField(100, 50, 200, 30,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, null);

        assertEquals(100, field.getX());
        assertEquals(50, field.getY());
    }

    @Test
    void testConstructor_ZeroXY() {
        MyTextField field = new MyTextField(0, 0, 200, 30,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, null);

        assertEquals(0, field.getX());
        assertEquals(0, field.getY());
    }

    @Test
    void testConstructor_NegativeXY() {
        MyTextField field = new MyTextField(-10, -20, 200, 30,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, null);

        assertEquals(-10, field.getX());
        assertEquals(-20, field.getY());
    }

    // PHÂN HOẠCH TƯƠNG ĐƯƠNG CHO WIDTH, HEIGHT
    // width, height: giá trị âm, 0, dương nhỏ, dương lớn

    @Test
    void testConstructor_PositiveSmallWidthHeight() {
        MyTextField field = new MyTextField(0, 0, 10, 10,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, null);

        assertEquals(10, field.getWidth());
        assertEquals(10, field.getHeight());
    }

    @Test
    void testConstructor_PositiveLargeWidthHeight() {
        MyTextField field = new MyTextField(0, 0, 1000, 500,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, null);

        assertEquals(1000, field.getWidth());
        assertEquals(500, field.getHeight());
    }

    @Test
    void testConstructor_ZeroWidthHeight() {
        MyTextField field = new MyTextField(0, 0, 0, 0,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, null);

        assertEquals(0, field.getWidth());
        assertEquals(0, field.getHeight());
    }

    @Test
    void testConstructor_NegativeWidthHeight() {
        MyTextField field = new MyTextField(0, 0, -100, -50,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, null);

        assertEquals(-100, field.getWidth());
        assertEquals(-50, field.getHeight());
    }

    // PHÂN HOẠCH TƯƠNG ĐƯƠNG CHO FONT
    // Font: null, font hợp lệ với các style khác nhau, kích thước khác nhau

    @Test
    void testConstructor_NullFont() {
        MyTextField field = new MyTextField(0, 0, 200, 30,
                null, 0.5f, 0.5f, null);

        assertNull(field.getFont());
    }

    @Test
    void testConstructor_PlainFont() {
        Font font = new Font("Arial", Font.PLAIN, 12);
        MyTextField field = new MyTextField(0, 0, 200, 30,
                font, 0.5f, 0.5f, null);

        assertEquals(font, field.getFont());
    }

    @Test
    void testConstructor_BoldFont() {
        Font font = new Font("Times New Roman", Font.BOLD, 14);
        MyTextField field = new MyTextField(0, 0, 200, 30,
                font, 0.5f, 0.5f, null);

        assertEquals(font, field.getFont());
    }

    @Test
    void testConstructor_ItalicFont() {
        Font font = new Font("Courier", Font.ITALIC, 16);
        MyTextField field = new MyTextField(0, 0, 200, 30,
                font, 0.5f, 0.5f, null);

        assertEquals(font, field.getFont());
    }

    @Test
    void testConstructor_BoldItalicFont() {
        Font font = new Font("Verdana", Font.BOLD | Font.ITALIC, 18);
        MyTextField field = new MyTextField(0, 0, 200, 30,
                font, 0.5f, 0.5f, null);

        assertEquals(font, field.getFont());
    }

    @Test
    void testConstructor_SmallFontSize() {
        Font font = new Font("Arial", Font.PLAIN, 8);
        MyTextField field = new MyTextField(0, 0, 200, 30,
                font, 0.5f, 0.5f, null);

        assertEquals(font, field.getFont());
    }

    @Test
    void testConstructor_LargeFontSize() {
        Font font = new Font("Arial", Font.PLAIN, 72);
        MyTextField field = new MyTextField(0, 0, 200, 30,
                font, 0.5f, 0.5f, null);

        assertEquals(font, field.getFont());
    }

    // PHÂN HOẠCH TƯƠNG ĐƯƠNG CHO ALIGNMENT X, Y
    // xAlignment, yAlignment:
    // - 0.0 (left/top)
    // - 0.5 (center)
    // - 1.0 (right/bottom)

    @Test
    void testConstructor_MinAlignment() {
        MyTextField field = new MyTextField(0, 0, 200, 30,
                new Font("Arial", Font.PLAIN, 12), 0.0f, 0.0f, null);

        assertEquals(0.0f, field.getAlignmentX(), 0.001f);
        assertEquals(0.0f, field.getAlignmentY(), 0.001f);
    }

    @Test
    void testConstructor_CenterAlignment() {
        MyTextField field = new MyTextField(0, 0, 200, 30,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, null);

        assertEquals(0.5f, field.getAlignmentX(), 0.001f);
        assertEquals(0.5f, field.getAlignmentY(), 0.001f);
    }

    @Test
    void testConstructor_MaxAlignment() {
        MyTextField field = new MyTextField(0, 0, 200, 30,
                new Font("Arial", Font.PLAIN, 12), 1.0f, 1.0f, null);

        assertEquals(1.0f, field.getAlignmentX(), 0.001f);
        assertEquals(1.0f, field.getAlignmentY(), 0.001f);
    }

    @Test
    void testConstructor_MixedAlignment() {
        MyTextField field = new MyTextField(0, 0, 200, 30,
                new Font("Arial", Font.PLAIN, 12), 0.0f, 1.0f, null);

        assertEquals(0.0f, field.getAlignmentX(), 0.001f);
        assertEquals(1.0f, field.getAlignmentY(), 0.001f);
    }

    // PHÂN HOẠCH TƯƠNG ĐƯƠNG CHO BORDER
    // Border: null, EmptyBorder, LineBorder, các loại border khác

    @Test
    void testConstructor_NullBorder() {
        MyTextField field = new MyTextField(0, 0, 200, 30,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, null);

        assertNull(field.getBorder());
    }

    @Test
    void testConstructor_EmptyBorder() {
        Border border = new EmptyBorder(5, 5, 5, 5);
        MyTextField field = new MyTextField(0, 0, 200, 30,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, border);

        assertEquals(border, field.getBorder());
    }

    @Test
    void testConstructor_LineBorder() {
        Border border = new LineBorder(Color.BLACK, 2);
        MyTextField field = new MyTextField(0, 0, 200, 30,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, border);

        assertEquals(border, field.getBorder());
    }

    @Test
    void testConstructor_CompoundBorder() {
        Border border = BorderFactory.createCompoundBorder(
                new LineBorder(Color.RED, 1),
                new EmptyBorder(3, 3, 3, 3)
        );
        MyTextField field = new MyTextField(0, 0, 200, 30,
                new Font("Arial", Font.PLAIN, 12), 0.5f, 0.5f, border);

        assertEquals(border, field.getBorder());
    }
}