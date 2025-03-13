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
import top.hcode.blog.entity.MEvent;
import top.hcode.blog.entity.SysUser;
import top.hcode.blog.entity.SysUser;
import top.hcode.blog.mapper.MBlogMapper;
import top.hcode.blog.mapper.SysUserMapper;
import top.hcode.blog.service.SysUserService;
import top.hcode.blog.service.SysUserService;
import top.hcode.blog.util.ShiroUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/blog/user")
public class MUserController {

    @Autowired
    private SysUserService SysUserService;

    @Autowired
    private MBlogMapper mBlogMapper;
//
//    @Autowired
//    private MEventMapper mEventMapper;

//    @RequiresAuthentication //需要登录认证才能访问
    @GetMapping("/index")
    public Object test(){
        SysUser user = SysUserService.getById(1L);
        return CommonResult.successResponse(user, "操作成功");
    }

    @GetMapping("/{username}")
    public CommonResult getUserBlog(@PathVariable("username")String username){
        QueryWrapper<SysUser> wrapper1 = new QueryWrapper<SysUser>().eq("user_name", username);

        SysUser user = SysUserService.getOne(wrapper1);

        if (user==null){
            return  CommonResult.errorResponse("该用户不存在");
        }
        QueryWrapper<MBlog> wrapper2 = new QueryWrapper<MBlog>().eq("user_id", user.getUserId());
        List<MBlog> mBlogs = mBlogMapper.selectList(wrapper2);
        System.out.println("mBlog : "+mBlogs.toString());

//        QueryWrapper<MEvent> wrapper3 = new QueryWrapper<MEvent>().eq("id", 1L).orderByDesc("gmt_create");
//        List<MEvent> mEvents = mEventMapper.selectList(wrapper3);
//        System.out.println("mEvent : "+mEvents.toString());

        return  CommonResult.successResponse(mBlogs, "查询成功");
    }

    @GetMapping("/user")
    public CommonResult blogs(Integer currentPage) {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = SysUserService.page(page, new QueryWrapper<SysUser>());
//        IPage pageData1 = blogEventService.page(page, new QueryWrapper<MEvent>().orderByDesc("gmt_create"));
//        System.out.println(pageData.getRecords());
//        System.out.println(pageData1.getRecords());
        return CommonResult.successResponse(pageData,"获取成功");
    }

    @GetMapping("/user/{id}")
    public CommonResult detail(@PathVariable(name = "id") Long id) {
        SysUser blog = SysUserService.getById(id);
        Assert.notNull(blog, "该博文已删除！");
        return CommonResult.successResponse(blog,"查询成功");
    }

//    @RequiresAuthentication
    @PostMapping("/user/edit")
    public CommonResult edit(@Validated @RequestBody SysUser user) {
        SysUser temp = null;
        if(user.getUserId() != null) {
            temp = SysUserService.getById(user.getUserId());
//            Assert.isTrue(temp.getId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑");
        } else {
            temp = new SysUser();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setStatus("0");
        }
        BeanUtil.copyProperties(user, temp, "id", "user_name","password", "avatar", "email", "password");
        SysUserService.saveOrUpdate(temp);
        return CommonResult.successResponse( null,"操作成功");
    }

    @RequiresAuthentication
    @GetMapping("/event/delete/{id}")
    public CommonResult delete(@PathVariable(name = "id") Long id) {
        boolean result = SysUserService.removeById(id);
        Assert.isTrue(result, "删除失败！该博文不存在！");
        return CommonResult.successResponse( null,"删除成功");
    }
}
