package com.ruoyi.purchase_detail.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购产品明细对象 purchase_detail
 * 
 * @author ruoyi
 * @date 2022-03-29
 */
public class PurchaseDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 采购明细ID */
    private Long id;

    /** 采购单ID */
    private Long purchaseKeyId;

    /** 产品SKU */
    @Excel(name = "产品SKU*")
    private String productSku;

    /** 产品名称 */
    private String productName;

    /** 采购数量 */
    @Excel(name = "采购数量*")
    private Long quantity;

    /** 采购单价 */
    @Excel(name = "采购单价*")
    private String price;

    /** 参考价格 */
    @Excel(name = "参考价格")
    private String referencePrice;

    /** 税率 */
    @Excel(name = "税率")
    private String taxRate;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 外部单号 */
    @Excel(name = "外部单号")
    private String externalOrderNo;

    /** 交付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryTime;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 已收货数量 */
    @Excel(name = "已收货数量")
    private Long quantityReceived;

    /** 是否已上架 */
    @Excel(name = "是否已上架")
    private Long isShelf;

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
    public void setPrice(String price) 
    {
        this.price = price;
    }

    public String getPrice() 
    {
        return price;
    }
    public void setReferencePrice(String referencePrice) 
    {
        this.referencePrice = referencePrice;
    }

    public String getReferencePrice() 
    {
        return referencePrice;
    }
    public void setTaxRate(String taxRate) 
    {
        this.taxRate = taxRate;
    }

    public String getTaxRate() 
    {
        return taxRate;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getRemark()
    {
        return remark;
    }
    public void setExternalOrderNo(String externalOrderNo) 
    {
        this.externalOrderNo = externalOrderNo;
    }

    public String getExternalOrderNo() 
    {
        return externalOrderNo;
    }
    public void setDeliveryTime(Date deliveryTime) 
    {
        this.deliveryTime = deliveryTime;
    }

    public Date getDeliveryTime() 
    {
        return deliveryTime;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setQuantityReceived(Long quantityReceived)
    {
        this.quantityReceived = quantityReceived;
    }

    public Long getQuantityReceived()
    {
        return quantityReceived;
    }
    public void setIsShelf(Long isShelf)
    {
        this.isShelf = isShelf;
    }

    public Long getIsShelf()
    {
        return isShelf;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("purchaseKeyId", getPurchaseKeyId())
            .append("productSku", getProductSku())
            .append("productName", getProductName())
            .append("quantity", getQuantity())
            .append("price", getPrice())
            .append("referencePrice", getReferencePrice())
            .append("taxRate", getTaxRate())
            .append("remark", getRemark())
            .append("externalOrderNo", getExternalOrderNo())
            .append("deliveryTime", getDeliveryTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("quantityReceived", getQuantityReceived())
            .append("isShelf", getIsShelf())
            .toString();
    }
}
