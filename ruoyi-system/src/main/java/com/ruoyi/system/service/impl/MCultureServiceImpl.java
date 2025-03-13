package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MCultureMapper;
import com.ruoyi.system.domain.MCulture;
import com.ruoyi.system.service.IMCultureService;

/**
 * 茶文化Service业务层处理
 * 
 * @author tea
 * @date 2025-03-10
 */
@Service
public class MCultureServiceImpl implements IMCultureService 
{
    @Autowired
    private MCultureMapper mCultureMapper;

    /**
     * 查询茶文化
     * 
     * @param id 茶文化主键
     * @return 茶文化
     */
    @Override
    public MCulture selectMCultureById(Long id)
    {
        return mCultureMapper.selectMCultureById(id);
    }

    /**
     * 查询茶文化列表
     * 
     * @param mCulture 茶文化
     * @return 茶文化
     */
    @Override
    public List<MCulture> selectMCultureList(MCulture mCulture)
    {
        return mCultureMapper.selectMCultureList(mCulture);
    }

    /**
     * 新增茶文化
     * 
     * @param mCulture 茶文化
     * @return 结果
     */
    @Override
    public int insertMCulture(MCulture mCulture)
    {
        return mCultureMapper.insertMCulture(mCulture);
    }

    /**
     * 修改茶文化
     * 
     * @param mCulture 茶文化
     * @return 结果
     */
    @Override
    public int updateMCulture(MCulture mCulture)
    {
        return mCultureMapper.updateMCulture(mCulture);
    }

    /**
     * 批量删除茶文化
     * 
     * @param ids 需要删除的茶文化主键
     * @return 结果
     */
    @Override
    public int deleteMCultureByIds(Long[] ids)
    {
        return mCultureMapper.deleteMCultureByIds(ids);
    }

    /**
     * 删除茶文化信息
     * 
     * @param id 茶文化主键
     * @return 结果
     */
    @Override
    public int deleteMCultureById(Long id)
    {
        return mCultureMapper.deleteMCultureById(id);
    }
}
