package com.ruoyi.products.domain;

import java.math.BigDecimal;

import com.ruoyi.brand.domain.Brand;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.entity.SysDept;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品资料管理对象 products
 * 
 * @author ruoyi
 * @date 2022-02-21
 */
public class Products extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品ID */
    private Long id;

    /** SKU编码 */
    @Excel(name = "SKU编码*")
    private String sku;

    /** 产品条码 */
    @Excel(name = "产品条码")
    private String ean;

    /** 产品名称 */
    @Excel(name = "产品名称*")
    private String productName;

    /** 产品名称英文 */
    @Excel(name = "产品英文名称")
    private String productNameEn;

    /** 单只毛重 */
    @Excel(name = "单只毛重")
    private BigDecimal singleGrossWeight;

    /** 单只净重 */
    @Excel(name = "单只净重")
    private BigDecimal singleNetWeight;

    /** 单只长 */
    @Excel(name = "单只长")
    private BigDecimal singleLong;

    /** 单只宽 */
    @Excel(name = "单只宽")
    private BigDecimal singleWidth;

    /** 单只高 */
    @Excel(name = "单只高")
    private BigDecimal singleHeight;

    /** 外箱毛重 */
    @Excel(name = "外箱毛重")
    private BigDecimal grossWeight;

    /** 外箱净重 */
    @Excel(name = "外箱净重")
    private BigDecimal netWeight;

    /** 外箱长 */
    @Excel(name = "外箱长")
    private BigDecimal length;

    /** 外箱宽 */
    @Excel(name = "外箱宽")
    private BigDecimal width;

    /** 外箱高 */
    @Excel(name = "外箱高")
    private BigDecimal height;

    /** 采购价 */
    @Excel(name = "采购价*")
    private String purchasePrice;

    /** 采购币种ID */
    @Excel(name = "采购币种ID*")
    private Long currencyId;

    /** 供应商ID */
    @Excel(name = "供应商ID*")
    private Long supplierId;

    /** 产品销售状态 */
    @Excel(name = "产品销售状态")
    private Long productStatus;

    /** 默认采购员 */
    @Excel(name = "默认采购员")
    private Long userId;

    /** 产品单位ID */
    @Excel(name = "产品单位ID")
    private Long unitId;

    /** 品类ID */
    @Excel(name = "品类ID")
    private Long varietiesId;

    /** 品牌ID */
    @Excel(name = "品牌ID")
    private Long brandId;

    /** 箱数量 */
    @Excel(name = "箱数量")
    private Long numberOfBoxes;

    /** 墨量 */
    @Excel(name = "墨量")
    private Long inkQuantity;

    /** 页数 */
    @Excel(name = "页数")
    private Long pageNumber;

    /** 产品描述 */
    @Excel(name = "产品描述")
    private String description;

    /** 工厂编码 */
    @Excel(name = "工厂编码")
    private String factoryCode;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long departmentId;

    /** 是否可用，0可用，1不可用 */
    @Excel(name = "是否可用*（0可用，1不可用）")
    private Long usable;

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
    public void setSku(String sku) 
    {
        this.sku = sku;
    }

    public String getSku() 
    {
        return sku;
    }
    public void setEan(String ean) 
    {
        this.ean = ean;
    }

    public String getEan() 
    {
        return ean;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setProductNameEn(String productNameEn) 
    {
        this.productNameEn = productNameEn;
    }

    public String getProductNameEn() 
    {
        return productNameEn;
    }
    public void setSingleGrossWeight(BigDecimal singleGrossWeight) 
    {
        this.singleGrossWeight = singleGrossWeight;
    }

    public BigDecimal getSingleGrossWeight() 
    {
        return singleGrossWeight;
    }
    public void setSingleNetWeight(BigDecimal singleNetWeight) 
    {
        this.singleNetWeight = singleNetWeight;
    }

    public BigDecimal getSingleNetWeight() 
    {
        return singleNetWeight;
    }
    public void setSingleLong(BigDecimal singleLong) 
    {
        this.singleLong = singleLong;
    }

    public BigDecimal getSingleLong() 
    {
        return singleLong;
    }
    public void setSingleWidth(BigDecimal singleWidth) 
    {
        this.singleWidth = singleWidth;
    }

    public BigDecimal getSingleWidth() 
    {
        return singleWidth;
    }
    public void setSingleHeight(BigDecimal singleHeight) 
    {
        this.singleHeight = singleHeight;
    }

    public BigDecimal getSingleHeight() 
    {
        return singleHeight;
    }
    public void setGrossWeight(BigDecimal grossWeight) 
    {
        this.grossWeight = grossWeight;
    }

    public BigDecimal getGrossWeight() 
    {
        return grossWeight;
    }
    public void setNetWeight(BigDecimal netWeight) 
    {
        this.netWeight = netWeight;
    }

    public BigDecimal getNetWeight() 
    {
        return netWeight;
    }
    public void setLength(BigDecimal length) 
    {
        this.length = length;
    }

    public BigDecimal getLength() 
    {
        return length;
    }
    public void setWidth(BigDecimal width) 
    {
        this.width = width;
    }

    public BigDecimal getWidth() 
    {
        return width;
    }
    public void setHeight(BigDecimal height) 
    {
        this.height = height;
    }

    public BigDecimal getHeight() 
    {
        return height;
    }
    public void setPurchasePrice(String purchasePrice)
    {
        this.purchasePrice = purchasePrice;
    }

    public String getPurchasePrice()
    {
        return purchasePrice;
    }
    public void setCurrencyId(Long currencyId) 
    {
        this.currencyId = currencyId;
    }

    public Long getCurrencyId() 
    {
        return currencyId;
    }
    public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }
    public void setProductStatus(Long productStatus) 
    {
        this.productStatus = productStatus;
    }

    public Long getProductStatus() 
    {
        return productStatus;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUnitId(Long unitId) 
    {
        this.unitId = unitId;
    }

    public Long getUnitId() 
    {
        return unitId;
    }
    public void setVarietiesId(Long varietiesId) 
    {
        this.varietiesId = varietiesId;
    }

    public Long getVarietiesId() 
    {
        return varietiesId;
    }
    public void setBrandId(Long brandId) 
    {
        this.brandId = brandId;
    }

    public Long getBrandId() 
    {
        return brandId;
    }
    public void setNumberOfBoxes(Long numberOfBoxes) 
    {
        this.numberOfBoxes = numberOfBoxes;
    }

    public Long getNumberOfBoxes() 
    {
        return numberOfBoxes;
    }
    public void setInkQuantity(Long inkQuantity) 
    {
        this.inkQuantity = inkQuantity;
    }

    public Long getInkQuantity() 
    {
        return inkQuantity;
    }
    public void setPageNumber(Long pageNumber) 
    {
        this.pageNumber = pageNumber;
    }

    public Long getPageNumber() 
    {
        return pageNumber;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setFactoryCode(String factoryCode) 
    {
        this.factoryCode = factoryCode;
    }

    public String getFactoryCode() 
    {
        return factoryCode;
    }
    public void setDepartmentId(Long departmentId) 
    {
        this.departmentId = departmentId;
    }

    public Long getDepartmentId() 
    {
        return departmentId;
    }
    public void setUsable(Long usable) 
    {
        this.usable = usable;
    }

    public Long getUsable() 
    {
        return usable;
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
            .append("sku", getSku())
            .append("ean", getEan())
            .append("productName", getProductName())
            .append("productNameEn", getProductNameEn())
            .append("singleGrossWeight", getSingleGrossWeight())
            .append("singleNetWeight", getSingleNetWeight())
            .append("singleLong", getSingleLong())
            .append("singleWidth", getSingleWidth())
            .append("singleHeight", getSingleHeight())
            .append("grossWeight", getGrossWeight())
            .append("netWeight", getNetWeight())
            .append("length", getLength())
            .append("width", getWidth())
            .append("height", getHeight())
            .append("purchasePrice", getPurchasePrice())
            .append("currencyId", getCurrencyId())
            .append("supplierId", getSupplierId())
            .append("productStatus", getProductStatus())
            .append("userId", getUserId())
            .append("unitId", getUnitId())
            .append("varietiesId", getVarietiesId())
            .append("brandId", getBrandId())
            .append("numberOfBoxes", getNumberOfBoxes())
            .append("inkQuantity", getInkQuantity())
            .append("pageNumber", getPageNumber())
            .append("description", getDescription())
            .append("factoryCode", getFactoryCode())
            .append("departmentId", getDepartmentId())
            .append("usable", getUsable())
            .append("operatorId", getOperatorId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
