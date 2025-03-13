package top.hcode.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.hcode.blog.common.results.CommonResult;
import top.hcode.blog.entity.SysMenu;
import top.hcode.blog.mapper.SysMenuMapper;
import top.hcode.blog.service.SysMenuService;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author tea
 * @since 2025-03-11
 */
@RestController
@RequestMapping("")
public class SysMenuController {
    @Autowired
    SysMenuService sysMenuService;

    @GetMapping("/blog-menu")
    public CommonResult blogMenu(Integer currentPage) {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 100);
        IPage pageData = sysMenuService.page(page, new QueryWrapper<SysMenu>().eq("parent_id",2000).orderByAsc("order_num"));
        return CommonResult.successResponse(pageData,"获取成功");
    }

    @GetMapping("/culture-menu")
    public CommonResult cultureMenu(Integer currentPage) {
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 100);
        IPage pageData = sysMenuService.page(page, new QueryWrapper<SysMenu>().eq("parent_id",2004).orderByAsc("order_num"));
        return CommonResult.successResponse(pageData,"获取成功");
    }

}
