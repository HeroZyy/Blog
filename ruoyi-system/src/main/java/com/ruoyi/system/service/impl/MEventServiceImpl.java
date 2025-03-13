package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MEventMapper;
import com.ruoyi.system.domain.MEvent;
import com.ruoyi.system.service.IMEventService;

/**
 * 活动管理Service业务层处理
 * 
 * @author tea
 * @date 2025-03-11
 */
@Service
public class MEventServiceImpl implements IMEventService 
{
    @Autowired
    private MEventMapper mEventMapper;

    /**
     * 查询活动管理
     * 
     * @param id 活动管理主键
     * @return 活动管理
     */
    @Override
    public MEvent selectMEventById(Long id)
    {
        return mEventMapper.selectMEventById(id);
    }

    /**
     * 查询活动管理列表
     * 
     * @param mEvent 活动管理
     * @return 活动管理
     */
    @Override
    public List<MEvent> selectMEventList(MEvent mEvent)
    {
        return mEventMapper.selectMEventList(mEvent);
    }

    /**
     * 新增活动管理
     * 
     * @param mEvent 活动管理
     * @return 结果
     */
    @Override
    public int insertMEvent(MEvent mEvent)
    {
        return mEventMapper.insertMEvent(mEvent);
    }

    /**
     * 修改活动管理
     * 
     * @param mEvent 活动管理
     * @return 结果
     */
    @Override
    public int updateMEvent(MEvent mEvent)
    {
        return mEventMapper.updateMEvent(mEvent);
    }

    /**
     * 批量删除活动管理
     * 
     * @param ids 需要删除的活动管理主键
     * @return 结果
     */
    @Override
    public int deleteMEventByIds(Long[] ids)
    {
        return mEventMapper.deleteMEventByIds(ids);
    }

    /**
     * 删除活动管理信息
     * 
     * @param id 活动管理主键
     * @return 结果
     */
    @Override
    public int deleteMEventById(Long id)
    {
        return mEventMapper.deleteMEventById(id);
    }
}
