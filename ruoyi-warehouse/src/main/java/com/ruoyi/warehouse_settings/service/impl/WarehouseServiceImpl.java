package com.ruoyi.warehouse_settings.service.impl;

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
import com.ruoyi.warehouse_settings.mapper.WarehouseMapper;
import com.ruoyi.warehouse_settings.domain.Warehouse;
import com.ruoyi.warehouse_settings.service.IWarehouseService;

import javax.validation.Validator;

/**
 * 仓库设置Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-08
 */
@Service
public class WarehouseServiceImpl implements IWarehouseService 
{
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    protected Validator validator;

    /**
     * 查询仓库设置
     * 
     * @param id 仓库设置主键
     * @return 仓库设置
     */
    @Override
    public Warehouse selectWarehouseById(Long id)
    {
        return warehouseMapper.selectWarehouseById(id);
    }

    /**
     * 查询仓库设置列表
     * 
     * @param warehouse 仓库设置
     * @return 仓库设置
     */
    @Override
    public List<Warehouse> selectWarehouseList(Warehouse warehouse)
    {
        // 获取当前的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (StringUtils.isNotNull(loginUser))
        {
            SysUser currentUser = loginUser.getUser();
            // 如果是超级管理员，则不过滤数据
            if (StringUtils.isNotNull(currentUser) && currentUser.isAdmin())
            {
                return warehouseMapper.selectWarehouseList(warehouse);
            }
        }
        if(warehouse.getDeptId()!=null&&warehouse.getIsQuery()){
            return warehouseMapper.selectWarehouseList(warehouse);
        }
        warehouse.setDeptId(loginUser.getDeptId());
        return warehouseMapper.selectDeptWarehouseList(warehouse);
    }

    /**
     * 新增仓库设置
     * 
     * @param warehouse 仓库设置
     * @return 结果
     */
    @Override
    public int insertWarehouse(Warehouse warehouse)
    {
        warehouse.setCreateTime(DateUtils.getNowDate());
        warehouse.setOperatorId(SecurityUtils.getUserId());
        return warehouseMapper.insertWarehouse(warehouse);
    }

    /**
     * 修改仓库设置
     * 
     * @param warehouse 仓库设置
     * @return 结果
     */
    @Override
    public int updateWarehouse(Warehouse warehouse)
    {
        warehouse.setUpdateTime(DateUtils.getNowDate());
        warehouse.setOperatorId(SecurityUtils.getUserId());
        return warehouseMapper.updateWarehouse(warehouse);
    }

    /**
     * 批量删除仓库设置
     * 
     * @param ids 需要删除的仓库设置主键
     * @return 结果
     */
    @Override
    public int deleteWarehouseByIds(Long[] ids)
    {
        return warehouseMapper.deleteWarehouseByIds(ids);
    }

    /**
     * 删除仓库设置信息
     * 
     * @param id 仓库设置主键
     * @return 结果
     */
    @Override
    public int deleteWarehouseById(Long id)
    {
        return warehouseMapper.deleteWarehouseById(id);
    }

    /**
     * 导入仓库数据
     *
     * @param warehouseList 仓库数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operatorId 操作用户
     * @return 结果
     */
    @Override
    public String importWarehouse(List<Warehouse> warehouseList, Boolean isUpdateSupport, Long operatorId)
    {
        if (StringUtils.isNull(warehouseList) || warehouseList.size() == 0)
        {
            throw new ServiceException("导入仓库数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Warehouse warehouse : warehouseList)
        {
            try
            {
                // 验证是否存在这个仓库
                Warehouse w = warehouseMapper.selectWarehouseById(warehouse.getId());
                if (StringUtils.isNull(w))
                {
                    BeanValidators.validateWithException(validator, warehouse);
                    warehouse.setOperatorId(operatorId);
                    this.insertWarehouse(warehouse);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、仓库 " + warehouse.getId() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    BeanValidators.validateWithException(validator, warehouse);
                    warehouse.setOperatorId(operatorId);
                    this.updateWarehouse(warehouse);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、仓库 " + warehouse.getId() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、仓库 " + warehouse.getId() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、仓库 " + warehouse.getId() + " 导入失败：";
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
