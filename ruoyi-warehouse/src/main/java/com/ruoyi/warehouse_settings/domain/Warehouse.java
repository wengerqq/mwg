package com.ruoyi.warehouse_settings.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 仓库设置对象 warehouse
 * 
 * @author ruoyi
 * @date 2022-03-08
 */
public class Warehouse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 仓库ID */
    @Excel(name = "仓库ID")
    private Long id;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    private String warehouseName;

    /** 仓库状态 */
    @Excel(name = "仓库状态ID")
    private String status;

    /** 仓库类型 */
    @Excel(name = "仓库类型ID")
    private String type;

    /** 运营方式 */
    @Excel(name = "运营方式ID")
    private String operate;

    /** 是否头程 */
    @Excel(name = "是否头程ID")
    private String headStroke;

    /** 国家地区 */
    @Excel(name = "国家地区ID")
    private String country;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 邮编 */
    @Excel(name = "邮编")
    private String zipCode;

    /** 门牌号 */
    @Excel(name = "门牌号")
    private String houseNumber;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String corporateName;

    /** 地址一 */
    @Excel(name = "地址一")
    private String addressOne;

    /** 地址二 */
    @Excel(name = "地址二")
    private String addressTwo;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contacts;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    /** 操作人ID */
    private Long operatorId;

    private Boolean isQuery;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setWarehouseName(String warehouseName) 
    {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseName() 
    {
        return warehouseName;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setOperate(String operate)
    {
        this.operate = operate;
    }

    public String getOperate()
    {
        return operate;
    }
    public void setHeadStroke(String headStroke)
    {
        this.headStroke = headStroke;
    }

    public String getHeadStroke()
    {
        return headStroke;
    }
    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCountry()
    {
        return country;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setZipCode(String zipCode) 
    {
        this.zipCode = zipCode;
    }

    public String getZipCode() 
    {
        return zipCode;
    }
    public void setHouseNumber(String houseNumber) 
    {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumber() 
    {
        return houseNumber;
    }
    public void setCorporateName(String corporateName) 
    {
        this.corporateName = corporateName;
    }

    public String getCorporateName() 
    {
        return corporateName;
    }
    public void setAddressOne(String addressOne) 
    {
        this.addressOne = addressOne;
    }

    public String getAddressOne() 
    {
        return addressOne;
    }
    public void setAddressTwo(String addressTwo) 
    {
        this.addressTwo = addressTwo;
    }

    public String getAddressTwo() 
    {
        return addressTwo;
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
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setOperatorId(Long operatorId) 
    {
        this.operatorId = operatorId;
    }

    public Long getOperatorId() 
    {
        return operatorId;
    }
    public void setIsQuery(Boolean isQuery)
    {
        this.isQuery = isQuery;
    }

    public Boolean getIsQuery()
    {
        return isQuery;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("warehouseName", getWarehouseName())
            .append("status", getStatus())
            .append("type", getType())
            .append("operate", getOperate())
            .append("headStroke", getHeadStroke())
            .append("country", getCountry())
            .append("province", getProvince())
            .append("city", getCity())
            .append("zipCode", getZipCode())
            .append("houseNumber", getHouseNumber())
            .append("corporateName", getCorporateName())
            .append("addressOne", getAddressOne())
            .append("addressTwo", getAddressTwo())
            .append("contacts", getContacts())
            .append("phone", getPhone())
            .append("deptId", getDeptId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("operatorId", getOperatorId())
            .toString();
    }
}
