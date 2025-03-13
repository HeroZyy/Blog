package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动管理对象 m_event
 * 
 * @author tea
 * @date 2025-03-11
 */
public class MEvent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 用户名 */
    @Excel(name = "用户名")
    private Long userId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 删除 */
    @Excel(name = "删除")
    private Long deleted;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 类型 */
    @Excel(name = "类型")
    private String bclass;

    /** 内容1 */
    @Excel(name = "内容1")
    private String column1;

    /** 内容2 */
    @Excel(name = "内容2")
    private String column2;

    /** 内容3 */
    @Excel(name = "内容3")
    private String column3;

    /** 图片 */
    @Excel(name = "图片")
    private String img;

    /** 内容4 */
    @Excel(name = "内容4")
    private String column4;

    /** 内容5 */
    @Excel(name = "内容5")
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

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setGmtCreate(Date gmtCreate) 
    {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreate() 
    {
        return gmtCreate;
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

    public void setGmtModified(Date gmtModified) 
    {
        this.gmtModified = gmtModified;
    }

    public Date getGmtModified() 
    {
        return gmtModified;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
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

    public void setImg(String img) 
    {
        this.img = img;
    }

    public String getImg() 
    {
        return img;
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
            .append("title", getTitle())
            .append("content", getContent())
            .append("userId", getUserId())
            .append("gmtCreate", getGmtCreate())
            .append("status", getStatus())
            .append("deleted", getDeleted())
            .append("gmtModified", getGmtModified())
            .append("description", getDescription())
            .append("bclass", getBclass())
            .append("column1", getColumn1())
            .append("column2", getColumn2())
            .append("column3", getColumn3())
            .append("img", getImg())
            .append("column4", getColumn4())
            .append("column5", getColumn5())
            .append("video", getVideo())
            .toString();
    }
}
