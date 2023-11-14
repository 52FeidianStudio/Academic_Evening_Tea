package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 *用户发布活动对象 tbl_activity
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TblActivity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 学术主题* */
    @Excel(name = "学术主题*")
    private String userImg2;

    /** 发布者id* */
    @Excel(name = "发布者id*")
    private Long userId;

    /** 发起人* */
    @Excel(name = "发起人*")
    private String userName;

    /** 主讲人名字* */
    @Excel(name = "主讲人人*")
    private String speakerName;

    /** 学院* */
    @Excel(name = "学院*")
    private String hbKeyword;

    /** 学号* */
    @Excel(name = "学号*")
    private String userTel;

    /** 举办时间* */
    @Excel(name = "举办时间*")
    private String lat;

    /** 举办地点* */
    @Excel(name = "举办地点*")
    private String address;

    /** 总人数* */
    @Excel(name = "总人数*")
    private Long hot;

    /** 线上地址* */
    @Excel(name = "线上地址*")
    private String cityname;

    /** 发起人电话 */
    @Excel(name = "发起人电话")
    private String qphone;

    /** 学术简介* */
    @Excel(name = "学术简介*")
    private String details;

    /** 发起人简介* */
    @Excel(name = "主讲人简介*")
    private String img;

    /** 发布时间* */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间*", width = 30, dateFormat = "yyyy-MM-dd")
    private String time;

    /**学院 人数限制 数组* */
    private  List<DeptActivity> deptActivities;
    private DeptNum[]  deptNums;
    //判断该用户是否已经报过名
    private List<TblUserActivity>  isApplication;

    /** 1未精选2已精选* */
    @Excel(name = "1未精选2已精选*")
    private Integer type;

    /** 1待举办2已举办* */
    @Excel(name = "1审核中2审核通过报名中3报名截止4活动进行中5活动已结束*")
    private Integer state;
    /** 1报名未结束2报名已结束* */
    @Excel(name = "1审核中2审核通过*")
    private Integer isClose;

    /** 1进行中2已结束* */
    @Excel(name = "1审核中2审核通过*")
    private Integer isEnd;

    /** 预留字段1 报名二维码*/
    @Excel(name = "签到二维码路径")
    private String signinFilePath;

    /** 预留字段2  签到二维码*/
    @Excel(name = "报名二维码路径")
    private String applicationFilePath;
    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
    //创建人
    private  String createBy;
    //更新人
    private  String updateBy;


    /** 报名人数* */
    @Excel(name = "报名人数*")
    private Long hbNum;

    /** 举办人自评分数* */
    @Excel(name = "举办人自评分数*")
    private Long top;

    /** 报名者评分总数* */
    @Excel(name = "报名者评分总数*")
    private Long givelike;

    /** 已参与评分人数* */
    @Excel(name = "已参与评分人数*")
    private Long views;

    /** 最后得分* */
    @Excel(name = "最后得分*")
    private BigDecimal money;

    /** 微信头像* */
    @Excel(name = "微信头像*")
    private String hong;

    /** 活动照片* */
    @Excel(name = "活动照片*")
    private String lng;
    private Integer pageNum;
    private Integer pageSize;


