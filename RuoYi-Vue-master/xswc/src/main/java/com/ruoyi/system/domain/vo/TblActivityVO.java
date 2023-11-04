package com.ruoyi.system.domain.vo;



import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TblActivityVO    {
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
    @Excel(name = "主讲人*")
    private String speakerName;

    /** 学院* */
    @Excel(name = "学院*")
    private String hbKeyword;

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


    /** 学术简介* */
    @Excel(name = "学术简介*")
    private String details;

    /** 发起人简介* */
    @Excel(name = "主讲人简介*")
    private String img;


    /** 1未精选2已精选* */
    @Excel(name = "1未精选2已精选*")
    private Long type;

    /** 1审核中2审核通过* */
    @Excel(name = "1审核中2审核通过*")
    private Long state;

    /** 1报名未结束2报名已结束* */
    @Excel(name = "1报名未结束2报名已结束*")
    private Long isClose;

    /** 1进行中2已结束* */
    @Excel(name = "1进行中2已结束*")
    private Long isEnd;

    /** 预留字段1 报名二维码*/
    @Excel(name = "预留字段1")
    private String str1;

    /** 预留字段2  签到二维码*/
    @Excel(name = "预留字段2")
    private String str2;



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
}
