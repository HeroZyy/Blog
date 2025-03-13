package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.MBlog;

/**
 * 种植技术Service接口
 *
 * @author tea
 * @date 2025-03-10
 */
public interface IMBlogService
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
     * 批量删除种植技术
     *
     * @param ids 需要删除的种植技术主键集合
     * @return 结果
     */
    public int deleteMBlogByIds(Long[] ids);

    /**
     * 删除种植技术信息
     *
     * @param id 种植技术主键
     * @return 结果
     */
    public int deleteMBlogById(Long id);
}
