package top.hcode.blog.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import top.hcode.blog.common.results.CommonResult;
import top.hcode.blog.entity.MBlog;
import top.hcode.blog.entity.MEvent;
import top.hcode.blog.mapper.MUserMapper;
import top.hcode.blog.service.MBlogService;
import top.hcode.blog.service.MEventService;
import top.hcode.blog.service.impl.MUserServiceImpl;
import top.hcode.blog.util.ShiroUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 1
 * @since 2025-03-07
 */
@RestController
@RequestMapping()

public class MEventController {
    @Autowired
    MBlogService blogService;

    @Autowired
    MUserMapper mUserMapper;

    @Autowired
    MEventService mEventService;

//    @Autowired
//    MUserServiceImpl mUserServiceImpl;

    @GetMapping("/event")
    public CommonResult blogs(Integer currentPage) {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 30);
        IPage pageData = mEventService.page(page, new QueryWrapper<MEvent>().orderByDesc("gmt_create"));
//        IPage pageData1 = blogEventService.page(page, new QueryWrapper<MEvent>().orderByDesc("gmt_create"));
//        System.out.println(pageData.getRecords());
//        System.out.println(pageData1.getRecords());
        return CommonResult.successResponse(pageData,"获取成功");
    }

    @GetMapping("/event/{id}")
    public CommonResult detail(@PathVariable(name = "id") Long id) {
        MEvent blog = mEventService.getById(id);
        Assert.notNull(blog, "该博文已删除！");
        return CommonResult.successResponse(blog,"查询成功");
    }

    @RequiresAuthentication
    @PostMapping("/event/edit")
    public CommonResult edit(@Validated @RequestBody MEvent blog) {
        MEvent temp = null;
        if(blog.getId() != null) {
            temp = mEventService.getById(blog.getId());
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑");
        } else {
            temp = new MEvent();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setGmtCreate(LocalDate.from(LocalDateTime.now()));
            temp.setStatus(0);
        }
        BeanUtil.copyProperties(blog, temp, "id", "userId", "gmtCreate", "status");
        mEventService.saveOrUpdate(temp);
        return CommonResult.successResponse( null,"操作成功");
    }

    @RequiresAuthentication
    @GetMapping("/event/delete/{id}")
    public CommonResult delete(@PathVariable(name = "id") Long id) {
        boolean result = blogService.removeById(id);
        Assert.isTrue(result, "删除失败！该博文不存在！");
        return CommonResult.successResponse( null,"删除成功");
    }
}
