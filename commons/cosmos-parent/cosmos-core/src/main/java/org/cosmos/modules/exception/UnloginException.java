package org.cosmos.modules.exception;

/**
 * User: mew <p />
 * Time: 17/11/6 14:21  <p />
 * Version: V1.0  <p />
 * Description: 自定义用户未登录exception <p />
 */
public class UnloginException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnloginException() {
        super("用户未登录");
    }

}
