package com.ruoyi.stock_check.controller;

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
import com.ruoyi.stock_check.domain.StockCheck;
import com.ruoyi.stock_check.service.IStockCheckService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 库存查询Controller
 * 
 * @author ruoyi
 * @date 2022-03-12
 */
@RestController
@RequestMapping("/warehouse/stock_check")
public class StockCheckController extends BaseController
{
    @Autowired
    private IStockCheckService stockCheckService;

    /**
     * 查询库存查询列表
     */
    @PreAuthorize("@ss.hasPermi('warehouse:stock_check:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody StockCheck stockCheck)
    {
        startPage();
        List<StockCheck> list = stockCheckService.selectStockCheckList(stockCheck);
        return getDataTable(list);
    }

    /**
     * 导出库存查询列表
     */
    @PreAuthorize("@ss.hasPermi('warehouse:stock_check:export')")
    @Log(title = "库存查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StockCheck stockCheck)
    {
        List<StockCheck> list = stockCheckService.selectStockCheckList(stockCheck);
        ExcelUtil<StockCheck> util = new ExcelUtil<StockCheck>(StockCheck.class);
        util.exportExcel(response, list, "库存查询数据");
    }

    /**
     * 获取库存查询详细信息
     */
    @PreAuthorize("@ss.hasPermi('warehouse:stock_check:query')")
    @PostMapping("/getInfo")
    public AjaxResult getInfo(@RequestBody Long id)
    {
        return AjaxResult.success(stockCheckService.selectStockCheckById(id));
    }

    /**
     * 新增库存查询
     */
    @PreAuthorize("@ss.hasPermi('warehouse:stock_check:add')")
    @Log(title = "库存查询", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StockCheck stockCheck)
    {
        return toAjax(stockCheckService.insertStockCheck(stockCheck));
    }

    /**
     * 修改库存查询
     */
    @PreAuthorize("@ss.hasPermi('warehouse:stock_check:edit')")
    @Log(title = "库存查询", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody StockCheck stockCheck)
    {
        return toAjax(stockCheckService.updateStockCheck(stockCheck));
    }

    /**
     * 删除库存查询
     */
    @PreAuthorize("@ss.hasPermi('warehouse:stock_check:remove')")
    @Log(title = "库存查询", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
    public AjaxResult remove(@RequestBody Long[] ids)
    {
        return toAjax(stockCheckService.deleteStockCheckByIds(ids));
    }
}
