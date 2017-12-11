package org.cosmos.modules.web;

/**
 * User: mew <p />
 * Time: 17/11/6 16:14  <p />
 * Version: V1.0  <p />
 * Description: 带UTF-8 charset 定义的MediaType. <br/>
 * <p>
 * Jax-RS和Spring的MediaType没有UTF-8的版本， <br/>
 * Google的MediaType必须再调用toString()函数而不是常量，不能用于Restful方法的annotation <p />
 */
public class MediaTypes {

    public final static String APPLICATION_XML = "application/xml";
    public final static String APPLICATION_XML_UTF_8 = "application/xml; charset=UTF-8";

    public final static String JSON = "application/json";
    public final static String JSON_UTF_8 = "application/json; charset=UTF-8";

    public final static String JAVASCRIPT = "application/javascript";
    public final static String JAVASCRIPT_UTF_8 = "application/javascript; charset=UTF-8";

    public final static String APPLICATION_XHTML_XML = "application/xhtml+xml";
    public final static String APPLICATION_XHTML_XML_UTF_8 = "application/xhtml+xml; charset=UTF-8";

    public final static String TEXT_PLAIN = "text/plain";
    public final static String TEXT_PLAIN_UTF_8 = "text/plain; charset=UTF-8";

    public final static String TEXT_XML = "text/xml";
    public final static String TEXT_XML_UTF_8 = "text/xml; charset=UTF-8";

    public final static String TEXT_HTML = "text/html";
    public final static String TEXT_HTML_UTF_8 = "text/html; charset=UTF-8";

    public final static String IMAGE_JPEG = "image/jpeg";
    public final static String IMAGE_JPEG_UTF_8 = "image/jpeg; charset=UTF-8";

    public final static String IMAGE_PNG = "image/png";
    public final static String IMAGE_PNG_UTF_8 = "image/png; charset=UTF-8";

    public final static String IMAGE_GIF = "image/gif";
    public final static String IMAGE_GIF_UTF_8 = "image/gif; charset=UTF-8";

}
