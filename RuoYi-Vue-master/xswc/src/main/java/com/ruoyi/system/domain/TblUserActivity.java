package com.ruoyi.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 【请填写功能名称】对象 tbl_user_activity
 * 
 * @author ruoyi
 * @date 2023-10-25
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TblUserActivity  {

    private static final long serialVersionUID = 1L;

    /**
     * 用户活动编号
     */
    private Long id;

    /**
     * 用户编号
     */
    @Excel(name = "用户编号")
    private Long userId;

    /**
     * 活动编号
     */
    @Excel(name = "活动编号")
    private Long activityId;

    /**
     * 1未签到2已签到3失约
     */
    @Excel(name = "1未签到2已签到3失约")
    private Integer status;

    public void setId(Long id) {
        this.id = id;
    }

    //学院id
    public Long deptId;
    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
    //创建人
    private  String createBy;
    //更新人
    private  String updateBy;
}
//
//    public Long getId()
//    {
//        return id;
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
//    public void setActivityId(Long activityId)
//    {
//        this.activityId = activityId;
//    }
//
//    public Long getActivityId()
//    {
//        return activityId;
//    }
//    public void setStatus(Integer status)
//    {
//        this.status = status;
//    }
//
//    public Integer getStatus()
//    {
//        return status;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//            .append("id", getId())
//            .append("userId", getUserId())
//            .append("activityId", getActivityId())
//            .append("status", getStatus())
//            .append("createTime", getCreateTime())
//            .append("createBy", getCreateBy())
//            .append("updateTime", getUpdateTime())
//            .append("updateBy", getUpdateBy())
//            .toString();
//    }
//}