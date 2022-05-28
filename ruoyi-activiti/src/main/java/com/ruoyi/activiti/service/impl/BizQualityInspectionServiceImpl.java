package com.ruoyi.activiti.service.impl;

import java.util.List;

import com.ruoyi.activiti.service.IProcessService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.activiti.mapper.BizQualityInspectionMapper;
import com.ruoyi.activiti.domain.BizQualityInspection;
import com.ruoyi.activiti.service.IBizQualityInspectionService;
import org.springframework.util.CollectionUtils;

/**
 * 采购质检Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-20
 */
@Service
@AllArgsConstructor
//@DataSource(value = DataSourceType.SLAVE)//开启第二数据库
public class BizQualityInspectionServiceImpl implements IBizQualityInspectionService 
{

    private BizQualityInspectionMapper bizQualityInspectionMapper;
    private IProcessService processService;

    /**
     * 查询采购质检
     * 
     * @param id 采购质检主键
     * @return 采购质检
     */
    @Override
    public BizQualityInspection selectBizQualityInspectionById(Long id)
    {

        return bizQualityInspectionMapper.selectBizQualityInspectionById(id);
    }

    /**
     * 查询采购质检列表
     * 
     * @param bizQualityInspection 采购质检
     * @return 采购质检
     */
    @Override
    public List<BizQualityInspection> selectBizQualityInspectionList(BizQualityInspection bizQualityInspection)
    {
        if (!SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUser().getUserId())) {
            bizQualityInspection.setCreateBy(SecurityUtils.getUsername());
        }
        List<BizQualityInspection> list =
                bizQualityInspectionMapper.selectBizQualityInspectionList(bizQualityInspection);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(item -> {
                try {
                    processService.richProcessField(item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return list;
    }

    /**
     * 新增采购质检
     * 
     * @param bizQualityInspection 采购质检
     * @return 结果
     */
    @Override
    public int insertBizQualityInspection(BizQualityInspection bizQualityInspection)
    {
        bizQualityInspection.setCreateBy(SecurityUtils.getUsername());
        bizQualityInspection.setCreateTime(DateUtils.getNowDate());
        return bizQualityInspectionMapper.insertBizQualityInspection(bizQualityInspection);
    }

    /**
     * 修改采购质检
     * 
     * @param bizQualityInspection 采购质检
     * @return 结果
     */
    @Override
    public int updateBizQualityInspection(BizQualityInspection bizQualityInspection)
    {
        bizQualityInspection.setUpdateTime(DateUtils.getNowDate());
        return bizQualityInspectionMapper.updateBizQualityInspection(bizQualityInspection);
    }

    /**
     * 批量删除采购质检
     * 
     * @param ids 需要删除的采购质检主键
     * @return 结果
     */
    @Override
    public int deleteBizQualityInspectionByIds(Long[] ids)
    {
        return bizQualityInspectionMapper.deleteBizQualityInspectionByIds(ids);
    }

    /**
     * 删除采购质检信息
     * 
     * @param id 采购质检主键
     * @return 结果
     */
    @Override
    public int deleteBizQualityInspectionById(Long id)
    {
        return bizQualityInspectionMapper.deleteBizQualityInspectionById(id);
    }
}
