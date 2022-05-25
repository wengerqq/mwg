package com.ruoyi.order.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单管理对象 order
 * 
 * @author ruoyi
 * @date 2022-05-11
 */
public class Order extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private Long id;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderId;

    /** 订单状态 */
    private Long status;

    /** 站点 */
    @Excel(name = "站点")
    private String site;

    /** 买家代码 */
    @Excel(name = "买家代码")
    private String buyerCode;

    /** 买家姓名 */
    @Excel(name = "买家姓名")
    private String buyerName;

    /** 买家邮箱 */
    @Excel(name = "买家邮箱")
    private String buyerEmail;

    /** 总金额 */
    @Excel(name = "总金额")
    private String total;

    /** 运费 */
    @Excel(name = "运费")
    private String freight;

    /** 总金额（含运费） */
    @Excel(name = "总金额", readConverterExp = "含=运费")
    private String totalFreight;

    /** 参考号 */
    @Excel(name = "参考号")
    private String referenceNumber;

    /** 仓库号 */
    @Excel(name = "仓库号")
    private String warehouseNumber;

    /** 发货仓库ID */
    @Excel(name = "发货仓库ID")
    private Long deliveryWarehouseId;

    /** 支付方式ID */
    @Excel(name = "支付方式ID")
    private Long paymentMethodId;

    /** 配送仓库ID */
    @Excel(name = "配送仓库ID")
    private Long warehouseDeliveryId;

    /** 重量 */
    @Excel(name = "重量")
    private String weight;

    /** 收件人税号 */
    @Excel(name = "收件人税号")
    private String recipientTaxNumber;

    /** 客服备注 */
    @Excel(name = "客服备注")
    private String customerServiceRemarks;

    /** 订单备注 */
    @Excel(name = "订单备注")
    private String orderRemarks;

    /** 税号所属国家代码 */
    @Excel(name = "税号所属国家代码")
    private String countryCodeOfTaxNumber;

    /** 收件人 */
    @Excel(name = "收件人")
    private String addressee;

    /** 手机电话 */
    @Excel(name = "手机电话")
    private Long phone;

    /** 公司名 */
    @Excel(name = "公司名")
    private String companyName;

    /** 收件人国家 */
    @Excel(name = "收件人国家")
    private String recipientCountry;

    /** 收件人省/州 */
    @Excel(name = "收件人省/州")
    private String recipientProvinceState;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 区 */
    @Excel(name = "区")
    private String district;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String detailedAddress;

    /** 门牌号 */
    @Excel(name = "门牌号")
    private String houseNumber;

    /** 邮编 */
    @Excel(name = "邮编")
    private String zipCode;

    /** CPF税号/通关序列号 */
    @Excel(name = "CPF税号/通关序列号")
    private String cpfTaxOrCustomsClearanceSerialNumber;

    /** 账单地址 */
    @Excel(name = "账单地址")
    private String billingAddress;

    /** 自定义标记编号 */
    @Excel(name = "自定义标记编号")
    private Long signType;

    /** 终审标志 */
    @Excel(name = "终审标志")
    private Long finalMark;

    /** 账单地址 */
    @Excel(name = "账单地址")
    private String abnormal;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditTime;

    /** 发货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setSite(String site) 
    {
        this.site = site;
    }

    public String getSite() 
    {
        return site;
    }
    public void setBuyerCode(String buyerCode) 
    {
        this.buyerCode = buyerCode;
    }

    public String getBuyerCode() 
    {
        return buyerCode;
    }
    public void setBuyerName(String buyerName) 
    {
        this.buyerName = buyerName;
    }

    public String getBuyerName() 
    {
        return buyerName;
    }
    public void setBuyerEmail(String buyerEmail) 
    {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerEmail() 
    {
        return buyerEmail;
    }
    public void setTotal(String total) 
    {
        this.total = total;
    }

    public String getTotal() 
    {
        return total;
    }
    public void setFreight(String freight) 
    {
        this.freight = freight;
    }

    public String getFreight() 
    {
        return freight;
    }
    public void setTotalFreight(String totalFreight) 
    {
        this.totalFreight = totalFreight;
    }

    public String getTotalFreight() 
    {
        return totalFreight;
    }
    public void setReferenceNumber(String referenceNumber) 
    {
        this.referenceNumber = referenceNumber;
    }

    public String getReferenceNumber() 
    {
        return referenceNumber;
    }
    public void setWarehouseNumber(String warehouseNumber) 
    {
        this.warehouseNumber = warehouseNumber;
    }

    public String getWarehouseNumber() 
    {
        return warehouseNumber;
    }
    public void setDeliveryWarehouseId(Long deliveryWarehouseId) 
    {
        this.deliveryWarehouseId = deliveryWarehouseId;
    }

    public Long getDeliveryWarehouseId() 
    {
        return deliveryWarehouseId;
    }
    public void setPaymentMethodId(Long paymentMethodId) 
    {
        this.paymentMethodId = paymentMethodId;
    }

    public Long getPaymentMethodId() 
    {
        return paymentMethodId;
    }
    public void setWarehouseDeliveryId(Long warehouseDeliveryId) 
    {
        this.warehouseDeliveryId = warehouseDeliveryId;
    }

    public Long getWarehouseDeliveryId() 
    {
        return warehouseDeliveryId;
    }
    public void setWeight(String weight) 
    {
        this.weight = weight;
    }

    public String getWeight() 
    {
        return weight;
    }
    public void setRecipientTaxNumber(String recipientTaxNumber) 
    {
        this.recipientTaxNumber = recipientTaxNumber;
    }

    public String getRecipientTaxNumber() 
    {
        return recipientTaxNumber;
    }
    public void setCustomerServiceRemarks(String customerServiceRemarks) 
    {
        this.customerServiceRemarks = customerServiceRemarks;
    }

    public String getCustomerServiceRemarks() 
    {
        return customerServiceRemarks;
    }
    public void setOrderRemarks(String orderRemarks) 
    {
        this.orderRemarks = orderRemarks;
    }

    public String getOrderRemarks() 
    {
        return orderRemarks;
    }
    public void setCountryCodeOfTaxNumber(String countryCodeOfTaxNumber) 
    {
        this.countryCodeOfTaxNumber = countryCodeOfTaxNumber;
    }

    public String getCountryCodeOfTaxNumber() 
    {
        return countryCodeOfTaxNumber;
    }
    public void setAddressee(String addressee) 
    {
        this.addressee = addressee;
    }

    public String getAddressee() 
    {
        return addressee;
    }
    public void setPhone(Long phone) 
    {
        this.phone = phone;
    }

    public Long getPhone() 
    {
        return phone;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setRecipientCountry(String recipientCountry) 
    {
        this.recipientCountry = recipientCountry;
    }

    public String getRecipientCountry() 
    {
        return recipientCountry;
    }
    public void setRecipientProvinceState(String recipientProvinceState) 
    {
        this.recipientProvinceState = recipientProvinceState;
    }

    public String getRecipientProvinceState() 
    {
        return recipientProvinceState;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setDistrict(String district) 
    {
        this.district = district;
    }

    public String getDistrict() 
    {
        return district;
    }
    public void setDetailedAddress(String detailedAddress) 
    {
        this.detailedAddress = detailedAddress;
    }

    public String getDetailedAddress() 
    {
        return detailedAddress;
    }
    public void setHouseNumber(String houseNumber) 
    {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumber() 
    {
        return houseNumber;
    }
    public void setZipCode(String zipCode) 
    {
        this.zipCode = zipCode;
    }

    public String getZipCode() 
    {
        return zipCode;
    }
    public void setCpfTaxOrCustomsClearanceSerialNumber(String cpfTaxOrCustomsClearanceSerialNumber) 
    {
        this.cpfTaxOrCustomsClearanceSerialNumber = cpfTaxOrCustomsClearanceSerialNumber;
    }

    public String getCpfTaxOrCustomsClearanceSerialNumber() 
    {
        return cpfTaxOrCustomsClearanceSerialNumber;
    }
    public void setBillingAddress(String billingAddress) 
    {
        this.billingAddress = billingAddress;
    }

    public String getBillingAddress() 
    {
        return billingAddress;
    }
    public void setSignType(Long signType)
    {
        this.signType = signType;
    }

    public Long getSignType()
    {
        return signType;
    }
    public void setFinalMark(Long finalMark)
    {
        this.finalMark = finalMark;
    }

    public Long getFinalMark()
    {
        return finalMark;
    }
    public void setAbnormal(String abnormal)
    {
        this.abnormal = abnormal;
    }

    public String getAbnormal()
    {
        return abnormal;
    }
    public void setAuditTime(Date auditTime) 
    {
        this.auditTime = auditTime;
    }

    public Date getAuditTime() 
    {
        return auditTime;
    }
    public void setDeliveryTime(Date deliveryTime) 
    {
        this.deliveryTime = deliveryTime;
    }

    public Date getDeliveryTime() 
    {
        return deliveryTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("status",getStatus())
            .append("site", getSite())
            .append("buyerCode", getBuyerCode())
            .append("buyerName", getBuyerName())
            .append("buyerEmail", getBuyerEmail())
            .append("total", getTotal())
            .append("freight", getFreight())
            .append("totalFreight", getTotalFreight())
            .append("referenceNumber", getReferenceNumber())
            .append("warehouseNumber", getWarehouseNumber())
            .append("deliveryWarehouseId", getDeliveryWarehouseId())
            .append("paymentMethodId", getPaymentMethodId())
            .append("warehouseDeliveryId", getWarehouseDeliveryId())
            .append("weight", getWeight())
            .append("recipientTaxNumber", getRecipientTaxNumber())
            .append("customerServiceRemarks", getCustomerServiceRemarks())
            .append("orderRemarks", getOrderRemarks())
            .append("countryCodeOfTaxNumber", getCountryCodeOfTaxNumber())
            .append("addressee", getAddressee())
            .append("phone", getPhone())
            .append("companyName", getCompanyName())
            .append("recipientCountry", getRecipientCountry())
            .append("recipientProvinceState", getRecipientProvinceState())
            .append("city", getCity())
            .append("district", getDistrict())
            .append("detailedAddress", getDetailedAddress())
            .append("houseNumber", getHouseNumber())
            .append("zipCode", getZipCode())
            .append("cpfTaxOrCustomsClearanceSerialNumber", getCpfTaxOrCustomsClearanceSerialNumber())
            .append("billingAddress", getBillingAddress())
            .append("signType", getSignType())
            .append("finalMark", getFinalMark())
            .append("abnormal", getAbnormal())
            .append("auditTime", getAuditTime())
            .append("deliveryTime", getDeliveryTime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
