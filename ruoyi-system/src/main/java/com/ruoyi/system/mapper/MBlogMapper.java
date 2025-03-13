package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.MBlog;

/**
 * 种植技术Mapper接口
 *
 * @author tea
 * @date 2025-03-10
 */
public interface MBlogMapper
{
    /**
     * 查询种植技术
     *
     * @param id 种植技术主键
     * @return 种植技术
     */
    public MBlog selectMBlogById(Long id);

    /**
     * 查询种植技术列表
     *
     * @param mBlog 种植技术
     * @return 种植技术集合
     */
    public List<MBlog> selectMBlogList(MBlog mBlog);

    /**
     * 新增种植技术
     *
     * @param mBlog 种植技术
     * @return 结果
     */
    public int insertMBlog(MBlog mBlog);

    /**
     * 修改种植技术
     *
     * @param mBlog 种植技术
     * @return 结果
     */
    public int updateMBlog(MBlog mBlog);

    /**
     * 删除种植技术
     *
     * @param id 种植技术主键
     * @return 结果
     */
    public int deleteMBlogById(Long id);

    /**
     * 批量删除种植技术
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMBlogByIds(Long[] ids);
}
