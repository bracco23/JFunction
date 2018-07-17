/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.bracco23.jfunctions;

import it.bracco23.jfunctions.tree.Node;
import it.bracco23.jfunctions.basefunctions.BaseFunctionFactory;
import it.bracco23.jfunctions.basefunctions.ConstantFunction;
import it.bracco23.jfunctions.basefunctions.UnknownFunctionException;
import it.bracco23.jfunctions.tree.NodeFactory;
import java.io.Serializable;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.zip.DataFormatException;

/**
 * It parses and stores in a usable way mathematical
 * function of all type.
 * <p>
 * The parser recognize this operator, listed by priority:<br>
 * <table style="width: 200px; border: 1; border-color: black;">
 * <caption>Recognized operators</caption>
 * <tr style="align: center; font-weight: bold;"><td style="width:
 * 50;">Symbol</td><td>Meaning</td></tr> <tr><td style="width:
 * 50;">^</td><td>Power elevation</td></tr> <tr><td style="width:
 * 50;">*</td><td>Multiplication</td></tr> <tr><td style="width: 50;">"\" or
 * "/"</td><td>Division</td></tr> <tr><td style="width:
 * 50;">+</td><td>Addition</td></tr> <tr><td style="width:
 * 50;">-</td><td>Subtraction</td></tr></table> <br>It also recognize the
 * basic function in different case inensitive ways:<table style="border: 1;
 * border-color: black;"><caption>Recognized functions</caption><tr style="align: center; font-weight:
 * bold;"><td>Function</td><td>Accepted Symbol</td></tr>
 * <tr><td>Sine</td><td>"sin" or "sen"</td></tr>
 * <tr><td>Cosine</td><td>"cos"</td></tr>
 * <tr><td>Tangent</td><td>"tan"</td></tr> <tr><td>Hyperbolic
 * Sine</td><td>"sinh"</td></tr> <tr><td>Hyperbolic
 * Cosine</td><td>"cosh"</td></tr> <tr><td>Hyperbolic tangent</td><td
 * >"tanh"</td></tr> <tr><td> Arcsin (inverse function of
 * sine)</td><td>"arcsin"</td></tr> <tr><td>Arcosine (inverse function of
 * cosine)</td><td>"arccos"</td></tr> <tr><td>Arctangent (inverse function of
 * tangent)</td><td>"arctan"</td></tr> <tr><td>Natural logarithm (logarith with
 * base e)</td><td>"log"</td></tr> <tr><td>Absolute
 * Value</td><td>"abs"</td></tr></table><br> <br> The function recognizes
 * only round brackets, mandatory around function arguments and to change
 * operator's priority.<br><br>The class is Thread-safe, and uses a
 * Read-Write Lock that allows to perform multiple reading operation when
 * multiple threads are sharing the object.
 *
 * @author emiddio
 */
public class Function implements Serializable, java.util.function.Function<Double, Double> {

    private String input;
    private Node tree;
    private char var;
    private final ReentrantReadWriteLock lock;

    /**
     * The char representing the indipendent variable.
     *
     * @return the char representing the indipendent variable
     */
    public char getVar() {
        return var;
    }

