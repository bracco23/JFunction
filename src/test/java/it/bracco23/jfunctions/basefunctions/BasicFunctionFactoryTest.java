/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.bracco23.jfunctions.basefunctions;

import it.bracco23.jfunctions.basefunctions.BaseFunctionFactory;
import it.bracco23.jfunctions.basefunctions.UnknownFunctionException;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Emiddio
 */
public class BasicFunctionFactoryTest {

    public BasicFunctionFactoryTest() {
    }

    @Test
    public void testParseConstantFunction() {
        String[] str = {"5", "pi", "e", "3.14", "-7,28"};
        double[] expected = {5, Math.PI, Math.E, 3.14, -7.28};
        for (int i = 0; i < str.length; i++) {
            Function<Double, Double> result = BaseFunctionFactory.parseFunction(str[i]);
            for (double x = -5; x <= 5; x++) {
                assertEquals(expected[i], result.apply(x), 1e-7, "Evaluating " + str[i] + " at " + x + ".");
            }
        }
    }

    @Test
    public void testParseIdentityFunction() {
        double[] expected = {5, Math.PI, Math.E, 3.14, -7.28};
        Function<Double, Double> result = BaseFunctionFactory.parseFunction("x");
        for (double expectedPoint : expected) {
            assertEquals(expectedPoint, result.apply(expectedPoint), 1e-7, "Evaluating x at " + expectedPoint + ".");
        }
    }

    @Test
    public void testParseSinFunction() {
        double[] expected = {5, Math.PI, Math.E, 3.14, -7.28};
        Function<Double, Double> resultSin = BaseFunctionFactory.parseFunction("sin(x)");
        Function<Double, Double> resultSen = BaseFunctionFactory.parseFunction("sen(x)");
        for (double expectedPoint : expected) {
            assertEquals(Math.sin(expectedPoint), resultSin.apply(expectedPoint), 1e-7, "Evaluating sin at " + expectedPoint + ".");
            assertEquals(Math.sin(expectedPoint), resultSen.apply(expectedPoint), 1e-7, "Evaluating sen at " + expectedPoint + ".");
        }
    }

    @Test
    public void testParseCosFunction() {
        double[] expected = {5, Math.PI, Math.E, 3.14, -7.28};
        Function<Double, Double> result = BaseFunctionFactory.parseFunction("cos(x)");
        for (double expectedPoint : expected) {
            assertEquals(Math.cos(expectedPoint), result.apply(expectedPoint), 1e-7, "Evaluating cos at " + expectedPoint + ".");
        }
    }

    @Test
    public void testParseTanFunction() {
        double[] expected = {5, Math.PI, Math.E, 3.14, -7.28};
        Function<Double, Double> result = BaseFunctionFactory.parseFunction("tan(x)");
        for (double expectedPoint : expected) {
            assertEquals(Math.tan(expectedPoint), result.apply(expectedPoint), 1e-7, "Evaluating tan at " + expectedPoint + ".");
        }
    }

    @Test
    public void testParseAbsFunction() {
        double[] expected = {5, Math.PI, Math.E, 3.14, -7.28};
        Function<Double, Double> result = BaseFunctionFactory.parseFunction("abs(x)");
        for (double expectedPoint : expected) {
            assertEquals(Math.abs(expectedPoint), result.apply(expectedPoint), 1e-7, "Evaluating abs at " + expectedPoint + ".");
        }
    }

    @Test
    public void testParseLogFunction() {
        double[] expected = {5, Math.PI, Math.E, 3.14, -7.28};
        Function<Double, Double> result = BaseFunctionFactory.parseFunction("log(x)");
        for (double expectedPoint : expected) {
            assertEquals(Math.log(expectedPoint), result.apply(expectedPoint), 1e-7, "Evaluating log at " + expectedPoint + ".");
        }
    }

    @Test
    public void testParseSinhFunction() {
        double[] expected = {5, Math.PI, Math.E, 3.14, -7.28};
        Function<Double, Double> result = BaseFunctionFactory.parseFunction("sinh(x)");
        Function<Double, Double> result2 = BaseFunctionFactory.parseFunction("senh(x)");
        for (double expectedPoint : expected) {
            assertEquals(Math.sinh(expectedPoint), result.apply(expectedPoint), 1e-7, "Evaluating sinh at " + expectedPoint + ".");
            assertEquals(Math.sinh(expectedPoint), result2.apply(expectedPoint), 1e-7, "Evaluating senh at " + expectedPoint + ".");
        }
    }

