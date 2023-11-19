package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TblGoods
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

    private List<String> imgs;
    //库存数量
private  Integer allnums;

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
//    public void setName(String name)
//    {
//        this.name = name;
//    }
//
//    public String getName()
//    {
//        return name;
//    }
//    public void setPrice(BigDecimal price)
//    {
//        this.price = price;
//    }
//
//    public BigDecimal getPrice()
//    {
//        return price;
//    }
//    public void setIntroduce(String introduce)
//    {
//        this.introduce = introduce;
//    }
//
//    public String getIntroduce()
//    {
//        return introduce;
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
//    public void setCoverPicture(String coverPicture)
//    {
//        this.coverPicture = coverPicture;
//    }
//
//    public String getCoverPicture()
//    {
//        return coverPicture;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//            .append("id", getId())
//            .append("name", getName())
//            .append("price", getPrice())
//            .append("introduce", getIntroduce())
//            .append("picture", getPicture())
//            .append("coverPicture", getCoverPicture())
//            .append("updateTime", getUpdateTime())
//            .append("updateBy", getUpdateBy())
//            .append("createTime", getCreateTime())
//            .append("createBy", getCreateBy())
//            .toString();
//    }
}
