package com.ruoyi.products.service;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.products.domain.Products;

/**
 * 产品资料管理Service接口
 * 
 * @author ruoyi
 * @date 2022-02-21
 */
public interface IProductsService 
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
     * 批量删除产品资料管理
     * 
     * @param ids 需要删除的产品资料管理主键集合
     * @return 结果
     */
    public int deleteProductsByIds(Long[] ids);

    /**
     * 删除产品资料管理信息
     * 
     * @param id 产品资料管理主键
     * @return 结果
     */
    public int deleteProductsById(Long id);

    /**
     * 修改产品是否可用
     * @param products 产品资料管理
     * @return 结果
     */
    public int updateProductUsable(Products products);

    /**
     * 导入产品数据
     *
     * @param productsList 用户产品列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operatorId 操作用户
     * @return 结果
     */
    public String importProducts(List<Products> productsList, Boolean isUpdateSupport, Long operatorId);
}
