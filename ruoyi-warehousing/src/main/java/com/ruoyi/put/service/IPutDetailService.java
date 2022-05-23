package com.ruoyi.put.service;

import java.util.List;
import com.ruoyi.put.domain.PutDetail;

/**
 * 上架管理Service接口
 * 
 * @author ruoyi
 * @date 2022-05-04
 */
public interface IPutDetailService 
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
     * 批量删除上架管理
     * 
     * @param ids 需要删除的上架管理主键集合
     * @return 结果
     */
    public int deletePutDetailByIds(Long[] ids);

    /**
     * 删除上架管理信息
     * 
     * @param id 上架管理主键
     * @return 结果
     */
    public int deletePutDetailById(Long id);

    /**
     * 根据采购ID查询上架管理
     *
     * @param purchaseKeyId 采购ID
     * @return 上架管理
     */
    public List<PutDetail> selectPutDetailByPurchaseKeyId(Long purchaseKeyId);

    /**
     * 确认上架
     *
     * @param putDetailList 上架管理
     * @return 结果
     */
    public int updatePutDetailList(List<PutDetail> putDetailList);

    /**
     * 取消上架
     *
     * @param putDetailList 上架管理
     * @return 结果
     */
    public int deletePutDetailList(List<PutDetail> putDetailList);
}
