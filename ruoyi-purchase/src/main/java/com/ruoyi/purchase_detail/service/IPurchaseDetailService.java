package com.ruoyi.purchase_detail.service;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.PurchaseUploadData;
import com.ruoyi.purchase_detail.domain.PurchaseDetail;

/**
 * 采购产品明细Service接口
 * 
 * @author ruoyi
 * @date 2022-03-29
 */
public interface IPurchaseDetailService 
{
    /**
     * 查询采购产品明细
     * 
     * @param id 采购产品明细主键
     * @return 采购产品明细
     */
    public PurchaseDetail selectPurchaseDetailById(Long id);

    /**
     * 查询采购产品明细列表
     * 
     * @param purchaseDetail 采购产品明细
     * @return 采购产品明细集合
     */
    public List<PurchaseDetail> selectPurchaseDetailList(PurchaseDetail purchaseDetail);

    /**
     * 新增采购产品明细
     * 
     * @param purchaseDetail 采购产品明细
     * @return 结果
     */
    public int insertPurchaseDetail(PurchaseDetail purchaseDetail);

    /**
     * 修改采购产品明细
     * 
     * @param purchaseDetail 采购产品明细
     * @return 结果
     */
    public int updatePurchaseDetail(PurchaseDetail purchaseDetail);

    /**
     * 批量删除采购产品明细
     * 
     * @param ids 需要删除的采购产品明细主键集合
     * @return 结果
     */
    public int deletePurchaseDetailByIds(Long[] ids);

    /**
     * 删除采购产品明细信息
     * 
     * @param id 采购产品明细主键
     * @return 结果
     */
    public int deletePurchaseDetailById(Long id);

    /**
     * 导入采购数据
     * @param purchaseDetailList 采购产品列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param purchaseUploadData 上传时携带的参数
     * @return 结果
     */
    public String importPurchaseDetail(List<PurchaseDetail> purchaseDetailList, Boolean isUpdateSupport, PurchaseUploadData purchaseUploadData);
}
