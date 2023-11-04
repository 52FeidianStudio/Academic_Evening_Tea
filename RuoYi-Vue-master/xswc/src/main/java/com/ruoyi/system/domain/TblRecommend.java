package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.time.LocalDateTime;

/**
 * 【请填写功能名称】对象 tbl_recommend
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
public class TblRecommend
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 推荐理由 */
    @Excel(name = "推荐理由")
    private String content;

    /** 主讲人姓名 */
    @Excel(name = "主讲人姓名")
    private String lecturerName;

    /** 主题 */
    @Excel(name = "主题")
    private String theme;

    /** 状态1已推荐2已采纳3有效推荐 */
    @Excel(name = "状态1已推荐2已采纳3有效推荐")
    private String status;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Integer likeCount;

    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
    //创建人
    private  String createBy;
    //更新人
    private  String updateBy;
//    public void setId(Long id)
//    {
//        this.id = id;
//    }
//
//    public Long getId()
//    {
//        return id;
//    }
//    public void setContent(String content)
//    {
//        this.content = content;
//    }
//
//    public String getContent()
//    {
//        return content;
//    }
//    public void setLecturerName(String lecturerName)
//    {
//        this.lecturerName = lecturerName;
//    }
//
//    public String getLecturerName()
//    {
//        return lecturerName;
//    }
//    public void setTheme(String theme)
//    {
//        this.theme = theme;
//    }
//
//    public String getTheme()
//    {
//        return theme;
//    }
//    public void setStatus(String status)
//    {
//        this.status = status;
//    }
//
//    public String getStatus()
//    {
//        return status;
//    }
//    public void setLikeCount(Integer likeCount)
//    {
//        this.likeCount = likeCount;
//    }
//
//    public Integer getLikeCount()
//    {
//        return likeCount;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//            .append("id", getId())
//            .append("content", getContent())
//            .append("lecturerName", getLecturerName())
//            .append("theme", getTheme())
//            .append("status", getStatus())
//            .append("likeCount", getLikeCount())
//            .append("updateTime", getUpdateTime())
//            .append("updateBy", getUpdateBy())
//            .append("createTime", getCreateTime())
//            .append("createBy", getCreateBy())
//            .toString();
//    }
}