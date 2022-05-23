package com.ruoyi.receiving.service.impl;

import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.purchase.domain.Purchase;
import com.ruoyi.purchase.mapper.PurchaseMapper;
import com.ruoyi.purchase_detail.domain.PurchaseDetail;
import com.ruoyi.purchase_detail.mapper.PurchaseDetailMapper;
import com.ruoyi.put.domain.PutDetail;
import com.ruoyi.put.mapper.PutDetailMapper;
import com.ruoyi.receiving.service.IReceivingService;
import org.apache.commons.collections4.Put;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 收货管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-23
 */
@Service
public class ReceivingServiceImpl implements IReceivingService
{
    @Autowired
    private PurchaseMapper purchaseMapper;

    @Autowired
    private PurchaseDetailMapper purchaseDetailMapper;

    @Autowired
    private PutDetailMapper putDetailMapper;

    /**
     * 查询收货管理
     * 
     * @param id 收货管理主键
     * @return 收货管理
     */
    @Override
    public Purchase selectPurchaseById(Long id)
    {
        Purchase purchase = purchaseMapper.selectPurchaseById(id);
        if(purchase != null){
            List<PurchaseDetail> purchaseDetail = purchaseDetailMapper.selectPurchaseDetailByPurchaseKeyId(purchase.getId());
            for (PurchaseDetail pd : purchaseDetail) {
                Map<String, Object> mp = new HashMap<>();
                mp.put("uncollected",pd.getQuantity()-pd.getQuantityReceived());
                mp.put("total",purchaseDetail.size());
                pd.setParams(mp);
            }
            Map<String, Object> mapData = new HashMap<>();
            mapData.put("receivingProductList",purchaseDetail);
            purchase.setParams(mapData);
        }
        return purchase;
    }

    /**
     * 查询收货管理列表
     * 
     * @param purchase 收货管理
     * @return 收货管理
     */
    @Override
    public List<Purchase> selectReceivingList(Purchase purchase)
    {
        List<Purchase> purchaseList = purchaseMapper.selectReceivingList(purchase);
        for (Purchase p : purchaseList) {
            List<PurchaseDetail> purchaseDetailList = purchaseDetailMapper.selectPurchaseDetailByPurchaseKeyId(p.getId());
            int quantity = 0;   //预期数
            int quantity_received = 0;  //已收数
            int uncollected = 0;    //未收数
            int put = 0;    //上架
            for (PurchaseDetail pd : purchaseDetailList) {
                quantity += pd.getQuantity();
                quantity_received += pd.getQuantityReceived();
                if (pd.getIsShelf() == 1){
                    put += pd.getQuantityReceived();
                }
            }
            uncollected = quantity-quantity_received;
            Map<String, Object> mapData = new HashMap<>();
            mapData.put("quantity",quantity);
            mapData.put("quantityReceived",quantity_received);
            mapData.put("uncollected",uncollected);
            mapData.put("put",put);
            p.setParams(mapData);
        }
        return purchaseList;
    }

    /**
     * 新增收货管理
     * 
     * @param purchase 收货管理
     * @return 结果
     */
    @Override
    public int insertPurchase(Purchase purchase)
    {
        purchase.setCreateTime(DateUtils.getNowDate());
        return purchaseMapper.insertPurchase(purchase);
    }

    /**
     * 修改收货管理
     * 
     * @param purchase 收货管理
     * @return 结果
     */
    @Override
    public int updatePurchase(Purchase purchase)
    {
        purchase.setUpdateTime(DateUtils.getNowDate());
        return purchaseMapper.updatePurchase(purchase);
    }

    /**
     * 确认收货
     * @param purchaseDetailList 收货产品数据
     * @return 结果
     */
    public int updatePurchaseDetail(List<PurchaseDetail> purchaseDetailList)
    {
        int success = 0;
        int dataNum = purchaseDetailList.size();
        Long purchaseKeyId = purchaseDetailList.get(0).getPurchaseKeyId();
        Date onTime = DateUtils.getNowDate();
        Long receivingStatus = Long.valueOf(DictUtils.getDictValue("sys_receiving_status","收货完成"));

        for (PurchaseDetail pd : purchaseDetailList) {
            Long uncollected = Long.valueOf(String.valueOf(pd.getParams().get("uncollected")));
            Long received = pd.getQuantityReceived()+uncollected;
            if(pd.getQuantity().equals(received)){
                pd.setStatus(receivingStatus);
            }else{
                if(uncollected!=0){
                    pd.setStatus(Long.valueOf(DictUtils.getDictValue("sys_receiving_status","收货中")));
                }
            }
            pd.setQuantityReceived(received);
            success += purchaseDetailMapper.updatePurchaseDetail(pd);
            if(uncollected!=0){
                //同步上架
                PutDetail putDetail = new PutDetail();
                putDetail.setPurchaseKeyId(pd.getPurchaseKeyId());
                putDetail.setPdId(pd.getId());
                putDetail.setProductSku(pd.getProductSku());
                putDetail.setProductName(pd.getProductName());
                putDetail.setQuantity(uncollected);
                if((boolean)pd.getParams().get("isPut")){
                    putDetail.setStatus(Long.valueOf(DictUtils.getDictValue("sys_put_status","已上架")));
                    putDetail.setUpdateTime(onTime);
                }
                putDetail.setOperatorId(SecurityUtils.getUserId());
                putDetail.setCreateTime(onTime);
                putDetailMapper.insertPutDetail(putDetail);
            }
        }
        //父表状态更新
        List<PurchaseDetail> purchaseDetail = purchaseDetailMapper.selectPurchaseDetailByPurchaseKeyId(purchaseKeyId);
        int pdSize = purchaseDetail.size();
        int count = 0;
        for (PurchaseDetail pd1:purchaseDetail){
            if(pd1.getStatus().equals(receivingStatus)){
                count += 1;
            }
        }
        if(pdSize==count){
            Purchase purchase = new Purchase();
            purchase.setId(purchaseKeyId);
            purchase.setStatus(Long.valueOf(DictUtils.getDictValue("sys_purchase_status","收货完成")));
            purchaseMapper.updatePurchase(purchase);
        }
        return success==dataNum?1:0;
    }

}
