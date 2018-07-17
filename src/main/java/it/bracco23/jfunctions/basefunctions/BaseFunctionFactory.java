/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.bracco23.jfunctions.basefunctions;

import it.bracco23.jfunctions.Utility;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class parses a string and create the right Function. This class offers a
 * method to parse a string and creates the right function that represent that
 * string. The function does not take into account any nested operation, but it
 * only allows to calculate the first level. Further parsing is required to
 * completely elaborate the string.
 *
 *
 * @author Emiddio
 */
public class BaseFunctionFactory {

    static final Map<String, Function<Double, Double>> SUPPORTED_FUNCTIONS = new HashMap<>();

    static {
        SUPPORTED_FUNCTIONS.put("arcsin", Math::asin);
        SUPPORTED_FUNCTIONS.put("arcsen", Math::asin);
        SUPPORTED_FUNCTIONS.put("asin", Math::asin);
        SUPPORTED_FUNCTIONS.put("asen", Math::asin);
        SUPPORTED_FUNCTIONS.put("arccos", Math::acos);
        SUPPORTED_FUNCTIONS.put("acos", Math::acos);
        SUPPORTED_FUNCTIONS.put("arcos", Math::acos);
        SUPPORTED_FUNCTIONS.put("arctan", Math::atan);
        SUPPORTED_FUNCTIONS.put("atan", Math::atan);
        SUPPORTED_FUNCTIONS.put("sinh", Math::sinh);
        SUPPORTED_FUNCTIONS.put("senh", Math::sinh);
        SUPPORTED_FUNCTIONS.put("cosh", Math::cosh);
        SUPPORTED_FUNCTIONS.put("tanh", Math::tanh);
        SUPPORTED_FUNCTIONS.put("sin", Math::sin);
        SUPPORTED_FUNCTIONS.put("sen", Math::sin);
        SUPPORTED_FUNCTIONS.put("cos", Math::cos);
        SUPPORTED_FUNCTIONS.put("tan", Math::tan);
        SUPPORTED_FUNCTIONS.put("abs", Math::abs);
        SUPPORTED_FUNCTIONS.put("log", Math::log);
    }

    /**
     * Parses a string and gives back the function represented. It parses a
     * string ang gives back the function represented, in the form of
     * {@link java.util.function.Function}.
     *
     * @param str the string to parse
     * @throws UnknownFunctionException if the given string is not a recognized
     * function
     * @throws IllegalArgumentException if str is null or empty
     * @return the function represented
     */
    public static Function<Double, Double> parseFunction(String str) {
        if(str == null || str.length() <= 0){
           throw new IllegalArgumentException();
        }
        String functions = SUPPORTED_FUNCTIONS.keySet().stream()
                .map((String f) -> ("(" + f + ")"))
                .collect(Collectors.joining("|"));
        Pattern p = Pattern.compile("(?<fun>" + functions + ")(?<arg>\\(.*\\))", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(str);
        if (m.matches() && Utility.checkBrackets(m.group("arg"))) {
            return SUPPORTED_FUNCTIONS.get(m.group("fun"));
        } else if ("x".equals(str.trim())) {
            return new IdentityFunction();
        } else if (Utility.isNumber(str)) {
            return new ConstantFunction(Utility.parseNumber(str));
        }
        throw new UnknownFunctionException("Unknown function given.");
    }
}
