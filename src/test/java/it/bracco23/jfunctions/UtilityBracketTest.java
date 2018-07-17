/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.bracco23.jfunctions;

import it.bracco23.jfunctions.Utility;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Emiddio
 */
public class UtilityBracketTest {

    public UtilityBracketTest() {
    }

    private void genericTest(String toTest, boolean expectedOutcome) {
        boolean result = Utility.checkBrackets(toTest);
        String message = "String " + toTest + " has failed the test.";
        assertEquals(expectedOutcome, result, message);
    }

    /**
     * Test of checkBrackets method, of class Utility.
     */
    @Test
    public void testCheckBracketsSuccess() {
        genericTest("()", true);
        genericTest("(())", true);
        genericTest("()(())", true);
        genericTest("(())(())", true);
        genericTest("(()())", true);
        genericTest("((())(()))", true);
    }
    
    /**
     * Test of checkBrackets method, of class Utility.
     */
    @Test
    public void testCheckBracketsFail() {
        genericTest("(", false);
        genericTest("(()", false);
        genericTest("())", false);
        genericTest("()())", false);
        genericTest("())(())", false);
        genericTest("()())", false);
        genericTest("((())(())", false);
    }
}
