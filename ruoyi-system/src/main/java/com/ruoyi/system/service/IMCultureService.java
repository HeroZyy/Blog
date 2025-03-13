package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.MCulture;

/**
 * 茶文化Service接口
 * 
 * @author tea
 * @date 2025-03-10
 */
public interface IMCultureService 
{
    /**
     * 查询茶文化
     * 
     * @param id 茶文化主键
     * @return 茶文化
     */
    public MCulture selectMCultureById(Long id);

    /**
     * 查询茶文化列表
     * 
     * @param mCulture 茶文化
     * @return 茶文化集合
     */
    public List<MCulture> selectMCultureList(MCulture mCulture);

    /**
     * 新增茶文化
     * 
     * @param mCulture 茶文化
     * @return 结果
     */
    public int insertMCulture(MCulture mCulture);

    /**
     * 修改茶文化
     * 
     * @param mCulture 茶文化
     * @return 结果
     */
    public int updateMCulture(MCulture mCulture);

    /**
     * 批量删除茶文化
     * 
     * @param ids 需要删除的茶文化主键集合
     * @return 结果
     */
    public int deleteMCultureByIds(Long[] ids);

    /**
     * 删除茶文化信息
     * 
     * @param id 茶文化主键
     * @return 结果
     */
    public int deleteMCultureById(Long id);
}
