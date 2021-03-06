package com.ruoyi.purchase.mapper;

import java.security.PublicKey;
import java.util.List;
import com.ruoyi.purchase.domain.Purchase;
import org.apache.ibatis.annotations.Options;

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
     * 根据ID查询采购/入库单
     * @param id
     * @return
     */
    public Purchase selectPurchaseById(Long id);

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
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
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

    /*******************************************收货管理**************************************************/

    /**
     * 查询收货列表
     * @param purchase
     * @return
     */
    public List<Purchase> selectReceivingList(Purchase purchase);
}
