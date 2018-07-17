/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.bracco23.jfunctions.basefunctions;

import java.util.function.Function;

/**
 *  Represents an identity function.
 *  This class represents an identity function, one in which:
 *  <ul>
 *      <li> f(x) = x </li>
 *  </ul>
 * 
 * @author Emiddio
 */
public class IdentityFunction implements Function<Double, Double>{
    

    public IdentityFunction() {
    }
    
    @Override
    public Double apply(Double x) {
        return x;
    }
    
}
