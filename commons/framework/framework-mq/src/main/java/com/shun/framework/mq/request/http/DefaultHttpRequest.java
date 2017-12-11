package com.shun.framework.mq.request.http;

import com.shun.framework.mq.request.MQRequest;
import com.shun.framework.mq.request.common.AbstractRequest;

import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/8 10:46  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DefaultHttpRequest extends AbstractRequest {

    private static final long serialVersionUID = -8180821459272721337L;

    private String url;

    private String method = "post";

    private String contentType = "text/html";

    private Map<String, String> params;

    public DefaultHttpRequest() {
    }

    /** @deprecated  */
    @Deprecated
    public DefaultHttpRequest(Integer actionId, String url, Map<String, String> params) {
        this.setActionId(actionId.intValue());
        this.url = url;
        this.params = params;
    }

    /** @deprecated  */
    @Deprecated
    public DefaultHttpRequest(Integer actionId, String url, String method, String contentType
            , Map<String, String> params) {
        this.setActionId(actionId.intValue());
        this.url = url;
        this.method = method;
        this.contentType = contentType;
        this.params = params;
    }

    public DefaultHttpRequest(String queueName, String url, Map<String, String> params) {
        this.setDestination(queueName);
        this.url = url;
        this.params = params;
    }

    public DefaultHttpRequest(String destination, int destinationType, String url, Map<String, String> params) {
        this.setDestination(destination);
        this.setDestinationType(destinationType);
        this.url = url;
        this.params = params;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getParams() {
        return this.params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public MQRequest deepCopy() {
        DefaultHttpRequest request = new DefaultHttpRequest();
        this.copyProperties(request);
        return request;
    }

    protected void copyProperties(MQRequest dest) {
        super.copyProperties(dest);
        if (dest != null && dest instanceof DefaultHttpRequest) {
            DefaultHttpRequest request = (DefaultHttpRequest) dest;
            request.setContentType(this.contentType);
            request.setMethod(this.method);
            request.setUrl(this.url);
            request.setParams(this.params);
        }
    }

    public String toString() {
        return "DefaultHttpRequest [url=" + this.url + ", method=" + this.method + ", contentType=" + this.contentType
                + ", params=" + this.params + "]";
    }

}
