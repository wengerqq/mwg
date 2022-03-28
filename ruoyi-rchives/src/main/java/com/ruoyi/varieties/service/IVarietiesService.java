package com.ruoyi.varieties.service;

import java.util.List;
import com.ruoyi.varieties.domain.Varieties;

/**
 * 品类管理Service接口
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
public interface IVarietiesService 
{
    /**
     * 查询品类管理
     * 
     * @param id 品类管理主键
     * @return 品类管理
     */
    public Varieties selectVarietiesById(Long id);

    /**
     * 查询品类管理列表
     * 
     * @param varieties 品类管理
     * @return 品类管理集合
     */
    public List<Varieties> selectVarietiesList(Varieties varieties);

    /**
     * 新增品类管理
     * 
     * @param varieties 品类管理
     * @return 结果
     */
    public int insertVarieties(Varieties varieties);

    /**
     * 修改品类管理
     * 
     * @param varieties 品类管理
     * @return 结果
     */
    public int updateVarieties(Varieties varieties);

    /**
     * 批量删除品类管理
     * 
     * @param ids 需要删除的品类管理主键集合
     * @return 结果
     */
    public int deleteVarietiesByIds(Long[] ids);

    /**
     * 删除品类管理信息
     * 
     * @param id 品类管理主键
     * @return 结果
     */
    public int deleteVarietiesById(Long id);
}
