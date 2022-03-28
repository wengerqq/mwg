package com.ruoyi.warehouse_settings.service;

import java.util.List;
import com.ruoyi.warehouse_settings.domain.Warehouse;

/**
 * 仓库设置Service接口
 * 
 * @author ruoyi
 * @date 2022-03-08
 */
public interface IWarehouseService 
{
    /**
     * 查询仓库设置
     * 
     * @param id 仓库设置主键
     * @return 仓库设置
     */
    public Warehouse selectWarehouseById(Long id);

    /**
     * 查询仓库设置列表
     * 
     * @param warehouse 仓库设置
     * @return 仓库设置集合
     */
    public List<Warehouse> selectWarehouseList(Warehouse warehouse);

    /**
     * 新增仓库设置
     * 
     * @param warehouse 仓库设置
     * @return 结果
     */
    public int insertWarehouse(Warehouse warehouse);

    /**
     * 修改仓库设置
     * 
     * @param warehouse 仓库设置
     * @return 结果
     */
    public int updateWarehouse(Warehouse warehouse);

    /**
     * 批量删除仓库设置
     * 
     * @param ids 需要删除的仓库设置主键集合
     * @return 结果
     */
    public int deleteWarehouseByIds(Long[] ids);

    /**
     * 删除仓库设置信息
     * 
     * @param id 仓库设置主键
     * @return 结果
     */
    public int deleteWarehouseById(Long id);

    /**
     * 导入仓库数据
     * @param warehouseList 仓库数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operatorId 操作用户
     * @return 结果
     */
    public String importWarehouse(List<Warehouse> warehouseList, Boolean isUpdateSupport, Long operatorId);
}
