package com.ruoyi.put.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.ServletUtils;
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
import com.ruoyi.put.domain.PutDetail;
import com.ruoyi.put.service.IPutDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 上架管理Controller
 * 
 * @author ruoyi
 * @date 2022-05-04
 */
@RestController
@RequestMapping("/warehousing/put")
public class PutDetailController extends BaseController
{
    @Autowired
    private IPutDetailService putDetailService;

    /**
     * 查询上架管理列表
     */
    @PreAuthorize("@ss.hasPermi('warehousing:put:list')")
    @PostMapping("/list")
    public TableDataInfo list(PutDetail putDetail)
    {
        startPage();
        Map<String,Object> params = new HashMap<>();
        params.put("purchaseId",ServletUtils.getParameter("purchaseId"));
        params.put("putId",ServletUtils.getParameter("putId"));
        params.put("receiptId",ServletUtils.getParameter("receiptId"));
        params.put("warehouseId",ServletUtils.getParameter("warehouseId"));
        params.put("deptId",ServletUtils.getParameter("deptId"));
        putDetail.setParams(params);
        List<PutDetail> list = putDetailService.selectPutDetailList(putDetail);
        return getDataTable(list);
    }

    /**
     * 导出上架管理列表
     */
    @PreAuthorize("@ss.hasPermi('warehousing:put:export')")
    @Log(title = "上架管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PutDetail putDetail)
    {
        List<PutDetail> list = putDetailService.selectPutDetailList(putDetail);
        ExcelUtil<PutDetail> util = new ExcelUtil<PutDetail>(PutDetail.class);
        util.exportExcel(response, list, "上架管理数据");
    }

    /**
     * 获取上架管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('warehousing:put:query')")
    @PostMapping("/getInfo")
    public AjaxResult getInfo(@RequestBody Long id)
    {
        return AjaxResult.success(putDetailService.selectPutDetailById(id));
    }

    /**
     * 新增上架管理
     */
    @PreAuthorize("@ss.hasPermi('warehousing:put:add')")
    @Log(title = "上架管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PutDetail putDetail)
    {
        return toAjax(putDetailService.insertPutDetail(putDetail));
    }

    /**
     * 修改上架管理
     */
    @PreAuthorize("@ss.hasPermi('warehousing:put:edit')")
    @Log(title = "上架管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody PutDetail putDetail)
    {
        return toAjax(putDetailService.updatePutDetail(putDetail));
    }

    /**
     * 删除上架管理
     */
    @PreAuthorize("@ss.hasPermi('warehousing:put:remove')")
    @Log(title = "上架管理", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
    public AjaxResult remove(@RequestBody Long[] ids)
    {
        return toAjax(putDetailService.deletePutDetailByIds(ids));
    }

    /**
     * 根据采购ID获取上架管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('warehousing:put:query')")
    @PostMapping("/getPurchaseKeyIdInfo")
    public AjaxResult getPurchaseKeyIdInfo(@RequestBody Long purchaseKeyId)
    {
        return AjaxResult.success(putDetailService.selectPutDetailByPurchaseKeyId(purchaseKeyId));
    }

    /**
     * 确认上架
     */
    @PreAuthorize("@ss.hasPermi('warehousing:put:edit')")
    @Log(title = "上架管理", businessType = BusinessType.UPDATE)
    @PostMapping("/updatePutList")
    public AjaxResult updatePutList(@RequestBody List<PutDetail> putDetailList)
    {
        return toAjax(putDetailService.updatePutDetailList(putDetailList));
    }

    /**
     * 取消上架
     */
    @PreAuthorize("@ss.hasPermi('warehousing:put:remove')")
    @Log(title = "上架管理", businessType = BusinessType.DELETE)
    @PostMapping("/cancelPutList")
    public AjaxResult cancelPutList(@RequestBody List<PutDetail> putDetailList)
    {
        return toAjax(putDetailService.deletePutDetailList(putDetailList));
    }
}
