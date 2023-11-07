package com.ruoyi.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 【请填写功能名称】对象 tbl_feedback
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TblFeedback
{
    private static final long serialVersionUID = 1L;

    /** 意见反馈编号 */
    private Long id;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    /** 反馈内容
 */
    @Excel(name = "反馈内容 ")
    private String content;

    /** 姓名（前端传） */
    @Excel(name = "姓名", readConverterExp = "前=端传")
    private String name;

    /** 电话号码（前段） */
    @Excel(name = "电话号码", readConverterExp = "前=段")
    private String phone;

    /** 图片存储 */
    @Excel(name = "图片存储")
    private String picture;

    //给前端的图片合集
    private List<String>  imgs;


    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
    //创建人
    private  String createBy;
    //更新人
    private  String updateBy;
//
//    public void setId(Long id)
//    {
//        this.id = id;
//    }
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
//    public void setContent(String content)
//    {
//        this.content = content;
//    }
//
//    public String getContent()
//    {
//        return content;
//    }
//    public void setName(String name)
//    {
//        this.name = name;
//    }
//
//    public String getName()
//    {
//        return name;
//    }
//    public void setPhone(String phone)
//    {
//        this.phone = phone;
//    }
//
//    public String getPhone()
//    {
//        return phone;
//    }
//    public void setPicture(String picture)
//    {
//        this.picture = picture;
//    }
//
//    public String getPicture()
//    {
//        return picture;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//            .append("id", getId())
//            .append("userId", getUserId())
//            .append("content", getContent())
//            .append("name", getName())
//            .append("phone", getPhone())
//            .append("picture", getPicture())
//            .append("updateBy", getUpdateBy())
//            .append("createBy", getCreateBy())
//            .append("updateTime", getUpdateTime())
//            .append("createTime", getCreateTime())
//            .toString();
//    }
}
