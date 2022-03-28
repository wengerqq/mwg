package com.ruoyi.stock_check.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.products.domain.Products;
import com.ruoyi.warehouse_settings.domain.Warehouse;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.Type;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 库存查询对象 stock_check
 * 
 * @author ruoyi
 * @date 2022-03-12
 */
public class StockCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 库存ID */
    private Long id;

    /** 产品id */
    @Excel(name = "产品id")
    private Long productsId;

    /** 仓库id */
    @Excel(name = "仓库id")
    private Long warehouseId;

    /** 采购在途 */
    @Excel(name = "采购在途")
    private Long procurementInTransit;

    /** 退件在途 */
    @Excel(name = "退件在途")
    private Long returnedPartsInTransit;

    /** 待上架 */
    @Excel(name = "待上架")
    private Long readyToGoOnTheShelf;

    /** 可用数量 */
    @Excel(name = "可用数量")
    private Long availableQuantity;

    /** 可售数量 */
    @Excel(name = "可售数量")
    private Long saleableQuantity;

    /** 待出库 */
    @Excel(name = "待出库")
    private Long toBeDelivered;

    /** 操作人id */
    @Excel(name = "操作人id")
    private Long operatorId;

    /** 产品对象 */
    @Excels({
            @Excel(name = "产品SKU", targetAttr = "sku", type = Type.EXPORT),
            @Excel(name = "产品名称", targetAttr = "productName", type = Type.EXPORT),
            @Excel(name = "产品销售状态", targetAttr = "productStatus", type = Type.EXPORT),
            @Excel(name = "默认供应商", targetAttr = "userId", type = Type.EXPORT)
    })
    private Products products;

    /** 仓库对象 */
    @Excels({
            @Excel(name = "仓库名称", targetAttr = "warehouseName", type = Type.EXPORT),
    })
    private Warehouse warehouse;

    private Boolean isQuery;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProductsId(Long productsId) 
    {
        this.productsId = productsId;
    }

    public Long getProductsId() 
    {
        return productsId;
    }
    public void setWarehouseId(Long warehouseId) 
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId() 
    {
        return warehouseId;
    }
    public void setProcurementInTransit(Long procurementInTransit) 
    {
        this.procurementInTransit = procurementInTransit;
    }

    public Long getProcurementInTransit() 
    {
        return procurementInTransit;
    }
    public void setReturnedPartsInTransit(Long returnedPartsInTransit) 
    {
        this.returnedPartsInTransit = returnedPartsInTransit;
    }

    public Long getReturnedPartsInTransit() 
    {
        return returnedPartsInTransit;
    }
    public void setReadyToGoOnTheShelf(Long readyToGoOnTheShelf) 
    {
        this.readyToGoOnTheShelf = readyToGoOnTheShelf;
    }

    public Long getReadyToGoOnTheShelf() 
    {
        return readyToGoOnTheShelf;
    }
    public void setAvailableQuantity(Long availableQuantity) 
    {
        this.availableQuantity = availableQuantity;
    }

    public Long getAvailableQuantity() 
    {
        return availableQuantity;
    }
    public void setSaleableQuantity(Long saleableQuantity) 
    {
        this.saleableQuantity = saleableQuantity;
    }

    public Long getSaleableQuantity() 
    {
        return saleableQuantity;
    }
    public void setToBeDelivered(Long toBeDelivered) 
    {
        this.toBeDelivered = toBeDelivered;
    }

    public Long getToBeDelivered() 
    {
        return toBeDelivered;
    }
    public void setOperatorId(Long operatorId) 
    {
        this.operatorId = operatorId;
    }

    public Long getOperatorId() 
    {
        return operatorId;
    }
    public Products getProducts()
    {
        return products;
    }

    public void setProducts(Products products)
    {
        this.products = products;
    }
    public Warehouse getWarehouse()
    {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse)
    {
        this.warehouse = warehouse;
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
            .append("productsId", getProductsId())
            .append("warehouseId", getWarehouseId())
            .append("procurementInTransit", getProcurementInTransit())
            .append("returnedPartsInTransit", getReturnedPartsInTransit())
            .append("readyToGoOnTheShelf", getReadyToGoOnTheShelf())
            .append("availableQuantity", getAvailableQuantity())
            .append("saleableQuantity", getSaleableQuantity())
            .append("toBeDelivered", getToBeDelivered())
            .append("operatorId", getOperatorId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("products", getProducts())
            .append("warehouse", getWarehouse())
            .toString();
    }
}
