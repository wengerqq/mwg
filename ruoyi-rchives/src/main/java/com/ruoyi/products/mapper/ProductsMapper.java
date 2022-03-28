package com.ruoyi.products.mapper;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.products.domain.Products;

/**
 * 产品资料管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-21
 */
public interface ProductsMapper 
{
    /**
     * 查询产品资料管理
     * 
     * @param id 产品资料管理主键
     * @return 产品资料管理
     */
    public Products selectProductsById(Long id);

    /**
     * 查询产品资料管理列表
     * 
     * @param products 产品资料管理
     * @return 产品资料管理集合
     */
    public List<Products> selectProductsList(Products products);

    /**
     * 新增产品资料管理
     * 
     * @param products 产品资料管理
     * @return 结果
     */
    public int insertProducts(Products products);

    /**
     * 修改产品资料管理
     * 
     * @param products 产品资料管理
     * @return 结果
     */
    public int updateProducts(Products products);

    /**
     * 删除产品资料管理
     * 
     * @param id 产品资料管理主键
     * @return 结果
     */
    public int deleteProductsById(Long id);

    /**
     * 批量删除产品资料管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductsByIds(Long[] ids);

    /**
     * 通过SKU查询产品
     *
     * @param sku 用户名
     * @return 用户对象信息
     */
    public Products selectProductsBySku(String sku);

    /**
     * 查询部门产品资料管理列表
     *
     * @param products 产品资料管理
     * @return 产品资料管理集合
     */
    public List<Products> selectDeptProductsList(Products products);
}
