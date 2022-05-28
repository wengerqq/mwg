package com.ruoyi.activiti.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.activiti.service.IProcessService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.activiti.domain.BizQualityInspection;
import com.ruoyi.activiti.service.IBizQualityInspectionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 采购质检Controller
 * 
 * @author ruoyi
 * @date 2022-05-20
 */
@RestController
@RequestMapping("/purchasee/inspection")
@AllArgsConstructor
public class BizQualityInspectionController extends BaseController
{

    private IBizQualityInspectionService bizQualityInspectionService;
    private IProcessService processService;

    /**
     * 查询采购质检列表
     */
    @PreAuthorize("@ss.hasPermi('purchasee:inspection:list')")
    @PostMapping("/list")
    public TableDataInfo list(BizQualityInspection bizQualityInspection)
    {
        startPage();
        List<BizQualityInspection> list = bizQualityInspectionService.selectBizQualityInspectionList(bizQualityInspection);
        return getDataTable(list);
    }

    /**
     * 导出采购质检列表
     */
    @PreAuthorize("@ss.hasPermi('purchasee:inspection:export')")
    @Log(title = "采购质检", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizQualityInspection bizQualityInspection)
    {
        List<BizQualityInspection> list = bizQualityInspectionService.selectBizQualityInspectionList(bizQualityInspection);
        ExcelUtil<BizQualityInspection> util = new ExcelUtil<BizQualityInspection>(BizQualityInspection.class);
        util.exportExcel(response, list, "采购质检数据");
    }

    /**
     * 获取采购质检详细信息
     */
    @PreAuthorize("@ss.hasPermi('purchasee:inspection:query')")
    @PostMapping("/getInfo")
    public AjaxResult getInfo(@RequestBody Long id)
    {
        return AjaxResult.success(bizQualityInspectionService.selectBizQualityInspectionById(id));
    }

    /**
     * 新增采购质检
     */
    @PreAuthorize("@ss.hasPermi('purchasee:inspection:add')")
    @Log(title = "采购质检", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizQualityInspection bizQualityInspection)
    {
        return toAjax(bizQualityInspectionService.insertBizQualityInspection(bizQualityInspection));
    }

    /**
     * 修改采购质检
     */
    @PreAuthorize("@ss.hasPermi('purchasee:inspection:edit')")
    @Log(title = "采购质检", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody BizQualityInspection bizQualityInspection)
    {
        return toAjax(bizQualityInspectionService.updateBizQualityInspection(bizQualityInspection));
    }

    /**
     * 删除采购质检
     */
    @PreAuthorize("@ss.hasPermi('purchasee:inspection:remove')")
    @Log(title = "采购质检", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
    public AjaxResult remove(@RequestBody Long[] ids)
    {
        return toAjax(bizQualityInspectionService.deleteBizQualityInspectionByIds(ids));
    }

    /**
     * 提交申请
     */
    @Log(title = "采购质检", businessType = BusinessType.UPDATE)
    @PostMapping( "/submitApply/{id}")
    @ResponseBody
    public AjaxResult submitApply(@PathVariable Long id) {
        try {
            BizQualityInspection bizQualityInspection = bizQualityInspectionService.selectBizQualityInspectionById(id);
        processService.submitApply(bizQualityInspection, "qualitylnspection");
        bizQualityInspectionService.updateBizQualityInspection(bizQualityInspection);
    } catch (Exception e) {
        e.printStackTrace();
        return error("提交申请出错：" + e.getMessage());
    }
        return success();
    }
}
