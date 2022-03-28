package com.ruoyi.stock_check.mapper;

import java.util.List;
import com.ruoyi.stock_check.domain.StockCheck;

/**
 * 库存查询Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-12
 */
public interface StockCheckMapper 
{
    /**
     * 查询库存查询
     * 
     * @param id 库存查询主键
     * @return 库存查询
     */
    public StockCheck selectStockCheckById(Long id);

    /**
     * 查询库存查询列表
     * 
     * @param stockCheck 库存查询
     * @return 库存查询集合
     */
    public List<StockCheck> selectStockCheckList(StockCheck stockCheck);

    /**
     * 新增库存查询
     * 
     * @param stockCheck 库存查询
     * @return 结果
     */
    public int insertStockCheck(StockCheck stockCheck);

    /**
     * 修改库存查询
     * 
     * @param stockCheck 库存查询
     * @return 结果
     */
    public int updateStockCheck(StockCheck stockCheck);

    /**
     * 删除库存查询
     * 
     * @param id 库存查询主键
     * @return 结果
     */
    public int deleteStockCheckById(Long id);

    /**
     * 批量删除库存查询
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStockCheckByIds(Long[] ids);

    /**
     * 查询库存查询列表（部门）
     *
     * @param stockCheck 库存查询
     * @return 库存查询集合
     */
    public List<StockCheck> selectDeptStockCheckList(StockCheck stockCheck);
}
