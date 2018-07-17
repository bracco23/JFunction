/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.bracco23.jfunctions.tree;

import it.bracco23.jfunctions.tree.FunctionNode;
import it.bracco23.jfunctions.basefunctions.ConstantFunction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Emiddio
 */
public class FunctionNodeTest {
    
    public FunctionNodeTest() {
    }
    
    /**
     * Test of evalutateNode method, of class FunctionNode.
     */
    @Test
    public void testEvalutateLeafNode() {
        FunctionNode instance = new FunctionNode(Math::abs, null);
        double[] x = {-2, -1.5, 0, 1, 2.5};
        double[] y = {2, 1.5, 0, 1, 2.5};
        for(int i = 0; i < x.length; i++){
            assertEquals(y[i], instance.evalutateNode(x[i]), 1e-7, "Evaluating abs");
        }
    }
    
    /**
     * Test of evalutateNode method, of class FunctionNode.
     */
    @Test
    public void testEvalutateIntermediateNode() {
        double[] x = {-2, -1.5, 0, 1, 2.5};
        double[] y = {2, 1.5, 0, 1, 2.5};
        for(int i = 0; i < x.length; i++){
            FunctionNode leaf = new FunctionNode(new ConstantFunction(x[i]), null);
            FunctionNode instance = new FunctionNode(Math::abs, leaf);
            assertEquals(y[i], instance.evalutateNode(x[i]), 1e-7, "Evaluating node with child");
        }
    }
    
}
