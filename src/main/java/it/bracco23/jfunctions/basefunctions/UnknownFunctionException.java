/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.bracco23.jfunctions.basefunctions;

/**
 * Exception thrown when a string is not a recognized function.
 * @author Emiddio
 */
public class UnknownFunctionException extends RuntimeException{

    public UnknownFunctionException() {
    }

    public UnknownFunctionException(String message) {
        super(message);
    }

    public UnknownFunctionException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownFunctionException(Throwable cause) {
        super(cause);
    }

    public UnknownFunctionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
