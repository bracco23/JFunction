package it.bracco23.jfunctions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import it.bracco23.jfunctions.Utility;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Emiddio
 */
public class UtilityNumberTest {

    public UtilityNumberTest() {
    }

    @Test
    public void testNull() {
        boolean result = Utility.isNumber(null);
        assertFalse(result);
        NullPointerException e = assertThrows(NullPointerException.class, () -> {
            Utility.parseNumber(null);
        });
    }

    @Test
    public void testEmpty() {
        boolean result = Utility.isNumber("");
        assertFalse(result);
        assertThrows(IllegalArgumentException.class, () -> {
            Utility.parseNumber("");
        });
    }

    @Test
    public void testWrong() {
        String toTest[] = {"5a", "-5.b", "e13e37", "-42pie42"};
        for (String test : toTest) {
            assertFalse(Utility.isNumber(test), test + " should not be seen as a number");
            assertThrows(NumberFormatException.class, () -> {
                Utility.parseNumber(test);
            });
        }
    }

    @Test
    public void testIntegers() {
        String toTest[] = {"5", "-5", "1337", "-4242"};
        double expected[] = {5, -5, 1337, -4242};
        assertEquals(toTest.length, expected.length, "Error in the test, number of cases should be equal");
        for (int i = 0; i < toTest.length; i++) {
            assertTrue(Utility.isNumber(toTest[i]), toTest[i] + " should be seen as a number");
            assertEquals(expected[i], Utility.parseNumber(toTest[i]), 1e-7);
        }
    }

    @Test
    public void testDouble() {
        String toTest[] = {"5,1", "-5.5", "1337e-2", "-4242,42", "6e7"};
        double expected[] = {5.1, -5.5, 1337e-2, -4242.42, 6e7};
        assertEquals(toTest.length, expected.length, "Error in the test, number of cases should be equal");
        for (int i = 0; i < toTest.length; i++) {
            assertTrue(Utility.isNumber(toTest[i]), toTest[i] + " should be seen as a number");
            assertEquals(expected[i], Utility.parseNumber(toTest[i]), 1e-7);
        }
    }

    @Test
    public void testPi() {
        String toTest[] = {"pi", "-pi"};
        double expected[] = {Math.PI, -Math.PI};
        assertEquals(toTest.length, expected.length, "Error in the test, number of cases should be equal");
        for (int i = 0; i < toTest.length; i++) {
            assertTrue(Utility.isNumber(toTest[i]), toTest[i] + " should be seen as a number");
            assertEquals(expected[i], Utility.parseNumber(toTest[i]), 1e-7);
        }
    }

    @Test
    public void testNeper() {
        String toTest[] = {"e", "-e"};
        double expected[] = {Math.E, -Math.E};
        assertEquals(toTest.length, expected.length, "Error in the test, number of cases should be equal");
        for (int i = 0; i < toTest.length; i++) {
            assertTrue(Utility.isNumber(toTest[i]), toTest[i] + " should be seen as a number");
            assertEquals(expected[i], Utility.parseNumber(toTest[i]), 1e-7);
        }
    }
}
