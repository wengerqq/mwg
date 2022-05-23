package com.ruoyi.put.mapper;

import java.util.List;
import com.ruoyi.put.domain.PutDetail;

/**
 * 上架管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-04
 */
public interface PutDetailMapper 
{
    /**
     * 查询上架管理
     * 
     * @param id 上架管理主键
     * @return 上架管理
     */
    public PutDetail selectPutDetailById(Long id);

    /**
     * 查询上架管理列表
     * 
     * @param putDetail 上架管理
     * @return 上架管理集合
     */
    public List<PutDetail> selectPutDetailList(PutDetail putDetail);

    /**
     * 新增上架管理
     * 
     * @param putDetail 上架管理
     * @return 结果
     */
    public int insertPutDetail(PutDetail putDetail);

    /**
     * 修改上架管理
     * 
     * @param putDetail 上架管理
     * @return 结果
     */
    public int updatePutDetail(PutDetail putDetail);

    /**
     * 删除上架管理
     * 
     * @param id 上架管理主键
     * @return 结果
     */
    public int deletePutDetailById(Long id);

    /**
     * 批量删除上架管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePutDetailByIds(Long[] ids);

    /**
     * 查询上架管理
     *
     * @param purchaseKeyId 采购ID
     * @return 上架管理
     */
    public List<PutDetail> selectPutDetailByPurchaseKeyId(Long purchaseKeyId);
}
