package com.ruoyi.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ruoyi.common.annotation.Excel;

import java.util.List;

/**
 * 【请填写功能名称】对象 tbl_recommend
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TblRecommend
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    private  Long userId;
    private String nickName;

    private  TblLike tblLike;

private String avatar;
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
    private Integer status;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Integer likeCount;


    //评论
    private List<TblRecommendComment> tblRecommendCommnets;


    //创建时间
    private String createTime;
    //更新时间
    private String updateTime;
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
