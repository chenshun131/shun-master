package com.shun.framework.mq;

import org.springframework.beans.factory.annotation.Required;

/**
 * User: mew <p />
 * Time: 17/11/8 12:39  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class GenericResultDelegate extends GenericCastMessageHandler<GenericResult> {

    private GenericHandler genericHandler;

    public GenericResultDelegate() {
    }

    @Override
    protected String getId(GenericResult request) {
        return request.getBusinessId();
    }

    @Override
    protected void handleCastedMessage(GenericResult message) throws Exception {
        this.genericHandler.handleResult(message);
    }

    @Required
    public void setGenericHandler(GenericHandler genericHandler) {
        this.genericHandler = genericHandler;
    }

}
