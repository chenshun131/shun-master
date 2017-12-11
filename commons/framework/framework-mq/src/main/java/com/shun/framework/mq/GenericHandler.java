package com.shun.framework.mq;

import com.shun.framework.exception.BusinessLogicException;

/**
 * User: mew <p />
 * Time: 17/11/8 11:56  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface GenericHandler {

    void handleResult(GenericResult var1) throws BusinessLogicException;

}
