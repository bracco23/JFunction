/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.bracco23.jfunctions.tree;

import java.util.Objects;
import java.util.function.Function;

/**
 *
 * @author Emiddio
 */
public class FunctionNode extends Node {

    private Function<Double, Double> function;

    public FunctionNode(Function<Double, Double> function, Node right) {
        super(null, right);
        this.function = function;
    }

    @Override
    public double evalutateNode(double x) {
        if (getRight() != null) {
            return function.apply(getRight().evalutateNode(x));
        } else {
            return function.apply(x);
        }
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 97 * hash + Objects.hashCode(this.function);
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
        final FunctionNode other = (FunctionNode) obj;
        if (!Objects.equals(this.function, other.function)) {
            return false;
        }
        return super.equals(obj);
    }
    
    

}
