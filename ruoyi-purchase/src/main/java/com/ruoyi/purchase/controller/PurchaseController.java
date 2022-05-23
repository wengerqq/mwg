package com.ruoyi.purchase.controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.OddNumUtil;
import com.ruoyi.common.utils.PurchaseUploadData;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.purchase_detail.domain.PurchaseDetail;
import com.ruoyi.purchase_detail.service.IPurchaseDetailService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.purchase.domain.Purchase;
import com.ruoyi.purchase.service.IPurchaseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 采购确认Controller
 * 
 * @author ruoyi
 * @date 2022-03-22
 */
@RestController
@RequestMapping("/purchase/purchase")
public class PurchaseController extends BaseController
{
    @Autowired
    private IPurchaseService purchaseService;

    @Autowired
    private IPurchaseDetailService purchaseDetailService;

    /**
     * 查询采购确认列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:purchase:list')")
    @PostMapping("/list")
    public TableDataInfo list(Purchase purchase)
    {
        startPage();
        List<Purchase> list = purchaseService.selectPurchaseList(purchase);
        return getDataTable(list);
    }

    /**
     * 导出采购确认列表
     */
    @PreAuthorize("@ss.hasPermi('purchase:purchase:export')")
    @Log(title = "采购确认", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Purchase purchase)
    {
        List<Purchase> list = purchaseService.selectPurchaseList(purchase);
        ExcelUtil<Purchase> util = new ExcelUtil<Purchase>(Purchase.class);
        util.exportExcel(response, list, "采购确认数据");
    }

    /**
     * 获取采购确认详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchase:purchase:query')")
    @PostMapping("/getInfo")
    public AjaxResult getInfo(@RequestBody Long id)
    {
        return AjaxResult.success(purchaseService.selectPurchaseById(id));
    }

    /**
     * 新增采购确认
     */
    @PreAuthorize("@ss.hasPermi('purchase:purchase:add')")
    @Log(title = "采购确认", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Purchase purchase)
    {
        purchase.setPurchaseId(OddNumUtil.getOddNum("CG", SecurityUtils.getUserId()));
        return toAjax(purchaseService.insertPurchase(purchase),"采购单【"+purchase.getPurchaseId()+"】录入成功");
    }

    /**
     * 修改采购确认
     */
    @PreAuthorize("@ss.hasPermi('purchase:purchase:edit')")
    @Log(title = "采购确认", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody Purchase purchase)
    {
        return toAjax(purchaseService.updatePurchase(purchase));
    }

    /**
     * 删除采购确认
     */
    @PreAuthorize("@ss.hasPermi('purchase:purchase:remove')")
    @Log(title = "采购确认", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
    public AjaxResult remove(@RequestBody String[] purchaseIds)
    {
        return toAjax(purchaseService.deletePurchaseByPurchaseIds(purchaseIds));
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<PurchaseDetail> util = new ExcelUtil<PurchaseDetail>(PurchaseDetail.class);
        util.importTemplateExcel(response, "采购产品数据");
    }

    @Log(title = "采购导入", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('purchase:purchase:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, PurchaseUploadData purchaseUploadData, boolean updateSupport) throws Exception
    {
        ExcelUtil<PurchaseDetail> util = new ExcelUtil<PurchaseDetail>(PurchaseDetail.class);
        List<PurchaseDetail> purchaseDetailList = util.importExcel(file.getInputStream());
        String message = purchaseDetailService.importPurchaseDetail(purchaseDetailList, updateSupport,purchaseUploadData);
        return AjaxResult.success(message);
    }
}
