package com.shun.server.manage.controller;

import com.shun.framework.base.BaseController;
import com.shun.upms.api.UpmsApiService;
import com.shun.upms.api.UpmsSystemService;
import com.shun.upms.dao.po.UpmsPermissionPo;
import com.shun.upms.dao.po.UpmsSystemPo;
import com.shun.upms.dao.po.UpmsSystemPoExample;
import com.shun.upms.dao.po.UpmsUserPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/9 13:56  <p />
 * Version: V1.0  <p />
 * Description: 后台controller <p />
 */
@Controller
@RequestMapping("/manage")
@Api(value = "后台管理", description = "后台管理")
public class ManageController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(ManageController.class);

    @Autowired
    private UpmsSystemService upmsSystemService;

    @Autowired
    private UpmsApiService upmsApiService;

    @ApiOperation(value = "后台首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        // 已注册系统
        UpmsSystemPoExample upmsSystemPoExample = new UpmsSystemPoExample();
        upmsSystemPoExample.createCriteria().andStatusEqualTo((byte) 1);
        List<UpmsSystemPo> upmsSystems = upmsSystemService.selectByExample(upmsSystemPoExample);
        modelMap.put("upmsSystems", upmsSystems);

        // 当前登录用户权限
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        UpmsUserPo upmsUser = upmsApiService.selectUpmsUserByUsername(username);
        List<UpmsPermissionPo> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());
        modelMap.put("upmsPermissions", upmsPermissions);
        return "/manage/index.jsp";
    }

}
