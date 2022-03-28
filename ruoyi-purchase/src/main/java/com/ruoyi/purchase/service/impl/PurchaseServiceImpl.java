package com.ruoyi.purchase.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.purchase.mapper.PurchaseMapper;
import com.ruoyi.purchase.domain.Purchase;
import com.ruoyi.purchase.service.IPurchaseService;

/**
 * 采购确认Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-22
 */
@Service
public class PurchaseServiceImpl implements IPurchaseService 
{
    @Autowired
    private PurchaseMapper purchaseMapper;

    /**
     * 查询采购确认
     * 
     * @param purchaseId 采购确认主键
     * @return 采购确认
     */
    @Override
    public Purchase selectPurchaseByPurchaseId(String purchaseId)
    {
        return purchaseMapper.selectPurchaseByPurchaseId(purchaseId);
    }

    /**
     * 查询采购确认列表
     * 
     * @param purchase 采购确认
     * @return 采购确认
     */
    @Override
    public List<Purchase> selectPurchaseList(Purchase purchase)
    {
        return purchaseMapper.selectPurchaseList(purchase);
    }

    /**
     * 新增采购确认
     * 
     * @param purchase 采购确认
     * @return 结果
     */
    @Override
    public int insertPurchase(Purchase purchase)
    {
        purchase.setCreateTime(DateUtils.getNowDate());
        return purchaseMapper.insertPurchase(purchase);
    }

    /**
     * 修改采购确认
     * 
     * @param purchase 采购确认
     * @return 结果
     */
    @Override
    public int updatePurchase(Purchase purchase)
    {
        purchase.setUpdateTime(DateUtils.getNowDate());
        return purchaseMapper.updatePurchase(purchase);
    }

    /**
     * 批量删除采购确认
     * 
     * @param purchaseIds 需要删除的采购确认主键
     * @return 结果
     */
    @Override
    public int deletePurchaseByPurchaseIds(String[] purchaseIds)
    {
        return purchaseMapper.deletePurchaseByPurchaseIds(purchaseIds);
    }

    /**
     * 删除采购确认信息
     * 
     * @param purchaseId 采购确认主键
     * @return 结果
     */
    @Override
    public int deletePurchaseByPurchaseId(String purchaseId)
    {
        return purchaseMapper.deletePurchaseByPurchaseId(purchaseId);
    }
}
