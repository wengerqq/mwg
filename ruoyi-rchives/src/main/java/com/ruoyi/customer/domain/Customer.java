package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户管理对象 customer
 * 
 * @author ruoyi
 * @date 2022-02-17
 */
public class Customer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户ID */
    private Long id;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customerName;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contacts;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 电子邮箱 */
    @Excel(name = "电子邮箱")
    private String email;

    /** 传真 */
    @Excel(name = "传真")
    private String fax;

    /** 期初应收 */
    @Excel(name = "期初应收")
    private String openingReceivable;

    /** 期末应收 */
    @Excel(name = "期末应收")
    private String closingReceivable;

    /** 纳税人识别号 */
    @Excel(name = "纳税人识别号")
    private String taxpayerIdentificationNumber;

    /** 税率 */
    @Excel(name = "税率")
    private String taxRate;

    /** 开户行 */
    @Excel(name = "开户行")
    private String bankName;

    /** 账号 */
    @Excel(name = "账号")
    private String accountNumber;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long operatorId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }
    public void setContacts(String contacts) 
    {
        this.contacts = contacts;
    }

    public String getContacts() 
    {
        return contacts;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setFax(String fax) 
    {
        this.fax = fax;
    }

    public String getFax() 
    {
        return fax;
    }
    public void setOpeningReceivable(String openingReceivable) 
    {
        this.openingReceivable = openingReceivable;
    }

    public String getOpeningReceivable() 
    {
        return openingReceivable;
    }
    public void setClosingReceivable(String closingReceivable) 
    {
        this.closingReceivable = closingReceivable;
    }

    public String getClosingReceivable() 
    {
        return closingReceivable;
    }
    public void setTaxpayerIdentificationNumber(String taxpayerIdentificationNumber) 
    {
        this.taxpayerIdentificationNumber = taxpayerIdentificationNumber;
    }

    public String getTaxpayerIdentificationNumber() 
    {
        return taxpayerIdentificationNumber;
    }
    public void setTaxRate(String taxRate) 
    {
        this.taxRate = taxRate;
    }

    public String getTaxRate() 
    {
        return taxRate;
    }
    public void setBankName(String bankName) 
    {
        this.bankName = bankName;
    }

    public String getBankName() 
    {
        return bankName;
    }
    public void setAccountNumber(String accountNumber) 
    {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() 
    {
        return accountNumber;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
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
            .append("id", getId())
            .append("customerName", getCustomerName())
            .append("contacts", getContacts())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("fax", getFax())
            .append("openingReceivable", getOpeningReceivable())
            .append("closingReceivable", getClosingReceivable())
            .append("taxpayerIdentificationNumber", getTaxpayerIdentificationNumber())
            .append("taxRate", getTaxRate())
            .append("bankName", getBankName())
            .append("accountNumber", getAccountNumber())
            .append("address", getAddress())
            .append("remarks", getRemarks())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("operatorId", getOperatorId())
            .toString();
    }
}
