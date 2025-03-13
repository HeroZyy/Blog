package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.system.mapper.SysMenuMapper;
import com.ruoyi.system.service.ISysMenuService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.MBlog;
import com.ruoyi.system.service.IMBlogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 种植技术Controller
 *
 * @author tea
 * @date 2025-03-10
 */
@RestController
@RequestMapping("/system/tea_tech")
public class MBlogController extends BaseController
{
    @Autowired
    private IMBlogService mBlogService;

    /**
     * 查询种植技术列表
     */
//    @PreAuthorize("@ss.hasPermi('system:tea_tech:list')")
    @GetMapping("/list")
    public TableDataInfo list(MBlog mBlog)
    {
        startPage();
        List<MBlog> list = mBlogService.selectMBlogList(mBlog);
        return getDataTable(list);
    }

    /**
     * 导出种植技术列表
     */
//    @PreAuthorize("@ss.hasPermi('system:tea_tech:export')")
    @Log(title = "种植技术", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MBlog mBlog)
    {
        List<MBlog> list = mBlogService.selectMBlogList(mBlog);
        ExcelUtil<MBlog> util = new ExcelUtil<MBlog>(MBlog.class);
        util.exportExcel(response, list, "种植技术数据");
    }

    /**
     * 获取种植技术详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:tea_tech:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mBlogService.selectMBlogById(id));
    }

    /**
     * 新增种植技术
     */
//    @PreAuthorize("@ss.hasPermi('system:tea_tech:add')")
    @Log(title = "种植技术", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MBlog mBlog)
    {
        return toAjax(mBlogService.insertMBlog(mBlog));
    }

    /**
     * 修改种植技术
     */
//    @PreAuthorize("@ss.hasPermi('system:tea_tech:edit')")
    @Log(title = "种植技术", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MBlog mBlog)
    {
        return toAjax(mBlogService.updateMBlog(mBlog));
    }

    /**
     * 删除种植技术
     */
//    @PreAuthorize("@ss.hasPermi('system:tea_tech:remove')")
    @Log(title = "种植技术", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mBlogService.deleteMBlogByIds(ids));
    }
}
