package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.MEvent;

/**
 * 活动管理Mapper接口
 * 
 * @author tea
 * @date 2025-03-11
 */
public interface MEventMapper 
{
    /**
     * 查询活动管理
     * 
     * @param id 活动管理主键
     * @return 活动管理
     */
    public MEvent selectMEventById(Long id);

    /**
     * 查询活动管理列表
     * 
     * @param mEvent 活动管理
     * @return 活动管理集合
     */
    public List<MEvent> selectMEventList(MEvent mEvent);

    /**
     * 新增活动管理
     * 
     * @param mEvent 活动管理
     * @return 结果
     */
    public int insertMEvent(MEvent mEvent);

    /**
     * 修改活动管理
     * 
     * @param mEvent 活动管理
     * @return 结果
     */
    public int updateMEvent(MEvent mEvent);

    /**
     * 删除活动管理
     * 
     * @param id 活动管理主键
     * @return 结果
     */
    public int deleteMEventById(Long id);

    /**
     * 批量删除活动管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMEventByIds(Long[] ids);
}
