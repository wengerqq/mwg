package com.ruoyi.sales_status.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.sales_status.mapper.ProductSalesStatusMapper;
import com.ruoyi.sales_status.domain.ProductSalesStatus;
import com.ruoyi.sales_status.service.IProductSalesStatusService;

/**
 * 产品销售状态管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-17
 */
@Service
public class ProductSalesStatusServiceImpl implements IProductSalesStatusService 
{
    @Autowired
    private ProductSalesStatusMapper productSalesStatusMapper;

    /**
     * 查询产品销售状态管理
     * 
     * @param id 产品销售状态管理主键
     * @return 产品销售状态管理
     */
    @Override
    public ProductSalesStatus selectProductSalesStatusById(Long id)
    {
        return productSalesStatusMapper.selectProductSalesStatusById(id);
    }

    /**
     * 查询产品销售状态管理列表
     * 
     * @param productSalesStatus 产品销售状态管理
     * @return 产品销售状态管理
     */
    @Override
    public List<ProductSalesStatus> selectProductSalesStatusList(ProductSalesStatus productSalesStatus)
    {
        return productSalesStatusMapper.selectProductSalesStatusList(productSalesStatus);
    }

    /**
     * 新增产品销售状态管理
     * 
     * @param productSalesStatus 产品销售状态管理
     * @return 结果
     */
    @Override
    public int insertProductSalesStatus(ProductSalesStatus productSalesStatus)
    {
        productSalesStatus.setCreateTime(DateUtils.getNowDate());
        productSalesStatus.setOperatorId(SecurityUtils.getUserId());
        return productSalesStatusMapper.insertProductSalesStatus(productSalesStatus);
    }

    /**
     * 修改产品销售状态管理
     * 
     * @param productSalesStatus 产品销售状态管理
     * @return 结果
     */
    @Override
    public int updateProductSalesStatus(ProductSalesStatus productSalesStatus)
    {
        productSalesStatus.setUpdateTime(DateUtils.getNowDate());
        productSalesStatus.setOperatorId(SecurityUtils.getUserId());
        return productSalesStatusMapper.updateProductSalesStatus(productSalesStatus);
    }

    /**
     * 批量删除产品销售状态管理
     * 
     * @param ids 需要删除的产品销售状态管理主键
     * @return 结果
     */
    @Override
    public int deleteProductSalesStatusByIds(Long[] ids)
    {
        return productSalesStatusMapper.deleteProductSalesStatusByIds(ids);
    }

    /**
     * 删除产品销售状态管理信息
     * 
     * @param id 产品销售状态管理主键
     * @return 结果
     */
    @Override
    public int deleteProductSalesStatusById(Long id)
    {
        return productSalesStatusMapper.deleteProductSalesStatusById(id);
    }
}
