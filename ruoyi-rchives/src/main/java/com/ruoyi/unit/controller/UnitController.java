package com.ruoyi.unit.controller;

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
import com.ruoyi.unit.domain.Unit;
import com.ruoyi.unit.service.IUnitService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 单位管理Controller
 * 
 * @author ruoyi
 * @date 2022-02-18
 */
@RestController
@RequestMapping("/rchives/unit")
public class UnitController extends BaseController
{
    @Autowired
    private IUnitService unitService;

    /**
     * 查询单位管理列表
     */
    @PreAuthorize("@ss.hasPermi('rchives:unit:list')")
    @GetMapping("/list")
    public TableDataInfo list(Unit unit)
    {
        startPage();
        List<Unit> list = unitService.selectUnitList(unit);
        return getDataTable(list);
    }

    /**
     * 导出单位管理列表
     */
    @PreAuthorize("@ss.hasPermi('rchives:unit:export')")
    @Log(title = "单位管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Unit unit)
    {
        List<Unit> list = unitService.selectUnitList(unit);
        ExcelUtil<Unit> util = new ExcelUtil<Unit>(Unit.class);
        util.exportExcel(response, list, "单位管理数据");
    }

    /**
     * 获取单位管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('rchives:unit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(unitService.selectUnitById(id));
    }

    /**
     * 新增单位管理
     */
    @PreAuthorize("@ss.hasPermi('rchives:unit:add')")
    @Log(title = "单位管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Unit unit)
    {
        return toAjax(unitService.insertUnit(unit));
    }

    /**
     * 修改单位管理
     */
    @PreAuthorize("@ss.hasPermi('rchives:unit:edit')")
    @Log(title = "单位管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Unit unit)
    {
        return toAjax(unitService.updateUnit(unit));
    }

    /**
     * 删除单位管理
     */
    @PreAuthorize("@ss.hasPermi('rchives:unit:remove')")
    @Log(title = "单位管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(unitService.deleteUnitByIds(ids));
    }
}
