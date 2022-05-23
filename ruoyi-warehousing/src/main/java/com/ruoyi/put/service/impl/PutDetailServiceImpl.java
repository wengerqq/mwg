package com.ruoyi.put.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.purchase.domain.Purchase;
import com.ruoyi.purchase.mapper.PurchaseMapper;
import com.ruoyi.purchase_detail.domain.PurchaseDetail;
import com.ruoyi.purchase_detail.mapper.PurchaseDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.put.mapper.PutDetailMapper;
import com.ruoyi.put.domain.PutDetail;
import com.ruoyi.put.service.IPutDetailService;

/**
 * 上架管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-04
 */
@Service
public class PutDetailServiceImpl implements IPutDetailService 
{
    @Autowired
    private PutDetailMapper putDetailMapper;

    @Autowired
    private PurchaseDetailMapper purchaseDetailMapper;

    @Autowired
    private PurchaseMapper purchaseMapper;

    /**
     * 查询上架管理
     * 
     * @param id 上架管理主键
     * @return 上架管理
     */
    @Override
    public PutDetail selectPutDetailById(Long id)
    {
        return putDetailMapper.selectPutDetailById(id);
    }

    /**
     * 查询上架管理列表
     * 
     * @param putDetail 上架管理
     * @return 上架管理
     */
    @Override
    public List<PutDetail> selectPutDetailList(PutDetail putDetail)
    {
        return putDetailMapper.selectPutDetailList(putDetail);
    }

    /**
     * 新增上架管理
     * 
     * @param putDetail 上架管理
     * @return 结果
     */
    @Override
    public int insertPutDetail(PutDetail putDetail)
    {
        putDetail.setCreateTime(DateUtils.getNowDate());
        return putDetailMapper.insertPutDetail(putDetail);
    }

    /**
     * 修改上架管理
     * 
     * @param putDetail 上架管理
     * @return 结果
     */
    @Override
    public int updatePutDetail(PutDetail putDetail)
    {
        putDetail.setUpdateTime(DateUtils.getNowDate());
        return putDetailMapper.updatePutDetail(putDetail);
    }

    /**
     * 批量删除上架管理
     * 
     * @param ids 需要删除的上架管理主键
     * @return 结果
     */
    @Override
    public int deletePutDetailByIds(Long[] ids)
    {
        return putDetailMapper.deletePutDetailByIds(ids);
    }

    /**
     * 删除上架管理信息
     * 
     * @param id 上架管理主键
     * @return 结果
     */
    @Override
    public int deletePutDetailById(Long id)
    {
        return putDetailMapper.deletePutDetailById(id);
    }

    /**
     * 查询上架管理
     *
     * @param purchaseKeyId 上架管理主键
     * @return 上架管理
     */
    @Override
    public List<PutDetail> selectPutDetailByPurchaseKeyId(Long purchaseKeyId)
    {
        List<PutDetail> putDetailList = putDetailMapper.selectPutDetailByPurchaseKeyId(purchaseKeyId);
        for(PutDetail pd:putDetailList){
            Map<String, Object> map = new HashMap<>();
            map.put("actualPut",pd.getQuantity());
            pd.setParams(map);
        }
        return putDetailList;
    }

    /**
     * 修改上架管理
     *
     * @param putDetailList 上架管理
     * @return 结果
     */
    @Override
    public int updatePutDetailList(List<PutDetail> putDetailList)
    {
        Date onTime = DateUtils.getNowDate();
        for(PutDetail pd:putDetailList){
            int actualPut = Integer.parseInt(String.valueOf(pd.getParams().get("actualPut")));
            int surplus = Integer.parseInt(String.valueOf(pd.getQuantity()))-actualPut;
            if(surplus==0){
                pd.setStatus(Long.valueOf(DictUtils.getDictValue("sys_put_status","已上架")));
                pd.setUpdateTime(onTime);
            }else{
                pd.setQuantity(Long.valueOf(surplus));
                PutDetail insertPd = new PutDetail();
                insertPd.setId(null);
                insertPd.setPurchaseKeyId(pd.getPurchaseKeyId());
                insertPd.setProductSku(pd.getProductSku());
                insertPd.setProductName(pd.getProductName());
                insertPd.setQuantity(Long.valueOf(actualPut));
                insertPd.setStatus(Long.valueOf(DictUtils.getDictValue("sys_put_status","已上架")));
                insertPd.setOperatorId(pd.getOperatorId());
                insertPd.setCreateTime(onTime);
                insertPd.setUpdateTime(onTime);
                putDetailMapper.insertPutDetail(insertPd);
            }
            putDetailMapper.updatePutDetail(pd);
        }
        return 1;
    }

    /**
     * 取消上架
     *
     * @param putDetailList 上架管理
     * @return 结果
     */
    @Override
    public int deletePutDetailList(List<PutDetail> putDetailList)
    {
        int count = 0;
        for(PutDetail pd:putDetailList){
            PurchaseDetail purchaseDetail = purchaseDetailMapper.selectPurchaseDetailById(pd.getPdId());
            Long quantity = purchaseDetail.getQuantityReceived()-pd.getQuantity();
            purchaseDetail.setQuantityReceived(quantity);
            if(quantity==0){
                purchaseDetail.setStatus(Long.valueOf(DictUtils.getDictValue("sys_receiving_status","在途")));
            }else{
                purchaseDetail.setStatus(Long.valueOf(DictUtils.getDictValue("sys_receiving_status","收货中")));
                count += 1;
            }
            int result = purchaseDetailMapper.updatePurchaseDetail(purchaseDetail);
            if(result>0){
                putDetailMapper.deletePutDetailById(pd.getId());
            }
        }
        Purchase purchase = purchaseMapper.selectPurchaseById(putDetailList.get(0).getPurchaseKeyId());
        Long status = count>0?Long.valueOf(DictUtils.getDictValue("sys_purchase_status","收货中")):Long.valueOf(DictUtils.getDictValue("sys_purchase_status","在途"));
        purchase.setStatus(status);
        return purchaseMapper.updatePurchase(purchase);
    }
}
