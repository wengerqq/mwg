package com.ruoyi.unit.mapper;

import java.util.List;
import com.ruoyi.unit.domain.Unit;

/**
 * 单位管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-18
 */
public interface UnitMapper 
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
     * 删除单位管理
     * 
     * @param id 单位管理主键
     * @return 结果
     */
    public int deleteUnitById(Long id);

    /**
     * 批量删除单位管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUnitByIds(Long[] ids);
}
