package com.ruoyi.put.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 上架管理对象 put_detail
 * 
 * @author ruoyi
 * @date 2022-05-04
 */
public class PutDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 上架ID */
    private Long id;

    /** 采购单ID */
    @Excel(name = "采购单ID")
    private Long purchaseKeyId;

    /** 产品详情ID */
    @Excel(name = "产品详情ID")
    private Long pdId;

    /** 产品SKU */
    @Excel(name = "产品SKU*")
    private String productSku;

    /** 产品名称 */
    private String productName;

    /** 数量 */
    @Excel(name = "数量")
    private Long quantity;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 操作员 */
    @Excel(name = "操作员")
    private Long operatorId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPurchaseKeyId(Long purchaseKeyId) 
    {
        this.purchaseKeyId = purchaseKeyId;
    }

    public Long getPurchaseKeyId() 
    {
        return purchaseKeyId;
    }
    public void setPdId(Long pdId)
    {
        this.pdId = pdId;
    }

    public Long getPdId()
    {
        return pdId;
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
    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setOperatorId(Long operatorId) 
    {
        this.operatorId = operatorId;
    }

    public Long getOperatorId() 
    {
        return operatorId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("purchaseKeyId", getPurchaseKeyId())
            .append("pdId", getPdId())
            .append("productSku", getProductSku())
            .append("productName", getProductName())
            .append("quantity", getQuantity())
            .append("status", getStatus())
            .append("operatorId", getOperatorId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
