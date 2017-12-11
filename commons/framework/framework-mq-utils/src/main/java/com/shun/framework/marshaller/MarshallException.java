package com.shun.framework.marshaller;

/**
 * User: mew <p />
 * Time: 17/11/8 10:12  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MarshallException extends RuntimeException {

    private static final long serialVersionUID = 9190333600846848361L;

    public MarshallException() {
    }

    public MarshallException(String message, Throwable cause) {
        super(message, cause);
    }

    public MarshallException(String message) {
        super(message);
    }

    public MarshallException(Throwable cause) {
        super(cause);
    }

}
