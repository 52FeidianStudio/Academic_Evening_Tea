package com.ruoyi.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ruoyi.common.annotation.Excel;

/**
 * 【请填写功能名称】对象 tbl_recommend_commnet
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TblRecommendComment
{
    private static final long serialVersionUID = 1L;

    /** 联表编号 */
    private Long id;
    private String nickName;
    private String avatar;

    /** 评论 */
    @Excel(name = "评论")
    private String comment;

    /** 评论人 */
    @Excel(name = "评论人")
    private Long userId;

    /** 推荐id */
    @Excel(name = "推荐id")
    private Long recommendId;


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
//    public void setComment(String comment)
//    {
//        this.comment = comment;
//    }
//
//    public String getComment()
//    {
//        return comment;
//    }
//    public void setUserId(Long userId)
//    {
//        this.userId = userId;
//    }
//
//    public Long getUserId()
//    {
//        return userId;
//    }
//    public void setRecommendId(Long recommendId)
//    {
//        this.recommendId = recommendId;
//    }
//
//    public Long getRecommendId()
//    {
//        return recommendId;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//            .append("id", getId())
//            .append("comment", getComment())
//            .append("userId", getUserId())
//            .append("recommendId", getRecommendId())
//            .append("updateTime", getUpdateTime())
//            .append("updateBy", getUpdateBy())
//            .append("createTime", getCreateTime())
//            .append("createBy", getCreateBy())
//            .toString();
//    }
}
