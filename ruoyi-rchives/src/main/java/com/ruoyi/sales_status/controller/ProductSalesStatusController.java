package com.ruoyi.sales_status.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.sales_status.domain.ProductSalesStatus;
import com.ruoyi.sales_status.service.IProductSalesStatusService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品销售状态管理Controller
 * 
 * @author ruoyi
 * @date 2022-02-17
 */
@RestController
@RequestMapping("/rchives/sales_status")
public class ProductSalesStatusController extends BaseController
{
    @Autowired
    private IProductSalesStatusService productSalesStatusService;

    /**
     * 查询产品销售状态管理列表
     */
    @PreAuthorize("@ss.hasPermi('rchives:sales_status:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductSalesStatus productSalesStatus)
    {
        startPage();
        List<ProductSalesStatus> list = productSalesStatusService.selectProductSalesStatusList(productSalesStatus);
        return getDataTable(list);
    }

    /**
     * 导出产品销售状态管理列表
     */
    @PreAuthorize("@ss.hasPermi('rchives:sales_status:export')")
    @Log(title = "产品销售状态管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductSalesStatus productSalesStatus)
    {
        List<ProductSalesStatus> list = productSalesStatusService.selectProductSalesStatusList(productSalesStatus);
        ExcelUtil<ProductSalesStatus> util = new ExcelUtil<ProductSalesStatus>(ProductSalesStatus.class);
        util.exportExcel(response, list, "产品销售状态管理数据");
    }

    /**
     * 获取产品销售状态管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('rchives:sales_status:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(productSalesStatusService.selectProductSalesStatusById(id));
    }

    /**
     * 新增产品销售状态管理
     */
    @PreAuthorize("@ss.hasPermi('rchives:sales_status:add')")
    @Log(title = "产品销售状态管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductSalesStatus productSalesStatus)
    {
        return toAjax(productSalesStatusService.insertProductSalesStatus(productSalesStatus));
    }

    /**
     * 修改产品销售状态管理
     */
    @PreAuthorize("@ss.hasPermi('rchives:sales_status:edit')")
    @Log(title = "产品销售状态管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductSalesStatus productSalesStatus)
    {
        return toAjax(productSalesStatusService.updateProductSalesStatus(productSalesStatus));
    }

    /**
     * 删除产品销售状态管理
     */
    @PreAuthorize("@ss.hasPermi('rchives:sales_status:remove')")
    @Log(title = "产品销售状态管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(productSalesStatusService.deleteProductSalesStatusByIds(ids));
    }
}
