package top.hcode.blog.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import top.hcode.blog.common.results.CommonResult;
import top.hcode.blog.entity.MBlog;
import top.hcode.blog.entity.MCulture;
import top.hcode.blog.entity.MCulture;
import top.hcode.blog.service.MCultureService;
import top.hcode.blog.service.MCultureService;
import top.hcode.blog.util.ShiroUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
//import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 1
 * @since 2025-03-09
 */
@RestController
@RequestMapping()
public class MCultureController {
    @Autowired
    MCultureService mCultureService;
    @GetMapping("/culture")
    public CommonResult blogs(Integer currentPage) {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 30);
        IPage pageData = mCultureService.page(page, new QueryWrapper<MCulture>().orderByDesc("gmt_create"));
        return CommonResult.successResponse(pageData,"获取成功");
    }

    @GetMapping("/culture/bclass")
    public CommonResult blogsClass(String bclass,Integer currentPage) {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 30);
        IPage pageData ;
        if(bclass == null || currentPage < 1) {
            pageData = mCultureService.page(page, new QueryWrapper<MCulture>().orderByDesc("gmt_create"));
        }
        else {
            pageData = mCultureService.page(page, new QueryWrapper<MCulture>().eq("bclass", bclass).orderByDesc("gmt_create"));
        }
        return CommonResult.successResponse(pageData,"获取成功");
    }

    @GetMapping("/culture/{id}")
    public CommonResult detail(@PathVariable(name = "id") Long id) {
        MCulture blog = mCultureService.getById(id);
        Assert.notNull(blog, "该博文已删除！");
        return CommonResult.successResponse(blog,"查询成功");
    }

    @RequiresAuthentication
    @PostMapping("/culture/edit")
    public CommonResult edit(@Validated @RequestBody MCulture blog) {
        MCulture temp = null;
        if(blog.getId() != null) {
            temp = mCultureService.getById(blog.getId());
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑");
        } else {
            temp = new MCulture();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setGmtCreate(LocalDate.now());
            temp.setStatus(0);
        }
        BeanUtil.copyProperties(blog, temp, "id", "userId", "gmtCreate", "status");
        mCultureService.saveOrUpdate(temp);
        return CommonResult.successResponse( null,"操作成功");
    }

    @RequiresAuthentication
    @GetMapping("/culture/delete/{id}")
    public CommonResult delete(@PathVariable(name = "id") Long id) {
        boolean result = mCultureService.removeById(id);
        Assert.isTrue(result, "删除失败！该博文不存在！");
        return CommonResult.successResponse( null,"删除成功");
    }
}
