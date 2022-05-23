package com.ruoyi.purchase_detail.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.*;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.products.domain.Products;
import com.ruoyi.products.mapper.ProductsMapper;
import com.ruoyi.purchase.domain.Purchase;
import com.ruoyi.purchase.mapper.PurchaseMapper;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import com.ruoyi.purchase_detail.mapper.PurchaseDetailMapper;
import com.ruoyi.purchase_detail.domain.PurchaseDetail;
import com.ruoyi.purchase_detail.service.IPurchaseDetailService;

import javax.validation.Validator;

/**
 * 采购产品明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-29
 */
@Service
public class PurchaseDetailServiceImpl implements IPurchaseDetailService 
{
    private static final Logger log = LoggerFactory.getLogger(PurchaseDetailServiceImpl.class);

    @Autowired
    private PurchaseDetailMapper purchaseDetailMapper;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    protected Validator validator;

    @Autowired
    private PurchaseMapper purchaseMapper;

    /**
     * 查询采购产品明细
     * 
     * @param id 采购产品明细主键
     * @return 采购产品明细
     */
    @Override
    public PurchaseDetail selectPurchaseDetailById(Long id)
    {
        return purchaseDetailMapper.selectPurchaseDetailById(id);
    }

    /**
     * 查询采购产品明细列表
     * 
     * @param purchaseDetail 采购产品明细
     * @return 采购产品明细
     */
    @Override
    public List<PurchaseDetail> selectPurchaseDetailList(PurchaseDetail purchaseDetail)
    {
        return purchaseDetailMapper.selectPurchaseDetailList(purchaseDetail);
    }

    /**
     * 新增采购产品明细
     * 
     * @param purchaseDetail 采购产品明细
     * @return 结果
     */
    @Override
    public int insertPurchaseDetail(PurchaseDetail purchaseDetail)
    {
        purchaseDetail.setCreateTime(DateUtils.getNowDate());
        return purchaseDetailMapper.insertPurchaseDetail(purchaseDetail);
    }

    /**
     * 修改采购产品明细
     * 
     * @param purchaseDetail 采购产品明细
     * @return 结果
     */
    @Override
    public int updatePurchaseDetail(PurchaseDetail purchaseDetail)
    {
        purchaseDetail.setUpdateTime(DateUtils.getNowDate());
        return purchaseDetailMapper.updatePurchaseDetail(purchaseDetail);
    }

    /**
     * 批量删除采购产品明细
     * 
     * @param ids 需要删除的采购产品明细主键
     * @return 结果
     */
    @Override
    public int deletePurchaseDetailByIds(Long[] ids)
    {
        return purchaseDetailMapper.deletePurchaseDetailByIds(ids);
    }

    /**
     * 删除采购产品明细信息
     * 
     * @param id 采购产品明细主键
     * @return 结果
     */
    @Override
    public int deletePurchaseDetailById(Long id)
    {
        return purchaseDetailMapper.deletePurchaseDetailById(id);
    }

    /**
     * 导入采购产品数据
     *
     * @param purchaseDetailList 采购数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importPurchaseDetail(List<PurchaseDetail> purchaseDetailList, Boolean isUpdateSupport, PurchaseUploadData purchaseUploadData)
    {
        Date onTime = DateUtils.getNowDate();
        if(purchaseUploadData.getCreateTime()==null){
            purchaseUploadData.setCreateTime(onTime);
        }
        String purchaseId = OddNumUtil.getOddNum("CG", SecurityUtils.getUserId());

        Purchase purchase = new Purchase();
        purchase.setPurchaseId(purchaseId);
        purchase.setWarehouseId(purchaseUploadData.getWarehouseId());
        purchase.setSupplierId(purchaseUploadData.getSupplierId());
        purchase.setBuyerId(purchaseUploadData.getBuyerId());
        if(!purchaseUploadData.getTrackingNumber().equals("null")){
            purchase.setTrackingNumber(purchaseUploadData.getTrackingNumber());
        }
        if(!purchaseUploadData.getSettlementMethod().equals("null")) {
            purchase.setSettlementMethod(purchaseUploadData.getSettlementMethod());
        }
        if(!purchaseUploadData.getCurrency().equals("null")) {
            purchase.setCurrency(purchaseUploadData.getCurrency());
        }
        if(!purchaseUploadData.getPaymentMethod().equals("null")) {
            purchase.setPaymentMethod(purchaseUploadData.getPaymentMethod());
        }
        if(!purchaseUploadData.getCollectionMethod().equals("null")) {
            purchase.setCollectionMethod(purchaseUploadData.getCollectionMethod());
        }
        if(!purchaseUploadData.getPaymentCycleType().equals("null")) {
            purchase.setPaymentCycleType(purchaseUploadData.getPaymentCycleType());
        }
        if(!purchaseUploadData.getSupplierShippingType().equals("null")) {
            purchase.setSupplierShippingType(purchaseUploadData.getSupplierShippingType());
        }
        if(!purchaseUploadData.getFreight().equals("null")) {
            purchase.setFreight(purchaseUploadData.getFreight());
        }
        if(!purchaseUploadData.getRemarks().equals("null")) {
            purchase.setRemarks(purchaseUploadData.getRemarks());
        }
        purchase.setEstimatedArrivalTime(purchaseUploadData.getEstimatedArrivalTime());
        purchase.setDeptId(purchaseUploadData.getDeptId());
        purchase.setCreateTime(purchaseUploadData.getCreateTime());
        int resId = purchaseMapper.insertPurchase(purchase);

        if (StringUtils.isNull(purchaseDetailList) || purchaseDetailList.size() == 0)
        {
            throw new ServiceException("导入采购产品数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (PurchaseDetail purchaseDetail : purchaseDetailList)
        {
            try
            {
                // 验证当前SKU是否对应选择供应商
                Products products = productsMapper.selectProductsBySku(purchaseDetail.getProductSku());
                if(products!=null){
                    if (products.getSupplierId()==purchaseUploadData.getSupplierId())
                    {
                        BeanValidators.validateWithException(validator, purchaseDetail);
                        purchaseDetail.setPurchaseKeyId(purchase.getId());
                        purchaseDetail.setProductName(products.getProductName());
                        purchaseDetail.setCreateTime(purchaseUploadData.getCreateTime());
                        purchaseDetailMapper.insertPurchaseDetail(purchaseDetail);
                        successNum++;
                        successMsg.append("<br/>" + successNum + "、产品 " + purchaseDetail.getProductSku() + " 导入成功");
                    }
                    else
                    {
                        failureNum++;
                        failureMsg.append("<br/>" + failureNum + "、产品 " + purchaseDetail.getProductSku() + " 非对应供应商");
                    }
                }
                else{
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、产品 " + purchaseDetail.getProductSku() + " 不存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + purchaseDetail.getProductSku() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }

        if (failureNum > 0 && successNum > 0)
        {
            failureMsg.insert(0, "已生成采购单【"+ purchaseId +"】<br/>有部分导入失败！共 " + failureNum + " 条数据失败，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else if(failureNum > 0 && successNum < 1)
        {
            failureMsg.insert(0, "采购产品全部导入失败！共 " + failureNum + " 条数据失败，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "已生成采购单【"+ purchaseId +"】，采购产品全部导入成功！共 " + successNum + " 条，数据如下：");
        }

        return successMsg.toString();
    }
}