    @Test
    public void testParseCoshFunction() {
        double[] expected = {5, Math.PI, Math.E, 3.14, -7.28};
        Function<Double, Double> result = BaseFunctionFactory.parseFunction("cosh(x)");
        for (double expectedPoint : expected) {
            assertEquals(Math.cosh(expectedPoint), result.apply(expectedPoint), 1e-7, "Evaluating cosh at " + expectedPoint + ".");
        }
    }

    @Test
    public void testParseTanhFunction() {
        double[] expected = {5, Math.PI, Math.E, 3.14, -7.28};
        Function<Double, Double> result = BaseFunctionFactory.parseFunction("tanh(x)");
        for (double expectedPoint : expected) {
            assertEquals(Math.tanh(expectedPoint), result.apply(expectedPoint), 1e-7, "Evaluating tanh at " + expectedPoint + ".");
        }
    }

    @Test
    public void testParseAsinFunction() {
        double[] expected = {-1, -0.3, 0, 0.3, 1};
        Function<Double, Double> result = BaseFunctionFactory.parseFunction("asin(x)");
        Function<Double, Double> result2 = BaseFunctionFactory.parseFunction("asen(x)");
        Function<Double, Double> result3 = BaseFunctionFactory.parseFunction("arcsin(x)");
        Function<Double, Double> result4 = BaseFunctionFactory.parseFunction("arcsen(x)");
        for (double expectedPoint : expected) {
            assertEquals(Math.asin(expectedPoint), result.apply(expectedPoint), 1e-7, "Evaluating asin at " + expectedPoint + ".");
            assertEquals(Math.asin(expectedPoint), result2.apply(expectedPoint), 1e-7, "Evaluating asem at " + expectedPoint + ".");
            assertEquals(Math.asin(expectedPoint), result3.apply(expectedPoint), 1e-7, "Evaluating arcsin at " + expectedPoint + ".");
            assertEquals(Math.asin(expectedPoint), result4.apply(expectedPoint), 1e-7, "Evaluating arcsen at " + expectedPoint + ".");
        }
    }

    @Test
    public void testParseAcosFunction() {
        double[] expected = {-1, -0.3, 0, 0.3, 1};
        Function<Double, Double> result = BaseFunctionFactory.parseFunction("acos(x)");
        Function<Double, Double> result2 = BaseFunctionFactory.parseFunction("arccos(x)");
        Function<Double, Double> result3 = BaseFunctionFactory.parseFunction("arcos(x)");
        for (double expectedPoint : expected) {
            assertEquals(Math.acos(expectedPoint), result.apply(expectedPoint), 1e-7, "Evaluating acos at " + expectedPoint + ".");
            assertEquals(Math.acos(expectedPoint), result2.apply(expectedPoint), 1e-7, "Evaluating arccos at " + expectedPoint + ".");
            assertEquals(Math.acos(expectedPoint), result3.apply(expectedPoint), 1e-7, "Evaluating arcos at " + expectedPoint + ".");
        }
    }

    @Test
    public void testParseAtanFunction() {
        double[] expected = {-1, -0.3, 0, 0.3, 1};
        Function<Double, Double> result = BaseFunctionFactory.parseFunction("atan(x)");
        Function<Double, Double> result2 = BaseFunctionFactory.parseFunction("arctan(x)");
        for (double expectedPoint : expected) {
            assertEquals(Math.atan(expectedPoint), result.apply(expectedPoint), 1e-7, "Evaluating atan at " + expectedPoint + ".");
            assertEquals(Math.atan(expectedPoint), result2.apply(expectedPoint), 1e-7, "Evaluating arctan at " + expectedPoint + ".");
        }
    }

    @Test
    public void testParseUnknownFunction() {
        assertThrows(UnknownFunctionException.class, () -> {
            BaseFunctionFactory.parseFunction("can(x)");
        });
        assertThrows(UnknownFunctionException.class, () -> {
            BaseFunctionFactory.parseFunction("log2(x)");
        });
    }
    
     @Test
    public void testNullArgument() {
        assertThrows(IllegalArgumentException.class, () -> {
            BaseFunctionFactory.parseFunction(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            BaseFunctionFactory.parseFunction("");
        });
    }

}
