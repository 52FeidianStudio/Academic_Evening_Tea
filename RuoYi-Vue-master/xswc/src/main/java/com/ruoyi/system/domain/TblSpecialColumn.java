package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 tbl_special_column
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
public class TblSpecialColumn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 精彩专栏编号 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 种类1精彩瞬间2科普活动 */
    @Excel(name = "种类1精彩瞬间2科普活动")
    private Integer kind;

    /** 简介 */
    @Excel(name = "简介")
    private String summary;

    /** 正文内容 */
    @Excel(name = "正文内容")
    private String content;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Integer likeCount;

    /** 浏览数 */
    @Excel(name = "浏览数")
    private Integer viewsNums;

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
    public void setKind(Integer kind) 
    {
        this.kind = kind;
    }

    public Integer getKind() 
    {
        return kind;
    }
    public void setSummary(String summary) 
    {
        this.summary = summary;
    }

    public String getSummary() 
    {
        return summary;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setLikeCount(Integer likeCount) 
    {
        this.likeCount = likeCount;
    }

    public Integer getLikeCount() 
    {
        return likeCount;
    }
    public void setViewsNums(Integer viewsNums) 
    {
        this.viewsNums = viewsNums;
    }

    public Integer getViewsNums() 
    {
        return viewsNums;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("kind", getKind())
            .append("summary", getSummary())
            .append("content", getContent())
            .append("likeCount", getLikeCount())
            .append("viewsNums", getViewsNums())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .toString();
    }
}
