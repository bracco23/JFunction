/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.bracco23.jfunctions.tree;

import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 *
 * @author Emiddio
 */
public class NodeFactory {
    
    Function<Double, Double> f;
    BinaryOperator<Double> op;

    public NodeFactory(Function<Double, Double> f) {
        this.f = f;
    }

    public NodeFactory(BinaryOperator<Double> op) {
        this.op = op;
    }
    
    public Node createNode(Node left, Node right){
        if(f != null && op == null){
            return new FunctionNode(f, right);
        }else if(op != null && f == null){
            return new OperatorNode(op, left, right);
        }else{
            throw new ImpossibleNodeException("A node must have at least an operation or a function to perform.");
        }
    }
}
