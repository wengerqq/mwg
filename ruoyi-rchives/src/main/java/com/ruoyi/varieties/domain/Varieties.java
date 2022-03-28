package com.ruoyi.varieties.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 品类管理对象 varieties
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
public class Varieties extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 品类ID */
    private Long id;

    /** 品类名称 */
    @Excel(name = "品类名称")
    private String varietiesName;

    /** 品类名称英文 */
    @Excel(name = "品类名称英文")
    private String varietiesCodeEn;

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
    public void setVarietiesName(String varietiesName) 
    {
        this.varietiesName = varietiesName;
    }

    public String getVarietiesName() 
    {
        return varietiesName;
    }
    public void setVarietiesCodeEn(String varietiesCodeEn) 
    {
        this.varietiesCodeEn = varietiesCodeEn;
    }

    public String getVarietiesCodeEn() 
    {
        return varietiesCodeEn;
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
            .append("varietiesName", getVarietiesName())
            .append("varietiesCodeEn", getVarietiesCodeEn())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("operatorId", getOperatorId())
            .toString();
    }
}
