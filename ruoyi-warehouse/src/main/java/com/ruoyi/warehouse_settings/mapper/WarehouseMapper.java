package com.ruoyi.warehouse_settings.mapper;

import java.util.List;
import com.ruoyi.warehouse_settings.domain.Warehouse;

/**
 * 仓库设置Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-08
 */
public interface WarehouseMapper 
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
     * 删除仓库设置
     * 
     * @param id 仓库设置主键
     * @return 结果
     */
    public int deleteWarehouseById(Long id);

    /**
     * 批量删除仓库设置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWarehouseByIds(Long[] ids);

    /**
     * 查询部门仓库设置列表
     *
     * @param warehouse 仓库设置
     * @return 仓库设置集合
     */
    public List<Warehouse> selectDeptWarehouseList(Warehouse warehouse);
}
