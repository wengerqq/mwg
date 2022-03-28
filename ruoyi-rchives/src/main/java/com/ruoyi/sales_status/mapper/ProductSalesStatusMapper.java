package com.ruoyi.sales_status.mapper;

import java.util.List;
import com.ruoyi.sales_status.domain.ProductSalesStatus;

/**
 * 产品销售状态管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-17
 */
public interface ProductSalesStatusMapper 
{
    /**
     * 查询产品销售状态管理
     * 
     * @param id 产品销售状态管理主键
     * @return 产品销售状态管理
     */
    public ProductSalesStatus selectProductSalesStatusById(Long id);

    /**
     * 查询产品销售状态管理列表
     * 
     * @param productSalesStatus 产品销售状态管理
     * @return 产品销售状态管理集合
     */
    public List<ProductSalesStatus> selectProductSalesStatusList(ProductSalesStatus productSalesStatus);

    /**
     * 新增产品销售状态管理
     * 
     * @param productSalesStatus 产品销售状态管理
     * @return 结果
     */
    public int insertProductSalesStatus(ProductSalesStatus productSalesStatus);

    /**
     * 修改产品销售状态管理
     * 
     * @param productSalesStatus 产品销售状态管理
     * @return 结果
     */
    public int updateProductSalesStatus(ProductSalesStatus productSalesStatus);

    /**
     * 删除产品销售状态管理
     * 
     * @param id 产品销售状态管理主键
     * @return 结果
     */
    public int deleteProductSalesStatusById(Long id);

    /**
     * 批量删除产品销售状态管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductSalesStatusByIds(Long[] ids);
}
