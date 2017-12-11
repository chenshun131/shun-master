package com.shun.framework.mq.handler.http;

import com.shun.framework.mq.handler.impl.AbstractMessageHandler;
import com.shun.framework.mq.request.http.DefaultHttpRequest;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/8 11:28  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DefaultHttpMessageHandler extends AbstractMessageHandler {

    private static Log log = LogFactory.getLog(DefaultHttpMessageHandler.class);

    private HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());

    private String contentType = "text/html";

    private String charset = "utf-8";

    public DefaultHttpMessageHandler() {
        this.client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        this.client.getHttpConnectionManager().getParams().setSoTimeout(10000);
    }

    public void handleMessage(Object request) throws Exception {
        if (log.isInfoEnabled()) {
            log.info("Request: " + request);
        }
        DefaultHttpRequest httpRequest = (DefaultHttpRequest) request;
        HttpMethod httpMethod = null;
        try {
            if (httpRequest.getMethod().equalsIgnoreCase("post")) {
                httpMethod = this.createPostMethod(httpRequest);
            } else {
                httpMethod = this.createGetMethod(httpRequest);
            }
            int status = this.client.executeMethod(httpMethod);
            if (status != 200) {
                throw new HttpException("Http reqest failed.");
            }
            if (log.isInfoEnabled()) {
                String resp = httpMethod.getResponseBodyAsString();
                log.info("Response: " + resp);
            }
        } finally {
            if (httpMethod != null) {
                httpMethod.releaseConnection();
            }
        }
    }

    private HttpMethod createGetMethod(DefaultHttpRequest httpRequest) {
        GetMethod method = new GetMethod();
        StringBuilder queryStr = new StringBuilder();
        Map<String, String> params = this.getRequestData(httpRequest);
        if (params != null && !params.isEmpty()) {
            for (Object key : params.keySet()) {
                queryStr.append(key).append("=").append(params.get(key)).append("&");
            }
        }
        method.setQueryString(queryStr.substring(0, queryStr.length() - 1));
        return method;
    }

    private HttpMethod createPostMethod(DefaultHttpRequest httpRequest) throws UnsupportedEncodingException {
        PostMethod method = new PostMethod(httpRequest.getUrl());
        Map<String, String> params = this.getRequestData(httpRequest);
        if (params != null && !params.isEmpty()) {
            for (Object key : params.keySet()) {
                method.addParameter((String) key, params.get(key));
            }
        }
        return method;
    }

    public Map<String, String> getRequestData(DefaultHttpRequest httpRequest) {
        return httpRequest.getParams();
    }

    public String getContentType(DefaultHttpRequest httpRequest) {
        String type = httpRequest.getContentType();
        return type != null ? type : this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCharset() {
        return this.charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

}
