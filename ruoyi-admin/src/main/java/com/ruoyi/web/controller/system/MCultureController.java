package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.system.domain.MCulture;
import com.ruoyi.system.service.IMCultureService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 茶文化Controller
 * 
 * @author tea
 * @date 2025-03-10
 */
@RestController
@RequestMapping("/system/culture")
public class MCultureController extends BaseController
{
    @Autowired
    private IMCultureService mCultureService;

    /**
     * 查询茶文化列表
     */
//    @PreAuthorize("@ss.hasPermi('system:culture:list')")
    @GetMapping("/list")
    public TableDataInfo list(MCulture mCulture)
    {
        startPage();
        List<MCulture> list = mCultureService.selectMCultureList(mCulture);
        return getDataTable(list);
    }

    /**
     * 导出茶文化列表
     */
//    @PreAuthorize("@ss.hasPermi('system:culture:export')")
    @Log(title = "茶文化", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MCulture mCulture)
    {
        List<MCulture> list = mCultureService.selectMCultureList(mCulture);
        ExcelUtil<MCulture> util = new ExcelUtil<MCulture>(MCulture.class);
        util.exportExcel(response, list, "茶文化数据");
    }

    /**
     * 获取茶文化详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:culture:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mCultureService.selectMCultureById(id));
    }

    /**
     * 新增茶文化
     */
//    @PreAuthorize("@ss.hasPermi('system:culture:add')")
    @Log(title = "茶文化", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MCulture mCulture)
    {
        return toAjax(mCultureService.insertMCulture(mCulture));
    }

    /**
     * 修改茶文化
     */
//    @PreAuthorize("@ss.hasPermi('system:culture:edit')")
    @Log(title = "茶文化", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MCulture mCulture)
    {
        return toAjax(mCultureService.updateMCulture(mCulture));
    }

    /**
     * 删除茶文化
     */
//    @PreAuthorize("@ss.hasPermi('system:culture:remove')")
    @Log(title = "茶文化", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mCultureService.deleteMCultureByIds(ids));
    }
}
