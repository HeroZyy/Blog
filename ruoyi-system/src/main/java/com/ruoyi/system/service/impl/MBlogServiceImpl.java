package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MBlogMapper;
import com.ruoyi.system.domain.MBlog;
import com.ruoyi.system.service.IMBlogService;

/**
 * 种植技术Service业务层处理
 *
 * @author tea
 * @date 2025-03-10
 */
@Service
public class MBlogServiceImpl implements IMBlogService
{
    @Autowired
    private MBlogMapper mBlogMapper;

    /**
     * 查询种植技术
     *
     * @param id 种植技术主键
     * @return 种植技术
     */
    @Override
    public MBlog selectMBlogById(Long id)
    {
        return mBlogMapper.selectMBlogById(id);
    }

    /**
     * 查询种植技术列表
     *
     * @param mBlog 种植技术
     * @return 种植技术
     */
    @Override
    public List<MBlog> selectMBlogList(MBlog mBlog)
    {
        return mBlogMapper.selectMBlogList(mBlog);
    }

    /**
     * 新增种植技术
     *
     * @param mBlog 种植技术
     * @return 结果
     */
    @Override
    public int insertMBlog(MBlog mBlog)
    {
        return mBlogMapper.insertMBlog(mBlog);
    }

    /**
     * 修改种植技术
     *
     * @param mBlog 种植技术
     * @return 结果
     */
    @Override
    public int updateMBlog(MBlog mBlog)
    {
        return mBlogMapper.updateMBlog(mBlog);
    }

    /**
     * 批量删除种植技术
     *
     * @param ids 需要删除的种植技术主键
     * @return 结果
     */
    @Override
    public int deleteMBlogByIds(Long[] ids)
    {
        return mBlogMapper.deleteMBlogByIds(ids);
    }

    /**
     * 删除种植技术信息
     *
     * @param id 种植技术主键
     * @return 结果
     */
    @Override
    public int deleteMBlogById(Long id)
    {
        return mBlogMapper.deleteMBlogById(id);
    }
}
