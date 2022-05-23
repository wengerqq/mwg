package com.ruoyi.purchase_detail.mapper;

import java.util.List;
import com.ruoyi.purchase_detail.domain.PurchaseDetail;

/**
 * 采购产品明细Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-29
 */
public interface PurchaseDetailMapper 
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
     * 删除采购产品明细
     * 
     * @param id 采购产品明细主键
     * @return 结果
     */
    public int deletePurchaseDetailById(Long id);

    /**
     * 批量删除采购产品明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePurchaseDetailByIds(Long[] ids);

    /**
     * 修改采购产品明细新
     * @param purchaseDetail
     * @return 结果
     */
    public int replacePurchaseDetail(PurchaseDetail purchaseDetail);

    /**
     * 删除采购产品明细
     * @param purchaseKeyId 采购产品明细采购单ID
     * @return 结果
     */
    public int deletePurchaseDetailByPurchaseKeyId(Long purchaseKeyId);

    /**
     * 查询当前采购单SKU入库情况
     * @param purchaseKeyId 采购单ID
     * @return
     */
    public List<PurchaseDetail> selectPurchaseDetailByPurchaseKeyId(Long purchaseKeyId);
}
