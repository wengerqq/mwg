package com.ruoyi.products.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.products.mapper.ProductsMapper;
import com.ruoyi.products.domain.Products;
import com.ruoyi.products.service.IProductsService;

import javax.validation.Validator;

/**
 * 产品资料管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-21
 */
@Service
public class ProductsServiceImpl implements IProductsService
{
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    protected Validator validator;

    /**
     * 查询产品资料管理
     *
     * @param id 产品资料管理主键
     * @return 产品资料管理
     */
    @Override
    public Products selectProductsById(Long id)
    {
        return productsMapper.selectProductsById(id);
    }

    /**
     * 查询产品资料管理列表
     *
     * @param products 产品资料管理
     * @return 产品资料管理
     */
    @Override
    public List<Products> selectProductsList(Products products)
    {
        // 获取当前的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (StringUtils.isNotNull(loginUser))
        {
            SysUser currentUser = loginUser.getUser();
            // 如果是超级管理员，则不过滤数据
            if (StringUtils.isNotNull(currentUser) && currentUser.isAdmin())
            {
                return productsMapper.selectProductsList(products);
            }
        }
        if(products.getDepartmentId()!=null&&products.getIsQuery()){
            return productsMapper.selectProductsList(products);
        }
        products.setDepartmentId(loginUser.getDeptId());
        return productsMapper.selectDeptProductsList(products);
    }

    /**
     * 新增产品资料管理
     * 
     * @param products 产品资料管理
     * @return 结果
     */
    @Override
    public int insertProducts(Products products)
    {
        products.setCreateTime(DateUtils.getNowDate());
        products.setOperatorId(SecurityUtils.getUserId());
        return productsMapper.insertProducts(products);
    }

    /**
     * 修改产品资料管理
     * 
     * @param products 产品资料管理
     * @return 结果
     */
    @Override
    public int updateProducts(Products products)
    {
        products.setUpdateTime(DateUtils.getNowDate());
        products.setOperatorId(SecurityUtils.getUserId());
        return productsMapper.updateProducts(products);
    }

    /**
     * 批量删除产品资料管理
     * 
     * @param ids 需要删除的产品资料管理主键
     * @return 结果
     */
    @Override
    public int deleteProductsByIds(Long[] ids)
    {
        return productsMapper.deleteProductsByIds(ids);
    }

    /**
     * 删除产品资料管理信息
     * 
     * @param id 产品资料管理主键
     * @return 结果
     */
    @Override
    public int deleteProductsById(Long id)
    {
        return productsMapper.deleteProductsById(id);
    }

    /**
     * 修改产品是否可用
     * @param products 产品资料管理
     * @return
     */
    @Override
    public int updateProductUsable(Products products)
    {
        products.setOperatorId(SecurityUtils.getUserId());
        return productsMapper.updateProducts(products);
    }

    /**
     * 导入产品数据
     *
     * @param productsList 产品数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operatorId 操作用户
     * @return 结果
     */
    @Override
    public String importProducts(List<Products> productsList, Boolean isUpdateSupport, Long operatorId)
    {
        if (StringUtils.isNull(productsList) || productsList.size() == 0)
        {
            throw new ServiceException("导入产品数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Products product : productsList)
        {
            try
            {
                // 验证是否存在这个产品
                Products p = productsMapper.selectProductsBySku(product.getSku());
                if (StringUtils.isNull(p))
                {
                    BeanValidators.validateWithException(validator, product);
                    this.insertProducts(product);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、产品 " + product.getSku() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    BeanValidators.validateWithException(validator, product);
                    this.updateProducts(product);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、产品 " + product.getSku() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、产品 " + product.getSku() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、产品 " + product.getSku() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
