package com.ruoyi.sales_status.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品销售状态管理对象 product_sales_status
 * 
 * @author ruoyi
 * @date 2022-02-17
 */
public class ProductSalesStatus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品销售状态ID */
    private Long id;

    /** 产品销售状态名称 */
    @Excel(name = "产品销售状态名称")
    private String statusName;

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
    public void setStatusName(String statusName) 
    {
        this.statusName = statusName;
    }

    public String getStatusName() 
    {
        return statusName;
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
            .append("statusName", getStatusName())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("operatorId", getOperatorId())
            .toString();
    }
}
