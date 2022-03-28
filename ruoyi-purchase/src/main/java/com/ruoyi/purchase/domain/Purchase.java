package com.ruoyi.purchase.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购确认对象 purchase
 * 
 * @author ruoyi
 * @date 2022-03-22
 */
public class Purchase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 采购单号 */
    @Excel(name = "采购单号")
    private String purchaseId;

    /** 仓库ID */
    @Excel(name = "仓库ID")
    private Long warehouseId;

    /** 供应商ID */
    @Excel(name = "供应商ID")
    private Long supplierId;

    /** 采购员ID */
    @Excel(name = "采购员ID")
    private Long buyerId;

    /** 跟踪单号 */
    @Excel(name = "跟踪单号")
    private Long trackingNumber;

    /** 结算方式 */
    private String settlementMethod;

    /** 币种 */
    private String currency;

    /** 支付方式 */
    private String paymentMethod;

    /** 收款方式 */
    private String collectionMethod;

    /** 支付周期类型 */
    private String paymentCycleType;

    /** 供应商运输方式 */
    @Excel(name = "供应商运输方式")
    private String supplierShippingType;

    /** 运费 */
    private String freight;

    /** 采购单预计到货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "采购单预计到货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date estimatedArrivalTime;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 操作人 */
    @Excel(name = "操作人")
    private Long operatorId;

    public void setPurchaseId(String purchaseId) 
    {
        this.purchaseId = purchaseId;
    }

    public String getPurchaseId() 
    {
        return purchaseId;
    }
    public void setWarehouseId(Long warehouseId) 
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId() 
    {
        return warehouseId;
    }
    public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }
    public void setBuyerId(Long buyerId) 
    {
        this.buyerId = buyerId;
    }

    public Long getBuyerId() 
    {
        return buyerId;
    }
    public void setTrackingNumber(Long trackingNumber) 
    {
        this.trackingNumber = trackingNumber;
    }

    public Long getTrackingNumber() 
    {
        return trackingNumber;
    }
    public void setSettlementMethod(String settlementMethod) 
    {
        this.settlementMethod = settlementMethod;
    }

    public String getSettlementMethod() 
    {
        return settlementMethod;
    }
    public void setCurrency(String currency) 
    {
        this.currency = currency;
    }

    public String getCurrency() 
    {
        return currency;
    }
    public void setPaymentMethod(String paymentMethod) 
    {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() 
    {
        return paymentMethod;
    }
    public void setCollectionMethod(String collectionMethod) 
    {
        this.collectionMethod = collectionMethod;
    }

    public String getCollectionMethod() 
    {
        return collectionMethod;
    }
    public void setPaymentCycleType(String paymentCycleType) 
    {
        this.paymentCycleType = paymentCycleType;
    }

    public String getPaymentCycleType() 
    {
        return paymentCycleType;
    }
    public void setSupplierShippingType(String supplierShippingType) 
    {
        this.supplierShippingType = supplierShippingType;
    }

    public String getSupplierShippingType() 
    {
        return supplierShippingType;
    }
    public void setFreight(String freight) 
    {
        this.freight = freight;
    }

    public String getFreight() 
    {
        return freight;
    }
    public void setEstimatedArrivalTime(Date estimatedArrivalTime) 
    {
        this.estimatedArrivalTime = estimatedArrivalTime;
    }

    public Date getEstimatedArrivalTime() 
    {
        return estimatedArrivalTime;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
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
            .append("purchaseId", getPurchaseId())
            .append("warehouseId", getWarehouseId())
            .append("supplierId", getSupplierId())
            .append("buyerId", getBuyerId())
            .append("trackingNumber", getTrackingNumber())
            .append("settlementMethod", getSettlementMethod())
            .append("currency", getCurrency())
            .append("paymentMethod", getPaymentMethod())
            .append("collectionMethod", getCollectionMethod())
            .append("paymentCycleType", getPaymentCycleType())
            .append("supplierShippingType", getSupplierShippingType())
            .append("freight", getFreight())
            .append("estimatedArrivalTime", getEstimatedArrivalTime())
            .append("deptId", getDeptId())
            .append("remarks", getRemarks())
            .append("operatorId", getOperatorId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
