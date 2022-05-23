package com.ruoyi.receiving.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.purchase.domain.Purchase;
import com.ruoyi.purchase.service.IPurchaseService;
import com.ruoyi.purchase_detail.domain.PurchaseDetail;
import com.ruoyi.receiving.service.IReceivingService;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收货管理Controller
 * 
 * @author ruoyi
 * @date 2022-04-23
 */
@RestController
@RequestMapping("/warehousing/receiving")
public class ReceivingController extends BaseController
{
    @Autowired
    private IReceivingService receivingService;

    /**
     * 查询收货管理列表
     */
    @PreAuthorize("@ss.hasPermi('warehousing:receiving:list')")
    @PostMapping("/list")
    public TableDataInfo list(Purchase purchase)
    {
        startPage();
        List<Purchase> list = receivingService.selectReceivingList(purchase);
        return getDataTable(list);
    }

    /**
     * 导出收货管理列表
     */
    @PreAuthorize("@ss.hasPermi('warehousing:receiving:export')")
    @Log(title = "收货管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Purchase purchase)
    {
        List<Purchase> list = receivingService.selectReceivingList(purchase);
        ExcelUtil<Purchase> util = new ExcelUtil<Purchase>(Purchase.class);
        util.exportExcel(response, list, "收货管理数据");
    }

    /**
     * 获取收货管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('warehousing:receiving:query')")
    @PostMapping("/getInfo")
    public AjaxResult getInfo(@RequestBody Long id)
    {
        return AjaxResult.success(receivingService.selectPurchaseById(id));
    }

    /**
     * 新增收货管理
     */
    @PreAuthorize("@ss.hasPermi('warehousing:receiving:add')")
    @Log(title = "收货管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Purchase purchase)
    {
        return toAjax(receivingService.insertPurchase(purchase));
    }

    /**
     * 修改收货管理
     */
    @PreAuthorize("@ss.hasPermi('warehousing:receiving:edit')")
    @Log(title = "收货管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody Purchase purchase)
    {
        return toAjax(receivingService.updatePurchase(purchase));
    }

    /**
     * 确认收货
     */
    @PreAuthorize("@ss.hasPermi('warehousing:receiving:receiving')")
    @Log(title = "确认收货", businessType = BusinessType.UPDATE)
    @PostMapping("/receiving")
    public AjaxResult receiving(@RequestBody List<PurchaseDetail> purchaseDetailList)
    {
        return toAjax(receivingService.updatePurchaseDetail(purchaseDetailList));
    }

}
