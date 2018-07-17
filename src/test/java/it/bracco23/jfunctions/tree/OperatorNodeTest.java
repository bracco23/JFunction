/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.bracco23.jfunctions.tree;

import it.bracco23.jfunctions.tree.ImpossibleNodeException;
import it.bracco23.jfunctions.tree.FunctionNode;
import it.bracco23.jfunctions.tree.OperatorNode;
import it.bracco23.jfunctions.basefunctions.IdentityFunction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Emiddio
 */
public class OperatorNodeTest {

    public OperatorNodeTest() {
    }

    @Test
    public void testEmptyOperatorNodeException() {
        FunctionNode temp = new FunctionNode(new IdentityFunction(), null);
        assertThrows(ImpossibleNodeException.class, () -> {
            OperatorNode n = new OperatorNode((a, b) -> (a + b), null, temp);
        }, "Right child null");
        assertThrows(ImpossibleNodeException.class, () -> {
            OperatorNode n = new OperatorNode((a, b) -> (a + b), temp, null);
        }, "Left child null");
        assertThrows(ImpossibleNodeException.class, () -> {
            OperatorNode n = new OperatorNode((a, b) -> (a + b), null, null);
        }, "Both child null");
    }

    @Test
    public void testEvalutateNode() {
        FunctionNode temp = new FunctionNode(new IdentityFunction(), null);
        OperatorNode instance = new OperatorNode((a, b) -> (a + b), temp, temp);
        double[] x = {-2, -1.5, 0, 1, 2.3};
        double[] y = {-4, -3, 0, 2, 4.6};
        for (int i = 0; i < x.length; i++) {
            assertEquals(y[i], instance.evalutateNode(x[i]), 1e-7, "Evaluating sum node");
        }
    }

}
