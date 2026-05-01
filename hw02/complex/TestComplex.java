package hw02.complex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestComplex {
    @Test
    public void testAccessors() {
        Complex c = new Complex();

        c.setReal(1.1);
        c.setImag(4.4);

        assertEquals(1.1, c.getReal(), 1e-8);
        assertEquals(4.4, c.getImag(), 1e-8);

        c.setValue(5.0, 6.0);
        assertEquals(5.0, c.getReal(), 1e-8);
        assertEquals(6.0, c.getImag(), 1e-8);
    }

    @Test
    public void testIs() {
        Complex realOnly = new Complex(1.0, 0.0);
        Complex imagOnly = new Complex(0.0, 1.0);

        assertTrue(realOnly.isReal());
        assertFalse(realOnly.isImaginary());

        assertTrue(imagOnly.isImaginary());
        assertFalse(imagOnly.isReal());
    }

    @Test
    public void testToString() {
        Complex c = new Complex(3.0, 4.0);
        assertEquals("3.0 + 4.0i", c.toString());
    }

    @Test
    public void testEquals() {
        Complex c1 = new Complex(1.1, 2.2);

        assertTrue(c1.equals(1.1, 2.2));

        Complex c2 = new Complex(1.1, 2.2);
        assertTrue(c1.equals(c2));
        assertFalse(c1.equals(new Complex(1.1, 3.3)));
    }

    @Test
    public void testOperation() {
        Complex c1 = new Complex(1.0, 2.0);
        Complex c2 = new Complex(3.0, 4.0);
        double delta = 1e-8;

        // add
        Complex sum = c1.add(c2);
        assertEquals(4.0, sum.getReal(), delta);
        assertEquals(6.0, sum.getImag(), delta);

        // subtract
        Complex diff = c1.subtract(c2);
        assertEquals(-2.0, diff.getReal(), delta);
        assertEquals(-2.0, diff.getImag(), delta);

        // multiply
        Complex product = c1.multiply(c2);
        assertEquals(-5.0, product.getReal(), delta);
        assertEquals(10.0, product.getImag(), delta);

        // divide
        Complex quotient = c1.divide(c2);
        assertEquals(0.44, quotient.getReal(), delta);
        assertEquals(0.08, quotient.getImag(), delta);

        // abs
        assertEquals(5.0, c2.abs(), delta);

        // 验证不可变性：确保 c1 的值没被上面的运算改掉
        assertEquals(1.0, c1.getReal(), delta);
    }

}
