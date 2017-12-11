package com.shun.server.cms.controller;

import com.shun.cms.api.CmsCategoryService;
import com.shun.cms.api.CmsMenuService;
import com.shun.cms.api.CmsSystemService;
import com.shun.cms.api.CmsTagService;
import com.shun.cms.dao.po.*;
import com.shun.framework.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/27 11:23  <p />
 * Version: V1.0  <p />
 * Description: 首页控制器 <p />
 */
@Controller
public class IndexController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private CmsMenuService cmsMenuService;

    @Autowired
    private CmsCategoryService cmsCategoryService;

    @Autowired
    private CmsTagService cmsTagService;

    @Autowired
    private CmsSystemService cmsSystemService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        // 所有系统
        CmsSystemPoExample cmsSystemExample = new CmsSystemPoExample();
        cmsSystemExample.setOrderByClause("orders asc");
        List<CmsSystemPo> systems = cmsSystemService.selectByExample(cmsSystemExample);
        model.addAttribute("systems", systems);

        // 所有类目
        CmsCategoryPoExample cmsCategoryExample = new CmsCategoryPoExample();
        cmsCategoryExample.setOrderByClause("orders asc");
        List<CmsCategoryPo> categories = cmsCategoryService.selectByExample(cmsCategoryExample);
        model.addAttribute("categories", categories);

        // 所有标签
        CmsTagPoExample cmsTagExample = new CmsTagPoExample();
        cmsTagExample.setOrderByClause("orders asc");
        List<CmsTagPo> tags = cmsTagService.selectByExample(cmsTagExample);
        model.addAttribute("tags", tags);
        return thymeleaf("/index");
    }

}
