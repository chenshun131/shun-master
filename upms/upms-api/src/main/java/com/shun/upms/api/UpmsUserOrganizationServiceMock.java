package com.shun.upms.api;

import com.shun.framework.base.BaseServiceMock;
import com.shun.upms.dao.mapper.UpmsUserOrganizationPoMapper;
import com.shun.upms.dao.po.UpmsUserOrganizationPo;
import com.shun.upms.dao.po.UpmsUserOrganizationPoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: mew <p />
 * Time: 17/11/10 17:09  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class UpmsUserOrganizationServiceMock extends BaseServiceMock<UpmsUserOrganizationPoMapper,
        UpmsUserOrganizationPo, UpmsUserOrganizationPoExample> implements UpmsUserOrganizationService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserOrganizationServiceMock.class);

    @Override
    public int organization(String[] organizationIds, int id) {
        _log.info("UpmsUserOrganizationServiceMock => organization");
        return 0;
    }

}
