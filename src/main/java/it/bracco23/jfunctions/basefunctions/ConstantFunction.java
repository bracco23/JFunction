/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.bracco23.jfunctions.basefunctions;

import java.util.function.Function;

/**
 *  Represents a Contant function.
 *  This class represents a costant function, one in which:
 *  <ul>
 *      <li> f(x) = c </li>
 *  </ul>
 * 
 * @author Emiddio
 */
public class ConstantFunction implements Function<Double, Double> {

    private double value = 0;

    /**
     * Default constructor.
     * It will initilize it with a 0 constant.
     */
    public ConstantFunction() {

    }

    /**
     * Function f(x) = value.
     * @param value the constant value of the function
     */
    public ConstantFunction(double value) {
        this.value = value;
    }

    @Override
    public Double apply(Double x) {
        return this.value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.value) ^ (Double.doubleToLongBits(this.value) >>> 32));
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
        final ConstantFunction other = (ConstantFunction) obj;
        if (Double.doubleToLongBits(this.value) != Double.doubleToLongBits(other.value)) {
            return false;
        }
        return true;
    }
    
    

}
