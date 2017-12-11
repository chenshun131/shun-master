package com.shun.upms.core.service.impl;

import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.upms.api.UpmsUserOrganizationService;
import com.shun.upms.dao.mapper.UpmsUserOrganizationPoMapper;
import com.shun.upms.dao.po.UpmsUserOrganizationPo;
import com.shun.upms.dao.po.UpmsUserOrganizationPoExample;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: mew <p />
 * Time: 2017/11/9 14:11 <p />
 * Version: V1.0  <p />
 * Description: UpmsUserOrganizationPoService实现 <p />
 */
@Service("upmsUserOrganizationService")
@Transactional
@BaseService
public class UpmsUserOrganizationServiceImpl extends BaseServiceImpl<UpmsUserOrganizationPoMapper,
        UpmsUserOrganizationPo, UpmsUserOrganizationPoExample> implements UpmsUserOrganizationService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserOrganizationServiceImpl.class);

    @Autowired
    UpmsUserOrganizationPoMapper upmsUserOrganizationPoMapper;

    @Override
    public int organization(String[] organizationIds, int id) {
        int result = 0;
        // 删除旧记录
        UpmsUserOrganizationPoExample upmsUserOrganizationExample = new UpmsUserOrganizationPoExample();
        upmsUserOrganizationExample.createCriteria().andUserIdEqualTo(id);
        upmsUserOrganizationPoMapper.deleteByExample(upmsUserOrganizationExample);

        // 增加新记录
        if (null != organizationIds) {
            for (String organizationId : organizationIds) {
                if (StringUtils.isBlank(organizationId)) {
                    continue;
                }
                UpmsUserOrganizationPo upmsUserOrganization = new UpmsUserOrganizationPo();
                upmsUserOrganization.setUserId(id);
                upmsUserOrganization.setOrganizationId(NumberUtils.toInt(organizationId));
                result = upmsUserOrganizationPoMapper.insertSelective(upmsUserOrganization);
            }
        }
        return result;
    }

}