    /**
     * Create a new function parsing the given String using 'x' as indipendent
     * variable. Parses the string assuming that the character 'x' is used to
     * represent the indipendent value.
     *
     * @param in the String representing the function
     * @throws IllegalArgumentException if the string contains errors or does not
     * represent a valid function string
     */
    public Function(String in) {
        this.lock = new ReentrantReadWriteLock();
        this.input = in;
        this.var = 'x';
        try {
            lock.writeLock().lock();
            this.tree = createTree(in);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Create a new function parsing the given String, using the given
     * indipendent variable. Parses the given string creating a function with x
     * as indipendent variable.
     *
     * @param in the String representing the function
     * @param x the indipendent variable
     * @throws IllegalArgumentException if the string contains errors or does not
     * represent a valid function
     */
    public Function(String in, char x){
        this.lock = new ReentrantReadWriteLock();
        this.input = in;
        this.var = x;
        try {
            lock.writeLock().lock();
            this.tree = createTree(in.replace(x, 'x'));
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Substitute the function stored with the new one. Change the function
     * holded by the object with the one represented by the given string. It is
     * equivalent to:<br><br>
     *
     * Function f=new Function(String s1);<br> f=new Function(input);<br>
     *
     * At the end of the substitution, it invoke the Garbage Collector to
     * release the memory used by the old function.
     *
     * @param input the string representing the function
     * @throws IllegalArgumentException if the string contains errors or does
     * not represent a valid function
     */
    public void setFunction(String input){
        try {
            lock.writeLock().lock();
            this.tree = createTree(input);
        } finally {
            lock.writeLock().unlock();
        }
        this.var = 'x';
        this.input = input;
    }

    /**
     * Substitute the function stored with the new one. Same as the
     * one-parameter method, but it looks for the given char as indipendent
     * variable instead of the default 'x'.
     *
     * @param input string, the new function to be parsed
     * @param var char, the indipendent variable of the function passed
     * @throws IllegalArgumentException thrown if the input string is not
     * correctly formatted
     * @see setFunction
     */
    public void setFunction(String input, char var) {
        try {
            lock.writeLock().lock();
            this.tree = createTree(input.replace(var, 'x'));
        } finally {
            lock.writeLock().unlock();
        }
        this.var = var;
        this.input = input;
    }

    private Node createTree(String in) {
        NodeFactory nf = null;
        java.util.function.Function<Double, Double> funz;
        Node temp, right = null, left = null;
        in = in.trim();
        int k = firstOperatorPosition(in);
        if (in.length() == 0) {
            throw new IllegalArgumentException();
        }
        if (in.charAt(0) == '(' && in.charAt(in.length() - 1) == ')' && k == -1) {
            return createTree(in.substring(1, in.length() - 1));
        }
        switch (k) {
            case -1:
                try {
                    funz = BaseFunctionFactory.parseFunction(in);
                    nf = new NodeFactory(funz);
                    if (in.indexOf('(') != -1) {
                        right = createTree(in.substring(in.indexOf('(')));
                    } else {
                        throw new IllegalArgumentException("Functions must have parenthesis");
                    }
                } catch (UnknownFunctionException ex) {
                    throw new IllegalArgumentException(ex);
                }
            case 0:
                if (in.charAt(0) == '+') {
                    return createTree(in.substring(1));
                } else if (in.charAt(0) == '-') {
                    if (Utility.isNumber(in.substring(1))) {
                        nf = new NodeFactory(new ConstantFunction(Utility.parseNumber(in)));
                    } else {
                        nf = new NodeFactory((a) -> (-a));
                        right = createTree(in.substring(1));
                    }
                }
                break;
            default:
                switch (in.charAt(k)) {
                    case '+':
                        nf = new NodeFactory((a, b) -> (a + b));
                        left = createTree(in.substring(0, k));
                        right = createTree(in.substring(k + 1));
                        break;
                    case '-':
                        nf = new NodeFactory((a, b) -> (a - b));
                        left = createTree(in.substring(0, k));
                        right = createTree(in.substring(k + 1));
                        break;
                    case '/':
                    case '\\':
                        nf = new NodeFactory((a, b) -> (a / b));
                        left = createTree(in.substring(0, k));
                        right = createTree(in.substring(k + 1));
                        break;
                    case '*':
                        nf = new NodeFactory((a, b) -> (a * b));
                        left = createTree(in.substring(0, k));
                        right = createTree(in.substring(k + 1));
                        break;
                    case '^':
                        nf = new NodeFactory(Math::pow);
                        left = createTree(in.substring(0, k));
                        right = createTree(in.substring(k + 1));
                        break;
                }
                break;
        }
        if (nf != null) {
            temp = nf.createNode(left, right);
        } else {
            throw new IllegalArgumentException("Invalid function provided");
        }
        return temp;
    }

    private int firstOperatorPosition(String str) {
        int i;
        for (i = str.length() - 1; i >= 0; i--) {
            if (Utility.checkBrackets(str.substring(0, i)) && (str.charAt(i) == '+' || str.charAt(i) == '-')) {
                return i;
            }
        }
        for (i = str.length() - 1; i >= 1; i--) {
            if (Utility.checkBrackets(str.substring(0, i)) && str.charAt(i) == '*') {
                return i;
            }
        }
        for (i = str.length() - 1; i >= 1; i--) {
            if (Utility.checkBrackets(str.substring(0, i)) && (str.charAt(i) == '/' || (str.charAt(i) == '\\'))) {
                return i;
            }
        }

        for (i = str.length() - 1; i >= 1; i--) {
            if (Utility.checkBrackets(str.substring(0, i)) && str.charAt(i) == '^') {
                return i;
            }
        }
        return -1;
    }

    /**
     * Return the value of the function in the given point. It evaluate the
     * function in the given point, returning the value of the function. If the
     * function does not exist on that particular point, it returns Nan. if the
     * value of the function is higher than Double.MAX_VALUE or lower than
     * Double.MIN_VALUE, it returns +inf and -inf. You can check the values
     * using Double.isNaN() and Double.isInfinite()
     *
     * @param x the value in which evaluate the function
     * @return the value evaluated
     */
    @Override
    public Double apply(Double x) {
        try {
            lock.readLock().lock();
            if (tree != null) {
                return tree.evalutateNode(x);
            } else {
                return 0.0;
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public String toString() {
        try {
            lock.readLock().lock();
            return input;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Function other = (Function) obj;
        if (tree != null) {
            return tree.equals(other.tree);
        }
        return other.tree == null;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.tree != null ? this.tree.hashCode() : 0);
        return hash;
    }
}
