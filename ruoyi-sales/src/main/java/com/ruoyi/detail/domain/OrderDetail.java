package com.ruoyi.detail.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单详情对象 order_detail
 * 
 * @author ruoyi
 * @date 2022-05-11
 */
public class OrderDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单详情ID */
    private Long id;

    /** 订单ID */
    @Excel(name = "订单ID")
    private Long orderKeyId;

    /** 产品SKU */
    @Excel(name = "产品SKU")
    private String productSku;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 数量 */
    @Excel(name = "数量")
    private Integer quantity;

    /** 单价 */
    @Excel(name = "单价")
    private String price;

    /** 库存 */
    @Excel(name = "库存")
    private Integer stock;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderKeyId(Long orderKeyId) 
    {
        this.orderKeyId = orderKeyId;
    }

    public Long getOrderKeyId() 
    {
        return orderKeyId;
    }
    public void setProductSku(String productSku) 
    {
        this.productSku = productSku;
    }

    public String getProductSku() 
    {
        return productSku;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setQuantity(Integer quantity) 
    {
        this.quantity = quantity;
    }

    public Integer getQuantity() 
    {
        return quantity;
    }
    public void setPrice(String price) 
    {
        this.price = price;
    }

    public String getPrice() 
    {
        return price;
    }
    public void setStock(Integer stock) 
    {
        this.stock = stock;
    }

    public Integer getStock() 
    {
        return stock;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderKeyId", getOrderKeyId())
            .append("productSku", getProductSku())
            .append("productName", getProductName())
            .append("quantity", getQuantity())
            .append("price", getPrice())
            .append("stock", getStock())
            .append("remarks", getRemarks())
            .toString();
    }
}
