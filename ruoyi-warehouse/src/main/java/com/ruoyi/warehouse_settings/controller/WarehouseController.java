package com.ruoyi.warehouse_settings.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysDictDataService;
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
import com.ruoyi.warehouse_settings.domain.Warehouse;
import com.ruoyi.warehouse_settings.service.IWarehouseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 仓库设置Controller
 * 
 * @author ruoyi
 * @date 2022-03-08
 */
@RestController
@RequestMapping("/warehouse/warehouse_settings")
public class WarehouseController extends BaseController
{
    @Autowired
    private IWarehouseService warehouseService;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysDictDataService dictDataService;

    /**
     * 查询仓库设置列表
     */
    @PreAuthorize("@ss.hasPermi('warehouse:warehouse_settings:list')")
    @PostMapping("/list")
    public TableDataInfo list(Warehouse warehouse)
    {
        startPage();
        List<Warehouse> list = warehouseService.selectWarehouseList(warehouse);
        return getDataTable(list);
    }

    /**
     * 导出仓库设置列表
     */
    @PreAuthorize("@ss.hasPermi('warehouse:warehouse_settings:export')")
    @Log(title = "仓库设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Warehouse warehouse)
    {
        List<Warehouse> list = warehouseService.selectWarehouseList(warehouse);
        ExcelUtil<Warehouse> util = new ExcelUtil<Warehouse>(Warehouse.class);
        util.exportExcel(response, list, "仓库设置数据");
    }

    /**
     * 获取仓库设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('warehouse:warehouse_settings:query')")
    @PostMapping("/getInfo")
    public AjaxResult getInfo(@RequestBody Long id)
    {
        return AjaxResult.success(warehouseService.selectWarehouseById(id));
    }

    /**
     * 新增仓库设置
     */
    @PreAuthorize("@ss.hasPermi('warehouse:warehouse_settings:add')")
    @Log(title = "仓库设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Warehouse warehouse)
    {
        return toAjax(warehouseService.insertWarehouse(warehouse));
    }

    /**
     * 修改仓库设置
     */
    @PreAuthorize("@ss.hasPermi('warehouse:warehouse_settings:edit')")
    @Log(title = "仓库设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody Warehouse warehouse)
    {
        return toAjax(warehouseService.updateWarehouse(warehouse));
    }

    /**
     * 删除仓库设置
     */
    @PreAuthorize("@ss.hasPermi('warehouse:warehouse_settings:remove')")
    @Log(title = "仓库设置", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
    public AjaxResult remove(@RequestBody Long[] ids)
    {
        return toAjax(warehouseService.deleteWarehouseByIds(ids));
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<Warehouse> util = new ExcelUtil<Warehouse>(Warehouse.class);
        List<SysDept> deptList = sysDeptService.selectUserDeptList(new SysDept());
        List<SysDictData> dictStatusList = dictDataService.selectDictDataByType("sys_warehouse_status");
        List<SysDictData> dictTypeList = dictDataService.selectDictDataByType("sys_warehouse_type");
        List<SysDictData> dictOperateList = dictDataService.selectDictDataByType("sys_warehouse_operate");
        List<SysDictData> dictCountryList = dictDataService.selectDictDataByType("sys_country_region");

        Map<String, List> listData = new HashMap<>();
        listData.put("Dept",deptList);
        listData.put("DictStatus",dictStatusList);
        listData.put("DictType",dictTypeList);
        listData.put("DictOperate",dictOperateList);
        listData.put("DictCountry",dictCountryList);
        util.importTemplatesExcel(response, "仓库数据",listData,"warehouse");
    }

    @Log(title = "仓库设置", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('warehouse:warehouse_settings:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<Warehouse> util = new ExcelUtil<Warehouse>(Warehouse.class);
        List<Warehouse> warehouseList = util.importExcel(file.getInputStream());
        Long operatorId = getUserId();
        String message = warehouseService.importWarehouse(warehouseList, updateSupport, operatorId);
        return AjaxResult.success(message);
    }
}
