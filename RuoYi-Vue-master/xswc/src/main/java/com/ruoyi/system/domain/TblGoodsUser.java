package com.ruoyi.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.ruoyi.common.annotation.Excel;

import java.math.BigDecimal;


/**
 * 【请填写功能名称】对象 tbl_goods_user
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TblGoodsUser
{
    private static final long serialVersionUID = 1L;

    /** 联查id */
    private Long id;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private Long goodsId;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    /** 数量 */
    @Excel(name = "数量")
    private Integer nums;
    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    /** 商品封面 */
    @Excel(name = "商品封面")
    private String coverPicture;
    private BigDecimal total;

    //1未领取2已领取
    private  Integer state;
    private  String phonenumber;
    private  String username;
    //创建时间
    private String createTime;
    //更新时间
    private String updateTime;
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
//    public void setGoodsId(Integer goodsId)
//    {
//        this.goodsId = goodsId;
//    }
//
//    public Integer getGoodsId()
//    {
//        return goodsId;
//    }
//    public void setUserId(Integer userId)
//    {
//        this.userId = userId;
//    }
//
//    public Integer getUserId()
//    {
//        return userId;
//    }
//    public void setNums(Integer nums)
//    {
//        this.nums = nums;
//    }
//
//    public Integer getNums()
//    {
//        return nums;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//            .append("id", getId())
//            .append("goodsId", getGoodsId())
//            .append("userId", getUserId())
//            .append("nums", getNums())
//            .append("updateTime", getUpdateTime())
//            .append("updateBy", getUpdateBy())
//            .append("createTime", getCreateTime())
//            .append("createBy", getCreateBy())
//            .toString();
//    }
}
