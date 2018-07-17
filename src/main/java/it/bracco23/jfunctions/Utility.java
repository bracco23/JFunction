/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.bracco23.jfunctions;

import java.util.Stack;

/**
 * Wrapper class with some static methods of general utility about string
 *
 * @author emiddio
 */
public class Utility {

    /**
     * Check if the string contains a balanced amount of curve brackets only. It
     * check the content of the string to see if there is a balanced amount of
     * curve brackets. It return true if there is an equal number open '(' and
     * closed ')' and if the brackets are well placed (each breackets is first
     * opened and then closed)
     *
     * @param s the string that will be checked
     * @return true if the brackets are balanced, false otherwise
     */
    static public boolean checkBrackets(String s) {
        int i, count = 0;

        for (i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                    count++;
                    break;
                case ')':
                    count--;
                    break;
            }
        }
        return count == 0;
    }

    /**
     * Parse a String for numbers adding some useful math symbol.
     *
     * Simply pass the string to the {@link } function, taking care of the
     * decimal sign and evaluating symbolic values ad pi or e (neperian number);
     *
     * @param str the string to be parsed
     * @return the number read
     * @throws NullPointerException if the string passed is null or empty
     * @throws IllegalArgumentException if the given string is the empty string
     * @throws NumberFormatException if the string does not contain a parsable
     * double.
     * @see Utility#isNumber(String)
     */
    static public double parseNumber(String str) {
        if (str == null) {
            throw new NullPointerException("The argument cannot be null");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("The string cannot be empty");
        }
        switch (str) {
            case "e":
                return Math.E;
            case "-e":
                return -Math.E;
            case "pi":
                return Math.PI;
            case "-pi":
                return -Math.PI;
            default:
                str = str.replace(",", ".");
                return Double.parseDouble(str);
        }
        
    }

    /**
     * Check the string for parsable double numbers
     *
     * Use the {@link } to check if there is a valid parsable numbers,
     * evaluating symbolic costants as 'pi' or neperian number 'e'
     *
     * @param str the string to be parsed
     * @return true if the number in the string can be parsed, false otherwise
     */
    public static boolean isNumber(String str) {
        boolean toReturn;
        try {
            Utility.parseNumber(str);
            toReturn = true;
        } catch (NullPointerException | IllegalArgumentException e) {
            toReturn = false;
        }
        return toReturn;

    }
}
