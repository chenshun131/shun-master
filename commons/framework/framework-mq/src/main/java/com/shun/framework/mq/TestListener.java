package com.shun.framework.mq;

import com.shun.framework.exception.BusinessLogicException;

/**
 * User: mew <p />
 * Time: 17/11/8 12:40  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TestListener extends GenericEnding {

    public TestListener() {
    }

    @Override
    protected void notifySuccess(GenericResult genericResult) throws BusinessLogicException {
        super.notifySuccess(genericResult);
    }

    @Override
    protected void notifyFailure(GenericResult genericResult) throws BusinessLogicException {
        super.notifyFailure(genericResult);
    }

}
