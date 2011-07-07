
package com.fclark.util;

/**
 *
 * @author Frederick
 * @version 07/05/2011 09:49:04 PM
 */
public class UnimplementedMethodException extends RuntimeException {

    public UnimplementedMethodException(String message) {
        super(message);
    }

    public UnimplementedMethodException() {
        super("This method should be overriden");
    }   
}
