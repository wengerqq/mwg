package com.ruoyi.varieties.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.varieties.mapper.VarietiesMapper;
import com.ruoyi.varieties.domain.Varieties;
import com.ruoyi.varieties.service.IVarietiesService;

/**
 * 品类管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@Service
public class VarietiesServiceImpl implements IVarietiesService 
{
    @Autowired
    private VarietiesMapper varietiesMapper;

    /**
     * 查询品类管理
     * 
     * @param id 品类管理主键
     * @return 品类管理
     */
    @Override
    public Varieties selectVarietiesById(Long id)
    {
        return varietiesMapper.selectVarietiesById(id);
    }

    /**
     * 查询品类管理列表
     * 
     * @param varieties 品类管理
     * @return 品类管理
     */
    @Override
    public List<Varieties> selectVarietiesList(Varieties varieties)
    {
        return varietiesMapper.selectVarietiesList(varieties);
    }

    /**
     * 新增品类管理
     * 
     * @param varieties 品类管理
     * @return 结果
     */
    @Override
    public int insertVarieties(Varieties varieties)
    {
        varieties.setCreateTime(DateUtils.getNowDate());
        varieties.setOperatorId(SecurityUtils.getUserId());
        return varietiesMapper.insertVarieties(varieties);
    }

    /**
     * 修改品类管理
     * 
     * @param varieties 品类管理
     * @return 结果
     */
    @Override
    public int updateVarieties(Varieties varieties)
    {
        varieties.setUpdateTime(DateUtils.getNowDate());
        varieties.setOperatorId(SecurityUtils.getUserId());
        return varietiesMapper.updateVarieties(varieties);
    }

    /**
     * 批量删除品类管理
     * 
     * @param ids 需要删除的品类管理主键
     * @return 结果
     */
    @Override
    public int deleteVarietiesByIds(Long[] ids)
    {
        return varietiesMapper.deleteVarietiesByIds(ids);
    }

    /**
     * 删除品类管理信息
     * 
     * @param id 品类管理主键
     * @return 结果
     */
    @Override
    public int deleteVarietiesById(Long id)
    {
        return varietiesMapper.deleteVarietiesById(id);
    }
}
