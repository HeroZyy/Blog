package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 种植技术对象 m_blog
 *
 * @author tea
 * @date 2025-03-10
 */
public class MBlog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /**  */
    private Long userId;

    /** 茶园名称 */
    @Excel(name = "茶园名称")
    private String title;

    /**  */
    private String description;

    /**  */
    private String content;

    /**  */
    private Date gmtCreate;

    /**  */
    private Date gmtModified;

    /**  */
    private Integer status;

    /**  */
    private Long deleted;

    /**  */
    private String author;

    /** 图片 */
    @Excel(name = "图片")
    private String img;

    /** 类型 */
    @Excel(name = "类型")
    private String bclass;

    /** 茶叶品种 */
    @Excel(name = "茶叶品种")
    private String column1;

    /** 施肥方法 */
    @Excel(name = "施肥方法")
    private String column2;

    /** 修剪技术 */
    @Excel(name = "修剪技术")
    private String column3;

    /** 采摘时机 */
    @Excel(name = "采摘时机")
    private String column4;

    /**  */
    private String column5;

    /** 视频 */
    @Excel(name = "视频")
    private String video;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    public void setGmtCreate(Date gmtCreate)
    {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreate()
    {
        return gmtCreate;
    }

    public void setGmtModified(Date gmtModified)
    {
        this.gmtModified = gmtModified;
    }

    public Date getGmtModified()
    {
        return gmtModified;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setDeleted(Long deleted)
    {
        this.deleted = deleted;
    }

    public Long getDeleted()
    {
        return deleted;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setImg(String img)
    {
        this.img = img;
    }

    public String getImg()
    {
        return img;
    }

    public void setBclass(String bclass)
    {
        this.bclass = bclass;
    }

    public String getBclass()
    {
        return bclass;
    }

    public void setColumn1(String column1)
    {
        this.column1 = column1;
    }

    public String getColumn1()
    {
        return column1;
    }

    public void setColumn2(String column2)
    {
        this.column2 = column2;
    }

    public String getColumn2()
    {
        return column2;
    }

    public void setColumn3(String column3)
    {
        this.column3 = column3;
    }

    public String getColumn3()
    {
        return column3;
    }

    public void setColumn4(String column4)
    {
        this.column4 = column4;
    }

    public String getColumn4()
    {
        return column4;
    }

    public void setColumn5(String column5)
    {
        this.column5 = column5;
    }

    public String getColumn5()
    {
        return column5;
    }

    public void setVideo(String video)
    {
        this.video = video;
    }

    public String getVideo()
    {
        return video;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("title", getTitle())
                .append("description", getDescription())
                .append("content", getContent())
                .append("gmtCreate", getGmtCreate())
                .append("gmtModified", getGmtModified())
                .append("status", getStatus())
                .append("deleted", getDeleted())
                .append("author", getAuthor())
                .append("img", getImg())
                .append("bclass", getBclass())
                .append("column1", getColumn1())
                .append("column2", getColumn2())
                .append("column3", getColumn3())
                .append("column4", getColumn4())
                .append("column5", getColumn5())
                .append("video", getVideo())
                .toString();
    }
}
