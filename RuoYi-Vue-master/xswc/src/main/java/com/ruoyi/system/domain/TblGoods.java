package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 tbl_goods
 * 
 * @author ruoyi
 * @date 2023-10-25
 */
public class TblGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品编号 */
    private Long id;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 商品介绍 */
    @Excel(name = "商品介绍")
    private String introduce;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String picture;

    /** 商品封面 */
    @Excel(name = "商品封面")
    private String coverPicture;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setIntroduce(String introduce) 
    {
        this.introduce = introduce;
    }

    public String getIntroduce() 
    {
        return introduce;
    }
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    public String getPicture() 
    {
        return picture;
    }
    public void setCoverPicture(String coverPicture) 
    {
        this.coverPicture = coverPicture;
    }

    public String getCoverPicture() 
    {
        return coverPicture;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("price", getPrice())
            .append("introduce", getIntroduce())
            .append("picture", getPicture())
            .append("coverPicture", getCoverPicture())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .toString();
    }
}
