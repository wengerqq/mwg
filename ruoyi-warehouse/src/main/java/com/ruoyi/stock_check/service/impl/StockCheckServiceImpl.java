package com.ruoyi.stock_check.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.products.domain.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import com.ruoyi.stock_check.mapper.StockCheckMapper;
import com.ruoyi.stock_check.domain.StockCheck;
import com.ruoyi.stock_check.service.IStockCheckService;

/**
 * 库存查询Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-12
 */
@Service
public class StockCheckServiceImpl implements IStockCheckService 
{
    @Autowired
    private StockCheckMapper stockCheckMapper;

    private Products products = new Products();

    /**
     * 查询库存查询
     * 
     * @param id 库存查询主键
     * @return 库存查询
     */
    @Override
    public StockCheck selectStockCheckById(Long id)
    {
        return stockCheckMapper.selectStockCheckById(id);
    }

    /**
     * 查询库存查询列表
     * 
     * @param stockCheck 库存查询
     * @return 库存查询
     */
    @Override
    public List<StockCheck> selectStockCheckList(StockCheck stockCheck)
    {
        // 获取当前的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (StringUtils.isNotNull(loginUser))
        {
            SysUser currentUser = loginUser.getUser();
            // 如果是超级管理员，则不过滤数据
            if (StringUtils.isNotNull(currentUser) && currentUser.isAdmin())
            {
                return stockCheckMapper.selectStockCheckList(stockCheck);
            }
        }
        if(stockCheck.getParams().get("deptId")!=null&&stockCheck.getIsQuery()){
            return stockCheckMapper.selectStockCheckList(stockCheck);
        }
        stockCheck.getParams().put("deptId",loginUser.getDeptId());
        return stockCheckMapper.selectDeptStockCheckList(stockCheck);
    }

    /**
     * 新增库存查询
     * 
     * @param stockCheck 库存查询
     * @return 结果
     */
    @Override
    public int insertStockCheck(StockCheck stockCheck)
    {
        stockCheck.setCreateTime(DateUtils.getNowDate());
        return stockCheckMapper.insertStockCheck(stockCheck);
    }

    /**
     * 修改库存查询
     * 
     * @param stockCheck 库存查询
     * @return 结果
     */
    @Override
    public int updateStockCheck(StockCheck stockCheck)
    {
        stockCheck.setUpdateTime(DateUtils.getNowDate());
        return stockCheckMapper.updateStockCheck(stockCheck);
    }

    /**
     * 批量删除库存查询
     * 
     * @param ids 需要删除的库存查询主键
     * @return 结果
     */
    @Override
    public int deleteStockCheckByIds(Long[] ids)
    {
        return stockCheckMapper.deleteStockCheckByIds(ids);
    }

    /**
     * 删除库存查询信息
     * 
     * @param id 库存查询主键
     * @return 结果
     */
    @Override
    public int deleteStockCheckById(Long id)
    {
        return stockCheckMapper.deleteStockCheckById(id);
    }
}
