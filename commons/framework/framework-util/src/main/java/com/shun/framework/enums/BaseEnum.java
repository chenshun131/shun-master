package com.shun.framework.enums;

/**
 * User: mew <p />
 * Time: 17/11/6 11:11  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public interface BaseEnum<E extends Enum<?>, T> {

    T getCode();

}
