package com.shun.framework.mq;

import com.shun.framework.mq.handler.notify.AbstractNotifyMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * User: mew <p />
 * Time: 17/11/8 10:53  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public abstract class GenericCastMessageHandler<T> extends AbstractNotifyMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(GenericCastMessageHandler.class);

    public GenericCastMessageHandler() {
    }

    @PostConstruct
    public void configFailureQuque() {
        this.setFailedQueue(this.getDestination() + ".failure");
    }

    @Override
    public void handleMessage(Object request) throws Exception {
        logger.trace(">>>q:{},message:{}", this.getDestination(), request);
        try {
            this.handleCastedMessage((T) request);
        } catch (Exception var3) {
            throw var3;
        }
    }

    protected String getId(T request) {
        return "";
    }

    protected abstract void handleCastedMessage(T var1) throws Exception;

}
