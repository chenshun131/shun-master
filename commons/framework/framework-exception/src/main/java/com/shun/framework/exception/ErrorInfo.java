package com.shun.framework.exception;

/**
 * User: mew <p />
 * Time: 17/11/6 10:53  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface ErrorInfo extends WithTrace, WithErrorCode {

    String getMessage();

}
