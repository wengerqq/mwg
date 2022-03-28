package com.ruoyi.unit.service;

import java.util.List;
import com.ruoyi.unit.domain.Unit;

/**
 * 单位管理Service接口
 * 
 * @author ruoyi
 * @date 2022-02-18
 */
public interface IUnitService 
{
    /**
     * 查询单位管理
     * 
     * @param id 单位管理主键
     * @return 单位管理
     */
    public Unit selectUnitById(Long id);

    /**
     * 查询单位管理列表
     * 
     * @param unit 单位管理
     * @return 单位管理集合
     */
    public List<Unit> selectUnitList(Unit unit);

    /**
     * 新增单位管理
     * 
     * @param unit 单位管理
     * @return 结果
     */
    public int insertUnit(Unit unit);

    /**
     * 修改单位管理
     * 
     * @param unit 单位管理
     * @return 结果
     */
    public int updateUnit(Unit unit);

    /**
     * 批量删除单位管理
     * 
     * @param ids 需要删除的单位管理主键集合
     * @return 结果
     */
    public int deleteUnitByIds(Long[] ids);

    /**
     * 删除单位管理信息
     * 
     * @param id 单位管理主键
     * @return 结果
     */
    public int deleteUnitById(Long id);
}