//    public void setId(Long id)
//    {
//        this.id = id;
//    }
//
//    public Long getId()
//    {
//        return id;
//    }
//    public void setDetails(String details)
//    {
//        this.details = details;
//    }
//
//    public String getDetails()
//    {
//        return details;
//    }
//    public void setImg(String img)
//    {
//        this.img = img;
//    }
//
//    public String getImg()
//    {
//        return img;
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
//    public void setUserName(String userName)
//    {
//        this.userName = userName;
//    }
//
//    public String getUserName()
//    {
//        return userName;
//    }
//    public void setUserTel(String userTel)
//    {
//        this.userTel = userTel;
//    }
//
//    public String getUserTel()
//    {
//        return userTel;
//    }
//    public void setHot(Long hot)
//    {
//        this.hot = hot;
//    }
//
//    public Long getHot()
//    {
//        return hot;
//    }
//    public void setHbNum(Long hbNum)
//    {
//        this.hbNum = hbNum;
//    }
//
//    public Long getHbNum()
//    {
//        return hbNum;
//    }
//    public void setTop(Long top)
//    {
//        this.top = top;
//    }
//
//    public Long getTop()
//    {
//        return top;
//    }
//    public void setGivelike(Long givelike)
//    {
//        this.givelike = givelike;
//    }
//
//    public Long getGivelike()
//    {
//        return givelike;
//    }
//    public void setViews(Long views)
//    {
//        this.views = views;
//    }
//
//    public Long getViews()
//    {
//        return views;
//    }
//    public void setMoney(BigDecimal money)
//    {
//        this.money = money;
//    }
//
//    public BigDecimal getMoney()
//    {
//        return money;
//    }
//    public void setTypeId(Long typeId)
//    {
//        this.typeId = typeId;
//    }
//
//    public Long getTypeId()
//    {
//        return typeId;
//    }
//    public void setState(Long state)
//    {
//        this.state = state;
//    }
//
//    public Long getState()
//    {
//        return state;
//    }
//    public void setTime(Date time)
//    {
//        this.time = time;
//    }
//
//    public Date getTime()
//    {
//        return time;
//    }
//    public void setAddress(String address)
//    {
//        this.address = address;
//    }
//
//    public String getAddress()
//    {
//        return address;
//    }
//    public void setHbKeyword(String hbKeyword)
//    {
//        this.hbKeyword = hbKeyword;
//    }
//
//    public String getHbKeyword()
//    {
//        return hbKeyword;
//    }
//    public void setHong(String hong)
//    {
//        this.hong = hong;
//    }
//
//    public String getHong()
//    {
//        return hong;
//    }
//    public void setUserImg2(String userImg2)
//    {
//        this.userImg2 = userImg2;
//    }
//
//    public String getUserImg2()
//    {
//        return userImg2;
//    }
//    public void setCityname(String cityname)
//    {
//        this.cityname = cityname;
//    }
//
//    public String getCityname()
//    {
//        return cityname;
//    }
//    public void setLat(String lat)
//    {
//        this.lat = lat;
//    }
//
//    public String getLat()
//    {
//        return lat;
//    }
//    public void setLng(String lng)
//    {
//        this.lng = lng;
//    }
//
//    public String getLng()
//    {
//        return lng;
//    }
//    public void setQphone(String qphone)
//    {
//        this.qphone = qphone;
//    }
//
//    public String getQphone()
//    {
//        return qphone;
//    }
//    public void setStr1(String str1)
//    {
//        this.str1 = str1;
//    }
//
//    public String getStr1()
//    {
//        return str1;
//    }
//    public void setStr2(String str2)
//    {
//        this.str2 = str2;
//    }
//
//    public String getStr2()
//    {
//        return str2;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//            .append("id", getId())
//            .append("details", getDetails())
//            .append("img", getImg())
//            .append("userId", getUserId())
//            .append("userName", getUserName())
//            .append("userTel", getUserTel())
//            .append("hot", getHot())
//            .append("hbNum", getHbNum())
//            .append("top", getTop())
//            .append("givelike", getGivelike())
//            .append("views", getViews())
//            .append("money", getMoney())
//            .append("typeId", getTypeId())
//            .append("state", getState())
//            .append("time", getTime())
//            .append("address", getAddress())
//            .append("hbKeyword", getHbKeyword())
//            .append("hong", getHong())
//            .append("userImg2", getUserImg2())
//            .append("cityname", getCityname())
//            .append("lat", getLat())
//            .append("lng", getLng())
//            .append("qphone", getQphone())
//            .append("str1", getStr1())
//            .append("str2", getStr2())
//            .toString();
//    }
}
