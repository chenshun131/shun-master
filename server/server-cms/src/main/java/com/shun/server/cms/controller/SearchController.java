package com.shun.server.cms.controller;

import com.shun.cms.api.CmsArticleService;
import com.shun.cms.dao.po.CmsArticlePo;
import com.shun.cms.dao.po.CmsArticlePoExample;
import com.shun.framework.base.BaseController;
import com.shun.framework.util.Paginator;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/27 11:33  <p />
 * Version: V1.0  <p />
 * Description: 搜索控制器 <p />
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private CmsArticleService cmsArticleService;

    @RequestMapping(value = "/{keyword}", method = RequestMethod.GET)
    public String index(@PathVariable("keyword") String keyword,
                        @RequestParam(required = false, defaultValue = "1", value = "page") int page,
                        @RequestParam(required = false, defaultValue = "orders", value = "sort") String sort,
                        @RequestParam(required = false, defaultValue = "desc", value = "order") String order,
                        HttpServletRequest request,
                        Model model) {
        // 该关键字文章列表
        int rows = 10;
        CmsArticlePoExample cmsArticleExample = new CmsArticlePoExample();
        cmsArticleExample.createCriteria().andStatusEqualTo((byte) 1).andTitleLike(keyword);
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            cmsArticleExample.setOrderByClause(sort + " " + order);
        }
        List<CmsArticlePo> articles =
                cmsArticleService.selectByExampleForOffsetPage(cmsArticleExample, (page - 1) * rows, rows);
        model.addAttribute("articles", articles);

        // 文章总数
        long total = cmsArticleService.countByExample(cmsArticleExample);

        // 分页
        Paginator paginator = new Paginator(total, page, rows, request);
        model.addAttribute("paginator", paginator);
        return thymeleaf("/search/index");
    }

}
