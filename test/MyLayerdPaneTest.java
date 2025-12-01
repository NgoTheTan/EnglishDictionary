import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MyLayerdPaneTest {
    // phân hoạch tương đương cho x, y
    // x, y: âm, 0, dương

    @Test
    void testConstructor_PositiveXY() {
        MyLayerdPane pane = new MyLayerdPane(100, 50, 300, 200,
                Color.WHITE, true);

        assertEquals(100, pane.getX());
        assertEquals(50, pane.getY());
    }

    @Test
    void testConstructor_ZeroXY() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                Color.WHITE, true);

        assertEquals(0, pane.getX());
        assertEquals(0, pane.getY());
    }

    @Test
    void testConstructor_NegativeXY() {
        MyLayerdPane pane = new MyLayerdPane(-50, -30, 300, 200,
                Color.WHITE, true);

        assertEquals(-50, pane.getX());
        assertEquals(-30, pane.getY());
    }

    @Test
    void testConstructor_LargePositiveXY() {
        MyLayerdPane pane = new MyLayerdPane(10000, 5000, 300, 200,
                Color.WHITE, true);

        assertEquals(10000, pane.getX());
        assertEquals(5000, pane.getY());
    }

    @Test
    void testConstructor_LargeNegativeXY() {
        MyLayerdPane pane = new MyLayerdPane(-5000, -3000, 300, 200,
                Color.WHITE, true);

        assertEquals(-5000, pane.getX());
        assertEquals(-3000, pane.getY());
    }

    // phân hoạch tương đương cho width, height
    // width, height: âm, 0, dương

    @Test
    void testConstructor_PositiveWidthHeight() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 18, 36,
                Color.WHITE, true);

        assertEquals(18, pane.getWidth());
        assertEquals(36, pane.getHeight());
    }

    @Test
    void testConstructor_ZeroWidthHeight() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 0, 0,
                Color.WHITE, true);

        assertEquals(0, pane.getWidth());
        assertEquals(0, pane.getHeight());
    }

    @Test
    void testConstructor_NegativeWidthHeight() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, -100, -50,
                Color.WHITE, true);

        assertEquals(-100, pane.getWidth());
        assertEquals(-50, pane.getHeight());
    }

    @Test
    void testConstructor_MixedPositiveNegativeWidthHeight() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 200, -100,
                Color.WHITE, true);

        assertEquals(200, pane.getWidth());
        assertEquals(-100, pane.getHeight());
    }

    // phân hoạch tương đương cho color
    // color: null, màu chuẩn, màu tùy chỉnh, màu trong suốt

    @Test
    void testConstructor_NullColor() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                null, true);

        assertNull(pane.getBackground());
    }

    @Test
    void testConstructor_WhiteColor() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                Color.WHITE, true);

        assertEquals(Color.WHITE, pane.getBackground());
    }

    @Test
    void testConstructor_BlackColor() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                Color.BLACK, true);

        assertEquals(Color.BLACK, pane.getBackground());
    }

    @Test
    void testConstructor_RedColor() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                Color.RED, true);

        assertEquals(Color.RED, pane.getBackground());
    }

    @Test
    void testConstructor_GreenColor() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                Color.GREEN, true);

        assertEquals(Color.GREEN, pane.getBackground());
    }

    @Test
    void testConstructor_BlueColor() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                Color.BLUE, true);

        assertEquals(Color.BLUE, pane.getBackground());
    }

    @Test
    void testConstructor_YellowColor() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                Color.YELLOW, true);

        assertEquals(Color.YELLOW, pane.getBackground());
    }

    @Test
    void testConstructor_CyanColor() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                Color.CYAN, true);

        assertEquals(Color.CYAN, pane.getBackground());
    }

    @Test
    void testConstructor_MagentaColor() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                Color.MAGENTA, true);

        assertEquals(Color.MAGENTA, pane.getBackground());
    }

    @Test
    void testConstructor_GrayColor() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                Color.GRAY, true);

        assertEquals(Color.GRAY, pane.getBackground());
    }

    @Test
    void testConstructor_DarkGrayColor() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                Color.DARK_GRAY, true);

        assertEquals(Color.DARK_GRAY, pane.getBackground());
    }

    @Test
    void testConstructor_LightGrayColor() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                Color.LIGHT_GRAY, true);

        assertEquals(Color.LIGHT_GRAY, pane.getBackground());
    }

    @Test
    void testConstructor_CustomRGBColor() {
        Color custom = new Color(128, 64, 192);
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                custom, true);

        assertEquals(custom, pane.getBackground());
        assertEquals(128, pane.getBackground().getRed());
        assertEquals(64, pane.getBackground().getGreen());
        assertEquals(192, pane.getBackground().getBlue());
    }

    @Test
    void testConstructor_CustomRGBColorMinValues() {
        Color custom = new Color(0, 0, 0);
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                custom, true);

        assertEquals(custom, pane.getBackground());
        assertEquals(0, pane.getBackground().getRed());
        assertEquals(0, pane.getBackground().getGreen());
        assertEquals(0, pane.getBackground().getBlue());
    }

    @Test
    void testConstructor_CustomRGBColorMaxValues() {
        Color custom = new Color(255, 255, 255);
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                custom, true);

        assertEquals(custom, pane.getBackground());
        assertEquals(255, pane.getBackground().getRed());
        assertEquals(255, pane.getBackground().getGreen());
        assertEquals(255, pane.getBackground().getBlue());
    }

    @Test
    void testConstructor_TransparentColor_FullyTransparent() {
        Color transparent = new Color(255, 0, 0, 0);
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                transparent, true);

        assertEquals(transparent, pane.getBackground());
        assertEquals(0, pane.getBackground().getAlpha());
    }

    @Test
    void testConstructor_TransparentColor_SemiTransparent() {
        Color transparent = new Color(100, 150, 200, 128);
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                transparent, true);

        assertEquals(transparent, pane.getBackground());
        assertEquals(128, pane.getBackground().getAlpha());
    }

    @Test
    void testConstructor_TransparentColor_FullyOpaque() {
        Color transparent = new Color(50, 100, 150, 255);
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                transparent, true);

        assertEquals(transparent, pane.getBackground());
        assertEquals(255, pane.getBackground().getAlpha());
    }

    // phân hoạch tương đương cho opaque
    // opaque: true, false

    @Test
    void testConstructor_OpaqueTrue() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                Color.WHITE, true);

        assertTrue(pane.isOpaque());
    }

    @Test
    void testConstructor_OpaqueFalse() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                Color.WHITE, false);

        assertFalse(pane.isOpaque());
    }

    @Test
    void testConstructor_OpaqueTrue_WithNullColor() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                null, true);

        assertTrue(pane.isOpaque());
    }

    @Test
    void testConstructor_OpaqueFalse_WithNullColor() {
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                null, false);

        assertFalse(pane.isOpaque());
    }

    @Test
    void testConstructor_OpaqueTrue_WithTransparentColor() {
        Color transparent = new Color(255, 0, 0, 128);
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                transparent, true);

        assertTrue(pane.isOpaque());
    }

    @Test
    void testConstructor_OpaqueFalse_WithTransparentColor() {
        Color transparent = new Color(255, 0, 0, 128);
        MyLayerdPane pane = new MyLayerdPane(0, 0, 300, 200,
                transparent, false);

        assertFalse(pane.isOpaque());
    }
}