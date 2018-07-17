/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.bracco23.jfunctions.tree;

import it.bracco23.jfunctions.tree.NodeFactory;
import it.bracco23.jfunctions.tree.ImpossibleNodeException;
import it.bracco23.jfunctions.tree.FunctionNode;
import it.bracco23.jfunctions.tree.OperatorNode;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Emiddio
 */
public class NodeFactoryTest {

    public NodeFactoryTest() {
    }

    @Test
    public void testWrongNodeFunction() {
        Function<Double, Double> f = null;
        NodeFactory nf = new NodeFactory(f);
        assertThrows(ImpossibleNodeException.class, () -> {
            nf.createNode(null, null);
        });
    }

    @Test
    public void testWrongNodeOperator() {
        BinaryOperator<Double> f = null;
        NodeFactory nf = new NodeFactory(f);
        assertThrows(ImpossibleNodeException.class, () -> {
            nf.createNode(null, null);
        });
    }

    @Test
    public void testCreateOperatorNode() {
        FunctionNode temp = new FunctionNode(Math::sin, null);
        NodeFactory nf = new NodeFactory(Math::max);
        assertThat(nf.createNode(temp, temp)).isInstanceOf(OperatorNode.class);
    }

    @Test
    public void testCreateFunctionNode() {
        NodeFactory nf = new NodeFactory(Math::sin);
        assertThat(nf.createNode(null, null)).isInstanceOf(FunctionNode.class);
    }
}
