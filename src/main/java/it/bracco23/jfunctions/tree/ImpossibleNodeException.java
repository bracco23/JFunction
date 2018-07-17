/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.bracco23.jfunctions.tree;

/**
 *
 * @author Emiddio
 */
public class ImpossibleNodeException extends RuntimeException{

    public ImpossibleNodeException() {
    }

    public ImpossibleNodeException(String message) {
        super(message);
    }

    public ImpossibleNodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImpossibleNodeException(Throwable cause) {
        super(cause);
    }
    
}
