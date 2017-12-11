package com.shun.framework.filter;

import com.alibaba.dubbo.rpc.*;
import com.shun.framework.util.LoggerUtil;
import com.shun.framework.util.UserIpUtil;
import org.cosmos.modules.utils.PropertiesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/6 11:16  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class DubboAuthorityFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DubboAuthorityFilter.class);

    private static final String IP_WHITE_LIST = "dubbo.ip.white.list";

    private PropertiesLoader propertiesLoader;

    public DubboAuthorityFilter() {
    }

    public void setPropertiesLoader(PropertiesLoader propertiesLoader) {
        this.propertiesLoader = propertiesLoader;
    }

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String whiteIpStr = this.propertiesLoader.getProperty("dubbo.ip.white.list");
        List<String> whiteIpList = UserIpUtil.convertIpStrToList(whiteIpStr);
        if (whiteIpList == null) {
            return invoker.invoke(invocation);
        } else {
            String clientIp = RpcContext.getContext().getRemoteHost();
            LoggerUtil.info(LOGGER, "----------The remote host IP is:{}", new Object[]{clientIp});
            if (!whiteIpList.contains(clientIp)) {
                throw new RpcException();
            } else {
                return invoker.invoke(invocation);
            }
        }
    }

}
