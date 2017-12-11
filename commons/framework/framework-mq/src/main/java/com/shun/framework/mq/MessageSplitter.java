package com.shun.framework.mq;

import com.shun.framework.mq.request.notify.DefaultNotifyRequest;

/**
 * User: mew <p />
 * Time: 17/11/8 10:35  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class MessageSplitter {

    public MessageSplitter() {
    }

    public String getContent(DefaultNotifyRequest<String> request) {
        return (String) request.getContent();
    }

}
