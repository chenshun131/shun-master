package org.cosmos.modules.exception;

/**
 * User: mew <p />
 * Time: 17/11/6 14:20  <p />
 * Version: V1.0  <p />
 * Description: 业务异常 <p />
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -310591244765467303L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
