package com.shun.server.cms.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.shun.cms.api.CmsPageService;
import com.shun.cms.dao.po.CmsPagePo;
import com.shun.cms.dao.po.CmsPagePoExample;
import com.shun.framework.base.BaseController;
import com.shun.framework.validator.LengthValidator;
import com.shun.server.cms.constant.CmsResult;
import com.shun.server.cms.constant.CmsResultConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/28 16:42  <p />
 * Version: V1.0  <p />
 * Description: 单页控制器 <p />
 */
@Controller
@Api(value = "单页管理", description = "单页管理")
@RequestMapping("/manage/page")
public class CmsPageController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(CmsPageController.class);

    @Autowired
    private CmsPageService cmsPageService;

    @ApiOperation(value = "评论首页")
    @RequiresPermissions("cms:page:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/page/index.jsp";
    }

    @ApiOperation(value = "评论列表")
    @RequiresPermissions("cms:page:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
                       @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
                       @RequestParam(required = false, value = "sort") String sort,
                       @RequestParam(required = false, value = "order") String order) {
        CmsPagePoExample cmsPageExample = new CmsPagePoExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            cmsPageExample.setOrderByClause(sort + " " + order);
        }
        List<CmsPagePo> rows = cmsPageService.selectByExampleForOffsetPage(cmsPageExample, offset, limit);
        long total = cmsPageService.countByExample(cmsPageExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增单页")
    @RequiresPermissions("cms:page:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/page/create.jsp";
    }

    @ApiOperation(value = "新增单页")
    @RequiresPermissions("cms:page:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(CmsPagePo cmsPage) {
        ComplexResult result = FluentValidator.checkAll()
                .on(cmsPage.getTitle(), new LengthValidator(1, 20, "标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new CmsResult(CmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        cmsPage.setCtime(time);
        cmsPage.setOrders(time);
        int count = cmsPageService.insertSelective(cmsPage);
        return new CmsResult(CmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除单页")
    @RequiresPermissions("cms:page:delete")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = cmsPageService.deleteByPrimaryKeys(ids);
        return new CmsResult(CmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改单页")
    @RequiresPermissions("cms:page:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        CmsPagePo page = cmsPageService.selectByPrimaryKey(id);
        modelMap.put("page", page);
        return "/manage/page/update.jsp";
    }

    @ApiOperation(value = "修改单页")
    @RequiresPermissions("cms:page:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, CmsPagePo cmsPage) {
        ComplexResult result = FluentValidator.checkAll()
                .on(cmsPage.getTitle(), new LengthValidator(1, 20, "标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new CmsResult(CmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        cmsPage.setPageId(id);
        int count = cmsPageService.updateByPrimaryKeySelective(cmsPage);
        return new CmsResult(CmsResultConstant.SUCCESS, count);
    }

}