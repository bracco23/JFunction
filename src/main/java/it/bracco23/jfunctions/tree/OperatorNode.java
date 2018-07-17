/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.bracco23.jfunctions.tree;

import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 *
 * @author Emiddio
 */
public class OperatorNode extends Node{
    
    private BinaryOperator<Double> operator;

    public OperatorNode(BinaryOperator<Double> operator, Node left, Node right) {
        super(left, right);
        if(left == null || right == null){
            throw new ImpossibleNodeException("Operators must have two operands.");
        }
        this.operator = operator;
    }

    @Override
    public double evalutateNode(double x) {
        return operator.apply(getLeft().evalutateNode(x), getRight().evalutateNode(x));
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 59 * hash + Objects.hashCode(this.operator);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OperatorNode other = (OperatorNode) obj;
        if (!Objects.equals(this.operator, other.operator)) {
            return false;
        }
        return super.equals(obj);
    }
    
    
    
}
