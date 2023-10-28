package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 tbl_goods_user
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
public class TblGoodsUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 联查id */
    private Long id;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private Integer goodsId;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Integer userId;

    /** 数量 */
    @Excel(name = "数量")
    private Integer nums;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGoodsId(Integer goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Integer getGoodsId() 
    {
        return goodsId;
    }
    public void setUserId(Integer userId) 
    {
        this.userId = userId;
    }

    public Integer getUserId() 
    {
        return userId;
    }
    public void setNums(Integer nums) 
    {
        this.nums = nums;
    }

    public Integer getNums() 
    {
        return nums;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsId", getGoodsId())
            .append("userId", getUserId())
            .append("nums", getNums())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .toString();
    }
}
