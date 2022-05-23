package com.ruoyi.common.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PurchaseUploadData {

    /** 仓库ID */
    private Long warehouseId;

    /** 是否中转 */
    private Long transferOrNot;

    /** 供应商ID */
    private Long supplierId;

    /** 采购员ID */
    private Long buyerId;

    /** 跟踪单号 */
    private String trackingNumber;

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
    private String supplierShippingType;

    /** 运费 */
    private String freight;

    /** 采购单预计到货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date estimatedArrivalTime;

    /** 部门ID */
    private Long deptId;

    /** 备注 */
    private String remarks;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public void setWarehouseId(Long warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId()
    {
        return warehouseId;
    }
    public void setTransferOrNot(Long transferOrNot)
    {
        this.transferOrNot = transferOrNot;
    }

    public Long getTransferOrNot()
    {
        return transferOrNot;
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
    public void setTrackingNumber(String trackingNumber)
    {
        this.trackingNumber = trackingNumber;
    }

    public String getTrackingNumber()
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
    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

}
