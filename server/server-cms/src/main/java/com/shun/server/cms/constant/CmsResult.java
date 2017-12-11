package com.shun.server.cms.constant;

import com.shun.framework.base.BaseResult;

/**
 * User: mew <p />
 * Time: 17/11/27 11:03  <p />
 * Version: V1.0  <p />
 * Description: cms系统常量枚举类 <p />
 */
public class CmsResult extends BaseResult {

    public CmsResult(CmsResultConstant cmsResultConstant, Object data) {
        super(cmsResultConstant.getCode(), cmsResultConstant.getMessage(), data);
    }

}
