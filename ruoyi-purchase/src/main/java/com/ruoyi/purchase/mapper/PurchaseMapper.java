package com.ruoyi.purchase.mapper;

import java.util.List;
import com.ruoyi.purchase.domain.Purchase;

/**
 * 采购确认Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-22
 */
public interface PurchaseMapper 
{
    /**
     * 查询采购确认
     * 
     * @param purchaseId 采购确认主键
     * @return 采购确认
     */
    public Purchase selectPurchaseByPurchaseId(String purchaseId);

    /**
     * 查询采购确认列表
     * 
     * @param purchase 采购确认
     * @return 采购确认集合
     */
    public List<Purchase> selectPurchaseList(Purchase purchase);

    /**
     * 新增采购确认
     * 
     * @param purchase 采购确认
     * @return 结果
     */
    public int insertPurchase(Purchase purchase);

    /**
     * 修改采购确认
     * 
     * @param purchase 采购确认
     * @return 结果
     */
    public int updatePurchase(Purchase purchase);

    /**
     * 删除采购确认
     * 
     * @param purchaseId 采购确认主键
     * @return 结果
     */
    public int deletePurchaseByPurchaseId(String purchaseId);

    /**
     * 批量删除采购确认
     * 
     * @param purchaseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePurchaseByPurchaseIds(String[] purchaseIds);
}
