package com.shun.server.cms.controller;

import com.shun.cms.api.*;
import com.shun.cms.dao.po.*;
import com.shun.framework.base.BaseController;
import com.shun.framework.util.Paginator;
import com.shun.framework.util.RequestUtil;
import com.shun.server.cms.constant.CmsResult;
import com.shun.server.cms.constant.CmsResultConstant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: mew <p />
 * Time: 17/11/27 11:30  <p />
 * Version: V1.0  <p />
 * Description: 问答首页控制器 <p />
 */
@Controller
@RequestMapping(value = "/qa")
public class QaController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(QaController.class);

    private static String CODE = "qa";

    private static Integer USERID = 1;

    @Autowired
    private CmsArticleService cmsArticleService;

    @Autowired
    private CmsCategoryService cmsCategoryService;

    @Autowired
    private CmsTagService cmsTagService;

    @Autowired
    private CmsSystemService cmsSystemService;

    @Autowired
    private CmsCommentService cmsCommentService;

    /**
     * 首页
     *
     * @param page
     * @param sort
     * @param order
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(@RequestParam(required = false, defaultValue = "1", value = "page") int page,
                        @RequestParam(required = false, defaultValue = "orders", value = "sort") String sort,
                        @RequestParam(required = false, defaultValue = "desc", value = "order") String order,
                        HttpServletRequest request,
                        Model model) {
        // 系统id
        CmsSystemPoExample cmsSystemExample = new CmsSystemPoExample();
        cmsSystemExample.createCriteria().andCodeEqualTo(CODE);
        CmsSystemPo system = cmsSystemService.selectFirstByExample(cmsSystemExample);
        model.addAttribute("system", system);

        // 该系统类目
        CmsCategoryPoExample cmsCategoryExample = new CmsCategoryPoExample();
        cmsCategoryExample.createCriteria().andSystemIdEqualTo(system.getSystemId());
        cmsCategoryExample.setOrderByClause("orders asc");
        List<CmsCategoryPo> categories = cmsCategoryService.selectByExample(cmsCategoryExample);
        model.addAttribute("categories", categories);

        // 该系统标签
        CmsTagPoExample cmsTagExample = new CmsTagPoExample();
        cmsTagExample.createCriteria().andSystemIdEqualTo(system.getSystemId());
        cmsTagExample.setOrderByClause("orders asc");
        List<CmsTagPo> tags = cmsTagService.selectByExample(cmsTagExample);
        model.addAttribute("tags", tags);

        // 该系统文章列表
        int rows = 10;
        CmsArticlePoExample cmsArticleExample = new CmsArticlePoExample();
        cmsArticleExample.createCriteria()
                .andStatusEqualTo((byte) 1)
                .andSystemIdEqualTo(system.getSystemId());
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
        return thymeleaf("/qa/index");
    }

    /**
     * 类目页
     *
     * @param alias
     * @param page
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/category/{alias}", method = RequestMethod.GET)
    public String category(@PathVariable("alias") String alias,
                           @RequestParam(required = false, defaultValue = "1", value = "page") int page,
                           HttpServletRequest request,
                           Model model) {
        // 系统id
        CmsSystemPoExample cmsSystemExample = new CmsSystemPoExample();
        cmsSystemExample.createCriteria().andCodeEqualTo(CODE);
        CmsSystemPo system = cmsSystemService.selectFirstByExample(cmsSystemExample);
        model.addAttribute("system", system);

        // 当前类目
        CmsCategoryPoExample cmsCategoryExample = new CmsCategoryPoExample();
        cmsCategoryExample.createCriteria()
                .andSystemIdEqualTo(system.getSystemId())
                .andAliasEqualTo(alias);
        CmsCategoryPo category = cmsCategoryService.selectFirstByExample(cmsCategoryExample);
        model.addAttribute("category", category);

        // 该类目文章列表
        int rows = 10;
        List<CmsArticlePo> articles =
                cmsArticleService.selectCmsArticlesByCategoryId(category.getCategoryId(), (page - 1) * rows, rows);
        model.addAttribute("articles", articles);

        // 文章总数
        long total = cmsArticleService.countByCategoryId(category.getCategoryId());

        // 分页
        Paginator paginator = new Paginator(total, page, rows, request);
        model.addAttribute("paginator", paginator);
        return thymeleaf("/qa/category/index");
    }

    /**
     * 标签页
     *
     * @param alias
     * @param page
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/tag/{alias}", method = RequestMethod.GET)
    public String tag(@PathVariable("alias") String alias,
                      @RequestParam(required = false, defaultValue = "1", value = "page") int page,
                      HttpServletRequest request,
                      Model model) {
        // 系统id
        CmsSystemPoExample cmsSystemExample = new CmsSystemPoExample();
        cmsSystemExample.createCriteria().andCodeEqualTo(CODE);
        CmsSystemPo system = cmsSystemService.selectFirstByExample(cmsSystemExample);
        model.addAttribute("system", system);

        // 当前标签
        CmsTagPoExample cmsTagExample = new CmsTagPoExample();
        cmsTagExample.createCriteria()
                .andSystemIdEqualTo(system.getSystemId())
                .andAliasEqualTo(alias);
        CmsTagPo tag = cmsTagService.selectFirstByExample(cmsTagExample);
        model.addAttribute("tag", tag);

        // 该标签文章列表
        int rows = 10;
        List<CmsArticlePo> articles = cmsArticleService.selectCmsArticlesByTagId(tag.getTagId(), (page - 1) * rows,
                rows);
        model.addAttribute("articles", articles);

        // 文章总数
        long total = cmsArticleService.countByTagId(tag.getTagId());

        // 分页
        Paginator paginator = new Paginator(total, page, rows, request);
        model.addAttribute("paginator", paginator);
        return thymeleaf("/qa/tag/index");
    }

    /**
     * 详情页
     *
     * @param articleId
     * @param model
     * @return
     */
    @RequestMapping(value = "/question/{articleId}", method = RequestMethod.GET)
    public String question(@PathVariable("articleId") int articleId, Model model) {
        CmsArticlePo article = cmsArticleService.selectByPrimaryKey(articleId);
        model.addAttribute("article", article);
        // 系统id
        CmsSystemPoExample cmsSystemExample = new CmsSystemPoExample();
        cmsSystemExample.createCriteria().andCodeEqualTo(CODE);
        CmsSystemPo system = cmsSystemService.selectFirstByExample(cmsSystemExample);
        model.addAttribute("system", system);

        // 评论列表
        CmsCommentPoExample cmsCommentExample = new CmsCommentPoExample();
        cmsCommentExample.createCriteria()
                .andSystemIdEqualTo(system.getSystemId())
                .andArticleIdEqualTo(articleId)
                .andStatusEqualTo((byte) 1);
        cmsCommentExample.setOrderByClause("ctime desc");
        List<CmsCommentPo> comments = cmsCommentService.selectByExampleWithBLOBs(cmsCommentExample);
        model.addAttribute("comments", comments);
        return thymeleaf("/qa/question/index");
    }

    /**
     * 新增回复
     *
     * @param articleId
     * @param cmsComment
     * @param request
     * @return
     */
    @RequestMapping(value = "/answer/{articleId}", method = RequestMethod.POST)
    @ResponseBody
    public Object answer(@PathVariable("articleId") int articleId, CmsCommentPo cmsComment,
                         HttpServletRequest request) {
        // 系统id
        CmsSystemPoExample cmsSystemExample = new CmsSystemPoExample();
        cmsSystemExample.createCriteria().andCodeEqualTo(CODE);
        CmsSystemPo system = cmsSystemService.selectFirstByExample(cmsSystemExample);
        long time = System.currentTimeMillis();
        cmsComment.setCtime(time);
        cmsComment.setArticleId(articleId);
        cmsComment.setUserId(USERID);
        cmsComment.setStatus((byte) 1);
        cmsComment.setIp(RequestUtil.getIpAddr(request));
        cmsComment.setAgent(request.getHeader("User-Agent"));
        cmsComment.setSystemId(system.getSystemId());
        int count = cmsCommentService.insertSelective(cmsComment);
        return new CmsResult(CmsResultConstant.SUCCESS, count);
    }

}
