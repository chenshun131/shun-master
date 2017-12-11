package com.shun.server.manage;

import com.shun.framework.base.BaseInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: mew <p />
 * Time: 17/11/8 18:04  <p />
 * Version: V1.0  <p />
 * Description: 系统接口 <p />
 */
public class Initialize implements BaseInterface {

    private static Logger _log = LoggerFactory.getLogger(Initialize.class);

    @Override
    public void init() {
        _log.info(">>>>> 系统初始化");
    }

}
