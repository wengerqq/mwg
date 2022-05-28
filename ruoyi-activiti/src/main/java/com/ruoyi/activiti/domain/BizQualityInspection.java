package com.ruoyi.activiti.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购质检对象 biz_quality_inspection
 * 
 * @author ruoyi
 * @date 2022-05-20
 */
public class BizQualityInspection extends ProcessEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNo;

    /** 采购数量 */
    @Excel(name = "采购数量")
    private Long purchaseNumber;

    /** 申请人 */
    private String applyUserId;

    /** 申请人 */
    @Excel(name = "申请人")
    private String applyUserName;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyTime;

    /** 流程实例ID */
    @Excel(name = "流程实例ID")
    private String instanceId;

    /** 流程定义key */
    private String processKey;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderNo(String orderNo) 
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo() 
    {
        return orderNo;
    }
    public void setPurchaseNumber(Long purchaseNumber) 
    {
        this.purchaseNumber = purchaseNumber;
    }

    public Long getPurchaseNumber() 
    {
        return purchaseNumber;
    }
    public void setApplyUserId(String applyUserId) 
    {
        this.applyUserId = applyUserId;
    }

    public String getApplyUserId() 
    {
        return applyUserId;
    }
    public void setApplyUserName(String applyUserName) 
    {
        this.applyUserName = applyUserName;
    }

    public String getApplyUserName() 
    {
        return applyUserName;
    }
    public void setApplyTime(Date applyTime) 
    {
        this.applyTime = applyTime;
    }

    public Date getApplyTime() 
    {
        return applyTime;
    }
    public void setInstanceId(String instanceId) 
    {
        this.instanceId = instanceId;
    }

    public String getInstanceId() 
    {
        return instanceId;
    }
    public void setProcessKey(String processKey) 
    {
        this.processKey = processKey;
    }

    public String getProcessKey() 
    {
        return processKey;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderNo", getOrderNo())
            .append("purchaseNumber", getPurchaseNumber())
            .append("applyUserId", getApplyUserId())
            .append("applyUserName", getApplyUserName())
            .append("applyTime", getApplyTime())
            .append("instanceId", getInstanceId())
            .append("processKey", getProcessKey())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
