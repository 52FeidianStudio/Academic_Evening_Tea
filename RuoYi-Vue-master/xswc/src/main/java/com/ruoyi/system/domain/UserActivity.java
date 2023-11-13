package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserActivity {
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

    /** 学术主题* */
    @Excel(name = "学术主题*")
    private String userImg2;


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
    private DeptNum[]  deptNums;

    //判断该用户是否已经报过名
    private List<TblUserActivity> isApplication;

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


    /** 报名人数* */
    @Excel(name = "报名人数*")
    private Long hbNum;

}
