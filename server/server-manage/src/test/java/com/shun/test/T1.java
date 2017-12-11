package com.shun.test;

import com.shun.framework.util.RedisUtil;

/**
 * User: mew <p />
 * Time: 17/11/13 13:42  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T1 {

    public static void main(String[] args) {
        RedisUtil.set("key11", "123");
    }

}
