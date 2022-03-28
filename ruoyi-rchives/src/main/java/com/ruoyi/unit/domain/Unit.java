package com.ruoyi.unit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 单位管理对象 unit
 * 
 * @author ruoyi
 * @date 2022-02-18
 */
public class Unit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 单位ID */
    private Long id;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String unitName;

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
    public void setUnitName(String unitName) 
    {
        this.unitName = unitName;
    }

    public String getUnitName() 
    {
        return unitName;
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
            .append("unitName", getUnitName())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("operatorId", getOperatorId())
            .toString();
    }
}
