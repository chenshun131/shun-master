package com.shun.upms.core.service.impl;

import com.shun.framework.annotation.BaseService;
import com.shun.framework.base.BaseServiceImpl;
import com.shun.upms.api.UpmsRoleService;
import com.shun.upms.dao.mapper.UpmsRolePoMapper;
import com.shun.upms.dao.po.UpmsRolePo;
import com.shun.upms.dao.po.UpmsRolePoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: mew <p />
 * Time: 2017/11/9 14:11 <p />
 * Version: V1.0  <p />
 * Description: UpmsRolePoService实现 <p />
 */
@Service("upmsRoleService")
@Transactional
@BaseService
public class UpmsRoleServiceImpl extends BaseServiceImpl<UpmsRolePoMapper, UpmsRolePo, UpmsRolePoExample>
        implements UpmsRoleService {

    private static Logger _log = LoggerFactory.getLogger(UpmsRoleServiceImpl.class);

    @Autowired
    UpmsRolePoMapper upmsRolePoMapper;

}
