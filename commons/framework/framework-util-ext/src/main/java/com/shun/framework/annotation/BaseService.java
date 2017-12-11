package com.shun.framework.annotation;

import java.lang.annotation.*;

/**
 * User: mew <p />
 * Time: 17/11/6 17:43  <p />
 * Version: V1.0  <p />
 * Description: 初始化继承BaseService的service <p />
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseService {

}
