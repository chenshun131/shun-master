package com.shun.server.cms.controller;

import com.shun.cms.api.CmsPageService;
import com.shun.cms.dao.po.CmsPagePo;
import com.shun.cms.dao.po.CmsPagePoExample;
import com.shun.framework.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: mew <p />
 * Time: 17/11/27 11:28  <p />
 * Version: V1.0  <p />
 * Description: 单页控制器 <p />
 */
@Controller
@RequestMapping(value = "/page")
public class PageController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private CmsPageService cmsPageService;

    @RequestMapping(value = "/{alias}", method = RequestMethod.GET)
    public String index(@PathVariable("alias") String alias, Model model) {
        CmsPagePoExample cmsPageExample = new CmsPagePoExample();
        cmsPageExample.createCriteria().andAliasEqualTo(alias);
        CmsPagePo page = cmsPageService.selectFirstByExampleWithBLOBs(cmsPageExample);
        model.addAttribute("page", page);
        return thymeleaf("/page/index");
    }

}
