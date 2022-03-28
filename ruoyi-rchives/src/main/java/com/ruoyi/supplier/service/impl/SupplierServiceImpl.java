package com.ruoyi.supplier.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.supplier.mapper.SupplierMapper;
import com.ruoyi.supplier.domain.Supplier;
import com.ruoyi.supplier.service.ISupplierService;

/**
 * 供应商管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
@Service
public class SupplierServiceImpl implements ISupplierService 
{
    @Autowired
    private SupplierMapper supplierMapper;

    /**
     * 查询供应商管理
     * 
     * @param id 供应商管理主键
     * @return 供应商管理
     */
    @Override
    public Supplier selectSupplierById(Long id)
    {
        return supplierMapper.selectSupplierById(id);
    }

    /**
     * 查询供应商管理列表
     * 
     * @param supplier 供应商管理
     * @return 供应商管理
     */
    @Override
    public List<Supplier> selectSupplierList(Supplier supplier)
    {
        return supplierMapper.selectSupplierList(supplier);
    }

    /**
     * 新增供应商管理
     * 
     * @param supplier 供应商管理
     * @return 结果
     */
    @Override
    public int insertSupplier(Supplier supplier)
    {
        supplier.setCreateTime(DateUtils.getNowDate());
        supplier.setOperatorId(SecurityUtils.getUserId());
        return supplierMapper.insertSupplier(supplier);
    }

    /**
     * 修改供应商管理
     * 
     * @param supplier 供应商管理
     * @return 结果
     */
    @Override
    public int updateSupplier(Supplier supplier)
    {
        supplier.setUpdateTime(DateUtils.getNowDate());
        supplier.setOperatorId(SecurityUtils.getUserId());
        return supplierMapper.updateSupplier(supplier);
    }

    /**
     * 批量删除供应商管理
     * 
     * @param ids 需要删除的供应商管理主键
     * @return 结果
     */
    @Override
    public int deleteSupplierByIds(Long[] ids)
    {
        return supplierMapper.deleteSupplierByIds(ids);
    }

    /**
     * 删除供应商管理信息
     * 
     * @param id 供应商管理主键
     * @return 结果
     */
    @Override
    public int deleteSupplierById(Long id)
    {
        return supplierMapper.deleteSupplierById(id);
    }
}
