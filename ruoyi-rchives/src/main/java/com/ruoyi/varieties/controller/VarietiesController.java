package com.ruoyi.varieties.controller;

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
import com.ruoyi.varieties.domain.Varieties;
import com.ruoyi.varieties.service.IVarietiesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 品类管理Controller
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@RestController
@RequestMapping("/rchives/varieties")
public class VarietiesController extends BaseController
{
    @Autowired
    private IVarietiesService varietiesService;

    /**
     * 查询品类管理列表
     */
    @PreAuthorize("@ss.hasPermi('rchives:varieties:list')")
    @GetMapping("/list")
    public TableDataInfo list(Varieties varieties)
    {
        startPage();
        List<Varieties> list = varietiesService.selectVarietiesList(varieties);
        return getDataTable(list);
    }

    /**
     * 导出品类管理列表
     */
    @PreAuthorize("@ss.hasPermi('rchives:varieties:export')")
    @Log(title = "品类管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Varieties varieties)
    {
        List<Varieties> list = varietiesService.selectVarietiesList(varieties);
        ExcelUtil<Varieties> util = new ExcelUtil<Varieties>(Varieties.class);
        util.exportExcel(response, list, "品类管理数据");
    }

    /**
     * 获取品类管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('rchives:varieties:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(varietiesService.selectVarietiesById(id));
    }

    /**
     * 新增品类管理
     */
    @PreAuthorize("@ss.hasPermi('rchives:varieties:add')")
    @Log(title = "品类管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Varieties varieties)
    {
        return toAjax(varietiesService.insertVarieties(varieties));
    }

    /**
     * 修改品类管理
     */
    @PreAuthorize("@ss.hasPermi('rchives:varieties:edit')")
    @Log(title = "品类管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Varieties varieties)
    {
        return toAjax(varietiesService.updateVarieties(varieties));
    }

    /**
     * 删除品类管理
     */
    @PreAuthorize("@ss.hasPermi('rchives:varieties:remove')")
    @Log(title = "品类管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(varietiesService.deleteVarietiesByIds(ids));
    }
}
