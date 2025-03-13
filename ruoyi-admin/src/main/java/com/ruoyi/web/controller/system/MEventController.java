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
import com.ruoyi.system.domain.MEvent;
import com.ruoyi.system.service.IMEventService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动管理Controller
 * 
 * @author tea
 * @date 2025-03-11
 */
@RestController
@RequestMapping("/system/event")
public class MEventController extends BaseController
{
    @Autowired
    private IMEventService mEventService;

    /**
     * 查询活动管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:event:list')")
    @GetMapping("/list")
    public TableDataInfo list(MEvent mEvent)
    {
        startPage();
        List<MEvent> list = mEventService.selectMEventList(mEvent);
        return getDataTable(list);
    }

    /**
     * 导出活动管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:event:export')")
    @Log(title = "活动管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MEvent mEvent)
    {
        List<MEvent> list = mEventService.selectMEventList(mEvent);
        ExcelUtil<MEvent> util = new ExcelUtil<MEvent>(MEvent.class);
        util.exportExcel(response, list, "活动管理数据");
    }

    /**
     * 获取活动管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:event:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mEventService.selectMEventById(id));
    }

    /**
     * 新增活动管理
     */
    @PreAuthorize("@ss.hasPermi('system:event:add')")
    @Log(title = "活动管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MEvent mEvent)
    {
        return toAjax(mEventService.insertMEvent(mEvent));
    }

    /**
     * 修改活动管理
     */
    @PreAuthorize("@ss.hasPermi('system:event:edit')")
    @Log(title = "活动管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MEvent mEvent)
    {
        return toAjax(mEventService.updateMEvent(mEvent));
    }

    /**
     * 删除活动管理
     */
    @PreAuthorize("@ss.hasPermi('system:event:remove')")
    @Log(title = "活动管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mEventService.deleteMEventByIds(ids));
    }
}
