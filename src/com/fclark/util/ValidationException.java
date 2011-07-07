/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fclark.util;

/**
 *
 * @author Frederick
 * @version 07/06/2011 10:00:36 PM
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException() {
    }
    
}
