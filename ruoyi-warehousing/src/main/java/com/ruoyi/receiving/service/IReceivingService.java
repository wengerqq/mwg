package com.ruoyi.receiving.service;

import com.ruoyi.purchase.domain.Purchase;
import com.ruoyi.purchase_detail.domain.PurchaseDetail;

import java.util.List;

/**
 * 收货管理Service接口
 * 
 * @author ruoyi
 * @date 2022-04-23
 */
public interface IReceivingService
{
    /**
     * 查询收货管理
     * 
     * @param id 收货管理主键
     * @return 收货管理
     */
    public Purchase selectPurchaseById(Long id);

    /**
     * 查询收货管理列表
     * 
     * @param purchase 收货管理
     * @return 收货管理集合
     */
    public List<Purchase> selectReceivingList(Purchase purchase);

    /**
     * 新增收货管理
     * 
     * @param purchase 收货管理
     * @return 结果
     */
    public int insertPurchase(Purchase purchase);

    /**
     * 修改收货管理
     * 
     * @param purchase 收货管理
     * @return 结果
     */
    public int updatePurchase(Purchase purchase);

    /**
     *  确认收货
     * @param purchaseDetailList 收货产品数据
     * @return 结果
     */
    public int updatePurchaseDetail(List<PurchaseDetail> purchaseDetailList);

}
