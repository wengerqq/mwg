package com.ruoyi.purchase.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.products.domain.Products;
import com.ruoyi.products.mapper.ProductsMapper;
import com.ruoyi.purchase_detail.domain.PurchaseDetail;
import com.ruoyi.purchase_detail.mapper.PurchaseDetailMapper;
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

    @Autowired
    private PurchaseDetailMapper purchaseDetailMapper;

    @Autowired
    private ProductsMapper productsMapper;

    /**
     * 查询采购确认
     * 
     * @param id 采购确认主键
     * @return 采购确认
     */
    @Override
    public Purchase selectPurchaseById(Long id)
    {
        PurchaseDetail purchaseDetail = new PurchaseDetail();
        purchaseDetail.setPurchaseKeyId(id);
        Map<String, Object> map = new HashMap<>();
        List<PurchaseDetail> list = purchaseDetailMapper.selectPurchaseDetailList(purchaseDetail);
        for (PurchaseDetail plist : list) {
            Products products = productsMapper.selectProductsBySku(plist.getProductSku());
            Map<String, Object> map1 = new HashMap<>();
            map1.put("lastPrice",products.getPurchasePrice());
            map1.put("currency",products.getCurrencyId());
            plist.setParams(map1);
        }
        map.put("productsDetails",list);
        map.put("isChange",false);

        Purchase purchase = purchaseMapper.selectPurchaseById(id);
        purchase.setParams(map);
        return purchase;
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
        return addOrUpdatePurchase(purchase,"add");
//        Date onTime = DateUtils.getNowDate();
//        //插入采购主表
//        purchase.setOperatorId(SecurityUtils.getUserId());
//        if(purchase.getCreateTime()==null){
//            purchase.setCreateTime(onTime);
//        }
//        int result = purchaseMapper.insertPurchase(purchase);
//        //插入采购明细表
//        if(result>0){
//            List<Map<String,Object>> list = (ArrayList)purchase.getParams().get("productsDetails");
//            for(Map<String, Object> item : list){
//                PurchaseDetail pd = new PurchaseDetail();
//                String sku = (String) item.get("productSku");
//                pd.setPurchaseId(purchase.getPurchaseId());
//                pd.setProductSku(sku);
//                pd.setProductName((String)item.get("productName"));
//                pd.setQuantity(Long.valueOf(String.valueOf(item.get("quantity"))));
//                pd.setPrice(String.valueOf(item.get("price")));
//                pd.setReferencePrice((String)item.get("referencePrice"));
//                pd.setTaxRate((String)item.get("taxRate"));
//                pd.setExternalOrderNo((String)item.get("externalOrderNo"));
//                pd.setRemark((String)item.get("remark"));
//                if(item.get("deliveryTime")!=null){
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    try {
//                        pd.setDeliveryTime(sdf.parse((String) item.get("deliveryTime")));
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//                pd.setCreateTime(onTime);
//                purchaseDetailMapper.insertPurchaseDetail(pd);
//            }
//        }
//        return result;
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
        return addOrUpdatePurchase(purchase,"edit");
//        Date onTime = DateUtils.getNowDate();
//        //插入采购主表
//        purchase.setOperatorId(SecurityUtils.getUserId());
//        if(purchase.getCreateTime()==null){
//            purchase.setCreateTime(onTime);
//        }
//        purchase.setUpdateTime(onTime);
//        int result = purchaseMapper.updatePurchase(purchase);
//        if(result>0){
//            List<Map<String,Object>> list = (ArrayList)purchase.getParams().get("productsDetails");
//            for(Map<String, Object> item : list){
//                PurchaseDetail pd = new PurchaseDetail();
//                String sku = (String) item.get("productSku");
//                pd.setId(Long.valueOf(String.valueOf(item.get("id"))));    //新增
//                pd.setPurchaseId(purchase.getPurchaseId());
//                pd.setProductSku(sku);
//                pd.setProductName((String)item.get("productName"));
//                pd.setQuantity(Long.valueOf(String.valueOf(item.get("quantity"))));
//                pd.setPrice(String.valueOf(item.get("price")));
//                pd.setReferencePrice((String)item.get("referencePrice"));
//                pd.setTaxRate((String)item.get("taxRate"));
//                pd.setExternalOrderNo((String)item.get("externalOrderNo"));
//                pd.setRemark((String)item.get("remark"));
//                if(item.get("deliveryTime")!=null){
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    try {
//                        pd.setDeliveryTime(sdf.parse((String) item.get("deliveryTime")));
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//                //pd.setCreateTime(createTime);   //删除
//                pd.setUpdateTime(onTime); //新增
//                //purchaseDetailMapper.insertPurchaseDetail(pd);    //删除
//                purchaseDetailMapper.replacePurchaseDetail(pd);  //新增
//
//            }
//        }
//        return result;
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

    private int addOrUpdatePurchase(Purchase purchase,String type){
        Date onTime = DateUtils.getNowDate();
        //插入采购主表
        purchase.setOperatorId(SecurityUtils.getUserId());
        if(purchase.getCreateTime()==null){
            purchase.setCreateTime(onTime);
        }
        int result = 0;
        if(type=="add"){
            result = purchaseMapper.insertPurchase(purchase);
        }else{
            purchase.setUpdateTime(onTime);
            result = purchaseMapper.updatePurchase(purchase);
        }
        if(result>0){
            if((Boolean) purchase.getParams().get("isChange")&&type=="edit"){
                purchaseDetailMapper.deletePurchaseDetailByPurchaseKeyId(purchase.getId());
            }
            List<Map<String,Object>> list = (ArrayList)purchase.getParams().get("productsDetails");
            for(Map<String, Object> item : list){
                PurchaseDetail pd = new PurchaseDetail();
                String sku = (String) item.get("productSku");
                pd.setPurchaseKeyId(purchase.getId());
                pd.setProductSku(sku);
                pd.setProductName((String)item.get("productName"));
                pd.setQuantity(Long.valueOf(String.valueOf(item.get("quantity"))));
                pd.setPrice(String.valueOf(item.get("price")));
                pd.setReferencePrice((String)item.get("referencePrice"));
                pd.setTaxRate((String)item.get("taxRate"));
                pd.setExternalOrderNo((String)item.get("externalOrderNo"));
                pd.setRemark((String)item.get("remark"));
                if(item.get("deliveryTime")!=null){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        pd.setDeliveryTime(sdf.parse((String) item.get("deliveryTime")));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                if(type=="add"){
                    pd.setCreateTime(onTime);
                    purchaseDetailMapper.insertPurchaseDetail(pd);    //删除
                }else{
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        pd.setCreateTime(item.get("createTime")!=null?sdf1.parse((String) item.get("createTime")):onTime);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    pd.setId(Long.valueOf(String.valueOf(item.get("id"))));    //新增
                    pd.setUpdateTime(onTime);
                    purchaseDetailMapper.replacePurchaseDetail(pd);  //新增
                }
            }
        }
        return result;
    }
}
