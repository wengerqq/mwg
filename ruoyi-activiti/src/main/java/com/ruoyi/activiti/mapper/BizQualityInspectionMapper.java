package com.ruoyi.activiti.mapper;

import java.util.List;
import com.ruoyi.activiti.domain.BizQualityInspection;

/**
 * 采购质检Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-20
 */
public interface BizQualityInspectionMapper 
{
    /**
     * 查询采购质检
     * 
     * @param id 采购质检主键
     * @return 采购质检
     */
    public BizQualityInspection selectBizQualityInspectionById(Long id);

    /**
     * 查询采购质检列表
     * 
     * @param bizQualityInspection 采购质检
     * @return 采购质检集合
     */
    public List<BizQualityInspection> selectBizQualityInspectionList(BizQualityInspection bizQualityInspection);

    /**
     * 新增采购质检
     * 
     * @param bizQualityInspection 采购质检
     * @return 结果
     */
    public int insertBizQualityInspection(BizQualityInspection bizQualityInspection);

    /**
     * 修改采购质检
     * 
     * @param bizQualityInspection 采购质检
     * @return 结果
     */
    public int updateBizQualityInspection(BizQualityInspection bizQualityInspection);

    /**
     * 删除采购质检
     * 
     * @param id 采购质检主键
     * @return 结果
     */
    public int deleteBizQualityInspectionById(Long id);

    /**
     * 批量删除采购质检
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizQualityInspectionByIds(Long[] ids);
}
