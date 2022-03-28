package com.ruoyi.unit.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.unit.mapper.UnitMapper;
import com.ruoyi.unit.domain.Unit;
import com.ruoyi.unit.service.IUnitService;

/**
 * 单位管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-18
 */
@Service
public class UnitServiceImpl implements IUnitService 
{
    @Autowired
    private UnitMapper unitMapper;

    /**
     * 查询单位管理
     * 
     * @param id 单位管理主键
     * @return 单位管理
     */
    @Override
    public Unit selectUnitById(Long id)
    {
        return unitMapper.selectUnitById(id);
    }

    /**
     * 查询单位管理列表
     * 
     * @param unit 单位管理
     * @return 单位管理
     */
    @Override
    public List<Unit> selectUnitList(Unit unit)
    {
        return unitMapper.selectUnitList(unit);
    }

    /**
     * 新增单位管理
     * 
     * @param unit 单位管理
     * @return 结果
     */
    @Override
    public int insertUnit(Unit unit)
    {
        unit.setCreateTime(DateUtils.getNowDate());
        unit.setOperatorId(SecurityUtils.getUserId());
        return unitMapper.insertUnit(unit);
    }

    /**
     * 修改单位管理
     * 
     * @param unit 单位管理
     * @return 结果
     */
    @Override
    public int updateUnit(Unit unit)
    {
        unit.setUpdateTime(DateUtils.getNowDate());
        unit.setOperatorId(SecurityUtils.getUserId());
        return unitMapper.updateUnit(unit);
    }

    /**
     * 批量删除单位管理
     * 
     * @param ids 需要删除的单位管理主键
     * @return 结果
     */
    @Override
    public int deleteUnitByIds(Long[] ids)
    {
        return unitMapper.deleteUnitByIds(ids);
    }

    /**
     * 删除单位管理信息
     * 
     * @param id 单位管理主键
     * @return 结果
     */
    @Override
    public int deleteUnitById(Long id)
    {
        return unitMapper.deleteUnitById(id);
    }
}
