package com.shun.server.manage.constant;

import com.shun.framework.base.BaseResult;

/**
 * User: mew <p />
 * Time: 17/11/9 08:40  <p />
 * Version: V1.0  <p />
 * Description: upms系统常量枚举类 <p />
 */
public class UpmsResult extends BaseResult {

    public UpmsResult(UpmsResultConstant upmsResultConstant, Object data) {
        super(upmsResultConstant.getCode(), upmsResultConstant.getMessage(), data);
    }

}
