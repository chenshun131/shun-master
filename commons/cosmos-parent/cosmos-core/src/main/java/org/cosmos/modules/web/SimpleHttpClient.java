package org.cosmos.modules.web;

import com.google.common.collect.Lists;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.cosmos.modules.utils.validate.CommonValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: mew <p />
 * Time: 17/11/6 16:15  <p />
 * Version: V1.0  <p />
 * Description: SIMPLE HTTP CLIENT 工具类
 * <br>
 * 修改日志:
 * <br>
 * 1.增加了基于GET方法的API
 * <br>
 * 2.增加了基于POST方法的一种使用StringEntity来接收请求参数的API.请求参数理论上可以是任意内容任意格式的普通字符串,但通常推荐使用基于JSON格式的字符串.
 * <br>
 * 3.在基于基于GET方法的API中引入了请求头
 * <br>
 * 4.添加CloseableHttpClient类型的私有属性.每一个请求方法中不再单独调用初始化方法和销毁方法,将其交给Spring容器进行管理,避免重复创建 <p />
 */
public class SimpleHttpClient {

    private static final Logger logger = LoggerFactory.getLogger(SimpleHttpClient.class);

    private static final String DEFAULT_CHARSET = "UTF-8";

    private int timeout = 60;

    private int poolSize = 30;

    private CloseableHttpClient closeableHttpClient;

    /**
     * 初始化CloseableHttpClient
     */
    private void init() {
        // --SET TIMEOUT--//
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout * 1000)
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setStaleConnectionCheckEnabled(true)
                .build();

        // --SET POOLSIZE, CREATE HTTP CLIENT--//
        closeableHttpClient = HttpClientBuilder.create().setMaxConnTotal(poolSize).setMaxConnPerRoute(poolSize)
                .setDefaultRequestConfig(requestConfig).build();
    }

    /**
     * POST请求
     *
     * @param url          URL地址
     * @param requestParam 请求参数,通常是基于JSON格式的字符串
     * @param charset      编码
     * @return response
     */
    public String postBySingleString(String url, String requestParam, String charset) {
        CloseableHttpClient httpClient = closeableHttpClient;
        HttpPost httpPost = new HttpPost(url);
        String body = null;

        try {
            if (!CommonValidateUtil.isEmpty(requestParam)) {
                httpPost.setEntity(new StringEntity(requestParam, charset));
            }

            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();

            body = EntityUtils.toString(entity);
            EntityUtils.consumeQuietly(entity);
            //EntityUtils.consume(entity);
        } catch (IOException e) {
            httpPost.abort();
            logger.error("post from remote " + url + "  error", e);
        }
        /*
        finally {
        	destroy(httpClient);
        }
        */
        return body;
    }

    /**
     * POST请求
     *
     * @param url      URL地址
     * @param paramMap 请求参数映射
     * @param charset  编码
     * @return response
     */
    public String postByKeyValuePair(String url, Map<String, String> paramMap, String charset) {
        CloseableHttpClient httpClient = closeableHttpClient;
        HttpPost httpPost = new HttpPost(url);
        String body = null;

        try {
            if (paramMap != null && !paramMap.isEmpty()) {
                List<NameValuePair> params = parseFromParamMap(paramMap);
                if (!params.isEmpty()) {
                    httpPost.setEntity(new UrlEncodedFormEntity(params, charset));
                }
            }

            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();

            body = EntityUtils.toString(entity);
            EntityUtils.consumeQuietly(entity);
            //EntityUtils.consume(entity);
        } catch (IOException e) {
            httpPost.abort();
            logger.error("post from remote " + url + "  error", e);
        }
        /*
        finally {
        	destroy(httpClient);
        }
        */
        return body;
    }


    public String post(String url, String param) {
        return postBySingleString(url, param, DEFAULT_CHARSET);
    }

    public String post(String url, Map<String, String> paramMap) {
        return postByKeyValuePair(url, paramMap, DEFAULT_CHARSET);
    }

    public String post(String url) {
        return postByKeyValuePair(url, null, DEFAULT_CHARSET);
    }

    public String get(String url, Map<String, String> headers, String charset) {
        CloseableHttpClient httpClient = closeableHttpClient;
        HttpGet httpGet = null;
        String body = null;

        //RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).build();
        try {
            //创建HTTP GET请求
            httpGet = new HttpGet(url);
            //System.out.println("executing request " + httpGet.getURI());

            //添加请求头,非必须
            if (headers != null && !headers.isEmpty()) {

                Set<String> headerKeySet = headers.keySet();

                for (String headerKey : headerKeySet) {
                    httpGet.addHeader(headerKey, headers.get(headerKey));
                }
            }

            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

            //获得响应体
            HttpEntity entity = httpResponse.getEntity();

            //这两行代码等同于下面的toString方法
            /*
            byte[] bytes = EntityUtils.toByteArray(entity);

            body = new String(bytes, DEFAULT_CHARSET);
            */

            body = EntityUtils.toString(entity);

            EntityUtils.consumeQuietly(entity);
            /*
            if (entity != null) {
            	EntityUtils.consume(entity);
            }
            */
        } catch (Exception e) {
            httpGet.abort();
            logger.error("get from remote " + url + "  error", e);
        }
        /*
        finally {
            destroy(httpClient);
        }
        */
        return body;
    }

    public String get(String url) {
        return get(url, null, DEFAULT_CHARSET);
    }

    public String get(String url, Map<String, String> headers) {
        return get(url, headers, DEFAULT_CHARSET);
    }

    private List<NameValuePair> parseFromParamMap(Map<String, String> paramMap) {
        List<NameValuePair> nameValuePairs = Lists.newArrayList();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            String paramName = entry.getKey();
            String paramValue = entry.getValue();

            BasicNameValuePair basicNameValuePair = new BasicNameValuePair(paramName, paramValue);
            nameValuePairs.add(basicNameValuePair);
        }
        return nameValuePairs;
    }

    /**
     * 关闭CloseableHttpClient Instance
     */
    private void destroy() {
        try {
            closeableHttpClient.close();
        } catch (IOException e) {
            logger.error("httpclient close fail", e);
        }
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

}
